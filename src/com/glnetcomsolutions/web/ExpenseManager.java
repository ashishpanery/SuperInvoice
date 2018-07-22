package com.glnetcomsolutions.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.glnetcomsolutions.report.DBConnection;

public class ExpenseManager {
	
	public static void createExpenseTable(){
		Connection conn = null;
		 Statement stat = null;
		try {
			conn = DBConnection.getConnection();
			stat = conn.createStatement();
			stat.execute("CREATE TABLE EXPENSES (CATEGORY VARCHAR(1024),EXPENSE_DATE LONG," +
					 "AMOUNT DOUBLE, SUBMITTED_BY VARCHAR(1024)," +
					 " DESCRIPTION VARCHAR(256), E_ID VARCHAR(256), YEAR INT, MONTH INT, DATE INT,TOTAL_EXPENSE_AMOUNT DOUBLE, DUE_AMOUNT DOUBLE)");
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			if (stat != null){
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void dropExpenseTable(){
		Connection conn = null;
		 Statement stat = null;
		try {
			conn = DBConnection.getConnection();
			stat = conn.createStatement();
			stat.execute("DROP TABLE EXPENSES");
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			if (stat != null){
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/*public static void getDueData(){
		
		Connection conn = null;
		 Statement stat = null;
		 try {
				conn = DBConnection.getConnection();
				stat = conn.createStatement();
				JSONArray arr = new JSONArray();
		        PreparedStatement ps=conn.prepareStatement("SELECT ITEM_DESC FROM ITEM_INVENTORY WHERE ITEM_REORDER_QTY <= QTY");
				
				 ResultSet rs=ps.executeQuery();   
	             while (rs.next()) {
	             String itemlist = rs.getString("ITEM_DESC");
				System.out.println(itemlist);
	       }				 
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				
				if (stat != null){
					try {
						stat.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		
		
	}*/
	
	
	public static JSONObject getDueData() throws ClassNotFoundException,
			SQLException {

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject object = new JSONObject();
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT ITEM_DESC,QTY,ITEM_REORDER_QTY FROM ITEM_INVENTORY WHERE ITEM_REORDER_QTY > QTY";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			JSONArray arr = new JSONArray();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("itemname", rs.getString("ITEM_DESC"));
				obj.put("qty", rs.getInt("QTY"));
				obj.put("reorder_qty", rs.getInt("ITEM_REORDER_QTY"));
				arr.add(obj);
			}

			object.put("data", arr);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		return object;
	}

	
	@SuppressWarnings("unchecked")
	public static JSONObject getAllIncome() throws ClassNotFoundException,
			SQLException {

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject object = new JSONObject();
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM INCOME";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			int total = 0;
			JSONArray arr = new JSONArray();
			JSONObject userobj = new JSONObject();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				total++;
				obj.put("category", rs.getString("CATEGORY"));
				obj.put("income_date", Util.convertLongToString(rs.getLong("INCOME_DATE")));
				obj.put("amount", rs.getDouble("AMOUNT"));
				obj.put("received_by", rs.getString("RECEIVED_BY"));
				obj.put("description", rs.getString("DESCRIPTION"));
				obj.put("year", rs.getInt("YEAR"));
				obj.put("month", rs.getInt("MONTH"));
				obj.put("date", rs.getInt("MONTH"));
				obj.put("incomeId", rs.getString("I_ID"));
				

				arr.add(obj);
			}

			object.put("page", "1");
			object.put("total", 1);
			object.put("records", "" + total);
			object.put("rows", arr);
			object.put("userdata", userobj);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		return object;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject getAllExpenses() throws ClassNotFoundException,
			SQLException {

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject object = new JSONObject();
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM EXPENSES";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			int total = 0;
			JSONArray arr = new JSONArray();
			JSONObject userobj = new JSONObject();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				total++;
				obj.put("category", rs.getString("CATEGORY"));
				obj.put("expense_date", Util.convertLongToString(rs.getLong("EXPENSE_DATE")));
				obj.put("amount", rs.getDouble("AMOUNT"));
				obj.put("submitted_by", rs.getString("SUBMITTED_BY"));
				obj.put("description", rs.getString("DESCRIPTION"));
				obj.put("year", rs.getInt("YEAR"));
				obj.put("month", rs.getInt("MONTH"));
				obj.put("date", rs.getInt("MONTH"));
				obj.put("expenseId", rs.getString("E_ID"));

				arr.add(obj);
			}

			object.put("page", "1");
			object.put("total", 1);
			object.put("records", "" + total);
			object.put("rows", arr);
			object.put("userdata", userobj);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		return object;
	}
	public static JSONObject getInvoiceIncomeReportForBarChart(String reportType, Set<Integer> timeseries)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		JSONObject incomeData = new JSONObject();
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT SUM(cast(isnull(TOTAL,0) as decimal(12,2))) AS AMOUNT, YEAR AS TIME  FROM INVOICEMASTER GROUP BY YEAR";
			if("yearly".equalsIgnoreCase(reportType)){
				 query = "SELECT SUM(cast(isnull(TOTAL,0) as decimal(12,2))) AS AMOUNT, YEAR AS TIME  FROM INVOICEMASTER GROUP BY YEAR";
			}else if("monthly".equalsIgnoreCase(reportType)){
				Date d = new Date();
				int year = d.getYear()+1900;
				 query = "SELECT SUM(cast(isnull(TOTAL,0) as decimal(12,2))) AS AMOUNT, MONTH AS TIME FROM INVOICEMASTER WHERE YEAR = "+year+" GROUP BY MONTH";
			}else if("daily".equalsIgnoreCase(reportType)){
				Date d = new Date();
				int month = d.getMonth()+1;
				 query = "SELECT SUM(cast(isnull(TOTAL,0) as decimal(12,2))) AS AMOUNT, DATE AS TIME FROM INVOICEMASTER WHERE MONTH = "+month+" GROUP BY DATE";
			}
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				incomeData.put(rs.getInt("TIME"), rs.getInt("AMOUNT"));
				timeseries.add(rs.getInt("TIME"));
			}

		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		return incomeData;
	}
	public static JSONObject getBarChartData(String reportType) throws ClassNotFoundException,
	SQLException {
		JSONObject obj = new JSONObject();
		Set<Integer> timeseries = new HashSet<Integer>();
		JSONObject allDataObject = new JSONObject();
		JSONObject expenseData = ExpenseManager.getExpenseReportForBarChart(reportType, timeseries);
		JSONObject incomeData = ExpenseManager.getInvoiceIncomeReportForBarChart(reportType, timeseries);
		
		
		JSONArray expenseDataArray = new JSONArray();
		JSONArray incomeDataArray = new JSONArray();
		for (Integer time : timeseries) {
			if(expenseData.containsKey(time)){
				expenseDataArray.add(expenseData.get(time));
			}else{
				expenseDataArray.add(0);
			}
			if(incomeData.containsKey(time)){
				incomeDataArray.add(incomeData.get(time));
			}else{
				incomeDataArray.add(0);
			}
		}
		JSONObject expense = new JSONObject();
		expense.put("name", "Expenses");
		expense.put("data", expenseDataArray);
		
		JSONObject income = new JSONObject();
		income.put("name", "Income");
		income.put("data", incomeDataArray);
		obj.put("incomeData", income);
		obj.put("expenseData", expense);
		obj.put("timeseries", timeseries);
		return obj;
	}
	
	
	public static JSONObject getExpensesByCategory(String reportType) throws ClassNotFoundException,
			SQLException {

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT SUM(AMOUNT) AS AMOUNT, CATEGORY FROM EXPENSES GROUP BY CATEGORY";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			int total = 0;
			while (rs.next()) {
				JSONArray obj = new JSONArray();
				total++;
				obj.add(rs.getString("CATEGORY"));
				obj.add(rs.getInt("AMOUNT"));
				array.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		JSONObject o = new JSONObject();
		o.put("data", array);
		return o;
	}

	public static void saveNewExpense(JSONObject data) throws SQLException{
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try {

			conn = DBConnection.getConnection();

			String query = "INSERT INTO EXPENSE"
					+ " (CATEGORY,EXPENSE_DATE,AMOUNT,SUBMITTED_BY,"
					+ " DESCRIPTION, E_ID, YEAR, MONTH, DATE,TOTAL_EXPENSE_AMOUNT, DUE_AMOUNT)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

			int i = 1;
			ps = conn.prepareStatement(query);
			ps.setString(i++, (String) data.get("category"));
			ps.setLong(i++, Util.convertStringDateToLong((String) data
					.get("date")));
			ps.setDouble(i++, Double.valueOf((String) data.get("amount")));
			ps.setString(i++, (String) data.get("submittedBy"));
			ps.setString(i++, (String) data.get("description"));
			ps.setString(i++,	System.currentTimeMillis()+"");
			ps.setInt(i++, Util.getYearFromDate((String) data
					.get("date")));
			ps.setInt(i++, Util.getMonthFromDate((String) data
					.get("date")));
			ps.setInt(i++, Util.getDateFromDate((String) data
					.get("date")));
			System.out.println(Util.getYearFromDate((String) data
					.get("date")));
			ps.setDouble(i++, Double.valueOf((String) data.get("totalAmount")));
			ps.setDouble(i++, Double.valueOf((String) data.get("dueAmount")));
			
			ps.execute();

			System.out.println("Expense Saved.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
	}
	public static JSONArray getAllIncomeCategory() throws SQLException {
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray arr = new JSONArray();
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT DISTINCT CATEGORY FROM INCOME";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				arr.add(rs.getString("CATEGORY"));
			}

		} catch (Exception e) {

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
	public static JSONArray getAllCategory() throws SQLException {
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray arr = new JSONArray();
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT DISTINCT CATEGORY FROM EXPENSES";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				arr.add(rs.getString("CATEGORY"));
				
			}

		} catch (Exception e) {

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
	public static void  saveCategory(String category) throws SQLException {
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try {
			conn = DBConnection.getConnection();
			String query = "INSERT INTO EXPENSES_CATEGORY VALUES(?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, category);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}

	}

	public static JSONObject getExpenseReportForBarChart(String reportType, Set<Integer> timeseries)
			throws ClassNotFoundException, SQLException {

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		JSONArray array = new JSONArray();
		JSONObject expenseData =  new JSONObject();
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT SUM(AMOUNT) AS AMOUNT, YEAR AS TIME  FROM EXPENSES GROUP BY YEAR";
			if("yearly".equalsIgnoreCase(reportType)){
				 query = "SELECT SUM(AMOUNT) AS AMOUNT, YEAR AS TIME  FROM EXPENSES GROUP BY YEAR";
			}else if("monthly".equalsIgnoreCase(reportType)){
				Date d = new Date();
				int year = d.getYear()+1900;
				 query = "SELECT SUM(AMOUNT) AS AMOUNT, MONTH AS TIME FROM EXPENSES WHERE YEAR = "+year+" GROUP BY MONTH";
			}else if("daily".equalsIgnoreCase(reportType)){
				Date d = new Date();
				int month = d.getMonth()+1;
				 query = "SELECT SUM(AMOUNT) AS AMOUNT, DATE AS TIME FROM EXPENSES WHERE MONTH = "+month+" GROUP BY DATE";
			}
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				expenseData.put(rs.getInt("TIME"), rs.getInt("AMOUNT"));
				timeseries.add(rs.getInt("TIME"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		
		return expenseData;
	}
}
