/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

/**
 *
 * @author Merve
 */
public class CreditLimit {
    
    private int creditLimitID;
    private int classLevel;
    private int maxCredit;
    private int semesterID;

    // Constructor
    public CreditLimit(int creditLimitID, int classLevel, int maxCredit, int semesterID) {
        this.creditLimitID = creditLimitID;
        this.classLevel = classLevel;
        this.maxCredit = maxCredit;
        this.semesterID = semesterID;
    }

    // Getters and Setters
    public int getCreditLimitID() {
        return creditLimitID;
    }

    public void setCreditLimitID(int creditLimitID) {
        this.creditLimitID = creditLimitID;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public int getMaxCredit() {
        return maxCredit;
    }

    public void setMaxCredit(int maxCredit) {
        this.maxCredit = maxCredit;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

}
