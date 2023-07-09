package common;

import account.LoginTest;
import base.BaseTest;
import dataFactory.CheckOutData;
import dataFactory.ProductData;
import dataObjectsModel.CheckOutModel;
import dataObjectsModel.ProductListModel;
import dataObjectsModel.ProductModel;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.swagLabs.account.*;
import pageObjects.swagLabs.common.HeaderPO;
import utilities.Constants;

import java.util.List;


public class EndToEndTest extends BaseTest {

    /*Test 1 : Verify that EndToEnd test working fine*/
    @Test
    public void endToEndFlow() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);
        ProductPO productPO = new ProductPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);
        YourCartPO yourCartPO = new YourCartPO(driver);
        CheckOutPO checkOutPO = new CheckOutPO(driver);
        CheckOutOverviewPO checkOutOverviewPO = new CheckOutOverviewPO(driver);
        ProductModel productModel;
        ProductListModel productListModel;

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), user.getPassword());

        Reporter.log("Step 3 - Verify that User navigate to Product page");
        String expectedUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");

        //using model class List
        Reporter.log("Step 4 - Verify Multiple product get added on YourCart page of clicked multiple product AddToCart button");
        ProductListModel productListModel1 = new ProductData().getProductListModel();
        productPO.clickOnMultipleAddToCartButtonUsingProductListModel(productListModel1);

        Reporter.log("Step 5 - Verify that valid total price get added Of clicked product 'AddToCart' button");
        productModel = new ProductData().getProductModel();
        String product1Price = productPO.getProductPrice(productModel.getProductName1()).replace("$","");
        String product2Price = productPO.getProductPrice(productModel.getProductName2()).replace("$","");
        String product3Price = productPO.getProductPrice(productModel.getProductName3()).replace("$","");
        //System.out.println(product1Price);

        Reporter.log("Step 6 - Verify that 'YourCart' page displayed when click on YourCart icon");
        headerPO.clickOnHeaderCartIconButton();
        String expectedCartPageUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedCartPageUrl, "Url doesn't match");
        Thread.sleep(5000);

        Reporter.log("Step 7 - Verify that All product name get displayed of clicked product 'AddToCart' button");
        List<String> allProductNameList = yourCartPO.getProductNameList();
        for (int i=0;i<allProductNameList.size();i++){
            System.out.println(allProductNameList.get(i));
            //Assert.assertEquals(yourCartPO.getProductName(),allProductNameList.get(i),"ProductName doesn't match");
        }
        Assert.assertEquals(allProductNameList.get(0),"Sauce Labs Backpack","ProductName doesn't match");
        Assert.assertEquals(allProductNameList.get(1),"Sauce Labs Bolt T-Shirt","ProductName doesn't match");
        Assert.assertEquals(allProductNameList.get(2),"Sauce Labs Onesie","ProductName doesn't match");

        Assert.assertEquals(allProductNameList.get(0),productModel.getProductName1());

        Reporter.log("Step 8 - Verify that user navigate to Checkout: Your Information page when click on 'Checkout Button'");
        yourCartPO.yourCartPageCheckoutButton();
        String expectedCheckOutYourInfoPageUrl = Constants.URL + "/checkout-step-one.html";
        Assert.assertEquals(selenium.getURL(), expectedCheckOutYourInfoPageUrl, "Url doesn't match");

        Thread.sleep(2000);
        Reporter.log("Step 9 - Fill out CheckOut form with valid details and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getCheckOutModel();
        checkOutPO.fillCheckOutDetails(checkOutModel);

        Reporter.log("Step 10 - Navigate to CheckOut: Overview page when click on 'Continue' button");
        String expectedUrll = Constants.URL + "/checkout-step-two.html";
        Assert.assertEquals(selenium.getURL(), expectedUrll, "Url doesn't match");

        Reporter.log("Step 11 - Verify that valid total price get added Of clicked product 'AddToCart' button");
        double product1PriceDouble = Double.parseDouble(product1Price);
        double product2PriceDouble = Double.parseDouble(product2Price);
        double product3PriceDouble = Double.parseDouble(product3Price);
        double totalPrice = product1PriceDouble + product2PriceDouble + product3PriceDouble;
        Assert.assertEquals(checkOutOverviewPO.getItemTotalPrice(),"Item total: $"+totalPrice);

        Thread.sleep(2000);
        Reporter.log("Step 12 - Verify Click on Finish button");
        checkOutOverviewPO.clickOnFinishButton();

        Reporter.log("Step 13 - Verify 'Finish' page get displayed when clicked on 'Finish' button");
        String expectedFinishUrl = Constants.URL + "/checkout-complete.html";
        Assert.assertEquals(selenium.getURL(), expectedFinishUrl, "Url doesn't match");
    }
}
