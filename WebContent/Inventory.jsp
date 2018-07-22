<%@page import="com.glnetcomsolutions.report.EnvironmentalConstants"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<%@page import="com.glnetcomsolutions.web.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.print.*" %>
<!DOCTYPE html>
<html>
<head>

<title>Super Invoice</title>

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

<script src="js/ItemDetail.js"></script>

</head>

<body>
	<div class=outerDiv>
		<%@ include file="navbar.jsp" %>
	    </div>
		
		<div class="mainContainer" 
			style=" margin: 0 auto;width:90%;">
			
			 <div class="table-responsive">
					<div id="barcode_table" style="display:none;">
						<table>
							<tr>
								<td>
									Bar Code 
								</td>
								<td>
									<input type="text" id="barcode_string" disabled="disabled">
								</td>
								<td>
									Rate
								</td>
								<td>
									<input type="text" id="barcode_rate" onkeypress="numeralsOnly(event);">
								</td>
							</tr>
							<tr>
								<td>
									Width
								</td>
								<td>
									<input type="text" id="barcode_width" value="111" onkeypress="numeralsOnly(event);">
								</td>
								<td>
									Height
								</td>
								<td>
									<input type="text" id="barcode_height" value="30" onkeypress="numeralsOnly(event);">
								</td>
							</tr>
							<tr>
								<td>
									Column
								</td>
								<td>
									<input type="text" id="barcode_column" value="5" onkeypress="numeralsOnly(event);">
								</td>
								<td>
									Rows
								</td>
								<td>
									<input type="text" id="barcode_rows" value="13" onkeypress="numeralsOnly(event);">
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align:  right;">
										<button onclick="javascript:printBarCodeMatrix()">Print</button>
										<button onclick="javascript:showItemDiv(true);">Cancel</button>
								</td>
							</tr>
						</table>
					
						
						
					</div>
			    					
			    					<div id="inventory_table" style="display:block;">
			    					<div style="display:none;">
			    						<button onclick="javascript:printAllBarCode();">Print BarCode</button>
			    					</div>
			    						<table id="loaderr"></table>
			    						<div id="ploaderr" ></div>
			    					</div>
			    					
			       
			            
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
drawJqGrid();
</script>
</html>