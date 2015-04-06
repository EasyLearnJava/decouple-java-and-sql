package com.easylearnjava.h2db;

import org.h2.tools.Server;

public class CreateInMemoryServer {

	public static void main(String[] args) {
		try {

			Server server = Server.createTcpServer().start();
			System.out.println("In memory data base server started and connection is open.");
			System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:ATHENA");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
