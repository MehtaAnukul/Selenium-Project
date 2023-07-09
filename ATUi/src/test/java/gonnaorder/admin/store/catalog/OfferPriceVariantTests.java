package gonnaorder.admin.store.catalog;

import base.BaseTest;
import datafactory.gonnaorder.admin.OfferData;
import dataobjects.gonnaorder.admin.Offer;
import enums.gonnaorder.DiscountType;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.catalog.AddOfferPO;
import pageobjects.gonnaorder.admin.store.catalog.AddPriceVariantPO;
import pageobjects.gonnaorder.admin.store.catalog.CatalogPO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.common.FooterPO;
import utilities.Constants;

public class OfferPriceVariantTests extends BaseTest {

    /*Test 1 (70): Verify that user can Add Price variant of Offer at admin ui and View at Customer ui*/
    @Test
    public void verifyAddOfferPriceVariantAtAdminUiAndViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        FooterPO footer = new FooterPO(driver);
        AddPriceVariantPO priceVariant = new AddPriceVariantPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId6 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit Offer Icon'");
        Offer offerData = new OfferData().getOfferData();
        offerData.setSellable(true);
        String category = Constants.StoreCategory6;
        String offer = Constants.StoreOffer6;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Price Variant' link ");
        addOffer.clickOnAddPriceVariantLink();

        Reporter.log("Step 6 - Fill the form of Price variant with NO Discount and click on Save button ");
        Offer data = new OfferData().getOfferPriceVariantData();
        data.setSellable(true);
        priceVariant.fillPriceVariantDetail(data);

        Reporter.log("Step 7 - Verify that Price Variant Present in Edit Offer Section");
        Assert.assertTrue(addOffer.isPriceVariantPresent(data.getPriceDescription()), "Price Variant isn't present");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl6);

        Reporter.log("Step 9 - Verify that Price Variant is displayed At Customer UI which was created in admin UI");
        landing.clickOnCategory(Constants.StoreCategory6);
        Assert.assertTrue(landing.isPriceVariantPresent(offer), "Price Variant isn't present at CustomerUi");

        Reporter.log("Step 10 - Verify that Price Variant displayed with All Valid Details and user can select from 'Add to Your Order Page' ");
        Assert.assertEquals(landing.getPriceVariantValue(offer), data.getPriceDescription() + "\n" + "€ " + data.getPriceDecimal() + "," + data.getPriceFloat(), "Price Variant Name and Price doesn't match");
        landing.selectOffer(offer);
        Assert.assertEquals(landing.getPriceVariantAtItemList(offer), data.getPriceDescription() + "\n" + "("  + "€ " + data.getPriceDecimal() + "," + data.getPriceFloat() + ")", "Price Variant Name and Price doesn't match");
        landing.clickOnPriceVariant(offer);
        Assert.assertEquals(footer.getTotalPriceFromAddToYourOrderButton(), "Add to your order " + "( " + "€ " + data.getPriceDecimal() + "," + data.getPriceFloat() + " )","Price of Offer Price Variant doesn't match 'Add to your Order Button' ");

        Reporter.log("CleanUp of Added Price Variant");
        try {
        selenium.navigateToPage(catalogPageUrl);
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);
        addOffer.clickOnPriceVariantEditLink(data.getPriceDescription());
        priceVariant.clickOnDeleteLink();
        } catch (Exception e) {
            return;
        }
    }


    /*Test 2 (71): Verify that user can Add Price variant with Percentile of Offer at admin ui and View at Customer ui*/
    @Test
    public void verifyAddOfferPriceVariantWithPercentileDiscountAtAdminUiAndViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        FooterPO footer = new FooterPO(driver);
        AddPriceVariantPO priceVariant = new AddPriceVariantPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId6 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit Offer Icon'");
        Offer offerData = new OfferData().getOfferData();
        offerData.setSellable(true);
        String category = Constants.StoreCategory6;
        String offer = Constants.StoreOffer6;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Price Variant' link ");
        addOffer.clickOnAddPriceVariantLink();

        Reporter.log("Step 6 - Fill the form of Price variant with Percentile Discount and click on Save button");
        Offer data = new OfferData().getOfferPriceVariantData();
        data.setSellable(true);
        data.setDiscountType(DiscountType.Percentile);
        priceVariant.fillPriceVariantDetail(data);

        Reporter.log("Step 7 - Verify that Price Variant Present in Edit Offer Section");
        Assert.assertTrue(addOffer.isPriceVariantPresent(data.getPriceDescription()), "Price Variant isn't present");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl6);

        Reporter.log("Step 9 - Verify that Price Variant is displayed At Customer UI which was created in admin UI");
        landing.clickOnCategory(Constants.StoreCategory6);
        Assert.assertTrue(landing.isPriceVariantPresent(offer), "Price Variant isn't present at CustomerUi");

        Reporter.log("Step 10 - Verify that Price Variant displayed with All Valid Details and user can select from 'Add to Your Order Page' ");
        float discountedValue = Float.parseFloat (data.getPriceDecimal() + "." + data.getPriceFloat()) * (1-Float.parseFloat(data.getPercentileDiscountValue().replace("%",""))/100);
        int discountedRoundValue = Math.round(discountedValue);
        Assert.assertEquals(landing.getPriceVariantValue(offer), data.getPriceDescription() + "\n" + "€ " + data.getPriceDecimal() + "," + data.getPriceFloat() + "\n" + "€ " + discountedRoundValue +  ",00" + "\n" + "- " + data.getPercentileDiscountValue(),  "Price Variant Name and Price doesn't match");
        landing.selectOffer(offer);
        Assert.assertEquals(landing.getPriceVariantAtItemList(offer), data.getPriceDescription() + "\n" + "€ " + data.getPriceDecimal() + "," + data.getPriceFloat() + "\n" + "€ " + discountedRoundValue +  ",00" + "\n" + "- " + data.getPercentileDiscountValue(), "Price Variant Name and Price doesn't match at 'Add to yor order' Page");
        landing.clickOnPriceVariant(offer);
        Assert.assertEquals(footer.getTotalPriceFromAddToYourOrderButton(), "Add to your order " + "(" + "\n" + "€ " + data.getPriceDecimal() + "," + data.getPriceFloat()  + "\n" + "€ " + discountedRoundValue +  ",00" + " )" ,"Price of Offer Price Variant doesn't match 'Add to your Order Button' ");

        Reporter.log("CleanUp of Added Price Variant");
        try {
            selenium.navigateToPage(catalogPageUrl);
            catalog.clickOnCategory(category);
            catalog.clickOnEditOfferIcon(category, offer);
            addOffer.clickOnPriceVariantEditLink(data.getPriceDescription());
            priceVariant.clickOnDeleteLink();
        } catch (Exception e) {
            return;
        }
    }


    /*Test 3 (72): Verify that user can Add Price variant with Monetory of Offer at admin ui and View at Customer ui*/
    @Test
    public void verifyAddOfferPriceVariantWithMonetoryDiscountAtAdminUiAndViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        FooterPO footer = new FooterPO(driver);
        AddPriceVariantPO priceVariant = new AddPriceVariantPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId6 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit Offer Icon'");
        Offer offerData = new OfferData().getOfferData();
        offerData.setSellable(true);
        String category = Constants.StoreCategory6;
        String offer = Constants.StoreOffer6;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Price Variant' link ");
        addOffer.clickOnAddPriceVariantLink();

        Reporter.log("Step 6 - Fill the form of Price variant with Monetory Discount and click on Save button");
        Offer data = new OfferData().getOfferPriceVariantData();
        data.setSellable(true);
        data.setDiscountType(DiscountType.Monetory);
        priceVariant.fillPriceVariantDetail(data);

        Reporter.log("Step 7 - Verify that Price Variant Present in Edit Offer Section");
        Assert.assertTrue(addOffer.isPriceVariantPresent(data.getPriceDescription()), "Price Variant isn't present");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl6);

        Reporter.log("Step 9 - Verify that Price Variant is displayed At Customer UI which was created in admin UI");
        landing.clickOnCategory(Constants.StoreCategory6);
        Assert.assertTrue(landing.isPriceVariantPresent(offer), "Price Variant isn't present at CustomerUi");

        Reporter.log("Step 10 - Verify that Price Variant displayed with All Valid Details and user can select from 'Add to Your Order Page' ");
        float discountedValue = Float.parseFloat (data.getPriceDecimal() + "." + data.getPriceFloat()) - (Float.parseFloat(data.getMonitoryDecimalDiscount() + "." + data.getMonitoryFloatDiscount().replace("-", "")));
        int discountedRoundValue = Math.round(discountedValue);
        Assert.assertEquals(landing.getMonetoryDiscountOfferValue(offer), "€ " + discountedRoundValue + ",00", "Monetory price variant details Discount doesn't match");
        landing.selectOffer(offer);
        Assert.assertEquals(landing.getPriceVariantAtItemList(offer), data.getPriceDescription() + "\n" + "€ " + data.getPriceDecimal() + "," + data.getPriceFloat() + "\n" + "€ " + discountedRoundValue + ",00", "Monetory price variant details doesn't match");
        landing.clickOnPriceVariant(offer);
        Assert.assertEquals(footer.getTotalPriceFromAddToYourOrderButton(), "Add to your order " + "(" + "\n" + "€ " + data.getPriceDecimal() + "," + data.getPriceFloat()  + "\n" + "€ " + discountedRoundValue +  ",00" + " )" ,"Price of Offer Price Variant doesn't match 'Add to your Order Button' ");

        Reporter.log("CleanUp of Added Price Variant");
        try {
            selenium.navigateToPage(catalogPageUrl);
            catalog.clickOnCategory(category);
            catalog.clickOnEditOfferIcon(category, offer);
            addOffer.clickOnPriceVariantEditLink(data.getPriceDescription());
            priceVariant.clickOnDeleteLink();
        } catch (Exception e) {
            return;
        }
    }

    /*Test 4 (79): Verify that user can Delete Price variant of Offer at admin ui and not View at Customer ui*/
    @Test
    public void verifyDeleteOfferPriceVariantAtAdminUiAndAndNotViewAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        FooterPO footer = new FooterPO(driver);
        AddPriceVariantPO priceVariant = new AddPriceVariantPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId6 + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit Offer Icon'");
        Offer offerData = new OfferData().getOfferData();
        offerData.setSellable(true);
        String category = Constants.StoreCategory6;
        String offer = Constants.StoreOffer6;
        catalog.clickOnCategory(category);
        catalog.clickOnEditOfferIcon(category, offer);

        Reporter.log("Step 5 - Click on the 'Price Variant' link ");
        addOffer.clickOnAddPriceVariantLink();

        Reporter.log("Step 6 - Fill the form of Price variant with NO Discount and click on Save button ");
        Offer data = new OfferData().getOfferPriceVariantData();
        data.setSellable(true);
        priceVariant.fillPriceVariantDetail(data);

        Reporter.log("Step 7 - Verify that Price Variant Present in Edit Offer Section");
        Assert.assertTrue(addOffer.isPriceVariantPresent(data.getPriceDescription()), "Price Variant isn't present");

        Reporter.log("Step 8 - Click on Price Variant and delete");
        addOffer.clickOnPriceVariantEditLink(data.getPriceDescription());
        priceVariant.clickOnDeleteLink();

        Reporter.log("Step 9 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl6);

        Reporter.log("Step 10 - Verify that Price Variant is not displayed At Customer UI which was created in admin UI");
        landing.clickOnCategory(Constants.StoreCategory6);
        Assert.assertFalse(landing.isPriceVariantPresent(offer,data.getPriceDescription()), "Price Variant is present at CustomerUi");
    }

}
