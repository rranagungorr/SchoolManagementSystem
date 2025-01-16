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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE
public int create(Student student) {
    String sql = "INSERT INTO Students (Name, Surname, Credits, ClassLevel, Email, DepartmentID, Username, Password, Gender, SemesterID, GeneralAverage) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getSurname());
        pstmt.setInt(3, student.getCredits() != 0 ? student.getCredits() : 0); // Varsayılan 0
        pstmt.setInt(4, student.getClassLevel());
        pstmt.setString(5, student.getEmail());
        
       
            pstmt.setInt(6, student.getDepartmentID());
      
        
        pstmt.setString(7, student.getUsername());
        pstmt.setString(8, student.getPassword());
        pstmt.setString(9, student.getGender());
        pstmt.setInt(10, student.getSemesterID());
        pstmt.setDouble(11, student.getGeneralAverage() != null ? student.getGeneralAverage() : 0.0); // Varsayılan 0.0

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Yeni StudentID
                }
            }
        }
    } catch (SQLIntegrityConstraintViolationException e) {
        // UNIQUE Constraint hatası için kullanıcı dostu mesaj
        System.err.println("UNIQUE constraint violated: " + e.getMessage());
    } catch (SQLException e) {
        // Genel veritabanı hataları için
        e.printStackTrace();
    }
    return 0; // Hata durumunda 0 döndür
}


    // READ by ID
    public Student getByID(int studentID) {
        String sql = "SELECT * FROM Students WHERE StudentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            try ( ResultSet rs = pstmt.executeQuery()) {
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
                    s.setSemesterID(rs.getInt("SemesterID"));
                    s.setGeneralAverage(rs.getDouble("GeneralAverage"));
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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

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
                s.setSemesterID(rs.getInt("SemesterID"));
                s.setGeneralAverage(rs.getDouble("GeneralAverage"));
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
                + "SET Name = ?, Surname = ?, Credits = ?, ClassLevel = ?, Email = ?, DepartmentID = ?, Username = ?, Password = ?, Gender = ?, SemesterID = ?, GeneralAverage = ? "
                + "WHERE StudentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getSurname());
            pstmt.setInt(3, student.getCredits());
            pstmt.setInt(4, student.getClassLevel());
            pstmt.setString(5, student.getEmail());
            pstmt.setInt(6, student.getDepartmentID());
            pstmt.setString(7, student.getUsername());
            pstmt.setString(8, student.getPassword());
            pstmt.setString(9, student.getGender());
            pstmt.setInt(10, student.getSemesterID());
            pstmt.setDouble(11, student.getGeneralAverage());
            pstmt.setInt(12, student.getStudentID());

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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Students WHERE Email = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Eğer e-posta adresi varsa true döner
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM Students WHERE Username = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Eğer kullanıcı adı varsa true döner
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean passwordExists(String password) {
        String sql = "SELECT COUNT(*) FROM Students WHERE Password = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, password);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Eğer şifre varsa true döner
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Student> getStudentsByDepartmentClassLevelAndSemester(int departmentID, String classLevel, int semesterID) {
    List<Student> students = new ArrayList<>();
    try (Connection conn = DBUtil.getConnection()) {
        String query = "SELECT * FROM Students " +
                       "WHERE DepartmentID = ? AND ClassLevel = ? AND SemesterID = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, departmentID);
        stmt.setString(2, classLevel);
        stmt.setInt(3, semesterID);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setStudentID(rs.getInt("StudentID"));
            student.setName(rs.getString("Name"));
            student.setSurname(rs.getString("Surname"));
            student.setDepartmentID(rs.getInt("DepartmentID"));
            students.add(student);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return students;
}


}
