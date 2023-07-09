package pageObjects.gonnaOrderr.account;

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


    @FindBy(xpath = "//button[@class='d-flex btn btn-link px-0']")
    private WebElement signUpLoginPageLink;

    @FindBy(css = "button[class='d-flex btn btn-link px-0']")      // for css $("button[class='d-flex btn btn-link px-0']")
    private WebElement SignUpLoginPageLinkk;

    @FindBy(xpath = "//h1[@class='text-center text-blue']")
    private WebElement loginText;

    @FindBy(css = "div > h1[class='text-center text-blue']")
    private WebElement loginTextt;

    @FindBy(xpath = "//div[@class='google-btn col-lg-12 col-xl-auto']//p")
    private WebElement signInWithGoogleButton;

    @FindBy(css = "div > p[class='btn-text text-center']")
    private WebElement signInWithGoogleButtonn;

    @FindBy(xpath = "//div[@class='facebook-btn col-lg-12 col-xl-auto mt-3 mt-xl-0']//p")
    private WebElement signInWithFacebookButton;

    @FindBy(css = "div > p[class='btn-text font-weight-bold text-center']")
    private WebElement signInWithFacebookButtonn;

    @FindBy(xpath = "//input[@formcontrolname='username']")
    private WebElement emailTextBox;

    @FindBy(css = "input[formcontrolname='username']")
    private WebElement emailTextBoxx;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordTextBox;

    @FindBy(css = "input[formcontrolname=password]")
    private WebElement passwordTextBoxx;

    @FindBy(xpath = "//button[@class='btn btn-primary font-weight-bold']")
    private WebElement loginButton;

    @FindBy(css = "button[class='btn btn-primary font-weight-bold']")
    private WebElement loginButtonn;

    @FindBy(xpath = "//button[@class='btn btn-link px-0']")
    private WebElement forgotPasswordLink;

    @FindBy(css = "button[class='btn btn-link px-0']")
    private WebElement forgotPasswordLinkk;

    @FindBy(xpath = "//div[@class='alert w-100 alert-danger ng-star-inserted']")
    private WebElement invalidDataValidationMessage;

    @FindBy(css = "div[class='alert w-100 alert-danger ng-star-inserted']")
    private WebElement invalidDataValidationMessageee;

    @FindBy(xpath = "//span[@class='ng-tns-c5-2 ng-star-inserted']")
    private WebElement languageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-4']")
    private WebElement frenchDropdownLanguage;

    @FindBy(xpath = "//mat-option[@id='mat-option-5']")
    private WebElement germanDropdownLanguage;

    @FindBy(xpath = "//mat-option[@id='mat-option-6']")
    private WebElement greekDropdownLanguage;

    @FindBy(xpath = "//mat-option[@id='mat-option-7']")
    private WebElement hindiDropdownLanguage;


    /**
     * Navigate to Register page
     *
     * @throws InterruptedException exception
     */
    public void clickOnSignUpLoginPageLink() throws InterruptedException {

        selenium.clickOn(signUpLoginPageLink);
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

    /**
     * Sign in with Google Credentials
     */
    public void signInWithGoogleButton() throws InterruptedException{
        selenium.clickOn(signInWithGoogleButton);
    }

    /**
     * Sign in with Facebook Credentials
     */
    public void signInWithFacebookButton() throws InterruptedException{
        selenium.clickOn(signInWithFacebookButton);
    }

    /**
     * Click on Language in Dropdown list
     */
    public void clickOnEnglishLanguageDropdown() throws InterruptedException{
        selenium.clickOn(languageDropdown);
    }
    public void clickOnFrenchLanguageDropdown() throws InterruptedException{
        selenium.clickOn(frenchDropdownLanguage);
    }
    public void clickOnGermanLanguageDropdown() throws InterruptedException{
        selenium.clickOn(germanDropdownLanguage);
    }
    public void clickOnGreekLanguageDropdown() throws InterruptedException{
        selenium.clickOn(greekDropdownLanguage);
    }
    public void clickOnHindiLanguageDropdown() throws InterruptedException{
        selenium.clickOn(hindiDropdownLanguage);
    }


}
