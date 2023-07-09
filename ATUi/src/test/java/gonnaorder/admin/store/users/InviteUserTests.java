package gonnaorder.admin.store.users;

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
import pageobjects.gonnaorder.admin.store.users.UserDashboadPO;
import utilities.Constants;
import utilities.emailhelpers.EmailHelpers;
import utilities.emailhelpers.mailinator.EmailDetailsPO;
import utilities.emailhelpers.mailinator.EmailListingGridPO;

import java.awt.*;

public class InviteUserTests extends BaseTest {

    /*Test 1 : Verify that store admin can invite another registered user successfully and invited user can access the store after receive an email*/
    @Test
    public void inviteRegisteredUserSuccessfullyAndInvitedUserAccessStoreAfterReceiveAnEmail() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        UserDashboadPO inviteUser = new UserDashboadPO(driver);
        HeaderPO header = new HeaderPO(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailVerificationPO emailVerification = new EmailVerificationPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store user page");
        String usersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/users";
        selenium.navigateToPage(usersPageUrl);

        Reporter.log("Step 4 - Enter valid password data in the password field and click on the Re-Authenticate button");
        inviteUser.fillPasswordDataAndClickOnReAuthenticateButton(Constants.PASSWORD);

        Reporter.log("Step 5 - Click on the 'Invite user' button");
        inviteUser.clickOnInviteUserButton();

        Reporter.log("Step 6 - Enter registered email data in the email field and click on the invite button");
        String email = Constants.InviteUserEMAIL;
        inviteUser.fillEmailDataAndClickOnInviteButton(email);
        Assert.assertEquals(inviteUser.getEmailAddress(), Constants.InviteUserEMAIL, "Email of invited user didn't match");

        Reporter.log("Step 7- Navigate to Email box for : " + email + " and verifying that 'Invite to access store' email is received");
        String emailFromInfo = "GonnaOrder";
        String emailSubjectSignUp = "You have been invited to manage a GonnaOrder store";
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectSignUp,10), "Email is not received");

        Reporter.log("Step 8- Opening Email and clicking on 'login' link");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectSignUp);
        emailDetails.clickOnButtonOrLink("login");

        Reporter.log("Step 9- Verify that on clicking 'login' link, user is navigated to login page and 'Access to store' message display.");
        selenium.switchToWindow(2);
        Assert.assertEquals(login.getInviteUserSuccessMessage(), "Success!\nYour registration was successful. Login to get access to " + Constants.StoreName5, "Success message of activate gonnaorder account didn't match");
        Assert.assertTrue(emailVerification.isLoginTitleTextPresent(), "Login page title isn't present");

        Reporter.log("Step 10- Enter Invited user data(email and password) and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.InviteUserEMAIL, Constants.InviteUserPASSWORD);

        Reporter.log("Step 11- Verify that invited user is logged in successfully or not by comparing account name");
        Assert.assertEquals(header.getProfileName(), Constants.InviteUserACCOUNTNAME, "Logged In user account name didn't match");

        Reporter.log("Step 12- Verify that invited user can access the store by comparing store name");
        Assert.assertEquals(header.getStoreName(), Constants.StoreName5, "Store name didn't match");
        header.clickOnProfileName();
        header.clickOnLogoutMenu();

        Reporter.log("Step 13- Open the store admin account and remove the invited user from access the store");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);
        selenium.navigateToPage(usersPageUrl);
        inviteUser.fillPasswordDataAndClickOnReAuthenticateButton(Constants.PASSWORD);
        inviteUser.clickOnRemoveUserIcon();
        inviteUser.clickOnOKButton();
        selenium.hardWait(5);
        header.clickOnProfileName();
        header.clickOnLogoutMenu();

        Reporter.log("Step 14- Verify that deleted invited user can not access the store by comparing store name");
        login.fillLoginDetailsAndPerformLogin(Constants.InviteUserEMAIL, Constants.InviteUserPASSWORD);
        Assert.assertNotEquals(header.getStoreName(), Constants.StoreName, "Store name didn't match");

    }

    /*Test 2 : Verify that store admin can invite another non registered user successfully and invited user can access the store after receive an email*/
    @Test
    public void inviteNonRegisteredUserSuccessfullyAndInvitedUserAccessStoreAfterReceiveAnEmail() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        SignUpPO signUp = new SignUpPO(driver);
        DeleteUserPO deleteUser = new DeleteUserPO(driver);
        UserDashboadPO inviteUser = new UserDashboadPO(driver);
        HeaderPO header = new HeaderPO(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailVerificationPO emailVerification = new EmailVerificationPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store user page");
        String usersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/users";
        selenium.navigateToPage(usersPageUrl);

        Reporter.log("Step 4 - Enter valid password data in the password field and click on the Re-Authenticate button");
        inviteUser.fillPasswordDataAndClickOnReAuthenticateButton(Constants.PASSWORD);

        Reporter.log("Step 5 - Click on the 'Invite user' button");
        inviteUser.clickOnInviteUserButton();

        Reporter.log("Step 6 - Enter non registered email data in the email field and click on the invite button");
        SignUpData data = new SignUpData();
        SignUp registerUserData = data.getSignUpDetailsData();
        String email = registerUserData.getEmail();
        inviteUser.fillEmailDataAndClickOnInviteButton(email);

        Reporter.log("Step 7- Navigate to Email box for : " + email + " and verifying that 'Invite to access store' email is received");
        String emailFromInfo = "GonnaOrder";
        String emailSubjectLogin = "Je bent uitgenodigd om een GonnaOrder-winkel te beheren";
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectLogin, 10), "Email is not received");

        Reporter.log("Step 8- Opening Email and clicking on 'register' link");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectLogin);
        emailDetails.clickOnButtonOrLink("registreren");

        Reporter.log("Step 9- Fill SignUp form and click on the 'Register' button");
        selenium.switchToWindow(2);
        //Test Data
        registerUserData.setEmail("");
        signUp.fillSignUpDetailsAndClickOnRegisterButton(registerUserData);

        Reporter.log("Step 10- Verify that invited user is logged in successfully or not by comparing account name");
        Assert.assertEquals(header.getProfileName(), email, "Logged In user account name didn't match");

        Reporter.log("Step 11- Verify that invited user can access the store by comparing store name");
        Assert.assertEquals(header.getStoreName(), Constants.StoreName5, "Store name didn't match");
        header.clickOnProfileName();
        header.clickOnLogoutMenu();

        Reporter.log("Step 12- Open the store admin account and remove the invited user from access the store");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);
        selenium.navigateToPage(usersPageUrl);
        selenium.hardWait(3);
        inviteUser.fillPasswordDataAndClickOnReAuthenticateButton(Constants.PASSWORD);
        inviteUser.clickOnRemoveUserIcon();
        inviteUser.clickOnOKButton();
        selenium.hardWait(5);
        header.clickOnProfileName();
        header.clickOnLogoutMenu();

        Reporter.log("Step 13- Verify that deleted invited user can not access the store by comparing store name");
        String passwordValue = registerUserData.getPassword();
        login.fillLoginDetailsAndPerformLogin(email, passwordValue);
        Assert.assertNotEquals(header.getStoreName(), Constants.StoreName5, "Store name didn't match");

        Reporter.log("Clean up- Delete the invited user by URL");
        deleteUser.deleteRegisteredUser();

    }

    /*Test 3 : Verify that store admin can invite user as Standard user successfully and invited user can access the store with limited tab access after receive an email*/
    @Test
    public void inviteUserAsStandardUserSuccessfullyAndInvitedUserAccessStoreWithLimitedAccessTabsAfterReceiveAnEmail() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        SignUpPO signUp = new SignUpPO(driver);
        DeleteUserPO deleteUser = new DeleteUserPO(driver);
        UserDashboadPO inviteUser = new UserDashboadPO(driver);
        HeaderPO header = new HeaderPO(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailVerificationPO emailVerification = new EmailVerificationPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store user page");
        String usersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/users";
        selenium.navigateToPage(usersPageUrl);

        Reporter.log("Step 4 - Enter valid password data in the password field and click on the Re-Authenticate button");
        inviteUser.fillPasswordDataAndClickOnReAuthenticateButton(Constants.PASSWORD);

        Reporter.log("Step 5 - Click on the 'Invite user' button");
        inviteUser.clickOnInviteUserButton();

        Reporter.log("Step 6 - Enter non registered email data in the email field ,select Standard User role option and click on the invite button");
        SignUpData data = new SignUpData();
        SignUp registerUserData = data.getSignUpDetailsData();
        String email = registerUserData.getEmail();
        inviteUser.clickOnStandardUserRadioButton();
        inviteUser.fillEmailDataAndClickOnInviteButton(email);

        Reporter.log("Step 7- Navigate to Email box for : " + email + " and verifying that 'Invite to access store' email is received");
        String emailFromInfo = "GonnaOrder";
        String emailSubjectLogin = "Je bent uitgenodigd om een GonnaOrder-winkel te beheren";
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectLogin, 10), "Email is not received");

        Reporter.log("Step 8- Opening Email and clicking on 'register' link");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectLogin);
        emailDetails.clickOnButtonOrLink("registreren");

        Reporter.log("Step 9- Fill SignUp form and click on the 'Register' button");
        selenium.switchToWindow(2);
        //Test Data
        registerUserData.setEmail("");
        signUp.fillSignUpDetailsAndClickOnRegisterButton(registerUserData);

        Reporter.log("Step 10- Verify that invited user is logged in successfully or not by comparing account name");
        Assert.assertEquals(header.getProfileName(), email, "Logged In user account name didn't match");

        Reporter.log("Step 11- Verify that invited user can access the store and can not access mentioned tabs by comparing store name");
        Assert.assertEquals(header.getStoreName(), Constants.StoreName5, "Store name didn't match");
        Assert.assertFalse(leftMenu.isSettingsMenuPresent(),"Setting tab Present for Standard User");
        Assert.assertFalse(leftMenu.isCatalogMenuPresent(),"Catalog tab Present for Standard user");
        Assert.assertFalse(leftMenu.isUsersMenuPresent(),"Users tab Present for Standard user");
        Assert.assertFalse(leftMenu.isTablesMenuPresent(),"Tables tab Present for Standard user");
        Assert.assertFalse(leftMenu.isSubscriptionMenuPresent(),"Subscriptions tab Present for Standard user");
        header.clickOnProfileName();
        header.clickOnLogoutMenu();

        Reporter.log("Step 12- Open the store admin account and remove the invited user from access the store");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);
        selenium.navigateToPage(usersPageUrl);
        selenium.hardWait(3);
        inviteUser.fillPasswordDataAndClickOnReAuthenticateButton(Constants.PASSWORD);
        inviteUser.clickOnRemoveUserIcon();
        inviteUser.clickOnOKButton();
        selenium.hardWait(5);
        header.clickOnProfileName();
        header.clickOnLogoutMenu();

        Reporter.log("Step 13- Verify that deleted invited user can not access the store by comparing store name");
        String passwordValue = registerUserData.getPassword();
        login.fillLoginDetailsAndPerformLogin(email, passwordValue);
        Assert.assertNotEquals(header.getStoreName(), Constants.StoreName5, "Store name didn't match");

        Reporter.log("Clean up- Delete the invited user by URL");
        deleteUser.deleteRegisteredUser();
    }

    /*Test 4 : Verify that store admin can invite another registered user as Standard User successfully and invited user can access the store with limited tab access after receive an email*/
    @Test
    public void inviteRegisteredUserAsStandardUserSuccessfullyAndInvitedUserAccessStoreWithLimitedAccessTabsAfterReceiveAnEmail() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        UserDashboadPO inviteUser = new UserDashboadPO(driver);
        HeaderPO header = new HeaderPO(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailVerificationPO emailVerification = new EmailVerificationPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store user page");
        String usersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/users";
        selenium.navigateToPage(usersPageUrl);

        Reporter.log("Step 4 - Enter valid password data in the password field and click on the Re-Authenticate button");
        inviteUser.fillPasswordDataAndClickOnReAuthenticateButton(Constants.PASSWORD);

        Reporter.log("Step 5 - Click on the 'Invite user' button");
        inviteUser.clickOnInviteUserButton();

        Reporter.log("Step 6 - Enter registered email data in the email field of of Standard User's and click on the invite button");
        String email = Constants.InviteUserEMAIL;
        inviteUser.clickOnStandardUserRadioButton();
        inviteUser.fillEmailDataAndClickOnInviteButton(email);
        Assert.assertEquals(inviteUser.getEmailAddress(), Constants.InviteUserEMAIL, "Email of invited user didn't match");

        Reporter.log("Step 7- Navigate to Email box for : " + email + " and verifying that 'Invite to access store' email is received");
        String emailFromInfo = "GonnaOrder";
        String emailSubjectSignUp = "You have been invited to manage a GonnaOrder store";
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectSignUp,10), "Email is not received");

        Reporter.log("Step 8- Opening Email and clicking on 'login' link");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectSignUp);
        emailDetails.clickOnButtonOrLink("login");

        Reporter.log("Step 9- Verify that on clicking 'login' link, user is navigated to login page and 'Access to store' message display.");
        selenium.switchToWindow(2);
        Assert.assertEquals(login.getInviteUserSuccessMessage(), "Success!\nYour registration was successful. Login to get access to " + Constants.StoreName5, "Success message of activate gonnaorder account didn't match");
        Assert.assertTrue(emailVerification.isLoginTitleTextPresent(), "Login page title isn't present");

        Reporter.log("Step 10- Enter Invited user data(email and password) and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.InviteUserEMAIL, Constants.InviteUserPASSWORD);

        Reporter.log("Step 11- Verify that invited user is logged in successfully or not by comparing account name");
        Assert.assertEquals(header.getProfileName(), Constants.InviteUserACCOUNTNAME, "Logged In user account name didn't match");

        Reporter.log("Step 12- Verify that invited user can access the store with limited tab access by comparing store name");
        Assert.assertEquals(header.getStoreName(), Constants.StoreName5, "Store name didn't match");
        Assert.assertFalse(leftMenu.isSettingsMenuPresent(),"Setting tab Present for Standard User");
        Assert.assertFalse(leftMenu.isCatalogMenuPresent(),"Catalog tab Present for Standard user");
        Assert.assertFalse(leftMenu.isUsersMenuPresent(),"Users tab Present for Standard user");
        Assert.assertFalse(leftMenu.isTablesMenuPresent(),"Tables tab Present for Standard user");
        Assert.assertFalse(leftMenu.isSubscriptionMenuPresent(),"Subscriptions tab Present for Standard user");
        header.clickOnProfileName();
        header.clickOnLogoutMenu();

        Reporter.log("Step 13- Open the store admin account and remove the invited user from access the store");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);
        selenium.navigateToPage(usersPageUrl);
        inviteUser.fillPasswordDataAndClickOnReAuthenticateButton(Constants.PASSWORD);
        inviteUser.clickOnRemoveUserIcon();
        inviteUser.clickOnOKButton();
        selenium.hardWait(5);
        header.clickOnProfileName();
        header.clickOnLogoutMenu();

        Reporter.log("Step 14- Verify that deleted invited user can not access the store by comparing store name");
        login.fillLoginDetailsAndPerformLogin(Constants.InviteUserEMAIL, Constants.InviteUserPASSWORD);
        Assert.assertNotEquals(header.getStoreName(), Constants.StoreName, "Store name didn't match");
    }

}