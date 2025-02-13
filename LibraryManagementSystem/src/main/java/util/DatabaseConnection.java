package util;

import java.sql.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnection {
	private static HikariDataSource dataSource;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loaded successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HikariConfig config=new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/lms");

		config.setUsername("root");
		config.setPassword("saro123");
		config.setMaximumPoolSize(10);
		config.setIdleTimeout(30000);
		config.setConnectionTimeout(2000);
		dataSource=new HikariDataSource(config);
	}
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}


}
