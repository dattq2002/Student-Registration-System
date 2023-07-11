package DTO;

public class TopicAssign extends Topic{
    //props
    private int TopicAssignID;
    private String SubjectCode;
    private int SubjectID;
    private String StartDate;
    private String ModifyDate;
    private String Semester;
    private String Status;
    
    //Contructor

    public TopicAssign(int TopicID, String TopicCode, int TopicAssignID, 
            String SubjectCode,int SubjectID, String StartDate, String ModifyDate, 
            String Semester, String Status, String lecName){
        super(TopicID, TopicCode, lecName);
        this.TopicAssignID = TopicAssignID;
        this.SubjectCode = SubjectCode;
        this.SubjectID = SubjectID;
        this.StartDate = StartDate;
        this.ModifyDate = ModifyDate;
        this.Semester = Semester;
        this.Status = Status;
    }
  
    //list Subject

    public TopicAssign(String SubjectCode, int SubjectID,
            int TopicID, String TopicCode) {
        super(TopicID, TopicCode);
        this.SubjectCode = SubjectCode;
        this.SubjectID = SubjectID;
    }

    public TopicAssign(int SubjectID, String Status, int TopicID, String TopicCode, 
            String name, String lecName, String ShortDescription, 
            String FullDescription, int SemesterID, int lecturerID) {
        super(TopicID, TopicCode, name, lecName, ShortDescription, 
                FullDescription, SemesterID, lecturerID);
        this.SubjectID = SubjectID;
        this.Status = Status;
    }
    

    //getter,setter

    public int getTopicAssignID() {
        return TopicAssignID;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getModifyDate() {
        return ModifyDate;
    }

    public String getSemester() {
        return Semester;
    }

    public String getStatus() {
        return Status;
    }   

    public void setTopicAssignID(int TopicAssignID) {
        this.TopicAssignID = TopicAssignID;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public void setModifyDate(String ModifyDate) {
        this.ModifyDate = ModifyDate;
    }

    public void setSemester(String Semester) {
        this.Semester = Semester;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
