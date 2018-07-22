package com.glnetcomsolutions.web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.glnetcomsolutions.report.EnvironmentalConstants;
import com.glnetcomsolutions.report.LicenseBean;
import com.glnetcomsolutions.web.LicenceChecker;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.*;
import com.glnetcomsolutions.report.DBConnection;

/* @MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
 maxFileSize=1024*1024*10,      // 10MB
 maxRequestSize=1024*1024*50)
 */
public class saveCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String organizationName = null;
		String email = null;
		String telephoneNumber = null;
		String tinnumber = null;
		String vatnumber = null;
		String Address = null;

		File file;
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;

		String filePath = "E:/Study/Eclipse Workspace/RajRatan/Invoice-Hojri/WebContent/CompanyLogoImages/";

		String contentType = request.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			factory.setRepository(new File("c:\\temp"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(maxFileSize);
			try {
				List fileItems = upload.parseRequest(request);
				Iterator i = fileItems.iterator();
				out.println("<html>");
				out.println("<body>");
				while (i.hasNext()) {
					System.out.println("Found Files.");
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						file = new File(filePath + fileName);
						fi.write(file);
						out.println("Uploaded Filename: " + filePath + fileName
								+ "<br>");
					} else {
						String fieldname = fi.getFieldName();
						String fieldvalue = fi.getString();
						switch (fieldname) {
							case "orgName":
								organizationName = fieldvalue;
								break;
							case "emailId":
								email = fieldvalue;
								break;
							case "phoneNumber":
								telephoneNumber = fieldvalue;
								break;
							case "tinNumber":
								tinnumber = fieldvalue;
								break;
							case "vatNumber":
								vatnumber = fieldvalue;
								break;
							case "Address":
								Address = fieldvalue;
								break;
						}
					}
				}
				out.println("</body>");
				out.println("</html>");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		} else {
			out.println("<html>");
			out.println("<body>");
			out.println("<p>No file uploaded</p>");
			out.println("</body>");
			out.println("</html>");
		}

		System.out.println(organizationName);
		System.out.println(email);

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			ps = conn
					.prepareStatement("UPDATE CUSTOMERDETAIL SET ORANIZATIONNAME=?,EMAILID=?,TELEPHONENUMBER=?,TINNUMBER=?,VATNUMBER=?,ADDRESS=? WHERE ID = 1");
			// ps =
			// conn.prepareStatement("INSERT INTO CUSTOMERDETAIL (ORANIZATIONNAME,EMAILID,TELEPHONENUMBER,TINNUMBER,VATNUMBER,ADDRESS) VALUES (?,?,?,?,?,?)");
			ps.setString(1, organizationName);
			ps.setString(2, email);
			ps.setString(3, telephoneNumber);
			ps.setString(4, tinnumber);
			ps.setString(5, vatnumber);
			ps.setString(6, Address);

			System.out.println(ps.execute());
			int row = ps.executeUpdate();
			System.out.println(row);
			if (row > 0) {
				out.println("Data saved into database");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
