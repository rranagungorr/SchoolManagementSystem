/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Instructor;
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
public class InstructorDAO {

    // CREATE
    public int create(Instructor instructor) {
        String sql = "INSERT INTO Instructors (Name, Surname, Email, Gender, DepartmentID, Username, Password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getSurname());
            pstmt.setString(3, instructor.getEmail());
            pstmt.setString(4, instructor.getGender());
            if (instructor.getDepartmentID() != null) {
                pstmt.setInt(5, instructor.getDepartmentID());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.setString(6, instructor.getUsername());
            pstmt.setString(7, instructor.getPassword());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new InstructorID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Instructor getByID(int instructorID) {
        String sql = "SELECT * FROM Instructors WHERE InstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Instructor i = new Instructor();
                    i.setInstructorID(rs.getInt("InstructorID"));
                    i.setName(rs.getString("Name"));
                    i.setSurname(rs.getString("Surname"));
                    i.setEmail(rs.getString("Email"));
                    i.setGender(rs.getString("Gender"));
                    i.setDepartmentID((Integer) rs.getObject("DepartmentID"));
                    i.setUsername(rs.getString("Username"));
                    i.setPassword(rs.getString("Password"));
                    return i;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Instructor> getAll() {
        List<Instructor> list = new ArrayList<>();
        String sql = "SELECT * FROM Instructors";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Instructor i = new Instructor();
                i.setInstructorID(rs.getInt("InstructorID"));
                i.setName(rs.getString("Name"));
                i.setSurname(rs.getString("Surname"));
                i.setEmail(rs.getString("Email"));
                i.setGender(rs.getString("Gender"));
                i.setDepartmentID((Integer) rs.getObject("DepartmentID"));
                i.setUsername(rs.getString("Username"));
                i.setPassword(rs.getString("Password"));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Instructor> getAllTeachers() {
        List<Instructor> teacherList = new ArrayList<>();

        // 1) Tablo adını ve sütunları veritabanınızdaki gerçek isimlerle değiştirin
        String sql = "SELECT InstructorID, Name FROM Instructors";
        // Örnek tablo adı: Instructors
        // Örnek sütunlar: InstructorID, Name

        String sql = "SELECT InstructorID, Name FROM Instructors"; 
        // Adjust column names, table name to match your DB


        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Instructor t = new Instructor();

                // 2) Sütun isimlerini sorguda kullandığınızla eşleştirin
                t.setInstructorID(rs.getInt("InstructorID"));
                t.setName(rs.getString("Name"));
                // Diğer alanları da set edebilirsiniz (Surname, Email vs.)


                t.setInstructorID(rs.getInt("InstructorID"));
                t.setName(rs.getString("Name"));

                teacherList.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherList;
    }

    // UPDATE
    public boolean update(Instructor instructor) {
        String sql = "UPDATE Instructors "
                + "SET Name = ?, Surname = ?, Email = ?, Gender = ?, DepartmentID = ?, Username = ?, Password = ? "
                + "WHERE InstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getSurname());
            pstmt.setString(3, instructor.getEmail());
            pstmt.setString(4, instructor.getGender());

            if (instructor.getDepartmentID() != null) {
                pstmt.setInt(5, instructor.getDepartmentID());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            pstmt.setString(6, instructor.getUsername());
            pstmt.setString(7, instructor.getPassword());
            pstmt.setInt(8, instructor.getInstructorID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int instructorID) {
        String sql = "DELETE FROM Instructors WHERE InstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
