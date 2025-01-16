package com.mycompany.schoolmanagementsystem.management;

public class Course {

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private int courseID;
    private String courseName;
    private String courseCode;
    private int credits;
    private int departmentID;
    private Integer instructorID; // Nullable
    private int classroomID;      // Non-nullable
    private int semesterID;       // Non-nullable
    private String weekDay;       // Non-nullable
    private String startTime;       // Zaman türü
    private String endTime;         // Zaman türü
    private int hours;            // Yeni özellik (ders süresi saat cinsinden)
    private int classLevel; // Yeni özellik

    // Constructor
    public Course() {
    }

    public Course(int courseID, String courseName, String courseCode, int credits,
            int departmentID, Integer instructorID, int classroomID, int semesterID,
            String weekDay, String startTime, String endTime, int hours, int classLevel) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.departmentID = departmentID;
        this.instructorID = instructorID;
        this.classroomID = classroomID;
        this.semesterID = semesterID;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hours = hours;
        this.classLevel = classLevel;
    }

    // Getter ve Setter'lar
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public Integer getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(Integer instructorID) {
        this.instructorID = instructorID;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours; // Yeni özellik
    }

    // Getter ve Setter
    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    @Override
    public String toString() {
        return courseName; // Sınıf adı
    }
}
