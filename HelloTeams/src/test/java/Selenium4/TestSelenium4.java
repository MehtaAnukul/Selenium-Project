package Selenium4;

import base.BaseTest;
import org.openqa.selenium.WindowType;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.helloteams.account.TestPO;
import java.io.IOException;



public class TestSelenium4 extends BaseTest
{
    @Test
    public void verifyNewFeatureWork() throws IOException, InterruptedException {


        Reporter.log("New Window");
        driver.get("https://www.google.com/");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://www.crmpro.com/");
        selenium.hardWait(3);

        Reporter.log("New Tab");
        driver.get("https://www.google.com/");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.crmpro.com/");
        selenium.hardWait(3);

        TestPO test = new TestPO(driver);
        Reporter.log("Take a screen shot using webElement and print size");
        test.takingScreenShotOFWebElement();

        Reporter.log("take a scree shot using section");
        test.takingScreenShotOfSection();

        Reporter.log("Relative Locators");
        driver.get("https://www.google.com/");
        driver.get("https://accounts.lambdatest.com/login");
        //test.clickOnSignInButtonOfGoogle();
        test.fillFormOfLogin();     // null value get

    }

}
