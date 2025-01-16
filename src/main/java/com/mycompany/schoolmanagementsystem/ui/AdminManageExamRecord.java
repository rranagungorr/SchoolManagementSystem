/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.ClassroomDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.SemesterDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentExamDAO;
import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.examsys.StudentExam;
import com.mycompany.schoolmanagementsystem.management.Classroom;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Semester;
import com.mycompany.schoolmanagementsystem.management.Student;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class AdminManageExamRecord extends javax.swing.JPanel implements IPage {

    private DefaultTableModel tableModel;
    private StudentDAO studentDAO;
    private ExamDAO examDAO;
    private StudentExamDAO studentExamDAO;
    private ClassroomDAO classroomDAO;
    private InstructorDAO instructorDAO;
    private DepartmentDAO departmentDAO;
    private SemesterDAO semesterDAO;

    public AdminManageExamRecord() {
        initComponents();
        studentDAO = new StudentDAO();
        examDAO = new ExamDAO();
        studentExamDAO = new StudentExamDAO();
        this.classroomDAO = new ClassroomDAO();
        this.instructorDAO = new InstructorDAO();
        this.departmentDAO = new DepartmentDAO();
        this.semesterDAO = new SemesterDAO();

        // Table model oluştur
        tableModel = new DefaultTableModel(new String[]{"Exam ID", "Exam Name", "Exam Date", "Course ID"}, 0);
        examRecordsTable.setModel(tableModel);

    }

    private void loadDepartments() {
        jComboBox3.removeAllItems(); // ComboBox'ı temizle
        List<Department> departments = departmentDAO.getAll();
        DefaultComboBoxModel<Department> departmentModel = new DefaultComboBoxModel<>();
        for (Department department : departments) {
            departmentModel.addElement(department); // Her bir departmanı modele ekle
        }
        jComboBox3.setModel(departmentModel);
    }

    private void loadSemesters() {
        List<Semester> semesters = semesterDAO.getAll();
        DefaultComboBoxModel<Semester> semesterModel = new DefaultComboBoxModel<>();
        for (Semester s : semesters) {
            semesterModel.addElement(s);
        }
        jComboBox5.setModel(semesterModel);
    }

    private void filterExamsAndStudents() {
        Department selectedDepartment = (Department) jComboBox3.getSelectedItem();
        String selectedClassLevel = (String) jComboBox4.getSelectedItem();
        Semester selectedSemester = (Semester) jComboBox5.getSelectedItem();

        if (selectedDepartment == null || selectedClassLevel == null || selectedSemester == null) {
            JOptionPane.showMessageDialog(this, "Please select Department, Class Level, and Semester.");
            return;
        }

        // 1. Exams Tablosunu Güncelle
        loadFilteredExams(selectedDepartment.getDepartmentID(), selectedClassLevel, selectedSemester.getSemesterID());

        // 2. Students JList Güncelle
        loadFilteredStudents(selectedDepartment.getDepartmentID(), selectedClassLevel, selectedSemester.getSemesterID());
    }

    private void loadFilteredExams(int departmentID, String classLevel, int semesterID) {
        List<Exam> filteredExams = examDAO.getExamsByDepartmentClassLevelAndSemester(departmentID, classLevel, semesterID);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Exam ID", "Exam Name", "Date", "Instructor", "Classroom"}, 0);
        for (Exam exam : filteredExams) {
            String instructorName = instructorDAO.getByID(exam.getInvigilatorID()).getName();
            String classroomName = classroomDAO.getByID(exam.getClassroomID()).getClassroomName();

            tableModel.addRow(new Object[]{exam.getExamID(), exam.getExamName(), exam.getExamDate(), instructorName, classroomName});
        }

        jTable2.setModel(tableModel);
    }

    private void loadFilteredStudents(int departmentID, String classLevel, int semesterID) {
        List<Student> filteredStudents = studentDAO.getStudentsByDepartmentClassLevelAndSemester(departmentID, classLevel, semesterID);

        DefaultListModel<Student> listModel = new DefaultListModel<>();
        for (Student student : filteredStudents) {
            listModel.addElement(student);
        }

        studentJList.setModel(listModel);
    }

    private void loadExamRecords() {
        try {
            List<StudentExam> studentExams = studentExamDAO.getAll(); // Veritabanından kayıtları al

            // Tablo modelini oluştur (StudentID ve ExamID gizli olacak)
            DefaultTableModel tableModel = new DefaultTableModel(
                    new String[]{"StudentID", "ExamID", "Student Name", "Exam Name", "Date", "Instructor Name", "Classroom"}, 0
            );

            for (StudentExam studentExam : studentExams) {
                // Öğrenci ve sınav bilgilerini al
                String studentName = studentDAO.getByID(studentExam.getStudentID()).getName();
                String studentSurname = studentDAO.getByID(studentExam.getStudentID()).getSurname(); // Öğrenci soyadı
                String fullStudentName = studentName + " " + studentSurname; // Tam öğrenci adı

                Exam exam = examDAO.getByID(studentExam.getExamID());
                String examName = exam.getExamName();
                java.sql.Date examDate = exam.getExamDate();

                String instructorName = instructorDAO.getByID(exam.getInvigilatorID()).getName();
                String instructorSurname = instructorDAO.getByID(exam.getInvigilatorID()).getSurname(); // Öğretmen soyadı
                String fullInstructorName = instructorName + " " + instructorSurname; // Tam öğretmen adı

                String classroomName = classroomDAO.getByID(exam.getClassroomID()).getClassroomName();

                // Tabloya yeni bir satır ekle
                tableModel.addRow(new Object[]{
                    studentExam.getStudentID(),
                    studentExam.getExamID(),
                    fullStudentName,
                    examName,
                    examDate,
                    fullInstructorName,
                    classroomName
                });
            }

            // Tabloyu modele bağla
            examRecordsTable.setModel(tableModel);

            // StudentID ve ExamID sütunlarını gizle
            examRecordsTable.getColumnModel().getColumn(0).setMinWidth(0);
            examRecordsTable.getColumnModel().getColumn(0).setMaxWidth(0);
            examRecordsTable.getColumnModel().getColumn(0).setPreferredWidth(0);

            examRecordsTable.getColumnModel().getColumn(1).setMinWidth(0);
            examRecordsTable.getColumnModel().getColumn(1).setMaxWidth(0);
            examRecordsTable.getColumnModel().getColumn(1).setPreferredWidth(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading exam records: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void recordStudentExams() {
        int selectedExamRow = jTable2.getSelectedRow();
        if (selectedExamRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an exam from the table.");
            return;
        }

        // Tablo modelinin ilk sütunundaki ExamID'yi al
        int examID = (Integer) jTable2.getValueAt(selectedExamRow, 0);

        // ExamDAO ile Exam nesnesini al
        Exam selectedExam = examDAO.getByID(examID);
        if (selectedExam == null) {
            JOptionPane.showMessageDialog(this, "Failed to retrieve the selected exam.");
            return;
        }

        // Öğrencileri seç
        List<Student> selectedStudents = studentJList.getSelectedValuesList();
        if (selectedStudents.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one student.");
            return;
        }

        for (Student student : selectedStudents) {
            // Aynı sınavı tekrar almasını kontrol et
            boolean alreadyRecorded = studentExamDAO.isStudentEnrolledInExam(student.getStudentID(), selectedExam.getExamID());
            if (alreadyRecorded) {
                JOptionPane.showMessageDialog(this, "Student " + student.getName() + " is already enrolled in this exam.");
                continue;
            }

            // Kaydı ekle
            boolean success = studentExamDAO.addStudentToExam(student.getStudentID(), selectedExam.getExamID());
            if (success) {
                JOptionPane.showMessageDialog(this, "Student " + student.getName() + " successfully added to the exam.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add student " + student.getName() + " to the exam.");
            }
        }

        // Exam Records Tablosunu Güncelle
        loadExamRecords();
    }

    private void deleteSelectedExamRecord() {
        // Tablodan seçilen satırın kontrolü
        int selectedRow = examRecordsTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a record to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Tablo modelinden StudentID ve ExamID'yi al
        int studentID = (Integer) examRecordsTable.getValueAt(selectedRow, 0); // StudentID sütunu
        int examID = (Integer) examRecordsTable.getValueAt(selectedRow, 1);    // ExamID sütunu

        // Silme işlemi
        boolean success = studentExamDAO.deleteStudentExam(studentID, examID);
        if (success) {
            JOptionPane.showMessageDialog(this, "Record deleted successfully.");
            loadExamRecords(); // Tabloyu güncelle
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete the record. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonRecord = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        examRecordsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButtonQuery = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        studentJList = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(253, 253, 236));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("EXAM RECORD");

        jButtonRecord.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButtonRecord.setText("Record");
        jButtonRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecordActionPerformed(evt);
            }
        });

        examRecordsTable.setBackground(new java.awt.Color(255, 228, 245));
        examRecordsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(examRecordsTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("EXAM RECORDS");

        jTable2.setBackground(new java.awt.Color(255, 255, 171));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("EXAMS");

        jLabel7.setText("Department");

        jLabel8.setText("ClassLevel");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jLabel9.setText("Semester");

        jButtonQuery.setText("Query");
        jButtonQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQueryActionPerformed(evt);
            }
        });

        studentJList.setBackground(new java.awt.Color(255, 255, 171));
        jScrollPane3.setViewportView(studentJList);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("STUDENTS");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jButtonQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jButtonQuery)
                        .addGap(130, 130, 130)
                        .addComponent(jButtonRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecordActionPerformed
        // TODO add your handling code here:
        recordStudentExams();
    }//GEN-LAST:event_jButtonRecordActionPerformed

    private void jButtonQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQueryActionPerformed
        filterExamsAndStudents();
    }//GEN-LAST:event_jButtonQueryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteSelectedExamRecord();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable examRecordsTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonQuery;
    private javax.swing.JButton jButtonRecord;
    private javax.swing.JComboBox<Department> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<Semester> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JList<Student> studentJList;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        loadDepartments();
        loadSemesters();
        loadExamRecords();

    }

}
