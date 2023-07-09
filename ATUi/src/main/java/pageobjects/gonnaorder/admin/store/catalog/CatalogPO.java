package pageobjects.gonnaorder.admin.store.catalog;

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

public class CatalogPO {

    WebDriver driver;
    SeleniumHelpers selenium;

    public CatalogPO(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//button[contains(text(),'Add Category')]")
    private WebElement addCategoryButton;

    @FindBy(xpath="//button[contains(text(),'Add Offer')]")
    private WebElement addOfferButton;

    /**
     * Click on Add Category button
     * @throws InterruptedException exception
     */
    public void clickOnAddCategoryButton() throws InterruptedException {
        selenium.clickOn(addCategoryButton);
    }

    /**
     * Click on Add Offer Button
     * @throws InterruptedException exception
     */
    public void clickOnAddOfferButton() throws InterruptedException {
        selenium.clickOn(addOfferButton);
    }

    /**
     * Click on Edit Category Icon
     * @throws InterruptedException exception
     */
    public void clickOnEditCategoryIcon(String categoryName) throws InterruptedException {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='" + categoryName + "']/following-sibling::i";
        selenium.click(By.xpath(xpathLocator));
    }

    /**
     * Get All Categories names
     * @return List of Categories names
     * @throws InterruptedException exception
     */
    public List<String> getAllCategoryNames() throws InterruptedException {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a";
        List<String> categoryNames = new ArrayList<>();
        List<WebElement> categories = selenium.waitTillAllElementsAreLocated(By.xpath(xpathLocator));
        for (WebElement category : categories) {
            categoryNames.add(selenium.getText(category));
        }
        return categoryNames;
    }

    /**
     * Is category present ?
     * @param categoryName category name
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isCategoryPresent(String categoryName) throws InterruptedException {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='" + categoryName +"']";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }

    /**
     * Click on Category
     * @param categoryName category name
     * @throws InterruptedException exception
     */
    public void clickOnCategory(String categoryName) throws InterruptedException {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='" + categoryName +"']/ancestor::mat-expansion-panel-header//span[contains(@class,'mat-expansion-indicator')]";
        selenium.click(By.xpath(xpathLocator));
    }

    /**
     * Get All offers name by category
     * @param categoryName category name
     * @return List of Offers Name
     * @throws InterruptedException exception
     */
    public List<String> getAllOfferNamesByCategory(String categoryName) throws InterruptedException {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='" + categoryName +"']/ancestor::mat-expansion-panel//*[@class='offerName']";
        List<String> offerNames= new ArrayList<>();
        List<WebElement> offers = selenium.waitTillAllElementsAreLocated(By.xpath(xpathLocator));
        for(WebElement offer:offers)
        {
            offerNames.add(selenium.getText(offer));
        }
        return offerNames;
    }

    /**
     * Is Offer present
     * @param categoryName category name
     * @param offerName offer name
     * @return true or false
     * @throws InterruptedException exception
     */
    public Boolean isOfferPresent(String categoryName, String offerName) throws InterruptedException {
        return getAllOfferNamesByCategory(categoryName).contains(offerName);
    }

    /**
     * Get Offer Price Description
     * @param categoryName  category name
     * @param offerName offer name
     * @return offer price description
     */
    public String getOfferPriceDescription(String categoryName, String offerName){
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='"+ categoryName +"']/ancestor::mat-expansion-panel//a[text()='"+ offerName +"']/parent::div/following-sibling::div/span[contains(@class,'priceDescription')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Offer Price Value
     * @param categoryName category name
     * @param offerName offer name
     * @return offer price
     */
    public String getOfferPriceValue(String categoryName, String offerName){
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='"+ categoryName +"']/ancestor::mat-expansion-panel//a[text()='"+ offerName +"']/parent::div/following-sibling::div/div[contains(@class,'price')]";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Offer strike through Price Value
     * @param categoryName category name
     * @param offerName offer name
     * @return offer price
     */
    public String getOfferStrikePriceValue(String categoryName, String offerName){
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='"+ categoryName +"']/ancestor::mat-expansion-panel//a[text()='"+ offerName +"']/parent::div/following-sibling::div/div[contains(@class,'price')]//span[@class='strikethrough mr-1']";
        return selenium.getText(By.xpath(xpathLocator));
    }


    /**
     * Click on Edit offer Icon
     * @throws InterruptedException exception
     */
    public void clickOnEditOfferIcon(String categoryName, String offerName) throws InterruptedException {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='"+ categoryName +"']/ancestor::mat-expansion-panel//a[contains(@class,'offerName')][text()='"+ offerName +"']/following-sibling::i[contains(@class,'editIcon')]";
        selenium.click(By.xpath(xpathLocator));
        selenium.hardWait(3);
    }



    /**
     * Get Discount Offer Price
     * @param categoryName category name
     * @param offerName offer name
     * @return discount price
     */
    public String getPercentileDiscountPrice(String categoryName, String offerName)
    {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='"+ categoryName +"']/ancestor::mat-expansion-panel//a[text()='"+ offerName +"']/parent::div/following-sibling::div//span[@class='discounted-price ml-1']";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Discount value
     * @param categoryName category name
     * @param offerName offer name
     * @return discount value
     */
    public String getPercentileDiscountValue(String categoryName, String offerName)
    {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='"+ categoryName +"']/ancestor::mat-expansion-panel//a[text()='"+ offerName +"']/parent::div/following-sibling::div//span[@class='discounted-price ml-2']";
        return selenium.getText(By.xpath(xpathLocator));
    }

    /**
     * Get Monetory Discount value
     * @param categoryName category name
     * @param offerName offer name
     * @return monetory discount value
     */
    public String getMonetoryDiscountValue(String categoryName, String offerName)
    {
        String xpathLocator = "//*[contains(@class,'categoryName')]/a[text()='"+ categoryName +"']/ancestor::mat-expansion-panel//a[text()='"+ offerName + "']/parent::div/following-sibling::div//span[@class='discounted-price ml-1']";
        return selenium.getText(By.xpath(xpathLocator));
    }

}


