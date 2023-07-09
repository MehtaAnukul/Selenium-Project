package cleanup.gonnaorder.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.catalog.AddOfferPO;
import pageobjects.gonnaorder.admin.store.catalog.CatalogPO;
import pageobjects.gonnaorder.admin.store.catalog.CategoryCreationPO;
import utilities.Constants;
import utilities.DriverManager;
import utilities.SeleniumHelpers;

import java.util.List;

public class CleanUpData {

    public void cleanUpCategoryData()  {
        DriverManager drivermanager = new DriverManager();
        WebDriver driver = drivermanager.setUp("chrome-headless");
        SeleniumHelpers selenium = new SeleniumHelpers(driver);
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        CategoryCreationPO categoryCreation = new CategoryCreationPO(driver);
        try
        {
            Reporter.log("Step 1 - Navigate to admin login page");
            selenium.navigateToPage(Constants.ADMIN_URL);

            Reporter.log("Step 2 - Entering valid sign in details and login to application");
            login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

            Reporter.log("Step 3 - Navigate to Store Catalog page");
            String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
            selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

            String categoryName= "Category";
            Reporter.log("Step 4 - Remove all categories with starts with name = " + categoryName);
            List<String> allCategories = catalog.getAllCategoryNames();
            for (String category:allCategories)
            {
                if(category.startsWith(categoryName) && !category.equalsIgnoreCase(Constants.StoreCategory))
                {
                    catalog.clickOnCategory(category);
                    catalog.clickOnEditCategoryIcon(category);
                    categoryCreation.clickOnDeleteLink();
                }
            }
        }
        catch (Exception e)
        {
            //ignore
        }
        finally {
            drivermanager.tearDown();
        }

    }

    public void cleanUpOfferData() {
        DriverManager drivermanager = new DriverManager();
        WebDriver driver = drivermanager.setUp("chrome-headless");
        SeleniumHelpers selenium = new SeleniumHelpers(driver);
        LoginPO login = new LoginPO(driver);
        CatalogPO catalog = new CatalogPO(driver);
        AddOfferPO addOffer = new AddOfferPO(driver);
        try
        {
            Reporter.log("Step 1 - Navigate to admin login page");
            selenium.navigateToPage(Constants.ADMIN_URL);

            Reporter.log("Step 2 - Entering valid sign in details and login to application");
            login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

            Reporter.log("Step 3 - Navigate to Store Catalog page");
            String catalogPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog";
            selenium.navigateToPage(Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/catalog");

            String offerName= "Automation";
            Reporter.log("Step 4 - Remove all Offers under " + Constants.StoreCategory +  " starts with name = " + offerName);
            String category = Constants.StoreCategory;

            catalog.clickOnCategory(category);
            List<String> allOffers = catalog.getAllOfferNamesByCategory(category);

            for (String offer:allOffers)
            {
                if(offer.startsWith(offerName) && !offer.equalsIgnoreCase(Constants.StoreOffer))
                {
                    catalog.clickOnEditOfferIcon(category, offer);
                    addOffer.clickOnDeleteLink();
                    catalog.clickOnCategory(category);
                }
            }
        }
        catch (Exception e)
        {
            //ignore
        }
        finally {
            drivermanager.tearDown();
        }

    }
}
