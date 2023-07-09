package pageobjects.gonnaorder.admin.account;

import dataobjects.gonnaorder.admin.SignUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class SignUpPO extends BasePO {

    public SignUpPO(WebDriver driver) {
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
    private WebElement lastNameTextBox;

    @FindBy(xpath = "//mat-select[@formcontrolname='countryId']")
    private WebElement countryDropDown;

    @FindBy(xpath = "//input[@formcontrolname='phoneNumber']")
    private WebElement phoneNumberTextBox;

    @FindBy(xpath = "//div[@class='mat-select-arrow']")
    private WebElement languageDropDown;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//input[@formcontrolname='confirmPassword']")
    private WebElement confirmPasswordTextBox;

    @FindBy(xpath = "//button[contains(text(),' Accept and Register ')]")
    private WebElement AcceptRegisterButton;

    @FindBy(css = "div[role='alert']")
    private WebElement successMessage;

    /**
     * Fill register user form and click on Register button
     *
     * @param signUpInfo Sign Up object
     * @throws InterruptedException exception
     */
    public void fillSignUpDetailsAndClickOnRegisterButton(SignUp signUpInfo) throws InterruptedException {

            selenium.clickOn(languageDropDown);
            String LanguageDropDownLocator = "//span[@class='mat-option-text'][contains(text(),'" + signUpInfo.getLanguage() + "')]";
            selenium.click(By.xpath(LanguageDropDownLocator));
            if (!signUpInfo.getEmail().isEmpty()) {
            selenium.enterText(emailTextBox, signUpInfo.getEmail(), false);
            }
            selenium.enterText(firstNameTextBox, signUpInfo.getFirstName(), false);
            selenium.enterText(lastNameTextBox, signUpInfo.getLastName(), false);
            selenium.clickOn(countryDropDown);
            String CountryDropDownLocator = "//span[@class='mat-option-text'][contains(text(),'" + signUpInfo.getCountry() + "')]";
            selenium.click(By.xpath(CountryDropDownLocator));
            selenium.enterText(phoneNumberTextBox, signUpInfo.getPhoneNumber(), false);
            selenium.enterText(passwordTextBox, signUpInfo.getPassword(), false);
            selenium.enterText(confirmPasswordTextBox, signUpInfo.getConfirmPassword(), false);
            selenium.clickOn(AcceptRegisterButton);
        }

    /**
     * Get success message
     *
     * @return success message
     */
    public String getSuccessMessage() {
        return selenium.getText(successMessage);
    }
}


