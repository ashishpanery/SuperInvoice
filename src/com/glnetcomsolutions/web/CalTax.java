package com.glnetcomsolutions.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.glnetcomsolutions.report.DBConnection;

public class CalTax {
	
	public static void updateTax(String invoiceId, double taxAmount){
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		   try{
			   conn =  DBConnection.getConnection();
				String query = "UPDATE INVOICEMASTER SET TAXAMOUNT = ? WHERE INVOICENO = ?";
				ps =  conn.prepareStatement(query);
				ps.setDouble(1, taxAmount);
				ps.setString(2, invoiceId);
				ps.execute();
				
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally{
			   

			   try{
				   
				   ps.close();
				   }catch(Exception e){
					 e.printStackTrace();  
				   }

			   
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }
	}
	
	public static void calTax(){
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		   try{
			   conn =  DBConnection.getConnection();
				String query = "SELECT * FROM INVOICEMASTER";
				ps =  conn.prepareStatement(query);
				rs =  ps.executeQuery();
				JSONParser parser = new JSONParser();
				while(rs.next()){
					
					
					String invoiceId =  rs.getString("INVOICENO");
					JSONArray arr = (JSONArray)parser.parse(rs.getString("ITEMARRAY"));
					double taxAmount = 0.0;
					for(int i = 0; i< arr.size(); i++){
						JSONObject item = (JSONObject) arr.get(i);
						if(item.containsKey("itemTax")){
							
							String tax = (String)item.get("itemTax");
							tax = tax.length() == 0 ? "0.0" :tax;
							taxAmount = taxAmount + Double.valueOf(tax);
							
						}
					}
					updateTax(invoiceId,(Math.floor(taxAmount * 100) / 100));
					System.out.println("Invoice = "+invoiceId+ "tax : "+(Math.floor(taxAmount * 100) / 100));
					
				}
				
		   }catch(Exception e){
			   e.printStackTrace();
		   }finally{
			   try{
				   
			  	 rs.close();
			   }catch(Exception e){
				   
			   }

			   try{
				   
				   ps.close();
				   }catch(Exception e){
					   
				   }

			   
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }
	}
}
