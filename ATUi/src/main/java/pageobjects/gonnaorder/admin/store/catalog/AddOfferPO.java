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

import java.util.ArrayList;
import java.util.List;

public class AddOfferPO
{

    public AddOfferPO(WebDriver driver)
    {
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
    private WebElement offerNameTextbox;

    @FindBy(id = "shortDesc")
    private WebElement shortDescTextbox;

    @FindBy(id = "longDesc")
    private WebElement longDescTextbox;

    @FindBy(id = "categoryId")
    private WebElement categoryIdDropdown;

    @FindBy(id = "priceVal")
    private WebElement priceValTextbox;

    @FindBy(id = "priceDecimalVal")
    private WebElement priceDecimalValTextbox;

    @FindBy(id = "priceDesc")
    private WebElement priceDescTextbox;

    @FindBy(xpath = "//label[contains(text(),'No Discount')]")
    private WebElement discountRadioButton;

    @FindBy(xpath = "//label[contains(text(),'Monetary')]")
    private WebElement monitoryDiscountRadioButton;

    @FindBy(xpath = "//label[@for='discount']/ancestor::Div[contains(@class,'row')]/following-sibling::div//input[@id='priceVal']")
    private WebElement monitoryPriceValTextBox;

    @FindBy(xpath = "//label[@for='discount']/ancestor::Div[contains(@class,'row')]/following-sibling::div//input[@id='priceDecimalVal']")
    private WebElement monitoryDecimalValTextBox;

    @FindBy(xpath = "//label[contains(text(),'Percentage')]")
    private WebElement percentileDiscountRadioButton;

    @FindBy(xpath= "//select[@placeholder='admin.store.discount.discount']")
    private WebElement percentileDiscountDropDown;

    @FindBy(className = "mat-slide-toggle-bar")
    private WebElement sellableToggle;

    @FindBy(id = "availability")
    private WebElement availabilityDropdown;

    private By saveOfferButton = By.xpath("//button[text()='Save']");

    private By deleteLink = By.xpath("//a[text()='Delete']");

    private By okButton = By.xpath("//button[text()='Ok']");

    //Offer Price Variant
    @FindBy(xpath = "//span[text()='Create Price Variant']")
    private WebElement addPriceVariantLink;

    private By OfferPriceVariantList = By.xpath("//div[contains(@class,'variantOffers')]//span");

    private By addOptionGroupLink = By.xpath("//a[text()='Add Option Group ']");


    /**
     * Fill details of offer form and click on Save button
     * @param offerInfo Offer object
     * @throws InterruptedException exception
     */
    public void fillOfferDetail(Offer offerInfo) throws InterruptedException
    {
        selenium.clearTextboxUsingKeys(offerNameTextbox);
        selenium.enterText(offerNameTextbox,offerInfo.getName(), false);
        selenium.clearTextboxUsingKeys(shortDescTextbox);
        selenium.enterText(shortDescTextbox, offerInfo.getShortDescription(), false);
        selenium.clearTextboxUsingKeys(longDescTextbox);
        selenium.enterText(longDescTextbox, offerInfo.getLongDescription(), false);
        selenium.selectDropdownValueByText(categoryIdDropdown, offerInfo.getCategory());
        selenium.clearTextboxUsingKeys(priceValTextbox);
        selenium.enterText(priceValTextbox, offerInfo.getPriceDecimal(), false);
        selenium.clearTextboxUsingKeys(priceDecimalValTextbox);
        selenium.enterText(priceDecimalValTextbox, offerInfo.getPriceFloat(), false);
        selenium.clearTextboxUsingKeys(priceDescTextbox);
        selenium.enterText(priceDescTextbox, offerInfo.getPriceDescription(), false);
        switch (offerInfo.getDiscountType()){
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
                selenium.enterText(monitoryPriceValTextBox, offerInfo.getMonitoryDecimalDiscount(), false);
                selenium.clearTextboxUsingKeys(monitoryDecimalValTextBox);
                selenium.enterText(monitoryDecimalValTextBox, offerInfo.getMonitoryFloatDiscount(), false);
                break;

            case Percentile:
                selenium.clickOn(percentileDiscountRadioButton);
                selenium.click(percentileDiscountDropDown);
                selenium.selectDropdownValueByText(percentileDiscountDropDown, offerInfo.getPercentileDiscountValue());
                break;
        }
        if(!offerInfo.isSellable())
        {
            selenium.javascriptClickOn(sellableToggle);
        }
        selenium.click(saveOfferButton);
        selenium.waitTillElementsCountIsLessThan(saveOfferButton,1);
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


    /*
    * Offer Price Variant
    * */

    /**
     * Click on Add Offer Price Variant link
     *
     * @throws InterruptedException exception
     */
    public void clickOnAddPriceVariantLink() throws InterruptedException {
        selenium.clickOn(addPriceVariantLink);
    }


    /**
     * Click on EditPrice variant Button
     * @throws InterruptedException exception
     */
    public void clickOnPriceVariantEditLink(String priceVariantName) throws InterruptedException
    {
        selenium.hardWait(5);
        String xpathLocator = "//span[contains(text(),'" + priceVariantName + "')]";
        selenium.click(By.xpath(xpathLocator));
    }

    /**
     * Get All Offer Price Variant List
     * @return List of Price variant Name
     */
    public List<String> getPriceVariantList(){
        List<String> variantNames= new ArrayList<>();
        List<WebElement> list = selenium.waitTillAllElementsAreLocated(OfferPriceVariantList);
        for (WebElement e: list){
            variantNames.add(selenium.getText(e));
        }
        return variantNames;
    }

    /**
     * Is Offer Price Variant present?
     * @param priceVariantName price variant name
     * @return true or false
     */

    public boolean isPriceVariantPresent(String priceVariantName) {
        return getPriceVariantList().contains(priceVariantName);
    }


    /**
     * Click on Add option group link
     *
     * @throws InterruptedException exception
     */
    public void clickOnAddOptionGroupLink() throws InterruptedException {
        selenium.click(addOptionGroupLink);
    }

    /**
     * Is optionGroup present ?
     *
     * @param optionGroupName optionGroup name
     * @return true or false
     * @throws InterruptedException exception
     */
    public Boolean isOptionGroupPresent(String optionGroupName) throws InterruptedException {
        String xpathLocator = "//div[contains(text(),'" + optionGroupName + "')]";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }

    /**
     * Click on Option Group
     *
     * @param optionGroupName OptionGroup name
     * @throws InterruptedException exception
     */
    public void clickOnOptionGroup(String optionGroupName) throws InterruptedException {
        String xpathLocator = "//div[contains(text(),'" + optionGroupName +"')]";
        selenium.click(By.xpath(xpathLocator));
    }

    /**
     * Click on Add option link
     *
     * @throws InterruptedException exception
     */
    public void clickOnAddOptionLink(String optionGroupName) throws InterruptedException {
       // String xpathLocator = "//div[contains(text(),'" + optionGroupName + "')]/following-sibling::div/a";
        String xpathLocator = "//div[text()='" + optionGroupName +" ']/parent::div//a[text()='Add Option ']";
        selenium.click(By.xpath(xpathLocator));
    }

    /**
     * Get All Option List
     *
     * @param optionGroupName OptionGroup name
     * @return List of Option Name
     */
    public List<String> getOptionList(String optionGroupName) {
        String xpathLocator = "//div[contains(text(),'" + optionGroupName + " ')]//parent::div[contains(@class, 'optionGoupHeader')]/following-sibling::div//label/span[1]";
        List<String> optionNames = new ArrayList<>();
        List<WebElement> list = selenium.waitTillAllElementsAreLocated(By.xpath(xpathLocator));
        for (WebElement e : list) {
            optionNames.add(selenium.getText(e));
        }
        return optionNames;
    }

    /**
     * Is Option Name present?
     *
     * @param optionGroupName option group name
     * @param optionName option name
     * @return true or false
     */

    public boolean isOptionNamePresent(String optionGroupName, String optionName){
        return getOptionList(optionGroupName).contains(optionName);
    }

    /**
     * Click on Option Group
     *
     * @param optionGroupName OptionGroup name
     * @throws InterruptedException exception
     */
    public void clickOnOption(String optionGroupName, String optionName) throws InterruptedException {
        String xpathLocator = "//div[contains(text(),'" + optionGroupName + "')]//parent::h3/following-sibling::div/div/label/span[contains(text(),'" + optionName + "')]";
        selenium.click(By.xpath(xpathLocator));
    }




}
