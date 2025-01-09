/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.service;

import com.mycompany.schoolmanagementsystem.examsys.DAO.AdminDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.FieldDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentCourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.*;
import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.examsys.ExamResult;
import com.mycompany.schoolmanagementsystem.examsys.StudentCourse;
import com.mycompany.schoolmanagementsystem.examsys.StudentExam;
import com.mycompany.schoolmanagementsystem.management.*;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Field;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import java.util.List;

/**
 *
 * @author PC
 */
public class StudentService {

    private StudentDAO studentDAO;
    private StudentCourseDAO studentCourseDAO;
    private AttendanceDAO attendanceDAO;
    private CourseScheduleDAO courseScheduleDAO;
    private ExamResultDAO examResultDAO;
    private StudentExamDAO studentExamDAO;
    private CourseDAO courseDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
        this.studentCourseDAO = new StudentCourseDAO();
        this.attendanceDAO = new AttendanceDAO();
        this.courseScheduleDAO = new CourseScheduleDAO();
        this.examResultDAO = new ExamResultDAO();
        this.studentExamDAO = new StudentExamDAO();
        this.courseDAO = new CourseDAO();
    }

    // ----------- Student Profile Management -----------
    public int registerStudent(String name, String surname, int credits, int classLevel,
            String email, Integer departmentID, String username,
            String password, String gender) {
        Student s = new Student();
        s.setName(name);
        s.setSurname(surname);
        s.setCredits(credits);
        s.setClassLevel(classLevel);
        s.setEmail(email);
        s.setDepartmentID(departmentID);
        s.setUsername(username);
        s.setPassword(password);
        s.setGender(gender);
        return studentDAO.create(s);
    }

    public Student getStudentByID(int studentID) {
        return studentDAO.getByID(studentID);
    }

    // ... updateStudentProfile, deleteStudent, etc.
    // ----------- Course Enrollment -----------
    public boolean enrollInCourse(int studentID, int courseID) {
        // Could add business logic, e.g. check capacity, check prerequisites, etc.
        StudentCourse sc = new StudentCourse();
        sc.setStudentID(studentID);
        sc.setCourseID(courseID);
        int newID = studentCourseDAO.create(sc);
        return newID > 0; // success if > 0
    }

    public List<Integer> getStudentCourseIDs(int studentID) {
        return studentCourseDAO.getCourseIDsByStudent(studentID);
    }

    public List<Course> getStudentCourses(int studentID) {
        // A simple approach: retrieve course IDs, then fetch each Course
        List<Integer> courseIDs = studentCourseDAO.getCourseIDsByStudent(studentID);
        return courseIDs.stream()
                .map(courseDAO::getByID)
                .toList();
    }

    // ----------- Checking Exam Results -----------
    public List<ExamResult> getExamResultsForStudent(int studentID) {
        return examResultDAO.getResultsByStudent(studentID);
    }

    // ----------- Checking Attendance -----------
    // We might need a more complex approach that joins Attendance and CourseSchedules
    // For brevity, let's show reading the raw attendance records:
    public List<Attendance> getAllAttendanceRecords(int studentID) {
        // This returns all attendance records in the system; 
        // you might want to filter by StudentID in the DAO or here
        return attendanceDAO.getAll().stream()
                .filter(a -> a.getStudentID() == studentID)
                .toList();
    }

    // ----------- Exam Enrollment -----------
    public boolean enrollInExam(int studentID, int examID) {
        // StudentExam table links student to exam
        StudentExam se = new StudentExam();
        se.setStudentID(studentID);
        se.setExamID(examID);
        int newID = studentExamDAO.create(se);
        return newID > 0;
    }

    // ... Additional student-related logic
    public List<Course> getAllCoursesForDepartment(int departmentID) {
        return courseDAO.getAllByDepartment(departmentID);
    }

    public boolean isStudentEnrolled(int studentID, int courseID) {
        // We are calling StudentCourseDAO's "exists" method
        return studentCourseDAO.exists(studentID, courseID);
    }
}
