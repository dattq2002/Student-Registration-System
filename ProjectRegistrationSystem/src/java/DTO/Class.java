package DTO;

public class Class {

    //props
    protected int CourseID;
    protected String CourseName;
    protected int CourseCode;
    protected int SemesterID;
    protected String StartDate;
    protected String EndDate;

    //contructors
    public Class() {
    }

    public Class(int CourseID, String CourseName, int CourseCode) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.CourseCode = CourseCode;
    }

    public Class(String CourseName, int CourseID, String StartDate, String EndDate, int SemesterID, int CourseCode) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.SemesterID = SemesterID;
        this.CourseCode = CourseCode;
    }

    public Class(int CourseID, String CourseName, int SemesterID, String StartDate, String EndDate) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.SemesterID = SemesterID;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public Class(int CourseID, String CourseName, String StartDate) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.StartDate = StartDate;
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

    public int getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(int CourseCode) {
        this.CourseCode = CourseCode;
    }

}
