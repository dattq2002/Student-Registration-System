package system.main.DTO;

public class Topic {
    //props
    protected int TopicID;
    protected String TopicCode;
    protected String TopicName;
    protected String Context;
    protected String Actor;
    protected String Function;
    
    //constructor

    public Topic() {
    }

    
    public Topic(int TopicID, String TopicCode, String TopicName, 
            String Context, String Actor, String Function) {
        this.TopicID = TopicID;
        this.TopicCode = TopicCode;
        this.TopicName = TopicName;
        this.Context = Context;
        this.Actor = Actor;
        this.Function = Function;
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

    public String getContext() {
        return Context;
    }

    public void setContext(String Context) {
        this.Context = Context;
    }

    public String getActor() {
        return Actor;
    }

    public void setActor(String Actor) {
        this.Actor = Actor;
    }

    public String getFunction() {
        return Function;
    }

    public void setFunction(String Function) {
        this.Function = Function;
    }
    

}
