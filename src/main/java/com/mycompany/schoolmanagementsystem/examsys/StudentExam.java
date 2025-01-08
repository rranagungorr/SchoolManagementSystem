/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

/**
 *
 * @author PC
 */
public class StudentExam {
    private int studentExamID;
    private int studentID; // FK to Student
    private int examID;    // FK to Exam

    public StudentExam() {
    }

    public StudentExam(int studentExamID, int studentID, int examID) {
        this.studentExamID = studentExamID;
        this.studentID = studentID;
        this.examID = examID;
    }

    public int getStudentExamID() {
        return studentExamID;
    }

    public void setStudentExamID(int studentExamID) {
        this.studentExamID = studentExamID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }
}
