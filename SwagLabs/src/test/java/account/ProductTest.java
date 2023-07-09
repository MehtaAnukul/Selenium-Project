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
import pageObjects.swagLabs.account.ProductPO;
import pageObjects.swagLabs.account.YourCartPO;
import pageObjects.swagLabs.common.HeaderPO;
import utilities.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductTest extends BaseTest {

    @BeforeMethod
    /*Test 1 : Verify that product page is displayed  */
    public void verifyProductPageDisplayed() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        Thread.sleep(3000);
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), user.getPassword());

        Reporter.log("Step 3 - Navigate to Product page");
        String expectedProductUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedProductUrl, "Url doesn't match");
    }

    /*Test 2 : Verify 'Product description' page is displayed when click on 'sauceLabsBackpack' name */
    @Test
    public void verifyThatProductDescriptionPageGetDisplayedOFClickedProductName() throws InterruptedException {
        ProductPO productPO = new ProductPO(driver);

        Reporter.log("Step 1 - Click on Sauce Labs Onesie ProductName");
        productPO.clickOnProductName("Sauce Labs Onesie");

        Reporter.log("Step 2 - Verify that Product description page get displayed of clicked 'Product Name'");
        String expectedProductDescriptionUrl = Constants.URL + "/inventory-item.html?id=2";
        Assert.assertEquals(selenium.getURL(), expectedProductDescriptionUrl, "Url doesn't match");
    }

    /*Test 3 : 'Product description' page is displayed when click on 'sauceLabsBackpack' product image */
    @Test
    public void verifyThatProductDescriptionPageGetDisplayedOFClickedProductImage() throws InterruptedException {
        ProductPO productPO = new ProductPO(driver);

        Reporter.log("Step 1 - Click on Sauce Labs Bolt T-Shirt ProductImage");
        productPO.clickOnProductImageIDXpath("item_1_img_link");

        Reporter.log("Step 2 - Verify that Product description page get displayed of clicked 'Product Image'");
        String expectedProductDescriptionUrl = Constants.URL + "/inventory-item.html?id=1";
        Assert.assertEquals(selenium.getURL(), expectedProductDescriptionUrl, "Url doesn't match");

    }

    /*Test 4 : Verify that Product get added on YourCart page Of Clicked Product AddToCart button*/
    @Test
    public void verifyThatProductGetAddedOnYourCartPageOFClickedProductAddToCartButton() throws InterruptedException{
        ProductPO productPO = new ProductPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Thread.sleep(2000);
        Reporter.log("Step 1 - Verify that Product get added on YourCart page of Clicked product 'AddToCart' button and 'AddToCart' button is replaced by 'Remove' button");
        productPO.clickOnProductAddToCartButton("Test.allTheThings() T-Shirt (Red)");

        Thread.sleep(2000);
        Reporter.log("Step 2 - Verify that Product is added in 'YourCart' page when click on 'AddToCart' button");
        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 3 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedCartUrl = Constants.URL+"/cart.html";
        Assert.assertEquals(selenium.getURL(),expectedCartUrl,"Url doesn't match");
    }

    /*Test 4 : Verify that Product get added on YourCart page Of Clicked Product AddToCart button*/   //Click Multiple Product AddToCartButton using  ProductModel as parameter
    @Test
    public void verifyThatProductGetAddedOnYourCartPageOFClickedProductAddToCartButtonUsingMultipleProductModelAsParameter() throws InterruptedException{
        ProductPO productPO = new ProductPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Verify Multiple product get added on YourCart page of clicked multiple product AddToCart button");
        ProductModel productModel1 = new ProductData().getProductModel();
        productPO.clickOnMultipleAddToCartButton(productModel1);

        Thread.sleep(2000);
        Reporter.log("Step 2 - Verify that Product is added in 'YourCart' page when click on 'AddToCart' button");
        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 3 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedCartUrl = Constants.URL+"/cart.html";
        Assert.assertEquals(selenium.getURL(),expectedCartUrl,"Url doesn't match");
    }

    /*Test 4 : Verify that Product get added on YourCart page Of Clicked Product AddToCart button*/   //Click Multiple Product AddToCartButton using ProductListModel as parameter
    @Test
    public void verifyThatProductGetAddedOnYourCartPageOFClickedProductAddToCartButtonUsingMultipleProductListModelAsParameter() throws InterruptedException{
        ProductPO productPO = new ProductPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Verify Multiple product get added on YourCart page of clicked multiple product AddToCart button");
        ProductListModel productListModel = new ProductData().getProductListModel();
        productPO.clickOnMultipleAddToCartButtonUsingProductListModel(productListModel);

        Thread.sleep(2000);
        Reporter.log("Step 2 - Verify that Product is added in 'YourCart' page when click on 'AddToCart' button");
        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 3 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedCartUrl = Constants.URL+"/cart.html";
        Assert.assertEquals(selenium.getURL(),expectedCartUrl,"Url doesn't match");
    }

    /*Test 4 : Verify that Product get added on YourCart page Of Clicked Product AddToCart button*/  //Click on Product AddToCartButton using ProductModel as parameter
    @Test
    public void verifyThatProductGetAddedOnYourCartPageOFClickedProductAddToCartButtonUsingProductModelAsParameter() throws InterruptedException{
        ProductPO productPO = new ProductPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);
        ProductModel productModel;


        Reporter.log("Step 1 - Verify that 'Sauce Labs Backpack' product get added on YourCart page Of clicked product 'AddToCart' button and 'AddToCart' button is replaced by 'Remove' button");
        productModel = new ProductData().getProductModel();
        productPO.clickOnProductAddToCartButtonUsingProductModel(productModel.getProductName1());

        Reporter.log("Step 2 - Verify that 'Sauce Labs Bolt T-Shirt' product get added on YourCart page Of clicked product 'AddToCart' button and 'AddToCart' button is replaced by 'Remove' button");
        productModel = new ProductData().getProductModel();
        productPO.clickOnProductAddToCartButtonUsingProductModel(productModel.getProductName2());

        Reporter.log("Step 3 - Verify that 'Sauce Labs Onesie' product get added on YourCart page Of clicked product 'AddToCart' button and 'AddToCart' button is replaced by 'Remove' button");
        productModel = new ProductData().getProductModel();
        productPO.clickOnProductAddToCartButtonUsingProductModel(productModel.getProductName3());

        Thread.sleep(2000);
        Reporter.log("Step 4 - Verify that Product is added in 'YourCart' page when click on 'AddToCart' button");
        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 5 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedCartUrl = Constants.URL+"/cart.html";
        Assert.assertEquals(selenium.getURL(),expectedCartUrl,"Url doesn't match");
    }

    /*Test 4 : Verify that Product get added on YourCart page Of Clicked Product AddToCart button */ //Click on Product AddToCartButton Using String ProductListModel as parameter
    @Test
    public void verifyThatProductGetAddedOnYourCartPageOFClickedProductAddToCartButtonUsingProductListModelAsParameter() throws InterruptedException {
        ProductPO productPO = new ProductPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);
        ProductListModel productListModel;

        Reporter.log("Step 1 - Verify that 'Sauce Labs Backpack' product get added on YourCart page Of clicked product 'AddToCart' button and 'AddToCart' button is replaced by 'Remove' button");
        productListModel = new ProductData().getProductListModel();
        productPO.clickOnProductAddToCartButtonUsingProductListModel(productListModel.getProductModelList().get(0).getProductName1());

        Reporter.log("Step 2 - Verify that 'Sauce Labs Bolt T-Shirt' product get added on YourCart page Of clicked product 'AddToCart' button and 'AddToCart' button is replaced by 'Remove' button");
        productListModel = new ProductData().getProductListModel();
        productPO.clickOnProductAddToCartButtonUsingProductListModel(productListModel.getProductModelList().get(0).getProductName2());

        Reporter.log("Step 3 - Verify that 'Sauce Labs Onesie' product get added on YourCart page Of clicked product 'AddToCart' button and 'AddToCart' button is replaced by 'Remove' button");
        productListModel = new ProductData().getProductListModel();
        productPO.clickOnProductAddToCartButtonUsingProductListModel(productListModel.getProductModelList().get(0).getProductName3());

        Thread.sleep(2000);

        selenium.refreshPage();

        Thread.sleep(2000);
        Reporter.log("Step 4 - Verify that Product is added in 'YourCart' page when click on 'AddToCart' button");
        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 5 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedCartUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedCartUrl, "Url doesn't match");
    }

    /*Test 5 : 'Product description' page is displayed when click on Remove button after product is on addToCart of 'Sauce Labs Bike Light' product name*/
    @Test
    public void verifyThatProductGetRemovedOnYourCartPageOFClickedProductRemoveButton() throws InterruptedException {
        ProductPO productPO = new ProductPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        verifyThatProductGetAddedOnYourCartPageOFClickedProductAddToCartButton();

        Thread.sleep(2000);
        Reporter.log("Step 1 - Navigate to previous page");
        selenium.back();

        Thread.sleep(2000);

        Reporter.log("Step 2 - Verify that Product get removed on YourCart page of Clicked product 'Remove' button and 'Remove' button is replaced by 'AddToCart' button");
        productPO.clickOnProductRemoveButton("Sauce Labs Bolt T-Shirt");

        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 3 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedCartUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedCartUrl, "Url doesn't match");
    }

    /*Test 6 : Verify that 'Product' is displayed according to 'Product sort container(dropDown)'*/
    @Test
    public void verifyThatProductGetSortedOFClickedProductSortingOptionInProductSortDropDown() throws InterruptedException {
        ProductPO productPO = new ProductPO(driver);

        Reporter.log("Step 1 - Click on Product sort Dropdown(container)");
        productPO.clickOnProductSortDropDown();

        Reporter.log("Step 2 - Click on 'productSortDropDown' and Select sort option");
        productPO.clickOnProductSortDropDownSelectSortOption("Price (high to low)");

        Reporter.log("Step 3 - Click on Product sort Dropdown(container)");
        productPO.clickOnProductSortDropDown();

    }

    /*Test 6 : Verify that All Product name get sorted of clicked product sort option */
    @Test
    public  void verifyThatAllProductNameGetSortedOfClickedProductSortOption() throws InterruptedException{
        ProductPO productPO = new ProductPO(driver);
        ProductModel productModel = new ProductModel();

        Reporter.log("Step 1 - Click on Product sort Dropdown(container)");
        productPO.clickOnProductSortDropDown();

        Reporter.log("Step 2 - Click on 'productSortDropDown' and Select sort option");
        productModel = new ProductData().getProductModel();
        productPO.clickOnProductSortDropDownSelectSortOptionUsingModel(productModel.getProductPriceLowToHighSort());

        Reporter.log("Step 3 - Verify that All Product name get sorted of clicked product sort option");
        List<String> allProductNameList = productPO.getProductNameList();
        ArrayList<String> sortedList = new ArrayList<>();
        for(String s:allProductNameList){
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        Assert.assertTrue(sortedList.equals(allProductNameList));
        Assert.assertEquals(sortedList,allProductNameList,"Product Name not sorted");
        System.out.println(sortedList.equals(allProductNameList));
        System.out.println(sortedList);
        System.out.println("");
        System.out.println(allProductNameList);
    }

    /*Test 6 : Verify that All Product name get sorted of clicked product sort option */
    @Test
    public  void verifyThatAllProductNameGetSortedOfClickedProductSortOptionUsingPrice() throws InterruptedException{
        ProductPO productPO = new ProductPO(driver);
        ProductModel productModel = new ProductModel();

        Reporter.log("Step 1 - Click on Product sort Dropdown(container)");
        productPO.clickOnProductSortDropDown();

        Reporter.log("Step 2 - Click on 'productSortDropDown' and Select sort option");
        productModel = new ProductData().getProductModel();
        productPO.clickOnProductSortDropDownSelectSortOptionUsingModel(productModel.getProductPriceLowToHighSort());

        Reporter.log("Step 3 - Verify that All Product name get sorted of clicked product sort option");
        List<Double> allProductNameList = productPO.getProductPriceList();
        ArrayList<String> sortedList = new ArrayList<>();
        for(Double s:allProductNameList){
            sortedList.add(String.valueOf(s));
        }
        Collections.sort(sortedList);
       // Collections.reverse(sortedList);
       // Assert.assertTrue(sortedList.equals(allProductNameList));
       // Assert.assertEquals(sortedList,allProductNameList,"Product Name not sorted");
        System.out.println(sortedList.equals(allProductNameList));
        System.out.println(sortedList);
        System.out.println("");
        System.out.println(allProductNameList);
    }
}
