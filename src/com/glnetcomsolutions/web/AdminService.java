package com.glnetcomsolutions.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.glnetcomsolutions.report.DBConnection;

/**
 * Servlet implementation class AdminService
 */

public class AdminService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		try {
			doProcess(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		
		String companyName = request.getParameter("companyName");
		String acNumber = request.getParameter("acNumber");
		String bankName = request.getParameter("bankName");
		String defaultPrinter = request.getParameter("defaultPrinter");

		System.out.println(companyName+","+acNumber+","+bankName+","+defaultPrinter);
		Connection conn = DBConnection.getConnection();
		
		java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO admin VALUES(?,?,?,?)");
		ps.setString(1, companyName);
		ps.setString(2, acNumber);
		ps.setString(3, bankName);
		ps.setString(4, defaultPrinter);
		System.out.println(ps.execute());
		ResultSet rs;
		 Statement stat = conn.createStatement();
        rs = stat.executeQuery("select * from ADMIN");
        while (rs.next()) {
            System.out.println(rs.getString("company_name"));
        }
        stat.close();
		ps.close();
		conn.close();
	}
	
}
