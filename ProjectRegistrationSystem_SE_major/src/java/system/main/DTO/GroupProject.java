package system.main.DTO;

public class GroupProject extends Group{
    //props
    private int ProjectID;
    private String ProjectCode;
    private String Context;
    private String Actors;
    private String Function;
    private String Note;
    private int TopicID;
    private String TopicCode;
    private String TopicName;
    private String EnrollDate;
    
    //constructor

    public GroupProject(int ProjectID, String Context, 
            String Actors, String Function, String Note, int TopicID, 
            int GroupID, String TopicName) {
        super(GroupID);
        this.ProjectID = ProjectID;
        this.Context = Context;
        this.Actors = Actors;
        this.Function = Function;
        this.Note = Note;
        this.TopicID = TopicID;
        this.TopicName = TopicName;
    }

    public GroupProject(int TopicID, String TopicCode, int GroupID, 
            String GroupName, int CourseID, String GroupCode) {
        super(GroupID, GroupName, CourseID, GroupCode);
        this.TopicID = TopicID;
        this.TopicCode = TopicCode;
    }

    public GroupProject(String Context, String Actor, String Function, 
            String Note, int ProjectID, int TopicID, String TopicName) {
        this.TopicID = TopicID;
        this.TopicName = TopicName;
        this.Context = Context;
        this.Actors = Actor;
        this.Function = Function;
        this.Note = Note;
        this.ProjectID = ProjectID;
    }

    public GroupProject(String StudentName, 
            int GroupID, String GroupName, String StartDate, 
            String Major, String isLeader, String StudentCode, 
            int StudentID, int CourseID, int SubjectID, String subCode, 
            int TopicID, String TopicCode) {
        super(StudentName, GroupID, GroupName, StartDate, Major, isLeader, 
                StudentCode, StudentID, CourseID, SubjectID, subCode);
        this.TopicID = TopicID;
        this.TopicCode = TopicCode;
    }


    
    //getter
    public String getContext() {
        return Context;
    }

    public String getActors() {
        return Actors;
    }

    public String getFunc() {
        return Function;
    }

    public String getNote() {
        return Note;
    }

    public String getFunction() {
        return Function;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public String getProjectCode() {
        return ProjectCode;
    }

    public int getTopicID() {
        return TopicID;
    }

    public String getTopicCode() {
        return TopicCode;
    }

    public String getEnrollDate() {
        return EnrollDate;
    }

    public String getTopicName() {
        return TopicName;
    }
    
}
