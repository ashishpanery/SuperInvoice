package com.glnetcomsolutions.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.glnetcomsolutions.report.DBConnection;
import com.glnetcomsolutions.report.EnvironmentalConstants;


public class Listener implements ServletContextListener
{
	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		final ServletContext context = sce.getServletContext();
		EnvironmentalConstants.setContextPath(context.getRealPath("/"));
		Properties props = new Properties();
		File file = new File(EnvironmentalConstants.getContextPath() + "WEB-INF"+File.separator+"database.properties");
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			props.load(is);
			System.out.println("url="+props.getProperty("dbUrl"));
			DBConnection.setDbDriver(props.getProperty("dbDriver"));
			String dburl = props.getProperty("dbUrl").replace("$contextPath", EnvironmentalConstants.getContextPath());
			System.out.println(dburl);
			DBConnection.setDbUrl(dburl);
			
			Connection conn = DBConnection.getConnection();
			if(conn!=null){
				System.out.println("***Connection created successfully***");
				conn.close();
			}else{
				System.out.print("DataBase Connection Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		boolean isValid = LicenceChecker.checkForLicense();
		System.out.println("isValid="+isValid);
		EnvironmentalConstants.setLicenced(isValid);
		boolean checkforkey = true;
		if(checkforkey){
			Properties keyProp = new Properties();
			File keyFile = new File(EnvironmentalConstants.getContextPath() +"WEB-INF"+File.separator+"gl.key");
			if(!keyFile.exists()){
				try {
					keyFile.createNewFile();
					FileWriter fw = new FileWriter(keyFile.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					Date today = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(today);
					cal.add(Calendar.DATE, 15); // add 10 days
					today = cal.getTime();
					String content = "key=419e0069455b5e6d1f242cb5049ca4f0\ntimestamp="+today.getTime()+"\nserde=serde001#1212\n";
					bw.write(content);
					bw.close();
					Connection connection = null;
					Statement stat = null;
					try{
						connection = DBConnection.getConnection();
						stat = connection.createStatement();
						
						stat.execute("DELETE FROM INVOICEMASTER");
						stat.execute("DELETE FROM ITEM_INVENTORY");
						stat.execute("DELETE FROM EXPENSES");
						stat.execute("DELETE FROM VENDORDETAILS");
						stat.execute("DELETE FROM CUSTOMER");
				        
					}catch(Exception esql){
						esql.printStackTrace();
					}finally{
						if(stat !=null){
							
							try {
								stat.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if(connection != null){
							try {
								connection.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			InputStream keystream = null;
			try {
				keystream = new FileInputStream(keyFile);
				keyProp.load(keystream);
				long time = Long.parseLong(keyProp.getProperty("timestamp",""));
				int maxInvoiceCount = 100;
				try{
					String licencecout = keyProp.getProperty("serde","serde001#1212");
					licencecout = new StringBuffer(licencecout.substring(5,licencecout.indexOf("#"))).reverse().toString();
					maxInvoiceCount = Integer.parseInt(licencecout);
				}catch(Exception e){
					
				}
				Timestamp expiration_time = new Timestamp(time);
				
				Timestamp actualTimeStampDate = null;
		        try {
		            Date actual = new Date();
		            actualTimeStampDate = new Timestamp(actual.getTime());
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
		        boolean expired = (expiration_time.getTime() < actualTimeStampDate.getTime());
		        if(expired){
		        	EnvironmentalConstants.setLicenced(false);
		        }else{
		        	
		        	int count = InvoiceMaster
		        			.getInvoiceCount();
					if (maxInvoiceCount > count){
						EnvironmentalConstants.setLicenced(true);
					}else{
						EnvironmentalConstants.setLicenced(false);
					}
		        
		        	
		        	
		        }


			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (keystream != null) {
					try {
						keystream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}else{
			EnvironmentalConstants.setLicenced(true);
		}
		
		System.out.println("*****Open http://localhost:8787/app in your browser.*****");
	}
}
