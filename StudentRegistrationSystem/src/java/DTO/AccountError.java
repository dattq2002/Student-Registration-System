package DTO;

public class AccountError {

    private String emailError;
    private String fullNameError;
    private String roleError;
    private String passwordError;
    private String confirmError;
    private String messageError;

    public AccountError() {
        this.emailError = "";
        this.fullNameError = "";
        this.roleError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.messageError = "";
    }

    public AccountError(String emailError, String fullNameError, String roleError, String passwordError, String confirmError, String messageError) {
        this.emailError = emailError;
        this.fullNameError = fullNameError;
        this.roleError = roleError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.messageError = messageError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

   
}
