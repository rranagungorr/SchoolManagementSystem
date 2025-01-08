/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.management;

/**
 *
 * @author PC
 */
public class Attendance {
    private int attendanceID;
    private int studentID;  // FK to Student
    private int scheduleID; // FK to CourseSchedule
    private String status;

    public Attendance() {
    }

    public Attendance(int attendanceID, int studentID, int scheduleID, String status) {
        this.attendanceID = attendanceID;
        this.studentID = studentID;
        this.scheduleID = scheduleID;
        this.status = status;
    }

    public int getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
