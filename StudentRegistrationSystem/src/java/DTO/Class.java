package DTO;

public class Class {

    //props
    protected int CourseID;
    protected String CourseName;
    protected int SemesterID;
    protected boolean Status;
    protected String StartDate;
    protected String EndDate;

    //contructors
    public Class() {
    }

    public Class(String CourseName, int CourseID, String StartDate, String EndDate, boolean Status) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.Status = Status;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public Class(int CourseID, String CourseName, int SemesterID, boolean Status, String StartDate, String EndDate) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.SemesterID = SemesterID;
        this.Status = Status;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    //getter,setter
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

    public int getSemesterID() {
        return SemesterID;
    }

    public void setSemesterID(int SemesterID) {
        this.SemesterID = SemesterID;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

}
