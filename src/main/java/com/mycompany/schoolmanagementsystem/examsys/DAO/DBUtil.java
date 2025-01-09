package com.mycompany.schoolmanagementsystem.examsys.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
<<<<<<< Updated upstream
    private static final String DATABASE_NAME = "school_management";
    private static final String URL = "jdbc:sqlserver://RANAGUNGOR\\SQLEXPRESS:1433;"
=======
    private static final String DATABASE_NAME = "SchMng";
    private static final String URL = "jdbc:sqlserver://localhost:1433;"
>>>>>>> Stashed changes
      + "databaseName=" + DATABASE_NAME + ";"
      + "encrypt=true;"
      + "trustServerCertificate=true;";
    private static final String USER = "sa";
<<<<<<< Updated upstream
    private static final String PASSWORD = "123";
=======
    private static final String PASSWORD = "123456";
>>>>>>> Stashed changes

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


