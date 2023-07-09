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

public class AddOptionPO {

    public AddOptionPO(WebDriver driver) {
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
    private WebElement optionNameTextBox;

    @FindBy(id = "shortDesc")
    private WebElement shortDescTextBox;

    @FindBy(id = "priceVal")
    private WebElement priceValTextBox;

    @FindBy(id = "priceDecimalVal")
    private WebElement priceDecimalValTextBox;

    @FindBy(className = "mat-slide-toggle-bar")
    private WebElement sellableToggle;

    @FindBy(id = "availability")
    private WebElement availabilityDropdown;

    private By saveOfferButton = By.xpath("//button[text()='Save']");

    private By deleteLink = By.xpath("//a[text()='Delete']");

    private By okButton = By.xpath("//button[text()='Ok']");



    /**
     * Fill details of option form and click on Save button
     *
     * @param optionInfo option object
     * @throws InterruptedException exception
     */
    public void fillOptionDetail(Offer optionInfo) throws InterruptedException {
        selenium.enterText(optionNameTextBox, optionInfo.getName(), false);
        selenium.enterText(shortDescTextBox, optionInfo.getShortDescription(), false);
        selenium.enterText(priceValTextBox, optionInfo.getPriceDecimal(), false);
        selenium.enterText(priceDecimalValTextBox, optionInfo.getPriceFloat(), false);
        if (!optionInfo.isSellable()) {
            selenium.javascriptClickOn(sellableToggle);
        }
        selenium.click(saveOfferButton);
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
