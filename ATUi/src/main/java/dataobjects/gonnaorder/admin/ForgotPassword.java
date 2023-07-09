package dataobjects.gonnaorder.admin;

public class ForgotPassword {
    private String email;
    private String ResetPassword;
    private String ResetConfirmPassword;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getResetPassword()
    {

        return ResetPassword;
    }

    public void setResetPassword(String ResetPassword)
    {
        this.ResetPassword = ResetPassword;
    }

    public String getResetConfirmPassword()
    {
        return ResetConfirmPassword;
    }

    public void setResetConfirmPassword(String ResetConfirmPassword)
    {
        this.ResetConfirmPassword = ResetConfirmPassword;
    }


}
