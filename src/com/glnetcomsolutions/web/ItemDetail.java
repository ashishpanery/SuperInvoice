package com.glnetcomsolutions.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.glnetcomsolutions.report.ChequeBean;
import com.glnetcomsolutions.report.ChequePrinter;
import com.glnetcomsolutions.report.DBConnection;
import com.glnetcomsolutions.report.EnvironmentalConstants;

/**
 * Servlet implementation class ChequeDetail
 */
public class ItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doProcess(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

		String requestType = request.getParameter("requestType");
		
		if("edit".equalsIgnoreCase(requestType)){
			

			
			String opr = request.getParameter("oper");
		
			if("add".equalsIgnoreCase(opr)){
				
//				String itemcode =  request.getParameter("itemcode");
				String item_desc =  request.getParameter("item_desc");
				String item_reorder_qty =  request.getParameter("item_reorder_qty");
				String item_sales_price_cash = request.getParameter("item_sales_price_cash");
				String item_sales_price_credit = request.getParameter("item_sales_price_credit");
				String supplier = request.getParameter("supplier");
				String date = request.getParameter("date");
				String item_retail_price = request.getParameter("item_retail_price");
				String qty = request.getParameter("qty");
				String u_o_m = request.getParameter("unit_of_measurement");
				String billno = request.getParameter("billno");

				String applicableTax = request.getParameter("applicable_tax");
				String itemcode = System.currentTimeMillis()+"";
				Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO ITEM_INVENTORY " +
						" (ITEMCODE,ITEM_DESC,ITEM_REORDER_QTY,ITEM_SALES_PRICE,ITEM_SALES_PRICE_CREDIT,SUPPLIER,TIMESTAMP,ITEM_RETAIL_PRICE,QTY,UNIT_OF_MEASUREMENT,BILL_NO,APPLICABLE_TAX)" +
						" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,)");
				ps.setString(1, itemcode);
				ps.setString(2, item_desc.toLowerCase());
				ps.setInt(3, Integer.valueOf(item_reorder_qty));
				ps.setDouble(4, Double.valueOf(item_sales_price_cash));
				ps.setDouble(5, Double.valueOf(item_sales_price_credit));
				ps.setString(6, supplier);
				ps.setString(7, date);
				ps.setDouble(8, Double.valueOf(item_retail_price));
				ps.setInt(9,Integer.valueOf(qty));
				ps.setString(10, u_o_m);
				ps.setString(11, billno);
				ps.setString(12, applicableTax);
				ps.execute();
				ps.close();
				conn.close();
				
			}
			if("edit".equalsIgnoreCase(opr)){
				
				String itemcode =  request.getParameter("id");
				String item_desc =  request.getParameter("item_desc");
				String item_reorder_qty =  request.getParameter("item_reorder_qty");
				String supplier = request.getParameter("supplier");
				String date = request.getParameter("date");
				String item_sales_price_cash = request.getParameter("item_sales_price_cash");
				String item_sales_price_credit = request.getParameter("item_sales_price_credit");
				String qty = request.getParameter("qty");
				String u_o_m = request.getParameter("unit_of_measurement");
				String billno = request.getParameter("billno");
				String applicableTax = request.getParameter("applicable_tax");

				String branch = request.getParameter("branch");
				
				Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("UPDATE ITEM_INVENTORY SET " +
						" ITEM_DESC=?,ITEM_REORDER_QTY=?,ITEM_SALES_PRICE=?,ITEM_SALES_PRICE_CREDIT=?,SUPPLIER=?," +
						"TIMESTAMP=?,ITEM_RETAIL_PRICE=?,QTY=?,UNIT_OF_MEASUREMENT=?,BILL_NO=?,APPLICABLE_TAX=? WHERE ITEMCODE=?");
				
				ps.setString(1, item_desc);
				ps.setInt(2, Integer.valueOf(item_reorder_qty));
				ps.setDouble(3, Double.valueOf(item_sales_price_cash));
				ps.setDouble(4, Double.valueOf(item_sales_price_credit));
				ps.setString(5, supplier);
				ps.setString(6, date);
				ps.setDouble(7, Double.valueOf(item_sales_price_cash));
				ps.setInt(8,Integer.valueOf(qty));
				ps.setString(9, u_o_m);
				ps.setString(10, billno);
				ps.setString(11, applicableTax);
				ps.setString(12, itemcode);
				
				ps.execute();
				ps.close();
				conn.close();
			}
			if("del".equalsIgnoreCase(opr)){
				
				String itemcode =  request.getParameter("id");
				Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM ITEM_INVENTORY WHERE ITEMCODE LIKE '"+itemcode+"'");
				ps.execute();
				ps.close();
				conn.close();
			}
			
		
			
		}else if(requestType.equalsIgnoreCase("export")){

			
			Connection conn = DBConnection.getConnection();
			String query = "SELECT * FROM CHEQUE_HISTORY";
			String currentTimeStamp = ""+System.currentTimeMillis();
			String filePath = EnvironmentalConstants.getReportPath()+File.separator+currentTimeStamp+".csv";
			PrintWriter pw = new PrintWriter(new File(filePath));
			java.sql.PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			StringBuilder sb = new StringBuilder();
			while(rs.next()){
				
				sb.append(rs.getInt("CHEQUEID"));
				sb.append(",");
				sb.append(rs.getString("CHEQUE_NUMBER"));
				sb.append(",");
				sb.append(rs.getString("PARTY"));
				sb.append(",");
				sb.append( rs.getInt("AMOUNT"));
				sb.append(",");
				sb.append( rs.getString("CHEQUE_TYPE"));
				sb.append(",");
				sb.append(rs.getString("BANK"));
				sb.append(",");
				sb.append(rs.getString("ISSUE_DATE"));
				sb.append(",");
				sb.append(rs.getString("ACCOUNT_NUMBER"));
				sb.append(",");
				sb.append(rs.getString("REMARKS"));
				sb.append("\n");
				pw.write(sb.toString());
				sb.setLength(0);
				pw.flush();
			}
			pw.close();
			rs.close();
			ps.close();
			conn.close();
			JSONObject obj = new JSONObject();
			obj.put("path", currentTimeStamp);
			ItemDetail.writeJSONResponse(response, obj);
		
			
		}else{
		
			Connection conn = DBConnection.getConnection();
			String query = "SELECT * FROM ITEM_INVENTORY";
			java.sql.PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			int total = 0;
			JSONArray arr = new JSONArray();
			JSONObject userobj = new JSONObject();
			long totalAmount = 0;
			while(rs.next()){
				JSONObject obj = new JSONObject();
				total++;
				obj.put("itemcode", rs.getString("ITEMCODE"));
				obj.put("item_desc", rs.getString("ITEM_DESC"));
				obj.put("supplier", rs.getString("SUPPLIER"));
				obj.put("qty", rs.getInt("QTY"));
				obj.put("item_retail_price", rs.getDouble("ITEM_RETAIL_PRICE"));
				obj.put("item_reorder_qty", rs.getInt("ITEM_REORDER_QTY"));
				obj.put("item_sales_price_cash", rs.getDouble("ITEM_SALES_PRICE"));
				obj.put("item_sales_price_credit", rs.getDouble("ITEM_SALES_PRICE_CREDIT"));
				obj.put("unit_of_measurement", rs.getString("UNIT_OF_MEASUREMENT"));
				obj.put("date",rs.getString("TIMESTAMP"));
				obj.put("billno",rs.getString("BILL_NO"));
				obj.put("applicable_tax",rs.getString("APPLICABLE_TAX"));
				
				
				obj.put("oper","<button onclick=\"javascript:printBarCode('"+rs.getString("ITEMCODE")+"','"+ rs.getDouble("ITEM_SALES_PRICE")+"')\">Print Bar Code</button>");
				obj.put("checkbox","<input type='checkbox' id='"+rs.getString("ITEMCODE")+"' onclick='javascript:selectItem(this,"+rs.getDouble("ITEM_SALES_PRICE")+");'>");
				arr.add(obj);
			}
			rs.close();
			ps.close();
			conn.close();
			
			JSONObject object = new JSONObject();
			object.put("page", "1");
			object.put("total", 1);
			object.put("records", ""+total);
			object.put("rows", arr);
			object.put("userdata", userobj);
			ItemDetail .writeJSONResponse(response, object);
		}
		
	}
	public static void writeJSONResponse(HttpServletResponse response, JSONObject jsonObject) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        final String jsonResponse = jsonObject.toJSONString();
        response.setContentLength(jsonResponse.length());
        response.getWriter().write(jsonResponse);
      response.flushBuffer();
    }
}
