/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

/**
 *
 * @author Merve
 */
public class CourseInstructor {
  
    private int courseInstructorID;
    private int instructorID;
    private int courseID;

    public CourseInstructor(int courseInstructorID, int instructorID, int courseID) {
        this.courseInstructorID = courseInstructorID;
        this.instructorID = instructorID;
        this.courseID = courseID;
    }

    public int getCourseInstructorID() {
        return courseInstructorID;
    }

    public void setCourseInstructorID(int courseInstructorID) {
        this.courseInstructorID = courseInstructorID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}


