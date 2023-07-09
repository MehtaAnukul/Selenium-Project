package account;

import base.BaseTest;
import dataFactory.CheckOutData;
import dataObjectsModel.CheckOutModel;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.swagLabs.account.*;
import pageObjects.swagLabs.common.HeaderPO;
import utilities.Constants;

public class CheckOutTest extends BaseTest {


    /*Test 0 : Above page data */
    @BeforeMethod
    public void AboveAllProcess() throws InterruptedException {
        LoginPO loginPO = new LoginPO(driver);
        ProductPO productPO = new ProductPO(driver);
        ProductDescriptionPO productDescriptionPO = new ProductDescriptionPO(driver);
        HeaderPO headerPO = new HeaderPO(driver);

        Reporter.log("Step 1 - Navigate to login page");
        selenium.navigateToPage(Constants.URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        loginPO.fillLoginDetailsAndPerformLogin(user.getUserName(), user.getPassword());

        Reporter.log("Step 3 - Navigate to Product page");
        String expectedUrl = Constants.URL + "/inventory.html";
        Assert.assertEquals(selenium.getURL(), expectedUrl, "Url doesn't match");

        Reporter.log("Step 4 - Click on Test.allTheThings() T-Shirt (Red) ProductName");
        productPO.clickOnProductName("Test.allTheThings() T-Shirt (Red)");
        String expectedInventoryUrl = Constants.URL + "/inventory-item.html?id=3";
        Assert.assertEquals(selenium.getURL(), expectedInventoryUrl, "Url doesn't match");

        Thread.sleep(2000);
        Reporter.log("Step 5 -  Verify that 'Add To Cart' button is replaced by 'Remove' button when user click on 'Add To Cart' button");
        productDescriptionPO.productDescriptionAddToCartButton();

        Thread.sleep(2000);

        Reporter.log("Step 6 - Verify that Product is added in 'YourCart' page when click on 'AddToCart' button");
        //productDescriptionPO.productDescriptionYourCartIcon();
        headerPO.clickOnHeaderCartIconButton();

        Reporter.log("Step 7 - Verify that 'YourCart' page displayed when click on YourCart icon");
        String expectedCartPageUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedCartPageUrl, "Url doesn't match");

        YourCartPO yourCartPO = new YourCartPO(driver);
        Reporter.log("Step 8 - Verify that user navigate to Checkout: Your Information page when click on 'Checkout Button'");
        yourCartPO.yourCartPageCheckoutButton();
        String expectedCheckOutPageUrl = Constants.URL + "/checkout-step-one.html";
        Assert.assertEquals(selenium.getURL(), expectedCheckOutPageUrl, "Url doesn't match");
    }

    /*Test 1 : Verify that user checkOut successfully with valid details */
    @Test
    public void verifyThatUserCheckOutSuccessfullyWithValidDetails() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Thread.sleep(2000);
        Reporter.log("Step 9 - Fill out CheckOut form with valid details and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getCheckOutModel();
        checkOutPO.fillCheckOutDetails(checkOutModel);

        Reporter.log("Step 10 - Navigate to CheckOut: Overview page when click on 'Continue' button");
        String expectedCheckOutOverviewPageUrl = Constants.URL + "/checkout-step-two.html";
        Assert.assertEquals(selenium.getURL(), expectedCheckOutOverviewPageUrl, "Url doesn't match");
    }

    /*Test 2 : Verify that Validation message displayed when Leaves all field blank and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedWhenLeavesAllFiledBlankAndClickOnContinueButton() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Thread.sleep(2000);
        Reporter.log("Step 9 - Verify that Validation message displayed when Leaves all field blank and click on 'Continue' button ");
        CheckOutModel checkOutModel = new CheckOutData().getAllFieldBlank();
        checkOutPO.fillCheckOutDetails(checkOutModel);

        Reporter.log("Step 10 - Verify that validation message is displayed");
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(checkOutPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error message doesn't match");
    }

    /*Test 3 : Verify that Validation message displayed when enter valid 'First name' and remaining field blank and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedForLastNameAndZipCodeFieldBlankAndClickOnContinueButton() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that Validation message displayed For LastName and ZipCode field when enter valid 'First name' and remaining field blank and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getValidFirstName();
        checkOutPO.fillCheckOutDetails(checkOutModel);

        Reporter.log("Step 10 - Verify that validation message is displayed");
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(checkOutPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error message doesn't match");
    }

    /*Test 4 : Verify that Validation message displayed For Blank filed when enter valid 'First name' ,'Last name' and 'Zip code' field blank and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedForZipCodeFieldBlankAndClickOnContinueButton() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that Validation message displayed when enter valid 'First name' ,'Last name' and 'Zip code' field blank and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getValidFirstAndLastName();
        checkOutPO.fillCheckOutDetails(checkOutModel);

        Reporter.log("Step 10 - Verify that validation message is displayed");
        String expectedErrorMessage = "Error: Postal Code is required";
        Assert.assertEquals(checkOutPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error message doesn't match");
    }

    /*Test 5 : Verify that Validation message displayed For Blank filed when enter valid 'First name' ,'Zip Code' and 'Last name' field blank and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedForLastNameFieldBlankAndClickOnContinueButton() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that Validation message displayed For Blank filed when enter valid 'First name' ,'Zip Code' and 'Last name' field blank and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getValidFirstAndZipCode();
        checkOutPO.fillCheckOutDetails(checkOutModel);

        Reporter.log("Step 10 - Verify that validation message is displayed");
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(checkOutPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error message doesn't match");
    }

    /*Test 6 : Verify that Validation message displayed when enter valid 'Last name' and 'Zip code' field and 'First name' field blank and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedForFirstNameFieldBlankAndClickOnContinueButton() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that Validation message displayed when enter valid 'Last name' and 'Zip code' field and 'First name' field blank and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getValidLastNameAndZipCode();
        checkOutPO.fillCheckOutDetails(checkOutModel);

        Reporter.log("Step 10 - Verify that validation message is displayed");
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(checkOutPO.getInvalidDataValidationMessage(), expectedErrorMessage, "Error message doesn't match");
    }

    /*Test 7 : Verify that Validation message displayed when User enter invalid 'First name', 'Last name 'and enter valid 'Zip code' field and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedForFirstNameAndLastNameInvalidAndClickOnContinueButton() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that Validation message displayed when User enter invalid 'First name', 'Last name 'and enter valid 'Zip code' field and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getInvalidFirstNameAndLastName();
        checkOutPO.fillCheckOutDetails(checkOutModel);
    }

    /*Test 8 : Verify that Validation message displayed when User enter invalid 'First name', 'Zip code 'and enter valid 'Last name' field and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedForFirstNameAndZipCodeInvalidAndClickOnContinueButton() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that Validation message displayed when User enter invalid 'First name', 'Zip code 'and enter valid 'Last name' field and click on 'Continue' button ");
        CheckOutModel checkOutModel = new CheckOutData().getInvalidFirstNameAndZipCode();
        checkOutPO.fillCheckOutDetails(checkOutModel);
    }

    /*Test 9 : Verify that Validation message displayed when User enter invalid 'Last name', 'Zip code 'and enter valid 'First name' field and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedForLastNameAndZipCodeInvalidAndClickOnContinueButton() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that Validation message displayed when User enter invalid 'Last name', 'Zip code 'and enter valid 'First name' field and click on 'Continue' button ");
        CheckOutModel checkOutModel = new CheckOutData().getInvalidLastNameAndZipCode();
        checkOutPO.fillCheckOutDetails(checkOutModel);
    }

    /*Test 10 : Verify that user navigate to previous page when click on Cancel button */
    @Test
    public void verifyClickOnCancelButtonUserNavigateToCartPage() throws InterruptedException {
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that user navigate to previous page when click on Cancel button");
        checkOutPO.cancelButton();
        //selenium.back();

        Reporter.log("Step 10 - Verify that Previous page is displayed");
        String expectedCartUrl = Constants.URL + "/cart.html";
        Assert.assertEquals(selenium.getURL(), expectedCartUrl, "Url doesn't match");
    }

    /*Test 11 : Verify that Validation message displayed when User enter spacial & junk character on 'FirstName','LastName' And 'ZipCode' field and click on 'Continue' button */
    @Test
    public void verifyValidationMessageDisplayedForFirstNameLastNameAndZipCodeFieldsAndClickOnContinueButton() throws InterruptedException{
        CheckOutPO checkOutPO = new CheckOutPO(driver);

        Reporter.log("Step 9 - Verify that Validation message displayed when User enter spacial & junk character on 'FirstName','LastName' And 'ZipCode' field and click on 'Continue' button");
        CheckOutModel checkOutModel = new CheckOutData().getSpacialAndJunkCharacterData();
        checkOutPO.fillCheckOutDetails(checkOutModel);
    }
}
