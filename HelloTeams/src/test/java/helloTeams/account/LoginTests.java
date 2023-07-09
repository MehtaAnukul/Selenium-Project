package helloTeams.account;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.helloteams.account.LoginPO;
import pageobjects.helloteams.account.OrganizationDashboardPO;
import utilities.Constants;

public class LoginTests extends BaseTest {

    /*Test 1 : Verify that user can login successfully*/
    @Test
    public void verifyLoginWithValidCredentials1() throws InterruptedException {

        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3- Verify that user is logged in successfully by checking organization text");
        String expectedResult = "Create New Organization";
        Assert.assertEquals(organization.getTextCreateOrganization(),expectedResult,"User is not able to login successfully");
    }

    /*Test 2 : Verify that user can't login with invalid credentials*/
    @Test
    public void verifyLoginWithInvalidCredentials() throws InterruptedException {

        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Entering invalid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin("piyush@test.com", "11111");

        Reporter.log("Step 3- Verify that validation message is displayed");
        String expectedErrorMessage = "Invalid email address or password";
        Assert.assertEquals(login.getInvalidDataValidationMessage(), expectedErrorMessage, "Error Message doesn't match");
    }

    /*Test 3 : Verify that user can't login without confirm account*/
    @Test
    public void verifyLoginWithoutConfirmAccount() throws InterruptedException {

        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Entering details without confirming account and login to application");
        login.fillLoginDetailsAndPerformLogin("piyush+11@helloteams.io", "Piyush+10");

        Reporter.log("Step 3- Verify that validation message is displayed");
        String expectedErrorMessage = "Invalid email address or password";
        Assert.assertEquals(login.getInvalidDataValidationMessage(), expectedErrorMessage, "Error Message doesn't match");
    }

    /*Test 4 : Verify that 'Forgot Password?' link works as expected*/
    @Test
    public void verifyForgotPasswordLinkWorkProperly() throws InterruptedException {

        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Click on Forgot password link");
        login.clickOnForgotPasswordLink();

        Reporter.log("Step 3- Verify that forgot password page is displayed");
        String expectedUrl = Constants.URL + "/resetrequest";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }
}
