package br.com.csouza.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection connection;
	
	private ConnectionFactory() {}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = initConnection();
		} else if (connection.isClosed()) {
			connection = initConnection();
		}
		
		return connection;
	}
	
	private static Connection initConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:postgresql://localhost:15432/db_mod29",
				"postgres",
				"admin"
				);			
	}
}
