<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<!DOCTYPE html>
<html>
<head>
<title>Super Invoice Print Software</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->



<!-- JavaScripts -->

<script src="js/projects.js"></script>

</head>
<%
	
session.setAttribute("user", null);
session.setAttribute("role", null);
response.sendRedirect("login.jsp");
	%>
<body >
	<div class=outerDiv>
		<div class="navbar navbar-inverse" role="navigation">
	      <div class="container-fluid">
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
					<td>
								<input type="submit" value="Log In">
								</td>
							</tr>
						</table>
						</form>
					</div>


</div>








							
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