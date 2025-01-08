/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.examsys.StudentExam;
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
public class StudentExamDAO {
    // CREATE
    public int create(StudentExam studentExam) {
        String sql = "INSERT INTO StudentExams (StudentID, ExamID) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, studentExam.getStudentID());
            pstmt.setInt(2, studentExam.getExamID());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new StudentExamID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public StudentExam getByID(int studentExamID) {
        String sql = "SELECT * FROM StudentExams WHERE StudentExamID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentExamID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    StudentExam se = new StudentExam();
                    se.setStudentExamID(rs.getInt("StudentExamID"));
                    se.setStudentID(rs.getInt("StudentID"));
                    se.setExamID(rs.getInt("ExamID"));
                    return se;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<StudentExam> getAll() {
        List<StudentExam> list = new ArrayList<>();
        String sql = "SELECT * FROM StudentExams";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                StudentExam se = new StudentExam();
                se.setStudentExamID(rs.getInt("StudentExamID"));
                se.setStudentID(rs.getInt("StudentID"));
                se.setExamID(rs.getInt("ExamID"));
                list.add(se);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    // Typically, you might not update StudentID and ExamID in a join table,
    // but let's provide a simple method for completeness.
    public boolean update(StudentExam studentExam) {
        String sql = "UPDATE StudentExams SET StudentID = ?, ExamID = ? WHERE StudentExamID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentExam.getStudentID());
            pstmt.setInt(2, studentExam.getExamID());
            pstmt.setInt(3, studentExam.getStudentExamID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int studentExamID) {
        String sql = "DELETE FROM StudentExams WHERE StudentExamID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentExamID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // EXTRA FUNCTIONALITY: Get all Exams for a specific Student
    // This can be used for "get student courses" style logic, adapted to exams.
    public List<Integer> getExamIDsByStudent(int studentID) {
        List<Integer> examIDs = new ArrayList<>();
        String sql = "SELECT ExamID FROM StudentExams WHERE StudentID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    examIDs.add(rs.getInt("ExamID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examIDs;
    }
}
