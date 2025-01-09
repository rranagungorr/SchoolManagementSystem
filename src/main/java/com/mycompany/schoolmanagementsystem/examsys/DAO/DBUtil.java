package com.mycompany.schoolmanagementsystem.examsys.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    

    private static final String DATABASE_NAME = "SchMng";
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
}


