package pageObjects.gonnaOrderr.account;

import dataObjectsModel.RegistrationModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

public class RegistrationPO extends BasePO {

    public RegistrationPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement emailTextBox;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//input[@formcontrolname='confirmPassword']")
    private WebElement confirmPasswordTextBox;

    @FindBy(xpath = "//span[@class='mat-button-wrapper']")
    private WebElement eyeIconConfirmPassword;

    @FindBy(xpath = "//div[contains(@class,'ng-tns-c7-0 ng-trigger ng-trigger-transitionMessages ng-star-inserted')]")
    private WebElement emailFieldRequiredValidationMsg;

    @FindBy(xpath = "//div[contains(@class,'ng-tns-c7-0 ng-trigger ng-trigger-transitionMessages ng-star-inserted')]")
    private WebElement emailInValidValidationMsg;

    @FindBy(xpath = "")
    private WebElement alreadyTakenEmail;

    @FindBy(xpath = "//div[contains(@class,'ng-tns-c7-1 ng-trigger ng-trigger-transitionMessages ng-star-inserted')]")
    private WebElement passwordValidationMsg;

    @FindBy(xpath = "//div[contains(@class,'ng-tns-c7-2 ng-trigger ng-trigger-transitionMessages ng-star-inserted')]")
    private WebElement confirmPasswordValidationMsg;

    @FindBy(xpath = "//button[@class='btn btn-primary px-4']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    private WebElement firstNameTextBox;

    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    private WebElement lastNameTextBox;

    @FindBy(xpath = "(//div[@class='col-12 '])[2]")
    private WebElement countryDropdownList;
    //"//mat-select[@class='mat-select ng-tns-c5-23 ng-untouched ng-pristine ng-star-inserted ng-valid']"
    //"//div[@class='col-12 ']//div[@class='mat-form-field-outline ng-tns-c7-44 ng-star-inserted']"
    //"//div[@class='col-12 ']//div[@class='mat-form-field-outline ng-tns-c7-44 ng-star-inserted']"

    @FindBy(xpath = "//input[@formcontrolname='phoneNumber']")
    private WebElement phoneNumberTextBox;

    @FindBy(xpath = "//button[@class='btn btn-primary px-4']")
    private WebElement acceptAndRegisterButton;

    @FindBy(xpath = "//div[@class='ng-tns-c7-13 ng-trigger ng-trigger-transitionMessages ng-star-inserted']")
    private WebElement firstNameValidationMsg;

    @FindBy(xpath = "//div[@class='ng-tns-c7-14 ng-trigger ng-trigger-transitionMessages ng-star-inserted']")
    private WebElement lastNameValidationMsg;

    @FindBy(xpath = "//div[@class='col-12 phone-error-dmsg ng-star-inserted']")
    private WebElement phoneNumValidationMsg;

    @FindBy(xpath = "//button[@class='btn btn-link px-0 link-btn']")
    private WebElement loginRegisterPageLink;

    @FindBy(xpath = "//span[@class='ng-tns-c5-3 ng-star-inserted']")
    private WebElement languageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-3']")
    private WebElement englishLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-4']")
    private WebElement frenchLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-5']")
    private WebElement germanLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-6']")
    private WebElement greekLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-7']")
    private WebElement hindiLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-8']")
    private WebElement italianLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-9']")
    private WebElement japaneseLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-10']")
    private WebElement portugueseLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-11']")
    private WebElement russianLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-12']")
    private WebElement spanishLanguageDropdown;

    @FindBy(xpath = "//mat-option[@id='mat-option-13']")
    private WebElement turkishLanguageDropdown;




    /*@FindBy(xpath = "//input[@name='display name']/following-sibling::div")
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
    private WebElement digitValidationMessage;*/

    /**
     * Fill out registration details and Register a new user
     *
     * @param registrationModel RegistrationGonna Order object
     * @throws InterruptedException exception
     */
    public void fillRegistrationDetailsAndPerformSignUp(RegistrationModel registrationModel) throws InterruptedException {

        selenium.enterText(emailTextBox, registrationModel.getEmailTextBox(),false);
        selenium.enterText(passwordTextBox,registrationModel.getPasswordTextBox(),false);
        selenium.enterText(confirmPasswordTextBox,registrationModel.getConfirmPasswordTextBox(),false);
        selenium.clickOn(continueButton);
        selenium.enterText(firstNameTextBox,registrationModel.getFirstNameTextBox(),false);
        selenium.enterText(lastNameTextBox,registrationModel.getLastNameTextBox(),false);
        selenium.clickOn(countryDropdownList);
       // selenium.selectDropDownValueByText(countryDropdownList,registrationModel.getCountryNameDropdown());
        selenium.selectDropDownValueByText(countryDropdownList,"Haiti");
        //selenium.selectDropDownValueByIndex(countryDropdownList,1);
       // selenium.getSelectedDropDownValue(countryDropdownList);
        selenium.enterText(phoneNumberTextBox,registrationModel.getPhoneNumberTextBox(),false);
        selenium.click(acceptAndRegisterButton);
    }

    /**
     * Click on 'Continue' button
     * further details you can fill
     *
     * @throws InterruptedException exception
     */
    public void clickOnContinueButton() throws InterruptedException{
        selenium.clickOn(continueButton);
    }

    /**
     *  Click on acceptAndRegister button
     *
     *  @throws InterruptedException exception
     */
    public void acceptAndRegisterButton() throws InterruptedException{
        selenium.clickOn(acceptAndRegisterButton);
    }

    /**
     * Get email field validation message
     *
     * @return validation message
     */
    public String getEmailValidationMessage(){
        return selenium.getText(emailFieldRequiredValidationMsg);
    }

    /**
     * Get password field validation message
     *
     * @return validation message
     */
    public String getPasswordValidationMessage() {
        return selenium.getText(passwordValidationMsg);
    }

    /**
     * Get confirm password field validation message
     *
     * @return validation message
     */
    public String getConfirmPasswordValidationMessage() {
        return selenium.getText(confirmPasswordValidationMsg);
    }

    /**
     * Enter password-confirm password details
     *
     * @param password password
     * @param confirmPassword confirm password
     */
    public void enterPasswordDetails(String password,String confirmPassword){
        selenium.enterText(passwordTextBox,password,false);
        selenium.enterText(confirmPasswordTextBox,confirmPassword,false);
    }
    /**
     *  Get First Name field validation message
     *
     * @return validation message
     */
    public String getFirstNameValidationMessage(){
        return selenium.getText(firstNameValidationMsg);
    }
    /**
     *  Get Last Name field validation message
     *
     * @return validation message
     */
    public String getLastNameValidationMessage(){
        return selenium.getText(lastNameValidationMsg);
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
     *  Click on 'Login' link
     * @throws InterruptedException exception
     */
    public void clickOnLoginRegisterPageLink() throws InterruptedException{
        selenium.clickOn(loginRegisterPageLink);
    }

    /*//**
            * Click on 'Cancel' button
     * @throws InterruptedException exception
     *//*
    public void clickOnCancelButton() throws InterruptedException
    {
        selenium.clickOn(cancelRegisterPageButton);
    }
   /* *//**
     * Get password field validation message for short password
     * @return validation message
     *//*
    public String getShortPasswordValidationMessage()
    {
        return selenium.getText(shortPassword);
    }

    *//**
     * Get password-confirm password fields validation message
     * @return validation message
     *//*
    public String getNotMatchPasswordValidationMessage()
    {
        return selenium.getText(passwordDoesNotMatch);
    }

    *//**
     * Click on 'Cancel' button
     * @throws InterruptedException exception
     *//*
    public void clickOnCancelButton() throws InterruptedException
    {
        selenium.clickOn(cancelRegisterPageButton);
    }

    *//**
     * Get caseLetter validation message for 'Password' field
     * @return validation message
     *//*
    public String getCaseLetterValidationMessage()
    {
        return selenium.getText(caseLetterValidationMessage);
    }

    *//**
     * Get upperLetter validation message for 'Password' field
     * @return validation message
     *//*
    public String getUppercaseValidationMessage() {
        return selenium.getText(upperCaseValidationMessage);
    }

    *//**
     * Get small letter validation message for 'Password' field
     * @return validation message
     *//*
    public String getLowerCaseValidationMessage() {
        return selenium.getText(lowerCaseValidationMessage);
    }

    *//**
     * Get digit validation message for 'Password' field
     * @return validation message
     *//*
    public String getDigitValidationMessage() {
        return selenium.getText(digitValidationMessage);
    }
*/
}
