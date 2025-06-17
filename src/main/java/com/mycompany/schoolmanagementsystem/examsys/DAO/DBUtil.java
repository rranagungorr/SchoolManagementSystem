package com.mycompany.schoolmanagementsystem.examsys.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {

    private static final String DATABASE_NAME = "SchManagement";
    private static final String URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=" + DATABASE_NAME + ";"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(Connection connection, PreparedStatement... statements) {
        // Tüm PreparedStatement nesnelerini kapat
        if (statements != null) {
            for (PreparedStatement ps : statements) {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Bağlantıyı kapat
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
