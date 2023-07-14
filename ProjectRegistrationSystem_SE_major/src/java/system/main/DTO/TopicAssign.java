package system.main.DTO;

public class TopicAssign extends Topic{
    //props
    private int TopicAssignID;
    private String SubjectCode;
    private int SubjectID;
    private String StartDate;
    private String ModifyDate;
    private int SemesterID;
    private String Status;
    private String lecName;
    private int lecID;
    //Contructor

    public TopicAssign(int TopicAssignID,String StartDate, String ModifyDate, 
            int SemesterID, String Status, int TopicID, String TopicCode, 
            String TopicName, String lecName, String ShortDescription, String FullDescription, int lecID) {
        super(TopicID, TopicCode, TopicName, ShortDescription, FullDescription);
        this.TopicAssignID = TopicAssignID;
        this.StartDate = StartDate;
        this.ModifyDate = ModifyDate;
        this.SemesterID = SemesterID;
        this.Status = Status;
        this.lecName = lecName;
        this.lecID = lecID;
    }

    public TopicAssign(String lecName, int lecID, int TopicID, String TopicCode, 
            String TopicName, String ShortDescription, String FullDescription) {
        super(TopicID, TopicCode, TopicName, ShortDescription, FullDescription);
        this.lecName = lecName;
        this.lecID = lecID;
    }

    public TopicAssign(int SubjectID, int SemesterID, int TopicID) {
        super(TopicID);
        this.SubjectID = SubjectID;
        this.SemesterID = SemesterID;
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

    public int getSemesterID() {
        return SemesterID;
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

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getLecName() {
        return lecName;
    }

    public int getLecID() {
        return lecID;
    }
    
    
}
