package account;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.gonnaOrderr.account.LoginPO;
import pageObjects.gonnaOrderr.common.HeaderPO;
import utilities.Constants;

public class HeaderTests extends BaseTest {

    /*Test 1 - Verify that  GonnaOrder logo text is clickable*/
    @Test
    public void clickOnGonnaOrderLogoText() throws InterruptedException{

        LoginPO loginPO = new LoginPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter valid login details and login to the application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Steps 3 - Click on GonnaOrder logo Full img text");
        headerPO.gonnaOrderLogoFullImg();

        Reporter.log("Steps 4 - Click on GonnaOrder logo Minimized img text");
        headerPO.gonnaOrderLogoMinimizedImg();

        Reporter.log("Steps 5 - Verify that Gonna Order website is displayed");
        String expectedUrl = Constants.gonnaOrderURL;
        Assert.assertEquals(selenium.getURL(),expectedUrl,"URL doesn't match");

    }

    /*Test 2 - Verify that support text is clickable */
    @Test
    public void clickOnSupportText() throws InterruptedException{

        LoginPO loginPO = new LoginPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter valid login details and login to the application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on support text");
        headerPO.supportText();

        Reporter.log("Step 4 - Verify that support page is displayed");
        String expectedUrl = Constants.URL+"/manager/profile/contactus";
        Assert.assertEquals(selenium.getURL(),expectedUrl,"URL doesn't match");
    }

    /*Test 3 - Verify that Admin dropDown text is clickable */
    @Test
    public void clickOnAdminDropDown() throws InterruptedException{
        LoginPO loginPO = new LoginPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter valid login details and login to the application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on Admin dropDown");
        headerPO.adminDropDown();

        int id = 4;
        String dropDownString = "Logout";
        switch (id){
            case 1: dropDownString = "My Profile";
                headerPO.myProfileAdminDropDownList();
                Reporter.log("Step 4 - Verify that My Profile page is displayed");
                String expectedUrl = Constants.URL+"/manager/profile/update";
                Assert.assertEquals(selenium.getURL(),expectedUrl,"URL doesn't match");
                break;
            case 2: dropDownString = "Social Accounts";
                headerPO.socialAccountsAdminDropDownList();
                Reporter.log("Step 5 - Verify that Social Accounts page is displayed");
                String expectedUrl1 = Constants.URL+"/manager/profile/social-accounts/update";
                Assert.assertEquals(selenium.getURL(),expectedUrl1,"URL doesn't match");
                break;
            case 3: dropDownString = "Change Password";
                headerPO.changePasswordAdminDropDownList();
                Reporter.log("Step 6 - Verify that Change Password page is displayed");
                String expectedUrl2 = Constants.URL+"/manager/profile/password/update";
                Assert.assertEquals(selenium.getURL(),expectedUrl2,"URL doesn't match");
                break;
            case 4: dropDownString = "Logout";
                headerPO.logout();
                Reporter.log("Step 7 - Verify that User is navigate to Login page");
                String expectedUrl3 = Constants.URL+"/login";
                Assert.assertEquals(selenium.getURL(),expectedUrl3,"URL doesn't match");
                break;
            default:
                System.out.println("Invalid Text");

        }

    }
}
