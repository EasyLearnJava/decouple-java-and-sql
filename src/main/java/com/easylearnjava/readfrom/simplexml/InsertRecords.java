package com.easylearnjava.readfrom.simplexml;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.easylearnjava.util.DBConnection;

public class InsertRecords {

	EntityManager entityManager;
	
	public static void main(String[] args) {

		Connection conn = DBConnection.getH2DBConnection();
		try {
			insertEmployeeRecords(conn, 1, "raghu", "secret");
			insertEmployeeRecords(conn, 2, "naveen", "topsecret");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Read the query from the xml
	public String getQueryFromXML() throws Exception {
		Properties properties = readProperties("com/easylearnjava/readfrom/simplexml/simplesql-queries.xml");
		String sqlString = properties.getProperty("INSERT_EMPLOYEE_RECORDS");
		Query query = entityManager.createNativeQuery(sqlString);
		return query.getResultList().get(0).toString();
	}
	
	//Read the xml file specified
	public Properties readProperties(String xmlFileName) throws Exception {
		Properties properties = new Properties();
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream(xmlFileName);
		properties.loadFromXML(stream);
		return properties;
	}
	
	//Insert Employee records
	public static void insertEmployeeRecords(Connection connection, int id, String name, String pwd) throws Exception{
		
		InsertRecords ir = new InsertRecords();
		PreparedStatement pStmt = null;
		String insertSQL = ir.getQueryFromXML();
		try{
			pStmt = connection.prepareStatement(insertSQL);
			pStmt.setInt(1, id);
			pStmt.setString(2, name);
			pStmt.setString(3, pwd);
			int count = pStmt.executeUpdate();
			System.out.println("No of records effected : " + count);
			pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}		

}

