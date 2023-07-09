package pageobjects.gonnaorder.customer.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class FooterPO extends BasePO {

    public FooterPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//div[contains(text(),'Add to your order')]")
    private WebElement addToYourOrderButton;

    @FindBy(xpath = "//a[contains(text(),'View order')]")
    private WebElement viewOrderButton;

    @FindBy(xpath = "//a[contains(text(),'Submit your order')]")
    private WebElement submitYourOrderButton;

    @FindBy(xpath = "//a[contains(text(),'Proceed to Payment')]")
    private WebElement proceedToPaymentButton;

    @FindBy(xpath = "//a[contains(text(),'Submit your order')]/span")
    private WebElement totalPriceOnSubmitYourOrderButton;


    /***
     * Click on 'Add to your order' Button
     * @throws InterruptedException
     */
    public void clickOnAddToYourOrderButton() throws InterruptedException {
        selenium.click(addToYourOrderButton);
    }

    /***
     * Click on 'View order' button
     * @throws InterruptedException
     */
    public void clickOnViewOrderButton() throws InterruptedException {
        selenium.click(viewOrderButton);
    }

    /***
     * Click on 'Submit your order' button
     * @throws InterruptedException
     */
    public void clickOnSubmitYourOrderButton() throws InterruptedException {
        selenium.click(submitYourOrderButton);
    }

    /***
     * Click on 'Proceed to payment' button
     * @throws InterruptedException
     */
    public void clickOnProceedToPaymentButton() throws InterruptedException {
        selenium.doubleClickOnElement(proceedToPaymentButton);
        selenium.hardWait(5);
    }

    /***
     * Verify 'Proceed to payment' button is enabled
     * @throws InterruptedException
     */
    public boolean verifyProceedToPaymentButtonIsEnabled() throws InterruptedException {
        return selenium.isElementEnabled(proceedToPaymentButton);
    }

    /**
     * Get total price on submit your order button
     *
     * @return total price on submit your order button
     */
    public String getTotalPriceOnSubmitYourOrderButton() {
        return selenium.getText(totalPriceOnSubmitYourOrderButton);
    }


    /**
     * Get total price from 'Add to Your Order' button
     *
     * @return total price on 'Add to Your Order' button
     */
    public String getTotalPriceFromAddToYourOrderButton() {
        return selenium.getText(addToYourOrderButton);
    }

}
