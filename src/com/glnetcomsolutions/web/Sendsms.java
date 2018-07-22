package com.glnetcomsolutions.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.glnetcomsolutions.report.DBConnection;


public class Sendsms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  PrintWriter pw = response.getWriter();  
		 String authkey= "19896ABZaaa43563ae4d8";
		 String route= "5";
		 URLConnection myURLConnection=null;
		   URL myURL=null;
		   BufferedReader reader=null;
		   
		   String mainUrl="http://bigmsg.in/api/balance.php?";

		   StringBuilder sbPostData= new StringBuilder(mainUrl);
		   sbPostData.append("authkey="+authkey); 
		   sbPostData.append("&type="+route);
		   mainUrl = sbPostData.toString();
		   try
		   {
		   myURL = new URL(mainUrl);
		    myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
		    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		    String response1;
		    while ((response1 = reader.readLine()) != null) 
		  
		   	pw.print("Credits:" +  response1.toString());
		   	    
		    reader.close();
		   }
		   catch (IOException e) 
		   { 
		   	e.printStackTrace();
		   } 
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String vendorurl= "";
		String AuthKey= "";
		String Route= "";
		String country= "0";
	    String CountryCode="";
	    String vname=request.getParameter("vendorName");
	    String mobiles=request.getParameter("phonenumber");
	    String message=request.getParameter("sms");
	    String sender=request.getParameter("SenderId");
			   	   
	    PrintWriter pw = response.getWriter(); 
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
	 
	 try{
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement("SELECT * FROM VENDORDETAILS WHERE VENDORNAME=?");
			ps.setString(1,vname);
			rs = ps.executeQuery();
		    while (rs.next()) {
		    vendorurl = rs.getString("VENDORURL");
		    AuthKey = rs.getString("AUTHKEY");
            Route = rs.getString("ROUTE");
             CountryCode= rs.getString("COUNTRYCODE");
                  
             }
			 			 
		}catch(Exception e){
			
		} 	
	 // System.out.println("outside");
	// System.out.println(vendorurl);

					
		   URLConnection myURLConnection=null;
		   URL myURL=null;
		   BufferedReader reader=null;
//		   http://bigmsg.in/api/sendhttp.php?authkey=YourAuthKey&mobiles=919999999990&message=message&sender=senderid&route=1&country=0
		// String mainUrl="http://bigmsg.in/sendhttp.php?";
		 
	       StringBuilder sbPostData= new StringBuilder(vendorurl);
		   sbPostData.append("?"); 
	       sbPostData.append("authkey="+AuthKey); 
		   sbPostData.append("&mobiles="+mobiles);
		   sbPostData.append("&message="+message);
		   sbPostData.append("&sender="+sender);
		   sbPostData.append("&route="+Route);
		   sbPostData.append("&Country code="+CountryCode);
		   sbPostData.append("&Country="+country);
		 			
		  vendorurl = sbPostData.toString();
		  System.out.println("after sbpost" + vendorurl);
		
		  try
		   {
		   myURL = new URL(vendorurl);
		//   System.out.println("in try catch myurl1");
		    myURLConnection = myURL.openConnection();
		    System.out.println("in try catch myurl2");
		    myURLConnection.connect();
		    System.out.println("in try catch myurl3");
		    System.out.println(myURL);
		    reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		    String response2;
		    while ((response2 = reader.readLine()) != null) 
		   // System.out.print(response2.toString());
		    pw.print("Your message send succesfully" + "" + response2.toString());
		    
		    reader.close();
		   }
		   catch (IOException e) 
		   { 
		   	e.printStackTrace();
		   }
	}

}
