package com.easylearnjava.readfrom.simplexml;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.easylearnjava.util.CheckForATableInDB;
import com.easylearnjava.util.DBConnection;

public class CreateTable {  
	
	EntityManager entityManager;
	
	public static void main(String[] args) {

		Connection conn = DBConnection.getH2DBConnection();
		try {
			createEmployeeTable(conn);
			CheckForATableInDB.checkIfTableExists(conn, "EMPLOYEE"); //Use all capitals for the table name
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Read the query from the xml
	public String getQueryFromXML() throws Exception {
		entityManager =  Persistence.createEntityManagerFactory("CreateTable").createEntityManager();
		Properties properties = readProperties("com/easylearnjava/readfrom/simplexml/simplesql-queries.xml");
		String sqlString = properties.getProperty("CREATE_EMPLOYEE_TABLE");
		Query query = entityManager.createQuery(sqlString);
		return query.getResultList().get(0).toString();
	}
	
	//Read the xml file specified
	public Properties readProperties(String xmlFileName) throws Exception {
		Properties properties = new Properties();
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream(xmlFileName);
		properties.loadFromXML(stream);
		return properties;
	}

	
	//Create Employee table
	public static void createEmployeeTable(Connection connection) throws Exception{
		
		CreateTable ct = new CreateTable();
		PreparedStatement pStmt = null;
		String createSQL = ct.getQueryFromXML();
		try{
			pStmt = connection.prepareStatement(createSQL);
			pStmt.execute();
			pStmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}	
	

}
