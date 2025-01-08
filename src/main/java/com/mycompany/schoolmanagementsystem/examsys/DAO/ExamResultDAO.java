/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.examsys.ExamResult;
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
public class ExamResultDAO {
    // CREATE
    public int create(ExamResult examResult) {
        String sql = "INSERT INTO ExamResults (StudentExamID, Score) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, examResult.getStudentExamID());
            pstmt.setDouble(2, examResult.getScore());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new ExamResultID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public ExamResult getByID(int examResultID) {
        String sql = "SELECT * FROM ExamResults WHERE ExamResultID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, examResultID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ExamResult er = new ExamResult();
                    er.setExamResultID(rs.getInt("ExamResultID"));
                    er.setStudentExamID(rs.getInt("StudentExamID"));
                    er.setScore(rs.getDouble("Score"));
                    return er;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<ExamResult> getAll() {
        List<ExamResult> list = new ArrayList<>();
        String sql = "SELECT * FROM ExamResults";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ExamResult er = new ExamResult();
                er.setExamResultID(rs.getInt("ExamResultID"));
                er.setStudentExamID(rs.getInt("StudentExamID"));
                er.setScore(rs.getDouble("Score"));
                list.add(er);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(ExamResult examResult) {
        String sql = "UPDATE ExamResults SET StudentExamID = ?, Score = ? WHERE ExamResultID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, examResult.getStudentExamID());
            pstmt.setDouble(2, examResult.getScore());
            pstmt.setInt(3, examResult.getExamResultID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int examResultID) {
        String sql = "DELETE FROM ExamResults WHERE ExamResultID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, examResultID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // EXTRA: Get results by StudentID (join with StudentExams)
    public List<ExamResult> getResultsByStudent(int studentID) {
        List<ExamResult> list = new ArrayList<>();
        String sql = "SELECT er.ExamResultID, er.StudentExamID, er.Score "
                   + "FROM ExamResults er "
                   + "JOIN StudentExams se ON er.StudentExamID = se.StudentExamID "
                   + "WHERE se.StudentID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ExamResult er = new ExamResult();
                    er.setExamResultID(rs.getInt("ExamResultID"));
                    er.setStudentExamID(rs.getInt("StudentExamID"));
                    er.setScore(rs.getDouble("Score"));
                    list.add(er);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
