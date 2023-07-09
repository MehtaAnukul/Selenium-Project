package utilities.emailhelpers.mailinator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailListingGridPO extends BasePO {

    public EmailListingGridPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(css = ".jambo_table tbody tr")
    private List<WebElement> allEmailRows;

    private By fromInfo = By.cssSelector("td:nth-of-type(3)");
    private By subjectInfo = By.cssSelector("td:nth-of-type(4)");


    /**
     * Get all email subject And from info as HasMap Key value pair
     *
     * @return Map<String, String>
     */
    public Map<String, String> getAllEmailsSubjectAndFromInfo() {
        HashMap<String, String> emailData = new HashMap<>();
        for (WebElement email : allEmailRows) {
            String fromInfoText = selenium.getText(email.findElement(fromInfo));
            String subjectText = selenium.getText(email.findElement(subjectInfo));
            emailData.put(subjectText, fromInfoText);
        }
        return emailData;
    }

    /**
     * Open given email by subject & from info
     *
     * @param from    Email from info
     * @param subject Email subject info
     */
    public void openEmail(String from, String subject) throws InterruptedException {
        for (WebElement e : allEmailRows) {
            String rowText = selenium.getText(e);
            if (rowText.contains(subject) && rowText.contains(from)) {
                selenium.clickOn(e.findElement(subjectInfo).findElement(By.tagName("a")));
                break;
            }
        }
    }
}
