package DTO;

public class Subject {
    //props
    private int ID;
    private String code;
    private String name;
    private String Description;
    private int Credit;
    //contructor

    public Subject() {
    }

    public Subject(int ID, String code, String name, String Description, int Credit) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.Description = Description;
        this.Credit = Credit;
    }
    
    //getter, setter

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int Credit) {
        this.Credit = Credit;
    }
    
}
