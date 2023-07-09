import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase{


    @Test
    public void doLogin() {

       /* webDriver.findElement(By.id("identifierId")).sendKeys("abc@gmail.com");
        webDriver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        System.out.println("CLick ");*/


            webDriver.findElement(By.id("identifierId")).sendKeys("abc@gmail.com");
            webDriver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
            //System.out.println("CLick ");

    }

}
