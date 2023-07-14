package system.main.DTO;

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

    public Class(int CourseID, String CourseName, int CourseCode, int SemesterID, 
            String StartDate, String EndDate) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.CourseCode = CourseCode;
        this.SemesterID = SemesterID;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public Class(String CourseName, int CourseCode, int SemesterID) {
        this.CourseName = CourseName;
        this.CourseCode = CourseCode;
        this.SemesterID = SemesterID;
    }

    
    //--------------------------------------------------------------------------
    //getter,setter

    public int getCourseID() {
        return CourseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public int getCourseCode() {
        return CourseCode;
    }

    public int getSemesterID() {
        return SemesterID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }
    
}
