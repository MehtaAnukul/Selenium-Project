package account;

import base.BaseTest;
import dataFactory.CheckOutData;
import dataFactory.ProductData;
import dataObjectsModel.CheckOutModel;
import dataObjectsModel.ProductListModel;
import dataObjectsModel.ProductModel;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.swagLabs.account.*;
import pageObjects.swagLabs.common.HeaderPO;
import utilities.Constants;

public class CheckOutOverviewTest extends BaseTest {
    String product1Price;
    String product2Price;
    String product3Price;


    /* Login to CheckOut overview page process method*/
    @BeforeMethod
    public void loginToCheckOutOverviewPage() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);
        ProductPO productPO = new ProductPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);
        YourCartPO yourCartPO = new YourCartPO(driver);
        CheckOutPO checkOutPO = new CheckOutPO(driver);
        //ProductModel productModel;

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), user.getPassword());

        Reporter.log("Step 3 - Verify that User navigate to Product page");
        String expectedUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");

        Reporter.log("Step 4 - Verify Multiple product get added on YourCart page of clicked multiple product AddToCart button");
        ProductModel productModel = new ProductData().getProductModel();
        productPO.clickOnMultipleAddToCartButton(productModel);

        Reporter.log("Step 7 - Verify that valid total price get added Of clicked product 'AddToCart' button");
        productModel = new ProductData().getProductModel();
        product1Price = productPO.getProductPrice(productModel.getProductName1()).replace("$29.99","29.99");
        product2Price = productPO.getProductPrice(productModel.getProductName2()).replace("$15.99","15.99");
        product3Price = productPO.getProductPrice(productModel.getProductName3()).replace("$7.99","7.99");
        //System.out.println(product1Price);

        Reporter.log("Step 5 - Verify that 'YourCart' page displayed when click on YourCart icon");
        headerPO.clickOnHeaderCartIconButton();
        String expectedCartPageUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedCartPageUrl, "Url doesn't match");
        Thread.sleep(5000);

        Reporter.log("Step 6 - Verify that user navigate to Checkout: Your Information page when click on 'Checkout Button'");
        yourCartPO.yourCartPageCheckoutButton();
        String expectedCheckOutYourInfoPageUrl = Constants.URL + "/checkout-step-one.html";
        Assert.assertEquals(selenium.getURL(), expectedCheckOutYourInfoPageUrl, "Url doesn't match");

        Thread.sleep(2000);
        Reporter.log("Step 7 - Fill out CheckOut form with valid details and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getCheckOutModel();
        checkOutPO.fillCheckOutDetails(checkOutModel);

        Reporter.log("Step 8 - Navigate to CheckOut: Overview page when click on 'Continue' button");
        String expectedUrll = Constants.URL + "/checkout-step-two.html";
        Assert.assertEquals(selenium.getURL(), expectedUrll, "Url doesn't match");
    }

    /* Test 1 - Verify 'Product description page' get displayed of clicked product name  */
    @Test
    public void verifyThatProductDescriptionPageGetDisplayedOfClickedProductName() throws InterruptedException {
        ProductPO productPO = new ProductPO(driver);
        ProductDescriptionPO productDescriptionPO = new ProductDescriptionPO(driver);

        Reporter.log("Step 9 - Click on Sauce Labs Bolt T-Shirt ProductName");
        productPO.clickOnProductName("Sauce Labs Bolt T-Shirt");

        Reporter.log("Step 10 - Verify 'Product description page' get displayed of clicked product name");
        String expectedInventoryUrl = Constants.URL + "/inventory-item.html?id=1";
        Assert.assertEquals(selenium.getURL(), expectedInventoryUrl, "Url doesn't match");

        Reporter.log("Step 11 - Click on Back button");
        productDescriptionPO.productDescriptionPageBackButton();

        Reporter.log("Step 12 - Navigate to CheckOut: Overview page when click on 'back' button");
        String expectedCheckOutOverviewPageUrl = Constants.URL + "/checkout-step-two.html";
        Assert.assertEquals(selenium.getURL(), expectedCheckOutOverviewPageUrl, "Url doesn't match");
    }

    /* Test 2 - Verify 'Product' page get displayed when clicked on 'Cancel' button  */
    @Test
    public void verifyThatProductPageGetDisplayedWhenClickedOnCancelButton() throws InterruptedException {
        CheckOutOverviewPO checkOutOverviewPO = new CheckOutOverviewPO(driver);

        Reporter.log("Step 9 - Verify Click on Cancel button");
        checkOutOverviewPO.clickOnCancelButton();

        Reporter.log("Step 10 - Verify 'Product' page get displayed when clicked on 'Cancel' button ");
        String expectedProductUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedProductUrl, "Url doesn't match");
    }

    /* Test 3 - Verify 'Finish' page get displayed when clicked on 'Finish' button */
    @Test
    public void verifyThatFinishPageGetDisplayedWhenClickedOnFinishButton() throws InterruptedException {
        CheckOutOverviewPO checkOutOverviewPO = new CheckOutOverviewPO(driver);

        Reporter.log("Step 9 - Verify Click on Finish button");
        checkOutOverviewPO.clickOnFinishButton();

        Reporter.log("Step 10 - Verify 'Finish' page get displayed when clicked on 'Finish' button");
        String expectedFinishUrl = Constants.URL + "/checkout-complete.html";
        Assert.assertEquals(selenium.getURL(), expectedFinishUrl, "Url doesn't match");
    }

    /*Test 4 - Verify that Valid total price get displayed of clicked products*/
    @Test
    public void verifyThatValidTotalPriceGetDisplayedOFClickedProducts() throws InterruptedException{
        CheckOutOverviewPO checkOutOverviewPO = new CheckOutOverviewPO(driver);

        Reporter.log("Step 12 - Verify that valid total price get added Of clicked product 'AddToCart' button");
        double product1PriceDouble = Double.parseDouble(product1Price);
        double product2PriceDouble = Double.parseDouble(product2Price);
        double product3PriceDouble = Double.parseDouble(product3Price);
        double totalPrice = product1PriceDouble + product2PriceDouble + product3PriceDouble;
        Assert.assertEquals(checkOutOverviewPO.getItemTotalPrice(),"Item total: $"+totalPrice);
    }


}
