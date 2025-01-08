/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

import java.sql.Date;
/**
 *
 * @author PC
 */
public class Exam {
    private int examID;
    private String examName;
    private Date examDate;
    private int courseID;         // FK to Course
    private Integer invigilatorID; // FK to Instructor (nullable)
    private Integer classroomID;   // FK to Classroom (nullable)

    public Exam() {
    }

    public Exam(int examID, String examName, Date examDate,
                int courseID, Integer invigilatorID, Integer classroomID) {
        this.examID = examID;
        this.examName = examName;
        this.examDate = examDate;
        this.courseID = courseID;
        this.invigilatorID = invigilatorID;
        this.classroomID = classroomID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Integer getInvigilatorID() {
        return invigilatorID;
    }

    public void setInvigilatorID(Integer invigilatorID) {
        this.invigilatorID = invigilatorID;
    }

    public Integer getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(Integer classroomID) {
        this.classroomID = classroomID;
    }
}
