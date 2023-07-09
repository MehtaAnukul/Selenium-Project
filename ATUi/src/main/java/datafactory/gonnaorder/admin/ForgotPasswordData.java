package datafactory.gonnaorder.admin;

import dataobjects.gonnaorder.admin.ForgotPassword;
import utilities.JavaHelpers;

public class ForgotPasswordData {

    /**
     * Generate test data for register user information
     *
     * @return Store object
     */
    public ForgotPassword getForgotPasswordData() {
        JavaHelpers java = new JavaHelpers();
        ForgotPassword info = new ForgotPassword();
        info.setResetPassword("test@123");
        info.setResetConfirmPassword("test@123");
        return info;

    }

}




