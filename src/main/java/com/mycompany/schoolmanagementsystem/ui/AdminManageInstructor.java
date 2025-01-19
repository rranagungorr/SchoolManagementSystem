/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDepartmentDAO;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.service.AdminService;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author PC
 */
public class AdminManageInstructor extends javax.swing.JPanel implements IPage {

    private InstructorDAO instructorDAO;
    private DepartmentDAO departmentDAO;
    private InstructorDepartmentDAO instructorDepartmentDAO;
    private DefaultTableModel tableModel;
    private DefaultTableModel instructorDepartmentTableModel;
      private AdminService adminService;

    public AdminManageInstructor() {
        initComponents();

        this.departmentDAO = new DepartmentDAO();
        this.instructorDAO = new InstructorDAO();
        this.instructorDepartmentDAO = new InstructorDepartmentDAO();
        this.adminService = new AdminService();

        JList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(JList);

        // Initialize the table model
        String[] columnNames = {"Instructor ID", "Name", "Surname", "Email", "Username"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells read-only
            }
        };
        instructorTable1.setModel(tableModel);
        loadInstructors();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        addInstructor = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        instructortNameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        surnameField = new javax.swing.JTextField();
        genderComboBox = new javax.swing.JComboBox<>();
        instructorUsernameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        deleteInstructor = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        updateInstructorjBut = new javax.swing.JButton();
        RefreshjBut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        instructorDepartmentTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        instructorTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        addDepartmentjBut = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(253, 253, 236));

        jPanel2.setBackground(new java.awt.Color(234, 234, 253));

        addInstructor.setIcon(new javax.swing.ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\plus20.png")); // NOI18N
        addInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInstructorActionPerformed(evt);
            }
        });

        jLabel4.setText("Name:");

        jLabel6.setText("Surname:");

        jLabel3.setText("Username:");

        jLabel7.setText("Password:");

        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jLabel8.setText("Gender:");

        deleteInstructor.setIcon(new javax.swing.ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\delete.png")); // NOI18N
        deleteInstructor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteInstructorActionPerformed(evt);
            }
        });

        updateInstructorjBut.setIcon(new javax.swing.ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\edit.png")); // NOI18N
        updateInstructorjBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateInstructorjButActionPerformed(evt);
            }
        });

        RefreshjBut.setIcon(new javax.swing.ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\refresh-button.png")); // NOI18N
        RefreshjBut.setBorder(null);
        RefreshjBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshjButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RefreshjBut, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(genderComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(addInstructor, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(updateInstructorjBut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(deleteInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPasswordField1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(instructorUsernameField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(surnameField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(instructortNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instructortNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(instructorUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateInstructorjBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addInstructor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(deleteInstructor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RefreshjBut, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MANAGE INSTRUCTOR");

        instructorDepartmentTable.setBackground(new java.awt.Color(234, 234, 253));
        instructorDepartmentTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(instructorDepartmentTable);

        instructorTable1.setBackground(new java.awt.Color(234, 234, 253));
        instructorTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        instructorTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instructorTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(instructorTable1);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setText("Instructors");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Departments Belong to Instructors");

        jPanel3.setBackground(new java.awt.Color(234, 234, 253));

        jScrollPane2.setViewportView(JList);

        jLabel2.setText("Department:");

        addDepartmentjBut.setText("Add Department");
        addDepartmentjBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDepartmentjButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(224, Short.MAX_VALUE)
                        .addComponent(addDepartmentjBut))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addDepartmentjBut)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Departmanları yüklemek için düzenleme
    // Departmanları yüklemek için düzenlenmiş metod
    private void loadDepartments() {
        DefaultListModel<Department> departmentModel = new DefaultListModel<>();
        List<Department> departments = departmentDAO.getAll();
        setupInstructorDepartmentTable();

        for (Department dept : departments) {
            departmentModel.addElement(dept);
        }

        JList.setModel(departmentModel); // Modeli JList'e bağlayın
        JList.repaint(); // Görüntüyü yenileyin
    }

    private void setupInstructorDepartmentTable() {
        String[] columnNames = {"Department ID", "Department Name"};
        instructorDepartmentTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hücre düzenlenemez
            }
        };
        instructorDepartmentTable.setModel(instructorDepartmentTableModel);
    }

    private void loadInstructors() {
        List<Instructor> instructors = instructorDAO.getAll();

        // Clear any previous rows in the table
        tableModel.setRowCount(0);

        // Add rows for each instructor
        for (Instructor instructor : instructors) {
            Object[] rowData = {
                instructor.getInstructorID(),
                instructor.getName(),
                instructor.getSurname(),
                instructor.getEmail(), // Email sütunu ekleniyor
                instructor.getUsername()
            };
            tableModel.addRow(rowData);
        }
    }

    private void loadInstructorDepartments(int instructorID) {
        List<Department> departments = instructorDepartmentDAO.getDepartmentsByInstructor(instructorID);

        // Tablodaki önceki verileri temizle
        instructorDepartmentTableModel.setRowCount(0);

        // Departmanları tabloya ekle
        for (Department dept : departments) {
            Object[] rowData = {
                dept.getDepartmentID(),
                dept.getDepartmentName()
            };
            instructorDepartmentTableModel.addRow(rowData);
        }
    }

    private void assignDepartmentsToInstructorActionPerformed() {
        // Tablodan seçili öğretmeni al
        int selectedRow = instructorTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an instructor.");
            return;
        }

        // Instructor ID'yi al
        int instructorID = (int) tableModel.getValueAt(selectedRow, 0);

        // Listeden seçili departmanları al
        List<Department> selectedDepartments = JList.getSelectedValuesList();
        if (selectedDepartments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one department.");
            return;
        }

        for (Department dept : selectedDepartments) {
            if (instructorDepartmentDAO.isDepartmentAlreadyAssigned(instructorID, dept.getDepartmentID())) {
                JOptionPane.showMessageDialog(this, "Department '" + dept.getDepartmentName() + "' is already assigned to this instructor.");
                return;
            }
            if (!instructorDepartmentDAO.canAssignMoreDepartments(instructorID)) {
                JOptionPane.showMessageDialog(this, "An instructor cannot have more than 3 departments.");
                return;
            }
            instructorDepartmentDAO.assignDepartmentToInstructor(instructorID, dept.getDepartmentID());
        }

        JOptionPane.showMessageDialog(this, "Departments assigned successfully!");
        loadInstructorDepartments(instructorID);
    }


    private void addInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInstructorActionPerformed
        String name = instructortNameField.getText().trim();
        String surname = surnameField.getText().trim();
        String username = instructorUsernameField.getText().trim();
        String password = jPasswordField1.getText().trim();
        String selectedGender = genderComboBox.getSelectedItem().toString();

        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Kullanıcı adı kontrolü
        if (instructorDAO.usernameExists(username)) {
            JOptionPane.showMessageDialog(this, "This username is already taken. Please choose a different one.");
            return;
        }

        // Şifre kontrolü
        if (instructorDAO.passwordExists(password)) {
            JOptionPane.showMessageDialog(this, "This password is already in use. Please choose a different one.");
            return;
        }

        // Yeni instructor ekleme işlemi
        String email = instructorDAO.generateEmail(name, surname);
        Instructor instructor = new Instructor();
        instructor.setName(name);
        instructor.setSurname(surname);
        instructor.setUsername(username);
        instructor.setPassword(password);
        instructor.setEmail(email);
        instructor.setGender(selectedGender);

        int instructorID = instructorDAO.create(instructor);

        if (instructorID > 0) {
            JOptionPane.showMessageDialog(this, "Instructor added successfully!");
            loadInstructors(); // Tabloyu güncelle
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add instructor.");
        }
    }//GEN-LAST:event_addInstructorActionPerformed

    private void deleteInstructorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteInstructorActionPerformed
        int selectedRow = instructorTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an instructor to delete.");
            return;
        }

// Get the selected instructor ID
        int instructorID = (int) tableModel.getValueAt(selectedRow, 0);

// Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete Instructor ID " + instructorID + "? This will nullify references in Courses, delete Exams and CourseInstructors records.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = adminService.deleteInstructorWithDependencies(instructorID);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Instructor deleted successfully. References updated.");
                    loadInstructors(); // Tabloyu güncelle
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete instructor. Please try again.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage());
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_deleteInstructorActionPerformed

    private void instructorTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instructorTable1MouseClicked

        int selectedRow = instructorTable1.getSelectedRow();
        if (selectedRow != -1) {
            // Seçilen satırdaki öğretmen ID'sini al
            int instructorID = (int) tableModel.getValueAt(selectedRow, 0);

            // Veritabanından öğretmen bilgilerini al
            Instructor instructor = instructorDAO.getByID(instructorID);

            // TextField'lara bilgileri yaz
            instructortNameField.setText(instructor.getName());
            surnameField.setText(instructor.getSurname());
            instructorUsernameField.setText(instructor.getUsername());
            jPasswordField1.setText(instructor.getPassword());
            genderComboBox.setSelectedItem(instructor.getGender());

            // Öğretmenin departmanlarını yükle (varsa)
            loadInstructorDepartments(instructorID);
        }

    }//GEN-LAST:event_instructorTable1MouseClicked

    private void addDepartmentjButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDepartmentjButActionPerformed
        assignDepartmentsToInstructorActionPerformed();
    }//GEN-LAST:event_addDepartmentjButActionPerformed

    private void updateInstructorjButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateInstructorjButActionPerformed
        int selectedRow = instructorTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an instructor to update.");
            return;
        }

        // Seçili öğretmenin ID'sini alın
        int instructorID = (int) tableModel.getValueAt(selectedRow, 0);

        // TextField'lardan yeni bilgileri alın
        String name = instructortNameField.getText().trim();
        String surname = surnameField.getText().trim();
        String username = instructorUsernameField.getText().trim();
        String password = jPasswordField1.getText().trim();
        String gender = (String) genderComboBox.getSelectedItem();

        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        // Kullanıcı adı kontrolü (diğer kayıtlar için)
        if (instructorDAO.usernameExistsForOtherID(username, instructorID)) {
            JOptionPane.showMessageDialog(this, "This username is already taken by another instructor. Please choose a different one.");
            return;
        }

        // E-posta adresini yeniden oluştur
        String email = instructorDAO.generateEmail(name, surname);

        // Instructor objesini güncelle
        Instructor instructor = new Instructor();
        instructor.setInstructorID(instructorID);
        instructor.setName(name);
        instructor.setSurname(surname);
        instructor.setUsername(username);
        instructor.setEmail(email); // Otomatik oluşturulan e-posta ayarlanıyor
        instructor.setPassword(password);
        instructor.setGender(gender);

        boolean success = instructorDAO.update(instructor);

        if (success) {
            JOptionPane.showMessageDialog(this, "Instructor updated successfully!");
            loadInstructors(); // Tabloyu güncelle
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update instructor.");
        }

    }//GEN-LAST:event_updateInstructorjButActionPerformed

    private void RefreshjButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshjButActionPerformed
        // Tüm TextField'ları ve ComboBox'u temizle
        instructortNameField.setText("");
        surnameField.setText("");
        instructorUsernameField.setText("");
        jPasswordField1.setText("");
        genderComboBox.setSelectedIndex(0); // Default seçili değer (örneğin "Male")

        // Departman tablosunu temizle
        instructorDepartmentTableModel.setRowCount(0);

        // Herhangi bir seçim kaldırılır
        instructorTable1.clearSelection();
    }//GEN-LAST:event_RefreshjButActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Department> JList;
    private javax.swing.JButton RefreshjBut;
    private javax.swing.JButton addDepartmentjBut;
    private javax.swing.JButton addInstructor;
    private javax.swing.JButton deleteInstructor;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JTable instructorDepartmentTable;
    private javax.swing.JTable instructorTable1;
    private javax.swing.JTextField instructorUsernameField;
    private javax.swing.JTextField instructortNameField;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField surnameField;
    private javax.swing.JButton updateInstructorjBut;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        loadDepartments();
        loadInstructors();

    }
}
