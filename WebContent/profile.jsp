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

<title> 9 Cube - Super Invoice</title>

<meta charset="US-ASCII">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->


<!-- JavaScripts -->
<script src="js/lib/jquery-2.1.0.min.js"></script>

<script src="js/lib/angular-sanitize.min.js"></script>


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
	h2.post-title a:hover {
			    color: #ff6760
			}



</style>
</head>

<body>
	<div class=outerDiv>
		<%@ include file="navbar.jsp" %>

		
		<div class="mainContainer" 
			style=" margin: 0 auto;">
						 			<div>
			    						
			    						<form name="myform" action="" method="post"  enctype="multipart/form-data"  onSubmit="return validateForm()">
			    						<div id "sc-register">
			    						<h1> Sign Up </h1>
			    						</div>
			    						
			    					<div class="sc-container">
    <input name="reg_username" type="text" title="Username" value="" class="" id="" placeholder="Username" required="required">
    <input name="reg_email" type="email" title="Email Address" value="" class="" id="" placeholder="Email Address" required="required">
    <input name="reg_password" type="password" title="Password" value="" class="" id="" placeholder="Password" required="required">
    <input name="reg_first_name" type="text" value="" title="First Name" class="" id="" placeholder="First Name">
    <input name="reg_last_name" value="" type="text" title="Last Name" class="" placeholder="Last Name" id="">
    <select name="gender" class="" id="" key="gender" type="select"><option value="Male">Male</option><option value="Female">Female</option></select>
    <input name="Twitter Profile URL" type="text" title="" value="" class="" id="" placeholder="Twitter Profile URL" key="Twitter Profile URL">
    <input type="submit" name="reg_submit" title="" value="Register" id="" class="">
  </div>
			 						
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