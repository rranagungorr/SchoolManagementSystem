package com.mycompany.schoolmanagementsystem.ui;

import com.mycompany.schoolmanagementsystem.examsys.DAO.StudentDAO;
import com.mycompany.schoolmanagementsystem.homepageUI.AdminHomePage;
import com.mycompany.schoolmanagementsystem.homepageUI.EventMenuSelected;
import com.mycompany.schoolmanagementsystem.homepageUI.InstructorHomePage;
import com.mycompany.schoolmanagementsystem.homepageUI.StudentHomePage;
import com.mycompany.schoolmanagementsystem.management.Admin;
import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Student;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author rrana
 */
public class MainFrame extends javax.swing.JFrame {

    public InstructorMainPanel getInstructorMainPanel() {
        return instructorMainPanel;
    }

    public InstructorCourseList getInstructorCourseList() {
        return instructorCourseList;
    }

    public InstructorAttendanceRecord getInstructorAttendanceRecord() {
        return instructorAttendanceRecord;
    }

    public InstructorExamList getInstructorExamList() {
        return instructorExamList;
    }

    public AdminMainScreen getAdminMainScreen() {
        return adminMainScreen;
    }

    public AdminManageCourse getAdminManageCourse() {
        return adminManageCourse;
    }

    public AdminManageExam getAdminManageExam() {
        return adminManageExam;
    }

    public AdminManageStudent getAdminManageStudent() {
        return adminManageStudent;
    }

    public AdminManageInstructor getAdminManageInstructor() {
        return adminManageInstructor;
    }

    public AdminManageExamRecord getAdminManageExamRecord() {
        return adminManageExamRecord;
    }

    public StudentCourseList getStudentCourseList() {
        return studentCourseList;
    }

    public StudentExamList getStudentExamList() {
        return studentExamList;
    }

    public StudentAttendanceList getStudentAttendanceList() {
        return studentAttendanceList;
    }

    public Object getAccount() {
        return account;
    }

    public void setAccount(Object account) {
        this.account = account;
    }

    public static MainFrame instance;

    private Object account;

    private final LoginPanel loginPanel;
    private final StudentMainPanel studentMainPanel;
    private final StudentCourseList studentCourseList;
    private final StudentExamList studentExamList;
    private final StudentAttendanceList studentAttendanceList;
    private final AdminMainScreen adminMainScreen;
    private final AdminManageCourse adminManageCourse;
    private final AdminManageExam adminManageExam;
    private final AdminManageStudent adminManageStudent;
    private final AdminManageInstructor adminManageInstructor;
    private final AdminManageExamRecord adminManageExamRecord;
    private final InstructorMainPanel instructorMainPanel;
    private final InstructorCourseList instructorCourseList;
    private final InstructorAttendanceRecord instructorAttendanceRecord;
    private final InstructorExamList instructorExamList;
    private final AdminHomePage adminHomePage;
    private final InstructorHomePage inHomePage;
    private final StudentHomePage studentHomePage;

    public MainFrame() {
        initComponents();

        loginPanel = new LoginPanel();
        studentMainPanel = new StudentMainPanel();
        studentCourseList = new StudentCourseList();
        studentExamList = new StudentExamList();
        studentAttendanceList = new StudentAttendanceList();

        adminMainScreen = new AdminMainScreen();
        adminManageCourse = new AdminManageCourse();
        adminManageExam = new AdminManageExam();
        adminManageStudent = new AdminManageStudent();
        adminManageInstructor = new AdminManageInstructor();
        adminManageExamRecord = new AdminManageExamRecord();

        instructorMainPanel = new InstructorMainPanel();
        instructorAttendanceRecord = new InstructorAttendanceRecord();
        instructorCourseList = new InstructorCourseList();
        instructorExamList = new InstructorExamList();

        adminHomePage = new AdminHomePage();
        inHomePage = new InstructorHomePage();
        studentHomePage = new StudentHomePage();

        this.add(mainPanel);
        setPage(loginPanel);

        this.setSize(new Dimension(1440, 750));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(235, 235, 235));
        mainPanel.setPreferredSize(new java.awt.Dimension(1425, 706));
        mainPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

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

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public final void setPage(JPanel page) {
        mainPanel.removeAll();
        mainPanel.add(page, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
        System.out.println("Page Setted!");

        if (page instanceof IPage iPage) {
            iPage.onPageSetted();
        }
    }

    public final void setPagePro(JPanel page) {

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
            setPage(studentHomePage);
        } else if (MainFrame.instance.getAccount() instanceof Instructor) {
            setPage(inHomePage);
        } else if (MainFrame.instance.getAccount() instanceof Admin) {
            setPage(adminHomePage);
            if (MainFrame.instance.getAccount() == null) {
                System.out.println("nullllllllll");
            }

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
