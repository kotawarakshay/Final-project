package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	private static Connection connection;

	private static DBConnection instance = null;
	public static Connection getConnection() throws SQLException, IOException {
		Properties properties=new Properties();
		FileInputStream fis=new FileInputStream("resources/jdbc.properties");
		properties.load(fis);
		
		
		String url=properties.getProperty("url");
		String driver=properties.getProperty("driver");
		String user=properties.getProperty("username");
		String password=properties.getProperty("password");
		
		try {
				Class.forName(driver);
				try {
					connection=DriverManager.getConnection(url,user,password);
					//System.out.println("connected");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return connection;
	}
	

}
