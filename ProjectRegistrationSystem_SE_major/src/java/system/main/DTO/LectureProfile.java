package system.main.DTO;

public class LectureProfile extends Person{

    public LectureProfile(int ID, String Code, String name, String Email) {
        super(ID, Code, name, Email);
    }

    public LectureProfile(int ID, String Code, String name, String birthday, 
            String PhoneNumber, String Gender, String Address, String City, String Email) {
        super(ID, Code, name, birthday, PhoneNumber, Gender, Address, City, Email);
    }
    
}
