/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.management;

/**
 *
 * @author PC
 */
public class Course {
    private int courseID;
    private String courseName;
    private String courseCode;
    private int credits;
    private int fieldID;         // FK to Field
    private Integer instructorID; // FK to Instructor, can be null

    public Course() {
    }

    public Course(int courseID, String courseName, String courseCode,
                  int credits, int fieldID, Integer instructorID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.fieldID = fieldID;
        this.instructorID = instructorID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public Integer getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(Integer instructorID) {
        this.instructorID = instructorID;
    }
}
