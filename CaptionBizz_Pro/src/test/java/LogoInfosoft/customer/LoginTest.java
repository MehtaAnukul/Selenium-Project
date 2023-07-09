package LogoInfosoft.customer;

import base.BaseTest;
import datafactory.LoginData;
import dataobjects.Login;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.LogoInfosoft.customer.Login.LoginPO;
import utilities.Constants;


public class LoginTest extends BaseTest {

    /*Test 1: Verify that user successfully login into the application '*/

    @Test
    public void testApplication() throws InterruptedException {

        LoginPO login = new LoginPO(driver);
        Login data = new LoginData().getLoginData();

        Reporter.log("Step 1: Navigate to URL");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2: Enter login credentials and click on the login button");
        login.enterLoginCredentialsAndClickOnLoginButton(data);

    }
}
