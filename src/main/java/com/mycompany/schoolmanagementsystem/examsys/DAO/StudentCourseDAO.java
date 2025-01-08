/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;
import com.mycompany.schoolmanagementsystem.examsys.StudentCourse;
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
public class StudentCourseDAO {
    // CREATE (enroll Student in Course)
    public int create(StudentCourse studentCourse) {
        String sql = "INSERT INTO StudentCourses (StudentID, CourseID) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, studentCourse.getStudentID());
            pstmt.setInt(2, studentCourse.getCourseID());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new StudentCourseID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public StudentCourse getByID(int studentCourseID) {
        String sql = "SELECT * FROM StudentCourses WHERE StudentCourseID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentCourseID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    StudentCourse sc = new StudentCourse();
                    sc.setStudentCourseID(rs.getInt("StudentCourseID"));
                    sc.setStudentID(rs.getInt("StudentID"));
                    sc.setCourseID(rs.getInt("CourseID"));
                    return sc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<StudentCourse> getAll() {
        List<StudentCourse> list = new ArrayList<>();
        String sql = "SELECT * FROM StudentCourses";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setStudentCourseID(rs.getInt("StudentCourseID"));
                sc.setStudentID(rs.getInt("StudentID"));
                sc.setCourseID(rs.getInt("CourseID"));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(StudentCourse studentCourse) {
        String sql = "UPDATE StudentCourses SET StudentID = ?, CourseID = ? WHERE StudentCourseID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentCourse.getStudentID());
            pstmt.setInt(2, studentCourse.getCourseID());
            pstmt.setInt(3, studentCourse.getStudentCourseID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int studentCourseID) {
        String sql = "DELETE FROM StudentCourses WHERE StudentCourseID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentCourseID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // EXTRA: Get all CourseIDs for a specific Student
    public List<Integer> getCourseIDsByStudent(int studentID) {
        List<Integer> courseIDs = new ArrayList<>();
        String sql = "SELECT CourseID FROM StudentCourses WHERE StudentID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    courseIDs.add(rs.getInt("CourseID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseIDs;
    }

    // EXTRA: Get all StudentIDs enrolled in a specific Course
    public List<Integer> getStudentIDsByCourse(int courseID) {
        List<Integer> studentIDs = new ArrayList<>();
        String sql = "SELECT StudentID FROM StudentCourses WHERE CourseID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    studentIDs.add(rs.getInt("StudentID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentIDs;
    }
}
