package pageobjects.gonnaorder.admin.store.catalog;

import dataobjects.gonnaorder.admin.Offer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.Constants;
import utilities.SeleniumHelpers;

public class AddOptionGroupPO {

    public AddOptionGroupPO(WebDriver driver) {
        this.driver = driver;
        this.selenium = new SeleniumHelpers(driver);
        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }

    WebDriver driver;
    SeleniumHelpers selenium;

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(id = "offerName")
    private WebElement optionGroupNameTextBox;

    @FindBy(id = "MIN_1_MAX_1")
    private WebElement exactlyOneOptionRadioButton;

    @FindBy(id = "MIN_0_MAX_N")
    private WebElement anyNumberOptionRadioButton;

    @FindBy(id = "MIN_0_MAX_1")
    private WebElement atMostOneOptionRadioButton;

    private By saveOptionGroupButton = By.xpath("//button[text()='Save']");

    private By deleteLink = By.xpath("//a[text()='Delete']");

    private By okButton = By.xpath("//button[text()='Ok']");




    /**
     * Fill the form with exactly ope option
     * * @param offerInfo Offer object
     *
     * @throws InterruptedException exception
     */
    public void fillTheForm(Offer offerInfo) throws InterruptedException {
        selenium.clearTextboxUsingKeys(optionGroupNameTextBox);
        selenium.enterText(optionGroupNameTextBox, offerInfo.getOptionGroupName(), false);
        selenium.hardWait(3);

        switch (offerInfo.getOptionGroupType()) {

            case ExactlyOne:
                selenium.click(exactlyOneOptionRadioButton);
                break;

                case AtMostOne:
                selenium.click(atMostOneOptionRadioButton);
                break;

            case AnyNumber:
                selenium.click(anyNumberOptionRadioButton);
                break;
        }

        selenium.click(saveOptionGroupButton);
        selenium.hardWait(3);

    }

    /**
     * Click on Delete link
     * @throws InterruptedException exception
     */
    public void clickOnDeleteLink() throws InterruptedException
    {
        selenium.click(deleteLink);
        selenium.click(okButton);
        selenium.waitTillElementsCountIsLessThan(okButton, 1);
    }


}
