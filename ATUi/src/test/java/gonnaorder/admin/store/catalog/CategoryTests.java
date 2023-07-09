package gonnaorder.admin.store.catalog;

import base.BaseTest;
import datafactory.gonnaorder.admin.CategoryData;
import datafactory.gonnaorder.admin.TranslationData;
import dataobjects.gonnaorder.admin.Category;
import dataobjects.gonnaorder.admin.Translation;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.common.LeftMenuPO;
import pageobjects.gonnaorder.admin.store.catalog.CatalogPO;
import pageobjects.gonnaorder.admin.store.catalog.CategoryCreationPO;
import pageobjects.gonnaorder.admin.store.catalog.TranslationPO;
import pageobjects.gonnaorder.customer.LandingPO;
import utilities.Constants;


public class CategoryTests extends BaseTest {


    /*Test 1 : Verify that user can Create Category and displayed at Customer UI [sellable = Yes]*/
    @Test
    public void verifyAddCategoryAtAdminUiWithSellableYesViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        CategoryCreationPO categoryCreation = new CategoryCreationPO(driver);
        LandingPO landing = new LandingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

        Reporter.log("Step 4 - Click on the 'Add Category' button");
        catalog.clickOnAddCategoryButton();

        Reporter.log("Step 5 - Fill Create Category form and then click on the Save button");
        Category data = new CategoryData().getCategoryData();
        data.setSellable(true);
        categoryCreation.fillCreateCategoryFormAndClickOnSaveButton(data);

        Reporter.log("Step 6 - Verify that category is present with correct name");
        Assert.assertTrue(catalog.isCategoryPresent(data.getName()), "Category isn't present");

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 8 - Verify that category is displayed which was created in admin UI");
        Assert.assertTrue(landing.isCategoryPresent(data.getName()), "Category isn't present on Customer UI");

    }

    /*Test 2 : Verify that user can Create Category and not displayed at Customer UI [sellable = No]*/
    @Test
    public void verifyAddCategoryAtAdminUiWithSellableNoNotViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        CategoryCreationPO categoryCreation = new CategoryCreationPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

        Reporter.log("Step 4 - Click on the 'Add Category' button");
        catalog.clickOnAddCategoryButton();

        Reporter.log("Step 5 - Fill Create Category form with Sellable=No and then click on the Save button");
        Category data = new CategoryData().getCategoryData();
        data.setSellable(false);
        categoryCreation.fillCreateCategoryFormAndClickOnSaveButton(data);

        Reporter.log("Step 6 - Verify that category is present with correct name");
        Assert.assertTrue(catalog.isCategoryPresent(data.getName()), "Category isn't present");

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 8 - Verify that category is NOT displayed which was created in admin UI");
        Assert.assertFalse(landing.isCategoryPresent(data.getName()), "Category is present on Customer UI");

    }

    /*Test 3 : Verify that user can Delete Category and not displayed at Customer UI */
    @Test
    public void verifyDeleteCategoryAtAdminUiNotViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        CategoryCreationPO categoryCreation = new CategoryCreationPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

        Reporter.log("Step 4 - Click on the 'Add Category' button");
        catalog.clickOnAddCategoryButton();

        Reporter.log("Step 5 - Fill Create Category form and then click on the Save button");
        Category data = new CategoryData().getCategoryData();
        categoryCreation.fillCreateCategoryFormAndClickOnSaveButton(data);
        String categoryName = data.getName();

        Reporter.log("Step 6 - Verify that category is present with correct name");
        Assert.assertTrue(catalog.isCategoryPresent(categoryName), "Category isn't present");

        Reporter.log("Step 7 - Click on the category and then click on the 'Edit Category Icon'");
        catalog.clickOnCategory(categoryName);
        catalog.clickOnEditCategoryIcon(categoryName);

        Reporter.log("Step 8 - Click on the 'Delete' link and then click on the 'OK' button ");
        categoryCreation.clickOnDeleteLink();

        Reporter.log("Step 9 - Verify that category is NOT displayed which was deleted");
        Assert.assertFalse(landing.isCategoryPresent(categoryName), "Category isn't present ");

        Reporter.log("Step 10 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 11 - Verify that category is NOT displayed which was deleted in admin UI");
        Assert.assertFalse(landing.isCategoryPresent(categoryName), "Category is present on Customer UI");
    }

    /*Test 4 : Verify that user can Edit Category and displayed at Customer UI [sellable = Yes]*/
    @Test
    public void verifyEditCategoryAtAdminUiWithSellableYesViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        LeftMenuPO leftMenu = new LeftMenuPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        CategoryCreationPO categoryCreation = new CategoryCreationPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(catalogPageUrl);

        Reporter.log("Step 4 - Click on the 'Add Category' button");
        catalog.clickOnAddCategoryButton();

        Reporter.log("Step 5 - Fill Create Category form and then click on the Save button");
        Category data = new CategoryData().getCategoryData();
        data.setSellable(false);
        categoryCreation.fillCreateCategoryFormAndClickOnSaveButton(data);
        String categoryName = data.getName();

        Reporter.log("Step 6 - Verify that category is present with correct name");
        Assert.assertTrue(catalog.isCategoryPresent(data.getName()), "Category isn't present");

        Reporter.log("Step 7 - Click on the category and then click on the 'Edit Category Icon'");
        catalog.clickOnCategory(categoryName);
        catalog.clickOnEditCategoryIcon(categoryName);

        Reporter.log("Step 8 - Edit Category data");
        Category editData = new CategoryData().getCategoryEditData();
        editData.setSellable(true);
        categoryCreation.fillCreateCategoryFormAndClickOnSaveButton(editData);
        Assert.assertTrue(catalog.isCategoryPresent(editData.getName()), "Category isn't present");

        Reporter.log("Step 9 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 10 - Verify that category is displayed which was created in admin UI");
        Assert.assertTrue(landing.isCategoryPresent(editData.getName()), editData.getName() + " - Category isn't present on Customer UI");

    }

    /*Test 5 : Verify that user can Edit Category and not displayed at Customer UI [sellable = NO]*/
    @Test
    public void verifyEditCategoryAtAdminUiWithSellableNoViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        CategoryCreationPO categoryCreation = new CategoryCreationPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

        Reporter.log("Step 4 - Click on the 'Add Category' button");
        catalog.clickOnAddCategoryButton();

        Reporter.log("Step 5 - Fill Create Category form and then click on the Save button");
        Category data = new CategoryData().getCategoryData();
        data.setSellable(true);
        categoryCreation.fillCreateCategoryFormAndClickOnSaveButton(data);
        String categoryName = data.getName();

        Reporter.log("Step 6 - Verify that category is present with correct name");
        Assert.assertTrue(catalog.isCategoryPresent(data.getName()), "Category isn't present");

        Reporter.log("Step 7 - Click on the category and then click on the 'Edit Category Icon'");
        catalog.clickOnCategory(categoryName);
        catalog.clickOnEditCategoryIcon(categoryName);

        Reporter.log("Step 8 - Edit Category data");
        Category editData = new CategoryData().getCategoryEditData();
        editData.setSellable(false);
        categoryCreation.fillCreateCategoryFormAndClickOnSaveButton(editData);
        Assert.assertTrue(catalog.isCategoryPresent(editData.getName()), "Category isn't present");

        Reporter.log("Step 9 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 10 - Verify that category is NOT displayed which was created in admin UI");
        Assert.assertFalse(landing.isCategoryPresent(editData.getName()), "Category is present on Customer UI");
    }

    /*Test 6 : Verify that user can Add/Edit/Delete language translation at admin ui and added language displayed at Customer ui*/
    @Test
    public void verifyAddEditDeleteLanguageTranslationAtAdminUiAndViewAtCustomerUiSuccessfully() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        LandingPO landing = new LandingPO(driver);
        TranslationPO translation = new TranslationPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Catalog page");
        String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
        selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

        Reporter.log("Step 4 - Click on the category and then click on the 'Edit Category Icon'");
        String category = Constants.StoreCategory;
        catalog.clickOnCategory(category);
        catalog.clickOnEditCategoryIcon(category);

        Reporter.log("Step 5 - Click on the 'Add New Translation' link ");
        translation.clickOnAddNewTranslationLink();

        Reporter.log("Step 6 - Fill the form of 'Add New Translation' and then click on the 'Save' button");
        Translation data = new TranslationData().getTranslationDetailsData();
        translation.fillTranslationFormAndClickOnSaveButton(data);

        Reporter.log("Step 7 - Verify that added language translation is present with correct language name");
        Assert.assertEquals(translation.getTableData(1,1), data.getLanguage(),"Language name doesn't match");
        Assert.assertEquals(translation.getTableData(1,2), data.getName(), "Category name doesn't match");
        Assert.assertEquals(translation.getTableData(1,3), data.getShortDescription(),"Short description doesn't match");

        Reporter.log("Step 8 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 9 - Click on the flag icon on header");
        landing.clickOnFlagIcon();

        Reporter.log("Step 10 - Verify that language is present which was added in admin ui");
        Assert.assertTrue(landing.isLanguageNamePresent(data.getLanguage()), "Language name is not present");

        Reporter.log("Step 11 - Click on the language name and click on the Ok");
        landing.selectLanguageName(data.getLanguage());

        Reporter.log("Step 12 - Verify that Category displayed with translated name");
        Assert.assertTrue(landing.isCategoryPresent(data.getName()), "Category isn't present on Customer UI");

        Reporter.log("Step 13 - Navigate to Store Catalog page");
        selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

        Reporter.log("Step 14 - Click on the category and then click on the 'Edit Category Icon'");
        catalog.clickOnCategory(category);
        catalog.clickOnEditCategoryIcon(category);

        Reporter.log("Step 15 - Expand to assigned translation and then click on the edit icon ");
        translation.expandTranslationsSection();

        Reporter.log("Step 16 - Click on the edit icon");
        translation.clickOnEditIcon();

        Reporter.log("Step 17 - Edit Translation data and then verify the edited data");
        Translation editData = new TranslationData().getTranslationEditData();
        editData.setLanguage("");
        translation.fillTranslationFormAndClickOnSaveButton(editData);
        Assert.assertEquals(translation.getTableData(1,2), editData.getName(),"Category name doesn't match");
        Assert.assertEquals(translation.getTableData(1,3), editData.getShortDescription(),"Short description doesn't match");

        Reporter.log("Step 18 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 19 - Click on the flag icon on header");
        landing.clickOnFlagIcon();

        Reporter.log("Step 20 - Verify that language is present which was added in admin ui");
        Assert.assertTrue(landing.isLanguageNamePresent(data.getLanguage()), "Language name is not present");

        Reporter.log("Step 21 - Click on the language name and click on the Ok");
        landing.selectLanguageName(data.getLanguage());

        Reporter.log("Step 22 - Verify that Translation Name is displayed which was edited in admin UI");
        Assert.assertTrue(landing.isCategoryPresent(editData.getName()), "Translation isn't present on Customer UI");

        Reporter.log("Step 23 - Navigate to Store Catalog page");
        selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

        Reporter.log("Step 24 - Click on the category and then click on the 'Edit Category Icon'");
        catalog.clickOnCategory(category);
        catalog.clickOnEditCategoryIcon(category);

        Reporter.log("Step 25 - Click on the edit icon");
        translation.expandTranslationsSection();

        Reporter.log("Step 26 - Click on the Edit icon");
        translation.clickOnEditIcon();

        Reporter.log("Step 27 - Click on the Delete link");
        translation.clickOnDeleteLink();

        Reporter.log("Step 28 - Verify that deleted language translation is not present");
        Assert.assertTrue(translation.getTotalRows()==0,"Translation is still present");

        Reporter.log("Step 29 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 30 - Verify that language is not present which was deleted in admin ui");
        Assert.assertFalse(landing.isLanguageNamePresent(data.getLanguage()), "Language name is present");
    }

}


