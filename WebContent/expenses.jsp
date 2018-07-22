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
<%
	
	String user = null;
	String role = null;
	if(session.getAttribute("user") != null){
		user = (String) session.getAttribute("user");
		role = (String) session.getAttribute("role");
	}else{
		
		user = request.getParameter("loginId");
		String password = request.getParameter("password");
		role = Util.getUserRole(user, password);
		if (role != null && ("admin".equals(role)))
		{
			session.setAttribute("user", user);
			session.setAttribute("role", role);
		}else{
			
			response.sendRedirect("login.jsp");
			
		}
	}
	%>
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
	<div class=outerDiv>
			<div class="navbar navbar-inverse" role="navigation">
	      	<div class="container-fluid">
				<div class="navbar-header" style="margin-top: 0.2%;">
					<span style="color: white !important; font-size: 20px;">Super Invoice</span>
				</div>
				<div class="navbar-collapse collapse">
			
				<ul class="nav navbar-nav navbar-right nav-web-perf">
						<li class="active"><a href="dashboard.jsp"><span
								class="glyphicon glyphicon-stats"></span>Dashboard</a></li>
						<li><a href="invoicemaster.jsp"><span
								class="glyphicon glyphicon-stats"></span>Invoice Master</a></li>
								<li><a href="InvoiceHistory.jsp"><span class="glyphicon glyphicon-saved"></span>Invoice History</a></li>
							
						<li><a href="Inventory.jsp"><span
								class="glyphicon glyphicon-flash"></span>Inventory</a></li>
						
								<li><a href="admin.jsp"><span
								class="glyphicon glyphicon-wrench"></span>Admin</a></li>
						<li><a href="logout.jsp" class="active"><span
								class="glyphicon glyphicon-user"></span>Log Out</a></li>
					</ul>
				</div>
			</div>
	      </div>
	    </div>
		
		<div class="mainContainer" 
			style=" margin: 0 auto;width:90%;">
			
			 <div class="table-responsive">
				<div id="barcode_table">
					<form method="POST" action="addexpenses.jsp" onsubmit="return verifyExpensesForm();" name="myForm">
						<table>
						<tr style="background: #2e6e9e;color:white;">
							<td colspan="4"><div> Add a New Expenses</div></td>
						</tr>
							<tr>
								<td>
									Expense Category
								</td>
								<td>
									<input type="text" id="e_category" name="e_category">
								</td>
								<td>
									 Date
								</td>
								<td>
									<input type="date" id="e_date" name="e_date">
								</td>
							</tr>
							<tr>
								<td>
									Amount
								</td>
								<td>
									<input type="text" id="e_amount" name="e_amount" onkeypress="numeralsOnly(event);">
								</td>
								<td>
									Submitted By
								</td>
								<td>
									<input type="text" id="e_submit_by"  name="e_submit_by">
								</td>
								
							</tr>
							
							<tr>
								<td>
									 Description
								</td>
								<td colspan="3">
									<textarea rows="3" cols="25" style="width:100%;" id="e_description" name="e_description"></textarea>
								</td>
								
							</tr>
							<tr>
								<td colspan="4" style="text-align:  right;">
								<input type="submit" value="Add">
										<input type="reset" value="Reset">
										
								</td>
							</tr>
						</table>
					</form>
					<div style="
    text-align: center;padding-top:10px;
"><button onclick="window.open('dashboard.jsp','_self')">Go Back</button></div>
						
						
					</div>
			
			    					
			    					
			    					
			       
			            
		          </div>
			
			
			</div>
		<script type="text/javascript">
			var selectedItem = new Object();
	  </script>
	  
	<!-- <script>
		angular.module('webLT', ['ngSanitize']);
	</script>
	-->
</body>
<script type="text/javascript">
function verifyNewProject(){
	console.log("Hi");
	if(document.myForm.e_category.value == ""){
		alert("Category Can't be empty. Please provide Category name.");
		document.myForm.e_category.focus();
		return false;
	}
	if(document.myForm.e_date.value == ""){
		alert("Date Can't be empty. Please provide date.");
		document.myForm.e_date.focus();
		return false;
	}
	
	if(document.myForm.e_amount.value == ""){
		alert(" Amount Can't be empty. Please provide  amount.");
		document.myForm.e_amount.focus();
		return false;
	}
	if(document.myForm.e_submit_by.value == ""){
		alert("Submitted By Can't be empty. Please provide Submitted By value.");
		document.myForm.e_submit_by.focus();
		return false;
	}
	
	return true;
	
}
var categories = <%=ExpenseManager.getAllCategory()%>
console.log(categories);
$('#e_category').autocomplete({source : categories });

</script>
</html>