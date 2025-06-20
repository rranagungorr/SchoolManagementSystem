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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, attendance.getStudentID());
            pstmt.setInt(2, attendance.getScheduleID());
            pstmt.setString(3, attendance.getStatus());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // Retrieve the generated primary key
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
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

    public boolean saveAttendance(int studentID, int scheduleID, String status) {
        String sql = "INSERT INTO Attendance (StudentID, ScheduleID, Status) VALUES (?, ?, ?)";

        try ( Connection connection = DBUtil.getConnection();  PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, studentID);
            ps.setInt(2, scheduleID);
            ps.setString(3, status);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // READ by ID
    public Attendance getByID(int attendanceID) {
        String sql = "SELECT * FROM Attendance WHERE AttendanceID = ?";
        Attendance record = null;
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, attendanceID);
            try ( ResultSet rs = pstmt.executeQuery()) {
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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentID);
            try ( ResultSet rs = pstmt.executeQuery()) {
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

    // Güncellenen Attendance DAO:
    public boolean checkIfAttendanceExists(int studentID, int scheduleID) {
        // Veritabanında mevcut kayıt olup olmadığını kontrol eden sorgu
        String query = "SELECT COUNT(*) FROM Attendance WHERE StudentID = ? AND ScheduleID = ?";
        try ( PreparedStatement stmt = DBUtil.getConnection().prepareStatement(query)) {
            stmt.setInt(1, studentID);
            stmt.setInt(2, scheduleID);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Eğer kayıt varsa true döner
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<Attendance> getAttendanceWithDateByStudentAndCourse(int studentID, int courseID) {
    List<Attendance> attendanceList = new ArrayList<>();
    String sql = "SELECT a.Status, cs.ScheduleDate " +
                 "FROM Attendance a " +
                 "JOIN CourseSchedules cs ON a.ScheduleID = cs.ScheduleID " +
                 "WHERE a.StudentID = ? AND cs.CourseID = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, studentID);
        stmt.setInt(2, courseID);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Attendance attendance = new Attendance();
            attendance.setStatus(rs.getString("Status"));
            attendance.setScheduleDate(rs.getDate("ScheduleDate").toLocalDate());
            attendanceList.add(attendance);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return attendanceList;
}

  

}
