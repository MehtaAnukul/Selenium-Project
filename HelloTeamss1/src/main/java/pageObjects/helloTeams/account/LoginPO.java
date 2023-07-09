package pageObjects.helloTeams.account;

import pageObjects.base.BasePO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends BasePO {

    public LoginPO(WebDriver driver) {
        super(driver);
    }


    /*
     *  All WebElements are identified by @FindBy annotation
     *  @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//button[@class='btn btn-secondary']")
    private WebElement registerLoginPageButton;

    @FindBy(css = "button[class='btn btn-secondary']")
    private WebElement registerLoginPageButtonn;

    @FindBy(xpath = "//div[contains(@style,'text-align: center')]//h1")
    private WebElement loginText;

    @FindBy(css = "div > h1")
    private WebElement loginTextt;

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailTextBoxx;

    @FindBy(css = "input[name='email']")
    private WebElement emailTextBoxxx;

    @FindBy(name = "password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTextBoxx;

    @FindBy(css = "input[name='password']")
    private WebElement passwordTextBoxxx;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement loginButton;

    @FindBy(css = "button[class='btn btn-primary']")
    private WebElement loginButtonn;

    @FindBy(xpath = "//div[@class='form-group']//a[contains(text(),'Forgot password')]")
    private WebElement forgotPasswordLink;

    @FindBy(css = "a[href='/resetrequest']")
    private WebElement forgotPasswordLinkk;

    @FindBy(xpath = "//span[@class='alert-message']")
    private WebElement invalidDataValidationMessage;

    @FindBy(xpath = "//span[contains(@class,'alert-message')]")
    private WebElement invalidDataValidationMessagee;

    @FindBy(css = "span[class='alert-message']")
    private WebElement invalidDataValidationMessageee;


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
    public String getLoginText() {
        return selenium.getText(loginText);
    }

    /**
     * Fill out login details and Login to application
     *
     * @throws InterruptedException exception
     */
    public void fillLoginDetailsAndPerformLogin(String email,String password) throws InterruptedException {

        selenium.enterText(emailTextBox, email, false);
        selenium.enterText(passwordTextBox, password, false);
        selenium.click(loginButton);
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
    public String getInvalidDataValidationMessage() {
        return selenium.getText(invalidDataValidationMessage);
    }


    /**
     * Navigate to Login page from registerConfirm page
     */
    public String getLoginLink() {
        return selenium.getURL();
    }


}
