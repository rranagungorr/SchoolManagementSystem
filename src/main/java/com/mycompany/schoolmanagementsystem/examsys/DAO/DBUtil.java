package com.mycompany.schoolmanagementsystem.examsys.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // Adjust your database name
    private static final String DATABASE_NAME = "schoolmanagement";

    // For Windows Authentication with integrated security:
    //   - Server:   RANAGUNGOR\SQLEXPRESS
    //   - Port:     1433 (default SQL Server port)
    //   - Using encryption & trusting the certificate
    //   - integratedSecurity=true
    private static final String URL = 
        "jdbc:sqlserver://RANAGUNGOR\\SQLEXPRESS:1433;"
      + "databaseName=" + DATABASE_NAME + ";"
      + "encrypt=true;"
      + "trustServerCertificate=true;"
      + "integratedSecurity=true;";

    // If using integrated security, you typically don't set user/password 
    // for Windows Authentication.
    // If you want SQL Auth, you would do something like:
    // "jdbc:sqlserver://RANAGUNGOR\\SQLEXPRESS:1433;databaseName=YourDB;user=sa;password=12345;"

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}


