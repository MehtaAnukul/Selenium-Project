package pageobjects.helloteams.account;

import dataobjects.Registration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class RegistrationPO extends BasePO {

    public RegistrationPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(name = "display name")
    private WebElement nameTextBox;

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(name = "password")
    private WebElement passwordTextBox;

    @FindBy(name = "confirmation")
    private WebElement confirmPasswordTextBox;

    @FindBy(css = "span.svc-checkbox-checkmark")
    private WebElement checkboxToAgreeCondition;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement registerButton;

    @FindBy(xpath = "//button[@class='btn btn-secondary']")
    private WebElement cancelRegisterPageButton;

  /*  @FindBy(xpath = "//a[contains(text(),'terms and conditions')]")
    private WebElement termsAndConditionLink;

    @FindBy(xpath = "//a[contains(text(),'privacy policy')]")
    private WebElement privacyPolicyLink;

    @FindBy(xpath = "//a[contains(text(),'support@helloteams.io')]")
    private WebElement supportLink; */

    @FindBy(xpath = "//input[@name='display name']/following-sibling::div")
    private WebElement nameTextBoxValidationMessage;

    @FindBy(xpath = "//input[@name='email']/following-sibling::div")
    private WebElement emailValidationMessage;

    @FindBy(xpath = "//input[@name='password']/following-sibling::div")
    private WebElement passwordValidationMessage;

    @FindBy(xpath = "//input[@name='confirmation']/following-sibling::div")
    private WebElement confirmPasswordValidationMessage;

    @FindBy(xpath = "//span[contains(@class,'alert-message')]")
    private WebElement invalidDomain;

    @FindBy(xpath = "//span[contains(@class,'alert-message')]")
    private WebElement alreadyTakenEmail;

    @FindBy(xpath = "//input[@name='password']/following-sibling::div")
    private WebElement shortPassword;

    @FindBy(xpath = "//input[@name='confirmation']/following-sibling::div")
    private WebElement passwordDoesNotMatch;

    @FindBy(xpath = "//span[contains(@class,'alert-message')]")
    private WebElement caseLetterValidationMessage;

    @FindBy(xpath = "//span[contains(@class,'alert-message')]")
    private WebElement upperCaseValidationMessage;

    @FindBy(xpath = "//span[contains(@class,'alert-message')]")
    private WebElement lowerCaseValidationMessage;

    @FindBy(xpath = "//span[contains(@class,'alert-message')]")
    private WebElement digitValidationMessage;

    /**
     * Fill out registration details and Register a new user
     *
     * @param data RegistrationHelloTeams object
     * @throws InterruptedException exception
     */
    public void fillRegistrationDetailsAndPerformSignUp(Registration data) throws InterruptedException {
        selenium.enterText(nameTextBox, data.getNameTextBox(), false);
        selenium.enterText(emailTextBox, data.getEmail(), false);
        selenium.enterText(passwordTextBox, data.getPassword(), false);
        selenium.enterText(confirmPasswordTextBox, data.getConfirmPassword(), false);
        selenium.checkbox(checkboxToAgreeCondition, true);
        selenium.clickOn(registerButton);
    }

    /**
     * Click on 'Checkbox' button
     * 'Register' button is displayed
     *
     * @throws InterruptedException exception
     */
    public void clickOnCheckbox() throws InterruptedException {
        selenium.clickOn(checkboxToAgreeCondition);
    }

    /**
     * Verify that 'Register' button  is present or not
     *
     * @return button if present
     */
    public boolean isRegisterButtonPresent() {
        return selenium.waitInCaseElementVisible(registerButton, 5) != null;
    }


    /**
     * Click on Register button
     *
     * @throws InterruptedException exception
     */
    public void clickOnRegisterButton() throws InterruptedException {
        selenium.clickOn(registerButton);
    }

    /**
     * Get display name field validation message
     *
     * @return validation message
     */
    public String getDisplayNameValidationMessage() {
        return selenium.getText(nameTextBoxValidationMessage);
    }

    /**
     * Get email field validation message
     *
     * @return validation message
     */
    public String getEmailValidationMessage() {
        return selenium.getText(emailValidationMessage);
    }

    /**
     * Get password field validation message
     *
     * @return validation message
     */
    public String getPasswordValidationMessage() {
        return selenium.getText(passwordValidationMessage);
    }

    /**
     * Get confirm password field validation message
     *
     * @return validation message
     */
    public String getConfirmPasswordValidationMessage() {
        return selenium.getText(confirmPasswordValidationMessage);
    }

    /**
     * Get email field validation message for invalid 'Domain'
     *
     * @return validation message
     */
    public String getInvalidDomainValidationMessage() {
        return selenium.getText(invalidDomain);
    }

    /**
     * Get email field validation message for already taken email
     *
     * @return validation message
     */
    public String getAlreadyTakenEmailValidationMessage() {
        return selenium.getText(alreadyTakenEmail);
    }

    /**
     * Enter password-confirm password details
     *
     * @param password        password
     * @param confirmPassword confirm password
     */
    public void enterPasswordDetails(String password, String confirmPassword) {
        selenium.enterText(passwordTextBox, password, false);
        selenium.enterText(confirmPasswordTextBox, confirmPassword, false);
    }

    /**
     * Get password field validation message for short password
     * @return validation message
     */
    public String getShortPasswordValidationMessage()
    {
        return selenium.getText(shortPassword);
    }

    /**
     * Get password-confirm password fields validation message
     * @return validation message
     */
    public String getNotMatchPasswordValidationMessage()
    {
        return selenium.getText(passwordDoesNotMatch);
    }

    /**
     * Click on 'Cancel' button
     * @throws InterruptedException exception
     */
    public void clickOnCancelButton() throws InterruptedException
    {
        selenium.clickOn(cancelRegisterPageButton);
    }

    /**
     * Get caseLetter validation message for 'Password' field
     * @return validation message
     */
    public String getCaseLetterValidationMessage()
    {
        return selenium.getText(caseLetterValidationMessage);
    }

    /**
     * Get upperLetter validation message for 'Password' field
     * @return validation message
     */
    public String getUppercaseValidationMessage() {
        return selenium.getText(upperCaseValidationMessage);
    }

    /**
     * Get small letter validation message for 'Password' field
     * @return validation message
     */
    public String getLowerCaseValidationMessage() {
        return selenium.getText(lowerCaseValidationMessage);
    }

    /**
     * Get digit validation message for 'Password' field
     * @return validation message
     */
    public String getDigitValidationMessage() {
        return selenium.getText(digitValidationMessage);
    }
}

