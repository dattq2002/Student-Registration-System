package DTO;

public abstract class Person {
    //props
    protected int ID;
    protected String Code;
    protected String name;
    protected String birthday;
    protected String PhoneNumber;
    protected String Gender;
    protected String Address;
    protected String City;
    protected String Email;
    
    //Contructor

    public Person(int ID, String Code, String name, String birthday, String PhoneNumber, String Gender, String Address, String City, String Email) {
        this.ID = ID;
        this.Code = Code;
        this.name = name;
        this.birthday = birthday;
        this.PhoneNumber = PhoneNumber;
        this.Gender = Gender;
        this.Address = Address;
        this.City = City;
        this.Email = Email;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
