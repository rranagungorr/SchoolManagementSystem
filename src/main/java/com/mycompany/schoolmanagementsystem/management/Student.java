package com.mycompany.schoolmanagementsystem.management;

public class Student {

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getGeneralAverage() {
        return generalAverage;
    }

    public void setGeneralAverage(Double generalAverage) {
        this.generalAverage = generalAverage;
    }

    private int studentID;
    private String name;
    private String surname;
    private int credits;
    private int classLevel;
    private String email;
    private int departmentID; // NULL olabilir
    private int semesterID;   // Yeni ekleme
    private String username;
    private String password;
    private String gender;
    private Double generalAverage; // Yeni ekleme

    // Constructor'lar
    public Student() {
    }

    public Student(int studentID, String name, String surname, int credits,
            int classLevel, String email, int departmentID, int semesterID,
            String username, String password, String gender, Double generalAverage) {
        this.studentID = studentID;
        this.name = name;
        this.surname = surname;
        this.credits = credits;
        this.classLevel = classLevel;
        this.email = email;
        this.departmentID = departmentID;
        this.semesterID = semesterID;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.generalAverage = generalAverage;
    }

    @Override
    public String toString() {
        return name + " " + surname; // Eğitmenin adı ve soyadı
    }
}
