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
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentDAO;
import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.management.Admin;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Field;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Student;
import java.util.List;

/**
 *
 * @author PC
 */
public class AdminService {
    private DepartmentDAO departmentDAO;
    private FieldDAO fieldDAO;
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
        this.fieldDAO = new FieldDAO();
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

    // ----------- Field Management -----------
    public int createField(String fieldName, int departmentID) {
        Field field = new Field();
        field.setFieldName(fieldName);
        field.setDepartmentID(departmentID);
        return fieldDAO.create(field);
    }

    public boolean updateField(int fieldID, String newName, int newDepartmentID) {
        Field f = fieldDAO.getByID(fieldID);
        if (f == null) {
            return false;
        }
        f.setFieldName(newName);
        f.setDepartmentID(newDepartmentID);
        return fieldDAO.update(f);
    }

    public boolean deleteField(int fieldID) {
        return fieldDAO.delete(fieldID);
    }

    // ----------- Course Management -----------
    public int createCourse(String courseName, String courseCode, int credits, int fieldID, Integer instructorID) {
        Course c = new Course();
        c.setCourseName(courseName);
        c.setCourseCode(courseCode);
        c.setCredits(credits);
        c.setFieldID(fieldID);
        c.setInstructorID(instructorID);
        return courseDAO.create(c);
    }

    public boolean updateCourse(int courseID, String newName, String newCode, int newCredits,
                                int newFieldID, Integer newInstructorID) {
        Course c = courseDAO.getByID(courseID);
        if (c == null) {
            return false;
        }
        c.setCourseName(newName);
        c.setCourseCode(newCode);
        c.setCredits(newCredits);
        c.setFieldID(newFieldID);
        c.setInstructorID(newInstructorID);
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
