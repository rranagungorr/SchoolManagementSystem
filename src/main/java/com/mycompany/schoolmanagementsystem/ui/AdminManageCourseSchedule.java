package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.AcademicCalendar;
import com.mycompany.schoolmanagementsystem.examsys.DAO.ClassroomDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseScheduleDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.DepartmentDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.InstructorDAO;
import com.mycompany.schoolmanagementsystem.examsys.DAO.SemesterDAO;
import com.mycompany.schoolmanagementsystem.examsys.Week;
import com.mycompany.schoolmanagementsystem.management.Classroom;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Department;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Semester;
import com.mycompany.schoolmanagementsystem.service.AdminService;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Merve
 */
public class AdminManageCourseSchedule extends javax.swing.JPanel implements IPage {

    private DefaultTableModel tableModel;
    private AdminService adminService;
    private DepartmentDAO departmentDAO;
    private ClassroomDAO classroomDAO;
    private CourseDAO courseDAO;
    private SemesterDAO semesterDAO;
    private CourseScheduleDAO courseScheduleDAO;

    public AdminManageCourseSchedule() {
        initComponents();

        this.adminService = new AdminService();
        this.departmentDAO = new DepartmentDAO();
        this.classroomDAO = new ClassroomDAO();
        this.semesterDAO = new SemesterDAO();
        this.courseScheduleDAO = new CourseScheduleDAO();
        this.courseDAO = new CourseDAO();

        String[] columnNames = {"Course ID", "Course Name", "Hours", "WeekDay", "Start Time", "End Time"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Table is read-only
            }
        };
        courseTable.setModel(tableModel);
        courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Schedule Table
        // Tablo için model tanımla
        String[] columnNames1 = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[][] data = {
            {"09:00 - 09:50", "", "", "", "", ""},
            {"10:00 - 10:50", "", "", "", "", ""},
            {"11:00 - 11:50", "", "", "", "", ""},
            {"12:00 - 12:50", "", "", "", "", ""},
            {"13:00 - 13:50", "", "", "", "", ""},
            {"14:00 - 14:50", "", "", "", "", ""},
            {"15:00 - 15:50", "", "", "", "", ""},
            {"16:00 - 16:50", "", "", "", "", ""},
            {"17:00 - 17:50", "", "", "", "", ""}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames1);
        scheduleTable.setModel(model);
        scheduleTable.setRowHeight(50);
        scheduleTable.setFont(new Font("Arial", Font.PLAIN, 14));
        scheduleTable.setGridColor(new Color(0, 51, 102)); // Dark blue grid
        scheduleTable.setShowGrid(true);

// Tablo başlıklarını daha koyu yap
        JTableHeader tableHeader = scheduleTable.getTableHeader();
        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(0, 51, 102)); // Dark blue background
                label.setForeground(Color.WHITE); // White text
                label.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
                return label;

            }
        });

// Seçili hücre rengini açık mavi yap
        scheduleTable.setSelectionBackground(new Color(173, 216, 230)); // Light blue for selection

    }

    private void loadFilteredCourses(int departmentID, String classLevel, int semesterID) {
        List<Course> filteredCourses = courseDAO.getCoursesByDepartmentClassLevelAndSemester(departmentID, classLevel, semesterID);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Course ID", "Course Name", "Hours", "Week Day", "Start Time", "End Time"}, 0);
        boolean allCoursesHaveNoSchedule = true; // Schedule olup olmadığını kontrol eden bayrak

        for (Course course : filteredCourses) {
            tableModel.addRow(new Object[]{
                course.getCourseID(),
                course.getCourseName(),
                course.getHours(),
                course.getWeekDay(),
                course.getStartTime(),
                course.getEndTime()
            });

            // Eğer Week Day, Start Time ve End Time mevcutsa Schedule Table'ı doldur
            if (!"-".equals(course.getWeekDay()) && !"-".equals(course.getStartTime()) && !"-".equals(course.getEndTime())) {
                allCoursesHaveNoSchedule = false; // En az bir dersin zaman bilgisi varsa bayrak false yapılır
                String weekDay = course.getWeekDay();
                String startTime = course.getStartTime();
                String endTime = course.getEndTime();

                // Start ve End Time'i tablo formatına uyarlama
                String startTimeFormatted = formatStartTime(startTime);
                String endTimeFormatted = formatEndTime(endTime);

                int weekDayIndex = getWeekDayIndex(weekDay); // Haftanın günü için sütun indexi

                if (weekDayIndex == -1) {
                    JOptionPane.showMessageDialog(this, "Invalid weekday found in data: " + weekDay);
                    continue;
                }

                // Start Time ve End Time arasında tabloyu doldur
                for (int scheduleRow = 0; scheduleRow < scheduleTable.getRowCount(); scheduleRow++) {
                    String scheduleTime = (String) scheduleTable.getValueAt(scheduleRow, 0); // Zaman sütunu

                    if (scheduleTime.equals(startTimeFormatted)) {
                        int startRow = scheduleRow;
                        int endRow = findEndRow(startTimeFormatted, endTimeFormatted);

                        if (endRow == -1) {
                            JOptionPane.showMessageDialog(this, "Invalid time range for course: " + course.getCourseName());
                            break;
                        }

                        for (int i = startRow; i <= endRow; i++) {
                            if (!scheduleTable.getValueAt(i, weekDayIndex).equals("")) {
                                JOptionPane.showMessageDialog(this, "Schedule conflict for course: " + course.getCourseName());
                                break;
                            }

                            scheduleTable.setValueAt(course.getCourseName(), i, weekDayIndex); // Ders adını tabloya ekle
                        }
                    }
                }
            }

        }
        // Eğer tüm derslerin Week Day, Start Time ve End Time bilgisi yoksa Schedule Table temizlenir
        if (allCoursesHaveNoSchedule) {
            clearScheduleTable();
        }

        courseTable.setModel(tableModel);
    }

    // Schedule Table'ı temizleyen metot
    private void clearScheduleTable() {
        DefaultTableModel scheduleModel = (DefaultTableModel) scheduleTable.getModel();
        for (int row = 0; row < scheduleModel.getRowCount(); row++) {
            for (int col = 1; col < scheduleModel.getColumnCount(); col++) {
                scheduleModel.setValueAt("", row, col); // Tüm hücreler temizlenir
            }
        }
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

    private void saveSchedules() {
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to save the schedules?", "Confirm Save", JOptionPane.YES_NO_OPTION);
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        // MainFrame'den akademik takvimi al
        AcademicCalendar academicCalendar = MainFrame.instance.getFallCalendar();
        List<Week> teachingWeeks = academicCalendar.getTeachingWeeks(); // Güz dönemi için eğitim haftaları

        for (int row = 0; row < courseTable.getRowCount(); row++) {
            String weekDay = (String) courseTable.getValueAt(row, 3);
            String startTime = (String) courseTable.getValueAt(row, 4);
            String endTime = (String) courseTable.getValueAt(row, 5);

            if ("-".equals(weekDay) || "-".equals(startTime) || "-".equals(endTime)) {
                JOptionPane.showMessageDialog(this, "Please schedule all courses before saving.");
                return;
            }

            int courseID = (int) courseTable.getValueAt(row, 0);

            // 1. Mevcut CourseScheduler kayıtlarını sil
            boolean deleteSuccess = courseScheduleDAO.deleteByCourseID(courseID);
            if (!deleteSuccess) {
                JOptionPane.showMessageDialog(this, "Failed to clear existing schedules for course ID: " + courseID);
                return;
            }

            // 2. Yeni CourseScheduler kayıtlarını ekle
            List<LocalDate> datesForCourse = getDatesForWeekDay(teachingWeeks, weekDay);
            for (LocalDate date : datesForCourse) {
                //System.out.println(date);
                boolean insertSuccess = courseScheduleDAO.addCourseSchedule(courseID, date);
                if (!insertSuccess) {
                    JOptionPane.showMessageDialog(this, "Failed to save schedule for course ID: " + courseID + " on date: " + date);
                    return;
                }
            }

            // 3. Courses tablosunda güncelle
            boolean updateSuccess = courseDAO.updateCourseSchedule(courseID, weekDay, startTime, endTime);
            if (!updateSuccess) {
                JOptionPane.showMessageDialog(this, "Failed to update course schedule for course ID: " + courseID);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "All schedules saved successfully.");
    }

    // Haftanın gününe göre tarihleri bulan yardımcı metot
    private List<LocalDate> getDatesForWeekDay(List<Week> teachingWeeks, String weekDay) {
        List<LocalDate> dates = new ArrayList<>();
        int dayOfWeek = getWeekDayIndex(weekDay); // Pazartesi için 1, Salı için 2 vs.

        for (Week week : teachingWeeks) {
            LocalDate startDate = week.getStartDate();
            LocalDate endDate = week.getEndDate();
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                if (date.getDayOfWeek().getValue() == dayOfWeek) {
                    dates.add(date);
                }
            }
        }

        return dates;
    }

    private void resetTables() {
        // Clear Course Table
        for (int row = 0; row < courseTable.getRowCount(); row++) {
            courseTable.setValueAt("-", row, 3); // Clear Week Day
            courseTable.setValueAt("-", row, 4); // Clear Start Time
            courseTable.setValueAt("-", row, 5); // Clear End Time
        }

        // Clear Schedule Table
        DefaultTableModel scheduleModel = (DefaultTableModel) scheduleTable.getModel();
        for (int row = 0; row < scheduleModel.getRowCount(); row++) {
            for (int col = 1; col < scheduleModel.getColumnCount(); col++) {
                scheduleModel.setValueAt("", row, col);
            }
        }

        JOptionPane.showMessageDialog(this, "Tables have been reset.");
    }

    private void clearCourseFromSchedule(String courseName) {
        DefaultTableModel scheduleModel = (DefaultTableModel) scheduleTable.getModel();
        for (int row = 0; row < scheduleModel.getRowCount(); row++) {
            for (int col = 1; col < scheduleModel.getColumnCount(); col++) { // 1. sütundan başla çünkü 0. sütun zaman
                String cellValue = (String) scheduleModel.getValueAt(row, col);
                if (courseName.equals(cellValue)) {
                    scheduleModel.setValueAt("", row, col); // Hücreyi temizle
                }
            }
        }
    }

    // Haftanın günü sütun indexini alır
    private int getWeekDayIndex(String weekDay) {
        switch (weekDay.toLowerCase()) {
            case "monday":
                return 1;
            case "tuesday":
                return 2;
            case "wednesday":
                return 3;
            case "thursday":
                return 4;
            case "friday":
                return 5;
            default:
                return -1;
        }
    }

// Start Time'i tablo formatına uyarlayan metot
    private String formatStartTime(String startTime) {
        String[] timeParts = startTime.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        // Dersin süresi 50 dakika kabul ediliyor
        int endMinute = minute + 50;
        int endHour = hour;
        if (endMinute >= 60) {
            endMinute -= 60;
            endHour++;
        }

        String endTime = String.format("%02d:%02d", endHour, endMinute);
        return startTime + " - " + endTime; // Örn: 09:00 - 09:50
    }

// End Time'i tablo formatına uyarlayan metot
    private String formatEndTime(String endTime) {
        String[] timeParts = endTime.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        // Dersin başlangıç saati için önceki saate geçiş
        int startMinute = minute - 50;
        int startHour = hour;
        if (startMinute < 0) {
            startMinute += 60;
            startHour--;
        }

        String startTime = String.format("%02d:%02d", startHour, startMinute);
        return startTime + " - " + endTime; // Örn: 13:00 - 13:50
    }

    // End Time'e uygun tablo satırını bulur
    private int findEndRow(String startTimeFormatted, String endTimeFormatted) {
        for (int row = 0; row < scheduleTable.getRowCount(); row++) {
            String scheduleTime = (String) scheduleTable.getValueAt(row, 0);
            if (scheduleTime.equals(endTimeFormatted)) {
                return row;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButQuery = new javax.swing.JButton();
        jButSave = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(253, 253, 236));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MANAGE COURSE SCHEDULE");

        jPanel2.setBackground(new java.awt.Color(217, 224, 245));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jLabel7.setText("Department");

        jLabel8.setText("ClassLevel");

        jLabel9.setText("Semester");

        jButQuery.setText("Query");
        jButQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButQueryActionPerformed(evt);
            }
        });

        jButSave.setText("Save Schedule");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jButQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jButSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jButQuery))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
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
        scheduleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scheduleTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(scheduleTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(693, 693, 693))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
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
    }//GEN-LAST:event_jButQueryActionPerformed

    private void jButSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButSaveActionPerformed
        saveSchedules();
    }//GEN-LAST:event_jButSaveActionPerformed

    private void scheduleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scheduleTableMouseClicked

        int selectedRow = scheduleTable.getSelectedRow();
        int selectedColumn = scheduleTable.getSelectedColumn();

        if (selectedColumn == 0) {
            JOptionPane.showMessageDialog(this, "Please select a weekday column, not the time column.");
            return;
        }

        // Start Time and Weekday
        String startTime = (String) scheduleTable.getValueAt(selectedRow, 0);
        String[] startTimeParts = startTime.split(" - ");
        String actualStartTime = startTimeParts[0].trim(); // '10:00' kısmı
        String weekDay = scheduleTable.getColumnName(selectedColumn);

        // Selected course
        int selectedCourseRow = courseTable.getSelectedRow();
        if (selectedCourseRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course from the course table.");
            return;
        }

        String courseName = (String) courseTable.getValueAt(selectedCourseRow, 1);
        Object courseHoursObj = courseTable.getValueAt(selectedCourseRow, 2); // 2. sütun (Course Hours)
        int courseHours;
        try {
            courseHours = Integer.parseInt(courseHoursObj.toString()); // String'i Integer'a çevir
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid course hours format. Please check your data.");
            return;
        }

        // ScheduleTable'dan daha önceki bu dersle ilgili hücreleri temizle
        clearCourseFromSchedule(courseName);

        // Check for enough space
        if (selectedRow + courseHours > scheduleTable.getRowCount()) {
            JOptionPane.showMessageDialog(this, "Not enough space in the table for this course.");
            courseTable.setValueAt("-", selectedCourseRow, 3); // Update Week Day
            courseTable.setValueAt("-", selectedCourseRow, 4); // Update Start Time
            courseTable.setValueAt("-", selectedCourseRow, 5); // Update End Time
            return;
        }

        // Check for overlaps
        for (int i = 0; i < courseHours; i++) {
            String cellValue = (String) scheduleTable.getValueAt(selectedRow + i, selectedColumn);
            if (cellValue != null && !cellValue.isEmpty()) {
                JOptionPane.showMessageDialog(this, "This time slot is already occupied by another course.");
                courseTable.setValueAt("-", selectedCourseRow, 3); // Update Week Day
                courseTable.setValueAt("-", selectedCourseRow, 4); // Update Start Time
                courseTable.setValueAt("-", selectedCourseRow, 5); // Update End Time
                return;
            }
        }

        // Fill the cells with the course name
        for (int i = 0; i < courseHours; i++) {
            scheduleTable.setValueAt(courseName, selectedRow + i, selectedColumn);
        }

        // End Time
        String endTime = (String) scheduleTable.getValueAt(selectedRow + courseHours - 1, 0);
        String[] endTimeParts = endTime.split(" - ");
        String actualEndTime = endTimeParts[1].trim(); // '10:00' kısmı

        courseTable.setValueAt(weekDay, selectedCourseRow, 3); // Update Week Day
        courseTable.setValueAt(actualStartTime, selectedCourseRow, 4); // Update Start Time
        courseTable.setValueAt(actualEndTime, selectedCourseRow, 5); // Update End Time

        JOptionPane.showMessageDialog(this, "Course scheduled successfully.");


    }//GEN-LAST:event_scheduleTableMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        resetTables();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable courseTable;
    private javax.swing.JButton jButQuery;
    private javax.swing.JButton jButSave;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<Department> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<Semester> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable scheduleTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        loadDepartments();
        loadSemesters();

    }

}
