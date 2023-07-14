package system.main.DTO;

public class ClassInformation extends Class{
    //props
    private int ID;
    private String SubjectCode;
    private int SubjectID;
    private String lecName;
    private boolean Status;
    private int lecID;
    //contructor

    public ClassInformation(String lecName, int CourseID, String CourseName, 
            int CourseCode, int SemesterID, String StartDate, String EndDate) {
        super(CourseID, CourseName, CourseCode, SemesterID, StartDate, EndDate);
        this.lecName = lecName;
    }

    public ClassInformation(String SubjectCode, int SubjectID, int lecID, 
            String CourseName, int CourseCode, int SemesterID) {
        super(CourseName, CourseCode, SemesterID);
        this.SubjectCode = SubjectCode;
        this.SubjectID = SubjectID;
        this.lecID = lecID;
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

    public int getLecID() {
        return lecID;
    }
    
}
