package pageObjects.swagLabs.account;

import dataObjectsModel.CheckOutModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

public class CheckOutPO extends BasePO {
    public CheckOutPO(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameTextBox;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameTextBox;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement zipCodeTextBox;

    @FindBy(xpath = "//a[@class='cart_cancel_link btn_secondary']")
    private WebElement cancelButton;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement continueButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement invalidDataValidationMessage;

    /**
     * Fill out CheckOut details
     *
     * @throws InterruptedException exception
     */
    public void fillCheckOutDetails(CheckOutModel checkOutModel) throws InterruptedException {
        selenium.enterText(firstNameTextBox, checkOutModel.getFirstNameTextBox(), false);
        selenium.enterText(lastNameTextBox, checkOutModel.getLastNameTextBox(), false);
        selenium.enterText(zipCodeTextBox, checkOutModel.getZipCodeTextBox(), false);
        selenium.clickOn(continueButton);
    }
    /**
     * Click on Cancel button
     *
     * @throws InterruptedException exception
     */
    public void cancelButton() throws InterruptedException {
        selenium.clickOn(cancelButton);
    }
    /**
     * Get Validation message
     *
     * @return validation message
     */
    public String getInvalidDataValidationMessage() {
        return selenium.getText(invalidDataValidationMessage);
    }

}
