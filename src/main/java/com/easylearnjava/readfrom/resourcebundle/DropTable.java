package com.easylearnjava.readfrom.resourcebundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.easylearnjava.util.CheckForATableInDB;
import com.easylearnjava.util.DBConnection;

public class DropTable {  
	
	public static void main(String[] args) {

		Connection conn = DBConnection.getH2DBConnection();
		try {
			dropEmployeeTable(conn);
			CheckForATableInDB.checkIfTableExists(conn, "EMPLOYEE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Read the properties file and retrieve the queries     
	private static String getQueryFromPropertiesFile(){
		
		ResourceBundle labels = ResourceBundle.getBundle("com/easylearnjava/readfrom/resourcebundle/sqlqueries");
		String query = labels.getString("DROP_EMPLOYEE_TABLE");
		return query;		
	}
	
	
	//Drop table 
	public static void dropEmployeeTable(Connection connection){
		
		PreparedStatement pStmt = null;
		String dropSQL = getQueryFromPropertiesFile();
		try{
			pStmt = connection.prepareStatement(dropSQL);
			pStmt.execute();			
			pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

}
