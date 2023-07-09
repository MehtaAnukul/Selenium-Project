package pageobjects.gonnaorder.customer.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class PaymentPagePO extends BasePO {

    public PaymentPagePO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    //Stripe Payment Test Page elements

    @FindBy(xpath = "//h1[@class='common-SectionTitle']")
    private WebElement iDealPaymentPageHeader;

    @FindBy(xpath = "//button[contains(text(),'Authorize Test Payment')]")
    private WebElement authorizedPaymentButton;

    @FindBy(xpath = "//button[contains(text(),'Fail Test Payment')]")
    private WebElement failedPaymentButton;

    //PayPal Payment Page elements

    @FindBy(xpath = "//div[@role='heading']")
    private WebElement payPalPaymentHeading;

    @FindBy(xpath = "//div[@class='css-1oobd0h']")
    private WebElement payPalStoreLink;

    @FindBy(xpath = "//div[@class='ppaf-currency-symbol']")
    private WebElement payPalCurrencySymbol;

    @FindBy(xpath = "//input[@name='amount']")
    private WebElement payPalTotalAmount;

    //Credit card Payment page elements

    @FindBy(css = "*[name*='__privateStripeFrame']")
    private WebElement creditCardPaymentIFrame2;

    @FindBy(css = "*[name*='__stripeJSChallengeFrame']")
    private WebElement creditCardPaymentIFrame3;

    @FindBy(className = "FullscreenFrame")
    private WebElement creditCardPaymentPage;

    @FindBy(xpath = "//h1[@class='common-SectionTitle']")
    private WebElement creditCardPaymentPageHeader;

    @FindBy(xpath = "//h3[@class='common-BodyTitle']")
    private WebElement creditCardPaymentHeading;

    @FindBy(xpath = "//button[contains(text(),'Complete authentication')]")
    private WebElement authorizedCreditCardPaymentButton;

    @FindBy(xpath = "//button[contains(text(),'Fail authentication')]")
    private WebElement failedCreditCardPaymentButton;


    /**
     * Verify iDEAL test Page Header
     * @throws InterruptedException exception
     */

    public String StripePaymentHeaderText() throws InterruptedException {
        return selenium.getText(iDealPaymentPageHeader);
    }

    /**
     * Click on Authorized Payment option from Stripe test payment page
     * @throws InterruptedException exception
     */

    public void clickOnStripePaymentSuccessButton() throws InterruptedException {
        selenium.clickOn(authorizedPaymentButton);
    }

    /**
     * Click on Failed Payment option from Stripe test payment page
     * @throws InterruptedException exception
     */

    public void clickOnStripePaymentFailedButton() throws InterruptedException {
        selenium.clickOn(failedPaymentButton);
    }

    /**
     * Verify PayPal Page Heading
     */
    public String getPayPalPaymentHeaderText()  {
        return selenium.getText(payPalPaymentHeading);
    }

    /**
     * Verify PayPal Store Link text
     */
    public String getPayPalStoreLinkText() {
        return selenium.getText(payPalStoreLink);
    }

    /**
     * Verify PayPal Currency Symbol
     */
    public String getPayPalCurrencySymbol() {
        return selenium.getText(payPalCurrencySymbol);
    }

    /**
     * Verify PayPal Total Amount
     */
    public String getPayPalTotalAmount() {
        return selenium.getElementAttributeValue(payPalTotalAmount,"value");
    }


    /**
     * Verify Credit Card Payment Header Text
     */
    public String getCreditCardPaymentHeaderText()  {
        selenium.switchToIframe(creditCardPaymentIFrame2);
        selenium.switchToIframe(creditCardPaymentIFrame3);
        selenium.switchToIframe(creditCardPaymentPage);
        return selenium.getText(creditCardPaymentPageHeader);
    }

    /**
     * Verify Credit Card Payment Heading Text
     */
    public String getCreditCardPaymentHeadingText() {
        return selenium.getText(creditCardPaymentHeading);
    }


    /**
     * Click on Authorized Payment option from Credit Card test payment page
     * @throws InterruptedException exception
     */

    public void clickOnCreditCardPaymentSuccessButton() throws InterruptedException {
        selenium.hardWait(3);
        selenium.clickOn(authorizedCreditCardPaymentButton);
        selenium.switchToMainIframe();
        selenium.hardWait(3);
    }

    /**
     * Click on Failed Payment option from Credit Card test payment page
     * @throws InterruptedException exception
     */

    public void clickOnCreditCardPaymentFailedButton() throws InterruptedException {
        selenium.hardWait(3);
        selenium.clickOn(failedCreditCardPaymentButton);
        selenium.switchToMainIframe();
        selenium.hardWait(3);
    }

}