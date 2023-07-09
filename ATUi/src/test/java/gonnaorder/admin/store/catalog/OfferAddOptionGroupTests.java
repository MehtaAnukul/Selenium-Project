package gonnaorder.admin.store.catalog;

import base.BaseTest;
import datafactory.gonnaorder.admin.OfferData;
import dataobjects.gonnaorder.admin.Offer;
import enums.gonnaorder.OptionGroupType;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.catalog.AddOfferPO;
import pageobjects.gonnaorder.admin.store.catalog.AddOptionGroupPO;
import pageobjects.gonnaorder.admin.store.catalog.CatalogPO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.OfferDetailsPO;
import utilities.Constants;

public class OfferAddOptionGroupTests extends BaseTest {

    /*Test 1 : Verify that user can add offer with 'Exactly one option' option group at admin ui and not displayed at customer UI*/
    @Test
    public void verifyAddOfferWithExactlyOneOptionGroupAtAdminUiNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionGroupPO addOptionGroup = new AddOptionGroupPO(driver);
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

        Reporter.log("Step 5 - Click on the 'Add option group' link");
        addOffer.clickOnAddOptionGroupLink();

        Reporter.log("Step 6 - Fill the form with 'Exactly one option' option group and save the offer");
        Offer offerData = new OfferData().getOfferData();
        String optionGroup = offerData.getOptionGroupName();
        addOptionGroup.fillTheForm(offerData);

        Reporter.log("Step 7 - Verify that created option group is displayed.");
        Assert.assertTrue(addOffer.isOptionGroupPresent(optionGroup), "Option group isn't present");

        Reporter.log("Step 8 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 9 - Verify that created option group is not present on customer ui.");
        Assert.assertFalse(offerDetails.isOptionGroupPresent(optionGroup), "Option group is present");

        Reporter.log("Clean up - Delete the created option group at admin ui.");
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnOptionGroup(optionGroup);
        addOptionGroup.clickOnDeleteLink();
    }

    /*Test 2 : Verify that user can add offer with 'Any Number of options' option group at admin ui and not displayed at customer UI*/
    @Test
    public void verifyAddOfferWithAnyNumberOptionGroupAtAdminUiNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionGroupPO addOptionGroup = new AddOptionGroupPO(driver);
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

        Reporter.log("Step 5 - Click on the 'Add option group' link");
        addOffer.clickOnAddOptionGroupLink();

        Reporter.log("Step 6 - Fill the form with 'Any number of option' option group and save the offer");
        Offer offerData = new OfferData().getOfferData();
        offerData.setOptionGroupType(OptionGroupType.AnyNumber);
        String optionGroup = offerData.getOptionGroupName();
        addOptionGroup.fillTheForm(offerData);

        Reporter.log("Step 7 - Verify that created option group is displayed.");
        Assert.assertTrue(addOffer.isOptionGroupPresent(optionGroup), "Option group isn't present");

        Reporter.log("Step 8 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 9 - Verify that created option group is not present on customer ui.");
        Assert.assertFalse(offerDetails.isOptionGroupPresent(optionGroup), "Option group is present");

        Reporter.log("Clean up - Delete the created option group at admin ui.");
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnOptionGroup(optionGroup);
        addOptionGroup.clickOnDeleteLink();
    }

    /*Test 3 : Verify that user can add offer with 'At Most one' option group at admin ui and not displayed at customer UI*/
    @Test
    public void verifyAddOfferWithAtMostOneOptionGroupAtAdminUiNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionGroupPO addOptionGroup = new AddOptionGroupPO(driver);
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

        Reporter.log("Step 5 - Click on the 'Add option group' link");
        addOffer.clickOnAddOptionGroupLink();

        Reporter.log("Step 6 - Fill the form with 'At Most one' option group and save the offer");
        Offer offerData = new OfferData().getOfferData();
        offerData.setOptionGroupType(OptionGroupType.AtMostOne);
        String optionGroup = offerData.getOptionGroupName();
        addOptionGroup.fillTheForm(offerData);

        Reporter.log("Step 7 - Verify that created option group is displayed.");
        Assert.assertTrue(addOffer.isOptionGroupPresent(optionGroup), "Option group isn't present");

        Reporter.log("Step 8 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 9 - Verify that created option group is not present on customer ui.");
        Assert.assertFalse(offerDetails.isOptionGroupPresent(optionGroup), "Option group is present");

        Reporter.log("Clean up - Delete the created option group at admin ui.");
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnOptionGroup(optionGroup);
        addOptionGroup.clickOnDeleteLink();
    }

    /*Test 4 : Verify that user can edit option group For ExactlyOneToAnyNumber OptionGroupType at admin ui and not displayed at customer UI*/
    @Test
    public void verifyEditOptionGroupForExactlyOneToAnyNumberOptionGroupTypeAtAdminUiNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionGroupPO addOptionGroup = new AddOptionGroupPO(driver);
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

        Reporter.log("Step 5 - Click on the 'Add option group' link");
        addOffer.clickOnAddOptionGroupLink();

        Reporter.log("Step 6 - Fill the form with 'Exactly one option' option group and save the offer");
        Offer offerData = new OfferData().getOfferData();
        String optionGroup = offerData.getOptionGroupName();
        addOptionGroup.fillTheForm(offerData);

        Reporter.log("Step 7 - Verify that created option group is displayed.");
        Assert.assertTrue(addOffer.isOptionGroupPresent(optionGroup), "Option group isn't present");

        Reporter.log("Step 8 - Click on the created option group and then edit the option group data with 'Any Number' option group");
        addOffer.clickOnOptionGroup(optionGroup);
        Offer editOfferData = new OfferData().getOfferEditData();
        editOfferData.setOptionGroupType(OptionGroupType.AnyNumber);
        String editOptionGroup = editOfferData.getOptionGroupName();
        addOptionGroup.fillTheForm(editOfferData);

        Reporter.log("Step 9 - Verify that edited option group is displayed.");
        Assert.assertTrue(addOffer.isOptionGroupPresent(editOptionGroup), "Edited Option group isn't present");

        Reporter.log("Step 10 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 11 - Verify that edited option group is not present on customer ui.");
        Assert.assertFalse(offerDetails.isOptionGroupPresent(optionGroup), "Edited Option group is present");

        Reporter.log("Clean up - Delete the created option group at admin ui.");
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnOptionGroup(editOptionGroup);
        addOptionGroup.clickOnDeleteLink();

    }

    /*Test 5 : Verify that user can delete option group admin ui and not displayed at customer UI*/
    @Test
    public void verifyDeleteOptionGroupAtAdminUiNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        AddOptionGroupPO addOptionGroup = new AddOptionGroupPO(driver);
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

        Reporter.log("Step 5 - Click on the 'Add option group' link");
        addOffer.clickOnAddOptionGroupLink();

        Reporter.log("Step 6 - Fill the form with 'Exactly one option' option group and save the offer");
        Offer offerData = new OfferData().getOfferData();
        String optionGroup = offerData.getOptionGroupName();
        addOptionGroup.fillTheForm(offerData);

        Reporter.log("Step 7 - Verify that created option group is displayed.");
        Assert.assertTrue(addOffer.isOptionGroupPresent(optionGroup), "Option group isn't present");

        Reporter.log("Step 8 - Click on the created option group and then click on the delete link");
        addOffer.clickOnOptionGroup(optionGroup);
        addOptionGroup.clickOnDeleteLink();

        Reporter.log("Step 9 - Verify that deleted option group is not displayed.");
        Assert.assertFalse(addOffer.isOptionGroupPresent(optionGroup), "Option group is present");

        Reporter.log("Step 10 - Navigate to the customer UI and click on the offer");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl5);
        landing.selectOffer(offer);

        Reporter.log("Step 11 - Verify that deleted option group is not present on customer ui.");
        Assert.assertFalse(offerDetails.isOptionGroupPresent(optionGroup), "Deleted Option group is present");

    }


}
