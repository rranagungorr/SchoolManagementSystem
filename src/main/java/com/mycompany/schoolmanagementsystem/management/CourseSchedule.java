/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.management;


import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author PC
 */
public class CourseSchedule {
    private int scheduleID;
    private int courseID;      // FK to Course
    private Date scheduleDate;
    
    public CourseSchedule() {
    }

    public CourseSchedule(int scheduleID, int courseID, Date scheduleDate) {
        this.scheduleID = scheduleID;
        this.courseID = courseID;
        this.scheduleDate = scheduleDate;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

   }
