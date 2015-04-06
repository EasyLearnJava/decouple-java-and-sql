package com.easylearnjava.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost:9092/mem:ATHENA;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
    public static Connection getH2DBConnection() {
		Connection conn = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
    
    
}
