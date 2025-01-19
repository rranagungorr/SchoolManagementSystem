/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

import java.time.LocalDate;

/**
 *
 * @author Merve
 */
public class Week {

    private String weekName; // Örneğin, "1. Hafta"
    private LocalDate startDate; // Haftanın başlangıç tarihi
    private LocalDate endDate;   // Haftanın bitiş tarihi

    public Week(String weekName, LocalDate startDate, LocalDate endDate) {
        this.weekName = weekName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getWeekName() {
        return weekName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return weekName + ": " + startDate + " - " + endDate;
    }

}
