package DTO;

public class StudentProfile extends Person {

    //props
    private String Major;
    //constructor

    public StudentProfile(int ID, String Code, String name, String birthday,
            String PhoneNumber, String Gender, String Address, String City, String Email) {
        super(ID, Code, name, birthday, PhoneNumber, Gender, Address, City, Email);
    }

    public StudentProfile(String name, String birthday, String PhoneNumber,
            String Gender, String Address, String City, String Email) {
        super(name, birthday, PhoneNumber, Gender, Address, City, Email);
    }

    public StudentProfile(int ID, String Code, String name, String birthday,
            String PhoneNumber, String Gender, String Address, String City, String Major, String Email) {
        super(ID, Code, name, birthday, PhoneNumber, Gender, Address, City, Email);
        this.Major = Major;
    }

    public StudentProfile(int ID, String Code, String name, String Email, String Major) {
        super(ID, Code, name, Email);
        this.Major = Major;
    }

    public StudentProfile(String Email, String image) {
        super(Email, image);
    }

    //getter, setter
    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }
}
