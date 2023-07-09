package gonnaorder.admin.store.catalog;

import base.BaseTest;
import datafactory.gonnaorder.admin.OfferData;
import dataobjects.gonnaorder.admin.Offer;
import enums.gonnaorder.DiscountType;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.common.LeftMenuPO;
import pageobjects.gonnaorder.admin.store.catalog.AddOfferPO;
import pageobjects.gonnaorder.admin.store.catalog.CatalogPO;
import pageobjects.gonnaorder.customer.LandingPO;
import utilities.Constants;

public class OfferTests extends BaseTest
{


    /*Test 1 : Verify that user can add offer and displayed at customer UI successfully*/
    @Test
    public void verifyAddOfferAtAdminUiViewAtCustomerUiSuccessfully() throws InterruptedException
    {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        LandingPO landing = new LandingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 7 - Fill Add offer form and click on Save button");
        Offer offerData = new OfferData().getOfferData();
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 8 - Expand category and verify that offer is present with correct details");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
       //catalog.clickOnCategory(category);
        Assert.assertTrue(catalog.isOfferPresent(category,offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category,offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferPriceValue(category,offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is displayed which was created in admin UI");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertTrue(landing.isOfferPresent(offer), "Offer isn't present on Customer UI");
        Assert.assertEquals(landing.getOfferDescription(offer), offerData.getShortDescription(), "Offer short description doesn't match");
        Assert.assertEquals(landing.getOfferPrice(offer), offerData.getPriceDescription() + " € " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Offer price value doesn't match");
    }

    /*Test 2 : Verify that user can delete offer and not displayed at customer UI */
    @Test
    public void verifyDeleteOfferAtAdminUiNotViewAtCustomerUiSuccessfully () throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 7 - Fill Add offer form and click on Save button");
        Offer offerData = new OfferData().getOfferData();
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 8 - Click on the category and then click on the 'Edit Offer Icon'");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 8 - Click on the 'Delete' link and then click on the 'OK' button ");
        addOffer.clickOnDeleteLink();

        Reporter.log("Step 9 - Verify that offer is NOT displayed which was deleted");
        Assert.assertFalse(landing.isOfferPresent(offer), "offer is present on Admin UI");

        Reporter.log("Step 10 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 11 - Verify that offer is NOT displayed which was deleted in admin UI");
        Assert.assertFalse(landing.isOfferPresent(offer), "offer is present on Customer UI");


    }

    /*Test 3 : Verify that user can add offer with Percentile Discount and displayed at customer UI successfully*/
    @Test
    public void verifyAddOfferAtAdminUiWithPercentileDiscountViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        LandingPO landing = new LandingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 7 - Fill Add offer form and click on Save button");
        Offer offerData = new OfferData().getOfferData();
        offerData.setDiscountType(DiscountType.Percentile);
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 8 - Expand category and verify that offer is present with correct details");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
       // catalog.clickOnCategory(category);
        float discountedValue = Float.parseFloat (offerData.getPriceDecimal() + "." + offerData.getPriceFloat()) * (1-Float.parseFloat(offerData.getPercentileDiscountValue().replace("%",""))/100);
        int discountedRoundValue = Math.round(discountedValue);
        Assert.assertTrue(catalog.isOfferPresent(category, offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category, offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferStrikePriceValue(category, offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(catalog.getPercentileDiscountPrice(category, offer), "€ " + discountedRoundValue + ",00", "Price Discount doesn't match");
        Assert.assertEquals(catalog.getPercentileDiscountValue(category, offer), "- " + offerData.getPercentileDiscountValue() , "Price Discount Value doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is displayed which was created in admin UI with Percentile Discount ");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertTrue(landing.isOfferPresent(offer), "Offer isn't present on Customer UI");
        Assert.assertEquals(landing.getOfferDescription(offer), offerData.getShortDescription(), "Offer short description doesn't match");
        Assert.assertEquals(landing.getStrikeOfferPrice(offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(landing.getPercentileDiscountOfferPrice(offer), "€ " + discountedRoundValue + ",00", "Price Discount doesn't match");
        Assert.assertEquals(landing.getPercentileDiscountOfferValue(offer), "- " + offerData.getPercentileDiscountValue() , "Price Discount Value doesn't match");
    }

    /*Test 4 : Verify that user can add offer with Monetory Discount and displayed at customer UI successfully*/
    @Test
    public void verifyAddOfferAtAdminUiWithMonetoryDiscountViewAtCustomerUiSuccessfully () throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 7 - Fill Add offer form and click on Save button");
        Offer offerData = new OfferData().getOfferData();
        offerData.setDiscountType(DiscountType.Monetory);
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 8 - Expand category and verify that offer is present with correct details");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
        catalog.clickOnCategory(category);
        float discountedValue = Float.parseFloat (offerData.getPriceDecimal() + "." + offerData.getPriceFloat()) - (Float.parseFloat(offerData.getMonitoryDecimalDiscount() + "." + offerData.getMonitoryFloatDiscount().replace("-", "")));
        int discountedRoundValue = Math.round(discountedValue);
        Assert.assertTrue(catalog.isOfferPresent(category, offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category, offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferStrikePriceValue(category, offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(catalog.getMonetoryDiscountValue(category, offer), "€ " + discountedRoundValue + ",00", "Price Discount doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is displayed which was created in admin UI with Monetory Discount ");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertTrue(landing.isOfferPresent(offer), "Offer isn't present on Customer UI");
        Assert.assertEquals(landing.getOfferDescription(offer), offerData.getShortDescription(), "Offer short description doesn't match");
        Assert.assertEquals(landing.getStrikeOfferPrice(offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(landing.getMonetoryDiscountOfferValue(offer), "€ " + discountedRoundValue + ",00", "Price Monetory Discount doesn't match");
    }

    /*Test 5 : Verify that user can add offer with Monetory Discount and Sellable NO is Not displayed at customer UI successfully*/

    @Test
    public void verifyAddOfferAtAdminUiWithSellableNoNotViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 5 - Fill Add Offer form with Sellable=No and then click on the Save button");
        Offer offerData = new OfferData().getOfferData();
        offerData.setSellable(false);
        offerData.setDiscountType(DiscountType.Monetory);
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 6 - Expand category and verify that offer is present with correct details");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
        catalog.clickOnCategory(category);
        float discountedValue = Float.parseFloat (offerData.getPriceDecimal() + "." + offerData.getPriceFloat()) - (Float.parseFloat(offerData.getMonitoryDecimalDiscount() + "." + offerData.getMonitoryFloatDiscount().replace("-", "")));
        int discountedRoundValue = Math.round(discountedValue);
        Assert.assertTrue(catalog.isOfferPresent(category, offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category, offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferStrikePriceValue(category, offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(catalog.getMonetoryDiscountValue(category, offer), "€ " + discountedRoundValue + ",00", "Price Discount doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is displayed which was created in admin UI with  Discount ");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertFalse(landing.isOfferPresent(offer), "Offer is present on Customer UI");

    }

    /*Test 6 : Verify that user can Edit offer of Name Description and Price with Sellable Yes is displayed at customer UI successfully*/

    @Test
    public void verifyEditOfferWithNameDescriptionPriceAtAdminUiWithSellableYesViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 5 - Fill Add Offer form with Sellable=No and then click on the Save button");
        Offer offerData = new OfferData().getOfferData();
        offerData.setSellable(true);
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 6 - Click on the category and then click on the 'Edit offer Icon'");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
        catalog.clickOnCategory(category);
        Assert.assertTrue(catalog.isOfferPresent(category,offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category,offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferPriceValue(category,offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        catalog.clickOnEditOfferIcon(category,offer);

        Reporter.log("Step 7 - Fill Edit Offer form of Name/Description/Price with Sellable=yes and then click on the Save button");
        Offer editOfferData = new OfferData().getOfferEditData();
        editOfferData.setSellable(true);
        addOffer.fillOfferDetail(editOfferData);
        String editOffer = editOfferData.getName();
        catalog.clickOnCategory(category);
        Assert.assertFalse(catalog.isOfferPresent(category,offer), "Offer is present : " + offer);
        Assert.assertTrue(catalog.isOfferPresent(category,editOffer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category,editOffer), editOfferData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferPriceValue(category,editOffer), "€ " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Price Value doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is displayed which was Edited in admin UI");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertFalse(landing.isOfferPresent(offer), "Offer is present on Customer UI");
        Assert.assertTrue(landing.isOfferPresent(editOffer), "Offer isn't present on Customer UI");
        Assert.assertEquals(landing.getOfferDescription(editOffer), editOfferData.getShortDescription(), "Offer short description doesn't match");
        Assert.assertEquals(landing.getOfferPrice(editOffer), editOfferData.getPriceDescription() + " € " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Offer price value doesn't match");

    }


    /*Test 7 : Verify that user can Edit offer form Percentile to Monetory Discount Type and  Sellable Yes is displayed at customer UI successfully*/

    @Test
    public void verifyEditOfferForPercentileToMonetoryDiscountTypeWithSellableYesViewAtCustomerUiSuccessfully()throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        LandingPO landing = new LandingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 5 - Fill Add offer form with Percentile Discount and click on Save button");
        Offer offerData = new OfferData().getOfferData();
        offerData.setDiscountType(DiscountType.Percentile);
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 6 - Expand category and verify that offer is present with correct details and click on Edit Offer Icon");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
        catalog.clickOnCategory(category);
        float discountedValue = Float.parseFloat (offerData.getPriceDecimal() + "." + offerData.getPriceFloat()) * (1-Float.parseFloat(offerData.getPercentileDiscountValue().replace("%",""))/100);
        int discountedRoundValue = Math.round(discountedValue);
        Assert.assertTrue(catalog.isOfferPresent(category, offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category, offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferStrikePriceValue(category, offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(catalog.getPercentileDiscountPrice(category, offer), "€ " + discountedRoundValue + ",00", "Price Discount doesn't match");
        Assert.assertEquals(catalog.getPercentileDiscountValue(category, offer), "- " + offerData.getPercentileDiscountValue() , "Price Discount Value doesn't match");
        catalog.clickOnEditOfferIcon(category,offer);

        Reporter.log("Step 7 - Fill Edit Offer form for Percentile to Monetory Discount type with Sellable=yes and then click on the Save button");
        Offer editOfferData = new OfferData().getOfferEditData();
        editOfferData.setSellable(true);
        editOfferData.setDiscountType(DiscountType.Monetory);
        addOffer.fillOfferDetail(editOfferData);
        String editOffer = editOfferData.getName();
        catalog.clickOnCategory(category);
        float editedDiscountedValue = Float.parseFloat (editOfferData.getPriceDecimal() + "." + editOfferData.getPriceFloat()) - (Float.parseFloat(editOfferData.getMonitoryDecimalDiscount() + "." + editOfferData.getMonitoryFloatDiscount().replace("-", "")));
        int editedDiscountedRoundValue = Math.round(editedDiscountedValue);
        Assert.assertFalse(catalog.isOfferPresent(category,offer), "Offer is present : " + offer);
        Assert.assertTrue(catalog.isOfferPresent(category, editOffer), "Offer isn't present : " + editOffer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category, editOffer), editOfferData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferStrikePriceValue(category, editOffer), "€ " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(catalog.getMonetoryDiscountValue(category, editOffer), "€ " + editedDiscountedRoundValue + ",00", "Price Discount doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is displayed which was edited in admin UI with Monetory Discount ");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertFalse(landing.isOfferPresent(offer), "Offer is present on Customer UI");
        Assert.assertTrue(landing.isOfferPresent(editOffer), "Offer isn't present on Customer UI");
        Assert.assertEquals(landing.getOfferDescription(editOffer), editOfferData.getShortDescription(), "Offer short description doesn't match");
        Assert.assertEquals(landing.getStrikeOfferPrice(editOffer), "€ " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(landing.getMonetoryDiscountOfferValue(editOffer), "€ " + editedDiscountedRoundValue + ",00", "Price Monetory Discount doesn't match");

    }

    /*Test 8 : Verify that user can Edit offer form with Monetory to Percentile Discount Type and  Sellable Yes is displayed at customer UI successfully*/

    @Test
    public void verifyEditOfferForMonetoryToPercentileDiscountTypeWithSellableYesViewAtCustomerUiSuccessfully()throws InterruptedException {

        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        LandingPO landing = new LandingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 5 - Fill Add offer form with Monetory Discount and click on Save button");
        Offer offerData = new OfferData().getOfferData();
        offerData.setDiscountType(DiscountType.Monetory);
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 6 - Expand category and verify that offer is present with correct details and click on Edit Offer icon");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
        catalog.clickOnCategory(category);
        float discountedValue = Float.parseFloat (offerData.getPriceDecimal() + "." + offerData.getPriceFloat()) - (Float.parseFloat(offerData.getMonitoryDecimalDiscount() + "." + offerData.getMonitoryFloatDiscount().replace("-", "")));
        int discountedRoundValue = Math.round(discountedValue);
        Assert.assertTrue(catalog.isOfferPresent(category, offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category, offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferStrikePriceValue(category, offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(catalog.getMonetoryDiscountValue(category, offer), "€ " + discountedRoundValue + ",00", "Price Discount doesn't match");
        catalog.clickOnEditOfferIcon(category,offer);

        Reporter.log("Step 7 - Fill Edit Offer form for Percentile to Monetory Discount type with Sellable=yes and then click on the Save button");
        Offer editOfferData = new OfferData().getOfferEditData();
        editOfferData.setSellable(true);
        editOfferData.setDiscountType(DiscountType.Percentile);
        addOffer.fillOfferDetail(editOfferData);
        String editOffer = editOfferData.getName();
        catalog.clickOnCategory(category);
        float editedDiscountedValue = Float.parseFloat (editOfferData.getPriceDecimal() + "." + editOfferData.getPriceFloat()) * (1-Float.parseFloat(editOfferData.getPercentileDiscountValue().replace("%",""))/100);
        int editedDiscountedRoundValue = Math.round(editedDiscountedValue);
        Assert.assertFalse(catalog.isOfferPresent(category,offer), "Offer is present : " + offer);
        Assert.assertTrue(catalog.isOfferPresent(category, editOffer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category, editOffer), editOfferData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferStrikePriceValue(category, editOffer), "€ " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(catalog.getPercentileDiscountPrice(category, editOffer), "€ " + editedDiscountedRoundValue + ",00", "Price Discount doesn't match");
        Assert.assertEquals(catalog.getPercentileDiscountValue(category, editOffer), "- " + editOfferData.getPercentileDiscountValue() , "Price Discount Value doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is displayed which was edited in admin UI with Percentile Discount ");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertFalse(landing.isOfferPresent(offer), "Offer is present on Customer UI");
        Assert.assertTrue(landing.isOfferPresent(editOffer), "Offer isn't present on Customer UI");
        Assert.assertEquals(landing.getOfferDescription(editOffer), editOfferData.getShortDescription(), "Offer short description doesn't match");
        Assert.assertEquals(landing.getStrikeOfferPrice(editOffer), "€ " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(landing.getPercentileDiscountOfferPrice(editOffer), "€ " + editedDiscountedRoundValue + ",00", "Price Discount doesn't match");
        Assert.assertEquals(landing.getPercentileDiscountOfferValue(editOffer), "- " + editOfferData.getPercentileDiscountValue() , "Price Discount Value doesn't match");
    }

    /*Test 9 : Verify that user can Edit offer form with Percentile to No Discount Type and Sellable Yes is displayed at customer UI successfully*/

    @Test
    public void verifyEditOfferForPercentiletoNoDiscountTypeWithSellableYesViewAtCustomerUiSuccessfully()throws InterruptedException {

        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        LandingPO landing = new LandingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 5 - Fill Add offer form with Percentile Discount and click on Save button");
        Offer offerData = new OfferData().getOfferData();
        offerData.setDiscountType(DiscountType.Percentile);
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 6 - Expand category and verify that offer is present with correct details and click on Edit Offer Icon");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
        catalog.clickOnCategory(category);
        float discountedValue = Float.parseFloat (offerData.getPriceDecimal() + "." + offerData.getPriceFloat()) * (1-Float.parseFloat(offerData.getPercentileDiscountValue().replace("%",""))/100);
        int discountedRoundValue = Math.round(discountedValue);
        Assert.assertTrue(catalog.isOfferPresent(category, offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category, offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferStrikePriceValue(category, offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        Assert.assertEquals(catalog.getPercentileDiscountPrice(category, offer), "€ " + discountedRoundValue + ",00", "Price Discount doesn't match");
        Assert.assertEquals(catalog.getPercentileDiscountValue(category, offer), "- " + offerData.getPercentileDiscountValue() , "Price Discount Value doesn't match");
        catalog.clickOnEditOfferIcon(category,offer);

        Reporter.log("Step 7 - Fill Edit Offer form with No Discount type and then click on the Save button");
        Offer editOfferData = new OfferData().getOfferEditData();
        editOfferData.setSellable(true);
        editOfferData.setDiscountType(DiscountType.None);
        addOffer.fillOfferDetail(editOfferData);
        String editOffer = editOfferData.getName();
        catalog.clickOnCategory(category);
        Assert.assertFalse(catalog.isOfferPresent(category,offer), "Offer is present : " + offer);
        Assert.assertTrue(catalog.isOfferPresent(category,editOffer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category,editOffer), editOfferData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferPriceValue(category,editOffer), "€ " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Price Value doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is present at Customer UI  which was Edited in admin UI");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertFalse(landing.isOfferPresent(offer), "Offer is present on Customer UI");
        Assert.assertTrue(landing.isOfferPresent(editOffer), "Offer isn't present on Customer UI");
        Assert.assertEquals(landing.getOfferDescription(editOffer), editOfferData.getShortDescription(), "Offer short description doesn't match");
        Assert.assertEquals(landing.getOfferPrice(editOffer), editOfferData.getPriceDescription()+ " € " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Offer price value doesn't match");

    }

    /*Test 10 : Verify that user can Edit offer form Sellable=No is Not displayed at customer UI successfully*/

    @Test
    public void verifyEditOfferAtAdminUiWithSellableNoNotViewAtCustomerUiSuccessfully()throws InterruptedException
    {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on 'Add Offer' button");
        catalog.clickOnAddOfferButton();

        Reporter.log("Step 5 - Fill Add Offer form with Sellable=Yes and then click on the Save button");
        Offer offerData = new OfferData().getOfferData();
        offerData.setSellable(true);
        addOffer.fillOfferDetail(offerData);

        Reporter.log("Step 6 - Click on the category and then click on the 'Edit offer Icon'");
        String category = Constants.StoreCategory;
        String offer = offerData.getName();
        catalog.clickOnCategory(category);
        Assert.assertTrue(catalog.isOfferPresent(category,offer), "Offer isn't present : " + offer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category,offer), offerData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferPriceValue(category,offer), "€ " + offerData.getPriceDecimal() + "," + offerData.getPriceFloat(), "Price Value doesn't match");
        catalog.clickOnEditOfferIcon(category,offer);

        Reporter.log("Step 7 - Fill Edit Offer form of Name/Description/Price with Sellable=No and then click on the Save button");
        Offer editOfferData = new OfferData().getOfferEditData();
        editOfferData.setSellable(false);
        addOffer.fillOfferDetail(editOfferData);
        String editOffer = editOfferData.getName();
        catalog.clickOnCategory(category);
        Assert.assertFalse(catalog.isOfferPresent(category,offer), "Offer is present : " + offer);
        Assert.assertTrue(catalog.isOfferPresent(category, editOffer), "Offer isn't present : " + editOffer);
        Assert.assertEquals(catalog.getOfferPriceDescription(category,editOffer), editOfferData.getPriceDescription(), "Price Description doesn't match");
        Assert.assertEquals(catalog.getOfferPriceValue(category,editOffer), "€ " + editOfferData.getPriceDecimal() + "," + editOfferData.getPriceFloat(), "Price Value doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Verify that offer is not displayed which was Edited in admin UI");
        landing.clickOnCategory(Constants.StoreCategory);
        Assert.assertFalse(landing.isOfferPresent(offer), "Offer is present on Customer UI");
        Assert.assertFalse(landing.isOfferPresent(editOffer), "Edited Offer is present on Customer UI");
    }


}