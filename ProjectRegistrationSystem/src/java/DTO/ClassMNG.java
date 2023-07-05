/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nam An
 */
public class ClassMNG {
    private int ID;
    private int courseID;
    private String classID;
    private String semester;
    private String subject;
    private String startDate;
    private String endDate;
    private String Email;

    public ClassMNG() {
    }

    public ClassMNG(int ID, int courseID, String classID, String semester, String subject, String startDate, String endDate, String Email) {
        this.ID = ID;
        this.courseID = courseID;
        this.classID = classID;
        this.semester = semester;
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Email = Email;
    }


    

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    
    
}
