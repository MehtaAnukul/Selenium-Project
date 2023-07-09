import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver webDriver;


    @BeforeSuite
    public void setUp(){
        if(webDriver == null){
            webDriver = new ChromeDriver();
            webDriver.get("http://gmail.com");
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);

        }
    }

    @AfterSuite
    public void tearDown(){
        webDriver.quit();
    }
}
