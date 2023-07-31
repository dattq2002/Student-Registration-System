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

    public TopicAssign(String lecName, int lecID, int TopicID, String TopicCode, 
            String TopicName, String Context, String Actor, String Function) {
        super(TopicID, TopicCode, TopicName, Context, Actor, Function);
        this.lecName = lecName;
        this.lecID = lecID;
    }

    public TopicAssign(int TopicAssignID, String StartDate, String ModifyDate, int SemesterID, String Status, String lecName, int lecID, int TopicID, String TopicCode, String TopicName, String Context, String Actor, String Function) {
        super(TopicID, TopicCode, TopicName, Context, Actor, Function);
        this.TopicAssignID = TopicAssignID;
        this.StartDate = StartDate;
        this.ModifyDate = ModifyDate;
        this.SemesterID = SemesterID;
        this.Status = Status;
        this.lecName = lecName;
        this.lecID = lecID;
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
