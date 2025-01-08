/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

/**
 *
 * @author PC
 */
public class StudentCourse {
    private int studentCourseID;
    private int studentID; // FK to Student
    private int courseID;  // FK to Course

    public StudentCourse() {
    }

    public StudentCourse(int studentCourseID, int studentID, int courseID) {
        this.studentCourseID = studentCourseID;
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public int getStudentCourseID() {
        return studentCourseID;
    }

    public void setStudentCourseID(int studentCourseID) {
        this.studentCourseID = studentCourseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
