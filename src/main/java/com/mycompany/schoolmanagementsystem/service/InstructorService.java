/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.service;

import com.mycompany.schoolmanagementsystem.examsys.DAO.AdminDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamDAO;
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
import com.mycompany.schoolmanagementsystem.management.Instructor;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class InstructorService {

    private InstructorDAO instructorDAO;
    private CourseScheduleDAO courseScheduleDAO;
    private AttendanceDAO attendanceDAO;
    private StudentCourseDAO studentCourseDAO;
      CourseDAO courseDAO;
    private ExamResultDAO examResultDAO;
    private StudentExamDAO studentExamDAO;

    public InstructorService() {
        this.instructorDAO = new InstructorDAO();
        this.courseScheduleDAO = new CourseScheduleDAO();
        this.attendanceDAO = new AttendanceDAO();
        this.studentCourseDAO = new StudentCourseDAO();
        this.courseDAO = new CourseDAO();
        this.examResultDAO = new ExamResultDAO();
        this.studentExamDAO = new StudentExamDAO();
    }

    // ----------- Scheduling a Course -----------
    public int setCourseSchedule(int courseID, Date scheduleDate) {
        CourseSchedule cs = new CourseSchedule();
        cs.setCourseID(courseID);
        cs.setScheduleDate(scheduleDate);
        return courseScheduleDAO.create(cs);
    }

    // ----------- Mark Attendance -----------
    public int markAttendance(int studentID, int scheduleID, String status) {
        // Could add logic: check if student is actually enrolled, if schedule is valid, etc.
        Attendance attendance = new Attendance();
        attendance.setStudentID(studentID);
        attendance.setScheduleID(scheduleID);
        attendance.setStatus(status);
        return attendanceDAO.create(attendance);
    }

    // Update attendance if needed
    public boolean updateAttendanceStatus(int attendanceID, String newStatus) {
        Attendance record = attendanceDAO.getByID(attendanceID);
        if (record == null) {
            return false;
        }
        record.setStatus(newStatus);
        return attendanceDAO.update(record);
    }

    // ----------- Enter Exam Results -----------
    public int enterExamResult(int studentID, int examID, double score) {
        // 1) Ensure there's a StudentExam record
        //    If not, create one or handle logic differently
        // For simplicity, let's assume we create it if not exist
        // (In a real system, you might check or require a prior enrollment step.)

        // Check if StudentExam record exists:
        List<StudentExam> allStudentExams = studentExamDAO.getAll();
        StudentExam existing = allStudentExams.stream()
                .filter(se -> se.getStudentID() == studentID && se.getExamID() == examID)
                .findFirst()
                .orElse(null);

        if (existing == null) {
            // create
            existing = new StudentExam();
            existing.setStudentID(studentID);
            existing.setExamID(examID);
            int newID = studentExamDAO.create(existing);
            if (newID <= 0) {
                return 0; // fail
            }
            existing.setStudentExamID(newID);
        }

        // 2) Now create an ExamResult for the found or created StudentExam
        ExamResult er = new ExamResult();
        er.setStudentExamID(existing.getStudentExamID());
        er.setScore(score);
        return examResultDAO.create(er);
    }

    // ----------- List Students in a Course -----------
    public List<Student> getStudentsByCourse(int courseID) {
        List<Integer> studentIDs = studentCourseDAO.getStudentIDsByCourse(courseID);
        // Then fetch Student objects for each ID
        StudentDAO studentDAO = new StudentDAO();
        return studentIDs.stream()
                .map(studentDAO::getByID)
                .collect(Collectors.toList());
    }
    
    public List<Course> getCoursesByInstructor(int instructorID) {
        return courseDAO.getCoursesByInstructor(instructorID);
    }
    // ----------- Other instructor-related methods -----------
    // e.g. update personal info for instructors, retrieve instructor by ID, etc.
}
