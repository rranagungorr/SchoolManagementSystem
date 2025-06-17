package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.ClassroomDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseScheduleDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.SemesterDAO;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Semester;
import com.mycompany.schoolmanagementsystem.management.Student;
import com.mycompany.schoolmanagementsystem.service.StudentService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class StudentCourseAdding extends javax.swing.JPanel implements IPage {

    private DefaultTableModel tableModel;
    private StudentService studentService;
    private DepartmentDAO departmentDAO;
    private ClassroomDAO classroomDAO;
    private CourseDAO courseDAO;
    private SemesterDAO semesterDAO;
    private CourseScheduleDAO courseScheduleDAO;
    int studentID;
    int classlevel;
    int departmentID;
    int semesterID;
    int studentCretid;

    public StudentCourseAdding() {
        initComponents();

        this.studentService = new StudentService();
        this.departmentDAO = new DepartmentDAO();
        this.classroomDAO = new ClassroomDAO();
        this.semesterDAO = new SemesterDAO();
        this.courseScheduleDAO = new CourseScheduleDAO();
        this.courseDAO = new CourseDAO();

        String[] columnNames = {"Course ID", "Course Name", "Credit", "Hours", "WeekDay", "Start Time", "End Time"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Table is read-only
            }
        };
        courseTable.setModel(tableModel);
        courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void loadFilteredCourses(int departmentID, String classLevel, int semesterID) {
        List<Course> filteredCourses = courseDAO.getCoursesByDepartmentClassLevelAndSemester(departmentID, classLevel, semesterID);

        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        model.setRowCount(0); // Mevcut verileri temizle

        if (filteredCourses == null || filteredCourses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No courses found for the selected filters.");
            return;
        }

        for (Course course : filteredCourses) {

            // Course ID kontrolü kaldırıldı çünkü int türü null olamaz
            tableModel.addRow(new Object[]{
                course.getCourseID(),
                course.getCourseName(),
                course.getCredits(),
                course.getHours(),
                course.getWeekDay(),
                course.getStartTime(),
                course.getEndTime()
            });
        }

    }

    private void loadSemesters() {
        List<Semester> semesters = semesterDAO.getAll();
        DefaultComboBoxModel<Semester> semesterModel = new DefaultComboBoxModel<>();
        for (Semester s : semesters) {
            semesterModel.addElement(s);
        }
        jComboBox5.setModel(semesterModel);
    }

    private int calculateTotalCredits() {
        DefaultTableModel studentCourseModel = (DefaultTableModel) studentCourseTable.getModel();
        int totalCredits = 0;

        for (int i = 0; i < studentCourseModel.getRowCount(); i++) {
            Object creditObj = studentCourseModel.getValueAt(i, 2); // 2. sütundaki kredi bilgisi
            System.out.println("Row " + i + ", Credit Value: " + creditObj);
            if (creditObj == null || !(creditObj instanceof Integer)) {
                System.err.println("Invalid credit value at row " + i + ". Skipping...");
                continue; // Eğer kredi bilgisi null veya geçersizse atla
            }
            totalCredits += (int) creditObj;
        }

        return totalCredits;
    }

    private void addCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.");
            return;
        }

        int courseID = (int) courseTable.getValueAt(selectedRow, 0);
        Course selectedCourse = courseDAO.getByID(courseID);
        if (selectedCourse == null) {
            JOptionPane.showMessageDialog(this, "Course details not found.");
            return;
        }

        List<Course> takenCourses = getTakenCoursesFromTable();
        List<Course> mandatoryCourses = courseDAO.getMandatoryCourses(departmentID, classlevel, semesterID);

        if (studentService.isCourseAlreadyTaken(courseID, takenCourses)) {
            JOptionPane.showMessageDialog(this, "This course has already been selected.");
            return;
        }

        // Seçilen dersin sınıf, fakülte ve dönem bilgisi
        int courseClassLevel = selectedCourse.getClassLevel();
        int courseDepartmentID = selectedCourse.getDepartmentID();
        int courseSemesterID = selectedCourse.getSemesterID();

        // Öğrencinin kendi sınıfı, fakültesi ve dönemi
        int studentClassLevel = classlevel;
        int studentDepartmentID = departmentID;
        int studentSemesterID = semesterID;

        if (courseClassLevel != studentClassLevel
                || courseDepartmentID != studentDepartmentID
                || courseSemesterID != studentSemesterID) {

            if (!studentService.isMandatoryCoursesCompleted(mandatoryCourses, takenCourses)) {
                JOptionPane.showMessageDialog(this,
                        "You must first take all mandatory courses from your department, class level, and semester.");
                return;
            }
        }

        if (!studentService.isCreditWithinLimit(takenCourses, selectedCourse, studentCretid)) {
            JOptionPane.showMessageDialog(this, "Credit limit exceeded.");
            return;
        }

        // 4. Ekleme işlemi
        ((DefaultTableModel) studentCourseTable.getModel()).addRow(new Object[]{
            selectedCourse.getCourseID(),
            selectedCourse.getCourseName(),
            selectedCourse.getCredits()
        });

        List<Course> takenCoursesUpdate = getTakenCoursesFromTable();  // tabloyu okuyor
        int totalCredits = studentService.calculateTotalCredits(takenCoursesUpdate);
        jTextField3.setText(String.valueOf(totalCredits));
        JOptionPane.showMessageDialog(this, "Course added successfully.");

    }

    private List<Course> getTakenCoursesFromTable() {
        List<Course> list = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) studentCourseTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int id = (int) model.getValueAt(i, 0);
            Course c = courseDAO.getByID(id);
            if (c != null) {
                list.add(c);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButQuery = new javax.swing.JButton();
        jButSave = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        ButtonAdd = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        studentCourseTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(253, 253, 236));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("STUDENT COURSE REGISTIRATION");

        jPanel2.setBackground(new java.awt.Color(217, 224, 245));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jLabel8.setText("ClassLevel");

        jLabel9.setText("Semester");

        jButQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButQueryActionPerformed(evt);
            }
        });

        jButSave.setText("Save Courses");
        jButSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButSaveActionPerformed(evt);
            }
        });

        jButton4.setText("Reset Selection");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        ButtonAdd.setText("Add Course");
        ButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox4, 0, 136, Short.MAX_VALUE)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jButSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        courseTable.setBackground(new java.awt.Color(255, 241, 255));
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
        jScrollPane3.setViewportView(courseTable);

        studentCourseTable.setBackground(new java.awt.Color(230, 253, 253));
        studentCourseTable.setModel(new javax.swing.table.DefaultTableModel(
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
        studentCourseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentCourseTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(studentCourseTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Student Credit:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Course Total Credit:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(624, 624, 624))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jTextField3)))
                    .addComponent(jLabel2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1070, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButQueryActionPerformed
        // Seçili değerleri al

        String selectedClassLevel = (String) jComboBox4.getSelectedItem();
        Semester selectedSemester = (Semester) jComboBox5.getSelectedItem();

        // Kontroller
        if (selectedClassLevel == null || selectedSemester == null) {
            JOptionPane.showMessageDialog(this, "Please select all fields: Department, Class Level, and Semester.");
            return;
        }

        // Filtrelenmiş dersleri yükle
        loadFilteredCourses(departmentID, selectedClassLevel, selectedSemester.getSemesterID());
    }//GEN-LAST:event_jButQueryActionPerformed

    private void jButSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButSaveActionPerformed
        DefaultTableModel studentCourseModel = (DefaultTableModel) studentCourseTable.getModel();

        if (studentCourseModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No courses to save.");
            return;
        }

        List<Course> takenCourses = getTakenCoursesFromTable();
        List<Course> mandatoryCourses = courseDAO.getMandatoryCourses(departmentID, classlevel, semesterID);

        if (!studentService.isMandatoryCoursesCompleted(mandatoryCourses, takenCourses)) {
            JOptionPane.showMessageDialog(this,
                    "You must first take all mandatory courses from your department, class level, and semester.");
            return;
        }

        int totalCredits = calculateTotalCredits();
        int studentCreditLimit = studentCretid;

        if (totalCredits > studentCreditLimit) {
            JOptionPane.showMessageDialog(this, "Cannot save. Credit limit exceeded.");
            return;
        }

        // Confirm save
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to save the selected courses?", "Confirm Save", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Save courses to StudentCourses table
        for (int i = 0; i < studentCourseModel.getRowCount(); i++) {
            int courseID = (int) studentCourseModel.getValueAt(i, 0);
            boolean success = courseDAO.addStudentCourse(studentID, courseID);
            System.out.println("lesson added to" + studentID);
            if (!success) {
                JOptionPane.showMessageDialog(this, "Failed to save course: " + studentCourseModel.getValueAt(i, 1));
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Courses saved successfully.");

        // Reset selections after save
        jButton4.doClick();

    }//GEN-LAST:event_jButSaveActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Clear StudentCourseTable
        DefaultTableModel studentCourseModel = (DefaultTableModel) studentCourseTable.getModel();
        studentCourseModel.setRowCount(0);

        // Reset total credits
        jTextField3.setText("0");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void studentCourseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentCourseTableMouseClicked


    }//GEN-LAST:event_studentCourseTableMouseClicked

    private void ButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddActionPerformed

        addCourse();
    }//GEN-LAST:event_ButtonAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JTable courseTable;
    private javax.swing.JButton jButQuery;
    private javax.swing.JButton jButSave;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<Semester> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable studentCourseTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        Object account = MainFrame.instance.getAccount();
        if (account instanceof Student student) {

            this.studentID = student.getStudentID();
            this.classlevel = student.getClassLevel();
            this.departmentID = student.getDepartmentID();
            this.semesterID = student.getSemesterID();
            this.studentCretid = student.getCredits();
            loadSemesters();
            jTextField2.setText(departmentDAO.getByID(departmentID).getDepartmentName());
            jTextField1.setText(studentCretid + "");
            System.out.println(studentID);

            DefaultTableModel studentCourseModel = (DefaultTableModel) studentCourseTable.getModel();
            studentCourseModel.setRowCount(0);
        }
    }

}
