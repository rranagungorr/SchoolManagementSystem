/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.examsys;

import com.mycompany.schoolmanagementsystem.ui.MainFrame;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Merve
 */
public class AcademicCalendar {

    public LocalDate getRegistrationStudentStartDate() {
        return registrationStudentStartDate;
    }

    public void setRegistrationStudentStartDate(LocalDate registrationStudentStartDate) {
        this.registrationStudentStartDate = registrationStudentStartDate;
    }

    public LocalDate getRegistrationStudentEndDate() {
        return registrationStudentEndDate;
    }

    public void setRegistrationStudentEndDate(LocalDate registrationStudentEndDate) {
        this.registrationStudentEndDate = registrationStudentEndDate;
    }

    public LocalDate getRegistrationCourseStartDate() {
        return registrationCourseStartDate;
    }

    public void setRegistrationCourseStartDate(LocalDate registrationCourseStartDate) {
        this.registrationCourseStartDate = registrationCourseStartDate;
    }

    public LocalDate getRegistrationCourseEndDate() {
        return registrationCourseEndDate;
    }

    public void setRegistrationCourseEndDate(LocalDate registrationCourseEndDate) {
        this.registrationCourseEndDate = registrationCourseEndDate;
    }

    public LocalDate getMidtermStartDate() {
        return midtermStartDate;
    }

    public void setMidtermStartDate(LocalDate midtermStartDate) {
        this.midtermStartDate = midtermStartDate;
    }

    public LocalDate getMidtermEndDate() {
        return midtermEndDate;
    }

    public void setMidtermEndDate(LocalDate midtermEndDate) {
        this.midtermEndDate = midtermEndDate;
    }

    public LocalDate getFinalStartDate() {
        return finalStartDate;
    }

    public void setFinalStartDate(LocalDate finalStartDate) {
        this.finalStartDate = finalStartDate;
    }

    public LocalDate getFinalEndDate() {
        return finalEndDate;
    }

    public void setFinalEndDate(LocalDate finalEndDate) {
        this.finalEndDate = finalEndDate;
    }

    private LocalDate registrationStudentStartDate;
    private LocalDate registrationStudentEndDate;
    private LocalDate registrationCourseStartDate;
    private LocalDate registrationCourseEndDate;
    private LocalDate midtermStartDate;
    private LocalDate midtermEndDate;
    private LocalDate finalStartDate;
    private LocalDate finalEndDate;

    private String studentRegistrationMessage = "Şu anda öğrenci kayıt dönemi dışında bir tarihtesiniz!";
    private String courseRegistrationMessage = "Şu anda ders kayıt dönemi dışında bir tarihtesiniz!";
    private String midtermPeriodMessage = "Şu anda ara sınav dönemi dışında bir tarihtesiniz!";
    private String finalPeriodMessage = "Şu anda final dönemi dışında bir tarihtesiniz!";

    public AcademicCalendar(LocalDate registrationStudentStartDate, LocalDate registrationStudentEndDate,
            LocalDate registrationCourseStartDate, LocalDate registrationCourseEndDate,
            LocalDate midtermStartDate, LocalDate midtermEndDate,
            LocalDate finalStartDate, LocalDate finalEndDate) {
        this.registrationStudentStartDate = registrationStudentStartDate;
        this.registrationStudentEndDate = registrationStudentEndDate;
        this.registrationCourseStartDate = registrationCourseStartDate;
        this.registrationCourseEndDate = registrationCourseEndDate;
        this.midtermStartDate = midtermStartDate;
        this.midtermEndDate = midtermEndDate;
        this.finalStartDate = finalStartDate;
        this.finalEndDate = finalEndDate;
    }

    public void errorMessage(String errorMessage, JPanel panel) {

        if (errorMessage != null) {
            JLabel messageLabel = new JLabel(errorMessage);
            messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
            messageLabel.setForeground(Color.RED);

            JOptionPane.showMessageDialog(null,
                    messageLabel,
                    "Hata",
                    JOptionPane.ERROR_MESSAGE);

            MainFrame.disablePanelComponents(panel);
        } else {
            System.out.println("Error message is null");
        }
    }

    // Tarihlerin kontrolü
    public void isWithinStudentRegistrationPeriod(LocalDate date, JPanel panel) {
        String errorMessage = "Şu anda öğrenci kayıt dönemi dışında bir tarihtesiniz!";
        if (!date.isBefore(registrationStudentStartDate) && !date.isAfter(registrationStudentEndDate)) {
            System.out.println("Öğrenci kayıt dönemindesiniz. İşlem yapabilirsiniz.");
        } else {
            System.out.println("Öğrenci kayıt dönemi dışında işlem yapılamaz.");
            errorMessage(errorMessage, panel);
        }
    }

    public boolean isWithinCourseRegistrationPeriod(LocalDate date) {
        return !date.isBefore(registrationCourseStartDate) && !date.isAfter(registrationCourseEndDate);
    }

    public boolean isWithinMidtermPeriod(LocalDate date) {
        return !date.isBefore(midtermStartDate) && !date.isAfter(midtermEndDate);
    }

    public boolean isWithinFinalPeriod(LocalDate date) {
        return !date.isBefore(finalStartDate) && !date.isAfter(finalEndDate);
    }

}
