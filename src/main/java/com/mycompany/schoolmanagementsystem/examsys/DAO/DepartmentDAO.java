/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class DepartmentDAO {

    // CREATE
    public int create(Department department) {
        String sql = "INSERT INTO Departments (DepartmentName) VALUES (?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, department.getDepartmentName());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // return generated ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Department getByID(int departmentID) {
        String sql = "SELECT DepartmentID, DepartmentName FROM Departments WHERE DepartmentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Department d = new Department();
                    d.setDepartmentID(rs.getInt("DepartmentID"));
                    d.setDepartmentName(rs.getString("DepartmentName"));
                    return d;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT DepartmentID, DepartmentName FROM Departments";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Department d = new Department();
                d.setDepartmentID(rs.getInt("DepartmentID"));
                d.setDepartmentName(rs.getString("DepartmentName"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Department department) {
        String sql = "UPDATE Departments SET DepartmentName = ? WHERE DepartmentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, department.getDepartmentName());
            pstmt.setInt(2, department.getDepartmentID());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int departmentID) {
        String sql = "DELETE FROM Departments WHERE DepartmentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Department> getDepartmentsByInstructorID(int instructorID) {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT d.DepartmentID, d.DepartmentName FROM Departments d "
                + "INNER JOIN InstructorDepartments id ON d.DepartmentID = id.DepartmentID "
                + "WHERE id.InstructorID = ?";

        try ( Connection connection = DBUtil.getConnection();  PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, instructorID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentID(rs.getInt("DepartmentID"));
                department.setDepartmentName(rs.getString("DepartmentName"));
                departments.add(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

}
