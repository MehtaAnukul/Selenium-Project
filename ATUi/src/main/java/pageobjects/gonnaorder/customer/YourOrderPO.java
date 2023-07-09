package pageobjects.gonnaorder.customer;

import dataobjects.gonnaorder.customer.CustomerOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class YourOrderPO extends BasePO {

    public YourOrderPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(css = "#totalOrderPrice div.price")
    protected WebElement totalPrice;

    @FindBy(tagName = "textarea")
    protected WebElement specialNoteTextBox;

    @FindBy(id = "table")
    private WebElement tableNumberTextBox;

    @FindBy(id = "name")
    private WebElement nameTextBox;

    @FindBy(xpath = "//input[@formcontrolname = 'street']")
    private WebElement streetAddressTextBox;

    @FindBy(css = "label[for='var-pickup-table'] span")
    private WebElement iAmSittingAtTableRadioButton;

    @FindBy(css = "label[for='var-pickup-self'] span")
    private WebElement iWillPickItUpRadioButton;

    @FindBy(css = "label[for='var-deliver-at-address'] span")
    private WebElement iWantReceiveAtAddressRadioButton;

    @FindBy(id = "zip")
    private WebElement zipCodeTextBox;

    @FindBy(id = "town")
    private WebElement townTextBox;

    @FindBy(id = "email")
    private WebElement emailTextBox;

    @FindBy(id = "phone")
    private WebElement phoneNumberTextBox;

    @FindBy(id = "payOnline")
    private WebElement payOnlineCheckBox;

    @FindBy(css = "*[name*='__privateStripeFrame']")
    private WebElement creditCardIframe;

    @FindBy(css = "*[title*='Secure iDEAL bank selection dropdown list']")
    private WebElement iDEALBankListIframe;

    @FindBy(name = "cardnumber")
    private WebElement cardNumberTextBox;

    @FindBy(name = "exp-date")
    private WebElement expDateTextBox;

    @FindBy(name = "cvc")
    private WebElement cvcTextBox;

    @FindBy(name = "postal")
    private WebElement postalTextBox;

    @FindBy(xpath = "//*[text()='Your table ']")
    private WebElement tableNumber;

    @FindBy(css = "label[for='var-dt-wish'] span")
    private WebElement iWantToSpecifyWhenToReceiveItRadioButton;

    @FindBy(css = "timepicker table tbody tr:nth-of-type(1) a.btn-link")
    private WebElement SelectEstimateHour;

    @FindBy(css = "timepicker table tbody td:nth-of-type(3) a.btn-link")
    private WebElement SelectEstimateMinute;

    @FindBy(xpath = "//div[contains(text(),'Pay online now')]")
    private WebElement payOnlineSection;

    @FindBy(css = "label[for='var-ideal'] span")
    private WebElement iDEALPaymentRadioButton;

    @FindBy(css = "label[for='var-credit-card'] span")
    private WebElement CreditCardPaymentRadioButton;

    @FindBy(css = "label[for='var-bancontact'] span")
    private WebElement banContactPaymentRadioButton;

    @FindBy(id = "ideal-bank-element")
    private WebElement iDealBankDropDown;

    @FindBy(xpath = "//h1[contains(text(),'Your order')]" )
    private WebElement yourOrderPageHeader;

    /**
     * Get nth order item name
     *
     * @param nth item index
     * @return item name
     */
    public String getOrderItemName(int nth) {
        String cssLocator = "div.item-list > div:nth-of-type(" + nth + ") div.item";
        return selenium.getText(By.cssSelector(cssLocator));
    }

    /**
     * Get nth order item price
     *
     * @param nth item index
     * @return item price
     */
    public String getOrderItemPrice(int nth) {
        String cssLocator = "div.item-list > div:nth-of-type(" + nth + ") div.price";
        return selenium.getText(By.cssSelector(cssLocator));
    }

    /**
     * Get nth order item quantity
     *
     * @param nth item index
     * @return item quantity
     */
    public String getOrderItemQuantity(int nth) {
        String cssLocator = "div.item-list > div:nth-of-type(" + nth + ") div.qty";
        return selenium.getText(By.cssSelector(cssLocator));
    }

    /**
     * Get table number
     *
     * @return item quantity
     */
    public String getTableNumber() {
        return selenium.getText(tableNumber);
    }


    /**
     * Get total price
     *
     * @return total price
     */
    public String getTotalPrice() {
        return selenium.getText(totalPrice);
    }

    /**
     * Select the radio button of I Am Sitting At Table Delivery option
     *
     * @throws InterruptedException exception
     */
    public void selectIAmSittingAtTableDeliveryOption() throws InterruptedException {
        selenium.click(iAmSittingAtTableRadioButton);
        selenium.hardWait(3); // As at times, Footer doesn't show price.
    }

    /**
     * Select the radio button of i Want To Specify When To Receive It option
     *
     * @throws InterruptedException exception
     */
    public void selectIWantToSpecifyWhenToReceiveItRadioButton() throws InterruptedException {
        selenium.pageScrollInView(iWantToSpecifyWhenToReceiveItRadioButton);
        selenium.clickOn(iWantToSpecifyWhenToReceiveItRadioButton);
        selenium.hardWait(3); // As at times, Footer doesn't show price.
    }

    /**
     * Is I Am Sitting At Table Delivery Option present ?
     *
     * @return true or false
     */
    public boolean isIAmSittingAtTableDeliveryOptionPresent()  {
        return selenium.waitInCaseElementVisible(iAmSittingAtTableRadioButton, 5) != null;
    }


    /**
     * Select the radio button of I Will Pick It Delivery option
     *
     * @throws InterruptedException exception
     */
    public void selectIWillPickItUpDeliveryOption() throws InterruptedException {
        selenium.click(iWillPickItUpRadioButton);
        selenium.hardWait(3); // As at times, Footer doesn't show price.
    }

    /**
     * Select the check box of payment option
     *
     * @throws InterruptedException exception
     */
    public void selectPayOnlineCheckBox() throws InterruptedException {
        selenium.pageScrollInView(payOnlineCheckBox);
        selenium.click(payOnlineCheckBox);
        selenium.hardWait(3); // As at times, Footer doesn't show price.
    }

    /**
     * Is I Will Pick It Up Delivery Option Present ?
     *
     * @return true or false
     */
    public boolean isIWillPickItUpDeliveryOptionPresent() {
        return selenium.waitInCaseElementVisible(iWillPickItUpRadioButton, 5) != null;
    }

    /**
     * Select the radio button of I Want Receive At Address option
     *
     * @throws InterruptedException exception
     */
    public void selectIWantReceiveAtAddressDeliveryOption() throws InterruptedException {
        selenium.click(iWantReceiveAtAddressRadioButton);
        selenium.hardWait(3); // As at times, Footer doesn't show price.
    }

    /**
     * Is I Want Receive At Address Delivery Option Present ?
     *
     * @return true or false
     */
    public boolean isIWantReceiveAtAddressDeliveryOptionPresent(){
        return selenium.waitInCaseElementVisible(iWantReceiveAtAddressRadioButton, 5) != null;
    }

    /**
     * Fill customer details form
     *
     * @param customerInfo Customer object
     * @throws InterruptedException exception
     */
    public void fillCustomerDetails(CustomerOrder customerInfo, Boolean isDeliveryAtAddress) throws InterruptedException {
        selenium.enterText(specialNoteTextBox, customerInfo.getSpecialNote(), false);

        if (!customerInfo.getTable().isEmpty()) {
            selenium.enterText(tableNumberTextBox, customerInfo.getTable(), false);
            selenium.enterText(nameTextBox, customerInfo.getName(), false);
            selenium.enterText(nameTextBox, Keys.TAB);
        } else {
            if (isDeliveryAtAddress) {
                selenium.enterText(nameTextBox, customerInfo.getName(), false);
                selenium.enterText(streetAddressTextBox, customerInfo.getStreetAddress(), false);
                selenium.enterText(townTextBox, customerInfo.getTown(), false);
                selenium.enterText(zipCodeTextBox, customerInfo.getZipCode(), false);
                selenium.enterText(emailTextBox, customerInfo.getEmail(), false);
                selenium.enterText(phoneNumberTextBox, customerInfo.getPhone(), false);
                selenium.enterText(phoneNumberTextBox, Keys.TAB);
            } else {
                selenium.enterText(nameTextBox, customerInfo.getName(), false);
                selenium.enterText(emailTextBox, customerInfo.getEmail(), false);
                selenium.enterText(phoneNumberTextBox, customerInfo.getPhone(), false);
                selenium.enterText(phoneNumberTextBox, Keys.TAB);
                selenium.enterText(phoneNumberTextBox, Keys.TAB);
            }
        }
        selenium.hardWait(5); // As at times, Footer doesn't show price.
    }

    /**
     * Fill customer payment details form
     *
     * @param customerInfo Customer object
     * @throws InterruptedException exception
     */
    public void fillCustomerPaymentDetails(CustomerOrder customerInfo) throws InterruptedException {
        selenium.switchToIframe(creditCardIframe);
        selenium.enterTextCharacterByCharacter(cardNumberTextBox, customerInfo.getCardNumber(), false);
        selenium.enterTextCharacterByCharacter(expDateTextBox, customerInfo.getExpDate(), false);
        selenium.enterTextCharacterByCharacter(cvcTextBox, customerInfo.getCvc(), false);
        selenium.switchToMainIframe();
        selenium.hardWait(3); // As at times, Footer doesn't show price.
    }


    /**
     * Fill customer details form
     *
     * @param tableNumber Customer object
     * @throws InterruptedException exception
     */
    public void AddTableNumberWithoutOptionalData(CustomerOrder tableNumber) throws InterruptedException {
            selenium.enterText(tableNumberTextBox, tableNumber.getTable(), false);
            selenium.hardWait(5);
    }


    /**
     * Get Estimate hour and minute from Text boxes
     *
     */
    public void SelectEstimateTime() throws InterruptedException {
        selenium.clickOn(SelectEstimateHour);
        selenium.clickOn(SelectEstimateMinute);

    }


    /**
     * Is Pay Online Section Present ?
     *
     * @return true or false
     */
    public boolean isPayOnlineTextPresent(){
        return selenium.waitInCaseElementVisible(payOnlineSection, 5) != null;
    }


    /**
     * Get iDEAL Payment method name
     *
     */
    public String getiDEALPaymentText() {
        return selenium.getText(iDEALPaymentRadioButton);
    }

    /**
     * Is iDeal bank dropdown Section Present ?
     *
     * @return true or false
     */
    public boolean isiDealBankDropdownPresent(){
        return selenium.waitInCaseElementVisible(iDealBankDropDown, 5) != null;
    }

    /**
     * Select the radio button of iDEAL Payment method
     *
     * @throws InterruptedException exception
     */
    public void selectIDEALPaymentOption() throws InterruptedException {
        selenium.pageScrollInView(iDEALPaymentRadioButton);
        selenium.click(iDEALPaymentRadioButton);
        selenium.hardWait(3);
    }

    /**
     * Select the Bank Name from iDEAL Bank dropdown
     *
     * @throws InterruptedException exception
     */
    public void selectBankNameFromDropDown(String BankName) throws InterruptedException {
        selenium.click(iDealBankDropDown);
        selenium.hardWait(3);
        selenium.switchToIframe(iDEALBankListIframe);
        String bankName = "//div[@class='IdealBankSelectList']//div//div[contains(text(), '" + BankName + "')]/parent::li";
        selenium.waitTillElementIsClickable(By.xpath(bankName)).click();
        selenium.switchToMainIframe();
    }

    /**
     * Get Credit Card Payment method name
     *
     */
    public String getCreditCardPaymentText() {
        return selenium.getText(CreditCardPaymentRadioButton);
    }

    /**
     * Select the radio button of iDEAL Payment method
     *
     * @throws InterruptedException exception
     */
    public void selectCreditCardPaymentOption() throws InterruptedException {
        selenium.pageScrollInView(CreditCardPaymentRadioButton);
        selenium.click(CreditCardPaymentRadioButton);
        selenium.hardWait(3);
    }

    /**
     * Get Ban Contact Payment method name
     *
     */
    public String getBanContactPaymentText() {
        return selenium.getText(banContactPaymentRadioButton);
    }


    /**
     * Select the radio button of Ban Contact Payment method
     *
     * @throws InterruptedException exception
     */
    public void selectBanContactPaymentOption() throws InterruptedException {
        selenium.pageScrollInView(banContactPaymentRadioButton);
        selenium.click(banContactPaymentRadioButton);
        selenium.hardWait(3);
    }

    /**
     * Get Your Order Page Header
     *
     */
    public String getYourOrderPageHeaderText() {
        return selenium.getText(yourOrderPageHeader);
    }

}