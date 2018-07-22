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
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {

		String requestType = request.getParameter("requestType");
		if("edituser".equals(requestType)){
			
			String id =  request.getParameter("id");
			String password =  request.getParameter("password");
			String role =  request.getParameter("role");
			String username = request.getParameter("username");
			Connection conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE USER SET " +
					" PASSWORD=?,ROLE=?,FNAME=? WHERE USERNAME=?");
			
			ps.setString(1, password);
			ps.setString(2, role);
			ps.setString(3, username);
			ps.setString(4, id);
			
			ps.execute();
			ps.close();
			conn.close();
			
			
		}else{
			Connection conn = DBConnection.getConnection();
			String query = "SELECT * FROM USER";
			java.sql.PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			int total = 0;
			JSONArray arr = new JSONArray();
			JSONObject userobj = new JSONObject();
			long totalAmount = 0;
			while(rs.next()){
				JSONObject obj = new JSONObject();
				total++;
				obj.put("loginid", rs.getString("USERNAME"));
				obj.put("password", rs.getString("PASSWORD"));
				obj.put("role", rs.getString("ROLE"));
				obj.put("username", rs.getString("FNAME"));
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

}
