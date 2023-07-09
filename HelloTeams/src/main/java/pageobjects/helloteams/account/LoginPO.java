package pageobjects.helloteams.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class LoginPO extends BasePO {

    public LoginPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//button[@class='btn btn-secondary']")
    private WebElement registerLoginPageButton;

    @FindBy(xpath = "//div[contains(@style,'text-align')]/h1")
    private WebElement loginText;

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(name= "password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement loginButton;

    @FindBy(css = "a[href='/resetrequest']")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//span[contains(@class,'alert-message')]")
    private WebElement invalidDataValidationMessage;

    /**
     * Navigate to Register page
     *
     * @throws InterruptedException exception
     */
    public void clickOnRegisterButton() throws InterruptedException {
        selenium.clickOn(registerLoginPageButton);
    }

    /**
     * Navigate to Login page from Register page
     *
     * @return Login text for assert
     */
    public String getLoginText(){
        return selenium.getText(loginText);
    }

    /**
     * Fill out login details and Login to application
     *
     * @param email    Enter email or phone details
     * @param password Enter password details
     * @throws InterruptedException exception
     */
    public void fillLoginDetailsAndPerformLogin(String email,String password) throws InterruptedException
    {
        selenium.enterText(emailTextBox, email ,false);
        selenium.enterText(passwordTextBox, password, false);
        selenium.clickOn(loginButton);
    }

    /**
     * Click on forgot password link
     *
     * @throws InterruptedException exception
     */
    public void clickOnForgotPasswordLink() throws InterruptedException {
        selenium.clickOn(forgotPasswordLink);
    }

    /**
     * Validation message for entering invalid data
     *
     * @return validation message
     */
    public String getInvalidDataValidationMessage()
    {
        return selenium.getText(invalidDataValidationMessage);
    }

    /**
     * Navigate to Login page from registerConfirm page
     */
    public String getLoginLink()
    {
        return selenium.getURL();
    }
}

