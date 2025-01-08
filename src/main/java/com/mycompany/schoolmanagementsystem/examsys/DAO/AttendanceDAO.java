/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Attendance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class AttendanceDAO {
    // CREATE (mark attendance or add a new attendance record)
    public int create(Attendance attendance) {
        String sql = "INSERT INTO Attendance (StudentID, ScheduleID, Status) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, attendance.getStudentID());
            pstmt.setInt(2, attendance.getScheduleID());
            pstmt.setString(3, attendance.getStatus());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // Retrieve the generated primary key
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // AttendanceID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;  // 0 indicates failure or no ID generated
    }

    // READ by ID
    public Attendance getByID(int attendanceID) {
        String sql = "SELECT * FROM Attendance WHERE AttendanceID = ?";
        Attendance record = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, attendanceID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    record = new Attendance();
                    record.setAttendanceID(rs.getInt("AttendanceID"));
                    record.setStudentID(rs.getInt("StudentID"));
                    record.setScheduleID(rs.getInt("ScheduleID"));
                    record.setStatus(rs.getString("Status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }

    // READ ALL
    public List<Attendance> getAll() {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM Attendance";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Attendance a = new Attendance();
                a.setAttendanceID(rs.getInt("AttendanceID"));
                a.setStudentID(rs.getInt("StudentID"));
                a.setScheduleID(rs.getInt("ScheduleID"));
                a.setStatus(rs.getString("Status"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Attendance attendance) {
        String sql = "UPDATE Attendance SET StudentID = ?, ScheduleID = ?, Status = ? WHERE AttendanceID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, attendance.getStudentID());
            pstmt.setInt(2, attendance.getScheduleID());
            pstmt.setString(3, attendance.getStatus());
            pstmt.setInt(4, attendance.getAttendanceID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;  // true if update succeeded
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int attendanceID) {
        String sql = "DELETE FROM Attendance WHERE AttendanceID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, attendanceID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;  // true if delete succeeded
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Example Extra Method: Get Attendance for a Specific Student
    public List<Attendance> getAttendanceByStudent(int studentID) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM Attendance WHERE StudentID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Attendance a = new Attendance();
                    a.setAttendanceID(rs.getInt("AttendanceID"));
                    a.setStudentID(rs.getInt("StudentID"));
                    a.setScheduleID(rs.getInt("ScheduleID"));
                    a.setStatus(rs.getString("Status"));
                    list.add(a);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
