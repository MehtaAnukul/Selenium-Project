package utilities.emailhelpers.mailinator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;


public class EmailDetailsPO extends BasePO {
    public EmailDetailsPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(id = "msg_body")
    private WebElement messageBodyIFrame;


    /**
     * Click on button or link
     * @parameter buttonOrLinkText button or link text
     */
    public void clickOnButtonOrLink(String buttonOrLinkText) {
        selenium.switchToIframe(messageBodyIFrame);
        String xpathLocator = "*//a[contains(text(),'" + buttonOrLinkText + "')]";
        selenium.javascriptClickOn(driver.findElement(By.xpath(xpathLocator)));
        selenium.switchToMainIframe();
    }


    //GonnaOrder Specific

   // @FindBy(xpath = "//table//p[3]")
   // private WebElement OrderIdFromMail;

    /**
     * Get Order id from Mail
     *
     */
    /*
    public String getOrderIdFromMail() {
        selenium.switchToIframe(messageBodyIFrame);
        String id = selenium.getText(OrderIdFromMail);
        selenium.switchToMainIframe();
        return id;
    }
*/
}
