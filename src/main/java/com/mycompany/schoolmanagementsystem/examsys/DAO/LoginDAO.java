package com.mycompany.schoolmanagementsystem.examsys.DAO;

/**
 *
 * @author emirs
 */
import com.mycompany.schoolmanagementsystem.management.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    /**
     * Attempts to find an Admin by username and password.
     * Returns the Admin object if found; otherwise returns null.
     */
    public Admin loginAdmin(String username, String password) {
        String sql = "SELECT * FROM Admins WHERE Username = ? AND Password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminID(rs.getInt("AdminID"));
                    admin.setName(rs.getString("Name"));
                    admin.setSurname(rs.getString("Surname"));
                    admin.setEmail(rs.getString("Email"));
                    admin.setGender(rs.getString("Gender"));
                    admin.setUsername(rs.getString("Username"));
                    admin.setPassword(rs.getString("Password"));
                    return admin;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // No match found
    }

    /**
     * Attempts to find an Instructor by username and password.
     * Returns the Instructor object if found; otherwise returns null.
     */
    public Instructor loginInstructor(String username, String password) {
        String sql = "SELECT * FROM Instructors WHERE Username = ? AND Password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Instructor instructor = new Instructor();
                    instructor.setInstructorID(rs.getInt("InstructorID"));
                    instructor.setName(rs.getString("Name"));
                    instructor.setSurname(rs.getString("Surname"));
                    instructor.setEmail(rs.getString("Email"));
                    instructor.setGender(rs.getString("Gender"));
                    instructor.setDepartmentID((Integer) rs.getObject("DepartmentID"));
                    instructor.setUsername(rs.getString("Username"));
                    instructor.setPassword(rs.getString("Password"));
                    return instructor;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // No match found
    }

    /**
     * Attempts to find a Student by username and password.
     * Returns the Student object if found; otherwise returns null.
     */
    public Student loginStudent(String username, String password) {
        String sql = "SELECT * FROM Students WHERE Username = ? AND Password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setStudentID(rs.getInt("StudentID"));
                    student.setName(rs.getString("Name"));
                    student.setSurname(rs.getString("Surname"));
                    student.setCredits(rs.getInt("Credits"));
                    student.setClassLevel(rs.getInt("ClassLevel"));
                    student.setEmail(rs.getString("Email"));
                    student.setDepartmentID((Integer) rs.getObject("DepartmentID"));
                    student.setUsername(rs.getString("Username"));
                    student.setPassword(rs.getString("Password"));
                    student.setGender(rs.getString("Gender"));
                    return student;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // No match found
    }
}