/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.examsys.CourseInstructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Merve
 */
public class CourseInstructorDAO {

    public void create(int instructorID, int courseID) {
        String sql = "INSERT INTO CourseInstructors (InstructorID, CourseID) VALUES (?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            pstmt.setInt(2, courseID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CourseInstructor> getAll() {
        List<CourseInstructor> list = new ArrayList<>();
        String sql = "SELECT * FROM CourseInstructors";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new CourseInstructor(
                        rs.getInt("CourseInstructorID"),
                        rs.getInt("InstructorID"),
                        rs.getInt("CourseID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> getCoursesByInstructor(int instructorID) {
        List<Integer> courseIDs = new ArrayList<>();
        String sql = "SELECT CourseID FROM CourseInstructors WHERE InstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    courseIDs.add(rs.getInt("CourseID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseIDs;
    }

    public List<Integer> getInstructorsByCourse(int courseID) {
        List<Integer> instructorIDs = new ArrayList<>();
        String sql = "SELECT InstructorID FROM CourseInstructors WHERE CourseID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    instructorIDs.add(rs.getInt("InstructorID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructorIDs;
    }

    public void delete(int courseInstructorID) {
        String sql = "DELETE FROM CourseInstructors WHERE CourseInstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseInstructorID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
