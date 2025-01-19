package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.CourseDAO;
import com.mycompany.schoolmanagementsystem.management.Course;
import com.mycompany.schoolmanagementsystem.management.Student;
import com.mycompany.schoolmanagementsystem.service.StudentService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class StudentCourseList extends javax.swing.JPanel implements IPage{

    
    private DefaultTableModel tableModel;
    private int studentID; // Giriş yapan öğrencinin ID'si
    private CourseDAO courseDAO;
    
    public StudentCourseList() {
        initComponents();
           initComponents();
           // Arayüzü başlat
        initComponents();

        // StudentService nesnesini oluştur
        this.courseDAO = new CourseDAO();

        // Tabloyu yapılandır
        configureTable();
       
    }

      private void configureTable() {
        String[] columnNames = {"Course ID", "Course Name"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabloda düzenlemeyi kapat
            }
        };
        courseTable.setModel(tableModel);
    }

      
    private void loadCourses() {
        // Öğrenciye ait dersleri al
        List<Course> studentCourses = courseDAO.getCoursesByStudentID(studentID);

        // Mevcut tabloyu temizle
        tableModel.setRowCount(0);

        // Her dersi tabloya ekle
        for (Course course : studentCourses) {
            Object[] rowData = {
                course.getCourseID(),
                course.getCourseName(),
            };
            tableModel.addRow(rowData);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        courseTable.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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
        jScrollPane1.setViewportView(courseTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("COURSE LIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable courseTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onPageSetted() {
        // Giriş yapan öğrenci nesnesini al
        Object account = MainFrame.instance.getAccount();
        if (account instanceof Student student) {
            this.studentID = student.getStudentID();
            loadCourses(); // Dersleri yükle
           
        }
    }
}
