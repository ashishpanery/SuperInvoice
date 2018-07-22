<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.glnetcomsolutions.report.DBConnection"%>
<%@page import="com.glnetcomsolutions.report.LicenseBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<%@page import="javax.servlet.http.Part" %>
<%@ page import ="java.io.PrintWriter" %>
<%@page import="java.sql.*" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>

<title>Super Invoice</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->



<!-- JavaScripts -->

</head>

<body >
<%  
String vendorName = request.getParameter("vendorname");
String vId = request.getParameter("vendorid");
	String Authkey = request.getParameter("authkey");
	String Route = request.getParameter("route");
	String Countrycode= request.getParameter("countrycode");
	 	  
	 Connection conn = null;
	PreparedStatement ps = null;
	 try{
		
		 conn = DBConnection.getConnection();
		 System.out.println(conn);
		 ps = conn.prepareStatement("INSERT INTO VENDORDETAILS (VENDORNAME,VENDORURL,AUTHKEY,ROUTE,COUNTRYCODE) VALUES(?,?,?,?,?)");
	
		 ps.setString(1, vendorName);
		 ps.setString(2, vId);
		 ps.setString(3, Authkey);
		 ps.setString(4, Route);
		 ps.setString(5, Countrycode);
		 
		ps.execute();
	
			// int row = ps.executeUpdate();

		// if (row > 0) {
			//out.println("Data saved into database");
        // }
	 } 
	  catch(Exception e1){
		 e1.printStackTrace();
	 }finally{
		 if(ps != null){
			 ps.close();
		 }
		 if(conn != null){
			 conn.close();
		 }
	 } 
	response.sendRedirect("SMSForm.jsp");
	 out.print("saved");
	%>
	<div class=outerDiv>
		
<div class="mainContainer"
			style="width: 760px; margin: 0 auto;">

</div>
<div style="text-align:right;">Powered By : <a href="http://9cube.in" target="_blank">9 Cube.in</a> Udaipur- 9024024250</div>
							
	    </div>

						</body>

</html>