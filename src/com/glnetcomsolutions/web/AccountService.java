package com.glnetcomsolutions.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class AccountService
 */
public class AccountService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountService() {
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
		
		String requestType = request.getParameter("requestType");
		
		if("editaccounts".equalsIgnoreCase(requestType)){
			
			String opr = request.getParameter("oper");
		
			if("add".equalsIgnoreCase(opr)){
				
				String acNumber =  request.getParameter("acnumber");
				String acHolder =  request.getParameter("acholder");
				String currentBalance =  request.getParameter("currentbalance");
				String bank = request.getParameter("bank");
				int balance = currentBalance != null ? Integer.parseInt(currentBalance):0;
				Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO ACCOUNTS VALUES(?,?,?,?)");
				ps.setString(1, acNumber);
				ps.setString(2, acHolder);
				ps.setInt(3, balance);
				ps.setString(4, bank);
				ps.execute();
				ps.close();
				conn.close();
				
			}
			if("edit".equalsIgnoreCase(opr)){
				
				String acNumber =  request.getParameter("acnumber");
				String acHolder =  request.getParameter("acholder");
				String currentBalance =  request.getParameter("currentbalance");
				String bank = request.getParameter("bank");
				int balance = currentBalance != null ? Integer.parseInt(currentBalance):0;
				Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("UPDATE ACCOUNTS SET BALANCE = "+balance+", BANK = '"+bank+"', ACHOLDER = '"+acHolder+"' WHERE ACNUMBER LIKE '"+acNumber+"'");
				ps.execute();
				ps.close();
				conn.close();
			}
			if("del".equalsIgnoreCase(opr)){
				
				String acNumber =  request.getParameter("id");
				Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM ACCOUNTS WHERE ACNUMBER LIKE '"+acNumber+"'");
				ps.execute();
				ps.close();
				conn.close();
			}
			
		}else if ("getallaccount".equalsIgnoreCase(requestType)){
			Connection conn = DBConnection.getConnection();
			ResultSet rs;
			Statement stat = conn.createStatement();
			rs = stat.executeQuery("SELECT * FROM ACCOUNTS");
			int total = 0;
			JSONArray rowArr = new JSONArray();
			while (rs.next()) {
				JSONObject row = new JSONObject();
				row.put("id", rs.getString("acnumber"));
				JSONArray rowData = new JSONArray();
				rowData.add(rs.getString("acnumber"));
				rowData.add(rs.getString("acholder"));
				rowData.add(rs.getInt("balance"));
				rowData.add(rs.getString("bank"));
				row.put("cell", rowData);
				rowArr.add(row);
				total++;
			}
			stat.close();
			conn.close();
			JSONObject obj = new JSONObject();
			obj.put("rows", rowArr);
			obj.put("page", "1");
			obj.put("total", "1");
			obj.put("records", total);
			Util.writeJSONResponse(response, obj);
		
		}
	}
}
