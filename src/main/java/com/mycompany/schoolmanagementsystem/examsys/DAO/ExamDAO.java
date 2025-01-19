/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.examsys.Exam;
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
public class ExamDAO {

    // CREATE
    public int create(Exam exam) {
        String sql = "INSERT INTO Exams (ExamName, ExamDate, CourseID, InvigilatorID, ClassroomID) "
                + "VALUES (?, ?, ?, ?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, exam.getExamName());
            pstmt.setDate(2, exam.getExamDate());
            pstmt.setInt(3, exam.getCourseID());

            // InvigilatorID can be null
            if (exam.getInvigilatorID() != null) {
                pstmt.setInt(4, exam.getInvigilatorID());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            // ClassroomID can be null
            if (exam.getClassroomID() != null) {
                pstmt.setInt(5, exam.getClassroomID());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // new ExamID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int createExam(Exam exam) {
        String sql = "INSERT INTO Exams (course_id, invigilator_id, classroom_id, exam_name, exam_date) "
                + "VALUES (?, ?, ?, ?, ?)";
        // Adjust column names to match your DB (some might be instructor_id, etc.)
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, exam.getCourseID());
            pstmt.setInt(2, exam.getInvigilatorID());
            pstmt.setInt(3, exam.getClassroomID());
            pstmt.setString(4, exam.getExamName());
            pstmt.setDate(5, exam.getExamDate()); // java.sql.Date

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // return generated exam_id
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Exam getByID(int examID) {
        String sql = "SELECT * FROM Exams WHERE ExamID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, examID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Exam e = new Exam();
                    e.setExamID(rs.getInt("ExamID"));
                    e.setExamName(rs.getString("ExamName"));
                    e.setExamDate(rs.getDate("ExamDate"));
                    e.setCourseID(rs.getInt("CourseID"));
                    e.setInvigilatorID((Integer) rs.getObject("InvigilatorID"));
                    e.setClassroomID((Integer) rs.getObject("ClassroomID"));
                    return e;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Exam> getAll() {
        List<Exam> list = new ArrayList<>();
        String sql = "SELECT * FROM Exams";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Exam e = new Exam();
                e.setExamID(rs.getInt("ExamID"));
                e.setExamName(rs.getString("ExamName"));
                e.setExamDate(rs.getDate("ExamDate"));
                e.setCourseID(rs.getInt("CourseID"));
                e.setInvigilatorID((Integer) rs.getObject("InvigilatorID"));
                e.setClassroomID((Integer) rs.getObject("ClassroomID"));
                list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public boolean update(Exam exam) {
        String sql = "UPDATE Exams "
                + "SET ExamName = ?, ExamDate = ?, CourseID = ?, InvigilatorID = ?, ClassroomID = ? "
                + "WHERE ExamID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, exam.getExamName());
            pstmt.setDate(2, exam.getExamDate());
            pstmt.setInt(3, exam.getCourseID());

            if (exam.getInvigilatorID() != null) {
                pstmt.setInt(4, exam.getInvigilatorID());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            if (exam.getClassroomID() != null) {
                pstmt.setInt(5, exam.getClassroomID());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            pstmt.setInt(6, exam.getExamID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int examID) {
        String sql = "DELETE FROM Exams WHERE ExamID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, examID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Exam> getExamsByCourse(int courseID) {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM Exams WHERE CourseID = ?";

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);

            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Exam exam = new Exam();
                    exam.setExamID(rs.getInt("ExamID"));
                    exam.setExamName(rs.getString("ExamName"));
                    exam.setExamDate(rs.getDate("ExamDate"));
                    exam.setCourseID(rs.getInt("CourseID"));
                    exams.add(exam);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exams;
    }

    public List<Exam> getExamsByDepartmentClassLevelAndSemester(int departmentID, String classLevel, int semesterID) {
        List<Exam> exams = new ArrayList<>();
        try ( Connection conn = DBUtil.getConnection()) {
            String query = "SELECT * FROM Exams e "
                    + "JOIN Courses c ON e.CourseID = c.CourseID "
                    + "WHERE c.DepartmentID = ? AND c.ClassLevel = ? AND c.SemesterID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, departmentID);
            stmt.setString(2, classLevel);
            stmt.setInt(3, semesterID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setExamID(rs.getInt("ExamID"));
                exam.setExamName(rs.getString("ExamName"));
                exam.setExamDate(rs.getDate("ExamDate"));
                exam.setCourseID(rs.getInt("CourseID"));
                exam.setInvigilatorID(rs.getInt("InvigilatorID"));
                exam.setClassroomID(rs.getInt("ClassroomID"));
                exams.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }

    public boolean isExamExists(int courseID, java.util.Date examDate, int classroomID) {
        String query = "SELECT COUNT(*) FROM Exams WHERE courseID = ? AND examDate = ? AND classroomID = ?";
        try ( Connection connection = DBUtil.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, courseID);
            preparedStatement.setDate(2, new java.sql.Date(examDate.getTime()));
            preparedStatement.setInt(3, classroomID);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Eğer kayıt varsa true döndür
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Hiçbir kayıt yoksa false döndür
    }
    
    public List<Exam> getExamsByCourseID(int courseID) {
    List<Exam> exams = new ArrayList<>();
    String sql = "SELECT ExamID, ExamName, ExamDate FROM Exams WHERE CourseID = ?";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, courseID);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Exam exam = new Exam();
            exam.setExamID(rs.getInt("ExamID"));
            exam.setExamName(rs.getString("ExamName"));
            exam.setExamDate(rs.getDate("ExamDate"));
            exams.add(exam);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return exams;
}


}
