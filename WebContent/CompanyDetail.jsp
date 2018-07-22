<%@page import="com.glnetcomsolutions.web.InvoiceMaster"%>
<%@page import="org.json.simple.JSONObject"%>
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
	JSONObject organizationDetail = InvoiceMaster.getOrganizationDetail();

	%>
<title> 9 Cube - Super Invoice</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->




<!-- JavaScripts -->
<script src="js/lib/jquery-2.1.0.min.js"></script>



<script src="js/adminuser.js"></script>
<script>

function validateForm(){  
var name=document.myform.organizationName.value;  
var Email=document.myform.emailid.value; 
var Phnum=document.myform.telephoneNumber.value;  
var tinnum=document.myform.tinNumber.value; 
var vatnum=document.myform.vatNumber.value; 
var add=document.myform.address.value; 
var upload=document.myform.file.value;
if (name==null || name==""){  
  alert("Name can't be blank");  
  return false;  
}
else if(Phnum.length<10){  
  alert("Phonenumber must be at least 10 characters long.");  
  return false;  
  }
else if(tinnum==null || tinnum==""){
	 alert("Tinnumber can't be blank"); 
	 return false;
}
else if(vatnum==null || vatnum==""){
	 alert("vatnumber can't be blank");
	 return false;
}
else if(upload==null || upload==""){
	 alert("Please Upload a file"); 
	 return false;
}

else if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(Email))
	{
	  return (true) 
  } else
  {
	  alert("You have entered an invalid email address!")  
	    return (false)  }
}

</script>
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
			    						
			    						<form name="myform" action ="companydetail" method="post" enctype="multipart/form-data">
			    						<table id="organizationTable">
			    							<tr>
			    								<td colspan="2" style="text-align: center;">
			    								Organisational Details <br><br>
			    								</td>
			    							</tr>
			    							<tr>	
			    								<td>Company Name</td>
			    								<td><input type="text" name="orgName" id="orgName" value="<%=organizationDetail.get("organizationname") %>">
			    							</tr>
			    							<tr>	
			    								<td>Email_Id</td>
			    								<td><input type="text" name="emailId" id="emailId" value="<%=organizationDetail.get("emailid") %>">
			    							</tr>
			    							<tr>	
			    								<td>Contact Number</td>
			    								<td><input type="text" name="phoneNumber" id="phoneNumber" value="<%=organizationDetail.get("telephonenumber") %>">
			    							</tr>
			    							<tr>	
			    								<td>TIN Numberr</td>
			    								<td><input type="text" name="tinNumber" id="tinNumber" value="<%=organizationDetail.get("tinnumber") %>">
			    							</tr>
			    							<tr>	
			    								<td>VAT Number</td>
			    								<td><input type="text" name="vatNumber" id="vatNumber" value="<%=organizationDetail.get("vatnumber") %>">
			    							</tr>
			    							<tr>	
			    								<td>Address</td>
			    								<td><input type="text" name="Address" id="Address" value="<%=organizationDetail.get("address") %>">
			    							</tr>
			    							<tr>	
			    								<td>Company Logo</td>
			    								<td><input type="file" name="file" size="50" id="COMPANYLOGO" ></td>
			    							</tr>
			    							<tr>	
													<td colspan="2" style="text-align: center;">
														<input type="submit" value="Save" style="width: 100px;">
													</td>			
			    							</tr>
			    							
			    						</table>	
			    						</form>
			    					</div>
		          </div>
			
			</div>
		<script type="text/javascript">

	  </script>
	  
</body>
<script type="text/javascript">
ready();
</script>
</html>