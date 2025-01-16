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
        String sql = "INSERT INTO Instructors (Name, Surname, Email, Gender, Username, Password) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getSurname());
            pstmt.setString(3, instructor.getEmail());
            pstmt.setString(4, instructor.getGender());
            pstmt.setString(5, instructor.getUsername());
            pstmt.setString(6, instructor.getPassword());

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
                i.setUsername(rs.getString("Username"));
                i.setPassword(rs.getString("Password"));
                list.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ PARTIAL
    public List<Instructor> getAllTeachers() {
        List<Instructor> teacherList = new ArrayList<>();
        String sql = "SELECT InstructorID, Name, Surname FROM Instructors"; // Surname eklendi

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Instructor t = new Instructor();
                t.setInstructorID(rs.getInt("InstructorID"));
                t.setName(rs.getString("Name"));
                t.setSurname(rs.getString("Surname")); // Soyadları çekiyoruz
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
                + "SET Name = ?, Surname = ?, Email = ?, Gender = ?, Username = ?, Password = ? "
                + "WHERE InstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getSurname());
            pstmt.setString(3, instructor.getEmail());
            pstmt.setString(4, instructor.getGender());
            pstmt.setString(5, instructor.getUsername());
            pstmt.setString(6, instructor.getPassword());
            pstmt.setInt(7, instructor.getInstructorID());

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

    public String generateEmail(String firstName, String lastName) {
        String baseEmail = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@stu.fsm.edu.tr";
        String alternateEmail = firstName.toLowerCase() + "_" + lastName.toLowerCase() + "@stu.fsm.edu.tr";

        if (emailExists(baseEmail)) {
            if (!emailExists(alternateEmail)) {
                return alternateEmail;
            } else {
                int counter = 1;
                String newEmail;
                do {
                    newEmail = firstName.toLowerCase() + counter + "." + lastName.toLowerCase() + "@stu.fsm.edu.tr";
                    counter++;
                } while (emailExists(newEmail));
                return newEmail;
            }
        }
        return baseEmail;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Instructors WHERE Email = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean instructorExists(String name, String surname, String email, String username) {
        String sql = "SELECT COUNT(*) FROM Instructors WHERE Name = ? AND Surname = ? AND (Email = ? OR Username = ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, email);
            pstmt.setString(4, username);

            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Eğer kayıt varsa true döner
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM Instructors WHERE Username = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean passwordExists(String password) {
        String sql = "SELECT COUNT(*) FROM Instructors WHERE Password = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, password);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean usernameExistsForOtherID(String username, int instructorID) {
        String sql = "SELECT COUNT(*) FROM Instructors WHERE Username = ? AND InstructorID != ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, instructorID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean passwordExistsForOtherID(String password, int instructorID) {
        String sql = "SELECT COUNT(*) FROM Instructors WHERE Password = ? AND InstructorID != ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, password);
            pstmt.setInt(2, instructorID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
