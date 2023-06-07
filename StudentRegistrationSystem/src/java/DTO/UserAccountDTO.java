package DTO;

public class UserAccountDTO {
    //props
    private String Email;
    private String Password;
    private String FullName;
    private String roleID;

    //contructor
    public UserAccountDTO() {
    }

    public UserAccountDTO(String Email, String Password, String FullName, String roleID) {
        this.Email = Email;
        this.Password = Password;
        this.FullName = FullName;
        this.roleID = roleID;
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
}
