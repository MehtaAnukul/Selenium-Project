package pageobjects.gonnaorder.admin.store.settings;

import dataobjects.gonnaorder.admin.PayPalAndOtherPayment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;
import pageobjects.gonnaorder.components.Toggle;

public class PaymentProvidersPO extends BasePO {

    public PaymentProvidersPO(WebDriver driver) {
        super(driver);
    }

    Toggle toggle = new Toggle(driver);

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//input[@id='enableStripePaymentcreditCard']//following-sibling::span")
    private WebElement creditCardSpan;

    @FindBy(xpath = "//input[@id='enableStripePaymentcreditCard']")
    private WebElement creditCardInput;

    @FindBy(xpath = "//input[@id='enableStripePaymentIdeal']//following-sibling::span")
    private WebElement iDEALSpan;

    @FindBy(xpath = "//input[@id='enableStripePaymentIdeal']")
    private WebElement iDEALInput;

    @FindBy(xpath = "//input[@id='enableStripePaymentBancontact']//following-sibling::span")
    private WebElement bancontactSpan;

    @FindBy(xpath = "//input[@id='enableStripePaymentBancontact']")
    private WebElement bancontactInput;

    @FindBy(xpath = "//input[@id='POST_ORDER_PAYMENT_LINK_PAYPALME_ENABLED']//following-sibling::span")
    private WebElement payPalSpan;

    @FindBy(xpath = "//input[@id='POST_ORDER_PAYMENT_LINK_PAYPALME_ENABLED']")
    private WebElement payPalInput;

    @FindBy(xpath = "//input[@id='POST_ORDER_PAYMENT_LINK_PAYPALME_URL']")
    private WebElement payPalURLInput;

    /**
     * Enable/Disable the toggle of credit card option
     *
     * @param select true if you want to enable it, false if you want to disable it
     * @throws InterruptedException exception
     */
    public void enableDisableCreditCardOption(boolean select) throws InterruptedException {

        toggle.toggle(creditCardSpan, creditCardInput, select);

    }

    /**
     * Enable/Disable the toggle of iDEAL option
     *
     * @param select true if you want to enable it, false if you want to disable it
     * @throws InterruptedException exception
     */
    public void enableDisableIDEALOption(boolean select) throws InterruptedException {

        toggle.toggle(iDEALSpan, iDEALInput, select);

    }

    /**
     * Enable/Disable the toggle of Bancontact option
     *
     * @param select true if you want to enable it, false if you want to disable it
     * @throws InterruptedException exception
     */
    public void enableDisableBancontactOption(boolean select) throws InterruptedException {

        toggle.toggle(bancontactSpan, bancontactInput, select);

    }

    /**
     * Enable/Disable the toggle of All Payment Provider option
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void enableDisableAllPaymentProviderOptions(boolean select) throws InterruptedException {

        toggle.toggle(creditCardSpan, creditCardInput, select);
        toggle.toggle(iDEALSpan, iDEALInput, select);
        toggle.toggle(bancontactSpan, bancontactInput, select);
    }

    /**
     * Enable/Disable the toggle of credit card and iDEAL option
     *
     * @param select true if you want to enable it, false if you want to disable it
     * @throws InterruptedException exception
     */
    public void enableDisableCreditCardAndiDealOption(boolean select) throws InterruptedException {

        toggle.toggle(creditCardSpan, creditCardInput, select);
        toggle.toggle(iDEALSpan, iDEALInput, select);

    }


    /**
     * Enable/Disable the toggle of iDEAL and Ban Contact option
     *
     * @param select true if you want to enable it, false if you want to disable it
     * @throws InterruptedException exception
     */
    public void enableDisableiDealAndBanContactOption(boolean select) throws InterruptedException {

        toggle.toggle(bancontactSpan, bancontactInput, select);
        toggle.toggle(iDEALSpan, iDEALInput, select);

    }


    /**
     * Enable/Disable the toggle of credit card and Bancontact option
     *
     * @param select true if you want to enable it, false if you want to disable it
     * @throws InterruptedException exception
     */
    public void enableDisableCreditCardAndBancontactOption(boolean select) throws InterruptedException {

        toggle.toggle(creditCardSpan, creditCardInput, select);
        toggle.toggle(bancontactSpan, bancontactInput, select);

    }

    /**
     * Fill out PayPal.Me link detail
     * @param payPalMeLinkInfo PayPal payment link object
     * @throws InterruptedException exception
     */
    public void fillPayPalMELink(PayPalAndOtherPayment payPalMeLinkInfo) throws InterruptedException
    {
        selenium.clearTextboxUsingKeys(payPalURLInput);
        selenium.enterText(payPalURLInput, payPalMeLinkInfo.getPayPalMeLink(),false);
    }

    /***
     * Verify 'PayPal.Me' toggle is enabled
     * @throws InterruptedException
     */
    public boolean verifyPayPalMEToggleIsEnabled() throws InterruptedException {
        return selenium.isElementEnabled(payPalSpan);
    }

}

