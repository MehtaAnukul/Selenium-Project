package gonnaorder.admin.account;

import base.BaseTest;
import datafactory.gonnaorder.admin.SignUpData;
import dataobjects.gonnaorder.admin.SignUp;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.DeleteUserPO;
import pageobjects.gonnaorder.admin.account.EmailVerificationPO;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.account.SignUpPO;
import pageobjects.gonnaorder.admin.common.HeaderPO;
import pageobjects.gonnaorder.admin.common.LeftMenuPO;
import utilities.Constants;
import utilities.JavaHelpers;
import utilities.emailhelpers.EmailHelpers;
import utilities.emailhelpers.mailinator.EmailDetailsPO;
import utilities.emailhelpers.mailinator.EmailListingGridPO;

public class SignUpTest extends BaseTest {


    /*Test 1 : Verify that user can Register successfully and login to the application after receive an email*/
    @Test
    public void registerUserSuccessfullyAndLogInSuccessfullyAfterReceiveAnEmail() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        HeaderPO header = new HeaderPO(driver);
        DeleteUserPO deleteUser = new DeleteUserPO(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);
        SignUpPO signUp = new SignUpPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailVerificationPO emailVerification = new EmailVerificationPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);
        JavaHelpers javaHelpers = new JavaHelpers();

        Reporter.log("Step 1 - Navigate to admin login page and click on the 'SignUp' link");
        selenium.navigateToPage(Constants.ADMIN_URL);
        login.clickOnSignUpButton();

        Reporter.log("Step 2 - Fill SignUp form and click on the 'Register' button");
        //Test Data
        SignUpData data = new SignUpData();
        SignUp registerUserData = data.getSignUpDetailsData();
        String email = registerUserData.getEmail();
        selenium.hardWait(2);
        signUp.fillSignUpDetailsAndClickOnRegisterButton(registerUserData);

        Reporter.log("Step 3- Verify that success message is displayed");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, Constants.ADMIN_URL + "/register/success", "Register partner URL doesn't match" );
        Assert.assertEquals(signUp.getSuccessMessage(), "Success!\nThank you for your registration. You will shortly receive an email to verify your account.", "Success message didn't match");

        Reporter.log("Step 4- Navigate to Email box for : " + email + " and verifying that Sign up email is received");
        String emailFromInfo = "GonnaOrder";
        String emailSubjectSignUp = "Activate your GonnaOrder Account";
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectSignUp, 10), "Email is not received");

        Reporter.log("Step 5- Opening Email and clicking on 'Activate your GonnaOrder Account' button");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectSignUp);
        emailDetails.clickOnButtonOrLink("activate your account");

        Reporter.log("Step 6- Verify that user is logged in successfully or not by comparing account name");
        selenium.switchToWindow(2);
        Assert.assertEquals(header.getProfileName(), email, "Logged In user account name didn't match");

        Reporter.log("Clean up- Delete the Registered user by URL");
        deleteUser.deleteRegisteredUser();

    }

    /*Test 2 : Verify that user can Register successfully as a partner and login to the application after receive an email and 'Partner Program' menu is display*/
    @Test
    public void registerUserSuccessfullyAsPartnerAndLogInSuccessfullyAfterReceiveAnEmailAndDisplayPartnerPage() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        HeaderPO header = new HeaderPO(driver);
        DeleteUserPO deleteUser = new DeleteUserPO(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);
        SignUpPO signUp = new SignUpPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailVerificationPO emailVerification = new EmailVerificationPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);

        Reporter.log("Step 1 - Navigate to SignUp page as partner");
        selenium.navigateToPage(Constants.ADMIN_URL + "/partners");

        Reporter.log("Step 2 - Fill SignUp form and click on the 'Register' button");
        //Test Data
        SignUpData data = new SignUpData();
        SignUp registerUserData = data.getSignUpDetailsData();
        String email = registerUserData.getEmail();
        signUp.fillSignUpDetailsAndClickOnRegisterButton(registerUserData);

        Reporter.log("Step 3- Verify that success message is displayed");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, Constants.ADMIN_URL + "/partners/success", "Register partner URL doesn't match" );
        Assert.assertEquals(signUp.getSuccessMessage(), "Success!\nThank you for your registration. You will shortly receive an email to verify your account.", "Success message didn't match");

        Reporter.log("Step 4- Navigate to Email box for : " + email + " and verifying that Sign up email is received");
        String emailFromInfo = "GonnaOrder";
        String emailSubjectSignUp = "Activate your GonnaOrder Account";
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectSignUp, 10), "Email is not received");

        Reporter.log("Step 5- Opening Email and clicking on 'Activate your GonnaOrder Account' button");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectSignUp);
        emailDetails.clickOnButtonOrLink("activate your account");

        Reporter.log("Step 6- Verify that user is logged in successfully or not by comparing account name");
        selenium.switchToWindow(2);
        Assert.assertEquals(header.getProfileName(), email, "Logged In user account name didn't match");

        Reporter.log("Step 7- Verify that 'Partner Program' menu is displayed on the left menu");
        Assert.assertTrue(leftMenu.isPartnerProgramMenuPresent(), "Partner program menu isn't present");

        Reporter.log("Clean up- Delete the registered user by URL");
        deleteUser.deleteRegisteredUser();

    }

    /*Test 3 : Verify that user can delete the Registered user successfully and can't login to the application */
    @Test(groups = {"registration"})
    public void deleteRegisteredUserAndCanNotLogInToApplication() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        HeaderPO header = new HeaderPO(driver);
        DeleteUserPO deleteUser = new DeleteUserPO(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);
        SignUpPO signUp = new SignUpPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailVerificationPO emailVerification = new EmailVerificationPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);

        Reporter.log("Step 1 - Navigate to admin login page and click on the 'SignUp' link");
        selenium.navigateToPage(Constants.ADMIN_URL);
        login.clickOnSignUpButton();

        Reporter.log("Step 2 - Fill SignUp form and click on the 'Register' button");
        //Test Data
        SignUpData data = new SignUpData();
        SignUp registerUserData = data.getSignUpDetailsData();
        String email = registerUserData.getEmail();
        signUp.fillSignUpDetailsAndClickOnRegisterButton(registerUserData);

        Reporter.log("Step 3- Verify that success message is displayed");
        Assert.assertEquals(signUp.getSuccessMessage(), "Success!\nThank you for your registration. You will shortly receive an email to verify your account.", "Success message didn't match");

        Reporter.log("Step 4- Navigate to Email box for : " + email + " and verifying that Sign up email is received");
        String emailFromInfo = "GonnaOrder";
        String emailSubjectSignUp = "Activate your GonnaOrder Account";
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectSignUp, 10), "Email is not received");

        Reporter.log("Step 5- Opening Email and clicking on 'Activate your GonnaOrder Account' button");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectSignUp);
        emailDetails.clickOnButtonOrLink("activate your account");

        Reporter.log("Step 6- Verify that user is logged in successfully or not by comparing account name");
        selenium.switchToWindow(2);
        Assert.assertEquals(header.getProfileName(), email, "Logged In user account name didn't match");

        Reporter.log("Step 7- Delete the Registered user by URL");
        deleteUser.deleteRegisteredUser();

        Reporter.log("Step 8- Verify that deleted user can't login the application");
        String passwordValue = registerUserData.getPassword();
        login.fillLoginDetailsAndPerformLogin(email, passwordValue);
        Assert.assertEquals(login.getErrorMessage(), "Error!\nInvalid credentials supplied.", " Error Message didn't match");

    }


}


