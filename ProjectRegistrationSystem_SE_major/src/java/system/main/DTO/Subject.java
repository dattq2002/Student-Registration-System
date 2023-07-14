package system.main.DTO;

public class Subject {
    //props
    private int SubjectID;
    private String SubjectCode;
    private String SubjectName;
    private String Description;
    private int Credit;
    
    //constructor

    public Subject(int SubjectID, String SubjectCode, String SubjectName, 
            String Description, int Credit) {
        this.SubjectID = SubjectID;
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
        this.Description = Description;
        this.Credit = Credit;
    }
    
    //getter

    public int getSubjectID() {
        return SubjectID;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getDescription() {
        return Description;
    }

    public int getCredit() {
        return Credit;
    }
    
}
