<%@page import="java.io.Writer"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="com.glnetcomsolutions.web.Util"%>
<%@page import="org.hsqldb.store.ReusableObjectCache"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.glnetcomsolutions.report.DBConnection"%>
<%@page import="java.sql.*"%>
<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.print.*" %>
    <%
    	

	response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=\"invoice-history.csv\"");
    Connection conn = null;
    java.sql.PreparedStatement ps = null;
    ResultSet rs = null;
    try
    {
    	PrintWriter outputStream = response.getWriter();
        StringBuilder outputResult = new StringBuilder();
        outputResult.append("INVOICE NO, INVOICE DATE, CUSTOMER NAME," +
        		" INVOICE ITEMS, TOTAL INVOICE AMOUNT,TAX AMOUNT" +
        		" \n");
        conn = DBConnection.getConnection();
		String query = "SELECT * FROM INVOICEMASTER";
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		int total = 0;
		long totalAmount = 0;
	
	
		while(rs.next()){
			JSONObject obj = new JSONObject();
			total++;
			outputResult.append(rs.getString("INVOICENO"));
			outputResult.append(",");
			outputResult.append(rs.getString("INVOICEDATE"));
			outputResult.append(",");
			outputResult.append(rs.getString("CUSTOMERNAME"));
			outputResult.append(",");
			outputResult.append(rs.getString("TOTALITEM"));
			outputResult.append(",");
			outputResult.append(rs.getString("TOTAL"));
			outputResult.append(",");
			outputResult.append(rs.getString("TAXAMOUNT"));
			
			outputResult.append("\n");
		}
		
        outputStream.write(outputResult.toString());
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

    %> 
