package DTO;

public class LectureProfile {
    //props
    private int ID;
    private String Code;
    private String name;
    private String birthday;
    private String email;
    //constructor

    public LectureProfile() {
    }

    public LectureProfile(int ID, String Code, String name, String birthday, String email) {
        this.ID = ID;
        this.Code = Code;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
    }
    
    //getter, setter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
