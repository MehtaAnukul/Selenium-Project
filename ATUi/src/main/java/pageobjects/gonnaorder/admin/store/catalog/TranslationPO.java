package pageobjects.gonnaorder.admin.store.catalog;

import dataobjects.gonnaorder.admin.Translation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.Constants;
import utilities.SeleniumHelpers;

public class TranslationPO {

    WebDriver driver;
    SeleniumHelpers selenium;


    public TranslationPO(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//span[text()='Add New Translation']")
    private WebElement addNewTranslationLink;

    @FindBy(id = "languageId")
    private WebElement languageDropdown;

    @FindBy(id = "name")
    private WebElement nameTextBox;

    @FindBy(id = "shortDescription")
    private WebElement shortDescriptionTextBox;

    @FindBy(id = "longDescription")
    private WebElement longDescriptionTextBox;

    @FindBy(className = "rightActions")
    private WebElement saveLanguageButton;

    @FindBy(className = "mat-content")
    private WebElement expandTranslationsSection;

    @FindBy(xpath = "//i[@class='fa fa-pen text-blue']")
    private WebElement editIcon;

    private By deleteLink = By.xpath("//*[contains(@id,'cdk-overlay')]//a[text()='Delete']");

    /**
     * Click on Add New Translation link
     *
     * @throws InterruptedException exception
     */
    public void clickOnAddNewTranslationLink() throws InterruptedException {
        selenium.click(addNewTranslationLink);
    }

    /**
     * Fill add translation form and click on Save button
     *
     * @param translationInfo Translation object
     * @throws InterruptedException exception
     */
    public void fillTranslationFormAndClickOnSaveButton(Translation translationInfo) throws InterruptedException {

        if(!translationInfo.getLanguage().isEmpty())
        {
            selenium.selectDropdownValueByText(languageDropdown, translationInfo.getLanguage());
        }
        selenium.enterText(nameTextBox, translationInfo.getName(), true);
        selenium.enterText(shortDescriptionTextBox, translationInfo.getShortDescription(), true);
        selenium.click(saveLanguageButton);
        selenium.hardWait(5);
    }

    /**
     * Get Table data for given row & column
     * @param row row index
     * @param column column index
     * @return value
     * @throws InterruptedException
     */
    public String getTableData(int row,int column) throws InterruptedException {
        String cssLocator = "table tbody tr:nth-of-type(" + row + ") td:nth-of-type(" + column + ")";
        return selenium.getText(By.cssSelector(cssLocator));
    }

    /**
     * Get Total Rows
     * @return rows count
     */
    public int getTotalRows() {
        String cssLocator = "table tbody tr";
        return selenium.waitTillAllElementsAreLocated(By.cssSelector(cssLocator)).size();
    }

    /**
     * Click on Edit icon
     *
     * @throws InterruptedException exception
     */
    public void clickOnEditIcon() throws InterruptedException {
        selenium.click(editIcon);
    }

    /**
     * Expand Translations Section
     *
     * @throws InterruptedException exception
     */
    public void expandTranslationsSection() throws InterruptedException {
        selenium.click(expandTranslationsSection);
    }

    /**
     * Click on Delete link
     *
     * @throws InterruptedException exception
     */
    public void clickOnDeleteLink() throws InterruptedException {
        selenium.click(deleteLink);
    }
}
