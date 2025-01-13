/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.homepageUI;

import com.mycompany.schoolmanagementsystem.management.Admin;
import com.mycompany.schoolmanagementsystem.management.Student;
import com.mycompany.schoolmanagementsystem.ui.IPage;
import com.mycompany.schoolmanagementsystem.ui.MainFrame;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class StudentHomePage extends javax.swing.JPanel implements IPage {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    public MainForm mainForm;
    public String username;
    public String gender;

    public StudentHomePage() {
        initComponents();
        init();
    }

    public void topBarInfo() {
        header.getJlabel2().setText(username);

        ImageAvatar avatar = header.getImageAvatar();
        if (gender.equals("Male")) {
            avatar.setIcon(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\studentMale.png")); // İkon ayarla
        } else if (gender.equals("Female")) {
            avatar.setIcon(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\studentFemale.png")); // İkon ayarla
        }
    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        this.setLayout(layout);

        menu = new Menu();
        header = new Header();
        mainForm = new MainForm();

        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                if (menuIndex == 0) {
                    MainFrame.instance.setPagePro(MainFrame.instance.getStudentAttendanceList());
                    mainForm.showForm(MainFrame.instance.getStudentAttendanceList());

                }
                if (menuIndex == 1) {
                    MainFrame.instance.setPagePro(MainFrame.instance.getStudentCourseList());
                    mainForm.showForm(MainFrame.instance.getStudentCourseList());

                }
                if (menuIndex == 2) {
                    MainFrame.instance.setPagePro(MainFrame.instance.getStudentExamList());
                    mainForm.showForm(MainFrame.instance.getStudentExamList());

                }
                if (menuIndex == 3) {
                    MainFrame.instance.setPagePro(MainFrame.instance.getStudentExamList());   // değişecek  score list
                    mainForm.showForm(MainFrame.instance.getAdminManageExam());
                }
            }

        });

        menu.initMenuItemStudent();

        this.add(menu, "w 281!, spany 2");    // Span Y 2cell
        this.add(header, "h 80!, wrap");
        this.add(mainForm, "w 100%, h 100%");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void onPageSetted() {
        Object account = MainFrame.instance.getAccount();
        if (account instanceof Student student) {
            System.out.println("Account is  Admin!");
            this.username = student.getUsername();
            this.gender = student.getGender();
            topBarInfo();
        }
    }
}
