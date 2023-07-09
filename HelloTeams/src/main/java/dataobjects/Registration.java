package dataobjects;

public class Registration {
    private String nameTextBox;
    private String email;
    private String password;
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNameTextBox() {
        return nameTextBox;
    }

    public void setNameTextBox(String nameTextBox) {
        this.nameTextBox = nameTextBox;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
