package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.ClassroomDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DBUtil;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.SemesterDAO;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Classroom;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Semester;
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
    private DepartmentDAO departmentDAO;
    private InstructorDAO instructorDAO;
    private ClassroomDAO classroomDAO;

    public AdminManageCourse() {
        initComponents();
        this.adminService = new AdminService();
        this.departmentDAO = new DepartmentDAO();
        this.instructorDAO = new InstructorDAO();
        this.classroomDAO = new ClassroomDAO();

        String[] columnNames = {"Course ID", "Course Name", "Course Code", "Credits", "Instructor", "Classroom", "Hours"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Table is read-only
            }
        };
        loadCourses();
        coursesTable.setModel(tableModel);

    }

    private void loadDepartments(List<Department> departments) {
        departmentComboBox.removeAllItems(); // ComboBox'ı temizle
        DefaultComboBoxModel<Department> departmentModel = new DefaultComboBoxModel<>();
        for (Department department : departments) {
            departmentModel.addElement(department); // Her bir departmanı modele ekle
        }
        departmentComboBox.setModel(departmentModel);
    }

    private void loadInstructors() {
        instructorComboBox.removeAllItems();
        List<Instructor> instructors = instructorDAO.getAll();
        DefaultComboBoxModel<Instructor> instructorModel = new DefaultComboBoxModel<>();
        for (Instructor i : instructors) {
            instructorModel.addElement(i);
        }
        instructorComboBox.setModel(instructorModel);
    }

    private void loadClassrooms() {
        classroomComboBox.removeAllItems();
        List<Classroom> classrooms = classroomDAO.getAll();
        DefaultComboBoxModel<Classroom> classroomModel = new DefaultComboBoxModel<>();
        for (Classroom c : classrooms) {
            classroomModel.addElement(c);
        }
        classroomComboBox.setModel(classroomModel);
    }

    private void loadCourses() {
        tableModel.setRowCount(0);
        List<Course> allCourses = adminService.getAllCourses();
        for (Course c : allCourses) {
            String instructorName = getInstructorName(c.getInstructorID());
            String classroomName = getClassroomName(c.getClassroomID());

            Object[] rowData = {
                c.getCourseID(),
                c.getCourseName(),
                c.getCourseCode(),
                c.getCredits(),
                instructorName,
                classroomName,
                c.getHours(),};
            tableModel.addRow(rowData);
        }
    }

    private void loadSemesters() {
        SemesterDAO semesterDAO = new SemesterDAO();
        List<Semester> semesters = semesterDAO.getAll();
        DefaultComboBoxModel<Semester> semesterModel = new DefaultComboBoxModel<>();
        for (Semester s : semesters) {
            semesterModel.addElement(s);
        }
        semesterComboB.setModel(semesterModel);
    }

    private String getInstructorName(Integer instructorID) {
        if (instructorID == null) {
            return "N/A";
        }
        Instructor instructor = instructorDAO.getByID(instructorID);
        return (instructor != null) ? instructor.getName() + " " + instructor.getSurname() : "Unknown";
    }

    private String getClassroomName(int classroomID) {
        Classroom classroom = classroomDAO.getByID(classroomID);
        return (classroom != null) ? classroom.getClassroomName() : "Unknown";
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
        departmentComboBox = new javax.swing.JComboBox<>();
        classroomComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        hoursField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        semesterComboB = new javax.swing.JComboBox<>();
        deleteCourse = new javax.swing.JButton();
        classLevelJComb = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        coursesTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(253, 253, 236));
        setPreferredSize(new java.awt.Dimension(1200, 750));

        jPanel1.setBackground(new java.awt.Color(199, 235, 255));

        jLabel2.setText("Department:");

        addCourse.setText("Add Course");
        addCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseActionPerformed(evt);
            }
        });

        jLabel4.setText("Instructor Name:");

        jLabel6.setText("Course Name:");

        instructorComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                instructorComboBoxİtemStateChanged(evt);
            }
        });

        jLabel7.setText("Credit:");

        jLabel3.setText("Classroom:");

        jLabel5.setText("Hours:");

        jLabel8.setText("Semester:");

        deleteCourse.setText("Delete Course");
        deleteCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCourseActionPerformed(evt);
            }
        });

        classLevelJComb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jLabel9.setText("Class level:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(courseNameField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(instructorComboBox, 0, 162, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(departmentComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(creditsField)
                                    .addComponent(classroomComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(semesterComboB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hoursField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(classLevelJComb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(deleteCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(courseNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creditsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classroomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(hoursField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semesterComboB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(classLevelJComb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MANAGE COURSES");

        coursesTable.setBackground(new java.awt.Color(199, 235, 255));
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
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCourseActionPerformed

        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course from the table.");
            return;
        }

        int courseID = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(null,
                "Deleting this course will also delete all related records (instructors, schedules, students, exams, grades). Do you want to proceed?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = adminService.deleteCourseWithDependencies(courseID);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Course and related records deleted successfully.");
                    loadCourses(); // Tabloyu güncelle
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete course. Please try again.");
                }
            } catch (Exception e) { // Genel bir istisna yakalayıcı kullanabilirsiniz
                JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_deleteCourseActionPerformed

    private void instructorComboBoxİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_instructorComboBoxİtemStateChanged
        Instructor selectedInstructor = (Instructor) instructorComboBox.getSelectedItem();
        if (selectedInstructor != null) {
            int instructorID = selectedInstructor.getInstructorID();

            // Eğitmenin bağlı olduğu departmanları InstructorDepartments tablosundan al
            InstructorDepartmentDAO instructorDepartmentDAO = new InstructorDepartmentDAO();
            List<Department> departments = instructorDepartmentDAO.getDepartmentsByInstructor(instructorID);

            if (!departments.isEmpty()) {
                loadDepartments(departments); // Departmanları yükle
            } else {
                JOptionPane.showMessageDialog(this, "Selected instructor has no assigned departments.");
            }
        }
    }//GEN-LAST:event_instructorComboBoxİtemStateChanged

    private void addCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCourseActionPerformed

        Department selectedDepartment = (Department) departmentComboBox.getSelectedItem();
        Instructor selectedInstructor = (Instructor) instructorComboBox.getSelectedItem();
        Classroom selectedClassroom = (Classroom) classroomComboBox.getSelectedItem();
        Semester selectedSemester = (Semester) semesterComboB.getSelectedItem();
        int semesterID = selectedSemester.getSemesterID();
        int classLevel = Integer.parseInt((String) classLevelJComb.getSelectedItem());

        if (selectedDepartment == null || selectedInstructor == null || selectedClassroom == null) {
            JOptionPane.showMessageDialog(this, "Please select all required fields.");
            return;
        }

        String courseName = courseNameField.getText().trim();
        String courseCode = generateRandomCourseCode();
        int credits;
        try {
            credits = Integer.parseInt(creditsField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Credits must be a valid integer.");
            return;
        }

        int hours;
        try {
            hours = Integer.parseInt(hoursField.getText().trim());
            if (hours < 2 || hours > 4) {
                JOptionPane.showMessageDialog(this, "Hours must be 2, 3, or 4.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Hours must be a valid integer.");
            return;
        }

        String weekDay = "-";
        String startTime = "-";
        String endTime = "-";

        // Kurs ekle
        int newCourseID = adminService.createCourse(courseName, courseCode, credits, selectedDepartment.getDepartmentID(),
                selectedInstructor.getInstructorID(), selectedClassroom.getClassroomID(), hours, semesterID, startTime, endTime, weekDay, classLevel);

        if (newCourseID > 0) {
            // courseInstructor tablosuna ilişki ekle
            boolean instructorMappingAdded = adminService.addInstructorToCourse(newCourseID, selectedInstructor.getInstructorID());
            if (instructorMappingAdded) {
                JOptionPane.showMessageDialog(this, "Course added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Course added, but failed to map instructor.");
            }
            loadCourses();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add course.");
        }


    }//GEN-LAST:event_addCourseActionPerformed

    private String generateRandomCourseCode() {
        // CODE + 4 haneli rastgele bir sayı (örnek: CODE1234)
        int random4Digit = (int) (Math.random() * 9000 + 1000); // 1000-9999 arasında rastgele sayı
        return "C" + random4Digit; // Toplam uzunluk: 5 karakter (örnek: C1234)
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCourse;
    private javax.swing.JComboBox<String> classLevelJComb;
    private javax.swing.JComboBox<Classroom> classroomComboBox;
    private javax.swing.JTextField courseNameField;
    private javax.swing.JTable coursesTable;
    private javax.swing.JTextField creditsField;
    private javax.swing.JButton deleteCourse;
    private javax.swing.JComboBox<Department> departmentComboBox;
    private javax.swing.JTextField hoursField;
    private javax.swing.JComboBox<Instructor> instructorComboBox;
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
    private javax.swing.JComboBox<Semester> semesterComboB;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {

        loadInstructors();
        loadClassrooms();
        loadCourses();
        loadSemesters();

    }
}
