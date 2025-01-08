/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Student;
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
public class StudentDAO {
    // CREATE
    public int create(Student student) {
        String sql = "INSERT INTO Students (Name, Surname, Credits, ClassLevel, Email, DepartmentID, Username, Password, Gender) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSurname());
            pstmt.setInt(3, student.getCredits());
            pstmt.setInt(4, student.getClassLevel());
            pstmt.setString(5, student.getEmail());

            if (student.getDepartmentID() != null) {
                pstmt.setInt(6, student.getDepartmentID());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.setString(7, student.getUsername());
            pstmt.setString(8, student.getPassword());
            pstmt.setString(9, student.getGender());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new StudentID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Student getByID(int studentID) {
        String sql = "SELECT * FROM Students WHERE StudentID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student();
                    s.setStudentID(rs.getInt("StudentID"));
                    s.setName(rs.getString("Name"));
                    s.setSurname(rs.getString("Surname"));
                    s.setCredits(rs.getInt("Credits"));
                    s.setClassLevel(rs.getInt("ClassLevel"));
                    s.setEmail(rs.getString("Email"));
                    s.setDepartmentID((Integer) rs.getObject("DepartmentID"));
                    s.setUsername(rs.getString("Username"));
                    s.setPassword(rs.getString("Password"));
                    s.setGender(rs.getString("Gender"));
                    return s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setStudentID(rs.getInt("StudentID"));
                s.setName(rs.getString("Name"));
                s.setSurname(rs.getString("Surname"));
                s.setCredits(rs.getInt("Credits"));
                s.setClassLevel(rs.getInt("ClassLevel"));
                s.setEmail(rs.getString("Email"));
                s.setDepartmentID((Integer) rs.getObject("DepartmentID"));
                s.setUsername(rs.getString("Username"));
                s.setPassword(rs.getString("Password"));
                s.setGender(rs.getString("Gender"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Student student) {
        String sql = "UPDATE Students "
                   + "SET Name = ?, Surname = ?, Credits = ?, ClassLevel = ?, Email = ?, DepartmentID = ?, Username = ?, Password = ?, Gender = ? "
                   + "WHERE StudentID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSurname());
            pstmt.setInt(3, student.getCredits());
            pstmt.setInt(4, student.getClassLevel());
            pstmt.setString(5, student.getEmail());

            if (student.getDepartmentID() != null) {
                pstmt.setInt(6, student.getDepartmentID());
            } else {
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.setString(7, student.getUsername());
            pstmt.setString(8, student.getPassword());
            pstmt.setString(9, student.getGender());
            pstmt.setInt(10, student.getStudentID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int studentID) {
        String sql = "DELETE FROM Students WHERE StudentID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
