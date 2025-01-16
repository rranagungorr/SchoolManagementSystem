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
public class TimeManager {

    private static TimeManager instance; // Singleton tasarımı için
    private LocalDate currentDate;

    // Singleton örneği oluştur
    private TimeManager() {
        this.currentDate = LocalDate.now(); // Varsayılan olarak bugünün tarihi
    }

    public static TimeManager getInstance() {
        if (instance == null) {
            instance = new TimeManager();
        }
        return instance;
    }

    // Tarihi güncelle
    public void setCurrentDate(LocalDate date) {
        this.currentDate = date;
    }

    // Mevcut tarihi al
    public LocalDate getCurrentDate() {
        return this.currentDate;
    }
}
