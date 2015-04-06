package com.easylearnjava.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class CheckForAllTablesInDB {
	
	public static void main(String[] args) {
		Connection conn = DBConnection.getH2DBConnection();
		try {			
			checkListOfTablesinDB(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Lists all the tables present in the db
	public static void checkListOfTablesinDB(Connection connection) {

		try {
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet res = meta.getTables(null, null, "%",
					new String[] { "TABLE" });	
			while (res.next()) {
				System.out.println("Table exists in the database...");
				System.out.println();
				System.out.println(" -- " + res.getString("TABLE_CAT") + " -- "
						+ res.getString("TABLE_SCHEM") + " -- "
						+ res.getString("TABLE_NAME") + " -- "
						+ res.getString("TABLE_TYPE") + " -- "
						+ res.getString("REMARKS"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
