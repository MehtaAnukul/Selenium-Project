package pageobjects.gonnaorder.admin.store.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;


public class OrdersDetailsPO extends BasePO {

    public OrdersDetailsPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */


    @FindBy(css = "i.fa-phone + span")
    private WebElement phoneNumber;

    @FindBy(css = "i.fas fa-check")
    private WebElement orderSent;

    @FindBy(css = "i.fa-envelope + span")
    private WebElement email;

    @FindBy(css = "div.phoneNumberSpacing")
    private WebElement paymentDetails;

    @FindBy(css = "div.cart-total-wrapper div.price")
    private WebElement totalPrice;

    @FindBy(xpath = "//h3[text()='Note']//following-sibling::div/span")
    private WebElement note;

    @FindBy(css = "div.card-header")
    private WebElement orderIdHeader;

    @FindBy(css = "i.fa-exclamation + span")
    private WebElement rejectedReason;

    @FindBy(xpath = "//span[contains(text(),'Download')]")
    private WebElement downloadButton;

    @FindBy(xpath = "//span[contains(text(),'Print')]")
    private WebElement printButton;



    /**
     * Is User Email detail present ?
     *
     * @return true or false
     */
    public boolean isEmailDetailPresent()  {
        return selenium.waitInCaseElementVisible(email, 5) != null;
    }


    /**
     * Is User Phone Number present ?
     *
     * @return true or false
     */
    public boolean isContactDetailPresent()  {
        return selenium.waitInCaseElementVisible(phoneNumber, 5) != null;
    }

    /**
     * Is Special Note present ?
     *
     * @return true or false
     */
    public boolean isSpecialNotePresent()  {
        return selenium.waitInCaseElementVisible(note, 5) != null;
    }

    /**
     * Get Pickup Delivery mode
     *
     * @return Delivery mode
     */
    public String getPickUpDeliveryMode() {
        String xpathLocator = "//span[contains(text(),'Pickup')]/parent::div";
        return selenium.getText(By.xpath(xpathLocator));
    }


    /**
     * Get Serve at table Delivery mode
     *
     * @return Delivery mode
     */
    public String getServeAtTableDeliveryMode() {
        String xpathLocator = "//span[contains(text(),'Serve')]/parent::div";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Table number
     *
     * @return Delivery mode
     */
    public String getTableNumber() {
        String xpathLocator = "//i[contains(text(),'room')]/parent::span";
        return selenium.getText(By.xpath(xpathLocator));
    }


    /**
     * Get Locations
     *
     * @return Locations
     */
    public String getLocation() {
        String xpathLocator = "//i[contains(text(),'room')]/parent::div";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Pickup Delivery mode
     *
     * @return Delivery mode
     */
    public String getDeliveryAtAddressMode() {
        String xpathLocator = "//span[contains(text(),'Deliver')]/parent::div";
        return selenium.getText(By.xpath(xpathLocator));
    }



    /**
     * Get Status of Order
     *
     * @return Status of Order
     */
    public String getOrderSentDetail() {
        String xpathLocator = "//span[contains(text(),'Order Sent')]";
        return selenium.getText(By.xpath(xpathLocator));
    }


    /**
     * Get User Contact number
     *
     * @return Contact number
     */
    public String getUserContactDetail() {
        return selenium.getText(phoneNumber);
    }


    /**
     * Get email Address
     *
     * @return email Address
     */
    public String getUserEmailAddressDetail() {
        return selenium.getText(email);
    }

    /**
     * Get Payment Detail
     *
     * @return Payment Detail
     */
    public String getPaymentDetail() {
        return selenium.getText(paymentDetails);
    }

    /**
     * Get Order Offer name
     *
     * @return Order Offer name
     */

    public String getOrderItemName(int nth) {
        String cssSelector = "div.item-list > div:nth-of-type(" + nth + ") div.item";
        return selenium.getText(By.cssSelector(cssSelector));
    }

    /**
     * Get Order Quantity
     *
     * @return order Quantity
     */

    public String getOrderItemQuantity(int nth) {
        String cssSelector = "div.item-list > div:nth-of-type(" + nth + ") div.qty";
        return selenium.getText(By.cssSelector(cssSelector));
    }

    /**
     * Get Order Price
     *
     * @return order Price
     */

    public String getOrderItemPrice(int nth) {
        String cssSelector = "div.item-list > div:nth-of-type(" + nth + ") div.price";
        return selenium.getText(By.cssSelector(cssSelector));
    }

    /**
     * Get Order Total Price
     *
     * @return order Total Price
     */

    public String getOrderItemTotalPrice(int nth) {
        return selenium.getText(totalPrice);
    }

    /**
     * Get Order Note
     *
     * @return order Note
     */

    public String getOrderNote() {
        return selenium.getText(note);
    }


    /**
     * Get Order Id from Header
     *
     * @return order Id
     */
    public String getOrderIDFromHeader() {
        return selenium.getText(orderIdHeader);
    }

    /**
     * Is Order ready message present ?
     *
     * @return true or false
     * @throws InterruptedException exception
     */

    public boolean isOrderReadyMessagePresent() throws InterruptedException {
        String xpathLocator = "//span[contains(text(),'Order is ready!')]";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }


    /**
     * Get order expected message
     * @return order expected message
     */
    public String getOrderExpectedMessagePresent()  {
        String xpathLocator = "//div[contains(text(),' Order Expected')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get order rejected reason message
     * @return order rejected reason message
     * @throws InterruptedException exception
     */
    public String getOrderRejectedReasonMessage() throws InterruptedException {
        return selenium.getText(rejectedReason);
    }

    /**
     * Click on Download button
     *
     * @throws InterruptedException exception
     */
    public void clickOnDownloadButton() throws InterruptedException {
       selenium.hardWait(3);
        selenium.clickOn(downloadButton);
        selenium.hardWait(5);
    }

    /**
     * Click on Print button
     *
     * @throws InterruptedException exception
     */
    public void clickOnPrintButton() throws InterruptedException {
        selenium.clickOn(printButton);
        selenium.hardWait(5);
    }

    /**
     * Get Expected Status of Order
     *
     * @return Status of Order
     */
    public String getOrderWishDetail() {
        String xpathLocator = "//span[contains(text(),'Order Wish')]/parent::div";
        return selenium.getText(By.xpath(xpathLocator));
    }


}
