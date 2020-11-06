package com.capg.jdbc.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;

public class AddressBookService 
{
	public static final String URL = "jdbc:mysql://localhost:3306/AddressBookService";
	public static final String USER = "root";
	public static final String PASSWORD = "Password@mysql1";
	private static Connection connection = null;
	
	public synchronized static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("Connection successfully acheived");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
	}
}
