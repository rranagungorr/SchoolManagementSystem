/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditLimitDAO {
  public int getMaxCredit(int classLevel, int semesterID) {
        String sql = "SELECT MaxCredit FROM CreditLimit WHERE Class = ? AND SemesterID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, classLevel);
            pstmt.setInt(2, semesterID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MaxCredit");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Varsayılan bir değer döndür (örneğin 30), eğer veri yoksa
        return 30;
    }
}

