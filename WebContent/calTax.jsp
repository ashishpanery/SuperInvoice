<%@page import="com.glnetcomsolutions.web.CalTax"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.glnetcomsolutions.web.Util"%>
<%@page import="org.hsqldb.store.ReusableObjectCache"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.glnetcomsolutions.report.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.print.*" %>
    <%
    
    CalTax.calTax();
	
   %> 
