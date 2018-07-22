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
	
	String user = null;
	String role = null;
	if(session.getAttribute("user") != null){
		user = (String) session.getAttribute("user");
		role = (String) session.getAttribute("role");
	}else{
		
		user = request.getParameter("loginId");
		String password = request.getParameter("password");
		role = Util.getUserRole(user, password);
		if (role != null)
		{
			session.setAttribute("user", user);
			session.setAttribute("role", role);
		}else{
			
			response.sendRedirect("login.jsp");
			return;
		}
	}
	if(!"admin".equals(role)){
		response.sendRedirect("logout.jsp");
	}

	String displayProp = "none";
	String displayButton = "none";
	if("admin".equalsIgnoreCase(role)){
		displayProp = "none";
		displayButton = "";
	}

	

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





</head>

<body>
<div class=outerDiv>
<style type="text/css">

ul{
  margin: 0px;
  padding: 0px;
  list-style:none;
}

ul.dropdown{ 
  position:positive; 
  width: 100%; 
}

ul.dropdown li{ 
  font-weight: bold; 
  float: left; 
  width: 150px; 
  position: relative;
  background: #245a8c;
}

ul.dropdown a:hover{ 
  color: #000; 
}

ul.dropdown li a { 
  display: block; 
padding-top: 5px;
	padding-bottom: 15x;
	font-size: 17px;
  color: #34495e; 
  position: relative; 
  z-index: 2000; 
  text-align:center;
  text-decoration:none;
  font-weight: 300;
}

ul.dropdown li a:hover,
ul.dropdown li a.hover{ 
  background: #4291CA;
  position: relative;
  color: #fff;
}


ul.dropdown ul{ 
 display: none;
 position: absolute; 
  top: 0; 
  left: 0; 
  width: 180px; 
  z-index: 1000;
}

ul.dropdown ul li { 
  font-weight: normal; 
  background: #f6f6f6; 
  color: #000; 
  border-bottom: 1px solid #ccc; 
}

ul.dropdown ul li a{ 
  display: block; 

  color: #fff !important;
  background: #245a8c !important;
} 

ul.dropdown ul li a:hover{
  display: block; 
  background: #3498db !important;
  color: #fff !important;
} 

.drop > a{
  position: relative;
}

.drop > a:after{
  content:"";
  position: absolute;
  right: 30px;
  top: 40%;
  border-left: 5px solid transparent;
  border-top: 5px solid #333;
  border-right: 5px solid transparent;
  z-index: 999;
}

.drop > a:hover:after{
  content:"";
   border-left: 5px solid transparent;
  border-top: 5px solid #fff;
  border-right: 5px solid transparent;
}


</style>

<script type="text/javascript">
		var maxHeight = 400;

		$(function(){

		    $(".dropdown > li").hover(function() {
		    
		         var $container = $(this),
		             $list = $container.find("ul"),
		             $anchor = $container.find("a"),
		             height = $list.height() * 1.1,       // make sure there is enough room at the bottom
		             multiplier = height / maxHeight;     // needs to move faster if list is taller
		        
		        // need to save height here so it can revert on mouseout            
		        $container.data("origHeight", $container.height());
		        
		        // so it can retain it's rollover color all the while the dropdown is open
		        $anchor.addClass("hover");
		        
		        // make sure dropdown appears directly below parent list item    
		        $list
		            .show()
		            .css({
		                paddingTop: $container.data("origHeight")
		            });
		        
		        // don't do any animation if list shorter than max
		        if (multiplier > 1) {
		            $container
		                .css({
		                    height: maxHeight,
		                    overflow: "hidden"
		                })
		                .mousemove(function(e) {
		                    var offset = $container.offset();
		                    var relativeY = ((e.pageY - offset.top) * multiplier) - ($container.data("origHeight") * multiplier);
		                    if (relativeY > $container.data("origHeight")) {
		                        $list.css("top", -relativeY + $container.data("origHeight"));
		                    };
		                });
		        }
		        
		    }, function() {
		    
		        var $el = $(this);
		        
		        // put things back to normal
		        $el
		            .height($(this).data("origHeight"))
		            .find("ul")
		            .css({ top: 0 })
		            .hide()
		            .end()
		            .find("a")
		            .removeClass("hover");
		    
		    });  
		    
		});
	  </script>
<div class="navbar navbar-inverse" role="navigation">
			<div class="navbar-header" style="margin-top: 0%;">
						<span style="color: white !important; font-size:20px;">Super Invoice</span>
						</div>
				<div class="navbar-collapse collapse" style="float:right;">
				
					<ul class="dropdown">
			        		<li ><a href="dashboard.jsp"><span
								class="glyphicon glyphicon-stats"></span>Dashboard</a></li>
			        	<li ><a href="invoicemaster.jsp"><span
								class="glyphicon glyphicon-stats"></span>Invoice Master</a>
			        	</li>
			        	<%
							if("admin".equals(role)){
						%>
			        	
			        	<li ><a href="Inventory.jsp"><span
								class="glyphicon glyphicon-flash"></span>Inventory</a>
			        	</li>
			        	<li style="width:215px;" class="drop"><a href="InvoiceHistory.jsp"><span class="glyphicon glyphicon-time"></span>Invoice History</a>
			        	</li>
			        	<li class="drop"><a href="#"><span
											class="glyphicon glyphicon-wrench"></span>Admin</a>
			        		<ul class="sub_menu">
										
										<li><a href="CompanyDetail.jsp">Company Details</a></li>
										<li><a href="SMSForm.jsp"> <span
								class="glyphicon glyphicon-stats"></span>SMS</a></li>
										<li><a href="admin.jsp">User & Tax Config</a></li>
										<li><a href="invoiceconfig.jsp">Invoice Template</a></li>
										
			        		</ul>
			        	</li>
			        	<%
							}
						%>
			        	<li><a href="logout.jsp"><span
								class="glyphicon glyphicon-user"></span>Log Out</a></li>
        		   </ul>
				</div>
			</div>
		</div>
