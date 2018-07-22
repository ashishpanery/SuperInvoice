package com.glnetcomsolutions.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.glnetcomsolutions.report.DBConnection;
import com.glnetcomsolutions.report.LicenseBean;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class InvoiceMaster {

	
	public static JSONArray getAllProductDetail() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray arr = new JSONArray();
		try{
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM ITEM_INVENTORY ORDER BY ITEM_DESC ";
			 ps = conn.prepareStatement(query);
			 rs = ps.executeQuery();
			
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("itemcode", rs.getString("ITEMCODE").toLowerCase());
				obj.put("item_desc", rs.getString("ITEM_DESC"));
				obj.put("supplier", rs.getString("SUPPLIER"));
				obj.put("qty", rs.getInt("QTY"));
				obj.put("item_retail_price", rs.getDouble("ITEM_RETAIL_PRICE"));
				obj.put("item_sales_price", rs.getDouble("ITEM_SALES_PRICE"));
				obj.put("item_sales_price_credit", rs.getDouble("ITEM_SALES_PRICE_CREDIT"));
				obj.put("unit_of_measurement", rs.getString("UNIT_OF_MEASUREMENT"));
				obj.put("applicable_tax", rs.getString("APPLICABLE_TAX"));
				arr.add(obj);
			}
		}catch(Exception e){
			
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		
		
		
		return arr;
	
	}
	public static JSONObject getOrganizationDetail() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject obj = new JSONObject();
		boolean flag = true;
		try{
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM CUSTOMERDETAIL WHERE ID=10";
			 ps = conn.prepareStatement(query);
			 rs = ps.executeQuery();
			while(rs.next()){
				
				obj.put("organizationname", rs.getString("ORANIZATIONNAME"));
				obj.put("tinnumber", rs.getString("TINNUMBER"));
				obj.put("telephonenumber", rs.getString("TELEPHONENUMBER"));
				obj.put("emailid", rs.getString("EMAILID"));
				obj.put("vatnumber", rs.getString("VATNUMBER"));
				obj.put("address", rs.getString("ADDRESS"));
			
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		
		if(flag){
			LicenseBean bean = LicenceChecker.getLicenseBean();
			obj.put("organizationname", bean.getOrganization());
			obj.put("tinnumber", "TIN Number: ");
			obj.put("telephonenumber", "Telephone: "+bean.getPhoneno());
		}
		
		return obj;
	
	}
	public static JSONObject getAllTaxDetail() throws SQLException{
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject obj = new JSONObject();
		try{
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM TAX";
			 ps = conn.prepareStatement(query);
			 rs = ps.executeQuery();
			
			while(rs.next()){
				JSONObject object = new JSONObject();
				object.put("name",rs.getString("TAXNAME"));
				object.put("rate", rs.getString("TAXVALUE"));
				obj.put(rs.getString("TAXID"),object);
			}
		}catch(Exception e){
			
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		
		
		
		return obj;
	
	}
public static JSONArray getAllProductDetailFromInvoiceMaster() throws ClassNotFoundException, SQLException{

		
		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM INVOICEMASTER";
		java.sql.PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		int total = 0;
		JSONArray arr = new JSONArray();
		JSONObject userobj = new JSONObject();
		long totalAmount = 0;
		while(rs.next()){
			JSONObject obj = new JSONObject();
			total++;
			obj.put("itemcode", rs.getString("ITEMCODE"));
			obj.put("item_desc", rs.getString("ITEM_DESC"));
			obj.put("supplier", rs.getString("SUPPLIER"));
			obj.put("qty", rs.getInt("QTY"));
			obj.put("item_retail_price", rs.getDouble("ITEM_RETAIL_PRICE"));
			obj.put("item_sales_price", rs.getDouble("ITEM_SALES_PRICE"));
			obj.put("item_sales_price_credit", rs.getDouble("ITEM_SALES_PRICE_CREDIT"));
			obj.put("unit_of_measurement", rs.getString("UNIT_OF_MEASUREMENT"));
			arr.add(obj);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return arr;
	
	}
	public static JSONObject getInvoiceHistory() throws ClassNotFoundException, SQLException{
		
		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM INVOICEMASTER";
		java.sql.PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		int total = 0;
		JSONArray arr = new JSONArray();
		JSONObject userobj = new JSONObject();
		long totalAmount = 0;
		
		while(rs.next()){
			JSONObject obj = new JSONObject();
			total++;
			obj.put("invoiceno", rs.getString("INVOICENO"));
			obj.put("invoicedate", rs.getString("INVOICEDATE"));
			obj.put("invoicetype", rs.getString("INVOICETYPE"));
			obj.put("duedate", rs.getString("DUEDATE"));
			obj.put("customername", rs.getString("CUSTOMERNAME"));
			obj.put("customeraddress", rs.getString("CUSTOMERADDRESS"));
			obj.put("subtotal", rs.getDouble("SUBTOTAL"));
				obj.put("itemarray",rs.getString("ITEMARRAY"));
			obj.put("taxamount", rs.getDouble("TAXAMOUNT"));
			obj.put("totalitem", rs.getString("TOTALITEM"));
			obj.put("total",rs.getDouble("TOTAL"));
			obj.put("year",rs.getInt("YEAR"));
			obj.put("month",rs.getInt("MONTH"));
			obj.put("date",rs.getInt("DATE"));
			arr.add(obj);
		}
		rs.close();
		ps.close();
		conn.close();
		
		JSONObject object = new JSONObject();
		object.put("page", "1");
		object.put("total", 1);
		object.put("records", ""+total);
		object.put("rows", arr);
		object.put("userdata", userobj);
		return object;		
	}
public static int getInvoiceCount(){
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		int total = -1;
		try{
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) FROM INVOICEMASTER";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				total = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return total;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
		getInvoiceItems("INV-11");
	}
	public static JSONObject getInvoiceItems(String invoiceId) throws ClassNotFoundException, SQLException, ParseException{
		
		Connection conn = DBConnection.getConnection();
		String query = "SELECT ITEMARRAY FROM INVOICEMASTER WHERE INVOICENO LIKE ('"+ invoiceId+"')";
		java.sql.PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		JSONArray arr = new JSONArray();
		JSONObject userobj = new JSONObject();

		
		while(rs.next()){
			
			JSONParser parser = new JSONParser();
			String items = rs.getString("ITEMARRAY");
			arr = (JSONArray) parser.parse(items);
		}
		rs.close();
		ps.close();
		
		conn.close();
		
		JSONObject object = new JSONObject();
		object.put("page", "1");
		object.put("total", arr.size());
		object.put("records", ""+arr.size());
		object.put("rows", arr);
		object.put("userdata", userobj);
		return object;		
	}
	public static String getUniqueInvoiceNumber() throws ClassNotFoundException, SQLException{
		String invoiceNo=null;
		Connection conn = DBConnection.getConnection();
		String query = "SELECT COUNT(*) FROM INVOICEMASTER";
		java.sql.PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
//			invoiceNo = "INV-"+(rs.getInt(1)+1);
			invoiceNo = ""+(rs.getInt(1)+1);
		}
		rs.close();
		ps.close();
		conn.close();
//		invoiceNo = "INV-"+System.currentTimeMillis();
		return invoiceNo;
	}

	private static Double getValue(String val) {
		double value = 0;
		try {
			if (val != null && !("".equals(val))) {
				value = Double.valueOf(val);
			}
		} catch (Exception e) {
		}
		return value;
	}
	public static void saveInvoiceDetail(JSONObject data) throws ClassNotFoundException, SQLException, java.text.ParseException{
		
		Connection conn = DBConnection.getConnection();

		String query = "INSERT INTO INVOICEMASTER"
				+ " (INVOICENO,INVOICEDATE,DUEDATE,CUSTOMERNAME"
				+ ",CUSTOMERADDRESS,SUBTOTAL,TAXAMOUNT,TOTALITEM," +
				"TOTAL,ITEMARRAY,INVOICETYPE, YEAR, MONTH,DATE)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		java.sql.PreparedStatement ps = conn.prepareStatement(query);
		int i = 1;
		ps.setString(i++, (String) data.get("invoiceNo"));
		ps.setString(i++, (String) data.get("invoiceDate"));
		ps.setString(i++, (String) data.get("dueDate"));
		ps.setString(i++, (String) data.get("customerName"));
		ps.setString(i++,(String) data.get("customerAddress") + ", City : "
						+ data.get("customerAddressCity") + ", Country :"
						+ data.get("customerAddressCountry"));
		ps.setDouble(i++, getValue((String) data.get("subTotal")));
		ps.setDouble(i++, getValue((String) data.get("taxAmt")));
		ps.setString(i++, (String) data.get("totalItem"));
		ps.setDouble(i++, getValue((String) data.get("total")));
		ps.setString(i++, data.get("itemArray").toString());
		ps.setString(i++, (String) data.get("invoicetype"));
		ps.setInt(i++, Util.getYearFromDate((String) data.get("invoiceDate")));
		ps.setInt(i++, Util.getMonthFromDate((String) data.get("invoiceDate")));
		ps.setInt(i++, Util.getDateFromDate((String) data.get("invoiceDate")));
		ps.execute();
		ps.close();
		conn.close();
		updateInventoryForNewItem((JSONArray)data.get("itemArray"));
		updateInventory((JSONArray)data.get("itemArray"));
		saveCustomerDetail(data);
		
		
		
	}
	public static void saveCustomerDetail(JSONObject data) throws ClassNotFoundException, SQLException{
		
		Connection conn = DBConnection.getConnection();
	
		String query = "INSERT INTO CUSTOMER" +
				" (CUSTOMERNAME,CUSTOMERADDRESS,CUSTOMERADDRESSCITY)" +
				" VALUES (?,?,?)";
		java.sql.PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, (String)data.get("customerName"));
		ps.setString(2, (String)data.get("customerAddress"));
		ps.setString(3, (String)data.get("customerAddressCity") );
		ps.execute();
		ps.close();
		conn.close();
		
		
		
	}
	public static JSONArray getCustomerDetail() throws ClassNotFoundException, SQLException{
		
		Connection conn = DBConnection.getConnection();
	
		String query = "SELECT * FROM CUSTOMER" ;
				
		java.sql.PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		JSONArray arr = new JSONArray();
	
		while(rs.next()){
			JSONObject obj = new JSONObject();
			obj.put("name", rs.getString("CUSTOMERNAME"));
			obj.put("address", rs.getString("CUSTOMERADDRESS"));
			obj.put("city", rs.getString("CUSTOMERADDRESSCITY"));
			arr.add(obj);
			
		}
		ps.execute();
		ps.close();
		conn.close();
		
		return arr;
		
	}
public static void updateInventoryForNewItem(JSONArray itemArray) throws ClassNotFoundException, SQLException{
		
		Connection conn = DBConnection.getConnection();
		
		String selectQty = "SELECT ITEM_DESC FROM ITEM_INVENTORY ";
		ArrayList<String> inventoryItem = new ArrayList<String>();		
	
			java.sql.PreparedStatement ps = conn.prepareStatement(selectQty);
			
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()){
				inventoryItem.add(rs.getString(1).toLowerCase());
			}
			rs.close();
			ps.close();
			HashMap<String, Double> newItem = new HashMap<String, Double> ();
			for(int i = 0; i< itemArray.size(); i++){
				
				JSONObject item = (JSONObject) itemArray.get(i);
				String itemName = ((String) item.get("itemName")).toLowerCase();
				String price = (String) item.get("itemRate");
				System.out.println("New Item  : "+itemName);
				if(!inventoryItem.contains(itemName)){
					System.out.println("New Item Found : "+itemName);
					newItem.put(itemName,Double.parseDouble(price));
				}
			}
			
			PreparedStatement ps2 = conn.prepareStatement("INSERT INTO ITEM_INVENTORY " +
					" (ITEMCODE,ITEM_DESC,ITEM_REORDER_QTY,ITEM_SALES_PRICE,ITEM_SALES_PRICE_CREDIT," +
					"SUPPLIER,TIMESTAMP,ITEM_RETAIL_PRICE,QTY,UNIT_OF_MEASUREMENT,BILL_NO)" +
					" VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			
			int i = 0;
			for (Map.Entry<String, Double> e : newItem.entrySet()) {
			
			i++;
				long code = System.currentTimeMillis()+i;
				ps2.setString(1, code+"");
				ps2.setString(2, e.getKey());
				ps2.setInt(3, 0);
				ps2.setDouble(4, e.getValue());
				ps2.setDouble(5, e.getValue());
				ps2.setString(6, "N/A");
				ps2.setString(7, "date");
				ps2.setDouble(8, 0);
				ps2.setInt(9,0);
				ps2.setString(10, "N/A");
				ps2.setString(11, "N/A");
				ps2.executeUpdate();
				
			}
			
			
			ps2.close();
			conn.close();
		
	}
	public static void updateInventory(JSONArray itemArray) throws ClassNotFoundException, SQLException{
		
		Connection conn = DBConnection.getConnection();
		
		String selectQty = "SELECT QTY FROM ITEM_INVENTORY WHERE ITEMCODE = ? ";
		String query = "UPDATE ITEM_INVENTORY SET QTY = ? WHERE ITEMCODE = ? " ;
		
		for (int i = 0; i < itemArray.size(); i++) {
			JSONObject obj = (JSONObject) itemArray.get(i);
			String code = (String) obj.get("code");
			String oQty = (String) obj.get("itemQty");
			int orderQty = oQty != null ? Integer.parseInt(oQty):0;
			java.sql.PreparedStatement ps = conn.prepareStatement(selectQty);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			int qty = 0;
			while(rs.next()){
				 qty =  rs.getInt(1);
			}
			java.sql.PreparedStatement ps2 = conn.prepareStatement(query);
			int updatedQty = (qty-orderQty);
			updatedQty = updatedQty > 0 ? updatedQty : 0;
			ps2.setInt(1,updatedQty );
			ps2.setString(2, code);
			ps2.execute();
			ps2.close();
			rs.close();
			ps.close();
			
		}
		
		
		
	}
	
	
}
