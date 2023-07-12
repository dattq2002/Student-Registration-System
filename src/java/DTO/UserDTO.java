package DTO;

public class UserDTO {
    //props
    private String Email;
    private String Password;
    private String FullName;
    private String roleID;
    private String Status;
    private int ID;
    private String code;

    //contructor
    public UserDTO() {
    }

    public UserDTO(String Email, String Password, String roleID, String FullName) {
        this.Email = Email;
        this.Password = Password;      
        this.roleID = roleID;
        this.FullName = FullName;
    }

    public UserDTO(String Email, String Password, String FullName, String roleID, String Status, int ID, String code) {
        this.Email = Email;
        this.Password = Password;
        this.FullName = FullName;
        this.roleID = roleID;
        this.Status = Status;
        this.ID = ID;
        this.code = code;
    }
    
    //getter,setter

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

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
    
    
}
