package com.mycompany.schoolmanagementsystem.examsys.DAO;

import com.mycompany.schoolmanagementsystem.management.Course;
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
public class CourseDAO {

    public int create(Course course) {
        String sql = "INSERT INTO Courses (CourseName, CourseCode, Credits, DepartmentID, InstructorID, "
                + "ClassroomID, SemesterID, StartTime, EndTime, WeekDay, Hours, ClassLevel) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getCourseCode());
            pstmt.setInt(3, course.getCredits());
            pstmt.setInt(4, course.getDepartmentID());
            if (course.getInstructorID() != null) {
                pstmt.setInt(5, course.getInstructorID());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.setInt(6, course.getClassroomID());
            pstmt.setInt(7, course.getSemesterID());
            pstmt.setString(8, course.getStartTime());
            pstmt.setString(9, course.getEndTime());
            pstmt.setString(10, course.getWeekDay());
            pstmt.setInt(11, course.getHours());
            pstmt.setInt(12, course.getClassLevel()); // Yeni ekleme

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try ( ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Yeni CourseID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // READ by ID
    public Course getByID(int courseID) {
        String sql = "SELECT * FROM Courses WHERE CourseID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Course c = new Course();
                    c.setCourseID(rs.getInt("CourseID"));
                    c.setCourseName(rs.getString("CourseName"));
                    c.setCourseCode(rs.getString("CourseCode"));
                    c.setCredits(rs.getInt("Credits"));
                    c.setDepartmentID(rs.getInt("DepartmentID"));
                    c.setInstructorID((Integer) rs.getObject("InstructorID"));
                    c.setClassroomID(rs.getInt("ClassroomID"));
                    c.setSemesterID(rs.getInt("SemesterID"));
                    c.setStartTime(rs.getString("StartTime"));
                    c.setEndTime(rs.getString("EndTime"));
                    c.setWeekDay(rs.getString("WeekDay"));
                    c.setHours(rs.getInt("Hours"));
                    c.setClassLevel(rs.getInt("ClassLevel")); // Yeni ekleme
                    return c;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ ALL
    public List<Course> getAll() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("CourseID"));
                c.setCourseName(rs.getString("CourseName"));
                c.setCourseCode(rs.getString("CourseCode"));
                c.setCredits(rs.getInt("Credits"));
                c.setDepartmentID(rs.getInt("DepartmentID"));
                c.setInstructorID((Integer) rs.getObject("InstructorID"));
                c.setClassroomID(rs.getInt("ClassroomID"));
                c.setSemesterID(rs.getInt("SemesterID"));
                c.setStartTime(rs.getString("StartTime"));
                c.setEndTime(rs.getString("EndTime"));
                c.setWeekDay(rs.getString("WeekDay"));
                c.setHours(rs.getInt("Hours"));
                c.setClassLevel(rs.getInt("ClassLevel"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT CourseID, CourseName FROM Courses";

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql);  ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Course c = new Course();
                c.setCourseID(rs.getInt("CourseID"));
                c.setCourseName(rs.getString("CourseName"));
                courseList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public boolean update(Course course) {
        String sql = "UPDATE Courses SET CourseName = ?, CourseCode = ?, Credits = ?, DepartmentID = ?, "
                + "InstructorID = ?, ClassroomID = ?, SemesterID = ?, StartTime = ?, EndTime = ?, WeekDay = ?, Hours = ?, ClassLevel = ? "
                + "WHERE CourseID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getCourseCode());
            pstmt.setInt(3, course.getCredits());
            pstmt.setInt(4, course.getDepartmentID());
            if (course.getInstructorID() != null) {
                pstmt.setInt(5, course.getInstructorID());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.setInt(6, course.getClassroomID());
            pstmt.setInt(7, course.getSemesterID());
            pstmt.setString(8, course.getStartTime());
            pstmt.setString(9, course.getEndTime());
            pstmt.setString(10, course.getWeekDay());
            pstmt.setInt(11, course.getHours());
            pstmt.setInt(12, course.getClassLevel()); // Yeni ekleme
            pstmt.setInt(13, course.getCourseID());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // DELETE
    public boolean delete(int courseID) {
        String sql = "DELETE FROM Courses WHERE CourseID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Course> getAllByDepartment(int departmentID) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses WHERE DepartmentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentID);
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Course c = new Course();
                    c.setCourseID(rs.getInt("CourseID"));
                    c.setCourseName(rs.getString("CourseName"));
                    c.setCourseCode(rs.getString("CourseCode"));
                    c.setCredits(rs.getInt("Credits"));
                    c.setDepartmentID(rs.getInt("DepartmentID"));
                    c.setInstructorID((Integer) rs.getObject("InstructorID"));
                    c.setClassroomID(rs.getInt("ClassroomID"));
                    c.setSemesterID(rs.getInt("SemesterID"));
                    c.setStartTime(rs.getString("StartTime"));
                    c.setEndTime(rs.getString("EndTime"));
                    c.setWeekDay(rs.getString("WeekDay"));
                    c.setHours(rs.getInt("Hours"));
                    c.setClassLevel(rs.getInt("ClassLevel"));
                    courses.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getCoursesByInstructor(int instructorID) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses WHERE InstructorID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorID);

            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Course c = new Course();
                    c.setCourseID(rs.getInt("CourseID"));
                    c.setCourseName(rs.getString("CourseName"));
                    c.setCourseCode(rs.getString("CourseCode"));
                    c.setCredits(rs.getInt("Credits"));
                    c.setDepartmentID(rs.getInt("DepartmentID"));
                    c.setInstructorID((Integer) rs.getObject("InstructorID"));
                    c.setClassroomID(rs.getInt("ClassroomID"));
                    c.setSemesterID(rs.getInt("SemesterID"));
                    c.setStartTime(rs.getString("StartTime"));
                    c.setEndTime(rs.getString("EndTime"));
                    c.setWeekDay(rs.getString("WeekDay"));
                    c.setHours(rs.getInt("Hours"));
                    c.setClassLevel(rs.getInt("ClassLevel"));
                    courses.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    // ClassLevel'lara göre departman ID'sine göre filtre
public List<String> getClassLevelsByDepartment(int departmentID) {
    String sql = "SELECT DISTINCT ClassLevel FROM Courses WHERE DepartmentID = ?";
    List<String> classLevels = new ArrayList<>();
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, departmentID);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                classLevels.add(rs.getString("ClassLevel"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return classLevels;
}

// Semester ve ClassLevel'e göre Course filtreleme
public List<Course> getCoursesByDepartmentClassLevelAndSemester(int departmentID, String classLevel, int semesterID) {
    String sql = "SELECT * FROM Courses WHERE DepartmentID = ? AND ClassLevel = ? AND SemesterID = ?";
    List<Course> courses = new ArrayList<>();
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, departmentID);
        pstmt.setString(2, classLevel);
        pstmt.setInt(3, semesterID);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                    Course c = new Course();
                    c.setCourseID(rs.getInt("CourseID"));
                    c.setCourseName(rs.getString("CourseName"));
                    c.setCourseCode(rs.getString("CourseCode"));
                    c.setCredits(rs.getInt("Credits"));
                    c.setDepartmentID(rs.getInt("DepartmentID"));
                    c.setInstructorID((Integer) rs.getObject("InstructorID"));
                    c.setClassroomID(rs.getInt("ClassroomID"));
                    c.setSemesterID(rs.getInt("SemesterID"));
                    c.setStartTime(rs.getString("StartTime"));
                    c.setEndTime(rs.getString("EndTime"));
                    c.setWeekDay(rs.getString("WeekDay"));
                    c.setHours(rs.getInt("Hours"));
                    c.setClassLevel(rs.getInt("ClassLevel"));
                    courses.add(c);
                }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return courses;
}

}
