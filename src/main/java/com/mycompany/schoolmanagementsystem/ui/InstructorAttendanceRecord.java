package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.AttendanceDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ClassroomDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseScheduleDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.SemesterDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentDAO;
import com.mycompany.schoolmanagementsystem.management.Admin;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Semester;
import com.mycompany.schoolmanagementsystem.management.Student;
import com.mycompany.schoolmanagementsystem.service.AdminService;
import com.mycompany.schoolmanagementsystem.service.InstructorService;
import java.awt.Color;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

public class InstructorAttendanceRecord extends javax.swing.JPanel implements IPage {

    int instructorID;

    private DefaultTableModel tableModel;
    private AdminService adminService;
    private DepartmentDAO departmentDAO;
    private ClassroomDAO classroomDAO;
    private CourseDAO courseDAO;
    private SemesterDAO semesterDAO;
    private CourseScheduleDAO courseScheduleDAO;
    private StudentDAO studentDAO;
    private AttendanceDAO attendanceDAO;
    private Map<LocalDate, String> attendanceStatusMap = new HashMap<>();
    private InstructorService instructorService;

    public InstructorAttendanceRecord() {
        initComponents();

        this.adminService = new AdminService();
        this.departmentDAO = new DepartmentDAO();
        this.classroomDAO = new ClassroomDAO();
        this.semesterDAO = new SemesterDAO();
        this.courseScheduleDAO = new CourseScheduleDAO();
        this.courseDAO = new CourseDAO();
        this.studentDAO = new StudentDAO();
        this.attendanceDAO = new AttendanceDAO();
        this.instructorService = new InstructorService();

    }

    private void loadDepartments(int instructorID) {
        departmentComboBox.removeAllItems(); // ComboBox'ı temizle
        List<Department> departments = departmentDAO.getDepartmentsByInstructorID(instructorID);
        DefaultComboBoxModel<Department> departmentModel = new DefaultComboBoxModel<>();
        for (Department department : departments) {
            departmentModel.addElement(department); // Her bir departmanı modele ekle
        }
        departmentComboBox.setModel(departmentModel);
    }

    private void loadSemesters() {
        List<Semester> semesters = semesterDAO.getAll();
        DefaultComboBoxModel<Semester> semesterModel = new DefaultComboBoxModel<>();
        for (Semester s : semesters) {
            semesterModel.addElement(s);
        }
        semesterComboBox.setModel(semesterModel);
    }

    private void loadCoursesForInstructor(int instructorID) {
        DefaultTableModel courseTableModel = new DefaultTableModel(new String[]{"Course ID", "Course Name", "Class Level", "Semester"}, 0);
        List<Course> courses = courseDAO.getCoursesByInstructorID(instructorID);
        // Kursları tabloya ekle
        for (Course course : courses) {
            courseTableModel.addRow(new Object[]{
                course.getCourseID(),
                course.getCourseName(),
                course.getClassLevel(),
                course.getSemesterID()
            });
        }
        courseTable.setModel(courseTableModel);
    }

    private void loadCoursesForFilters(int instructorID, int departmentID, int semesterID, String classLevel) {
        DefaultTableModel courseTableModel = new DefaultTableModel(new String[]{"Course ID", "Course Name", "Class Level", "Semester"}, 0);

        // Filtrelenmiş kursları al
        List<Course> filteredCourses = courseDAO.getCoursesByFilters(instructorID, departmentID, semesterID, classLevel);

        // Kursları tabloya ekle
        for (Course course : filteredCourses) {
            courseTableModel.addRow(new Object[]{
                course.getCourseID(),
                course.getCourseName(),
                course.getClassLevel(),
                course.getSemesterID()
            });
        }

        courseTable.setModel(courseTableModel);
    }

    private void loadStudentsForCourse(int courseID) {
        DefaultTableModel studentTableModel = new DefaultTableModel(new String[]{"Student ID", "Name", "Surname"}, 0);
        List<Student> students = studentDAO.getStudentsByCourseID(courseID);
        for (Student student : students) {
            studentTableModel.addRow(new Object[]{student.getStudentID(), student.getName(), student.getSurname()});
        }
        studentTable.setModel(studentTableModel);
    }

    private void loadScheduleDatesForCourse(int courseID) {
        DefaultListModel<LocalDate> scheduleListModel = new DefaultListModel<>();
        List<LocalDate> scheduleDates = courseScheduleDAO.getScheduleDatesByCourseID(courseID);
        for (LocalDate date : scheduleDates) {
            scheduleListModel.addElement(date);
        }
        scheduleList.setModel(scheduleListModel);
    }

    private void setupComboBoxListeners() {
        departmentComboBox.addActionListener(evt -> filterCourses());
        semesterComboBox.addActionListener(evt -> filterCourses());
        classLevelComboBox.addActionListener(evt -> filterCourses());
    }

    private void filterCourses() {
        Department selectedDepartment = (Department) departmentComboBox.getSelectedItem();
        Semester selectedSemester = (Semester) semesterComboBox.getSelectedItem();
        String selectedClassLevel = (String) classLevelComboBox.getSelectedItem();

        if (selectedDepartment == null || selectedSemester == null || selectedClassLevel == null) {
            return;
        }

        loadCoursesForFilters(
                instructorID,
                selectedDepartment.getDepartmentID(),
                selectedSemester.getSemesterID(),
                selectedClassLevel
        );
    }

   private void updateAttendanceStatus(String status) {
    List<LocalDate> selectedDates = scheduleList.getSelectedValuesList();
    
    if (selectedDates == null || selectedDates.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select at least one schedule date.");
        return;
    }

    // TEST EDİLEBİLİR KISIM – Service'e aktarılmıştır
    instructorService.updateAttendanceStatus(attendanceStatusMap, selectedDates, status);

    // UI Render işlemi
    scheduleList.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            LocalDate date = (LocalDate) value;
            String dateStatus = attendanceStatusMap.get(date);

            if ("Attend".equalsIgnoreCase(dateStatus)) {
                label.setBackground(Color.GREEN);
            } else if ("Not Attend".equalsIgnoreCase(dateStatus)) {
                label.setBackground(Color.RED);
            } else {
                label.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            }

            return label;
        }
    });

    scheduleList.repaint();
}


    private void resetSelection() {
        // Seçimleri temizle
        scheduleList.clearSelection();

        // Varsayılan renderer'a dön
        scheduleList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
                return label;
            }
        });

        // Modeli sıfırla (boş bir model ile yeniden başlat)
        scheduleList.setModel(new DefaultListModel<>());

        // Yeniden çizimi tetikle
        scheduleList.repaint();
    }

    private void saveAttendance() {
        int selectedStudentRow = studentTable.getSelectedRow();
        if (selectedStudentRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student.");
            return;
        }

        int studentID = (int) studentTable.getValueAt(selectedStudentRow, 0);

        int selectedCourseRow = courseTable.getSelectedRow();
        if (selectedCourseRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.");
            return;
        }

        int courseID = (int) courseTable.getValueAt(selectedCourseRow, 0);

        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to save attendance?", "Confirm Save", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        // Tüm tarihler ve durumları kaydet
        ListModel<LocalDate> model = scheduleList.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            LocalDate date = model.getElementAt(i);
            String status = attendanceStatusMap.getOrDefault(date, "Attend"); // Varsayılan: Attend

            // Daha önce aynı öğrenci ve ders için kayıt var mı kontrol et
            int scheduleID = courseScheduleDAO.getScheduleIDByDateAndCourseID(date, courseID);
            if (scheduleID == -1) {
                JOptionPane.showMessageDialog(this, "Schedule not found for date: " + date);
                return;
            }

            if (attendanceDAO.checkIfAttendanceExists(studentID, scheduleID)) {
                JOptionPane.showMessageDialog(this, "Attendance already exists for Student ID: " + studentID + " and Schedule ID: " + scheduleID);
                return;
            }

            // Kaydı veritabanına ekle
            boolean success = attendanceDAO.saveAttendance(studentID, scheduleID, status);
            if (!success) {
                JOptionPane.showMessageDialog(this, "Failed to save attendance for date: " + date);
            }
        }

        JOptionPane.showMessageDialog(this, "Attendance saved successfully!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        jButAttend = new javax.swing.JButton();
        jButNotAttend = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        departmentComboBox = new javax.swing.JComboBox<>();
        classLevelComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        semesterComboBox = new javax.swing.JComboBox<>();
        jButQuery = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        scheduleList = new javax.swing.JList<>();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(253, 253, 236));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("ATTENDANCE RECORD");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Course List:");

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
        courseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                courseTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(courseTable);

        jButAttend.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButAttend.setText("Attend");
        jButAttend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButAttendActionPerformed(evt);
            }
        });

        jButNotAttend.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButNotAttend.setText("Not Attend");
        jButNotAttend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButNotAttendActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(217, 224, 245));

        departmentComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentComboBoxActionPerformed(evt);
            }
        });

        classLevelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jLabel7.setText("Department");

        jLabel8.setText("ClassLevel");

        jLabel9.setText("Semester");

        jButQuery.setIcon(new javax.swing.ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\refresh-button.png")); // NOI18N
        jButQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButQueryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(semesterComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(154, 154, 154))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(departmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(classLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departmentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classLevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(semesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButQuery, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addGap(27, 27, 27))
        );

        studentTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(studentTable);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Student List:");

        scheduleList.setBackground(new java.awt.Color(231, 255, 231));
        jScrollPane3.setViewportView(scheduleList);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Course Dates Weekly:");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setText("Reset Selection");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("save");
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
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(190, 190, 190))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(49, 49, 49)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButAttend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButNotAttend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(47, 47, 47)
                        .addComponent(jButton1)))
                .addGap(101, 101, 101))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(26, 26, 26)))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButAttend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(jButNotAttend, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButQueryActionPerformed

        loadCoursesForInstructor(instructorID);
    }//GEN-LAST:event_jButQueryActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        resetSelection();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButAttendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButAttendActionPerformed
        updateAttendanceStatus("Attend"); // "attend" olarak kaydedilir
    }//GEN-LAST:event_jButAttendActionPerformed

    private void jButNotAttendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButNotAttendActionPerformed
        updateAttendanceStatus("Not Attend"); // "not attend" olarak kaydedilir
    }//GEN-LAST:event_jButNotAttendActionPerformed

    private void courseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courseTableMouseClicked
        // Selected course
        int selectedCourseRow = courseTable.getSelectedRow();
        if (selectedCourseRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course from the course table.");
            return;
        }

        Object courseIDObj = courseTable.getValueAt(selectedCourseRow, 0);

        // Course ID'nin türünü kontrol edin ve işleyin
        if (courseIDObj instanceof Integer) {
            int courseID = (int) courseIDObj;
            loadScheduleDatesForCourse(courseID);
            loadStudentsForCourse(courseID);
        } else {
            JOptionPane.showMessageDialog(this, "Course ID is not valid. Please check your data.");
        }
    }//GEN-LAST:event_courseTableMouseClicked

    private void departmentComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departmentComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        saveAttendance();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> classLevelComboBox;
    private javax.swing.JTable courseTable;
    private javax.swing.JComboBox<Department> departmentComboBox;
    private javax.swing.JButton jButAttend;
    private javax.swing.JButton jButNotAttend;
    private javax.swing.JButton jButQuery;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<LocalDate> scheduleList;
    private javax.swing.JComboBox<Semester> semesterComboBox;
    private javax.swing.JTable studentTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        Object account = MainFrame.instance.getAccount();
        if (account instanceof Instructor instructor) {
            System.out.println("Account is  Admin!");
            this.instructorID = instructor.getInstructorID();
            loadDepartments(instructorID);
            loadSemesters();
            loadCoursesForInstructor(instructorID);
            setupComboBoxListeners();
        }
    }

}
