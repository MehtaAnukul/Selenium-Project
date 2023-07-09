package account;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.swagLabs.account.LoginPO;
import utilities.Constants;

public class LoginTest extends BaseTest {

    /*Test 1 : Verify that user can login successfully*/
    @Test
    public void verifyThatUserCanLoginSuccessfully() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), user.getPassword());

        Reporter.log("Step 3 - Verify that User navigate to Product page");
        String expectedProductUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedProductUrl, "Url doesn't match");
    }

    /* Test 2 : Verify that validation message is displayed if user 'Login' without entering credentials */
    @Test
    public void verifyValidationMessageDisplayedForUserNameAndPasswordFieldBlankAndClickOnLoginButton() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Click on Login button without entering credentials");
        loginPO.fillLoginDetailsAndPerformLogin("", "");

        Reporter.log("Step 3 - Verify that validation message is displayed");
        String expectedErrorMessage = "Epic sadface: Username is required";
        Assert.assertEquals(loginPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error message doesn't match");
    }

    /* Test 3 : Verify that validation message is displayed for password field */
    @Test
    public void verifyValidationMessageDisplayedForPasswordFieldBlankAndClickOnLoginButton() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Click on Login button without entering password");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), "");

        Reporter.log("Step 3 - Verify that validation message is displayed");
        String expectedErrorMessage = "Epic sadface: Password is required";
        Assert.assertEquals(loginPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error message doesn't match");
    }

    /* Test 4 : Verify that validation message is displayed for userName field */
    @Test
    public void verifyValidationMessageDisplayedForUserNameFieldBlankAndClickOnLoginButton() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Click on Login button without entering userName");
        loginPO.fillLoginDetailsAndPerformLogin("", user.getPassword());

        Reporter.log("Step 3 - Verify that validation message is displayed");
        String expectedErrorMessage = "Epic sadface: Username is required";
        Assert.assertEquals(loginPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error Message doesn't match");
    }

    /* Test 5 : Verify that validation message is displayed for invalid userName field */
    @Test
    public void verifyValidationMessageDisplayedForUserNameFieldInvalidAndClickOnLoginButton() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Click on login button with invalid userName and valid password");
        loginPO.fillLoginDetailsAndPerformLogin("abcd", user.getPassword());

        Reporter.log("Step 3 - Verify that validation message is displayed");
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error Message doesn't match");
    }

    /* Test 6 : Verify that validation message is displayed for invalid password field */
    @Test
    public void verifyValidationMessageDisplayedForPasswordFieldInvalidAndClickOnLoginButton() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Click on login button with valid userName and invalid password");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), "abcd123");

        Reporter.log("Step 3 - Verify that validation message is displayed");
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error Message doesn't match");
    }

    /*Test 7 : Verify that user can't login with invalid credentials*/
    @Test
    public void verifyValidationMessageDisplayedForUserNameAndPasswordFieldInvalidAndClickOnLoginButton() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering invalid signIn details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin("abcd", "abcd1234");

        Reporter.log("Step 3 - Verify that validation message is displayed");
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(loginPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error Message doesn't match");
    }

}
