package com.easylearnjava.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class CheckForATableInDB {
	
	public static void main(String[] args) {
		Connection conn = DBConnection.getH2DBConnection();
		try {			
			checkIfTableExists(conn, "EMPLOYEE"); //Use all capitals for the table name
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void checkIfTableExists(Connection connection, String tableName) {

		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet res = meta.getTables("ATHENA", "PUBLIC", tableName,
					new String[] { "TABLE" });	
			while (res.next()) {
				System.out.println("Table exists in the database...");
				System.out.println();
				System.out.println("Table Name : " + res.getString("TABLE_NAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
