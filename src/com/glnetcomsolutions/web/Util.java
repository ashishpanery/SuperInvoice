package com.glnetcomsolutions.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.glnetcomsolutions.report.DBConnection;

public class Util {
	public static String convertLongToString(long timestamp) throws ParseException{

		Date d = new Date(timestamp);
		return d.toString();
	}
	public static long convertStringDateToLong(String date) throws ParseException{

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date d = f.parse(date);
		return d.getTime();
	}
	@SuppressWarnings("deprecation")
	public static int getYearFromDate(String date) throws ParseException{

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date d = f.parse(date);
		
		return d.getYear()+1900;
	}
	public static int getMonthFromDate(String date) throws ParseException{

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date d = f.parse(date);
	
		return d.getMonth()+1;
	}
	public static int getDateFromDate(String date) throws ParseException{

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date d = f.parse(date);
		
		return d.getDate();
	}

	public static String getUserRole(String username,String password) throws ClassNotFoundException, SQLException{
		String role = null;
		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
		java.sql.PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		
		while(rs.next()){
			role = rs.getString("ROLE");
		}
		rs.close();
		ps.close();
		conn.close();
		
		return role;
		
	}
	
	public static void writeJSONResponse(HttpServletResponse response, JSONObject jsonObject) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        final String jsonResponse = jsonObject.toJSONString();
        response.setContentLength(jsonResponse.length());
        response.getWriter().write(jsonResponse);
      response.flushBuffer();
    }
	public static void createExcelReport(HttpServletResponse response){
		response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=\"invoice-history.csv\"");
	    Connection conn = null;
	    java.sql.PreparedStatement ps = null;
	    ResultSet rs = null;
	    try
	    {
	        OutputStream outputStream = response.getOutputStream();
	        StringBuilder outputResult = new StringBuilder();
	        outputResult.append("INVOICE NO, INVOICE DATE, DUE DATE, CUSTOMER NAME," +
	        		" CUSTOMER ADDRESS, INVOICE ITEM, TOTAL, CASH, " +
	        		"DUE,MOBILE NUMBER, SALES MAN \n");
	        conn = DBConnection.getConnection();
			String query = "SELECT * FROM INVOICEMASTER";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			int total = 0;
			long totalAmount = 0;
			while(rs.next()){
				JSONObject obj = new JSONObject();
				total++;
				outputResult.append(rs.getInt("INVOICENO"));
				outputResult.append(",");
				outputResult.append(rs.getString("INVOICEDATE"));
				outputResult.append(",");
				outputResult.append(rs.getString("DUEDATE"));
				outputResult.append(",");
				outputResult.append(rs.getString("CUSTOMERNAME"));
				outputResult.append(",");
				outputResult.append(rs.getString("CUSTOMERADDRESS"));
				outputResult.append(",");
				outputResult.append(rs.getString("TOTALITEM"));
				outputResult.append(",");
				outputResult.append(rs.getString("TOTAL"));
				outputResult.append(",");
				outputResult.append(rs.getString("CASH"));
				outputResult.append(",");
				outputResult.append(rs.getString("DUE"));
				outputResult.append(",");
				outputResult.append(rs.getString("MOBILENUMBER"));
				outputResult.append(",");
				outputResult.append(rs.getString("SALESMAN"));
				outputResult.append("\n");
			}
			
	        outputStream.write(outputResult.toString().getBytes());
	        outputStream.flush();
	        outputStream.close();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }finally{
	    	try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		System.out.println("Asdhs");
		System.out.println(Util.getUserRole("administrator", "password"));
	}
}
