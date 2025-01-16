/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.CourseSchedule;
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
public class CourseScheduleDAO {
    // CREATE
    public int create(CourseSchedule schedule) {
        String sql = "INSERT INTO CourseSchedules (CourseID, ScheduleDate) "
                   + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, schedule.getCourseID());
            pstmt.setDate(2, schedule.getScheduleDate());

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
    public CourseSchedule getByID(int scheduleID) {
        String sql = "SELECT * FROM CourseSchedules WHERE ScheduleID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, scheduleID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CourseSchedule cs = new CourseSchedule();
                    cs.setScheduleID(rs.getInt("ScheduleID"));
                    cs.setCourseID(rs.getInt("CourseID"));
                    cs.setScheduleDate(rs.getDate("ScheduleDate"));
                    
                    return cs;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<CourseSchedule> getAll() {
        List<CourseSchedule> list = new ArrayList<>();
        String sql = "SELECT * FROM CourseSchedules";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CourseSchedule cs = new CourseSchedule();
                cs.setScheduleID(rs.getInt("ScheduleID"));
                cs.setCourseID(rs.getInt("CourseID"));
                cs.setScheduleDate(rs.getDate("ScheduleDate"));
        
                list.add(cs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(CourseSchedule schedule) {
        String sql = "UPDATE CourseSchedules "
                   + "SET CourseID = ?, ScheduleDate = ?, StartTime = ?, EndTime = ? "
                   + "WHERE ScheduleID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, schedule.getCourseID());
            pstmt.setDate(2, schedule.getScheduleDate());
          

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int scheduleID) {
        String sql = "DELETE FROM CourseSchedules WHERE ScheduleID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, scheduleID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
