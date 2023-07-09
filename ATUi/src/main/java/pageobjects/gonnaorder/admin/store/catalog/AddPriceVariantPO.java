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

public class AddPriceVariantPO {

    WebDriver driver;
    SeleniumHelpers selenium;


    public AddPriceVariantPO(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */



    @FindBy(id = "priceDesc")
    private WebElement priceVariantDescTextBox;

    @FindBy(id = "priceVal")
    private WebElement priceVariantPriceValTextBox;

    @FindBy(id = "priceDecimalVal")
    private WebElement priceVariantPriceDecimalValTextBox;

    private By savePriceVariantButton = By.xpath("//button[text()='Save']");


    @FindBy(xpath = "//label[contains(text(),'No Discount')]")
    private WebElement discountRadioButton;

    @FindBy(xpath = "//label[contains(text(),'Monetary')]")
    private WebElement monitoryDiscountRadioButton;

    @FindBy(xpath = "//label[@for='discount']/ancestor::Div[contains(@class,'row')]/following-sibling::div//input[@id='priceVal']")
    private WebElement monitoryPriceValTextBox;

    @FindBy(xpath = "//label[@for='discount']/ancestor::Div[contains(@class,'row')]/following-sibling::div//input[@id='priceDecimalVal']")
    private WebElement monitoryDecimalValTextBox;

    @FindBy(xpath = "//label[@for='percentile']")
    private WebElement percentileDiscountRadioButton;

    @FindBy(xpath= "//select[@formcontrolname='discount']")
    private WebElement percentileDiscountDropDown;

    @FindBy(className = "mat-slide-toggle-bar")
    private WebElement sellableToggle;

    private By deleteLink = By.xpath("//a[text()='Delete']");

    private By okButton = By.xpath("//button[text()='Ok']");


    /**
     * Fill details of Price Variant form and click on Save button
     * @param  priceVariantInfo object
     * @throws InterruptedException exception
     */

    public void fillPriceVariantDetail(Offer priceVariantInfo) throws InterruptedException
    {
        selenium.clearTextboxUsingKeys(priceVariantDescTextBox);
        selenium.enterText(priceVariantDescTextBox, priceVariantInfo.getPriceDescription(), false);

        selenium.clearTextboxUsingKeys(priceVariantPriceValTextBox);
        selenium.enterText(priceVariantPriceValTextBox, priceVariantInfo.getPriceDecimal(), false);

        selenium.clearTextboxUsingKeys(priceVariantPriceDecimalValTextBox);
        selenium.enterText(priceVariantPriceDecimalValTextBox, priceVariantInfo.getPriceFloat(), false);
        switch (priceVariantInfo.getDiscountType()){
            case None:
                if(!discountRadioButton.isSelected())
                {
                    selenium.clickOn(discountRadioButton);
                    selenium.hardWait(5);

                }
                break;
            case Monetory:
                selenium.clickOn(monitoryDiscountRadioButton);
                selenium.clearTextboxUsingKeys(monitoryPriceValTextBox);
                selenium.enterText(monitoryPriceValTextBox, priceVariantInfo.getMonitoryDecimalDiscount(), false);
                selenium.clearTextboxUsingKeys(monitoryDecimalValTextBox);
                selenium.enterText(monitoryDecimalValTextBox, priceVariantInfo.getMonitoryFloatDiscount(), false);
                break;

            case Percentile:
                selenium.clickOn(percentileDiscountRadioButton);
                selenium.clickOn(percentileDiscountDropDown);
                selenium.selectDropdownValueByText(percentileDiscountDropDown, priceVariantInfo.getPercentileDiscountValue());
                break;
        }
        if(!priceVariantInfo.isSellable())
        {
            selenium.javascriptClickOn(sellableToggle);
        }
        selenium.click(savePriceVariantButton);
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
