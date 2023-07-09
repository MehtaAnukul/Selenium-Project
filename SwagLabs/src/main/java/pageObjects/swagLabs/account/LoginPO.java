package pageObjects.swagLabs.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

public class LoginPO extends BasePO {
    public LoginPO(WebDriver driver) {
        super(driver);
    }

    /*
     *  All WebElements are identified by @FindBy annotation
     *  @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(id = "user-name")
    private WebElement userNameTextBox;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement invalidDataValidationMessage;

    /**
     * Fill out login details and Login to application
     *
     * @throws InterruptedException exception
     */
    public void fillLoginDetailsAndPerformLogin(String userName, String password) throws InterruptedException {
        selenium.enterText(userNameTextBox, userName, false);
        selenium.enterText(passwordTextBox, password, false);
        selenium.click(loginButton);
    }

    /**
     * Validation message for entering invalid data
     *
     * @return validation message
     */
    public String getInvalidDataValidationMessage() {
        return selenium.getText(invalidDataValidationMessage);
    }

}
