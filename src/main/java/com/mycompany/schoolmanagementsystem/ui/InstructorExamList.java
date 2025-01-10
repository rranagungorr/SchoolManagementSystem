/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DBUtil;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamDAO;
import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class InstructorExamList extends javax.swing.JPanel implements IPage {

    private DefaultTableModel tableModel;
    private ExamDAO examDAO; // ExamDAO kullanarak tüm sınavları alacağız

    /**
     * Creates new form InstructorExamList
     */
    public InstructorExamList() {
        initComponents();
        String[] columnNames = {"Exam ID", "Exam Name", "Exam Date", "Course ID", "Course Name"};

        // Setup the table model
        tableModel = new DefaultTableModel(columnNames, 0) {
            // Make columns not editable (optional)
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        examTable.setModel(tableModel);
        examTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.examDAO = new ExamDAO(); // ExamDAO üzerinden sınavları alacağız
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        examTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        examTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(examTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Exam List");

        jButton1.setText("my exams");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("all exams");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(562, 562, 562)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // Öğretmen bilgilerini almak için MainFrame üzerinden hesap objesini alıyoruz
        if (MainFrame.instance.getAccount() instanceof Instructor instructor) {
            // Öğretmenin derslerini almak için CourseDAO kullanıyoruz
            CourseDAO courseDAO = new CourseDAO();
            List<Course> instructorCourses = courseDAO.getCoursesByInstructor(instructor.getInstructorID());

            // Öğretmenin verdiği derslerin sınavlarını almak için ExamDAO kullanıyoruz
            ExamDAO examDAO = new ExamDAO();
            List<Exam> filteredExams = new ArrayList<>();

            // Öğretmenin verdiği derslerin sınavlarını filtreliyoruz
            for (Course course : instructorCourses) {
                List<Exam> courseExams = examDAO.getExamsByCourse(course.getCourseID());
                filteredExams.addAll(courseExams);
            }

            // Tabloyu sıfırlıyoruz
            tableModel.setRowCount(0);

            // Filtrelenmiş sınavları tabloya ekliyoruz
            for (Exam exam : filteredExams) {
                // Sınavlar için gerekli bilgileri tabloya ekliyoruz
                Object[] rowData = {
                    exam.getExamID(),
                    exam.getExamName(),
                    exam.getExamDate(),
                    exam.getCourseID() // Burada dersin ID'sini alıyoruz, courseName'i de alabilirsiniz
                };
                tableModel.addRow(rowData);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadAllExams();
    }//GEN-LAST:event_jButton2ActionPerformed


    private void loadAllExams() {
        // 1) Retrieve all exams from the database using ExamDAO
        List<Exam> exams = examDAO.getAll(); // ExamDAO'dan tüm sınavları alıyoruz

        // 2) Clear existing rows
        tableModel.setRowCount(0);

        // 3) Populate table
        for (Exam exam : exams) {
            // 4) Ders adını almak için CourseDAO kullanıyoruz
            CourseDAO courseDAO = new CourseDAO();
            String courseName = "Unknown Course"; // Varsayılan değer

            // Ders bilgilerini almak için courseID'yi kullanıyoruz
            Course course = courseDAO.getByID(exam.getCourseID());
            if (course != null) {
                courseName = course.getCourseName(); // Ders adı
            }

            Object[] rowData = {
                exam.getExamID(),
                exam.getExamName(),
                exam.getExamDate(),
                exam.getCourseID(),
                courseName// Eğer CourseName'i almak için ilgili sınıf eklediyseniz
            };
            tableModel.addRow(rowData);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable examTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        // Tüm sınavları yükle
        loadAllExams();
    }

}
