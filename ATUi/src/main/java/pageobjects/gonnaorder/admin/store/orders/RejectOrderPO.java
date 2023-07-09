package pageobjects.gonnaorder.admin.store.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;


public class RejectOrderPO extends BasePO {

    public RejectOrderPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */


    @FindBy(xpath = "//span[contains(text(),'Reject')]")
    private WebElement rejectButton;

    @FindBy(css = "label[for='reject-without-reason'] span")
    private WebElement rejectWithoutReasonRadioButton;

    @FindBy(css = "label[for='reject-with-reason'] span")
    private WebElement rejectWithReasonRadioButton;

    @FindBy(xpath = "//button[text()='Reject']")
    private WebElement rejectOrderButton;

    @FindBy(className = "col-md-9")
    private WebElement rejectionReasonTextBox;


    /**
     * Click on Reject button
     *
     * @throws InterruptedException exception
     */
    public void clickOnRejectButton() throws InterruptedException {
        selenium.clickOn(rejectButton);
    }

    /**
     * Select the radio button of Reject without reason option
     *
     * @throws InterruptedException exception
     */
    public void selectRejectWithoutReasonRadioButton() throws InterruptedException {
        selenium.clickOn(rejectWithoutReasonRadioButton);
    }


    /**
     * Select the radio button of Reject with reason option
     *
     * @throws InterruptedException exception
     */
    public void selectRejectWithReasonRadioButton() throws InterruptedException {
        selenium.clickOn(rejectWithReasonRadioButton);
    }


    /**
     * Click on Reject order button
     *
     * @throws InterruptedException exception
     */
    public void clickOnRejectOrderButton() throws InterruptedException {
        selenium.clickOn(rejectOrderButton);
    }

    /**
     * Enter Rejected reason
     *
     * @throws InterruptedException exception
     */
    public void fillRejectedReason(String rejectedReason) throws InterruptedException {
        selenium.clickOn(rejectionReasonTextBox);
        selenium.enterText(rejectionReasonTextBox, rejectedReason, false);
    }
}

