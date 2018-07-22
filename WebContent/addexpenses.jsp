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
    	Connection conn = null;
        PreparedStatement ps = null;
        try{
    		String e_category = request.getParameter("e_category");
    		String e_date = request.getParameter("e_date");
    		String e_amount = request.getParameter("e_amount");
    		String e_submit_by = request.getParameter("e_submit_by");
    		String e_description = request.getParameter("e_description");
    		String e_id = "" + System.currentTimeMillis();

    		conn = DBConnection.getConnection();
    		String query = "INSERT INTO EXPENSES"
    				+ " (CATEGORY,EXPENSE_DATE,AMOUNT,SUBMITTED_BY,"
    				+ " DESCRIPTION,E_ID, YEAR, MONTH, DATE)"
    				+ " VALUES (?,?,?,?,?,?,?,?,?)";

    		int i = 1;
    		ps = conn.prepareStatement(query);
    		ps.setString(i++, e_category);
    		ps.setLong(i++, Util.convertStringDateToLong(e_date));
    		ps.setDouble(i++,
    				Double.valueOf(e_amount));
    		ps.setString(i++, e_submit_by);
    		ps.setString(i++, e_description);
    		ps.setString(i++, e_id);
			ps.setInt(i++, Util.getYearFromDate((e_date)));
			ps.setInt(i++, Util.getMonthFromDate(e_date));
			ps.setInt(i++, Util.getDateFromDate(e_date));
    		ps.execute();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		if (ps != null) {
    			ps.close();
    		}
    		if (conn != null) {
    			conn.close();
    		}
    	}

    	response.sendRedirect("dashboard.jsp");
    %> 
<!DOCTYPE html>
<html>
<head>
<title> 9 Cube - Super Invoice</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->

<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />
<link rel="stylesheet" href="css/ui.jqgrid.css" type="text/css" />

<!-- JavaScripts -->
<script src="js/lib/jquery-2.1.0.min.js"></script>

<script src="js/lib/bootstrap.min.js"></script>
<script src="js/lib/jquery-ui.js"></script>

<script src="js/grid.locale-en.js"></script>
<script src="js/jquery.jqGrid.min.js"></script>


</head>

<body>
	
			<p> Your Expense has been added.
		
			
		
</body>
<script type="text/javascript">

</script>
</html>