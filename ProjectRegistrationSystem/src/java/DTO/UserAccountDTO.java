package DTO;

public class UserAccountDTO {
    //props
    private int ID;
    private String Code;
    private String Email;
    private String Password;
    private String RoleID;
    private String FullName;
    private String Status;

    //contructor

    public UserAccountDTO(String RoleID, String FullName, String Status) {
        this.RoleID = RoleID;
        this.FullName = FullName;
        this.Status = Status;
    }
    
    public UserAccountDTO(int ID, String Code, String Email, String Password, String RoleID, String FullName, String Status) {
        this.ID = ID;
        this.Code = Code;
        this.Email = Email;
        this.Password = Password;
        this.RoleID = RoleID;
        this.FullName = FullName;
        this.Status = Status;
    }

    public UserAccountDTO(String Code, String Email, String Password, String RoleID, String FullName, String Status) {
        this.Code = Code;
        this.Email = Email;
        this.Password = Password;
        this.RoleID = RoleID;
        this.FullName = FullName;
        this.Status = Status;
    }
    
    
    //getter,setter
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return Code;
    }
    
    public void setCode(String code) {    
        this.Code = code;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String roleID) {
        this.RoleID = roleID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }   
}
