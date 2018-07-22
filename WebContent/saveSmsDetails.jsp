<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.glnetcomsolutions.report.DBConnection"%>
<%@page import="com.glnetcomsolutions.report.LicenseBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.glnetcomsolutions.web.LicenceChecker"%>
<%@page import="javax.servlet.http.Part" %>
<%@ page import ="java.io.PrintWriter" %>
<%@page import="java.sql.*" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%@ page import = "org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%
File file ;
int maxFileSize = 5000 * 1024;
int maxMemSize = 5000 * 1024;
ServletContext context = pageContext.getServletContext();
String filePath = context.getInitParameter("file-upload");

// Verify the content type
String contentType = request.getContentType();
if ((contentType.indexOf("multipart/form-data") >= 0)) {

   DiskFileItemFactory factory = new DiskFileItemFactory();
   // maximum size that will be stored in memory
   factory.setSizeThreshold(maxMemSize);
   // Location to save data that is larger than maxMemSize.
   factory.setRepository(new File("c:\\temp"));

   // Create a new file upload handler
   ServletFileUpload upload = new ServletFileUpload(factory);
   // maximum file size to be uploaded.
   upload.setSizeMax( maxFileSize );
   try{ 
      // Parse the request to get file items.
      List fileItems = upload.parseRequest(request);

      // Process the uploaded file items
      Iterator i = fileItems.iterator();

      out.println("<html>");
      out.println("<head>");
      out.println("<title>JSP File upload</title>");  
      out.println("</head>");
      out.println("<body>");
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
         // Get the uploaded file parameters
         String fieldName = fi.getFieldName();
         String fileName = fi.getName();
         boolean isInMemory = fi.isInMemory();
         long sizeInBytes = fi.getSize();
         // Write the file
         if( fileName.lastIndexOf("\\") >= 0 ){
         file = new File( filePath + 
         fileName.substring( fileName.lastIndexOf("\\"))) ;
         }else{
         file = new File( filePath + 
         fileName.substring(fileName.lastIndexOf("\\")+1)) ;
         }
         fi.write( file ) ;
         out.println("Uploaded Filename: " + filePath + 
         fileName + "<br>");
         }
      }
      out.println("</body>");
      out.println("</html>");
   }catch(Exception ex) {
      System.out.println(ex);
   }
}else{
   out.println("<html>");
   out.println("<head>");
   out.println("<title>Servlet upload</title>");  
   out.println("</head>");
   out.println("<body>");
   out.println("<p>No file uploaded</p>"); 
   out.println("</body>");
   out.println("</html>");
}
 

String organizationName = request.getParameter("organizationName");
	String email = request.getParameter("emailid");
	String telephoneNumber = request.getParameter("telephoneNumber");
	String tinnumber = request.getParameter("tinNumber");
	String vatnumber = request.getParameter("vatNumber");
	String Address= request.getParameter("address");
	
	 
	Connection conn = null;
	PreparedStatement ps = null;
	 try{
		 conn = DBConnection.getConnection();
		 ps = conn.prepareStatement("UPDATE CUSTOMERDETAIL SET ORANIZATIONNAME=?,EMAILID=?,TELEPHONENUMBER=?,TINNUMBER=?,VATNUMBER=?,ADDRESS=?");
		 ps.setString(1, organizationName);
		 ps.setString(2, email);
		 ps.setString(3, telephoneNumber);
		 ps.setString(4, tinnumber);
		 ps.setString(5, vatnumber);
		 ps.setString(6, Address);
		
		 ps.execute();
		 int row = ps.executeUpdate();
		 if (row > 0) {
           out.println("Data saved into database");
         }
	 }catch(Exception e){
		 e.printStackTrace();
	 }finally{
		 if(ps != null){
			 ps.close();
		 }
		 if(conn != null){
			 conn.close();
		 }
	 }
	//response.sendRedirect("admin.jsp");
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
		
<div class="mainContainer"
			style="width: 760px; margin: 0 auto;">

</div>
<div style="text-align:right;">Powered By : <a href="http://9cube.in" target="_blank">9 Cube.in</a> Udaipur- 9024024250</div>
							
	    </div>

						</body>

</html>