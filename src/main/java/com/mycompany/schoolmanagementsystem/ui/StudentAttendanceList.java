/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.AttendanceDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.management.Attendance;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Student;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class StudentAttendanceList extends javax.swing.JPanel implements IPage {

    private int studentID; // Giriş yapan öğrencinin ID'si
    private CourseDAO courseDAO;
    private AttendanceDAO attendanceDAO;

    public StudentAttendanceList() {
        initComponents();
        courseDAO = new CourseDAO();
        attendanceDAO = new AttendanceDAO();
    }

    // Öğrencinin derslerini yükle
    private void loadStudentCourses() {
        List<Course> courses = courseDAO.getCoursesByStudentID(studentID); // Öğrencinin derslerini al
        DefaultTableModel courseModel = (DefaultTableModel) courseTable.getModel();
        courseModel.setRowCount(0); // Mevcut veriyi temizle

        for (Course course : courses) {
            courseModel.addRow(new Object[]{course.getCourseID(), course.getCourseName()}); // Ders bilgilerini tabloya ekle
        }
    }

    // Seçilen dersin attendance bilgilerini yükle
    private void loadAttendanceForCourse(int courseID) {
        List<Attendance> attendances = attendanceDAO.getAttendanceWithDateByStudentAndCourse(studentID, courseID); // Attendance bilgilerini al
        DefaultTableModel attendanceModel = (DefaultTableModel) attendanceTable.getModel();
        attendanceModel.setRowCount(0); // Mevcut veriyi temizle

        for (Attendance attendance : attendances) {
            attendanceModel.addRow(new Object[]{
                attendance.getScheduleDate(),
                attendance.getStatus()
            }); // Tarih ve durum bilgilerini tabloya ekle
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        attendanceTable = new javax.swing.JTable();

        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        courseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                courseTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(courseTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Attendance List");

        attendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(attendanceTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                        .addComponent(jScrollPane2)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void courseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseTableMouseClicked
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow != -1) {
            int courseID = (int) courseTable.getValueAt(selectedRow, 0); // Seçilen dersin ID'sini al
            loadAttendanceForCourse(courseID); // Seçilen ders için attendance bilgilerini yükle
        }
    }//GEN-LAST:event_courseTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendanceTable;
    private javax.swing.JTable courseTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

       @Override
    public void onPageSetted() {
        Object account = MainFrame.instance.getAccount();
        if (account instanceof Student student) {
            this.studentID = student.getStudentID(); // Öğrenci ID'sini al
            loadStudentCourses(); // Dersleri yükle
        }
    }

}
