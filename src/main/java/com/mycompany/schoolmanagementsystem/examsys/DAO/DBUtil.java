package com.mycompany.schoolmanagementsystem.examsys.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    private static final String DATABASE_NAME = "";
    private static final String URL = "jdbc:sqlserver://RANAGUNGOR\\SQLEXPRESS:1433;"
      + "databaseName=" + DATABASE_NAME + ";"
      + "encrypt=true;"
      + "trustServerCertificate=true;"
      + "integratedSecurity=true;";;
    private static final String USER = "sa";
    private static final String PASSWORD = "";

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


