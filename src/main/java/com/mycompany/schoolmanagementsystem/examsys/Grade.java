/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

/**
 *
 * @author PC
 */
public class Grade {
    private int gradeID;
    private int studentID; // FK to Student
    private int courseID;  // FK to Course
    private double grade;  // e.g., final letter grade or numeric

    public Grade() {
    }

    public Grade(int gradeID, int studentID, int courseID, double grade) {
        this.gradeID = gradeID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getGradeID() {
        return gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
