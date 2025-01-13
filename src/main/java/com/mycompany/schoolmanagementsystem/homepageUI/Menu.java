/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.schoolmanagementsystem.homepageUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Merve
 */
public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItemAdmin() {
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Manage Student"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Manage Instructor"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Manage Courses"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Manage Course Schedule"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Manage Exams"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Exam Record"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Student"));
        

    }
    
     public void initMenuItemStudent() {
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Attandance List"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Course List"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Exam List"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Score List"));
      
    }
     
     public void initMenuItemInstructor() {
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Course List"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Exam List"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Attandence Record"));
        addMenu(new ModelMenu(new ImageIcon("C:\\Users\\Merve\\OneDrive\\Desktop\\icons\\6.png"), "Score Record"));
        

    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()),"h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                System.out.println("Menu press");
                return false;
            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile1 = new com.mycompany.schoolmanagementsystem.homepageUI.Profile();

        setBackground(new java.awt.Color(78, 216, 221));
        setPreferredSize(new java.awt.Dimension(400, 230));

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(profile1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private com.mycompany.schoolmanagementsystem.homepageUI.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
