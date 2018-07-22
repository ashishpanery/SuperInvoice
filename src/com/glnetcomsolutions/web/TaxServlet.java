package com.glnetcomsolutions.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.glnetcomsolutions.report.DBConnection;

/**
 * Servlet implementation class UserServlet
 */
public class TaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaxServlet() {
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
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {

		String requestType = request.getParameter("requestType");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			if ("edittax".equals(requestType)) {

				String opr = request.getParameter("oper");

				if ("add".equalsIgnoreCase(opr)) {
					
					String taxid = System.currentTimeMillis()+"";
					String taxName = request.getParameter("taxname");
					String taxValue = request.getParameter("taxvalue");
					conn = DBConnection.getConnection();
					ps = conn.prepareStatement("INSERT INTO TAX (TAXID,TAXNAME,TAXVALUE) VALUES(?,?,?) ");
					ps.setString(1, taxid);
					ps.setString(2, taxName);
					ps.setString(3, taxValue);
					ps.execute();

				} else if ("edit".equalsIgnoreCase(opr)) {

					String id = request.getParameter("id");
					String taxName = request.getParameter("taxname");
					String taxValue = request.getParameter("taxvalue");

					conn = DBConnection.getConnection();
					ps = conn.prepareStatement("UPDATE TAX SET "
							+ " TAXNAME=?,TAXVALUE=? WHERE TAXID=?");

					ps.setString(1, taxName);
					ps.setString(2, taxValue);
					ps.setString(3, id);

					ps.execute();

				} else if ("del".equalsIgnoreCase(opr)) {
					String id = request.getParameter("id");

					conn = DBConnection.getConnection();
					ps = conn.prepareStatement("DELETE FROM TAX WHERE TAXID=?");

					ps.setString(1, id);

					ps.execute();

				}

			} else if("getAllTaxes".equals(requestType)){
				conn = DBConnection.getConnection();
				String query = "SELECT * FROM TAX";
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				JSONObject obj = new JSONObject();
				while (rs.next()) {
					obj.put(rs.getString("TAXID"),rs.getString("TAXNAME")+"("+rs.getString("TAXVALUE")+")");
				}
			System.out.println(obj.toJSONString());
				
				ItemDetail.writeJSONResponse(response, obj);
			}else {
				conn = DBConnection.getConnection();
				String query = "SELECT * FROM TAX";
				ps = conn.prepareStatement(query);
				rs = ps.executeQuery();
				int total = 0;
				JSONArray arr = new JSONArray();
				JSONObject userobj = new JSONObject();
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					total++;
					obj.put("taxid", rs.getString("TAXID"));
					obj.put("taxname", rs.getString("TAXNAME"));
					obj.put("taxvalue", rs.getString("TAXVALUE"));
					arr.add(obj);
				}

				JSONObject object = new JSONObject();
				object.put("page", "1");
				object.put("total", 1);
				object.put("records", "" + total);
				object.put("rows", arr);
				object.put("taxdata", userobj);
				ItemDetail.writeJSONResponse(response, object);
			}

		}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(rs != null)
			rs.close();
		if(ps != null)
			ps.close();
		if(conn != null)
			conn.close();
	}
	}

}
