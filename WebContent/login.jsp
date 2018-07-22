<%@page import="com.glnetcomsolutions.report.EnvironmentalConstants"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.print.*" %>
    <%
	if(EnvironmentalConstants.isLicenced()){
		
	}else{
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "licence.jsp"); 
		response.sendRedirect("licence.jsp");
	}
%> 
<!DOCTYPE html>
<html>
<head>
<title>9 Cube- Super Invoice</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->

<link rel="stylesheet" href="css/jquery.dataTables.css" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css" />


<!-- JavaScripts -->
<script src="js/lib/jquery-2.1.0.min.js"></script>
<script src="js/lib/bootstrap.min.js"></script>
<script src="js/lib/jquery-ui.js"></script>

<script src="js/projects.js"></script>

</head>

<body >
	<div class=outerDiv>
		<div class="navbar navbar-inverse" role="navigation">
	      <div class="container-fluid">
	      	<img src="images/9cube-logo.png" style="width:100px;height:100px;float:left;">
	       
	      	<div class="navbar-header" style="margin-top: 0.2%;">
	      	
	      		<span style="color: white !important; font-size: 20px;">Super Invoice</span>
	        </div> 
	        <div class="navbar-collapse collapse">
	         </div>
	          
	      </div>
	    </div>

<div class="mainContainer" ng-controller="projectCtrl"
			style="width: 760px; margin: 0 auto;">

			<div class='runTimeParams'>
			<form method="post" action="invoicemaster.jsp">
				<table style="width: 95%;">
					
					<tr>
						<td><label>Login Id</label></td>
						<td><input type="text" class="form-control input-sm"
							id="loginId" name="loginId"></td>
					</tr>
				<tr>
					<td><label>Password</label></td>
						<td >
							<div class="ui-widget">
								<input id="password" name="password" class="form-control input-sm" type="password">
							</div>
						<td>
				</tr>
				
				<tr>
					<td colspan="2" style="float:right;">
								<input type="submit" value="Log In">
								</td>
							</tr>
						</table>
						</form>
					</div>


</div>







<div style="text-align:right;">Powered By : <a href="http://9cube.in" target="_blank">9 Cube.in</a> - 9024024250</div>
							
	    </div>
	  <script type="text/javascript">
	
	  function getSWKey(){
		  $.ajax({
				url : 'lc.do?requestType=getswkey',
				type:'POST',
				dataType: "json",
				
			}).done(
					function(data){
					
						$('#swkey').val(data["key"])
					}
			);
	  }
	  getSWKey();
	  </script> <!-- <script>
		angular.module('webLT', ['ngSanitize']);
	</script>
	-->

						</body>

</html>