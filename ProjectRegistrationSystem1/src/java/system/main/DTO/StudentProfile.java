package system.main.DTO;

public class StudentProfile extends Person{

    //props
    private String Major;
    //constructor

    public StudentProfile(int ID, String Code, String name, String birthday, 
            String PhoneNumber, String Gender, String Address, String City, String Email) {
        super(ID, Code, name, birthday, PhoneNumber, Gender, Address, City, Email);
    }        

    public StudentProfile(int ID, String Code, String name) {
        super(ID, Code, name);
    }
    
    //getter, setter
    public String getMajor() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major = Major;
    }
    
}
