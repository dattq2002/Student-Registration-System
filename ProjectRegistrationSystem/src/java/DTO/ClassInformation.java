package DTO;

public class ClassInformation extends Class{
    //props
    private int ID;
    private String SubjectCode;
    private int SubjectID;
    private String lecName;
    private boolean Status;
    //contructor

    public ClassInformation(int ID, String SubjectCode, int SubjectID, String lecName, 
            String CourseName, int CourseID,int CourseCode, String StartDate, String EndDate, boolean Status,int SemesterID) {
        super(CourseName, CourseID, StartDate, EndDate, SemesterID, CourseCode);
        this.ID = ID;
        this.SubjectCode = SubjectCode;
        this.SubjectID = SubjectID;
        this.lecName = lecName;
        this.Status = Status;
    }
    
    //getter,setter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public String getLecName() {
        return lecName;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }

    public boolean isStatus() {
        return Status;
    }
    
}
