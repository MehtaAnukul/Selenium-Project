package pageobjects.gonnaorder.admin.store.ShareAndPreview;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class SharePagePO extends BasePO {

    public SharePagePO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */


    @FindBy(xpath = "//a[contains(text(),'Download QR code image')]")
    private WebElement imageDownloadFromShare;


    /**
     * Click on Image download link of Share page
     *
     * @throws InterruptedException exception
     */
    public void clickOnImageDownloadLinkOfSharePage() throws InterruptedException {
        selenium.click(imageDownloadFromShare);
        selenium.hardWait(5);
    }

}
