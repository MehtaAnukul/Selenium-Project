package pageobjects.gonnaorder.admin.store.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;


public class CloseOrderPO extends BasePO {

    public CloseOrderPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */


    @FindBy(xpath = "//span[contains(text(),'Close')]")
    private WebElement closeButton;

    @FindBy(css = "label[for='accept-order'] span")
    private WebElement acceptOrderRadioButton;

    @FindBy(css = "label[for='accept-order-mark-ready'] span")
    private WebElement acceptOrderAndMarkReadyRadioButton;

    @FindBy(css = "label[for='accept-order-estimated-time'] span")
    private WebElement acceptOrderAndEstimatedTimeRadioButton;

    @FindBy(css = "input.input-field")
    private WebElement estimatedTimeTextBox;

    @FindBy(id = "btnSubmit")
    private WebElement closeOrderButton;

    @FindBy(xpath = "//span[text()='minutes']/following::input[1]")
    private WebElement hourTime;

    @FindBy(xpath = "//span[text()='minutes']/following::input[2]")
    private WebElement minuteTime;


    /**
     * Click on Close button
     *
     * @throws InterruptedException exception
     */
    public void clickOnCloseButton() throws InterruptedException {
        selenium.clickOn(closeButton);
    }

    /**
     * Select the radio button of Accept order option
     *
     * @throws InterruptedException exception
     */
    public void selectAcceptOrderRadioButton() throws InterruptedException {
        selenium.clickOn(acceptOrderRadioButton);
    }

    /**
     * Select the radio button of Accept order and mark as ready option
     *
     * @throws InterruptedException exception
     */
    public void selectAcceptOrderAndMarkAsReadyRadioButton() throws InterruptedException {
        selenium.clickOn(acceptOrderAndMarkReadyRadioButton);
    }

    /**
     * Select the radio button of Accept order and estimated time option
     *
     * @throws InterruptedException exception
     */
    public void selectAcceptOrderAndEstimatedTimeRadioButton() throws InterruptedException {
        selenium.clickOn(acceptOrderAndEstimatedTimeRadioButton);
    }


    /**
     * Click on Close order button
     *
     * @throws InterruptedException exception
     */
    public void clickOnCloseOrderButton() throws InterruptedException {
        selenium.clickOn(closeOrderButton);
    }

    /**
     * Get hour time
     * @return hour time
     * @throws InterruptedException exception
     */
    public String getHourTime() throws InterruptedException {
        return selenium.getText(hourTime);
    }

    /**
     * Get minute time
     * @return minute time
     * @throws InterruptedException exception
     */
    public String getMinuteTime() throws InterruptedException {
        return selenium.getText(minuteTime);
    }

    /**
     * Enter estimated time
     *
     * @throws InterruptedException exception
     */
    public void fillExpectedTime(int addMinute) throws InterruptedException {
        selenium.hardWait(5);
        selenium.clickOn(estimatedTimeTextBox);
        selenium.clearTextboxUsingKeys(estimatedTimeTextBox);
        selenium.enterText(estimatedTimeTextBox, Integer.toString(addMinute), false);
    }
}

