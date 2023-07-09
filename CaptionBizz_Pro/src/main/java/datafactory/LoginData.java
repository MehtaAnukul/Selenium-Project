package datafactory;

import dataobjects.Login;

public class LoginData {
    public Login getLoginData() {
        Login login = new Login();
        login.setUserName("standard_user");
        login.setPassword("secret_sauce");
        return login;
    }
}
