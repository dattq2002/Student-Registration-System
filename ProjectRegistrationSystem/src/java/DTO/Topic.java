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
    protected int lecturerID;
    
    //constructor

    public Topic(int TopicID, String TopicCode, String lecName) {
        this.TopicID = TopicID;
        this.TopicCode = TopicCode;
        this.lecName = lecName;
    }

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

    public Topic(int topicID, String topicCode, String topicName, 
            String shortDescription, String fullDescription) {
        this.TopicID = topicID;
        this.TopicCode = topicCode;
        this.name = topicName;
        this.ShortDescription = shortDescription;
        this.FullDescription = fullDescription;
    }

    public Topic(int topicID, String topicCode, String topicName, int lecturerID, 
            String shortDescription, String fullDescription, int semester) {
        this.TopicID = topicID;
        this.TopicCode = topicCode;
        this.name = topicName;
        this.lecturerID = lecturerID;
        this.ShortDescription = shortDescription;
        this.FullDescription = fullDescription;
        this.SemesterID = semester;
    }
     
    public Topic(int topicID, String topicCode, String topicName, int lecturerID, 
            String shortDescription, String fullDescription) {
        this.TopicID = topicID;
        this.TopicCode = topicCode;
        this.name = topicName;
        this.lecturerID = lecturerID;
        this.ShortDescription = shortDescription;
        this.FullDescription = fullDescription;
    }

    public Topic(int TopicID, String TopicCode, String name, String lecName, 
            String ShortDescription, String FullDescription, 
            int SemesterID, int lecturerID) {
        this.TopicID = TopicID;
        this.TopicCode = TopicCode;
        this.name = name;
        this.lecName = lecName;
        this.ShortDescription = ShortDescription;
        this.FullDescription = FullDescription;
        this.SemesterID = SemesterID;
        this.lecturerID = lecturerID;
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

    public void setSemesterID(int SemesterID) {
        this.SemesterID = SemesterID;
    }

    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }
    
}
