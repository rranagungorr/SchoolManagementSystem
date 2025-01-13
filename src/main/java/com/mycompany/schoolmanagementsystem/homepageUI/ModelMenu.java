package com.mycompany.schoolmanagementsystem.homepageUI;

import javax.swing.Icon;

public class ModelMenu {

       public Icon getIcon() {
        return icon;
    }

   
    public String getMenuName() {
        return menuName;
    }

    

    public String[] getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String[] subMenu) {
        this.subMenu = subMenu;
    }

    public ModelMenu(Icon icon, String menuName, String... subMenu) {
        this.icon = icon;
        this.menuName = menuName;
        this.subMenu = subMenu;
    }

    public ModelMenu() {
    }

    private Icon icon;
    private String menuName;
    private String subMenu[];
}
