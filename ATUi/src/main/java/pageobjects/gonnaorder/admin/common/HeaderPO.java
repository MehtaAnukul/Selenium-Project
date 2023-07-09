package pageobjects.gonnaorder.admin.common;

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

    @FindBy(className = "current-store")
    private WebElement storeName;

    @FindBy(css = ".app-header .dropdown span")
    private WebElement profileName;

    @FindBy(xpath = "//span[contains(text(),'Logout')]")
    private WebElement logoutLink;


    /**
     * Get Store Name
     *
     * @return string Store name
     */
    public String getStoreName() {
        return selenium.getText(storeName);
    }

    /**
     * Get Profile Name
     *
     * @return string profile name
     */
    public String getProfileName() {
        return selenium.getText(profileName);
    }

    /***
     * Click on Profile Name
     * @throws InterruptedException
     */
    public void clickOnProfileName() throws InterruptedException {
        selenium.clickOn(profileName);
    }

    /***
     * Click on Logout menu
     * @throws InterruptedException
     */
    public void clickOnLogoutMenu() throws InterruptedException {
        selenium.clickOn(logoutLink);
    }


}
