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
public class PresentationMNG {

    private int ID;
    private String type;
    private String subjectID;
    private String courseID;
    private int groupID;
    private String email;
    private int room;
    private String presentDate;
    private String time;
    private String note;

    public PresentationMNG() {
    }

    public PresentationMNG(int ID, String type, String subjectID, String courseID, int groupID, int room, String presentDate, String time) {
        this.ID = ID;
        this.type = type;
        this.subjectID = subjectID;
        this.courseID = courseID;
        this.groupID = groupID;
        this.room = room;
        this.presentDate = presentDate;
        this.time = time;
    }

    public PresentationMNG(int ID, String type, String subjectID, String courseID, int groupID, String email, int room, String presentDate, String time, String note) {
        this.ID = ID;
        this.type = type;
        this.subjectID = subjectID;
        this.courseID = courseID;
        this.groupID = groupID;
        this.email = email;
        this.room = room;
        this.presentDate = presentDate;
        this.time = time;
        this.note = note;
    }
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(String presentDate) {
        this.presentDate = presentDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
