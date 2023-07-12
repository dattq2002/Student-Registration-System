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
public class Application {

    private String type;
    private int ID;
    private int LecID;
    private String lecName;
    private int StuID;
    private String StuCode;
    private String reason;
    private String LecNote;
    private String CreateDate;
    private String processDate;
    private String Status;
    private int CourseID;
    private String CourseName;
    private int subID;
    private String subCode;
    private int grID;
    private String grName;
    private String room;
    private String presentDate;
    private String time;
    private String Email;
    private String publish;
    private int topicID;
    private String topicCode;

    public Application(int ID, String LecNote, String Status) {
        this.ID = ID;
        this.LecNote = LecNote;
        this.Status = Status;
    }

    
    
    public Application(String type, int ID, int LecID, String lecName, int StuID, String StuCode, String reason, String LecNote, String CreateDate, String processDate, String Status, int CourseID, String CourseName, int subID, String subCode, int grID, String grName, int topicID, String topicCode) {
        this.type = type;
        this.ID = ID;
        this.LecID = LecID;
        this.lecName = lecName;
        this.StuID = StuID;
        this.StuCode = StuCode;
        this.reason = reason;
        this.LecNote = LecNote;
        this.CreateDate = CreateDate;
        this.processDate = processDate;
        this.Status = Status;
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.subID = subID;
        this.subCode = subCode;
        this.grID = grID;
        this.grName = grName;
        this.topicID = topicID;
        this.topicCode = topicCode;
    }

    public Application(String type, int ID, int LecID, String lecName, int StuID, String StuCode, String reason, String LecNote, String CreateDate, String processDate, String Status, int CourseID, String CourseName, int subID, String subCode, int grID, String grName, String room, String presentDate, String time, String publish, int topicID, String topicCode) {
        this.type = type;
        this.ID = ID;
        this.LecID = LecID;
        this.lecName = lecName;
        this.StuID = StuID;
        this.StuCode = StuCode;
        this.reason = reason;
        this.LecNote = LecNote;
        this.CreateDate = CreateDate;
        this.processDate = processDate;
        this.Status = Status;
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.subID = subID;
        this.subCode = subCode;
        this.grID = grID;
        this.grName = grName;
        this.room = room;
        this.presentDate = presentDate;
        this.time = time;
        this.publish = publish;
        this.topicID = topicID;
        this.topicCode = topicCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLecID() {
        return LecID;
    }

    public void setLecID(int LecID) {
        this.LecID = LecID;
    }

    public String getLecName() {
        return lecName;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }

    public int getStuID() {
        return StuID;
    }

    public void setStuID(int StuID) {
        this.StuID = StuID;
    }

    public String getStuCode() {
        return StuCode;
    }

    public void setStuCode(String StuCode) {
        this.StuCode = StuCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLecNote() {
        return LecNote;
    }

    public void setLecNote(String LecNote) {
        this.LecNote = LecNote;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public int getSubID() {
        return subID;
    }

    public void setSubID(int subID) {
        this.subID = subID;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public int getGrID() {
        return grID;
    }

    public void setGrID(int grID) {
        this.grID = grID;
    }

    public String getGrName() {
        return grName;
    }

    public void setGrName(String grName) {
        this.grName = grName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    
}
