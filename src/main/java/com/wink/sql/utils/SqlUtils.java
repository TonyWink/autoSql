package com.wink.sql.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlUtils {
	
	private static Connection con;

	//source
	private static String url;
	private static String driverClass;
	private static String username;
	private static String password;
	
	static{
		Properties prop=new Properties();
		try {
			prop.load(ClassLoader.getSystemResourceAsStream("application.properties"));
		    url=prop.getProperty("server.jdbc.url");
		    driverClass=prop.getProperty("server.jdbc.driverClass");
		    username=prop.getProperty("server.jdbc.username");
		    password=prop.getProperty("server.jdbc.password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
	try {
		  if(con==null){
				Class.forName(driverClass);
				con=DriverManager.getConnection(url, username, password);
		}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return con;
	}
	
}
