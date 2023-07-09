package pageobjects.helloteams.account;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.locators.RelativeLocator;
import pageobjects.base.BasePO;
import utilities.Constants;

import java.io.File;
import java.io.IOException;


public class TestPO extends BasePO {
    public TestPO(WebDriver driver) {
        super(driver);
    }

@FindBy(id = "gb_70")
private WebElement signInButtonInGoogle;


    public void clickOnSignInButtonOfGoogle() throws InterruptedException {
        selenium.clickOn(signInButtonInGoogle);
    }
    public void fillFormOfLogin() throws InterruptedException {
        WebElement emailId = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(RelativeLocator.withTagName("input").below(emailId));
        selenium.enterText(password,"1234",false);
       /* WebElement emailId = driver.findElement(By.id("identifierId"));
        WebElement forgetMail = driver.findElement(RelativeLocator.withTagName("button").below(emailId));
        selenium.clickOn(forgetMail);*/

    }

    public void takingScreenShotOFWebElement() throws IOException, InterruptedException {

        WebElement logo=driver.findElement(By.xpath("//img[@src='https://classic.freecrm.com/img/logo.png']"));
                 File file=logo.getScreenshotAs(OutputType.FILE);
                 selenium.hardWait(3);
        FileUtils.copyFile(file,new File(Constants.SCREENSHOT_LOCATION+"\\"+"logo.png"));      //folder create thase. In selenium helper just need to pass webElement and fileName as parameter

        System.out.println("get react method for finding object location");     // why we need to find object location using react
        System.out.println("Height:" +logo.getRect().getDimension().getHeight());
        System.out.println("Height:" +logo.getRect().getDimension().getWidth());
        System.out.println("X Location: " + logo.getRect().getX());
        System.out.println("Y Location: " +logo.getRect().getY());
    }

    public void takingScreenShotOfSection() throws InterruptedException, IOException {
        WebElement login=driver.findElement(By.className("input-group"));
        File file=login.getScreenshotAs(OutputType.FILE);
        selenium.hardWait(3);
        FileUtils.copyFile(file,new File(Constants.SCREENSHOT_LOCATION+"\\"+"login.png"));
    }
}
