package br.com.csouza.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLTestFactory {
    private static Connection conn;

    private PostgreSQLTestFactory() {}

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = initConnection();
        } else if (conn.isClosed()) {
            conn = initConnection();
        }

        return conn;
    }

    private static Connection initConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:15432/db_mod30_test",
                "postgres",
                "admin"
        );
    }
}
