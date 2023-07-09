package account;

import base.BaseTest;
import dataFactory.RegistrationData;
import dataObjectsModel.RegistrationModel;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.gonnaOrderr.account.LoginPO;
import pageObjects.gonnaOrderr.account.RegistrationPO;
import utilities.Constants;

public class RegistrationTests extends BaseTest {

    /*Test 1 : Verify that user can register successfully*/
    @Test
    public void verifyRegistrationWithValidDetails() throws InterruptedException{


        LoginPO loginPO = new LoginPO(driver);
        RegistrationPO registrationPO = new RegistrationPO(driver);

        Reporter.log(("Step 1 - Navigate to Login page"));
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'SignUp' page");
        loginPO.clickOnSignUpLoginPageLink();

        Reporter.log("Step 3 - Fill out SignUp form with valid details and click on 'Sign Up' button");
        RegistrationModel registrationModel = new RegistrationData().getRegistrationModelData();
        registrationPO.fillRegistrationDetailsAndPerformSignUp(registrationModel);

        Reporter.log(("Step 4 - Navigate to Login page"));
        selenium.navigateToPage(Constants.URL+"/login");

    }

    /*Test 2 : Verify that validation messages are displayed for mandatory fields*/
    @Test
    public void verifyValidationMessageDisplayedAllMandatoryFields() throws InterruptedException{
        LoginPO loginPO = new LoginPO(driver);
        RegistrationPO registrationPO = new RegistrationPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'SignUp' page");
        loginPO.clickOnSignUpLoginPageLink();

        Reporter.log("Step 3 - Click on 'Continue' button without entering details");
        registrationPO.clickOnContinueButton();

        Reporter.log("Step 4 - Click on 'Continue' button after entering valid details");
        registrationPO.clickOnContinueButton();
        String expectedUrl = Constants.URL+"/register-user-details";
        Assert.assertEquals(selenium.getURL(),expectedUrl,"Url doesn't match");

        Reporter.log("Step 5 - Click on 'Accept And Register' button without entering details");
        registrationPO.acceptAndRegisterButton();

        Reporter.log("Step 6 - Verify that validation message are displayed for all mandatory fields");
        String expectedMessage = "This field is required.";
        Assert.assertEquals(registrationPO.getEmailValidationMessage(),expectedMessage,"Email validation message doesn't match");
        Assert.assertEquals(registrationPO.getPasswordValidationMessage(),expectedMessage,"Password validation message doesn't match");
        Assert.assertEquals(registrationPO.getConfirmPasswordValidationMessage(),expectedMessage,"Confirm password validation message doesn't match");
        Assert.assertEquals(registrationPO.getFirstNameValidationMessage(),expectedMessage,"FirstName validation message doesn't match");
        Assert.assertEquals(registrationPO.getLastNameValidationMessage(),expectedMessage,"LastName validation message doesn't match");
    }

    /*Test 3 : Verify that validation message displayed for already taken email*/
    @Test
    public void verifyValidationMessageDisplayedForAlreadyTakenEmail() throws InterruptedException{

        RegistrationPO registrationPO = new RegistrationPO(driver);
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'SignUp' page");
        loginPO.clickOnSignUpLoginPageLink();

        Reporter.log("Step 3 - Enter already taken email");
        RegistrationModel registrationModel = new RegistrationData().getAlreadyTakenEmailData();
        registrationPO.fillRegistrationDetailsAndPerformSignUp(registrationModel);

        Reporter.log("Step 4 - Verify that validation message is displayed for already taken email address");
        String expectedMessage = "User name  '"+registrationModel.getFirstNameTextBox()+"'  is already taken."  +
                ",Email    '"+registrationModel.getEmailTextBox()+"' is already taken.";

        Assert.assertEquals(registrationPO.getAlreadyTakenEmailValidationMessage(),expectedMessage,"Email validation message doesn't match");
        //getAlreadyTakenEmailValidationMessage() xpath not define as per issue
    }

    /*Test 4 : Verify that validation message displayed for short password */
    @Test
    public void verifyValidationMessageDisplayedForShortPassword() throws InterruptedException{

        RegistrationPO registrationPO = new RegistrationPO(driver);
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'SignUp' page");
        loginPO.clickOnSignUpLoginPageLink();

        Reporter.log("Step 3 - Enter short password");
        registrationPO.enterPasswordDetails("ab","ab");

        Reporter.log("Step 4 - Verify that validation message is displayed for incomplete password");
        String expectedMessage = "This field should be minimum 8 characters.";
        Assert.assertEquals(registrationPO.getPasswordValidationMessage(),expectedMessage,"Password validation message doesn't match");

    }


    /*Test 6 : Verify that validation message displayed when password - confirm password doesn't match*/
    @Test
    public void verifyValidationMessageDisplayedForPasswordAndConfirmPasswordNotMatch() throws InterruptedException{
        RegistrationPO registrationPO = new RegistrationPO(driver);
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'SignUp' page");
        loginPO.clickOnSignUpLoginPageLink();

        Reporter.log("Step 3 - Enter different values for password and confirm password");
        registrationPO.enterPasswordDetails("12345678","1234567789");

        Reporter.log("Step 4 - Verify that validation message is displayed when password - confirm password doesn't match");
        String expectedMessage = "Passwords do not match.";
        Assert.assertEquals(registrationPO.getConfirmPasswordValidationMessage(), expectedMessage, "Password validation message doesn't matched");
    }

    /*Test 7 : Verify that 'Login' link is work as per expected*/
    @Test
    public void verifyLoginLinkWorkAsExpected() throws InterruptedException{
        RegistrationPO registrationPO = new RegistrationPO(driver);
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'SignUp' page");
        loginPO.clickOnSignUpLoginPageLink();

        Reporter.log("Step 1 - Navigate to 'Login' page");
        registrationPO.clickOnLoginRegisterPageLink();
        String expectedResult = "Login";
        Assert.assertEquals(loginPO.getLoginText(), expectedResult, "User does not return to login page");


    }

}
