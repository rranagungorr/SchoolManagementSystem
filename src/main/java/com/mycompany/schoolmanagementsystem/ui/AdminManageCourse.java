/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.DBUtil;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.FieldDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDAO;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Field;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.service.AdminService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class AdminManageCourse extends javax.swing.JPanel implements IPage {

    private DefaultTableModel tableModel;
    private AdminService adminService;
    private FieldDAO fieldDAO;

    public AdminManageCourse() {
        initComponents();
        this.adminService = new AdminService();
        this.fieldDAO = new FieldDAO();

        String[] columnNames = {"Course ID", "Course Name", "Course Code", "Credits", "Instructor", "Field"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make the table read-only
            }
        };

        coursesTable.setModel(tableModel);
        coursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadFields();
        loadInstructors();
        loadCourses();
    }

    private void loadFields() {
        fieldComboBox.removeAllItems();
        List<Field> fields = fieldDAO.getAll();
        DefaultComboBoxModel<Field> fieldModel = new DefaultComboBoxModel<>();

        for (Field f : fields) {
            fieldModel.addElement(f);
        }
        fieldComboBox.setModel(fieldModel);
    }

    private void loadInstructors() {
        instructorComboBox.removeAllItems();
        var instDAO = new InstructorDAO();
        List<Instructor> instructors = instDAO.getAll();

        DefaultComboBoxModel<Instructor> instructorModel = new DefaultComboBoxModel<>();
        for (Instructor i : instructors) {
            instructorModel.addElement(i);
        }
        instructorComboBox.setModel(instructorModel);
    }

    private void loadCourses() {
        tableModel.setRowCount(0);

        List<Course> allCourses = adminService.getAllCourses();
        for (Course c : allCourses) {
            String instructorName = getInstructorName(c.getInstructorID());
            String fieldName = getFieldName(c.getFieldID());

            Object[] rowData = {
                c.getCourseID(),
                c.getCourseName(),
                c.getCourseCode(),
                c.getCredits(),
                instructorName,
                fieldName
            };
            tableModel.addRow(rowData);
        }
    }

    private String getInstructorName(Integer instructorID) {
        var instDAO = new InstructorDAO();
        if (instructorID == null) {
            return "N/A";
        }
        Instructor instructor = instDAO.getByID(instructorID);
        return (instructor != null) ? instructor.getName() + " " + instructor.getSurname() : "Unknown";
    }

    private String getFieldName(Integer fieldID) {
        Field field = fieldDAO.getByID(fieldID);
        return (field != null) ? field.getFieldName() : "Unknown";
    }

    

    private void loadFieldsByDepartment(int departmentID) {
        fieldComboBox.removeAllItems(); // Önce temizle
        List<Field> fields = fieldDAO.getFieldsByDepartmentID(departmentID); // Veritabanından çek
        DefaultComboBoxModel<Field> fieldModel = new DefaultComboBoxModel<>();

        for (Field f : fields) {
            fieldModel.addElement(f); // Fieldları modele ekle
        }
        fieldComboBox.setModel(fieldModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        addCourse = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        courseNameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        instructorComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        creditsField = new javax.swing.JTextField();
        fieldComboBox = new javax.swing.JComboBox<>();
        deketeCourse = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        coursesTable = new javax.swing.JTable();

        jLabel2.setText("Field:");

        addCourse.setText("Add Course");
        addCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseActionPerformed(evt);
            }
        });

        jLabel4.setText("Instructor name:");

        jLabel6.setText("course name:");

        instructorComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                instructorComboBoxİtemStateChanged(evt);
            }
        });

        jLabel7.setText("credit:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(addCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 45, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(courseNameField))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(instructorComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(creditsField))))
                        .addGap(45, 45, 45))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instructorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creditsField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(153, 153, 153)
                .addComponent(addCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        deketeCourse.setText("Delete Course");
        deketeCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deketeCourseActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MANAGE COURSES");

        coursesTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(coursesTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(deketeCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deketeCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(86, 86, 86))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deketeCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deketeCourseActionPerformed
        // TODO add your handling code here:
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course from the table.");
            return;
        }

        int courseID = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete course ID " + courseID + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = adminService.deleteCourse(courseID);
            if (success) {
                JOptionPane.showMessageDialog(this, "Course deleted successfully!");
                loadCourses();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete course.");
            }
        }
    }//GEN-LAST:event_deketeCourseActionPerformed

    private void addCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCourseActionPerformed
        // TODO add your handling code here:
        // 1) Eğitmeni (Instructor) seçtik mi?
        Field selectedField = (Field) fieldComboBox.getSelectedItem();

        Instructor selectedInstructor = (Instructor) instructorComboBox.getSelectedItem();

        if (selectedInstructor == null || selectedInstructor.getDepartmentID() == null) {
            JOptionPane.showMessageDialog(this, "The selected instructor does not have a valid department. Please assign a department first.");
            return;
        }

        String courseName = courseNameField.getText().trim();
        String creditsStr = creditsField.getText().trim();

        // Alanların kontrolü
        if (selectedField == null || selectedInstructor == null || courseName.isEmpty() || creditsStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        int credits;
        try {
            credits = Integer.parseInt(creditsStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Credits must be a valid integer.");
            return;
        }

// Rastgele bir courseCode oluştur
        String courseCode = generateRandomCourseCode();

        int instructorID = selectedInstructor.getInstructorID();
        int fieldID = selectedField.getFieldID();

        int newCourseID = adminService.createCourse(courseName, courseCode, credits, fieldID, instructorID);
        if (newCourseID > 0) {
            JOptionPane.showMessageDialog(this, "Course added successfully with ID=" + newCourseID);
            loadCourses();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add course.");
        }


    }//GEN-LAST:event_addCourseActionPerformed

    private void instructorComboBoxİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_instructorComboBoxİtemStateChanged
        Instructor selectedInstructor = (Instructor) instructorComboBox.getSelectedItem();
        if (selectedInstructor != null) {
            Integer departmentID = selectedInstructor.getDepartmentID(); // Eğitmenin departmanı
            if (departmentID != null) {
                loadFieldsByDepartment(departmentID); // Departmana ait alanları yükle
            } else {
                JOptionPane.showMessageDialog(this, "Selected instructor has no department assigned.");
            }
        }
    }//GEN-LAST:event_instructorComboBoxİtemStateChanged

    private String generateRandomCourseCode() {
        // CODE + 4 haneli rastgele bir sayı (örnek: CODE1234)
        int random4Digit = (int) (Math.random() * 9000 + 1000); // 1000-9999 arasında rastgele sayı
        return "C" + random4Digit; // Toplam uzunluk: 5 karakter (örnek: C1234)
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCourse;
    private javax.swing.JTextField courseNameField;
    private javax.swing.JTable coursesTable;
    private javax.swing.JTextField creditsField;
    private javax.swing.JButton deketeCourse;
    private javax.swing.JComboBox<Field> fieldComboBox;
    private javax.swing.JComboBox<Instructor> instructorComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void onPageSetted() {
        loadFields();
        loadInstructors();
        loadCourses();
    }
}
