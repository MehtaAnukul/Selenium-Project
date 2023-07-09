package Selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.log.Log;
import org.openqa.selenium.devtools.security.Security;
import org.testng.Reporter;

import java.util.concurrent.TimeUnit;

public class DevToolsSelenium4 {
    public static void main(String arg[])
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        DevTools devTools = ((ChromeDriver)driver).getDevTools();   // method in ChromiumDriver only
        devTools.createSession();

        Reporter.log("get a devtools data of githug which is open in chrome");
        devTools.send(Log.enable());   //devTools log
        devTools.addListener(Log.entryAdded(), entry -> System.out.println(entry.getText()));
        driver.get("https://nhnb.github.io/console-log/console-log/demo.html");
        //  devTools.addListener(Log.entryAdded(), entry -> System.out.println(entry.asSeleniumLogEntry()));  needed import org.openqa.selenium.devtools.Log; have this library in previous version

        Reporter.log("get a website which is expired(not opened and display insecure error)");
        devTools.send(Security.enable()); //devTools Security
        devTools.send(Security.setIgnoreCertificateErrors(true)); //for ignore give boolean value true
        driver.get("https://expired.badssl.com/");
    }
}
