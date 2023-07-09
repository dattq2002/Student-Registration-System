package DTO;

public class GroupProject extends Group{
    //props
    private String Context;
    private String Actors;
    private String Function;
    private String Note;
    private int ProjectID;
    
    //constructor
    public GroupProject(String Context, String Actors, String Function, 
            String Note, String GroupName, int CourseID, int TopicID) {
        super(GroupName, CourseID,TopicID);
        this.Context = Context;
        this.Actors = Actors;
        this.Function = Function;
        this.Note = Note;
    }

    public GroupProject(String Context, String Actor, String Function, 
            String Note, int ProjectID, int TopicID, String TopicName) {
        super(TopicID, TopicName);
        this.Context = Context;
        this.Actors = Actor;
        this.Function = Function;
        this.Note = Note;
        this.ProjectID = ProjectID;
    }

    public GroupProject(String Context, String Actors, String Function, 
            String Note, int ProjectID, int TopicID) {
        super(TopicID);
        this.Context = Context;
        this.Actors = Actors;
        this.Function = Function;
        this.Note = Note;
        this.ProjectID = ProjectID;
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
    
}
