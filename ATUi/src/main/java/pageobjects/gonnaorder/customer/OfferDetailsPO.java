package pageobjects.gonnaorder.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.base.BasePO;

public class OfferDetailsPO extends BasePO {

    public OfferDetailsPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */


    /**
     * Is option group present ?
     *
     * @param optionGroupName option group name
     * @return true or false
     * @throws InterruptedException exception
     */


    public boolean isOptionGroupPresent(String optionGroupName) throws InterruptedException {
        String xpathLocator = "//div[contains(@class,'option-wrapper')]//descendant::h3[text()='" + optionGroupName + "']";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }

    /**
     * Is option present ?
     *
     * @param optionGroupName optionGroup name
     * @param optionName option name
     * @return true or false
     * @throws InterruptedException exception
     */
    public boolean isOptionPresent(String optionGroupName, String optionName) throws InterruptedException {
       // String xpathLocator = "//div[contains(@class,'option-wrapper')]//descendant::h3[text()='" + optionGroupName + "']//parent::*//following-sibling::div//descendant::div[contains(text(),'" + optionName + "')]"
        String xpathLocator = "//div[contains(@class,'option-wrapper')]//parent::*//following-sibling::div//descendant::div[contains(text(),'" + optionName + "')]";
        return selenium.isElementPresent(By.xpath(xpathLocator));
    }

    /**
     * Get Option Price
     *
     * @return Price of Option
     */
    public String getOptionPrice(String optionGroupName, String optionName) {
       // String xpathLocator = "//div[contains(@class,'option-wrapper')]//descendant::h3[text()='" + optionGroupName + "']//parent::*//following-sibling::div//descendant::div[contains(text(),'" + optionName + "')]/span";
        String xpathLocator = "//div[contains(@class,'option-wrapper')]//parent::*//following-sibling::div//descendant::div[contains(text(),'" + optionName + "')]/span";
        return selenium.getText(By.xpath(xpathLocator));
    }


    /**
     * Select Option
     * @param optionGroupName optionGroup Name
     * @param optionName option Name
     * @throws InterruptedException exception
     */
    public void clickOnOption(String optionGroupName, String optionName) throws InterruptedException {
        String xpathLocator = "//div[contains(@class,'option-wrapper')]//parent::*//following-sibling::div//descendant::div[contains(text(),'" + optionName + "')]/span";
        selenium.click(By.xpath(xpathLocator));
    }
}

