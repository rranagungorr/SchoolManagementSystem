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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentID);
            try ( ResultSet rs = pstmt.executeQuery()) {
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
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentID);
            pstmt.setString(2, classLevel);
            pstmt.setInt(3, semesterID);
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

    public boolean updateCourseSchedule(int courseID, String weekDay, String startTime, String endTime) {
        String query = "UPDATE Courses SET WeekDay = ?, StartTime = ?, EndTime = ? WHERE CourseID = ?";
        try ( Connection connection = DBUtil.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, weekDay);
            preparedStatement.setString(2, startTime);
            preparedStatement.setString(3, endTime);
            preparedStatement.setInt(4, courseID);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getCourseIDByName(String courseName) {
        String query = "SELECT CourseID FROM Courses WHERE CourseName = ?";
        try ( Connection connection = DBUtil.getConnection();  PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, courseName);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("CourseID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Eğer kurs bulunamazsa -1 döner
    }

    public List<Course> getCoursesByFilters(int instructorID, int departmentID, int semesterID, String classLevel) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses "
                + "WHERE InstructorID = ? AND DepartmentID = ? AND SemesterID = ? AND ClassLevel = ?";

        try ( Connection connection = DBUtil.getConnection();  PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, instructorID);
            ps.setInt(2, departmentID);
            ps.setInt(3, semesterID);
            ps.setString(4, classLevel);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                course.setClassLevel(rs.getInt("ClassLevel"));
                course.setSemesterID(rs.getInt("SemesterID"));
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public List<Course> getCoursesByInstructorID(int instructorID) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses WHERE InstructorID = ?";

        try ( Connection connection = DBUtil.getConnection();  PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, instructorID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                course.setClassLevel(rs.getInt("ClassLevel"));
                course.setSemesterID(rs.getInt("SemesterID"));

                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public boolean addStudentCourse(int studentID, int courseID) {
        String sql = "INSERT INTO StudentCourses (StudentID, CourseID) VALUES (?, ?)";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentID);
            pstmt.setInt(2, courseID);
            return pstmt.executeUpdate() > 0; // Ekleme başarılı mı?
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Hata durumunda false döner
    }

    public List<Course> getMandatoryCourses(int departmentID, int classLevel, int semesterID) {
        String sql = "SELECT * FROM Courses WHERE DepartmentID = ? AND ClassLevel = ? AND SemesterID = ?";
        List<Course> courses = new ArrayList<>();
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentID);
            pstmt.setInt(2, classLevel);
            pstmt.setInt(3, semesterID);

            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setCourseID(rs.getInt("CourseID"));
                    course.setCourseName(rs.getString("CourseName"));
                    course.setCredits(rs.getInt("Credits"));
                    course.setClassLevel(rs.getInt("ClassLevel"));
                    course.setDepartmentID(rs.getInt("DepartmentID"));
                    course.setSemesterID(rs.getInt("SemesterID"));
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> getCoursesByStudentID(int studentID) {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT DISTINCT c.CourseID, c.CourseName "
                + "FROM StudentCourses sc "
                + "JOIN Courses c ON sc.CourseID = c.CourseID "
                + "WHERE sc.StudentID = ?";
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

}
