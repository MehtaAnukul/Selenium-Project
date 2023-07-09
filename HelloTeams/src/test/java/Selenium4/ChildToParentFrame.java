package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class ChildToParentFrame {
    public static void main(String arg[]) {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Reporter.log("going one frame to another frame");
        driver.get("https://www.quackit.com/html/tags/html_iframe_tag.cfm");

        Reporter.log("Page to ParentFrame");
        WebElement parentFrameLocator = driver.findElement(By.cssSelector("iframe[src$='editorCode4']"));
        driver.switchTo().frame(parentFrameLocator);

        Reporter.log("Parent frame to child frame");
        WebElement childFrameLocator = driver.findElement(By.cssSelector("iframe[src$='tag_example.cfm']"));
        driver.switchTo().frame(childFrameLocator);

        Reporter.log("Child Frame to Parent Frame");
        driver.switchTo().parentFrame(); // new Feature

        if(childFrameLocator.isDisplayed())
        {
            System.out.println("inside parentFrame");
        }
        driver.close();
    }
}
