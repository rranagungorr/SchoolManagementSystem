/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.examsys.Grade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PC
 */
public class GradeDAO {
    // CREATE
    public int create(Grade grade) {
        String sql = "INSERT INTO Grades (StudentID, CourseID, Grade) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, grade.getStudentID());
            pstmt.setInt(2, grade.getCourseID());
            pstmt.setDouble(3, grade.getGrade());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Grade getByID(int gradeID) {
        String sql = "SELECT * FROM Grades WHERE GradeID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, gradeID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Grade g = new Grade();
                    g.setGradeID(rs.getInt("GradeID"));
                    g.setStudentID(rs.getInt("StudentID"));
                    g.setCourseID(rs.getInt("CourseID"));
                    g.setGrade(rs.getDouble("Grade"));
                    return g;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Grade> getAll() {
        List<Grade> list = new ArrayList<>();
        String sql = "SELECT * FROM Grades";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Grade g = new Grade();
                g.setGradeID(rs.getInt("GradeID"));
                g.setStudentID(rs.getInt("StudentID"));
                g.setCourseID(rs.getInt("CourseID"));
                g.setGrade(rs.getDouble("Grade"));
                list.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Grade grade) {
        String sql = "UPDATE Grades SET StudentID = ?, CourseID = ?, Grade = ? WHERE GradeID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, grade.getStudentID());
            pstmt.setInt(2, grade.getCourseID());
            pstmt.setDouble(3, grade.getGrade());
            pstmt.setInt(4, grade.getGradeID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int gradeID) {
        String sql = "DELETE FROM Grades WHERE GradeID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, gradeID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // EXTRA: get all Grades for a specific Student
    public List<Grade> getGradesByStudent(int studentID) {
        List<Grade> list = new ArrayList<>();
        String sql = "SELECT * FROM Grades WHERE StudentID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Grade g = new Grade();
                    g.setGradeID(rs.getInt("GradeID"));
                    g.setStudentID(rs.getInt("StudentID"));
                    g.setCourseID(rs.getInt("CourseID"));
                    g.setGrade(rs.getDouble("Grade"));
                    list.add(g);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
