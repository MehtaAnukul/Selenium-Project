package pageobjects.gonnaorder.admin.store.settings;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utilities.Constants;
import utilities.SeleniumHelpers;



public class SettingPO {

        WebDriver driver;
        SeleniumHelpers selenium;

        public SettingPO(WebDriver driver) {
            this.driver = driver;
            selenium = new SeleniumHelpers(driver);

            //This initElements method will create all WebElements
            PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
        }


        /*
         * All WebElements are identified by @FindBy annotation
         * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
         */

        @FindBy(xpath = "//button[contains(text(),'Ordering')]")
        private WebElement orderingButton;

        @FindBy(xpath = "//button[contains(text(),'Payment Providers')]")
        private WebElement paymentProvidersButton;

        @FindBy(id = "settingsHeader")
        private WebElement settingHeader;
        /**
         * Click on Ordering button
         * @throws InterruptedException exception
         */
        public void clickOnOrderingButton() throws InterruptedException {
            selenium.pageScrollInView(settingHeader);
            selenium.hardWait(5);
            selenium.clickOn(orderingButton);
        }


    /**
     * Click on Payment Providers button
     * @throws InterruptedException exception
     */
    public void clickOnPaymentProvidersButton() throws InterruptedException {
        selenium.hardWait(5);
        selenium.clickOn(paymentProvidersButton);
    }


}
