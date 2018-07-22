package com.glnetcomsolutions.report;

import java.io.File;

public class EnvironmentalConstants {

	private static String contextPath = null;
	private static String reportPath = null;
	private static String licenceKey = null;
	private static boolean isLicenced = true ;
	
	public static boolean isLicenced() {
		return isLicenced;
	}

	public static void setLicenced(boolean isLicenced) {
		EnvironmentalConstants.isLicenced = isLicenced;
	}

	public static String getLicenceKey() {
		return licenceKey;
	}

	public static void setLicenceKey(String licenceKey) {
		EnvironmentalConstants.licenceKey = licenceKey;
	}

	public static void setReportPath(String reportPath) {
		EnvironmentalConstants.reportPath = reportPath;
	}

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextpath) {
		contextPath = contextpath;
		reportPath = contextPath + "report" + File.separator;
	}

	public static String getReportPath() {
		return reportPath;
	}
}
