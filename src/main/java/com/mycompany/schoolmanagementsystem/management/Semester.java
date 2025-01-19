/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.management;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Semester {
    private int semesterID;
    private String semesterName;
    private Date startDate;
    private Date endDate;
    private String status;
    private List<LocalDate> weekDates; // Yeni alan

    public Semester() {
        weekDates = new ArrayList<>(); // Listeyi başlat
    }

    public Semester(int semesterID, String semesterName, Date startDate, Date endDate, String status) {
        this.semesterID = semesterID;
        this.semesterName = semesterName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.weekDates = new ArrayList<>(); // Listeyi başlat
    }

    // Getter ve Setter
    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LocalDate> getWeekDates() {
        return weekDates;
    }

    public void setWeekDates(List<LocalDate> weekDates) {
        this.weekDates = weekDates;
    }

    @Override
    public String toString() {
        return semesterName; // Dönem adı (örnek: 2024 Güz)
    }
}

