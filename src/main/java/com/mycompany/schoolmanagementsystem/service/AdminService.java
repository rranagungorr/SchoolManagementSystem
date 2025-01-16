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
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentDAO;
import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.management.Admin;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Student;
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


    // ----------- Course Management -----------
   public int createCourse(String courseName, String courseCode, int credits, int departmentID, Integer instructorID, 
                        int classroomID, int hours, int semesterID, String startTime, String endTime, 
                        String weekDay,int classlevel) {
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
                            String newStartTime, String newEndTime, String newWeekDay,int classlevel) {
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



    public boolean deleteCourse(int courseID) {
        return courseDAO.delete(courseID);
    }

    public Course getCourseByID(int courseID) {
        return courseDAO.getByID(courseID);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    public int createStudent(Student s) 
    {

        // Call the DAO to insert into the DB
        return studentDAO.create(s);
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

}
