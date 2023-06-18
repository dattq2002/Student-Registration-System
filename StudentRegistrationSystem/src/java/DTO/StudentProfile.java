package DTO;

public class StudentProfile extends Person{
    //props
    private String Major;
    //constructor

    public StudentProfile(int ID, String Code, String name, String birthday, 
            String PhoneNumber, String Gender, String Address, String City) {
        super(ID, Code, name, birthday, PhoneNumber, Gender, Address, City);
    }

    public StudentProfile(int ID, String Code, String name, String birthday, 
            String PhoneNumber, String Gender, String Address, String City, String Major) {
        super(ID, Code, name, birthday, PhoneNumber, Gender, Address, City);
        this.Major = Major;
    }
      
    //getter, setter

    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }   
}
