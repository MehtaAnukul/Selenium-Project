package gonnaorder.admin.account;

import base.BaseTest;
import datafactory.gonnaorder.admin.ForgotPasswordData;
import datafactory.gonnaorder.admin.SignUpData;
import dataobjects.gonnaorder.admin.ForgotPassword;
import dataobjects.gonnaorder.admin.SignUp;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.*;
import pageobjects.gonnaorder.admin.common.HeaderPO;
import utilities.Constants;
import utilities.emailhelpers.EmailHelpers;
import utilities.emailhelpers.mailinator.EmailDetailsPO;
import utilities.emailhelpers.mailinator.EmailListingGridPO;

public class ForgotPasswordTests extends BaseTest {

	/*Test 1 : Verify that user can Change Password successfully*/
	@Test
	public void verifyThatPasswordResetWorksViaForgotPasswordLink() throws InterruptedException {
		LoginPO login = new LoginPO(driver);
		HeaderPO header = new HeaderPO(driver);
		DeleteUserPO deleteUser = new DeleteUserPO(driver);
		EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);
		SignUpPO signUp = new SignUpPO(driver);
		EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
		EmailVerificationPO emailVerification = new EmailVerificationPO(driver);
		EmailHelpers emailhelpers = new EmailHelpers(driver);
		ForgotPasswordPO forgotPassword = new ForgotPasswordPO(driver);
		ResetPasswordPO resetPassword = new ResetPasswordPO(driver);

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
		String RegisterURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisterURL, Constants.ADMIN_URL + "/register/success", "Register success URL doesn't match" );
		Assert.assertEquals(signUp.getSuccessMessage(), "Success!\nThank you for your registration. You will shortly receive an email to verify your account.", "Success message didn't match");

		Reporter.log("Step 4- Navigate to Email box for : " + email + " and verifying that Sign up email is received");
		String emailFromInfo = "GonnaOrder";
		String emailSubjectSignUp = "Activate your GonnaOrder Account";
		Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectSignUp, 10), "Email is not received");

		Reporter.log("Step 5- Opening Email and clicking on 'Activate your GonnaOrder Account' button");
		emailListingGrid.openEmail(emailFromInfo, emailSubjectSignUp);
		emailDetails.clickOnButtonOrLink("activate your account");

		Reporter.log("Step 6- Verify that on clicking Forgot Password Link, user navigate to forgot password page");
		selenium.switchToWindow(2);
		Assert.assertEquals(header.getProfileName(), email, "Logged In user account name didn't match");
		header.clickOnProfileName();
		header.clickOnLogoutMenu();
		login.clickOnForgotPasswordLink();
		forgotPassword.fillForgotPasswordDetailAndSubmit(email);

		Reporter.log("Step 7- Verify that success message is displayed");
		String ResetURL = driver.getCurrentUrl();
		Assert.assertEquals(ResetURL, Constants.ADMIN_URL + "/password/reset/success", "Reset password success URL doesn't match" );
		Assert.assertEquals(signUp.getSuccessMessage(), "Well Done!\nYou will shortly receive an email to reset your password.", "Password reset Success message didn't match");

		Reporter.log("Step 8- Navigate to Email box for : " + email + " and verifying that Sign up email is received");
		String emailFromInfo1 = "GonnaOrder";
		String emailSubjectSignUp1 = "Verify change of your GonnaOrder account password";
		Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo1, emailSubjectSignUp1, 10), "Email is not received");

		Reporter.log("Step 9- Opening Email and clicking on 'reset your password' button");
		emailListingGrid.openEmail(emailFromInfo1, emailSubjectSignUp1);
		emailDetails.clickOnButtonOrLink("reset your password");

		Reporter.log("Step 10- Verify that on clicking 'Reset Password' link, user is navigated to Update Password page");
		selenium.switchToWindow(2);
		Assert.assertEquals(resetPassword.getUpdatePasswordHeading(),"Update Password","Update Password page title doesn't match");
		Assert.assertEquals(email,resetPassword.getEmailTextBoxValue(),"email for reset password doesn't match with register email");

		Reporter.log("Step 11- Fill password reset form and click on the 'Update' button");
		ForgotPasswordData data1 = new ForgotPasswordData();
		ForgotPassword resetPasswordData = data1.getForgotPasswordData();
		String ResetPassword = resetPasswordData.getResetPassword();
		String ResetConfirmPassword = resetPasswordData.getResetConfirmPassword();
		resetPassword.fillResetPasswordDetailAndUpdate(ResetPassword, ResetConfirmPassword);
		selenium.switchToWindow(2);
		Assert.assertEquals(resetPassword.getPasswordResetSuccessMessage(), "Success!\nYour password has changed. Login below to continue.", "Success message of reset password doesn't match");

		Reporter.log("Step 12- Enter valid credential of registered user and login to application");
		String passwordValue = resetPasswordData.getResetPassword();;
		login.fillLoginDetailsAndPerformLogin(email, passwordValue);

		Reporter.log("Step 13- Verify that user is logged in successfully or not by comparing account name");
		Assert.assertEquals(header.getProfileName(), email, "Logged In user account name didn't match");

		Reporter.log("Clean up- Delete the Registered user by URL");
		deleteUser.deleteRegisteredUser();
	}

}
