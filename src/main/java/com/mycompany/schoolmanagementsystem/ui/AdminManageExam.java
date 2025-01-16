/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.ClassroomDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.SemesterDAO;
import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.management.Classroom;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Semester;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class AdminManageExam extends javax.swing.JPanel implements IPage {

    private CourseDAO courseDAO;
    private SemesterDAO semesterDAO;
    private ClassroomDAO classroomDAO;
    private InstructorDAO instructorDAO;
    private DepartmentDAO departmentDAO;

    public AdminManageExam() {
        initComponents();
        this.courseDAO = new CourseDAO();
        this.semesterDAO = new SemesterDAO();
        this.classroomDAO = new ClassroomDAO();
        this.instructorDAO = new InstructorDAO();
        this.departmentDAO = new DepartmentDAO();
        loadExamsIntoTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        coursejList1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(253, 253, 236));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MANAGE EXAM");

        jTable1.setBackground(new java.awt.Color(217, 224, 245));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(217, 224, 245));

        jScrollPane2.setViewportView(coursejList1);

        jLabel2.setText("Course:");

        jLabel3.setText("Date:");

        jButton1.setText("Add Exam");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Instructor:");

        jLabel5.setText("Classroom:");

        jLabel6.setText("Exam Type:");

        jButton2.setText("Cancel Exam");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jLabel7.setText("Department");

        jLabel8.setText("ClassLevel");

        jLabel9.setText("Semester");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Midterm", "Final" }));

        jButton3.setText("Query");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, 0, 144, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                    .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jButton3)
                        .addGap(53, 53, 53))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadFilteredCourses(int departmentID, String classLevel, int semesterID) {
        // Dersleri filtrele ve JList'e ekle
        List<Course> filteredCourses = courseDAO.getCoursesByDepartmentClassLevelAndSemester(departmentID, classLevel, semesterID);

        DefaultListModel<Course> listModel = new DefaultListModel<>();
        for (Course course : filteredCourses) {
            listModel.addElement(course); // Course nesnesini direkt ekliyoruz
        }

        // Filtrelenmiş dersleri JList'e ayarla
        coursejList1.setModel(listModel);
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

    private void loadInstructors() {
        jComboBox1.removeAllItems();
        List<Instructor> instructors = instructorDAO.getAll();
        DefaultComboBoxModel<Instructor> instructorModel = new DefaultComboBoxModel<>();
        for (Instructor i : instructors) {
            instructorModel.addElement(i);
        }
        jComboBox1.setModel(instructorModel);
    }

    private void loadSemesters() {
        List<Semester> semesters = semesterDAO.getAll();
        DefaultComboBoxModel<Semester> semesterModel = new DefaultComboBoxModel<>();
        for (Semester s : semesters) {
            semesterModel.addElement(s);
        }
        jComboBox5.setModel(semesterModel);
    }

    private void loadClassrooms() {
        jComboBox2.removeAllItems();
        List<Classroom> classrooms = classroomDAO.getAll();
        DefaultComboBoxModel<Classroom> classroomModel = new DefaultComboBoxModel<>();
        for (Classroom c : classrooms) {
            classroomModel.addElement(c);
        }
        jComboBox2.setModel(classroomModel);
    }

    private boolean validateDateRange(java.util.Date selectedDate, String startDateStr, String endDateStr) {
        java.util.Date startDate = java.sql.Date.valueOf(startDateStr);
        java.util.Date endDate = java.sql.Date.valueOf(endDateStr);

        if (selectedDate.before(startDate) || selectedDate.after(endDate)) {
            JOptionPane.showMessageDialog(this,
                    "Selected date is out of range for this exam type!",
                    "Invalid Date", JOptionPane.WARNING_MESSAGE);
            jDateChooser1.setDate(null); // Tarihi sıfırla
            return false; // Geçersiz tarih
        }
        return true; // Geçerli tarih
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addExam();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cancelExam();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Seçili değerleri al
        Department selectedDepartment = (Department) jComboBox3.getSelectedItem();
        String selectedClassLevel = (String) jComboBox4.getSelectedItem();
        Semester selectedSemester = (Semester) jComboBox5.getSelectedItem();

        // Kontroller
        if (selectedDepartment == null || selectedClassLevel == null || selectedSemester == null) {
            JOptionPane.showMessageDialog(this, "Please select all fields: Department, Class Level, and Semester.");
            return;
        }

        // Filtrelenmiş dersleri yükle
        loadFilteredCourses(selectedDepartment.getDepartmentID(), selectedClassLevel, selectedSemester.getSemesterID());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cancelExam() {
        // 1. Seçilen satırın kontrolü
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this,
                    "Please select an exam from the table to cancel or delete!",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Seçilen satırdan Exam ID'yi al
        int examID;
        try {
            examID = (int) jTable1.getValueAt(selectedRow, 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to retrieve Exam ID. Please ensure the table is configured correctly.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Kullanıcıdan onay al
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete Exam ID " + examID + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) {
            // Eğer kullanıcı "Hayır" derse işlemi sonlandır
            return;
        }

        // 4. ExamDAO kullanarak sınavı sil
        ExamDAO examDAO = new ExamDAO();
        try {
            boolean deleted = examDAO.delete(examID);
            if (deleted) {
                JOptionPane.showMessageDialog(this,
                        "Exam ID " + examID + " has been successfully canceled!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to cancel Exam ID " + examID + ". It might already be deleted or does not exist.",
                        "Deletion Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "An error occurred while deleting Exam ID " + examID + ": " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // 5. Tabloyu yenile
        loadExamsIntoTable();
    }

    private void addExam() {
        try {
            int selectedIndex = coursejList1.getSelectedIndex(); // Seçili satırın indeksini al
            if (selectedIndex < 0) {
                System.out.println("No course selected.");
                JOptionPane.showMessageDialog(this, "Please select a course from the list.");
                return;
            }

            Course selectedCourse = coursejList1.getSelectedValue(); // Seçili öğeyi al

            // Invigilator (Instructor) ve Classroom seçimini kontrol et
            Instructor selectedInstructor = (Instructor) jComboBox1.getSelectedItem();
            Classroom selectedClassroom = (Classroom) jComboBox2.getSelectedItem();

            if (selectedInstructor == null || selectedClassroom == null) {
                JOptionPane.showMessageDialog(this, "Please select both an instructor and a classroom.");
                return;
            }

            // Exam türü ve tarih doğrulaması
            String examType = (String) jComboBox6.getSelectedItem();
            java.util.Date selectedDate = jDateChooser1.getDate();

            if (examType == null || selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Please select an exam type and a valid date.");
                return;
            }

            // Tarih aralığı kontrolü
            if (examType.equals("Midterm")) {
                if (!validateDateRange(selectedDate, "2024-11-04", "2025-01-03")) {
                    return; // Geçersiz tarih, işlem durduruluyor
                }
            } else if (examType.equals("Final")) {
                if (!validateDateRange(selectedDate, "2025-01-13", "2025-01-25")) {
                    return; // Geçersiz tarih, işlem durduruluyor
                }
            }

            // Exam adını oluştur
            String examName = selectedCourse.getCourseName() + " - " + examType;

            // Sınavın zaten var olup olmadığını kontrol et
            ExamDAO examDAO = new ExamDAO();
            boolean exists = examDAO.isExamExists(selectedCourse.getCourseID(), selectedDate, selectedClassroom.getClassroomID());
            if (exists) {
                JOptionPane.showMessageDialog(this, "This exam already exists!");
                return; // Sınav zaten mevcut, işlem durduruluyor
            }

            // Yeni Exam oluştur ve veritabanına ekle
            Exam newExam = new Exam();
            newExam.setExamName(examName);
            newExam.setExamDate(new java.sql.Date(selectedDate.getTime())); // java.sql.Date'e dönüştür
            newExam.setCourseID(selectedCourse.getCourseID());
            newExam.setInvigilatorID(selectedInstructor.getInstructorID());
            newExam.setClassroomID(selectedClassroom.getClassroomID());

            int generatedID = examDAO.create(newExam);

            if (generatedID > 0) {
                JOptionPane.showMessageDialog(this, "Exam added successfully! ID: " + generatedID);
                loadExamsIntoTable(); // Tabloda yeni sınavı göster
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add exam.");
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format: " + ex.getMessage());
        }
    }

    private void loadExamsIntoTable() {
        ExamDAO examDAO = new ExamDAO();
        List<Exam> examList = examDAO.getAll();

        String[] columnNames = {"Exam ID", "Exam Name", "Exam Date", "Course Name", "Instructor", "Classroom"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Exam exam : examList) {
            String courseName = courseDAO.getByID(exam.getCourseID()).getCourseName();
            String instructorName = instructorDAO.getByID(exam.getInvigilatorID()).getName();
            String classroomName = classroomDAO.getByID(exam.getClassroomID()).getClassroomName();

            Object[] rowData = {
                exam.getExamID(),
                exam.getExamName(),
                exam.getExamDate(),
                courseName,
                instructorName,
                classroomName
            };
            tableModel.addRow(rowData);
        }

        jTable1.setModel(tableModel);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Course> coursejList1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<Instructor> jComboBox1;
    private javax.swing.JComboBox<Classroom> jComboBox2;
    private javax.swing.JComboBox<Department> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<Semester> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        loadDepartments();
        loadSemesters();
        loadInstructors();
        loadClassrooms();
        loadExamsIntoTable();

    }

}
