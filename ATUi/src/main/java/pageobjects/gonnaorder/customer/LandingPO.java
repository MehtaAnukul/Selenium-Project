package pageobjects.gonnaorder.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class LandingPO extends BasePO {


    public LandingPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */


    @FindBy(className = "catalog-language-selector")
    private WebElement flagIcon;

    private By okLink = By.xpath("//a[text()='OK']");

    /**
     * Is category present ?
     *
     * @param categoryName category name
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isCategoryPresent(String categoryName) throws InterruptedException {
        String xpathLocator = "//ul[@id='myTabs']/li/a[text()='" + categoryName + "']";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }

    /**
     * Click on Offer
     *
     * @param offerName offer name
     * @throws InterruptedException exception
     */
    public void selectOffer(String offerName) throws InterruptedException {
       // String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'tab-pane')]/a//div[contains(@class,'item')][text()='" + offerName + "']";
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']";
        selenium.click(By.xpath(xpathLocator));
    }

    /**
     * Click on Category
     *
     * @param categoryName category name
     * @throws InterruptedException exception
     */
    public void clickOnCategory(String categoryName) throws InterruptedException {
        String xpathLocator = "//ul[@id='myTabs']/li/a[text()='" + categoryName + "']";
        selenium.click(By.xpath(xpathLocator));
    }

    /**
     * Click on Flag icon
     *
     * @throws InterruptedException exception
     */
    public void clickOnFlagIcon() throws InterruptedException {
        selenium.click(flagIcon);
    }

    /**
     * Is language present ?
     *
     * @param languageName language name
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isLanguageNamePresent(String languageName) throws InterruptedException {
        String xpathLocator = "//label[@for='" + languageName + "']";
        return selenium.waitInCaseElementVisible(By.xpath(xpathLocator),5) !=null;
    }

    /**
     * Click on Language
     * @param languageName language name
     * @throws InterruptedException
     */
    public void selectLanguageName(String languageName) throws InterruptedException {
        String xpathLocator = "//label[@for='" + languageName + "']";
        selenium.click(By.xpath(xpathLocator));
        selenium.click(okLink);
    }


    /**
     * Is offer present ?
     *
     * @param offerName offer name
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isOfferPresent(String offerName) throws InterruptedException {
        //String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'tab-pane')]/a//div[contains(@class,'item')][text()='" + offerName + "']";
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }


    /**
     * Get Offer Description
     *
     * @param offerName offer name
     * @return offer description
     */
    public String getOfferDescription(String offerName) {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/following-sibling::div[contains(@class,'description')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Offer Price
     *
     * @param offerName offer price
     * @return offer price
     */
    public String getOfferPrice(String offerName) {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/parent::*//div[contains(@class,'price')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Strike through Offer Price
     *
     * @param offerName offer price
     * @return strike through offer price
     */
    public String getStrikeOfferPrice(String offerName) {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/parent::*//div//span[contains(@class,'strikethrough')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Discount Offer Price
     *
     * @param offerName offer price
     * @return Discount offer price
     */
    public String getPercentileDiscountOfferPrice(String offerName) {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/parent::*//div//span[contains(@class,'discounted-price ml-1')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Discount value
     *
     * @param offerName offer price0
     * @return Discount value
     */
    public String getPercentileDiscountOfferValue(String offerName) {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/parent::*//div//span[contains(@class,'discounted-price ml-2')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Monetory discount value
     * @param offerName offer price
     * @return Discount value
     */
    public String getMonetoryDiscountOfferValue(String offerName) {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/parent::*//div//span[contains(@class,'discounted-price ml-1')]";
        return selenium.getText(By.xpath(xpathLocator));
    }



    /*
     * Price variant xpath are defined
     */


    /**
     * Is PriceVariant present ?
     * @param offerName offer price
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isPriceVariantPresent(String offerName) throws InterruptedException {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/parent::*//div[2]/span[last()][contains(@class,'variant')]";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }

    /**
     * Is PriceVariant present ?
     * @param offerName offer price
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isPriceVariantPresent(String offerName , String priceVariantName) throws InterruptedException {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/parent::*//div[2]/span[last()][contains(@class,'variant')]/div/span[text()='" + priceVariantName + "']";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }


    /**
     * Get Offer Price Variant value
     * @param offerName offer price
     * @return Offer Price Variant name and Price
     */
    public String getPriceVariantValue(String offerName) {
        String xpathLocator = "//div[@role='tabpanel']/div[contains(@class,'fast')]/div/a//div[contains(@class,'item')][text()='" + offerName + "']/parent::*//div[2]/span[last()][contains(@class,'variant')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Offer PriceVariant from variant List
     ** @param offerName offer price
     * @throws InterruptedException exception
     */
    public String getPriceVariantAtItemList(String offerName)  {
        String xpathLocator = "//div[contains(@class,'item')][text()='" + offerName + "']/parent::div/following-sibling::div[1]/div/div[last()]/preceding-sibling::div[1][contains(@class,'variant-wrapper')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Select Price Variant radio button
     * @param offerName offer price
     * @throws InterruptedException exception
     */
    public void clickOnPriceVariant(String offerName) throws InterruptedException {
        String xpathLocator = "//div[contains(@class,'item')][text()='" + offerName + "']/parent::div/following-sibling::div[1]/div/div[last()]/preceding-sibling::div[1][contains(@class,'variant-wrapper')]";
        selenium.click(By.xpath(xpathLocator));
    }

}
