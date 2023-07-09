package account;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.gonnaOrderr.account.LoginPO;
import utilities.Constants;

public class LoginTest extends BaseTest {

    /*Test 1 : Verify that user can login successfully*/
    @Test
    public void verifyLoginWithValidCredentials() throws InterruptedException{
        LoginPO loginPO = new LoginPO(driver);



        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

    }

    /*Test 2 : Verify that user can't login with invalid credentials*/
    @Test
    public void verifyLoginWithInvalidCredentials() throws InterruptedException{
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Entering invalid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin("abc@gmail.com","abc@123456789");

        Reporter.log("Step 3 - Verify that validation message is displayed");
        String expectedErrorMessage = "Invalid credentials supplied.";
        Assert.assertEquals(loginPO.getInvalidDataValidationMessage(),expectedErrorMessage,"Error Message doesn't match");
    }

    /*Test 3 : Verify that user can't login without confirm account*/
    @Test
    public void verifyLoginWithoutConfirmAccount() throws InterruptedException{
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Entering details without confirming account and login to application");
        loginPO.fillLoginDetailsAndPerformLogin("gonnaorder@gmail.com","ab@1234567890");

        Reporter.log("Step 3 - Verify that validation message is displayed");
        String expectedErrorMessage = "Invalid email address or password";
        Assert.assertEquals(loginPO.getInvalidDataValidationMessage(), expectedErrorMessage,"Error Message doesn't match");

    }
    /*Test 4 : Verify that 'Forgot Password?' link works as expected*/
    @Test
    public void verifyForgotPasswordLinkWorkProperly() throws InterruptedException{

        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Click on forgot password link");
        loginPO.clickOnForgotPasswordLink();

        Reporter.log("Step 3 - Verify that forgot password page is displayed");
        String expectedUrl = Constants.URL+"password/reset";
        Assert.assertEquals(selenium.getURL(),expectedUrl,"Url doesn't match");
    }



    /* *//*Test 5 : Verify that 'Sign in with Facebook' button works as expected*//*
    public void verifySignInWithFacebook() throws InterruptedException{
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Click on Sign in with Facebook button");
        loginPO.signInWithFacebookButton();

        Reporter.log("Step 3 - Verify that Facebook login page is displayed");
        String expectedUrl =
    }*/
}
