/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.homepageUI;

import com.mycompany.schoolmanagementsystem.management.Instructor;
import com.mycompany.schoolmanagementsystem.management.Student;
import com.mycompany.schoolmanagementsystem.ui.IPage;
import com.mycompany.schoolmanagementsystem.ui.InstructorAttendanceRecord;
import com.mycompany.schoolmanagementsystem.ui.InstructorCourseList;
import com.mycompany.schoolmanagementsystem.ui.InstructorExamList;
import com.mycompany.schoolmanagementsystem.ui.InstructorScoreAdding;
import com.mycompany.schoolmanagementsystem.ui.MainFrame;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class InstructorHomePage extends javax.swing.JPanel implements IPage {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    public MainForm mainForm;
    public String username;
    public String gender;

    public InstructorHomePage() {
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
                    InstructorAttendanceRecord d = new InstructorAttendanceRecord();
                    MainFrame.instance.setPagePro(d);
                    mainForm.showForm(d);
                }
                if (menuIndex == 1) {
                    InstructorExamList c = new InstructorExamList();
                    MainFrame.instance.setPagePro(c);
                    mainForm.showForm(c);

                }
                if (menuIndex == 2) {
                    InstructorCourseList b = new InstructorCourseList();
                    MainFrame.instance.setPagePro(b);
                    mainForm.showForm(b);
                }
                if (menuIndex == 3) {
                    InstructorScoreAdding a = new InstructorScoreAdding();
                    MainFrame.instance.setPagePro(a); 
                    mainForm.showForm(a);
                }
            }

        });

        menu.initMenuItemInstructor();

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
        if (account instanceof Instructor instructor) {
            System.out.println("Account is  Admin!");
            this.username = instructor.getUsername();
            this.gender = instructor.getGender();
            System.out.println(username);
            topBarInfo();
        }
    }
}
