package pageobjects.helloteams.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;
import utilities.Constants;

import java.time.Duration;

public class RegistrationConfirmationPO extends BasePO
{

    public RegistrationConfirmationPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//h1[contains(text(),'Thank you')]")
    private WebElement thankYouText;

    @FindBy(xpath = "//span[contains(@style,'color')]")
    private WebElement emailSent;

    @FindBy(xpath = "//div[contains(@style,'text-align')]/a")
    private WebElement returnToLoginPage;

    /**
     * Message Seen after successful registration
     *
     * @return ThankYou message return
     */
    public String getThankYouMessage() {
        return selenium.getText(thankYouText);
    }

    /**
     * Message seen after successful registration
     * @return Email sent message return
     */
    public String getEmailSentMessage() {
        return selenium.getText(emailSent);
    }

    /**
     * Verify that 'return to login' link is present or not
     *
     * @return link if present
     */
    public boolean isLoginLinkPresent() {
        return selenium.waitInCaseElementVisible(returnToLoginPage, 3) != null;
    }

    /**
     * Navigate to Login page after successful registration
     * Click on Login link
     *
     * @throws InterruptedException exception
     */
    public void clickOnLoginLink() throws InterruptedException {
        selenium.clickOn(returnToLoginPage);
    }
}
