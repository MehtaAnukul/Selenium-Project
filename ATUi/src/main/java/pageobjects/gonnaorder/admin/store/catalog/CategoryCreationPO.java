package pageobjects.gonnaorder.admin.store.catalog;

import dataobjects.gonnaorder.admin.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.Constants;
import utilities.SeleniumHelpers;

public class CategoryCreationPO {

    WebDriver driver;
    SeleniumHelpers selenium;


    public CategoryCreationPO(WebDriver driver)
    {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(id="offerName")
    private WebElement categoryNameTextbox;

    @FindBy(id="shortDesc")
    private WebElement shortDescTextbox;

    @FindBy(className = "mat-slide-toggle-bar")
    private WebElement sellableToggle;

    @FindBy(css = "div.mat-slide-toggle-bar input")
    private WebElement sellableToggleInput;

    @FindBy(id = "availability")
    private WebElement availabilityDropdown;

    private By saveButton = By.xpath("//button[text()='Save']");

    private By deleteLink = By.xpath("//a[text()='Delete']");

    private By okButton = By.xpath("//button[text()='Ok']");


    /**
     * Fill create category form and click on Save button
     * @param categoryInfo Category object
     * @throws InterruptedException exception
     */
    public void fillCreateCategoryFormAndClickOnSaveButton(Category categoryInfo) throws InterruptedException
    {
        selenium.clearTextboxUsingKeys(categoryNameTextbox);
        selenium.enterText(categoryNameTextbox, categoryInfo.getName(), false);
        selenium.clearTextboxUsingKeys(shortDescTextbox);
        selenium.enterText(shortDescTextbox, categoryInfo.getShortDesc(), false);

        String isSellableToggleSelected = selenium.getElementAttributeValue(sellableToggleInput,"aria-checked");

        if((categoryInfo.isSellable() && !isSellableToggleSelected.equalsIgnoreCase("true")) ||
                (!categoryInfo.isSellable() && isSellableToggleSelected.equalsIgnoreCase("true")))
        {
            selenium.click(sellableToggle);
        }

        selenium.hardWait(5);
        selenium.click(saveButton);
        selenium.waitTillElementsCountIsLessThan(saveButton, 1);
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

