package DTO;

public class Topic {
    //props
    protected int TopicID;
    protected String TopicCode;
    protected String name;
    protected String lecName;
    protected String ShortDescription;
    protected String FullDescription;
    protected int SemesterID;
    
    //constructor

    public Topic(int TopicID, String TopicCode) {
        this.TopicID = TopicID;
        this.TopicCode = TopicCode;
    }

    public Topic(int TopicID, String TopicCode, String name, String lecName, 
            String ShortDescription, String FullDescription, int SemesterID) {
        this.TopicID = TopicID;
        this.TopicCode = TopicCode;
        this.name = name;
        this.lecName = lecName;
        this.ShortDescription = ShortDescription;
        this.FullDescription = FullDescription;
        this.SemesterID = SemesterID;
    }

    
    
    //getter,setter

    public int getTopicID() {
        return TopicID;
    }

    public void setTopicID(int ID) {
        this.TopicID = ID;
    }

    public String getTopicCode() {
        return TopicCode;
    }

    public void setTopicCode(String TopicCode) {
        this.TopicCode = TopicCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecName() {
        return lecName;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }
    
    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String ShortDescription) {
        this.ShortDescription = ShortDescription;
    }

    public String getFullDescription() {
        return FullDescription;
    }

    public void setFullDescription(String FullDescription) {
        this.FullDescription = FullDescription;
    }
    
    public int getSemesterID() {
        return SemesterID;
    }
}
