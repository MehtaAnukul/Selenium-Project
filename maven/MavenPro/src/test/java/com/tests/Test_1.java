package com.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestListener;
import org.testng.annotations.*;

public class Test_1
{
    @Test
    public void testLogin(){
       /* WebDriver driver = new FirefoxDriver();
        driver.get("https://www.javatpoint.com/java-tutorial");*/
        System.out.println("Testing login screen");                             // 4
    }

    @Test
    public void testRegistration(){
        System.out.println("Testing registration form");
    }      // 7

    @BeforeMethod
    public void openBrowser(){
        System.out.println("Opening Browser");
    }             // 3   // 6

    @AfterMethod
    public void closeBrowser(){
        System.out.println("Closing Browser");
    }            // 5   // 8

    @BeforeTest
    public void openDbConnection(){
        System.out.println("Create DB connection");
    }   // 2

    @AfterTest
    public void closeDbConnection(){
        System.out.println("Close DB connection");
    }   // 11

    @BeforeSuite
    protected void runSeleniumServer(){
        System.out.println("Starting selenium server");
    }  // 1

    @AfterSuite
    public void stopSeleniumServer(){
        System.out.println("Stopping selenium server");
    }  //  12


}
