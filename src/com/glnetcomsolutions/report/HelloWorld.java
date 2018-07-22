package com.glnetcomsolutions.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A very simple class that shows how to load the driver, create a database,
 * create a table, and insert some data.
 */
public class HelloWorld {

    /**
     * Called when ran from command line.
     *
     * @param args ignored
     */
    public static void main(String... args) throws Exception {
        // delete the database named 'test' in the user home directory
    	
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = dateFormat.parse("25/06/2015");
    	long time = date.getTime();
    	System.out.println(time);
    	 Timestamp expiration_time = new Timestamp(time);

    	Timestamp actualTimeStampDate = null;

        try {
            Date actual = new Date();
            actualTimeStampDate = new Timestamp(actual.getTime());
            System.out.println(System.currentTimeMillis()+","+actual.getTime());
            
            Date today = new Date();
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(today);
			 cal.add(Calendar.DATE, 15); // add 10 days
			  
			 today = cal.getTime();
			 System.out.println("15 = "+today.getTime());
			 
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        boolean expired = (expiration_time.getTime() < actualTimeStampDate.getTime());

        // Everything seems ok, except the boolean
        System.out.println("product exp: "+expiration_time+", actual: "+actualTimeStampDate+" expired? "+expired);
        
    	
        Class.forName("org.h2.Driver");
     //  Connection conn = DriverManager.getConnection("jdbc:h2:E:/Study/ChequePrinter/apache-tomcat-7.0.28-dev/database/billing","ashish","panery");
       Connection conn = DriverManager.getConnection("jdbc:h2:E:/Study/Eclipse Workspace/RajRatan/Invoice-Hojri/WebContent/database/billing","ashish","panery");
//        Connection conn = DriverManager.getConnection("jdbc:h2:D:/Damini/Assignments/Invoice-Hojri/WebContent/database/billing","ashish","panery");
        
        Statement stat = conn.createStatement();
        
       // stat.execute("CREATE TABLE CUSTOMER (CUSTOMERNAME VARCHAR(1024),CUSTOMERADDRESS VARCHAR(1024),CUSTOMERADDRESSCITY VARCHAR(1024))");
		
   
//        stat.execute("DROP TABLE CUSTOMERDETAIL ");
       //stat.execute("CREATE TABLE CUSTOMERDETAIL (ID INT,ORANIZATIONNAME VARCHAR(1024),EMAILID VARCHAR(1024),TELEPHONENUMBER VARCHAR(1024),TINNUMBER VARCHAR(1024),VATNUMBER VARCHAR(1024),ADDRESS VARCHAR(1024))");
//        stat.execute("INSERT INTO CUSTOMERDETAIL VALUES(1,'YOUR ORGANIZATION NAME','TIN Number:12345671218','TELEPHONE NUMBER : 1234567890')");
//        stat.execute("DROP TABLE LICENSE ");
   //    stat.execute("ALTER TABLE CUSTOMERDETAIL ADD COMPANYLOGO BLOB");
//      stat.execute("CREATE TABLE LICENSE (CUSTOMERNAME VARCHAR(1024),ORGANIZATION VARCHAR(1024),PHONENO VARCHAR(1024),EMAILID VARCHAR(1024),KEYBASE VARCHAR(1024),LICENSEKEY VARCHAR(1024),LICENSETYPE VARCHAR(1024),INVOICECOUNT INT,USERCOUNT INT,EXPIRATIONTIME VARCHAR(1024))");
      //    stat.execute("DELETE FROM INVOICEMASTER ");
//    stat.execute("DELETE FROM ITEM_INVENTORY ");
//    stat.execute("DELETE FROM CUSTOMER ");
//
//    stat.execute("DELETE FROM EXPENSES ");
////
//    stat.execute("DROP TABLE EXPENSES ");
//    
//	stat.execute("CREATE TABLE EXPENSES (CATEGORY VARCHAR(1024),EXPENSE_DATE LONG," +
//	"AMOUNT DOUBLE, SUBMITTED_BY VARCHAR(1024)," +
//	" DESCRIPTION VARCHAR(256), E_ID VARCHAR(256), YEAR INT, MONTH INT, DATE INT, TOTAL_EXPENSE_AMOUNT DOUBLE)");
        
//        stat.execute("DROP TABLE  INVOICEMASTER ");
//		stat.execute("CREATE TABLE INVOICEMASTER (INVOICENO VARCHAR(256) PRIMARY KEY, INVOICEDATE VARCHAR(256), " +
//				"DUEDATE VARCHAR(256), CUSTOMERNAME VARCHAR(1024), CUSTOMERADDRESS VARCHAR(1024)," +
//				" SUBTOTAL DOUBLE(256), TAXAMOUNT DOUBLE(256), TOTALITEM VARCHAR(256), " +
//				"TOTAL DOUBLE(256), ITEMARRAY VARCHAR(8000),INVOICETYPE VARCHAR(256)," +
//				"YEAR INT,MONTH INT,DATE INT)");
		
		//stat.execute("select * from CUSTOMERDETAIL");
//    stat.execute(" ALTER TABLE INVOICEMASTER ADD  YEAR INT");
//    stat.execute(" ALTER TABLE INVOICEMASTER ADD  MONTH INT");
//    stat.execute(" ALTER TABLE INVOICEMASTER ADD   DATE INT");

//      stat.execute(" ALTER TABLE INVOICEMASTER ADD   BRANCH VARCHAR(1024)");
        
//        stat.execute(" ALTER TABLE ITEM_INVENTORY ADD APPLICABLE_TAX VARCHAR(1024)");
        
//      
//        stat.execute("CREATE TABLE LICENCE (LICENCE_TYPE VARCHAR(1024), LICENCE_KEY VARCHAR(1024), EXPIRY_DATE VARCHAR(1024),INSTALLATION_DATE VARCHAR(1024))");
//        stat.execute("CREATE TABLE TAX (TAXID VARCHAR(1024), TAXNAME VARCHAR(1024), TAXVALUE VARCHAR(1024))");
        
//        stat.execute("CREATE TABLE ITEM_INVENTORY (ITEMCODE VARCHAR(256) primary key," +
//				"ITEM_DESC VARCHAR(1024),QTY INT, SUPPLIER VARCHAR(1024), ITEM_REORDER_QTY INT, " +
//				"ITEM_RETAIL_PRICE DOUBLE, ITEM_SALES_PRICE DOUBLE, UNIT_OF_MEASUREMENT VARCHAR(1024)," +
//				" TIMESTAMP VARCHAR(1024))");
//        stat.execute(" ALTER TABLE ITEM_INVENTORY ADD  ITEM_RETAIL_PRICE DOUBLE");
//        stat.execute(" ALTER TABLE ITEM_INVENTORY ADD  ITEM_SALES_PRICE_CREDIT DOUBLE");
        stat.execute(" ALTER TABLE ITEM_INVENTORY DROP COLUMN  BRANCH");
//        stat.execute(" ALTER TABLE ITEM_INVENTORY ADD  BRANCH VARCHAR(1024)");
        
//        stat.execute(" UPDATE ITEM_INVENTORY SET  BRANCH ='Branch1'");
//        stat.execute(" ALTER TABLE INVOICEMASTER ADD  INVOICETYPE VARCHAR(256)");
//        stat.execute("CREATE TABLE USER (USERNAME VARCHAR(1024), PASSWORD VARCHAR(1024),ROLE VARCHAR(1024),FNAME VARCHAR(1024))");
   // 	stat.execute("INSERT INTO USER VALUES('administrator','password','admin','Ashish')");
//    	stat.execute("INSERT INTO USER VALUES('counter','counter','user','Ankit')");
        
//      stat.execute(" ALTER TABLE ITEM_INVENTORY ADD  BILL_NO VARCHAR(1024)");
//        stat.execute("INSERT INTO ITEM_INVENTORY VALUES ('75148545855','Kasdk',10,'Hamid Ansari',12,1241,'Meter','24/09/2014')");
//        stat.execute("drop table ITEM_INVENTORY");
//        stat.execute("CREATE TABLE INVOICEMASTER (INVOICENO VARCHAR(256) PRIMARY KEY, INVOICEDATE VARCHAR(256), DUEDATE VARCHAR(256), CUSTOMERNAME VARCHAR(1024), CUSTOMERADDRESS VARCHAR(1024), SUBTOTAL VARCHAR(256), TAXAMOUNT VARCHAR(256), TOTALITEM VARCHAR(256), TOTAL VARCHAR(256), ITEMARRAY VARCHAR(8000))");	
        
        // this line would initialize the database
        // from the SQL script file 'init.sql'
        // stat.execute("runscript from 'init.sql'");
//        companyName="+companyName+"&acNumber="+acNumber+"&bankName="+bankName+"&defaultPrinter="+defaultPrinter
//        stat.execute("create table admin(company_name varchar(1024),acnumber varchar(1024),bank_name varchar(1024), defaultprinter varchar(1024))");
//       stat.execute("create table groups(groupname varchar(1024))");
//      stat.execute(" Alter table bulkprinting add id int");
//      
                
//        ResultSet rs = stat.executeQuery("select * from CUSTOMERDETAIL");
//        while(rs.next()){
//			System.out.println(rs.getString(1));
//		}
        
//        stat.execute("CREATE TABLE INVOICETEMPLATE (PART VARCHAR(1024), HTMLCONTENT VARCHAR(8024))");
//        stat.execute("INSERT INTO INVOICETEMPLATE VALUES('header','fist')");
//        stat.execute("INSERT INTO INVOICETEMPLATE VALUES('footer','footer')");
//       stat.execute("CREATE TABLE VENDORDETAILS (VENDORNAME VARCHAR(1024),VENDORURL VARCHAR(1024),AUTHKEY VARCHAR(1024),ROUTE VARCHAR(1024),COUNTRYCODE VARCHAR(1024))");
//        ResultSet rs = null;
//        rs = stat.executeQuery("select * from CUSTOMERDETAIL");
//        System.out.println(rs.getRow());
//        while (rs.next()) {
//            System.out.println("a"+rs.getString(1));
//        }
//        stat.execute("DELETE FROM CUSTOMERDETAIL");
        stat.close();
        conn.close();
    }

}