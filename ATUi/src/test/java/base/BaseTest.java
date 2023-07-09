package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Constants;
import utilities.DriverManager;
import utilities.JavaHelpers;
import utilities.SeleniumHelpers;


public class BaseTest {

    protected WebDriver driver;
    protected SeleniumHelpers selenium;
    private DriverManager drivermanager;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) {
        drivermanager = new DriverManager();
        driver = drivermanager.setUp(browser);
        selenium = new SeleniumHelpers(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        try
        {
            //capturing screenshot if failed
            if (ITestResult.FAILURE == result.getStatus()) {
                selenium.takeScreenshot(result.getName());
            }
        }
        catch (Exception e)
        {
            //ignore
        }

        drivermanager.tearDown();
    }

    @BeforeSuite
    public void cleanUpData() throws InterruptedException {

        //Deletes screenshots
        JavaHelpers java = new JavaHelpers();
        java.deleteAllFilesFromFolder(Constants.SCREENSHOT_LOCATION);

        //Clean up GonnaOrder admin data
       // CleanUpData cleanUp = new CleanUpData();
        //cleanUp.cleanUpCategoryData();
        //cleanUp.cleanUpOfferData();
    }

}
