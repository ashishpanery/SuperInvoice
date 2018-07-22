package com.glnetcomsolutions.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.glnetcomsolutions.report.DBConnection;
import com.glnetcomsolutions.report.EnvironmentalConstants;

/**
 * Servlet implementation class InvoiceMasterServlet
 */
public class ExpenseMasterServlet_d extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExpenseMasterServlet_d() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				doProcess(request, response);
			} catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

		String requestType = request.getParameter("requestType");
		if("getExpenseHistory".equals(requestType)){
			ItemDetail.writeJSONResponse(response, ExpenseManager.getAllExpenses());
		}else if("getExpensePieData".equals(requestType)){
			ItemDetail.writeJSONResponse(response, ExpenseManager.getExpensesByCategory(request.getParameter("reportType")));
		}else if("getExpenseBarData".equals(requestType)){
			ItemDetail.writeJSONResponse(response, ExpenseManager.getBarChartData(request.getParameter("reportType")));
		}else if("editexpenses".equals(requestType)){
			doExpenseEdit(request, response);
		}else if("createExpenseTable".equals(requestType)){
			ExpenseManager.createExpenseTable();
		}else if("dropExpenseTable".equals(requestType)){
			ExpenseManager.dropExpenseTable();
		}
		else if("getExpenseDueData".equals(requestType)){
			ItemDetail.writeJSONResponse(response, ExpenseManager.getDueData());
			//ExpenseManager.getDueData();
		}
	}

	@SuppressWarnings("unchecked")
	protected void doExpenseEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ClassNotFoundException, SQLException, ParseException {

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();

			String requestType = request.getParameter("requestType");

			if ("editexpenses".equalsIgnoreCase(requestType)) {

				String opr = request.getParameter("oper");

				if ("add".equalsIgnoreCase(opr)) {}
				if ("edit".equalsIgnoreCase(opr)) {
				
						
					String category = request.getParameter("category");
					String description = request.getParameter("description");
					String month = request.getParameter("month");
					String year = request.getParameter("year");
					String submittedBy = request.getParameter("submitted_by");
					String date = request.getParameter("expense_date");
					String expense_date = request.getParameter("expense_date");
					String amount = request.getParameter("amount");
					
					String eid = request.getParameter("id");
					String query = "UPDATE EXPENSES SET "
							+ " CATEGORY = ?, EXPENSE_DATE = ?, AMOUNT = ?,SUBMITTED_BY=?,"
							+ " DESCRIPTION=?, YEAR=?, MONTH=?, DATE=? WHERE E_ID=?";
							

					int i = 1;
					ps = conn.prepareStatement(query);
					ps.setString(i++, category);
					ps.setLong(i++, Util.convertStringDateToLong(expense_date));
					ps.setDouble(i++, Double.valueOf(amount));
					ps.setString(i++, submittedBy);
					ps.setString(i++, description);
					ps.setInt(i++, Util.getYearFromDate(date));
					ps.setInt(i++, Util.getMonthFromDate(date));
					ps.setInt(i++, Util.getDateFromDate(date));
					ps.setString(i++, eid);
					
					ps.execute();
				}
				if ("del".equalsIgnoreCase(opr)) {

					String p_id = request.getParameter("id");
					 ps = conn
							.prepareStatement("DELETE FROM EXPENSES WHERE E_ID LIKE '"
									+ p_id + "'");
					ps.execute();
				}

			} else if (requestType.equalsIgnoreCase("export")) {

				String query = "SELECT * FROM PROJECT";
				String currentTimeStamp = "" + System.currentTimeMillis();
				String filePath = EnvironmentalConstants.getReportPath()
						+ File.separator + currentTimeStamp + ".csv";
				PrintWriter pw = new PrintWriter(new File(filePath));
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				StringBuilder sb = new StringBuilder();
				while (rs.next()) {
					
					sb.append(rs.getInt("PROJECT_ID"));
					sb.append(",");
					sb.append(rs.getString("PROJECT_NAME"));
					sb.append(",");
					sb.append(rs.getString("PROJECT_START_DATE"));
					sb.append(",");
					sb.append(rs.getInt("CLIENT_NAME"));
					sb.append(",");
					sb.append(rs.getInt("DURATION"));
					sb.append(",");
					sb.append(rs.getDouble("PROJECT_AMOUNT"));
					sb.append(",");
					sb.append(rs.getString("DESCRIPTION"));
					
					sb.append("\n");
					pw.write(sb.toString());
					sb.setLength(0);
					pw.flush();
				}
				JSONObject obj = new JSONObject();
				obj.put("path", currentTimeStamp);
				ItemDetail.writeJSONResponse(response, obj);

			} 
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
		}

	}
}
