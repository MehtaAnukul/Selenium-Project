package pageobjects.gonnaorder.admin.store.table;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class TablePO extends BasePO {

    public TablePO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */


    @FindBy(css = "a[href$='/manage']")
    private WebElement tableLink;

    @FindBy(xpath = "//a[contains(text(),'Download Image QR code')]")
    private WebElement imageDownload;

    /**
     * Click on Table link
     *
     * @throws InterruptedException exception
     */
    public void clickOnTableLink() throws InterruptedException {
        selenium.click(tableLink);
    }

    /**
     * Click on Image download link
     *
     * @throws InterruptedException exception
     */
    public void clickOnImageDownloadLink() throws InterruptedException {
        selenium.click(imageDownload);
        selenium.hardWait(5);
    }

}


