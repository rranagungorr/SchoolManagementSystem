/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.examsys.InstructorDepartment;
import com.mycompany.schoolmanagementsystem.management.Department;
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
public class InstructorDepartmentDAO {

    public void create(int instructorID, int departmentID) {
        String sql = "INSERT INTO InstructorDepartments (InstructorID, DepartmentID) VALUES (?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            pstmt.setInt(2, departmentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<InstructorDepartment> getAll() {
        List<InstructorDepartment> list = new ArrayList<>();
        String sql = "SELECT * FROM InstructorDepartments";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new InstructorDepartment(rs.getInt("InstructorID"), rs.getInt("DepartmentID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(int instructorID, int departmentID) {
        String sql = "DELETE FROM InstructorDepartments WHERE InstructorID = ? AND DepartmentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            pstmt.setInt(2, departmentID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Department> getDepartmentsByInstructor(int instructorID) {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT d.DepartmentID, d.DepartmentName "
                + "FROM InstructorDepartments id "
                + "JOIN Departments d ON id.DepartmentID = d.DepartmentID "
                + "WHERE id.InstructorID = ?";

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Department department = new Department();
                    department.setDepartmentID(rs.getInt("DepartmentID"));
                    department.setDepartmentName(rs.getString("DepartmentName"));
                    departments.add(department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public boolean assignDepartmentToInstructor(int instructorID, int departmentID) {
        String sql = "INSERT INTO InstructorDepartments (InstructorID, DepartmentID) VALUES (?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            pstmt.setInt(2, departmentID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean canAssignMoreDepartments(int instructorID) {
        String sql = "SELECT COUNT(*) AS departmentCount FROM InstructorDepartments WHERE InstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int departmentCount = rs.getInt("departmentCount");
                    return departmentCount < 3; // Sadece 3'ten azsa true döner
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Hata durumunda departman eklenmez
    }

    public boolean isDepartmentAlreadyAssigned(int instructorID, int departmentID) {
        String sql = "SELECT COUNT(*) FROM InstructorDepartments WHERE InstructorID = ? AND DepartmentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            pstmt.setInt(2, departmentID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Eğer sonuç 1'den büyükse, departman zaten atanmış
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteByInstructorID(int instructorID) {
        String sql = "DELETE FROM InstructorDepartments WHERE InstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Eğer ilişkiler varsa ve silindiyse true döner
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
