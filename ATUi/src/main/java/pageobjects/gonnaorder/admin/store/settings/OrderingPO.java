package pageobjects.gonnaorder.admin.store.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;
import pageobjects.gonnaorder.components.Toggle;

public class OrderingPO extends BasePO {

    public OrderingPO(WebDriver driver) {
        super(driver);
    }

    Toggle toggle = new Toggle(driver);
    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//input[@id='DELIVERY_NO_LOCATION']//following-sibling::span")
    private WebElement pickupSpan;

    @FindBy(xpath = "//input[@id='DELIVERY_NO_LOCATION']")
    private WebElement pickupInput;

    @FindBy(xpath = "//input[@id='DELIVERY_ADDRESS']//following-sibling::span")
    private WebElement deliveryAtAddressSpan;

    @FindBy(xpath = "//input[@id='DELIVERY_ADDRESS']")
    private WebElement deliveryAtAddressInput;

    @FindBy(xpath = "//input[@id='DELIVERY_IN_STORE_LOCATION']//following-sibling::span")
    private WebElement serveAtTableSpan;

    @FindBy(xpath = "//input[@id='DELIVERY_IN_STORE_LOCATION']")
    private WebElement serveAtTableInput;

    @FindBy(xpath = "//input[@id='ENABLE_ORDERING']//following-sibling::span")
    private WebElement orderingToggleSpan;

    @FindBy(xpath = "//input[@id='ENABLE_ORDERING']")
    private WebElement orderingToggleInput;

    @FindBy(id = "PAYMENT_OPTION_DISABLED")
    private WebElement paymentDisabledRadioButton;

    @FindBy(id = "PAYMENT_OPTION_OPTIONAL")
    private WebElement paymentOptionalRadioButton;

    @FindBy(id = "PAYMENT_OPTION_MANDATORY")
    private WebElement paymentMandatoryRadioButton;

    @FindBy(xpath = "//input[@id='BASKET_ENABLED']//following-sibling::span")
    private WebElement ShoppingBasketToggleSpan;

    @FindBy(xpath = "//input[@id='BASKET_ENABLED']")
    private WebElement ShoppingBasketToggleInput;



    /**
     * Select/De-select the Shopping Basket toggle
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectShoppingBasketToggle(boolean select) throws InterruptedException {

        toggle.toggle(ShoppingBasketToggleSpan, ShoppingBasketToggleInput, select);

    }


    /**
     * Select/De-select the checkbox of Pickup option
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectPickupDeliveryOption(boolean select) throws InterruptedException {

        checkbox(pickupSpan, pickupInput, select);

    }


    /**
     * Select/De-select the checkbox of Delivery At Address option
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectDeliveryAtAddressDeliveryOption(boolean select) throws InterruptedException {

        checkbox(deliveryAtAddressSpan, deliveryAtAddressInput, select);

    }


    /**
     * Select/De-select the checkbox of Serve At Table option
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectServeAtTableDeliveryOption(boolean select) throws InterruptedException {

        checkbox(serveAtTableSpan, serveAtTableInput, select);

    }

    /**
     * Select/De-select the checkboxes of Delivery At Address And Pickup options
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectDeliveryAtAddressAndPickupDeliveryOptions(boolean select) throws InterruptedException {

        checkbox(deliveryAtAddressSpan, deliveryAtAddressInput, select);
        checkbox(pickupSpan, pickupInput, select);

    }

    /**
     * Select/De-select the checkboxes of Delivery At Address And Pickup options
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectServeAtTableAndPickupDeliveryOptions(boolean select) throws InterruptedException {

        checkbox(serveAtTableSpan, serveAtTableInput, select);
        checkbox(pickupSpan, pickupInput, select);

    }

    /**
     * Select/De-select the checkboxes of Delivery At Address And Serve At Table options
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectServeAtTableAndDeliveryAtAddressDeliveryOptions(boolean select) throws InterruptedException {

        checkbox(serveAtTableSpan, serveAtTableInput, select);
        checkbox(deliveryAtAddressSpan, deliveryAtAddressInput, select);

    }

    /**
     * Select/De-select the checkboxes of Delivery At Address And Pickup options
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectAllDeliveryOptions(boolean select) throws InterruptedException {

        checkbox(serveAtTableSpan, serveAtTableInput, select);
        checkbox(deliveryAtAddressSpan, deliveryAtAddressInput, select);
        checkbox(pickupSpan, pickupInput, select);
    }

    /**
     * Select/De-select the ordering toggle
     *
     * @param select true if you want to select it, false if you want to de-select it
     * @throws InterruptedException exception
     */
    public void selectDeselectOrderingToggle(boolean select) throws InterruptedException {

        toggle.toggle(orderingToggleSpan, orderingToggleInput, select);

    }


    /**
     * Select the radio button of Payment Disabled option
     *
     * @throws InterruptedException exception
     */
    public void selectPaymentDisabledOption() throws InterruptedException {
        selenium.click(paymentDisabledRadioButton);
    }

    /**
     * Select the radio button of Payment Optional option
     *
     * @throws InterruptedException exception
     */
    public void selectPaymentOptionalOption() throws InterruptedException {
        selenium.click(paymentOptionalRadioButton);
    }

    /**
     * Select the radio button of Payment Mandatory option
     *
     * @throws InterruptedException exception
     */
    public void selectPaymentMandatoryOption() throws InterruptedException {
        selenium.click(paymentMandatoryRadioButton);
    }


    //Checkbox handling

    /**
     * Custom method only for Orders page to handle checkbox selection
     *
     * @param checkboxSpan  checkbox span WebElement object
     * @param checkboxInput checkbox input WebElement object
     * @param select true / false based on checkbox selection need
     * @throws InterruptedException exception
     */
    private void checkbox(WebElement checkboxSpan, WebElement checkboxInput, boolean select) throws InterruptedException {
        if (select) {
            if (!selenium.isCheckboxSelected(checkboxInput)) {
                selenium.click(checkboxSpan);
            }
        } else {
            if (selenium.isCheckboxSelected(checkboxInput)) {
                selenium.click(checkboxSpan);
            }
        }

        selenium.hardWait(5);
    }



    /**
     * Click on Ordering button
     * @throws InterruptedException exception
     */
    public Boolean IsShoppingBasketToggleEnabled() throws InterruptedException {
        String xpathLocator = "//input[@id='BASKET_ENABLED']//following-sibling::span";
        return selenium.isElementPresent(By.xpath(xpathLocator));

    }

}
