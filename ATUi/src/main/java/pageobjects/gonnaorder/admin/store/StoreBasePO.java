package pageobjects.gonnaorder.admin.store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class StoreBasePO extends BasePO {

    public StoreBasePO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */
    @FindBy(id = "name")
    protected WebElement nameTextbox;
    @FindBy(id = "description")
    protected WebElement descriptionTextbox;
    @FindBy(id = "alias")
    protected WebElement aliasTextbox;
    @FindBy(id = "country")
    protected WebElement countryDropdown;
    @FindBy(id = "addressLine1")
    protected WebElement addressLine1Textbox;
    @FindBy(id = "addressLine2")
    protected WebElement addressLine2Textbox;
    @FindBy(id = "postCode")
    protected WebElement postCodeTextbox;
    @FindBy(id = "region")
    protected WebElement regionTextbox;
    @FindBy(id = "city")
    protected WebElement cityTextbox;
    @FindBy(id = "phoneNumber")
    protected WebElement phoneNumberTextbox;
    protected By saveButton = By.xpath("//button[text()='Save']");


}
