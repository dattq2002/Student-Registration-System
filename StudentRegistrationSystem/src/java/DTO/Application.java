package DTO;

public class Application {

    //props
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

    //constructor
    public Application() {
    }

    //fill_form
    public Application(String type, int LecID, int StuID, String reason, int CourseID) {
        this.type = type;
        this.LecID = LecID;
        this.StuID = StuID;
        this.reason = reason;
        this.CourseID = CourseID;
    }

    public Application(int ID, int StuID, String StuCode, String CreateDate, 
       String type, int LecID, String lecName, String reason, String LecNote,
            String Status, int CourseID,String CourseName, String processDate) {
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
    }

    //getter,setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLecID() {
        return LecID;
    }

    public void setLecID(int LecID) {
        this.LecID = LecID;
    }

    public int getStuID() {
        return StuID;
    }

    public void setStuID(int StuID) {
        this.StuID = StuID;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLecName() {
        return lecName;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }

    public String getStuCode() {
        return StuCode;
    }

    public void setStuCode(String StuCode) {
        this.StuCode = StuCode;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

}
