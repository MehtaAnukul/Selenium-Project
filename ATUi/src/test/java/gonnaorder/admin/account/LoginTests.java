package gonnaorder.admin.account;
import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.common.HeaderPO;
import utilities.Constants;

public class LoginTests extends BaseTest {

	/*Test 1 : Verify that user can login successfully*/
	@Test
	public void loginSuccessful() throws InterruptedException {
		LoginPO login = new LoginPO(driver);
		HeaderPO header = new HeaderPO(driver);

		Reporter.log("Step 1 - Navigate to admin login page");
		selenium.navigateToPage(Constants.ADMIN_URL);

		Reporter.log("Step 2 - Entering valid sign in details and login to application");
		login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

		Reporter.log("Step 3- Verify that user is logged in successfully or not by comparing account name");
		Assert.assertEquals(header.getProfileName(), Constants.ACCOUNTNAME, "Logged In user account name didn't match");
	}


	/*Test 2 : Verify that user can't login with invalid credentials*/
	@Test
	public void loginUnsuccessful() throws InterruptedException {

		LoginPO login = new LoginPO(driver);
		HeaderPO header = new HeaderPO(driver);

		Reporter.log("Step 1 - Navigate to home page, click on Sign In button");
		selenium.navigateToPage(Constants.ADMIN_URL);

		Reporter.log("Step 2 - Entering invalid sign in details and login to application");
		login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, "Incorrect");

		Reporter.log("Step 3- Verify that validation message is displayed");
		Assert.assertEquals(login.getErrorMessage(), "Error!\nInvalid credentials supplied.", "Validation message didn't match");
	}

}
