package helloTeams.account;

import base.BaseTest;
import datafactory.RegistrationData;
import dataobjects.Registration;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.helloteams.account.LoginPO;
import pageobjects.helloteams.account.RegistrationConfirmationPO;
import pageobjects.helloteams.account.RegistrationPO;
import utilities.Constants;

public class RegistrationTests extends BaseTest {

    /*Test 1 : Verify that user can register successfully*/
    @Test
    public void verifyRegisterWithValidDetail() throws InterruptedException {

        LoginPO login = new LoginPO(driver);
        RegistrationPO register = new RegistrationPO(driver);
        RegistrationConfirmationPO registrationConfirm = new RegistrationConfirmationPO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Fill out Sign up form with valid details and click on 'Register' button");
        Registration data = new RegistrationData().getRegistrationData();
        register.fillRegistrationDetailsAndPerformSignUp(data);
        String expectedResult = "Thank you";
        Assert.assertEquals(registrationConfirm.getThankYouMessage(),expectedResult,"Texts does not match");
        String expectedResult1 = "An email has been sent to confirm your account.";
        Assert.assertEquals(registrationConfirm.getEmailSentMessage(),expectedResult1,"Email Text is not displayed");
        Assert.assertTrue(registrationConfirm.isLoginLinkPresent(),"Email link is not Present");
        registrationConfirm.clickOnLoginLink();
        String expectedResult2 = "Login";
        Assert.assertEquals(login.getLoginText(),expectedResult2,"User does not return to Login page");
      //  Assert.assertEquals(login.getLoginLink(),Constants.URL,"User does not return to Login page");
    }

    /*Test 2 : Verify that validation messages are displayed for mandatory fields*/
    @Test
    public void verifyValidationMessageDisplayedAllMandatoryFields() throws InterruptedException {

        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Click on 'Checkbox' button without entering detail");
        register.clickOnCheckbox();

        Reporter.log("Step 4 - Click on 'Register' button");
        Assert.assertTrue(register.isRegisterButtonPresent(),"'Register' button is not displayed");
        register.clickOnRegisterButton();

        Reporter.log("Step 5- Verify that validation messages are displayed for all mandatory fields ");
        String expectedMessage = "The display name field is required";
        String expectedMessage1 = "The email field is required";
        String expectedMessage2 = "The password field is required";
        String expectedMessage3 = "The confirmation field is required";
        Assert.assertEquals(register.getDisplayNameValidationMessage(), expectedMessage, "First Name validation message doesn't match");
        Assert.assertEquals(register.getEmailValidationMessage(), expectedMessage1, "Email Name validation message doesn't match");
        Assert.assertEquals(register.getPasswordValidationMessage(), expectedMessage2, "Password validation message doesn't match");
        Assert.assertEquals(register.getConfirmPasswordValidationMessage(), expectedMessage3, "Confirm Password validation message doesn't match");

    }

    /*Test 3 : Verify that validation message displayed for invalid Domain*/
    @Test
    public void verifyValidationMessageDisplayedForInvalidDomain() throws InterruptedException {

        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Enter invalid domain");
        Registration data = new RegistrationData().getInvalidDomainData();
        register.fillRegistrationDetailsAndPerformSignUp(data);

        Reporter.log("Step 4- Verify that validation message is displayed for invalid Domain name");
        String expectedMessage = "Domain Forbidden";
        Assert.assertEquals(register.getInvalidDomainValidationMessage(), expectedMessage, "Domain validation message doesn't match");
    }

    /*Test 4 : Verify that validation message displayed for already taken email*/
    @Test
    public void verifyValidationMessageDisplayedForAlreadyTakenEmail() throws InterruptedException {

        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Enter already taken email");
        Registration data = new RegistrationData().getAlreadyTakenEmailData();
        register.fillRegistrationDetailsAndPerformSignUp(data);

        Reporter.log("Step 4- Verify that validation message is displayed for already taken email address");
        String expectedMessage = "User name '" + data.getEmail() + "' is already taken" + ".,Email '" + data.getEmail() + "' is already taken.";
        Assert.assertEquals(register.getAlreadyTakenEmailValidationMessage(), expectedMessage, "Email validation message doesn't match");
    }

    /*Test 5 : Verify that validation message displayed for short password */
    @Test
    public void verifyValidationMessageDisplayedForShortPassword() throws InterruptedException {

        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Enter short password e.g 1 ");
        register.enterPasswordDetails("1", "1");
        register.clickOnCheckbox();
        register.isRegisterButtonPresent();
        register.clickOnRegisterButton();

        Reporter.log("Step 4 - Verify that validation message is displayed for incomplete password ");
        String expectedMessage = "The password field must be at least 8 characters";
        Assert.assertEquals(register.getShortPasswordValidationMessage(), expectedMessage, "Password validation message doesn't match");
    }

    /*Test 6 : Verify that validation message displayed when password - confirm password doesn't match*/
    @Test
    public void verifyValidationMessageDisplayedForPasswordAndConfirmPasswordDoesNotMatch() throws InterruptedException {

        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Enter different values for password and confirm password");
        register.enterPasswordDetails("12345", "123456");

        Reporter.log("Step 4 - Verify that validation message is displayed when password - confirm password doesn't match");
        String expectedMessage = "The confirmation confirmation does not match";
        Assert.assertEquals(register.getNotMatchPasswordValidationMessage(), expectedMessage, "Password validation Message doesn't matched");
    }

    /*Test 7 : Verify that 'Cancel' button is work as per expected*/
    @Test
    public void verifyCancelButtonWorkAsExpected() throws InterruptedException
    {
        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step : 3 - Navigate to 'Login' page");
        register.clickOnCancelButton();
        String expectedResult2 = "Login";
        Assert.assertEquals(login.getLoginText(),expectedResult2,"User does not return to Login page");
     //  Assert.assertEquals(login.getLoginLink(),Constants.URL,"User does not return to Login page");
    }

    /*Test 8 : Verify that validation message displayed for password without letters */
    @Test
    public void verifyValidationMessageDisplayedForWithoutLettersInPassword() throws InterruptedException
    {
        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Verify that validation message is displayed for not including letters");
        Registration data = new RegistrationData().getDigitPasswordData();
        register.fillRegistrationDetailsAndPerformSignUp(data);
        String expectedMessage = "Passwords must have at least one lowercase ('a'-'z').,Passwords must have at least one uppercase ('A'-'Z').";
        Assert.assertEquals(register.getCaseLetterValidationMessage(),expectedMessage,"Validation message does not match");
    }

    /* Test 9 : Verify that validation message displayed for password without capital letter*/
    @Test
    public  void verifyValidationMessageDisplayedForNotIncludingUppercaseInPassword() throws InterruptedException
    {
        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Verify that validation message is displayed for not including capital letter");
        Registration data = new RegistrationData().getDigitLowerCaseData();
        register.fillRegistrationDetailsAndPerformSignUp(data);
        String expectedResult = "Passwords must have at least one uppercase ('A'-'Z').";
        Assert.assertEquals(register.getUppercaseValidationMessage(),expectedResult,"Validation message does not match");
    }

    /* Test 10 : Verify that validation message displayed for password without lower letter*/
    @Test
    public  void verifyValidationMessageDisplayedForNotIncludingLowercaseInPassword() throws InterruptedException
    {
        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Verify that validation message is displayed for not including small letter");
        Registration data = new RegistrationData().getDigitUpperCaseData();
        register.fillRegistrationDetailsAndPerformSignUp(data);
        String expectedResult = "Passwords must have at least one lowercase ('a'-'z').";
        Assert.assertEquals(register.getLowerCaseValidationMessage(),expectedResult,"Validation message does not match");
    }

    /* Test 11 : Verify that validation message displayed for password without digits*/
    @Test
    public  void verifyValidationMessageDisplayedForNotIncludingDigitsInPassword() throws InterruptedException
    {
        RegistrationPO register = new RegistrationPO(driver);
        LoginPO login = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to 'Login' page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Navigate to 'Register' page");
        login.clickOnRegisterButton();

        Reporter.log("Step 3 - Verify that validation message is displayed for not including digits");
        Registration data = new RegistrationData().getUpperLowerCaseData();
        register.fillRegistrationDetailsAndPerformSignUp(data);
        String expectedResult = "Passwords must have at least one digit ('0'-'9').";
        Assert.assertEquals(register.getDigitValidationMessage(),expectedResult,"Validation message does not match");
    }
}

