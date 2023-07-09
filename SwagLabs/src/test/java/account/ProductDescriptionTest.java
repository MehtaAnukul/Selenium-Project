package account;

import base.BaseTest;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.swagLabs.account.LoginPO;
import pageObjects.swagLabs.account.ProductDescriptionPO;
import pageObjects.swagLabs.account.ProductPO;
import pageObjects.swagLabs.common.HeaderPO;
import utilities.Constants;

public class ProductDescriptionTest extends BaseTest {

    /*Test 1 : Verify that product description page is displayed */
    @BeforeMethod
    public void verifyProductDescriptionPageDisplayed() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);
        ProductPO productPO = new ProductPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), user.getPassword());

        Reporter.log("Step 3 - Navigate to Product page");
        String expectedUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");

        Reporter.log("Step 4 - Click on Test.allTheThings() T-Shirt (Red) ProductName");
        productPO.clickOnProductName("Test.allTheThings() T-Shirt (Red)");
        String expectedUrll = Constants.URL + "/inventory-item.html?id=3";
        Assert.assertEquals(selenium.getURL(), expectedUrll, "Url doesn't match");
    }

    /*Test 2 : Verify that user is navigate to previous page when click on Back button */
    @Test
    public void verifyUserNavigateToPreviousPageWhenClickOnBackButton() throws InterruptedException {
        ProductDescriptionPO productDescriptionPO = new ProductDescriptionPO(driver);

        Reporter.log("Step 1 - Verify that user is navigate to previous page when click on Back button");
        productDescriptionPO.productDescriptionPageBackButton();

        Reporter.log("Step 2 - Verify that previous page is displayed");
        String expectedUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }

    /*Test 3 : Verify that Product is added in 'YourCart' page when click on 'AddToCart' button */
    @Test
    public void verifyProductAddedYourCartPageWhenClickOnAddToCartButton() throws InterruptedException {
        ProductDescriptionPO productDescriptionPO = new ProductDescriptionPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 -  Verify that 'Add To Cart' button is replaced by 'Remove' button when user click on 'Add To Cart' button");
        productDescriptionPO.productDescriptionAddToCartButton();

        Reporter.log("Step 2 - Verify that Product is added in 'YourCart' page when click on 'AddToCart' button");
        //productDescriptionPO.productDescriptionYourCartIcon();
        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 3 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }

    /*Test 4 : Verify that added product in YourCart page has been removed when click on 'Remove' button  */
    @Test
    public void verifyProductRemovedInYourCartPageWhenClickOnRemoveButton() throws InterruptedException {
        ProductDescriptionPO productDescriptionPO = new ProductDescriptionPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        verifyProductAddedYourCartPageWhenClickOnAddToCartButton();

        Thread.sleep(2000);
        Reporter.log("Step 1 - Navigate to previous page");
        selenium.back();

        Thread.sleep(2000);
        Reporter.log("Step 2 - Verify that 'Remove' button is replaced by 'AddToCart' button when user click on 'Remove' button ");
        productDescriptionPO.productDescriptionRemoveButton();

        Reporter.log("Step 3 - Verify that Product is removed in 'YourCart' page when click on 'Remove' button");
        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 4 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");
    }
}
