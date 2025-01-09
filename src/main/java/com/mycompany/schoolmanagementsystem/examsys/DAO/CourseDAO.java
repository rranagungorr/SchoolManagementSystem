/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Course;
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
public class CourseDAO {

    // CREATE
    public int create(Course course) {
        String sql = "INSERT INTO Courses (CourseName, CourseCode, Credits, FieldID, InstructorID) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getCourseCode());
            pstmt.setInt(3, course.getCredits());
            pstmt.setInt(4, course.getFieldID());

            if (course.getInstructorID() != null) {
                pstmt.setInt(5, course.getInstructorID());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new CourseID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Course getByID(int courseID) {
        String sql = "SELECT * FROM Courses WHERE CourseID = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Course c = new Course();
                    c.setCourseID(rs.getInt("CourseID"));
                    c.setCourseName(rs.getString("CourseName"));
                    c.setCourseCode(rs.getString("CourseCode"));
                    c.setCredits(rs.getInt("Credits"));
                    c.setFieldID(rs.getInt("FieldID"));
                    c.setInstructorID((Integer) rs.getObject("InstructorID"));
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Course> getAll() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("CourseID"));
                c.setCourseName(rs.getString("CourseName"));
                c.setCourseCode(rs.getString("CourseCode"));
                c.setCredits(rs.getInt("Credits"));
                c.setFieldID(rs.getInt("FieldID"));
                c.setInstructorID((Integer) rs.getObject("InstructorID"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT course_id, course_name FROM Courses";
        // Adjust to match your schema/columns

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("course_id"));
                c.setCourseName(rs.getString("course_name"));
                courseList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    // UPDATE
    public boolean update(Course course) {
        String sql = "UPDATE Courses "
                + "SET CourseName = ?, CourseCode = ?, Credits = ?, FieldID = ?, InstructorID = ? "
                + "WHERE CourseID = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getCourseCode());
            pstmt.setInt(3, course.getCredits());
            pstmt.setInt(4, course.getFieldID());

            if (course.getInstructorID() != null) {
                pstmt.setInt(5, course.getInstructorID());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            pstmt.setInt(6, course.getCourseID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int courseID) {
        String sql = "DELETE FROM Courses WHERE CourseID = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Course> getAllByDepartment(int departmentID) {
        List<Course> courses = new ArrayList<>();
        // We assume the link between Courses and Departments is via the FieldID => DepartmentID
        // So we might need a join or subquery, depending on your schema

        // Option 1 (JOIN on Fields and Departments):
        // SELECT c.* 
        // FROM Courses c
        // JOIN Fields f ON c.FieldID = f.FieldID
        // WHERE f.DepartmentID = ?
        String sql = "SELECT c.* "
                + "FROM Courses c "
                + "JOIN Fields f ON c.FieldID = f.FieldID "
                + "WHERE f.DepartmentID = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Course c = new Course();
                    c.setCourseID(rs.getInt("CourseID"));
                    c.setCourseName(rs.getString("CourseName"));
                    c.setCourseCode(rs.getString("CourseCode"));
                    c.setCredits(rs.getInt("Credits"));
                    c.setFieldID(rs.getInt("FieldID"));
                    c.setInstructorID((Integer) rs.getObject("InstructorID"));
                    courses.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getCoursesByInstructor(int instructorID) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses WHERE InstructorID = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Course c = new Course();
                    c.setCourseID(rs.getInt("CourseID"));
                    c.setCourseName(rs.getString("CourseName"));
                    c.setCourseCode(rs.getString("CourseCode"));
                    c.setCredits(rs.getInt("Credits"));
                    c.setFieldID(rs.getInt("FieldID"));
                    c.setInstructorID((Integer) rs.getObject("InstructorID"));
                    courses.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
