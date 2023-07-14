package system.main.DTO;

public class LectureProfile extends Person{

    public LectureProfile(int ID, String Code, String name, String birthday, String PhoneNumber, String Gender, String Address, String City, String Email, String image) {
        super(ID, Code, name, birthday, PhoneNumber, Gender, Address, City, Email, image);
    }

    public int getID() {
        return ID;
    }

    public String getCode() {
        return Code;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getEmail() {
        return Email;
    }

    public String getImage() {
        return image;
    }
    
}
