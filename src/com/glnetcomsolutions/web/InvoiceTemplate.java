package com.glnetcomsolutions.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.glnetcomsolutions.report.DBConnection;

/**
 * Servlet implementation class LicenceServlet
 */
public class InvoiceTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceTemplate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operation = request.getParameter("operation");
		System.out.println("oper="+operation);
		if("save".equalsIgnoreCase(operation)){
			String part =  request.getParameter("part");
			String content =  request.getParameter("content");

			Connection conn = null;
			java.sql.PreparedStatement ps = null;
			try{
				conn = DBConnection.getConnection();
				String query = "UPDATE INVOICETEMPLATE SET HTMLCONTENT = ? WHERE PART = ?";
				 ps = conn.prepareStatement(query);
				 ps.setString(1, content);
				 ps.setString(2, part);
				 ps.execute();
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				if (ps != null){
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}else if ("get".equalsIgnoreCase(operation)){

			String part =  request.getParameter("part");
			Connection conn = null;
			java.sql.PreparedStatement ps = null;
			ResultSet rs = null;
			String content = "";
			JSONObject obj = new JSONObject();
			try{
				conn = DBConnection.getConnection();
				String query = "SELECT * FROM INVOICETEMPLATE";
				 ps = conn.prepareStatement(query);
				 rs = ps.executeQuery();
				
				 while(rs.next()){
					 obj.put(rs.getString("PART"), rs.getString("HTMLCONTENT"));
				 }
				 
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				if (rs != null){
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (ps != null){
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			ItemDetail.writeJSONResponse(response, obj);
		}
		
	}
}
