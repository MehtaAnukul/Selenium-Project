package base;



import dataObjectsModel.User;
import dataObjectsModel.Users;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Constants;
import utilities.DriverManager;
import utilities.JavaHelpers;
import utilities.SeleniumHelpers;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;
    protected SeleniumHelpers selenium;
    private DriverManager drivermanager;
    protected User user;

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
    public void beforeSuite() throws InterruptedException, IOException, ParseException {

        //User Setup
        String USERS_JSON ="src/main/resources/users.json";
        user = JavaHelpers.jsonDeserialization(JavaHelpers.jsonToString(USERS_JSON), Users.class).getUsers().stream().filter(x->x.getEnv().equalsIgnoreCase(Constants.ENV)).findFirst().get();

        //Deletes screenshots
        new JavaHelpers().deleteAllFilesFromFolder(Constants.SCREENSHOT_LOCATION);
    }

}
