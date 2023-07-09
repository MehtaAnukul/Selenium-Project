package datafactory.gonnaorder.admin;

import dataobjects.gonnaorder.admin.SignUp;
import utilities.JavaHelpers;

public class SignUpData {

    /**
     * Generate test data for register user information
     *
     * @return Store object
     */
    public SignUp getSignUpDetailsData() {
        SignUp info = new SignUp();
        JavaHelpers java = new JavaHelpers();
        info.setEmail("auto" + java.getTimeStamp("yyMMddHHmmss") + "@mailinator.com");
        info.setFirstName("QA");
        info.setLastName("Automation");
        info.setCountry("India");
        info.setPhoneNumber("45454554");
        info.setLanguage("English");
        info.setPassword("123456789");
        info.setConfirmPassword("123456789");
        return info;

    }

}




