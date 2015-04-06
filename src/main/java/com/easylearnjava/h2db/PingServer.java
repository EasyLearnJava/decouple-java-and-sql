package com.easylearnjava.h2db;

import java.sql.Connection;

import com.easylearnjava.util.DBConnection;

public class PingServer {

	public static void main(String[] args) {

		//Get a connection object returned by getH2DBConnection method
		Connection conn = DBConnection.getH2DBConnection();
		try {
			//Check if connection is valid
			if (conn.isValid(1000)) {
				System.out.println("Connection established and server reachable...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
