/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.service;

import com.mycompany.schoolmanagementsystem.examsys.DAO.AdminDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DBUtil;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentDAO;
import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.management.Admin;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC
 */
public class AdminService {

    private DepartmentDAO departmentDAO;
    private CourseDAO courseDAO;
    private ExamDAO examDAO;
    private InstructorDAO instructorDAO;
    private AdminDAO adminDAO;
    private StudentDAO studentDAO;
    // ... plus any other DAO you may need

    public AdminService() {
        // Ideally, you might use Dependency Injection
        // or a DI framework to manage these.
        this.departmentDAO = new DepartmentDAO();
        this.courseDAO = new CourseDAO();
        this.examDAO = new ExamDAO();
        this.instructorDAO = new InstructorDAO();
        this.adminDAO = new AdminDAO();
        this.studentDAO = new StudentDAO();
    }

    // ----------- Department Management -----------
    public int createDepartment(String departmentName) {
        Department d = new Department();
        d.setDepartmentName(departmentName);
        return departmentDAO.create(d);
    }

    public boolean updateDepartment(int departmentID, String newName) {
        Department d = departmentDAO.getByID(departmentID);
        if (d == null) {
            return false; // or throw exception
        }
        d.setDepartmentName(newName);
        return departmentDAO.update(d);
    }

    public boolean deleteDepartment(int departmentID) {
        return departmentDAO.delete(departmentID);
    }

    public Department getDepartmentByID(int id) {
        return departmentDAO.getByID(id);
    }

    public List<Department> getAllDepartments() {
        return departmentDAO.getAll();
    }

    public boolean deleteStudentWithDependencies(int studentID) {
        Connection connection = null;
        PreparedStatement deleteAttendance = null;
        PreparedStatement deleteStudentCourses = null;
        PreparedStatement deleteStudentExams = null;
        PreparedStatement deleteGrades = null;
        PreparedStatement deleteStudent = null;

        try {
            connection = DBUtil.getConnection();

            // 1. Attendance tablosundan sil
            String deleteAttendanceSQL = "DELETE FROM Attendance WHERE StudentID = ?";
            deleteAttendance = connection.prepareStatement(deleteAttendanceSQL);
            deleteAttendance.setInt(1, studentID);
            deleteAttendance.executeUpdate();

            // 2. StudentCourses tablosundan sil
            String deleteStudentCoursesSQL = "DELETE FROM StudentCourses WHERE StudentID = ?";
            deleteStudentCourses = connection.prepareStatement(deleteStudentCoursesSQL);
            deleteStudentCourses.setInt(1, studentID);
            deleteStudentCourses.executeUpdate();

            // 3. StudentExams tablosundan sil
            String deleteStudentExamsSQL = "DELETE FROM StudentExams WHERE StudentID = ?";
            deleteStudentExams = connection.prepareStatement(deleteStudentExamsSQL);
            deleteStudentExams.setInt(1, studentID);
            deleteStudentExams.executeUpdate();

            // 4. Grades tablosundan sil
            String deleteGradesSQL = "DELETE FROM Grades WHERE StudentID = ?";
            deleteGrades = connection.prepareStatement(deleteGradesSQL);
            deleteGrades.setInt(1, studentID);
            deleteGrades.executeUpdate();

            // 5. Students tablosundan sil
            String deleteStudentSQL = "DELETE FROM Students WHERE StudentID = ?";
            deleteStudent = connection.prepareStatement(deleteStudentSQL);
            deleteStudent.setInt(1, studentID);
            deleteStudent.executeUpdate();

            return true; // Başarılı

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata
        } finally {
            // Kaynakları manuel olarak kapat
            if (deleteAttendance != null) {
                try {
                    deleteAttendance.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteStudentCourses != null) {
                try {
                    deleteStudentCourses.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteStudentExams != null) {
                try {
                    deleteStudentExams.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteGrades != null) {
                try {
                    deleteGrades.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteStudent != null) {
                try {
                    deleteStudent.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ----------- Course Management -----------
    public int createCourse(String courseName, String courseCode, int credits, int departmentID, Integer instructorID, //  bu varrrrr
            int classroomID, int hours, int semesterID, String startTime, String endTime,
            String weekDay, int classlevel) {
        Course c = new Course();
        c.setCourseName(courseName);
        c.setCourseCode(courseCode);
        c.setCredits(credits);
        c.setDepartmentID(departmentID);
        c.setInstructorID(instructorID);
        c.setClassroomID(classroomID); // Yeni özellik
        c.setHours(hours); // Yeni özellik
        c.setSemesterID(semesterID); // Yeni özellik
        c.setStartTime(startTime); // Yeni özellik
        c.setEndTime(endTime); // Yeni özellik
        c.setWeekDay(weekDay); // Yeni özellik
        c.setClassLevel(classlevel); // Yeni özellik
        return courseDAO.create(c);
    }

    public boolean updateCourse(int courseID, String newName, String newCode, int newCredits, int newDepartmentID,
            Integer newInstructorID, int newClassroomID, int newHours, int newSemesterID,
            String newStartTime, String newEndTime, String newWeekDay, int classlevel) {
        Course c = courseDAO.getByID(courseID);
        if (c == null) {
            return false;
        }
        c.setCourseName(newName);
        c.setCourseCode(newCode);
        c.setCredits(newCredits);
        c.setDepartmentID(newDepartmentID);
        c.setInstructorID(newInstructorID);
        c.setClassroomID(newClassroomID);
        c.setHours(newHours);
        c.setSemesterID(newSemesterID);
        c.setStartTime(newStartTime);
        c.setEndTime(newEndTime);
        c.setWeekDay(newWeekDay);
        c.setClassLevel(classlevel);
        return courseDAO.update(c);
    }

    public boolean deleteCourseWithDependencies(int courseID) {
        Connection connection = null;
        PreparedStatement deleteCourseInstructors = null;
        PreparedStatement deleteCourseSchedules = null;
        PreparedStatement deleteStudentCourses = null;
        PreparedStatement deleteExams = null;
        PreparedStatement deleteGrades = null;
        PreparedStatement deleteCourse = null;

        try {
            connection = DBUtil.getConnection();

            // 1. CourseInstructors tablosundan sil
            String deleteCourseInstructorsSQL = "DELETE FROM CourseInstructors WHERE CourseID = ?";
            deleteCourseInstructors = connection.prepareStatement(deleteCourseInstructorsSQL);
            deleteCourseInstructors.setInt(1, courseID);
            deleteCourseInstructors.executeUpdate();

            // 2. CourseSchedules tablosundan sil
            String deleteCourseSchedulesSQL = "DELETE FROM CourseSchedules WHERE CourseID = ?";
            deleteCourseSchedules = connection.prepareStatement(deleteCourseSchedulesSQL);
            deleteCourseSchedules.setInt(1, courseID);
            deleteCourseSchedules.executeUpdate();

            // 3. StudentCourses tablosundan sil
            String deleteStudentCoursesSQL = "DELETE FROM StudentCourses WHERE CourseID = ?";
            deleteStudentCourses = connection.prepareStatement(deleteStudentCoursesSQL);
            deleteStudentCourses.setInt(1, courseID);
            deleteStudentCourses.executeUpdate();

            // 4. Exams tablosundan sil
            String deleteExamsSQL = "DELETE FROM Exams WHERE CourseID = ?";
            deleteExams = connection.prepareStatement(deleteExamsSQL);
            deleteExams.setInt(1, courseID);
            deleteExams.executeUpdate();

            // 5. Grades tablosundan sil
            String deleteGradesSQL = "DELETE FROM Grades WHERE CourseID = ?";
            deleteGrades = connection.prepareStatement(deleteGradesSQL);
            deleteGrades.setInt(1, courseID);
            deleteGrades.executeUpdate();

            // 6. Courses tablosundan sil
            String deleteCourseSQL = "DELETE FROM Courses WHERE CourseID = ?";
            deleteCourse = connection.prepareStatement(deleteCourseSQL);
            deleteCourse.setInt(1, courseID);
            deleteCourse.executeUpdate();

            return true; // Başarılı

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata
        } finally {
            // Kaynakları manuel olarak kapat
            if (deleteCourseInstructors != null) {
                try {
                    deleteCourseInstructors.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteCourseSchedules != null) {
                try {
                    deleteCourseSchedules.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteStudentCourses != null) {
                try {
                    deleteStudentCourses.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteExams != null) {
                try {
                    deleteExams.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteGrades != null) {
                try {
                    deleteGrades.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteCourse != null) {
                try {
                    deleteCourse.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Course getCourseByID(int courseID) {
        return courseDAO.getByID(courseID);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    public int createStudent(Student s) {

        // Call the DAO to insert into the DB
        return studentDAO.create(s);
    }

    public boolean addInstructorToCourse(int courseID, int instructorID) {            // bu varr
        String query = "INSERT INTO courseInstructors (CourseID, InstructorID) VALUES (?, ?)";
        try ( Connection connection = DBUtil.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, courseID);
            preparedStatement.setInt(2, instructorID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteInstructorMapping(int courseID) {
        String query = "DELETE FROM courseInstructors WHERE CourseID = ?";
        try ( Connection connection = DBUtil.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, courseID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ----------- Exam Management -----------
    public int createExam(String examName, java.sql.Date examDate, int courseID, Integer invigilatorID, Integer classroomID) {
        Exam exam = new Exam();
        exam.setExamName(examName);
        exam.setExamDate(examDate);
        exam.setCourseID(courseID);
        exam.setInvigilatorID(invigilatorID);
        exam.setClassroomID(classroomID);
        return examDAO.create(exam);
    }

    public boolean updateExam(int examID, String examName, java.sql.Date examDate,
            int courseID, Integer invigilatorID, Integer classroomID) {
        Exam exam = examDAO.getByID(examID);
        if (exam == null) {
            return false;
        }
        exam.setExamName(examName);
        exam.setExamDate(examDate);
        exam.setCourseID(courseID);
        exam.setInvigilatorID(invigilatorID);
        exam.setClassroomID(classroomID);
        return examDAO.update(exam);
    }

    public boolean deleteExam(int examID) {
        return examDAO.delete(examID);
    }

    public List<Exam> getAllExams() {
        return examDAO.getAll();
    }

    // ----------- Instructor Management -----------
    public int createInstructor(String name, String surname, String email, String gender,
            Integer departmentID, String username, String password) {
        Instructor i = new Instructor();
        i.setName(name);
        i.setSurname(surname);
        i.setEmail(email);
        i.setGender(gender);
        i.setDepartmentID(departmentID);
        i.setUsername(username);
        i.setPassword(password);
        return instructorDAO.create(i);
    }

    public boolean deleteInstructorWithDependencies(int instructorID) {
        Connection connection = null;
        PreparedStatement updateCourses = null;
        PreparedStatement updateExams = null;
        PreparedStatement deleteCourseInstructors = null;
        PreparedStatement deleteInstructor = null;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false); // Transaction başlat

            // 1. Courses tablosunda instructorID'yi NULL yap
            String updateCoursesSQL = "UPDATE Courses SET InstructorID = NULL WHERE InstructorID = ?";
            updateCourses = connection.prepareStatement(updateCoursesSQL);
            updateCourses.setInt(1, instructorID);
            updateCourses.executeUpdate();

            // 2. Exams tablosunda instructor ile ilişkili kayıtları sil
            String deleteExamsSQL = "UPDATE Exams SET InvigilatorID = NULL WHERE InvigilatorID = ?";
            updateExams = connection.prepareStatement(deleteExamsSQL);
            updateExams.setInt(1, instructorID);
            updateExams.executeUpdate();

            // 3. CourseInstructors tablosunda instructor ile ilişkili kayıtları sil
            String deleteCourseInstructorsSQL = "DELETE FROM CourseInstructors WHERE InstructorID = ?";
            deleteCourseInstructors = connection.prepareStatement(deleteCourseInstructorsSQL);
            deleteCourseInstructors.setInt(1, instructorID);
            deleteCourseInstructors.executeUpdate();

            // 4. Instructors tablosundan instructor'ı sil
            String deleteInstructorSQL = "DELETE FROM Instructors WHERE InstructorID = ?";
            deleteInstructor = connection.prepareStatement(deleteInstructorSQL);
            deleteInstructor.setInt(1, instructorID);
            deleteInstructor.executeUpdate();

            connection.commit(); // Transaction'u tamamla
            return true;

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Hata durumunda rollback
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            // Kaynakları manuel olarak kapat
            if (updateCourses != null) {
                try {
                    updateCourses.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (updateExams != null) {
                try {
                    updateExams.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteCourseInstructors != null) {
                try {
                    deleteCourseInstructors.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (deleteInstructor != null) {
                try {
                    deleteInstructor.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    } 

    // ... Additional instructor-related methods, e.g., update, delete
    // ----------- Admin Management (itself) -----------
    // For completeness, methods to manage admin accounts if needed
    public int createAdmin(String name, String surname, String email, String gender, String username, String password) {
        Admin a = new Admin();
        a.setName(name);
        a.setSurname(surname);
        a.setEmail(email);
        a.setGender(gender);
        a.setUsername(username);
        a.setPassword(password);
        return adminDAO.create(a);
    }

    public boolean deleteStudent(int studentID) {
        studentDAO.delete(studentID);
        return true;
    }

// AdminService.java içine eklenir
    public boolean validateDateRange(java.util.Date selectedDate, String startDateStr, String endDateStr) {
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        return !(selectedDate.before(startDate) || selectedDate.after(endDate));
    } 

    public boolean validateExamDate(java.util.Date selectedDate, String examType) {
        if ("Midterm".equals(examType)) {
            return validateDateRange(selectedDate, "2024-11-04", "2025-01-03");
        } else if ("Final".equals(examType)) {
            return validateDateRange(selectedDate, "2025-01-13", "2025-01-25");
        }
        return false;
    }

}
