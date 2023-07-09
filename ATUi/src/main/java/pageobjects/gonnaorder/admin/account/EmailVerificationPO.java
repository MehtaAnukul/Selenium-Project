package pageobjects.gonnaorder.admin.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class EmailVerificationPO extends BasePO {

    public EmailVerificationPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "")
    private WebElement pageTitle;

    @FindBy(css = "div[role='alert']")
    private WebElement successMessage;

    @FindBy(id = "msg_body")
    private WebElement messageBodyIFrame;


    /**
     * Is login title text present ?
     *
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isLoginTitleTextPresent() throws InterruptedException {
        String xpathLocator = "//*[text()='Login']";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }

    /**
     * Get success message text
     *
     * @return message text
     */
    public String getSuccessMessageText() {
        return selenium.getText(successMessage);
    }

}