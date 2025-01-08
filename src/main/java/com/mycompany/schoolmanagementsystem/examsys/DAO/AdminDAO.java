/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Admin;
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
public class AdminDAO {
    // CREATE
    public int create(Admin admin) {
        String sql = "INSERT INTO Admins (Name, Surname, Email, Gender, Username, Password) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getSurname());
            pstmt.setString(3, admin.getEmail());
            pstmt.setString(4, admin.getGender());
            pstmt.setString(5, admin.getUsername());
            pstmt.setString(6, admin.getPassword());

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
    public Admin getByID(int adminID) {
        String sql = "SELECT * FROM Admins WHERE AdminID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adminID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Admin a = new Admin();
                    a.setAdminID(rs.getInt("AdminID"));
                    a.setName(rs.getString("Name"));
                    a.setSurname(rs.getString("Surname"));
                    a.setEmail(rs.getString("Email"));
                    a.setGender(rs.getString("Gender"));
                    a.setUsername(rs.getString("Username"));
                    a.setPassword(rs.getString("Password"));
                    return a;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Admin> getAll() {
        List<Admin> list = new ArrayList<>();
        String sql = "SELECT * FROM Admins";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Admin a = new Admin();
                a.setAdminID(rs.getInt("AdminID"));
                a.setName(rs.getString("Name"));
                a.setSurname(rs.getString("Surname"));
                a.setEmail(rs.getString("Email"));
                a.setGender(rs.getString("Gender"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Admin admin) {
        String sql = "UPDATE Admins "
                   + "SET Name = ?, Surname = ?, Email = ?, Gender = ?, Username = ?, Password = ? "
                   + "WHERE AdminID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getSurname());
            pstmt.setString(3, admin.getEmail());
            pstmt.setString(4, admin.getGender());
            pstmt.setString(5, admin.getUsername());
            pstmt.setString(6, admin.getPassword());
            pstmt.setInt(7, admin.getAdminID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int adminID) {
        String sql = "DELETE FROM Admins WHERE AdminID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adminID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
