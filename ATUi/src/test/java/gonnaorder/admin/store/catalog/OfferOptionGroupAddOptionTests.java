package gonnaorder.admin.store.catalog;

import base.BaseTest;
import datafactory.gonnaorder.admin.OfferData;
import dataobjects.gonnaorder.admin.Offer;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.catalog.AddOfferPO;
import pageobjects.gonnaorder.admin.store.catalog.AddOptionPO;
import pageobjects.gonnaorder.admin.store.catalog.CatalogPO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.OfferDetailsPO;
import pageobjects.gonnaorder.customer.common.FooterPO;
import utilities.Constants;

public class OfferOptionGroupAddOptionTests extends BaseTest {

    /*Test 1 : Verify that user can add option with 'Exactly one option' option group at admin ui and displayed at customer UI [sellable = Yes]*/
    @Test
    public void verifyAddOptionWithExactlyOneOptionGroupAtAdminUiWithSellableYesViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionPO addOption = new AddOptionPO(driver);
        LandingPO landing = new LandingPO(driver);
        OfferDetailsPO offerDetails = new OfferDetailsPO(driver);
        FooterPO footer = new FooterPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit offer Icon'");
        String category = Constants.StoreCategory5;
        String offer = Constants.StoreOffer5;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Add option' link");
        String optionGroup = Constants.StoreOptionGroup5;
        addOffer.clickOnAddOptionLink(optionGroup);

        Reporter.log("Step 6 - Fill the form with 'Exactly one option' option group and save the option");
        Offer offerData = new OfferData(). getAddOptionData();
        String optionName = offerData.getName();
        String offerPrice = Constants.StoreOfferPrice5;
        String offerPriceFloat= Constants.StoreOfferFloatPrice5;
        String optionPriceFloat = offerData.getPriceFloat();
        String optionPriceDecimal = offerData.getPriceDecimal();
        addOption.fillOptionDetail(offerData);

        Reporter.log("Step 7 - Verify that Added option is Present in Edit Offer Section");
        Assert.assertTrue(addOffer.isOptionNamePresent(optionGroup, optionName), "Option Name isn't present");

        Reporter.log("Step 8 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 9 - Verify that created option is present on customer ui.");
        Assert.assertTrue(offerDetails.isOptionPresent(optionGroup, optionName), "Option isn't present");
        Assert.assertEquals(offerDetails.getOptionPrice(optionGroup,optionName), "(+ € " + optionPriceDecimal + ","  + optionPriceFloat +")", "Total price doesn't match");

        Reporter.log("Step 10 - Select the option");
        offerDetails.clickOnOption(optionGroup,optionName);

        Reporter.log("Step 11 - Verify that total price(offer price + option price) is displayed on the 'Add to your order' button.");
        float discountedValue = Float.parseFloat (offerPrice + "." + offerPriceFloat) + (Float.parseFloat(optionPriceDecimal + "." + optionPriceFloat));
        String str_discountedValue = String.format("%.2f",discountedValue).replace(".", ",");
        Assert.assertEquals(footer.getTotalPriceFromAddToYourOrderButton(), "Add to your order ( € "+str_discountedValue +" )", "Total price doesn't match");

        Reporter.log("Clean up - Delete the created option at admin ui.");
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnOption(optionGroup,optionName);
        addOption.clickOnDeleteLink();
    }

    /*Test 2 : Verify that user can add option with 'Exactly one option' option group at admin ui and not displayed at customer UI [sellable = No]*/
    @Test
    public void verifyAddOptionWithExactlyOneOptionGroupAtAdminUiWithSellableNoAndNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionPO addOption = new AddOptionPO(driver);
        LandingPO landing = new LandingPO(driver);
        OfferDetailsPO offerDetails = new OfferDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit offer Icon'");
        String category = Constants.StoreCategory5;
        String offer = Constants.StoreOffer5;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Add option' link");
        String optionGroup = Constants.StoreOptionGroup5;
        addOffer.clickOnAddOptionLink(optionGroup);

        Reporter.log("Step 6 - Fill the form with 'Exactly one option' option group and save the option");
        Offer offerData = new OfferData(). getAddOptionData();
        offerData.setSellable(false);
        String optionName = offerData.getName();
        addOption.fillOptionDetail(offerData);

        Reporter.log("Step 7 - Verify that Added option is Present in Edit Offer Section");
        Assert.assertTrue(addOffer.isOptionNamePresent(optionGroup, optionName), "Option Name isn't present");

        Reporter.log("Step 8 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 9 - Verify that created option is not present on customer ui.");
        Assert.assertFalse(offerDetails.isOptionPresent(optionGroup, optionName), "Option is present");

        Reporter.log("Clean up - Delete the created option at admin ui.");
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnOption(optionGroup,optionName);
        addOption.clickOnDeleteLink();
    }

    /*Test 3 : Verify that user can add option with 'Any number option' option group at admin ui and displayed at customer UI [sellable = Yes]*/
    @Test
    public void verifyAddOptionWithAnyNumberOptionGroupAtAdminUiWithSellableYesViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionPO addOption = new AddOptionPO(driver);
        LandingPO landing = new LandingPO(driver);
        OfferDetailsPO offerDetails = new OfferDetailsPO(driver);
        FooterPO footer = new FooterPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit offer Icon'");
        String category = Constants.StoreCategory5;
        String offer = Constants.StoreOffer5;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Add option' link");
        String optionGroup = Constants.StoreOptionGroup15;
        addOffer.clickOnAddOptionLink(optionGroup);

        Reporter.log("Step 6 - Fill the form with 'Any number option' option group and save the option");
        Offer offerData = new OfferData(). getAddOptionData();
        String optionName = offerData.getName();
        String offerPrice = Constants.StoreOfferPrice5;
        String offerPriceFloat= Constants.StoreOfferFloatPrice5;
        String optionPriceFloat = offerData.getPriceFloat();
        String optionPriceDecimal = offerData.getPriceDecimal();
        addOption.fillOptionDetail(offerData);

        Reporter.log("Step 7 - Verify that Added option is Present in Edit Offer Section");
        Assert.assertTrue(addOffer.isOptionNamePresent(optionGroup, optionName), "Option Name isn't present");

        Reporter.log("Step 8 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 9 - Verify that created option is present on customer ui.");
        Assert.assertTrue(offerDetails.isOptionPresent(optionGroup, optionName), "Option isn't present");
        Assert.assertEquals(offerDetails.getOptionPrice(optionGroup,optionName), "(+ € " + optionPriceDecimal + ","  + optionPriceFloat +")", "Total price doesn't match");

        Reporter.log("Step 10 - Select the option");
        offerDetails.clickOnOption(optionGroup,optionName);

        Reporter.log("Step 11 - Verify that total price(offer price + option price) is displayed on the 'Add to your order' button.");
        float discountedValue = Float.parseFloat (offerPrice + "." + offerPriceFloat) + (Float.parseFloat(optionPriceDecimal + "." + optionPriceFloat));
        String str_discountedValue = String.format("%.2f",discountedValue).replace(".", ",");
        Assert.assertEquals(footer.getTotalPriceFromAddToYourOrderButton(), "Add to your order ( € "+str_discountedValue +" )", "Total price doesn't match");

        Reporter.log("Clean up - Delete the created option at admin ui.");
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnOption(optionGroup,optionName);
        addOption.clickOnDeleteLink();
    }

    /*Test 4 : Verify that user can add option with 'Any number option' option group at admin ui and not displayed at customer UI [sellable = No]*/
    @Test
    public void verifyAddOptionWithAnyNumberOptionGroupAtAdminUiWithSellableNoAndNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionPO addOption = new AddOptionPO(driver);
        LandingPO landing = new LandingPO(driver);
        OfferDetailsPO offerDetails = new OfferDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit offer Icon'");
        String category = Constants.StoreCategory5;
        String offer = Constants.StoreOffer5;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Add option' link");
        String optionGroup = Constants.StoreOptionGroup15;
        addOffer.clickOnAddOptionLink(optionGroup);

        Reporter.log("Step 6 - Fill the form with 'Any number option' option group and save the option");
        Offer offerData = new OfferData(). getAddOptionData();
        offerData.setSellable(false);
        String optionName = offerData.getName();
        addOption.fillOptionDetail(offerData);

        Reporter.log("Step 7 - Verify that Added option is Present in Edit Offer Section");
        Assert.assertTrue(addOffer.isOptionNamePresent(optionGroup, optionName), "Option Name isn't present");

        Reporter.log("Step 8 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 9 - Verify that created option is not present on customer ui.");
        Assert.assertFalse(offerDetails.isOptionPresent(optionGroup, optionName), "Option is present");

        Reporter.log("Clean up - Delete the created option at admin ui.");
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnOption(optionGroup,optionName);
        addOption.clickOnDeleteLink();
    }

    /*Test 5 : Verify that user can delete option with 'Exactly one option' option group at admin ui and not displayed at customer UI*/
    @Test
    public void verifyDeleteOptionWithExactlyOneOptionGroupAtAdminUiAndNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionPO addOption = new AddOptionPO(driver);
        LandingPO landing = new LandingPO(driver);
        OfferDetailsPO offerDetails = new OfferDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId5 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit offer Icon'");
        String category = Constants.StoreCategory5;
        String offer = Constants.StoreOffer5;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Add option' link");
        String optionGroup = Constants.StoreOptionGroup15;
        addOffer.clickOnAddOptionLink(optionGroup);

        Reporter.log("Step 6 - Fill the form with 'Any number option' option group and save the option");
        Offer offerData = new OfferData(). getAddOptionData();
        offerData.setSellable(false);
        String optionName = offerData.getName();
        addOption.fillOptionDetail(offerData);

        Reporter.log("Step 7 - click on the Added option in Edit Offer Section");
        addOffer.clickOnOption(optionGroup,optionName);

        Reporter.log("Step 8 - Delete the Added option");
        addOption.clickOnDeleteLink();

        Reporter.log("Step 9 - Verify that deleted option is not present in Edit Offer Section.");
        Assert.assertFalse(addOffer.isOptionNamePresent(optionGroup, optionName), "Option Name is present");

        Reporter.log("Step 10 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 11 - Verify that deleted option is not present on customer ui.");
        Assert.assertFalse(offerDetails.isOptionPresent(optionGroup, optionName), "Option is present");

    }

}
