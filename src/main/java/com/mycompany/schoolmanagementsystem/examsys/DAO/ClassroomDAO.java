/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Classroom;
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
public class ClassroomDAO {
    // CREATE
    public int create(Classroom classroom) {
        String sql = "INSERT INTO Classrooms (ClassroomName, Capacity) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, classroom.getClassroomName());
            pstmt.setInt(2, classroom.getCapacity());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new ClassroomID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Classroom getByID(int classroomID) {
        String sql = "SELECT * FROM Classrooms WHERE ClassroomID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, classroomID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Classroom c = new Classroom();
                    c.setClassroomID(rs.getInt("ClassroomID"));
                    c.setClassroomName(rs.getString("ClassroomName"));
                    c.setCapacity(rs.getInt("Capacity"));
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Classroom> getAll() {
        List<Classroom> list = new ArrayList<>();
        String sql = "SELECT * FROM Classrooms";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Classroom c = new Classroom();
                c.setClassroomID(rs.getInt("ClassroomID"));
                c.setClassroomName(rs.getString("ClassroomName"));
                c.setCapacity(rs.getInt("Capacity"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Classroom classroom) {
        String sql = "UPDATE Classrooms SET ClassroomName = ?, Capacity = ? WHERE ClassroomID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, classroom.getClassroomName());
            pstmt.setInt(2, classroom.getCapacity());
            pstmt.setInt(3, classroom.getClassroomID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int classroomID) {
        String sql = "DELETE FROM Classrooms WHERE ClassroomID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, classroomID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
