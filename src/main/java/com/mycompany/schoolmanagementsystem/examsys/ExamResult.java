/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

/**
 *
 * @author PC
 */
public class ExamResult {
    private int examResultID;
    private int studentExamID; // FK to StudentExam
    private double score;

    public ExamResult() {
    }

    public ExamResult(int examResultID, int studentExamID, double score) {
        this.examResultID = examResultID;
        this.studentExamID = studentExamID;
        this.score = score;
    }

    public int getExamResultID() {
        return examResultID;
    }

    public void setExamResultID(int examResultID) {
        this.examResultID = examResultID;
    }

    public int getStudentExamID() {
        return studentExamID;
    }

    public void setStudentExamID(int studentExamID) {
        this.studentExamID = studentExamID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
