package account;

import base.BaseTest;
import dataFactory.ProductData;
import dataObjectsModel.ProductListModel;
import dataObjectsModel.ProductModel;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.swagLabs.account.LoginPO;
import pageObjects.swagLabs.account.ProductDescriptionPO;
import pageObjects.swagLabs.account.ProductPO;
import pageObjects.swagLabs.account.YourCartPO;
import pageObjects.swagLabs.common.HeaderPO;
import utilities.Constants;

import java.util.List;

public class YourCartTest extends BaseTest {

    /* Test 0 : Verify that YourCart Page is displayed */
    @BeforeMethod
    public void verifyYourCartPageDisplayed() throws InterruptedException{

        LoginPO loginPO = new LoginPO(driver);
        ProductPO productPO = new ProductPO(driver);
        ProductDescriptionPO productDescriptionPO = new ProductDescriptionPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), user.getPassword());

        Reporter.log("Step 3 - Navigate to Product page");
        String expectedUrl = Constants.URL+"/inventory.html";
        Assert.assertEquals(selenium.getURL(),expectedUrl,"Url doesn't match");

        /*Reporter.log("Step 4 - Click on Test.allTheThings() T-Shirt (Red) ProductName");
        productPO.clickOnProductName("Test.allTheThings() T-Shirt (Red)");
        String expectedUrll = Constants.URL+"/inventory-item.html?id=3";
        Assert.assertEquals(selenium.getURL(),expectedUrll,"Url doesn't match");*/
        //using model class List
        Reporter.log("Step 4 - Verify Multiple product get added on YourCart page of clicked multiple product AddToCart button");
        ProductListModel productListModel1 = new ProductData().getProductListModel();
        productPO.clickOnMultipleAddToCartButtonUsingProductListModel(productListModel1);

        /*Thread.sleep(2000);
        Reporter.log("Step 5 -  Verify that Product is added in 'YourCart' page when click on 'AddToCart' button and 'Add To Cart' button is replaced by 'Remove' button");
        productDescriptionPO.productDescriptionAddToCartButton();
*/
        Thread.sleep(2000);

        Reporter.log("Step 8 - Verify that 'YourCart' page displayed when click on YourCart icon");
        headerPO.clickOnHeaderCartIconButton();
        String expectedCartPageUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedCartPageUrl, "Url doesn't match");
    }

    /* Test 1 : Verify that Product is removed in YourCart page when click on 'Remove' button */
    @Test
    public void verifyProductRemovedInYourCartPageWhenClickOnRemoveButton() throws InterruptedException{
        YourCartPO yourCartPO = new YourCartPO(driver);

        Reporter.log("Step 1 - Verify that Product is removed in YourCart page when click on 'Remove' button");
        yourCartPO.yourCartPageRemoveButton();
    }
    /*Test 2 : Verify that Product get removed of clicked product 'Remove' button */
    @Test
    public void verifyMultipleProductGetRemovedOfClickedProductRemoveButton() throws InterruptedException{
        YourCartPO yourCartPO = new YourCartPO(driver);

        Reporter.log("Step 1 - Verify that Product get removed in YourCart page of clicked product 'Remove' button");
        ProductModel productModel = new ProductData().getProductModel();
        yourCartPO.clickOnMultipleYourCartPageRemoveButton(productModel);
    }

    /* Test 3 : Verify that Product page displayed when click on 'Continue Shopping Button' */
    @Test
    public void verifyProductPageDisplayedWhenClickOnContinueShoppingButton() throws InterruptedException{
        YourCartPO yourCartPO = new YourCartPO(driver);

        Reporter.log("Step 1 - Verify that user navigate to Product page when click on 'Continue Shopping Button'");
        yourCartPO.yourCartPageContinueShoppingButton();
        String expectedUrl = Constants.URL+"/inventory.html";
        Assert.assertEquals(selenium.getURL(),expectedUrl,"Url doesn't match");
    }

    /* Test 4 : Verify that Checkout: Your Information page displayed when click on 'Checkout' button */
    @Test
    public void verifyCheckoutYourInformationPageDisplayedWhenClickOnCheckoutButton() throws InterruptedException{
        YourCartPO yourCartPO = new YourCartPO(driver);

        Reporter.log("Step 1 - Verify that user navigate to Checkout: Your Information page when click on 'Checkout Button'");
        yourCartPO.yourCartPageCheckoutButton();
        String expectedUrl = Constants.URL+"/checkout-step-one.html";
        Assert.assertEquals(selenium.getURL(),expectedUrl,"Url doesn't match");
    }

    /* Test 5 : Verify that All product name get displayed of clicked product 'AddToCart' button*/
    @Test
    public void verifyThatAllProductNameGetDisplayedOfClickedProduct() throws InterruptedException{
        YourCartPO yourCartPO = new YourCartPO(driver);
        Reporter.log("Step 1 - Verify that All product name get displayed of clicked product 'AddToCart' button");
        List<String> allProductNameList = yourCartPO.getProductNameList();
        for (int i=0;i<allProductNameList.size();i++){
            System.out.println(allProductNameList.get(i));
            //Assert.assertEquals(yourCartPO.getProductName(),allProductNameList.get(i),"ProductName doesn't match");
        }
        Assert.assertEquals(allProductNameList.get(0),"Sauce Labs Backpack","ProductName doesn't match");
        Assert.assertEquals(allProductNameList.get(1),"Sauce Labs Bolt T-Shirt","ProductName doesn't match");
        Assert.assertEquals(allProductNameList.get(2),"Sauce Labs Onesie","ProductName doesn't match");
    }
}
