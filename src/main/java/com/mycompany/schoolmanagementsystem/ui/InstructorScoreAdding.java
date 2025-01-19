/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ExamResultDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentExamDAO;
import com.mycompany.schoolmanagementsystem.examsys.Exam;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Student;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Merve
 */
public class InstructorScoreAdding extends javax.swing.JPanel implements IPage{

   
    private CourseDAO courseDAO;
    private StudentExamDAO studentExamDAO;
    private ExamResultDAO examResultDAO;
    private ExamDAO examDAO;

    int instructorID;

    public InstructorScoreAdding() {
        initComponents();
         this.courseDAO = new CourseDAO();
        this.studentExamDAO = new StudentExamDAO();
        this.examResultDAO = new ExamResultDAO();
        this.examDAO = new ExamDAO();
        loadCourses();
    }
    
     private void loadCourses() {
        List<Course> courses = courseDAO.getCoursesByInstructorID(instructorID);
        DefaultTableModel courseModel = (DefaultTableModel) courseTable.getModel();
        courseModel.setRowCount(0);
        for (Course course : courses) {
            courseModel.addRow(new Object[]{course.getCourseID(), course.getCourseName()});
        }
    }

    private void loadStudentsForSelectedCourse(int courseID) {
        List<Student> students = studentExamDAO.getStudentsByCourseID(courseID);
        DefaultTableModel studentModel = (DefaultTableModel) studentTable.getModel();
        studentModel.setRowCount(0);
        for (Student student : students) {
            studentModel.addRow(new Object[]{student.getStudentID(), student.getName(), student.getSurname()});
        }
    }
    private void loadExamsForSelectedCourse(int courseID) {
    List<Exam> exams = examDAO.getExamsByCourseID(courseID);
    DefaultTableModel examModel = (DefaultTableModel) examTable.getModel();
    examModel.setRowCount(0);
    for (Exam exam : exams) {
        examModel.addRow(new Object[]{exam.getExamID(), exam.getExamName(), exam.getExamDate()});
    }
}

private void loadStudentsForSelectedExam(int examID) {
    List<Student> students = studentExamDAO.getStudentsByExamID(examID);
    DefaultTableModel studentModel = (DefaultTableModel) studentTable.getModel();
    studentModel.setRowCount(0);
    for (Student student : students) {
        studentModel.addRow(new Object[]{student.getStudentID(), student.getName(), student.getSurname()});
    }
}


    private void assignScore() {
        int selectedCourseRow = courseTable.getSelectedRow();
        if (selectedCourseRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.");
            return;
        }

        int selectedStudentRow = studentTable.getSelectedRow();
        if (selectedStudentRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student.");
            return;
        }

        String scoreText = scoreTextField.getText();
        int score;
        try {
            score = Integer.parseInt(scoreText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Score must be a valid number.");
            return;
        }

        if (score < 0 || score > 100) {
            JOptionPane.showMessageDialog(this, "Score must be between 0 and 100.");
            return;
        }

        int studentID = (int) studentTable.getValueAt(selectedStudentRow, 0);
        int courseID = (int) courseTable.getValueAt(selectedCourseRow, 0);
        int examID = studentExamDAO.getExamIDByCourseAndStudent(courseID, studentID);

       
        boolean success = examResultDAO.assignScore(studentID, examID, score);
        if (success) {
            JOptionPane.showMessageDialog(this, "Score assigned successfully.");
            scoreTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to assign score.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        scoreTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        examTable = new javax.swing.JTable();

        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "course", "", ""
            }
        ));
        courseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                courseTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(courseTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Score Record");

        jButton1.setText("add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "name", "surname", ""
            }
        ));
        jScrollPane2.setViewportView(studentTable);

        jLabel2.setText("Score:");

        examTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "exam", "date", ""
            }
        ));
        examTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                examTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(examTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scoreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 894, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 637, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      assignScore();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void courseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseTableMouseClicked
         int selectedRow = courseTable.getSelectedRow();
        if (selectedRow != -1) {
            int courseID = (int) courseTable.getValueAt(selectedRow, 0);
            loadExamsForSelectedCourse(courseID);
        }
    }//GEN-LAST:event_courseTableMouseClicked

    private void examTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_examTableMouseClicked
        int selectedRow = examTable.getSelectedRow();
    if (selectedRow != -1) {
        int examID = (int) examTable.getValueAt(selectedRow, 0); // ExamID'nin doğru alındığını kontrol edin
loadStudentsForSelectedExam(examID); // Doğru ExamID'yi fonksiyona gönderin
    }
    }//GEN-LAST:event_examTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable courseTable;
    private javax.swing.JTable examTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField scoreTextField;
    private javax.swing.JTable studentTable;
    // End of variables declaration//GEN-END:variables
  
    @Override
    public void onPageSetted() {
        Object account = MainFrame.instance.getAccount();
        if (account instanceof Instructor instructor) {
            this.instructorID = instructor.getInstructorID();
            loadCourses();
        }
    }



}
