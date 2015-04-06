package com.easylearnjava.readfrom.springconfigfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easylearnjava.util.DBConnection;

public class ReadRecords {

	public static void main(String[] args) {

		Connection conn = DBConnection.getH2DBConnection();
		try {
			readEmployeeRecords(conn);
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

	
	//Read Employee records
	public static void readEmployeeRecords(Connection connection){
		
		ReadRecords rr = new ReadRecords();
		PreparedStatement pStmt = null;
		String readSQL = rr.getQueryFromXMLUsingSpring("SELECT_ALL_EMPLOYEES");
		try{
			pStmt = connection.prepareStatement(readSQL);
			ResultSet rs = pStmt.executeQuery();
			System.out.println("EMPLOYEE ID " + "\t" + "EMPLOYEE NAME " + "\t" + "EMPLOYEE PASSWORD");
			System.out.println("----------- " + "\t" + "-------------- " + "\t" + "-----------------");
			while (rs.next()) {
				int id = rs.getInt("EMP_ID");
				String name = rs.getString("EMP_NAME");
				String password = rs.getString("EMP_PASSWORD");
				System.out.println(id + "\t\t" + name + "\t\t" + password);
			}			
			pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}		

}

