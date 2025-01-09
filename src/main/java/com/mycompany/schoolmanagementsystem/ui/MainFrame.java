package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentDAO;
import com.mycompany.schoolmanagementsystem.management.Admin;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Student;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author rrana
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * @return the instructorMainPanel
     */
    public InstructorMainPanel getInstructorMainPanel() {
        return instructorMainPanel;
    }

    /**
     * @return the instructorCourseList
     */
    public InstructorCourseList getInstructorCourseList() {
        return instructorCourseList;
    }

    /**
     * @return the instructorAttendanceRecord
     */
    public InstructorAttendanceRecord getInstructorAttendanceRecord() {
        return instructorAttendanceRecord;
    }

    /**
     * @return the adminMainScreen
     */
    public AdminMainScreen getAdminMainScreen() {
        return adminMainScreen;
    }

    /**
     * @return the adminManageCourse
     */
    public AdminManageCourse getAdminManageCourse() {
        return adminManageCourse;
    }

    /**
     * @return the adminManageExam
     */
    public AdminManageExam getAdminManageExam() {
        return adminManageExam;
    }

    /**
     * @return the adminManageStudent
     */
    public AdminManageStudent getAdminManageStudent() {
        return adminManageStudent;
    }

    /**
     * @return the studentCourseList
     */
    public StudentCourseList getStudentCourseList() {
        return studentCourseList;
    }

    /**
     * @return the studentExamList
     */
    public StudentExamList getStudentExamList() {
        return studentExamList;
    }

    /**
     * @return the account
     */
    public Object getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Object account) {
        this.account = account;
    }

    public static MainFrame instance;

    private Object account;
    
    private final LoginPanel loginPanel;
    private final StudentMainPanel studentMainPanel;
    private final StudentCourseList studentCourseList;
    private final StudentExamList studentExamList;
    private final AdminMainScreen adminMainScreen;
    private final AdminManageCourse adminManageCourse;
    private final AdminManageExam adminManageExam;
    private final AdminManageStudent adminManageStudent;
    private final InstructorMainPanel instructorMainPanel;
    private final InstructorCourseList instructorCourseList;
    private final InstructorAttendanceRecord instructorAttendanceRecord;
    
    public MainFrame() {
        initComponents();

        loginPanel = new LoginPanel();
        studentMainPanel = new StudentMainPanel();
        studentCourseList = new StudentCourseList();
        studentExamList = new StudentExamList();
        
        adminMainScreen = new AdminMainScreen();
        adminManageCourse = new AdminManageCourse();
        adminManageExam = new AdminManageExam();
        adminManageStudent = new AdminManageStudent();
        
        instructorMainPanel = new InstructorMainPanel();
        instructorAttendanceRecord = new InstructorAttendanceRecord();
        instructorCourseList = new InstructorCourseList();
        
        this.add(mainPanel);
        setPage(loginPanel);
        
        this.setSize(new Dimension(1200, 600));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(225, 225, 225));
        mainPanel.setLayout(new java.awt.GridLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                instance = new MainFrame();
                instance.setVisible(true);
            }
        });
    }
    
    public LoginPanel getLoginPanel(){
        return loginPanel;
    }

    public final void setPage(JPanel page) {
        mainPanel.removeAll();
        mainPanel.add(page);
        mainPanel.revalidate();
        mainPanel.repaint();
        System.out.println("Page Setted!");

        if (page instanceof IPage iPage) {
            iPage.onPageSetted();
        }
    }

    public void logout() {
        setAccount(null);
    }

    public void login() {
        if (MainFrame.instance.getAccount() instanceof Student) {
            setPage(studentMainPanel);
        }else if (MainFrame.instance.getAccount() instanceof Instructor) {
            setPage(instructorMainPanel);
        }else if (MainFrame.instance.getAccount() instanceof Admin) {
            setPage(adminMainScreen);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the studentMainPanel
     */
    public StudentMainPanel getStudentMainPanel() {
        return studentMainPanel;
    }
}
