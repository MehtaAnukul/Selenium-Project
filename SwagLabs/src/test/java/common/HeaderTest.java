package common;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.swagLabs.account.LoginPO;
import pageObjects.swagLabs.common.HeaderPO;
import utilities.Constants;

public class HeaderTest extends BaseTest {

    /* Test 1 : Verify that HeaderSideBarList is open when click on headerNavigationDrawerIcon  */
    @BeforeMethod
    public void verifyThatSideBarListDisplayedWhenClickOnHeaderNavigationDrawerIcon() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);
        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), user.getPassword());

        Reporter.log("Step 3 - Navigate to Product page");
        String expectedUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }

    /* Test 2 : Verify that HeaderSideBarList is Close when click on headerNavigationDrawer close icon after open  */
    @Test
    public void verifyThatSideBarListCloseWhenClickOnHeaderNavigationDrawerCloseIcon() throws InterruptedException {
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Verify that sideBarList is open when click on NavigationDrawer Icon Menu");
        headerPO.clickOnHeaderNavigationDrawerIconMenu();

        Reporter.log("Step 2 - Verify that sideBarList is Close when click on navigationDrawer close icon after open");
        headerPO.clickOnHeaderNavigationDrawerIconMenuCrossIcon();
    }

    /* Test 3 : Verify that 'Product' page displayed when click on 'All Items' text on HeaderNavigationDrawerIconSideBarList */
    @Test
    public void verifyThatProductPageDisplayedWhenClickOnAllItemTextOFHeaderNavigationDrawerIconSideBarList() throws InterruptedException {
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Verify that sideBarList is open when click on NavigationDrawer Icon Menu");
        headerPO.clickOnHeaderNavigationDrawerIconMenu();

        Reporter.log("Step 2 - Verify that user Navigate to 'Product' page when click on 'All Items' text on SideBar Navigation Drawer List");
        headerPO.clickOnHeaderNavigationDrawerIconSideBarListText("All Items");
        String expectedUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }

    /* Test 4 : Verify that sauceLabs site open when click on 'About' text on SideBar Navigation Drawer List */
    @Test
    public void verifyThatSaucesSiteDisplayedWhenClickedOnAboutTextOFHeaderNavigationDrawerIconSideBarList() throws InterruptedException {
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Verify that sideBarList is open when click on NavigationDrawer Icon Menu");
        headerPO.clickOnHeaderNavigationDrawerIconMenu();

        Reporter.log("Step 2 - Verify that SauceLabs site open when click on 'About' text on SideBar Navigation Drawer List");
        headerPO.clickOnHeaderNavigationDrawerIconSideBarListText("About");
        String expectedUrl = Constants.sauceLabsUrl;
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");

    }

    /* Test 5 : Verify that Login page open when click on 'LogOut' text on SideBar Navigation Drawer List */
    @Test
    public void verifyThatLoginPageDisplayedWhenClickOnLogOutTextOFHeaderNavigationDrawerIconSideBarList() throws InterruptedException {
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Verify that sideBarList is open when click on NavigationDrawer Icon Menu");
        headerPO.clickOnHeaderNavigationDrawerIconMenu();

        Reporter.log("Step 2 - Verify that Login page open when click on 'LogOut' text on SideBar Navigation Drawer List");
        headerPO.clickOnHeaderNavigationDrawerIconSideBarListText("Logout");
        String expectedUrl = Constants.URL + "/index.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }

    /* Test 6 : Verify that 'Your Cart' page displayed when click on Header Cart icon button*/
    @Test
    public void verifyYourCartPageDisplayedWhenClickOnHeaderCartIcon() throws InterruptedException {
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Verify that YourCart page displayed when click on Header Cart icon");
        headerPO.clickOnHeaderCartIconButton();
        String expectedUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }
}
