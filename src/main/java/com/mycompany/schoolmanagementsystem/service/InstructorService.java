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
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
    
     public InstructorService(StudentExamDAO studentExamDAO, ExamResultDAO examResultDAO) {
        this.studentExamDAO = studentExamDAO;
        this.examResultDAO = examResultDAO;
       
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

    

    public void updateAttendanceStatus(Map<LocalDate, String> attendanceStatusMap, List<LocalDate> selectedDates, String status) {
        if (selectedDates == null || selectedDates.isEmpty()) {
            return;
        }

        for (LocalDate date : selectedDates) {
            attendanceStatusMap.put(date, status);
        }
    }



    // ----------- Enter Exam Results -----------
    public boolean assignScoreToStudent(int studentID, int courseID, int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }

        int examID = studentExamDAO.getExamIDByCourseAndStudent(courseID, studentID);
        return examResultDAO.assignScore(studentID, examID, score);
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
