<%@page import="com.glnetcomsolutions.web.InvoiceMaster"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.glnetcomsolutions.report.EnvironmentalConstants"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<%@page import="com.glnetcomsolutions.web.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.print.*" %>
    <%

%> 
<!DOCTYPE html>
<html>
<head>
<%
	
	JSONObject organizationDetail = InvoiceMaster.getOrganizationDetail();

	%>
<title> 9 Cube - Super Invoice</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->

<link rel="stylesheet" href="css/jquery.dataTables.css" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />
<link rel="stylesheet" href="css/ui.jqgrid.css" type="text/css" />

<!-- JavaScripts -->
<script src="js/lib/jquery-2.1.0.min.js"></script>

<script src="js/lib/angular-sanitize.min.js"></script>
<script src="js/lib/bootstrap.min.js"></script>
<script src="js/lib/jquery-ui.js"></script>

<script src="js/grid.locale-en.js"></script>
<script src="js/jquery.jqGrid.min.js"></script>

<style type="text/css">
#organizationTable{
	
	width: 600px;
	margin: 0 auto;
	
}
#organizationTable td{
	padding: 1px;
}
#organizationTable input {
	margin: 1px;
	width: 100%;
}
</style>
</head>

<body>
	<div class=outerDiv>
				<%@ include file="navbar.jsp" %>	

		
		<div class="mainContainer" 
			style=" margin: 0 auto;">
			
						
			    					
			    					<div>
			    						<table id="loaderr" style="width:100%;hieght:600px"></table>
			    						<div id="ploaderr" style="width:100%;hieght:150px"></div>
			    					</div>
			    					
			       					<br><br><br>
			    					<div>
			    						<table id="loaderr_tax" style="width:100%;hieght:600px"></table>
			    						<div id="ploaderr_tax" style="width:100%;hieght:150px"></div>
			    					</div>
			            			
		          </div>
			
			</div>
		<script type="text/javascript"  src="js/adminuser.js">

	  </script>
	  
</body>
<script type="text/javascript">
ready();
</script>
</html>