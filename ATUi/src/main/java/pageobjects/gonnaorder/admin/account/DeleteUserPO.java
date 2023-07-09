package pageobjects.gonnaorder.admin.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;
import utilities.Constants;

public class DeleteUserPO extends BasePO {

    public DeleteUserPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//button[contains(text(),'Ok')]")
    private WebElement deleteButton;



    /**
     * Delete the registered user
     */
    public void deleteRegisteredUser() {
        try {
            selenium.navigateToPage(Constants.ADMIN_URL + "/manager/profile/delete");
            selenium.hardWait(3);
            selenium.clickOn(deleteButton);
            selenium.hardWait(3);
        } catch (Exception e) {
            return;
        }
    }
}