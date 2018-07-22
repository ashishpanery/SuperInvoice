<%@page import="com.glnetcomsolutions.report.LicenseBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<%
	String customerName = request.getParameter("customerName");
	String phoneNumber = request.getParameter("phoneNumber");
	String emailId = request.getParameter("emailId");
	String licenseKey = request.getParameter("licenseKey");
	String keyBase = request.getParameter("keyBase");
	String licenseType = request.getParameter("licenseType");

	String organizationName = request.getParameter("organizationName");
	String operation = request.getParameter("operation");
	LicenseBean bean = new LicenseBean();
	bean.setCustomerName(customerName);
	bean.setPhoneno(phoneNumber);
	bean.setEmailId(emailId);
	bean.setOrganization(organizationName);
	bean.setOperation(operation);
	boolean isValid = LicenceChecker.setLicenseInBean(bean, keyBase, licenseKey);
	String msg = "Your License Key is not valid. Please update your license with a valid key.";
	if(isValid){
		LicenceChecker.saveLicenseBean(bean);
		msg = "Your license updated successfully.Please restart your software to apply new license. Thanks!!";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Super Invoice</title>

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
		<h1><%=msg %></h1>
<div class="mainContainer"
			style="width: 760px; margin: 0 auto;">

</div>
<div style="text-align:right;">Powered By : <a href="http://9cube.in" target="_blank">9 Cube.in</a> Udaipur- 9024024250</div>
							
	    </div>

						</body>

</html>