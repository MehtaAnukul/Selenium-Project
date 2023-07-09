package pageobjects.cord.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class HeaderPO extends BasePO {

    public HeaderPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(css = "p[class*='ProfileToolbar-name']")
    private WebElement profileName;
/*
    @FindBy(css = "button[aria-controls='profile-menu']")
    private WebElement profileIcon;*/

    @FindBy(className = "ion__svg")
    private WebElement logoutButton;

    /**
     * Get profile name
     *
     * @return name
     */
    public String getProfileName() {
        return selenium.getText(profileName);
    }


    /**
     * Click on Profile Icon and click on Sign out button
     */
    public void logOut() throws InterruptedException {
       // selenium.clickOn(profileIcon);
        selenium.clickOn(logoutButton);
        selenium.hardWait(3);
    }
}

