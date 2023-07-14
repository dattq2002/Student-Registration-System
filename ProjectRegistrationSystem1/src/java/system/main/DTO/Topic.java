package system.main.DTO;

public class Topic {
    //props
    protected int TopicID;
    protected String TopicCode;
    protected String TopicName;
    protected String ShortDescription;
    protected String FullDescription;
    
    //constructor

    public Topic() {
    }

    
    public Topic(int TopicID, String TopicCode, String TopicName, 
            String ShortDescription, String FullDescription) {
        this.TopicID = TopicID;
        this.TopicCode = TopicCode;
        this.TopicName = TopicName;
        this.ShortDescription = ShortDescription;
        this.FullDescription = FullDescription;
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

    public String getTopicName() {
        return TopicName;
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
}
