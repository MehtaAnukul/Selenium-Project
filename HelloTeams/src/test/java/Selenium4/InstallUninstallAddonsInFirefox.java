package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class InstallUninstallAddonsInFirefox {
    public static void main(String args[]) throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Reporter.log("install and uninstall addons in firefox(this methods only for this browser)");
        Path path = Paths.get("C://Users//Chhevangee//Downloads//selenium_ide-3.17.0-fx.xpi");
        Thread.sleep(5000);
        String extension = ((FirefoxDriver)driver).installExtension(path);
        System.out.println("extension Id"+extension);
        Thread.sleep(5000);
        ((FirefoxDriver)driver).uninstallExtension(extension);
        driver.close();
    }
}
