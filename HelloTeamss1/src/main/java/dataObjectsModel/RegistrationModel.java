package dataObjectsModel;

public class RegistrationModel {

    private String nameTextBox;
    private String emailTextBox;
    private String passwordTextBox;
    private String confirmPasswordTextBox;

    /*public RegistrationModel(String nameTextBox, String emailTextBox, String passwordTextBox, String confirmPasswordTextBox) {
        this.nameTextBox = nameTextBox;
        this.emailTextBox = emailTextBox;
        this.passwordTextBox = passwordTextBox;
        this.confirmPasswordTextBox = confirmPasswordTextBox;
    }*/

    public String getNameTextBox() {
        return nameTextBox;
    }

    public void setNameTextBox(String nameTextBox) {
        this.nameTextBox = nameTextBox;
    }

    public String getEmailTextBox() {
        return emailTextBox;
    }

    public void setEmailTextBox(String emailTextBox) {
        this.emailTextBox = emailTextBox;
    }

    public String getPasswordTextBox() {
        return passwordTextBox;
    }

    public void setPasswordTextBox(String passwordTextBox) {
        this.passwordTextBox = passwordTextBox;
    }

    public String getConfirmPasswordTextBox() {
        return confirmPasswordTextBox;
    }

    public void setConfirmPasswordTextBox(String confirmPasswordTextBox) {
        this.confirmPasswordTextBox = confirmPasswordTextBox;
    }
}
