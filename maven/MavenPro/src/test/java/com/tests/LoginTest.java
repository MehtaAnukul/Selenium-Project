package com.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{


    @Test
    public void doLogin(){

        try {
            webDriver.findElement(By.id("Email")).sendKeys("abc@gmail.com");
            webDriver.findElement(By.id("next")).click();
        }catch (Throwable t){
            //capturing screenshot
        }

    }

}
