package com.glnetcomsolutions.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChequeBean {
	private int chequeId;
	private String chequeNo;
	private String party;
	private String bankType;
	private double amount;
	private String issueDate;
	private String amountInWords;
	private String amountInWordsWB;
	public String getAmountInWordsWB() {
		return amountInWordsWB;
	}
	public void setAmountInWordsWB(String amountInWordsWB) {
		this.amountInWordsWB = amountInWordsWB;
	}
	private String selectedPrinter;
	private String dateforAxis;
	private String acnumber;
	private String remarks;
	public String getAcnumber() {
		return acnumber;
	}
	public void setAcnumber(String acnumber) {
		this.acnumber = acnumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDateforAxis() {
		return dateforAxis;
	}
	public void setDateforAxis(String dateforAxis) {
		this.dateforAxis = dateforAxis;
	}
	public int getChequeId() {
		return chequeId;
	}
	public void setChequeId(int chequeId) {
		this.chequeId = chequeId;
	}
	public String getSelectedPrinter() {
		return selectedPrinter;
	}
	public void setSelectedPrinter(String selectedPrinter) {
		this.selectedPrinter = selectedPrinter;
	}
	public String getAmountInWords2() {
		return amountInWords2;
	}
	public void setAmountInWords2(String amountInWords2) {
		this.amountInWords2 = amountInWords2;
	}
	private String amountInWords2;
	private String amountWithSlash;
	private int date;
	private int month;
	private int year;
	private String dateWithSlash;
	private String monthWithSlash;
	private String chequeType;
	
	public String getChequeType() {
		return chequeType;
	}
	public void setChequeType(String chequeType) {
		this.chequeType = chequeType;
	}
	public String getDateWithSlash() {
		return dateWithSlash;
	}
	public void setDateWithSlash(String dateWithSlash) {
		if("Axis".equalsIgnoreCase(this.bankType)){
			
			this.dateWithSlash = dateWithSlash;
		}else{
			this.dateWithSlash = dateWithSlash+"-";;
		}
	}
	public String getMonthWithSlash() {
		return monthWithSlash;
	}
	public void setMonthWithSlash(String monthWithSlash) {
		if("Axis".equalsIgnoreCase(this.bankType)){
			
			this.monthWithSlash = monthWithSlash;
		}else{
			this.monthWithSlash = monthWithSlash+"-";;
		}
		
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getAmountWithSlash() {
		return amountWithSlash;
	}
	public void setAmountWithSlash(String amountWithSlash) {
		this.amountWithSlash = amountWithSlash;
	}
	public String getAmountInWords() {
		return amountInWords;
	}
	public void setAmountInWords(String amount) {
		this.setAmountInWordsWB(amount);
		if(amount.length()>50){
			this.amountInWords = amount.substring(0,51);
			this.amountInWords2 = amount.substring(51,amount.length());
		}else{
			
			this.amountInWords = amount;
			this.amountInWords2 = "";
		}
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double d) {
		this.amount = d;
		this.setAmountWithSlash(d+".00/-");
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) throws ParseException {
		this.issueDate = issueDate;
		SimpleDateFormat st = new SimpleDateFormat("YYYY-MM-dd");
		Date date =  st.parse(issueDate);
		String [] dateParts = issueDate.split("-");
		this.setDateWithSlash(dateParts[2]);
		this.setMonthWithSlash(dateParts[1]);
		this.setDate(date.getDate());
		this.setMonth(date.getMonth());
		this.setYear(Integer.parseInt(dateParts[0].substring(2)));
		System.out.println(this.getDateWithSlash()+","+this.getMonthWithSlash());
		String axisDate = dateParts[2]+dateParts[1]+dateParts[0];
		StringBuilder sb = new StringBuilder();
		for (char c : axisDate.toCharArray()) {
			sb.append(" ");
			sb.append(c);
			sb.append("  ");
		}
		this.setDateforAxis(sb.toString());
		
	}
}
