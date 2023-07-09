package dataFactory;

import dataObjectsModel.LoginModel;
import utilities.JavaHelpers;

public class LoginData {

    /**
     * Declared variable and create objects
     */

    LoginModel loginModel = new LoginModel();

    String timeStamp = new JavaHelpers().timeStamp();
    String userName = "standard_user";
    String password = "secret_sauce";

    /**
     * Set login data
     *
     * @return LoginSwagLabs object
     */
    public LoginModel getLoginModelData() {
        loginModel.setUserNameTextBox(userName);
        loginModel.setPasswordTextBox(password);
        return loginModel;
    }

    /* *//**
     * Set login data with invalid Domain(username)
     * @return LoginSwagLabs object
     *//*
    public LoginModel getInvalidDomainData(){
        loginModel.setUserNameTextBox(userName + timeStamp+ "@");
        loginModel.setPasswordTextBox(password);
        return loginModel;
    }*/

}
