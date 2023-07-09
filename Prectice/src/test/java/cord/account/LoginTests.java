package cord.account;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.cord.account.LoginPO;
import pageobjects.cord.common.HeaderPO;
import utilities.Constants;

public class LoginTests extends BaseTest {

    /*Test 1 : Verify that user can login successfully*/
    @Test
    public void verifyLoginWithValidCredentials() throws InterruptedException {

        LoginPO login = new LoginPO(driver);
        HeaderPO header = new HeaderPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3- Verify that user is logged in successfully by comparing account name");
        String expectedName = "Hi, " + "Friend";
        Assert.assertEquals(header.getProfileName(), expectedName, "Profile name doesn't match");
    }

    /*Test 2 : Verify that user can't login with invalid credentials*/
    @Test
    public void verifyLoginWithInvalidCredentials() throws InterruptedException {

        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering invalid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin("piyush@temp.com", "1111");

        Reporter.log("Step 3- Verify that validation message is displayed");
        String expectedErrorMessage = "Something wasn't right. Try again, or reset password.";
        Assert.assertEquals(login.getErrorMessage(), expectedErrorMessage, "Error Message doesn't match");
    }


    /*Test 3 : Verify that user can logout successfully*/
    @Test
    public void verifyUserCanLogoutSuccessfully() throws InterruptedException {

        LoginPO login = new LoginPO(driver);
        HeaderPO header = new HeaderPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on Profile Icon and click on Sign Out button");
        header.signOut();

        Reporter.log("Step 4- Verify that login page message displayed");
        String expectedUrl = Constants.URL + "/login?returnTo=%2Fprojects";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }

    /*Test 4 : Verify that 'Forgot Password?' link works as expected*/
    @Test
    public void verifyForgotPasswordLinkWorkProperly() throws InterruptedException {

        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Click on Forgot password link");
        login.clickOnForgotPasswordLink();

        Reporter.log("Step 3- Verify that forgot password page is displayed");
        String expectedUrl = Constants.URL + "/forgot-password";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }


}
