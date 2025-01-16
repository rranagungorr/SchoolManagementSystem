/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Semester;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SemesterDAO {
      public List<Semester> getAll() {
        List<Semester> semesters = new ArrayList<>();
        String sql = "SELECT SemesterID, SemesterName FROM Semester"; // Tablo adını düzenleyin

        try (Connection conn = DBUtil.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Semester semester = new Semester();
                semester.setSemesterID(rs.getInt("SemesterID"));
                semester.setSemesterName(rs.getString("SemesterName"));
                semesters.add(semester);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return semesters;
    }
      
      // Semesterları departman ve ClassLevel'e göre filtrele
public List<Semester> getSemestersByDepartmentAndClassLevel(int departmentID, String classLevel) {
    String sql = "SELECT DISTINCT SemesterID, SemesterName FROM Courses WHERE DepartmentID = ? AND ClassLevel = ?";
    List<Semester> semesters = new ArrayList<>();
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, departmentID);
        pstmt.setString(2, classLevel);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Semester semester = new Semester();
                semester.setSemesterID(rs.getInt("SemesterID"));
                semester.setSemesterName(rs.getString("SemesterName"));
                semesters.add(semester);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return semesters;
}

}
