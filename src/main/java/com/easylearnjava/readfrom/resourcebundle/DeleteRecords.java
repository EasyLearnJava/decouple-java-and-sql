package com.easylearnjava.readfrom.resourcebundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.easylearnjava.util.DBConnection;

public class DeleteRecords {

	public static void main(String[] args) {

		Connection conn = DBConnection.getH2DBConnection();
		try {
			//delete an employee with an employee id 2
			deleteEmployeeRecords(conn, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	//Read the properties file and retrieve the queries     
	private static String getQueryFromPropertiesFile(){
		
		ResourceBundle labels = ResourceBundle.getBundle("com/easylearnjava/readfrom/resourcebundle/sqlqueries");
		String query = labels.getString("DELETE_EMPLOYEE_BY_ID");
		return query;		
	}		
	
	
	//Delete Employee record
	public static void deleteEmployeeRecords(Connection connection, int id){
		
		PreparedStatement pStmt = null;
		String deleteSQL = getQueryFromPropertiesFile();
		try{
			pStmt = connection.prepareStatement(deleteSQL);
			pStmt.setInt(1, id);			
			int count = pStmt.executeUpdate();
			System.out.println("No of records effected : " + count);
			pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}		

}

