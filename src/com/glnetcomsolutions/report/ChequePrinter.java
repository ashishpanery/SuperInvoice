package com.glnetcomsolutions.report;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.view.JasperViewer;

public class ChequePrinter {
	
	public static int getChequeId(){
		int checkId = 1;
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) FROM CHEQUE_HISTORY";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				checkId = rs.getInt(1);
				checkId++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return checkId;
	}
	
	public static double getAccountBalance(String accountNumber){
		double balance = 0;
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBConnection.getConnection();
			
			String query = "SELECT * FROM ACCOUNTS WHERE ACNUMBER = ?";
			ps = conn.prepareStatement(query);
			ps.setString(1, accountNumber);
			rs = ps.executeQuery();
			if(rs.next()){
				balance = rs.getDouble("BALANCE");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return balance;
	}
	public static void updateAccountBalance(String accountNumber, double amount){

		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try{
			conn = DBConnection.getConnection();
			
			String query = "UPDATE ACCOUNTS SET BALANCE = ? WHERE ACNUMBER = ?";
			ps = conn.prepareStatement(query);
			ps.setDouble(1, amount);
			ps.setString(2, accountNumber);
			ps.execute();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	
	}
	
	public static void saveCheque(ChequeBean bean){
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try{
			conn = DBConnection.getConnection();
			
			String query = "INSERT INTO CHEQUE_HISTORY (CHEQUEID,CHEQUE_NUMBER,PARTY, AMOUNT, AMOUNT_IN_WORDS, ISSUE_DATE, CHEQUE_TYPE, BANK, ACCOUNT_NUMBER, REMARKS)VALUES (?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			int i = 1;
			ps.setInt(i++, bean.getChequeId());
			ps.setString(i++, bean.getChequeNo());
			ps.setString(i++, bean.getParty());
			ps.setDouble(i++, bean.getAmount());
			ps.setString(i++, bean.getAmountInWordsWB());
			ps.setString(i++, bean.getIssueDate());
			ps.setString(i++, bean.getChequeType());
			ps.setString(i++, bean.getBankType());
			ps.setString(i++, bean.getAcnumber());
			ps.setString(i++, bean.getRemarks());
			
			ps.execute();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public static long printCheck(ChequeBean bean, boolean isCheque) {

		try {
			String reportSuffix = "counterfoil";
			if(isCheque){
				reportSuffix = "";
			}
			
			JasperPrint jasperPrint = null;
			Map parameters = new HashMap();
			java.util.List<ChequeBean> list = new ArrayList<>();
			list.add(bean);
			String report = "idbichequeacpayee";
			if(bean.getChequeType().equals("Bearer")){
				if(bean.getBankType().equalsIgnoreCase("axis")){
					report = "axischequebearerpayee";
				}else{
					report = "idbichequebearerpayee";
				}
			}else{
				if(bean.getBankType().equalsIgnoreCase("axis")){
					report = "axischequeacpayee";
				}else{
					report = "idbichequeacpayee";
				}
			}
			report = report + reportSuffix;
			String reportName = EnvironmentalConstants.getReportPath()+File.separator+report;
			jasperPrint = JasperFillManager.fillReport(reportName + ".jasper",
					parameters, new JRBeanCollectionDataSource(list));

//			JRSaver.saveObject(jasperPrint, reportName + ".jrprint");
			long timestamp = System.currentTimeMillis();
			JasperExportManager.exportReportToPdfFile(jasperPrint,reportName+File.separator+".."+File.separator+timestamp+".pdf");
//			JasperViewer.viewReport(jasperPrint);
			String selectedPrinter = bean.getSelectedPrinter();

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            PrintService selectedService = null;

            if(services.length != 0 || services != null)
            {
                for(PrintService service : services){
                    String existingPrinter = service.getName().toLowerCase();
                    if(existingPrinter.equalsIgnoreCase(selectedPrinter))
                    {
                        selectedService = service;
                        break;
                    }
                }

                if(selectedService != null)
                {
                    printerJob.setPrintService(selectedService);
                    boolean printSucceed = JasperPrintManager.printReport(jasperPrint, false);
                    if(isCheque){
                    	saveCheque(bean);
                    	double balance = getAccountBalance(bean.getAcnumber());
                    	balance = balance - bean.getAmount();
                    	updateAccountBalance(bean.getAcnumber(), balance);
                    }
                    
                }
            }
			return timestamp;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("EXCEPTION: " + ex);
			return 0;
		}
		
	}

	public static void main(String[] args) throws JRException, ParseException, PrinterException {

		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);

        for (PrintService printer : printServices)
            System.out.println("Printer: " + printer.getName()); 
		
		
//		try {
			ChequeBean bean = new ChequeBean();
			bean.setAmount(123576763);
			bean.setBankType("Axis");
			bean.setChequeNo("432341");
			bean.setIssueDate("2014-06-01");
			bean.setDate(29);
			bean.setYear(14);
			bean.setMonth(06);
			bean.setParty("RADHA RAMAN KISHANA DAAS CHAMPAK LAL GADHA MEMORABLE SOCIETY");
			bean.setAmountInWords(" TWELVE CRORE THIRTY-FIVE LAKH SEVENTY-SIX THOUSAND SEVEN HUNDRED SIXTY-THREE RUPEES   AND   ZERO  PAISE ONLY");
			bean.setAcnumber("61047015522");
			bean.setRemarks("Test Remarks Test Remarks Test Remarks");
			JasperPrint jasperPrint = null;
			Map parameters = new HashMap();

			java.util.List<ChequeBean> list = new ArrayList<>();
			list.add(bean);

			String reportName = "E:\\Study\\Eclipse Workspace\\ChequePrintingService\\WebContent\\report\\axischequeacpayeecounterfoil";
			jasperPrint = JasperFillManager.fillReport(reportName + ".jasper",
					parameters, new JRBeanCollectionDataSource(list));
			JRSaver.saveObject(jasperPrint, reportName + ".jrprint");
//
			JasperExportManager.exportReportToPdfFile(jasperPrint,"E:\\Study\\Eclipse Workspace\\ChequePrintingService\\WebContent\\report\\"+System.currentTimeMillis()+".pdf");
			JasperViewer.viewReport(jasperPrint);
			String selectedPrinter = "Canon LBP2900";

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            PrintService selectedService = null;
            services = null;
            if(services.length != 0 || services != null)
            {
                for(PrintService service : services){
                    String existingPrinter = service.getName().toLowerCase();
                    if(existingPrinter.equalsIgnoreCase(selectedPrinter))
                    {
                        selectedService = service;
                        break;
                    }
                }

                if(selectedService != null)
                {
                    printerJob.setPrintService(selectedService);
                    boolean printSucceed = JasperPrintManager.printReport(jasperPrint, false);
                }
            }
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.out.println("EXCEPTION: " + ex);
//		}

	}
}
