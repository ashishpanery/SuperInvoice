<%@page import="com.glnetcomsolutions.report.EnvironmentalConstants"%>
<%@page import="com.glnetcomsolutions.report.LicenseBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<%
	
	LicenseBean bean = LicenceChecker.getLicenseBean();
	String organizationName = "";
	String keyBase = "";
	String licenseKey = "";
	String licenseType = "";
	String customerName = "";
	String phoneNumber = "";
	String emailId = "";
	String msg = "";
	String operation;
	if(bean != null){
		keyBase = bean.getKeyBase();
		keyBase = LicenceChecker.encrypt(keyBase);
		licenseKey = bean.getLicenseKey();
		licenseType = bean.getLicenseType();
		licenseType = LicenceChecker.getLicenseTypeInString(Integer.parseInt(licenseType));
		organizationName = bean.getOrganization();
		customerName = bean.getCustomerName();
		phoneNumber = bean.getPhoneno();
		emailId = bean.getEmailId();
		operation = "update";
		if(EnvironmentalConstants.isLicenced()){
			msg = "You are using "+licenseType+" license.Total Invoice granted to you is "+bean.getInvoiceCount()+".";
		}else{
			msg="Your license is expired. Please renew your license by sending us an email at sales@9cube.in.";
		}
	}else{
		msg = "Use our demo license. It is only valid for 15 days and you can generate only 50 invoice.";
		operation = "insert";
		licenseType = "demo";
		keyBase = System.currentTimeMillis()+"";
		licenseKey = LicenceChecker.getDemoLicense(keyBase);
		keyBase = LicenceChecker.encrypt(keyBase);
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Super Invoice</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->


<!-- JavaScripts -->

<script src="js/projects.js"></script>

</head>

<body >
	<div class=outerDiv>
		<div class="navbar navbar-inverse" role="navigation">
	      <div class="container-fluid">
	      	<div class="navbar-header" style="margin-top: 0.2%;">
	      		<span style="color: white !important; font-size: 20px;">Super Invoice</span>
	        </div>
	        <div class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right nav-web-perf">
						
						<li class="active"><a href="#"><span class="glyphicon glyphicon-stats"></span>License</a></li>
						<li><a href="http://9cube.in/help/"><span class="glyphicon glyphicon-flash"></span>Help</a></li>
						<li><a target="#" href="http://9cube.in/9cube-software/super-invoice.php#panel2"><span class="glyphicon glyphicon-flash"></span>Purchase a license</a></li>
					
						
					</ul>
	        </div>  
	      </div>
	    </div>

<div class="mainContainer"
			style="width: 760px; margin: 0 auto;">

			<div class='runTimeParams'>
				<form name="licenseForm" action="saveLicence.jsp" method="POST">
				<h4><%=msg %></h4>
				
				<table style="width:100%;">
					<tbody>
						<tr>
							<td>Name</td>
							<td><input type="text" name="customerName" id="customerName" value="<%= customerName%>">
						</tr>
						<tr>
							<td>Organization Name</td>
							<td><input type="text" name="organizationName" id="organizationName" value="<%= organizationName%>">
						</tr>
						<tr>
							<td>Phone Number</td>
							<td><input type="text" name="phoneNumber" id="phoneNumber" value="<%= phoneNumber%>">
							</td>
						</tr>
						<tr>
							<td>Email Id</td>
							<td><input type="text" name="emailId" id="emailId" value="<%= emailId%>">
							</td>
						</tr>
						<tr>
							<td>
								License Type
							</td>
							<td>
							
								<input type="text" name="licenseType" id="licenseType" value="<%= licenseType%>">
							</td>
						</tr>
						<tr>
							<td>
								Key Base
							</td>
							<td>
								<input type="text" name="keyBase" id="keyBase"  readonly="true" value="<%= keyBase%>">
							</td>
						</tr>
						<tr>
							<td>License Key</td>
							<td><input type="text" name="licenseKey" id="licenseKey" value="<%= licenseKey%>">
							</td>
						</tr>
						<tr style="display:none">
							<td>Operation</td>
							<td><input type="text" name="operation" id="operation" value="<%= operation%>">
							</td>
						</tr>
						<tr>
							
							<td>
							<input type="submit" value="Save">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			</div>
</div><div style="text-align:right;">Powered By : <a href="http://9cube.in" target="_blank">9 Cube.in</a> Udaipur- 9024024250</div>
							
	    </div>

						</body>

</html>