/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.management;

/**
 *
 * @author Merve
 */
public class Semester {

    private int semesterID;
    private String semesterName;
    private String startDate;
    private String endDate;
    private String status;

    public Semester() {
    }

    public Semester(int semesterID, String semesterName, String startDate,
            String endDate, String status) {
        this.semesterID = semesterID;
        this.semesterName = semesterName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return semesterName; // Dönem adı (örnek: 2024 Güz)
    }
}
