package DTO;

public class Topic {
    //props
    private int ID;
    private String code;
    private String name;
    private String lecName;
    private boolean Status;
    private String ShortDescription;
    private String FullDescription;
    
    //constructor

    public Topic() {
    }

    public Topic(int ID, String code, String name, String lecName, boolean Status, String ShortDescription, String FullDescription) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.lecName = lecName;
        this.Status = Status;
        this.ShortDescription = ShortDescription;
        this.FullDescription = FullDescription;
    }
    
    //getter,setter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
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
