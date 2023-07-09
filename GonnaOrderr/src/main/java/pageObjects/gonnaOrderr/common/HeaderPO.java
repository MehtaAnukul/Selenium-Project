package pageObjects.gonnaOrderr.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

public class HeaderPO extends BasePO {
    public HeaderPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//a[@class='navbar-brand justify-content-center']//img[@class='navbar-brand-full ng-star-inserted']")
    private WebElement gonnaOrderLogoFullImg;

    @FindBy(xpath = "//a[@class='navbar-brand justify-content-center']//img[@class='navbar-brand-minimized ng-star-inserted']")
    private WebElement gonnaOrderLogoMinimizedImg;

    @FindBy(xpath = "//button[@class='navbar-toggler d-none d-lg-block ng-star-inserted']//span[@class='navbar-toggler-icon']")
    private WebElement navigationMenuIcon;

    @FindBy(xpath = "//span[@class='d-none d-md-inline-block d-lg-inline-block d-xl-inline-block ml-1']")
    private WebElement supportText;

    @FindBy(xpath = "//span[@class='d-none d-sm-inline-block mr-1']")
    private WebElement adminDropDown;

    @FindBy(xpath = "//a[@class='dropdown-item dropdown-links']//i[@class='fas fa-user adminIcons']")
    private WebElement myProfileAdminDropDownList;

    @FindBy(xpath = "//a[@class='dropdown-item dropdown-links']//i[@class='fas fa-user-friends adminIcons']")
    private WebElement socialAccountsAdminDropDownList;

    @FindBy(xpath = "//a[@class='dropdown-item dropdown-links']//i[@class='fas fa-lock adminIcons']")
    private WebElement changePasswordAdminDropDownList;

    @FindBy(xpath = "//a[@class='dropdown-item dropdown-links']//i[@class='adminIcons material-icons']")
    private WebElement logoutAdminDropDownList;


    /**
     * Click on gonnaOrderLogoFullImg
     * @throws InterruptedException exception
     */
    public void gonnaOrderLogoFullImg() throws InterruptedException{
        selenium.clickOn(gonnaOrderLogoFullImg);
    }
    /**
     * Click on gonnaOrderLogoMinimizedImg
     * @throws InterruptedException exception
     */
    public void gonnaOrderLogoMinimizedImg() throws InterruptedException{
        selenium.clickOn(gonnaOrderLogoMinimizedImg);
    }
    /**
     * Click on Support Text
     * @throws InterruptedException exception
     */
    public void supportText() throws InterruptedException{
        selenium.clickOn(supportText);
    }

    /**
     * Click on adminDropdown
     */
    public void adminDropDown() throws InterruptedException{
        selenium.clickOn(adminDropDown);
    }

    /**
     * Click on adminDropdown and click on My Profile text
     */
    public void myProfileAdminDropDownList() throws InterruptedException{
        selenium.clickOn(myProfileAdminDropDownList);
    }
    /**
     * Click on adminDropdown and click on Social Account text
     */
    public void socialAccountsAdminDropDownList() throws InterruptedException{
        selenium.clickOn(socialAccountsAdminDropDownList);
    }
    /**
     * Click on adminDropdown and click on Change Password text
     */
    public void changePasswordAdminDropDownList() throws InterruptedException{
        selenium.clickOn(changePasswordAdminDropDownList);
    }
    /**
     * Click on adminDropdown and click on Logout text
     */
    public void logout() throws InterruptedException{
        selenium.clickOn(logoutAdminDropDownList);
        selenium.hardWait(3);
    }


}
