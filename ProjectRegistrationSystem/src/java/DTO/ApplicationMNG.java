package DTO;

public class ApplicationMNG {

    private int ID;
    private String type;
    private String subjectID;
    private String courseID;
    private int groupID;
    private String studentID;
    private String reason;
    private String createDate;
    private String note;
    private String email;
    private int room;
    private String presentDate;
    private String time;
    private String status;

    public ApplicationMNG() {
    }

    public ApplicationMNG(int ID, String type, String subjectID, String courseID, 
            
            int groupID, String studentID, String reason, String createDate, 
            String note, int room, String presentDate, String time) {
        this.ID = ID;
        this.type = type;
        this.subjectID = subjectID;
        this.courseID = courseID;
        this.groupID = groupID;
        this.studentID = studentID;
        this.reason = reason;
        this.createDate = createDate;
        this.note = note;
        this.room = room;
        this.presentDate = presentDate;
        this.time = time;
    }    
    
    public ApplicationMNG(int ID, String type, String subjectID, String courseID, 
            int groupID, String studentID, String reason, String createDate, String note) {
        this.ID = ID;
        this.type = type;
        this.subjectID = subjectID;
        this.courseID = courseID;
        this.groupID = groupID;
        this.studentID = studentID;
        this.reason = reason;
        this.createDate = createDate;
        this.note = note;
    }

    public ApplicationMNG(int ID, String note) {
        this.ID = ID;
        this.note = note;
    }

    public ApplicationMNG(int ID, String note, int room, String presentDate, String time) {
        this.ID = ID;
        this.note = note;
        this.room = room;
        this.presentDate = presentDate;
        this.time = time;
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
