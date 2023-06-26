package DTO;

public class TopicAssign extends Topic{
    //props
    private int TopicAssignID;
    private String SubjectCode;
    private int SubjectID;
    private String StartDate;
    private String ModifyDate;
    private String Semester;
    private boolean Status;
    
    //Contructor

    public TopicAssign(int TopicID, String TopicCode, int TopicAssignID, 
            String SubjectCode,int SubjectID, String StartDate, String ModifyDate, 
            String Semester, boolean Status){
        super(TopicID, TopicCode);
        this.TopicAssignID = TopicAssignID;
        this.SubjectCode = SubjectCode;
        this.SubjectID = SubjectID;
        this.StartDate = StartDate;
        this.ModifyDate = ModifyDate;
        this.Semester = Semester;
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

    public boolean isStatus() {
        return Status;
    }   
}
