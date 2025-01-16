/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.CreditLimitDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.SemesterDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentDAO;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Semester;
import com.mycompany.schoolmanagementsystem.management.Student;
import com.mycompany.schoolmanagementsystem.service.AdminService;
import com.mycompany.schoolmanagementsystem.service.StudentService;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Merve
 */
public class AdminManageStudent extends javax.swing.JPanel implements IPage {

    private DefaultListModel<Department> listModel;
    private AdminService adminService;
    private StudentService studentService;
    private DefaultTableModel tableModel;
    private DepartmentDAO departmentDAO;
    private StudentDAO studentDAO;
    private CreditLimitDAO creditLimitDAO;

    public AdminManageStudent() {
        initComponents();

        listModel = new DefaultListModel<>();
        this.departmentDAO = new DepartmentDAO();
        departmentJList.setModel(listModel);
        departmentJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.adminService = new AdminService();
        this.studentService = new StudentService();
        this.studentDAO = new StudentDAO();
        this.creditLimitDAO = new CreditLimitDAO();

        String[] columnNames = {"ID", "Name", "Surname", "Credits", "Department"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells read-only
            }
        };
        studentsTable.setModel(tableModel);
        studentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadStudents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        departmentJList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        addStudent = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        surnameField = new javax.swing.JTextField();
        classComboBox = new javax.swing.JComboBox<>();
        genderComboBox = new javax.swing.JComboBox<>();
        deleteCourse = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        semesterComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        generalAverageComboBox = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentsTable = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(253, 253, 236));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 750));

        jScrollPane2.setViewportView(departmentJList);

        jLabel2.setText("Department:");

        addStudent.setText("Add Student");
        addStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentActionPerformed(evt);
            }
        });

        jLabel4.setText("Name:");

        jLabel6.setText("Surname:");

        jLabel3.setText("Class Level:");

        jLabel7.setText("Gender:");

        classComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        deleteCourse.setText("Delete Student");
        deleteCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCourseActionPerformed(evt);
            }
        });

        jLabel8.setText("Semester:");

        jLabel9.setText("General Average:");

        jLabel10.setText("Password:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(classComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(semesterComboBox, 0, 154, Short.MAX_VALUE)
                            .addComponent(surnameField)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(generalAverageComboBox, 0, 154, Short.MAX_VALUE)
                                    .addComponent(genderComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generalAverageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(addStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteCourse)
                .addGap(27, 27, 27))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MANAGE STUDENTS");

        studentsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(studentsTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadSemesters() {
        SemesterDAO semesterDAO = new SemesterDAO();
        List<Semester> semesters = semesterDAO.getAll();
        DefaultComboBoxModel<Semester> semesterModel = new DefaultComboBoxModel<>();
        for (Semester s : semesters) {
            semesterModel.addElement(s);
        }
        semesterComboBox.setModel(semesterModel);
    }

    private void loadDepartments() {

        List<Department> departmentList = departmentDAO.getAll();
        listModel.clear();
        for (Department dept : departmentList) {
            listModel.addElement(dept);
        }
    }

    private void loadStudents() {
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new String[]{
            "ID", "Name", "Surname", "Credits", "ClassLevel", "Username", "Department"
        });

        List<Student> students = studentService.getAllStudents();

        // 4) Her öğrenci için bir satır oluştur
        for (Student s : students) {
            String deptName = "N/A";

            Department d = departmentDAO.getByID(s.getDepartmentID());
            deptName = d.getDepartmentName();

            // 5) classLevel ve username ekledik
            Object[] rowData = {
                s.getStudentID(),
                s.getName(),
                s.getSurname(),
                s.getCredits(),
                s.getClassLevel(),
                s.getUsername(),
                deptName
            };
            tableModel.addRow(rowData);
        }
    }

    private void populateGeneralAverageComboBox() {
        DefaultComboBoxModel<Double> model = new DefaultComboBoxModel<>();
        for (double i = 0.0; i <= 4.0; i += 0.1) {
            model.addElement(Math.round(i * 10.0) / 10.0); // Ondalık değeri 1 basamağa yuvarla
        }
        generalAverageComboBox.setModel(model);
    }

    private String getDeptName(int deptID) {
        Department d = departmentDAO.getByID(deptID);
        return d != null ? d.getDepartmentName() : "Unknown";
    }
    
   

    private void addStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentActionPerformed

        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String classLevel = (String) classComboBox.getSelectedItem();
        Semester selectedSemester = (Semester) semesterComboBox.getSelectedItem();
        int semesterID = selectedSemester.getSemesterID();
        String gender = (String) genderComboBox.getSelectedItem();
        String password = jPasswordField1.getText();

        // Department bilgisini JList'ten alıyoruz
        Department selectedDepartment = departmentJList.getSelectedValue();
        if (selectedDepartment == null) {
            JOptionPane.showMessageDialog(this, "Please select a department from the list!");
            return;
        }
        int departmentID = selectedDepartment.getDepartmentID();

        // General Average bilgisini JComboBox'tan alıyoruz
        Double generalAverage = (Double) generalAverageComboBox.getSelectedItem();

        // Kontrol: Alanlar boş olamaz
        if (name.isEmpty() || surname.isEmpty() || classLevel == null || selectedSemester == null || gender == null || generalAverage == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            return;
        }

        // E-posta, Username ve Şifre oluştur
        String baseEmail = name.toLowerCase() + "." + surname.toLowerCase() + "@stu.fsm.edu.tr";
        String baseUsername = name.toLowerCase() + "." + surname.toLowerCase();
        String email = baseEmail;
        String username = baseUsername;

        // Kontrol: Email ve Username benzersiz hale getirme
        int counter = 1;
        while (studentDAO.emailExists(email)) {
            email = email + "_" + counter;
            counter++;
        }

        counter = 1;
        while (studentDAO.usernameExists(username)) {
            username = baseUsername + "_" + counter;
            counter++;
        }
        
        // Şifre kontrolü
        if (studentDAO.passwordExists(password)) {
            JOptionPane.showMessageDialog(this, "This password is already in use. Please choose a different one.");
            return;
        }

        int classLevelInt = Integer.parseInt(classLevel);

        // Credit Limiti belirle
        int maxCredit = creditLimitDAO.getMaxCredit(classLevelInt, semesterID);

        // Yeni bir Student nesnesi oluştur ve gerekli alanları doldur
        Student s = new Student();
        s.setName(name);
        s.setSurname(surname);
        s.setCredits(maxCredit);
        s.setClassLevel(classLevelInt);
        s.setGender(gender);
        s.setEmail(email); // Basit e-posta
        s.setUsername(username); // Kullanıcı adı
        s.setPassword(password);
        s.setDepartmentID(departmentID);
        s.setSemesterID(semesterID); // Kullanıcı adı
        s.setGeneralAverage(generalAverage);

        int studentID = studentDAO.create(s);

        if (studentID > 0) {
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            loadStudents(); // Tabloyu güncelle
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add student.");
        }
    }//GEN-LAST:event_addStudentActionPerformed

    private void deleteCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCourseActionPerformed
        // TODO add your handling code here:
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table.");
            return;
        }

        // Get the student ID from the table's first column
        int studentID = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete student ID " + studentID + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = adminService.deleteStudent(studentID);
            if (success) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete student.");
            }
        }
    }//GEN-LAST:event_deleteCourseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudent;
    private javax.swing.JComboBox<String> classComboBox;
    private javax.swing.JButton deleteCourse;
    private javax.swing.JList<Department> departmentJList;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JComboBox<Double> generalAverageComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameField;
    private javax.swing.JComboBox<Semester> semesterComboBox;
    private javax.swing.JTable studentsTable;
    private javax.swing.JTextField surnameField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        loadDepartments();
        loadSemesters();
        loadStudents();
        populateGeneralAverageComboBox();
    }

}
