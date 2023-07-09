package pageobjects.gonnaorder.customer;

import dataobjects.gonnaorder.customer.CustomerOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class ThankYouPO extends BasePO {

    public ThankYouPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(css = "div.store-info h1")
    protected WebElement pageHeaderText;

    @FindBy(css = "app-store-thank-you div.content div.order-number")
    protected WebElement orderID;

    @FindBy(css = "div.thank-you-note")
    protected WebElement thankYouMessage;

    @FindBy(css = "div.ready div.dot")
    protected WebElement acceptOrder;

    @FindBy(css = "div.rejected div.dot")
    protected WebElement rejectOrder;

    protected By expectedOrder = By.className("variant-wrapper");


    @FindBy(css = "h3.font-size-heading")
    private WebElement EmailHeader;

    @FindBy(id = "email")
    private WebElement emailAddressTextBox;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement emailSendButton;

    @FindBy(css = "h3.text-center")
    private WebElement emailStatus;

    @FindBy(xpath = "//div[@class='option-wrapper']/following-sibling::div")
    private WebElement payPalPaymentButton;

    //Payment Failed Error page element

    @FindBy(xpath = "//div[@class='content-wrapper']//following-sibling::div//div")
    private WebElement failedPaymentErrorMessage;

    /**
     * Get email header text
     *
     * @return thank you message text
     */
    public String getGetEmailHeaderText(){
        return selenium.getText(EmailHeader);
    }

    /**
     * Get Email Send Status
     * @return thank you message text
     */
    public String getGetEmailSendStatus()  {
        return selenium.getText(emailStatus);
    }

    /**
     * Get Page header text
     *
     * @return header text
     */
    public String getPageHeaderText() {
        return selenium.getText(pageHeaderText);
    }


    /**
     * Get Failed payment by Credit card Error Page text
     *
     * @return Error text
     */
    public String getFailedPaymentErrorText() {
        return selenium.getText(failedPaymentErrorMessage);
    }


    /**
     * Get Thank you message text
     *
     * @return thank you message text
     */
    public String getThankYouMessageText()  {
        return selenium.getText(thankYouMessage);
    }

    /**
     * Get Order ID
     *
     * @return Order ID
     * @throws InterruptedException exception
     */
    public String getOrderID() throws InterruptedException {
        selenium.hardWait(5);
        return selenium.getText(orderID);
    }

    /**
     * Get accepted state color
     *
     * @return color
     * @throws InterruptedException exception
     */
    public String getAcceptedStateColor() throws InterruptedException {
        selenium.hardWait(10); // wait for auto refreshing the page
        return selenium.getElementCssValue(acceptOrder, "background-color");
    }

    /**
     * Get rejected state color
     *
     * @return color
     * @throws InterruptedException exception
     */
    public String getRejectedStateColor() throws InterruptedException {
        selenium.hardWait(10); // wait for auto refreshing the page
        return selenium.getElementCssValue(rejectOrder, "background-color");
    }

    /**
     * Is Ready order message present ?
     *
     * @return true or false
     * @throws InterruptedException exception
     */

    public boolean isOrderReadyMessagePresent() throws InterruptedException {
        String xpathLocator = "//*[text()=' Your order is ready ']";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }

    /**
     * Is Expected order message present ?
     *
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isOrderExpectedMessagePresent() throws InterruptedException {
        String xpathLocator = "//*[text()=' Your order is expected ']";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }


    /**
     * Get order expected message
     * @return order expected message
     */
    public String getOrderExpectedMessagePresent() {
        return selenium.getText(expectedOrder);
    }

    /**
     * Fill email info and Click on Send button
     *
     * @param emailInfo object
     * @throws InterruptedException exception
     */
    public void fillEmailAndClickOnSendButton(CustomerOrder emailInfo) throws InterruptedException {

        selenium.enterText(emailAddressTextBox, emailInfo.getEmailAddress(), false);
        selenium.clickOn(emailSendButton);
    }

    /**
     * Is order rejected reason message present ?
     *
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isOrderRejectedReasonMessagePresent() throws InterruptedException {
        return selenium.isElementPresent(expectedOrder);
    }

    /**
     * Click on PayPal Payment button
     * @throws InterruptedException exception
     */
    public void ClickOnPayPalPaymentButton() throws InterruptedException {
        selenium.clickOn(payPalPaymentButton);
    }

}
