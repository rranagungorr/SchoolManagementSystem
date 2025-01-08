/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Field;
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
public class FieldDAO {
    // CREATE
    public int create(Field field) {
        String sql = "INSERT INTO Fields (FieldName, DepartmentID) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, field.getFieldName());
            pstmt.setInt(2, field.getDepartmentID());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // newly generated FieldID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Field getByID(int fieldID) {
        String sql = "SELECT FieldID, FieldName, DepartmentID FROM Fields WHERE FieldID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, fieldID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Field f = new Field();
                    f.setFieldID(rs.getInt("FieldID"));
                    f.setFieldName(rs.getString("FieldName"));
                    f.setDepartmentID(rs.getInt("DepartmentID"));
                    return f;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Field> getAll() {
        List<Field> list = new ArrayList<>();
        String sql = "SELECT FieldID, FieldName, DepartmentID FROM Fields";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Field f = new Field();
                f.setFieldID(rs.getInt("FieldID"));
                f.setFieldName(rs.getString("FieldName"));
                f.setDepartmentID(rs.getInt("DepartmentID"));
                list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Field field) {
        String sql = "UPDATE Fields SET FieldName = ?, DepartmentID = ? WHERE FieldID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, field.getFieldName());
            pstmt.setInt(2, field.getDepartmentID());
            pstmt.setInt(3, field.getFieldID());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int fieldID) {
        String sql = "DELETE FROM Fields WHERE FieldID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, fieldID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
