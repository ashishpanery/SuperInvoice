package com.glnetcomsolutions.report;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static String dbDriver = null;
	private static String dbUrl = null;
	
	public static String getDbDriver() {
		return dbDriver;
	}

	public static void setDbDriver(String driverName) {
		dbDriver = driverName;
	}

	public static String getDbUrl() {
		return dbUrl;
	}

	public static void setDbUrl(String url) {
				dbUrl = url;
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException{
			Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.getConnection(dbUrl,"ashish","panery");
	        return conn;
	}
	
}
