<%@page import="com.glnetcomsolutions.web.ExpenseManager"%>
<%@page import="com.glnetcomsolutions.report.EnvironmentalConstants"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<%@page import="com.glnetcomsolutions.web.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.print.*" %>
<!DOCTYPE html>
<html>
<head>


<title> 9 Cube - Super Project Manager</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->
<style type="text/css">
#barcode_table table td{
padding: 10px;
}
#barcode_table table{
	margin: 0 auto;
	border: 1px solid gray;
}
</style>
<link rel="stylesheet" href="css/jquery.dataTables.css" type="text/css" />


<!-- JavaScripts -->

</head>

<body>
	<div class=outerDiv>
			<%@ include file="navbar.jsp" %>
	    </div>
		
		<div class="mainContainer" 
			style=" margin: 0 auto;width:90%;">
			
			 <div class="table-responsive">
				<div>Report Interval : 
				<select id="reportType" onchange="javascript : changeChartDuration()">
					<option value="daily">Last 1 Month (Day Wise)</option>
					<option value="monthly">Last 1 Year(Month Wise)</option>
					<option value="yearly">Year Wise</option>
				</select></div>
			
				
				
			<div>
				<div id="pie_chart_container" style="height:400px;width:40%;float:left;">
				
						<div id="pie_container" style="min-width: 210px; height: 400px; max-width: 300px; margin: 0 auto"></div>
				</div>
				<div id="bar_chart_container" style="height:400px;width:60%;float:right;">
					
							<div id="bar_container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
				</div>
		
			</div>
			    				
				    					<div id="inventory_table" style="display:block;">
			    						<div>
			    						<button onclick="javascript:window.open('expenses.jsp','_self');">Add New Expense</button>
			    						&nbsp;&nbsp;Group By: <select id="dynamicGrouping">
											<option value="clear">No Grouping</option>
											<option value="category">category</option>
											<option value="expense_date"> Date</option>
											<option value="month"> Month</option>
											<option value="year"> Year</option>
											<option value="submitted_by">submitted by </option>
											
										</select>
										
			    						<table id="loaderr"></table>
			    						<div id="ploaderr" ></div>
			    					</div>
			    					<hr>
			    					<br>
			    					
			    					
			    					</div>			    							       
			            
		          </div>
			
			</div>
			<div id="outside">
			<table id="mytable">
			<tr><th>Itemlist</th><tr>
			
			</table>
			
			</div>
	
</body>

<script type="text/javascript">

ready();

</script>
</html>