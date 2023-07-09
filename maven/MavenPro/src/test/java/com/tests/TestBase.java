package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver webDriver;


    @BeforeSuite
    public void setUp(){
        if(webDriver == null){
            webDriver = new FirefoxDriver();
            webDriver.get("https://maven.apache.org/");
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);

        }
    }

    @AfterSuite
    public void tearDown(){
        webDriver.quit();
    }
}
