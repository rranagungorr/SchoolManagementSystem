/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Student;
import com.mycompany.schoolmanagementsystem.service.StudentService;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class StudentExamList extends javax.swing.JPanel implements IPage{

    
    private int studentID;
    private StudentService studentService;
    private DefaultTableModel tableModel;
    
    public StudentExamList() {
        initComponents();
          studentService = new StudentService();
        setupTableModel();
    }
    
    

 private void setupTableModel() {
        String[] columnNames = {"Exam ID", "Exam Name", "Exam Date", "Course ID"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tablodaki hücreleri düzenlenemez yap
            }
        };
        examTable.setModel(tableModel);
    }

 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        examTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadStudentExams() {
        // 1) Retrieve the exams for this student
        List<Exam> exams = studentService.getExamsForStudent(studentID);

        // 2) Clear existing rows
        tableModel.setRowCount(0);

        // 3) Populate table
        for (Exam exam : exams) {
            Object[] rowData = {
                exam.getExamID(),
                exam.getExamName(),
                exam.getExamDate(),
            };
            tableModel.addRow(rowData);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable examTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

     @Override
    public void onPageSetted() {
        if (MainFrame.instance.getAccount() instanceof com.mycompany.schoolmanagementsystem.management.Student student) {
            this.studentID = student.getStudentID(); // Öğrenci ID'sini al
            loadStudentExams(); // Öğrenciye ait sınavları yükle
        }
    }
}
