package com.easylearnjava.readfrom.springconfigfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easylearnjava.util.DBConnection;

public class UpdateRecords {

	public static void main(String[] args) {

		Connection conn = DBConnection.getH2DBConnection();
		try {
			//update employee password with an employee id 2
			updateEmployeeRecords(conn, 2, "nosecret");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 // load xml file 
	 private Object getBeanFromFactory(String beanId) {
		 Object obj = null; 
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("springtutorial-context.xml");
		 if (ctx != null && beanId != null) {
			 obj = ctx.getBean(beanId);
		 }
		 return obj;
	 }
	
	 
	 // get the query by id
	 private String getQueryFromXMLUsingSpring(String queryId) {
		 Map queryMap = null;
		 String query = null;
		 queryMap = (Map) this.getBeanFromFactory("queries");
		 query = (String) queryMap.get(queryId);
		 return query;
	 }
	
	 
	//Update Employee records
	public static void updateEmployeeRecords(Connection connection, int id, String pwd){
		
		UpdateRecords ur = new UpdateRecords();
		PreparedStatement pStmt = null;
		String updateSQL = ur.getQueryFromXMLUsingSpring("UPDATE_EMPLOYEE_PASSWORD_BY_ID");
		try{
			pStmt = connection.prepareStatement(updateSQL);
			pStmt.setString(1, pwd);
			pStmt.setInt(2, id);			
			int count = pStmt.executeUpdate();
			System.out.println("No of records effected : " + count);
			pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}		

}

