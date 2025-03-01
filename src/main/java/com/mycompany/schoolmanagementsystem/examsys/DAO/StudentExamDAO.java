/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.examsys.StudentExam;
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
public class StudentExamDAO {

    // CREATE
    public int create(StudentExam studentExam) {
        String sql = "INSERT INTO StudentExams (StudentID, ExamID) VALUES (?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, studentExam.getStudentID());
            pstmt.setInt(2, studentExam.getExamID());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new StudentExamID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addStudentToExam(int studentID, int examID) {
        try ( Connection conn = DBUtil.getConnection()) {
            String query = "INSERT INTO StudentExams (StudentID, ExamID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentID);
            stmt.setInt(2, examID);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Başarıyla eklendi
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Başarısız
    }

    // READ by ID
    public StudentExam getByID(int studentExamID) {
        String sql = "SELECT * FROM StudentExams WHERE StudentExamID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentExamID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    StudentExam se = new StudentExam();
                    se.setStudentExamID(rs.getInt("StudentExamID"));
                    se.setStudentID(rs.getInt("StudentID"));
                    se.setExamID(rs.getInt("ExamID"));
                    return se;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<StudentExam> getAll() {
        List<StudentExam> list = new ArrayList<>();
        String sql = "SELECT * FROM StudentExams";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                StudentExam se = new StudentExam();
                se.setStudentExamID(rs.getInt("StudentExamID"));
                se.setStudentID(rs.getInt("StudentID"));
                se.setExamID(rs.getInt("ExamID"));
                list.add(se);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    // Typically, you might not update StudentID and ExamID in a join table,
    // but let's provide a simple method for completeness.
    public boolean update(StudentExam studentExam) {
        String sql = "UPDATE StudentExams SET StudentID = ?, ExamID = ? WHERE StudentExamID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentExam.getStudentID());
            pstmt.setInt(2, studentExam.getExamID());
            pstmt.setInt(3, studentExam.getStudentExamID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int studentExamID) {
        String sql = "DELETE FROM StudentExams WHERE StudentExamID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentExamID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteStudentExam(int studentID, int examID) {
        String query = "DELETE FROM StudentExams WHERE StudentID = ? AND ExamID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentID);
            stmt.setInt(2, examID);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // EXTRA FUNCTIONALITY: Get all Exams for a specific Student
    // This can be used for "get student courses" style logic, adapted to exams.
    public List<Integer> getExamIDsByStudent(int studentID) {
        List<Integer> examIDs = new ArrayList<>();
        String sql = "SELECT ExamID FROM StudentExams WHERE StudentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    examIDs.add(rs.getInt("ExamID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examIDs;
    }

    // StudentExamDAO.java (example method)
    public List<Exam> getExamsByStudent(int studentID) {
        List<Exam> examList = new ArrayList<>();
        String sql = "SELECT e.ExamID, e.ExamName, e.ExamDate "
                + "FROM StudentExams se "
                + "JOIN Exams e ON se.ExamID = e.ExamID "
                + "JOIN Courses c ON e.CourseID = c.CourseID "
                + "WHERE se.StudentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Exam exam = new Exam();
                    exam.setExamID(rs.getInt("ExamID"));
                    exam.setExamName(rs.getString("ExamName"));
                    exam.setExamDate(rs.getDate("ExamDate"));
                    // If you stored CourseName inside your Exam model, or have an extended model:
                    examList.add(exam);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examList;
    }

    public boolean isStudentEnrolledInExam(int studentID, int examID) {
        try ( Connection conn = DBUtil.getConnection()) {
            String query = "SELECT COUNT(*) FROM StudentExams WHERE StudentID = ? AND ExamID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentID);
            stmt.setInt(2, examID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Öğrenci zaten kayıtlı
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<Student> getStudentsByCourseID(int courseID) {
    List<Student> students = new ArrayList<>();
    String query = "SELECT s.StudentID, s.Name, s.Surname " +
                   "FROM StudentExams se " +
                   "JOIN Student s ON se.StudentID = s.StudentID " +
                   "WHERE se.ExamID IN (SELECT ExamID FROM Exam WHERE CourseID = ?)";

    try (Connection conn = DBUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, courseID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Student student = new Student();
            student.setStudentID(rs.getInt("StudentID"));
            student.setName(rs.getString("Name"));
            student.setSurname(rs.getString("Surname"));
            students.add(student);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return students;
}
    
    public int getExamIDByCourseAndStudent(int courseID, int studentID) {
    String query = "SELECT se.ExamID " +
                   "FROM StudentExams se " +
                   "JOIN Exams e ON se.ExamID = e.ExamID " +
                   "WHERE e.CourseID = ? AND se.StudentID = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, courseID);
        ps.setInt(2, studentID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("ExamID");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Eğer sınav bulunamazsa -1 döner.
}
    
    
public List<Student> getStudentsByExamID(int examID) {
    List<Student> students = new ArrayList<>();
    String sql = "SELECT s.StudentID, s.Name, s.Surname " +
                 "FROM StudentExams se " +
                 "JOIN Students s ON se.StudentID = s.StudentID " +
                 "WHERE se.ExamID = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, examID);
        System.out.println("Query: " + sql);
        System.out.println("ExamID: " + examID);
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println("Found Student: " + rs.getInt("StudentID"));
            Student student = new Student();
            student.setStudentID(rs.getInt("StudentID"));
            student.setName(rs.getString("Name"));
            student.setSurname(rs.getString("Surname"));
            students.add(student);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println("Total students found: " + students.size());
    return students;
}



}
