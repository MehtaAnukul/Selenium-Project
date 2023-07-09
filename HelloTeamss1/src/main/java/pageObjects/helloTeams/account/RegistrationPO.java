package pageObjects.helloTeams.account;

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

    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    private WebElement firstNameTextBox;

    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    private WebElement LastNameTextBox;

    @FindBy(xpath = "//mat-select[@id='mat-select-1']")
    private WebElement countryDropdownList;

    @FindBy(xpath = "//input[@formcontrolname='phoneNumber']")
    private WebElement phoneNumberTextBox;



    /*@FindBy(name = "display name")
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

  *//*  @FindBy(xpath = "//a[contains(text(),'terms and conditions')]")
    private WebElement termsAndConditionLink;

    @FindBy(xpath = "//a[contains(text(),'privacy policy')]")
    private WebElement privacyPolicyLink;

    @FindBy(xpath = "//a[contains(text(),'support@helloteams.io')]")
    private WebElement supportLink; *//*

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
    private WebElement digitValidationMessage;*/

}
