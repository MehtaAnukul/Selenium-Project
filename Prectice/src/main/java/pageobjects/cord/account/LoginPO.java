package pageobjects.cord.account;

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

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(name= "password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//Span[contains(text(),'Sign In')]")
    private WebElement signInButton;

    @FindBy(css = "p[class*='LoginForm-formError']")
    private WebElement errorMessage;

    @FindBy(css = "a[href='/forgot-password']")
    private WebElement forgotPasswordLink;


    /**
     * Fill out login details and Login to application
     *
     * @param email    Enter email or phone details
     * @param password Enter password details
     * @throws InterruptedException exception
     */
    public void fillLoginDetailsAndPerformLogin(String email,String password) throws InterruptedException
    {
        selenium.enterText(emailTextBox, email, false);
        selenium.enterText(passwordTextBox, password, false);
        selenium.clickOn(signInButton);
    }

    /**
     *Get error message
     * @return error message
     */
    public String getErrorMessage()
    {
        return selenium.getText(errorMessage);
    }

    /**
     * Click on forgot password link
     */
    public void clickOnForgotPasswordLink() throws InterruptedException {
        selenium.clickOn(forgotPasswordLink);
    }
}

