package utilities.emailhelpers;

import org.openqa.selenium.WebDriver;
import utilities.Constants;
import utilities.SeleniumHelpers;
import utilities.emailhelpers.mailinator.EmailDetailsPO;
import utilities.emailhelpers.mailinator.EmailListingGridPO;

import java.util.Map;


public class EmailHelpers {
    WebDriver driver;
    SeleniumHelpers selenium;
    EmailListingGridPO emailListing;
    EmailDetailsPO emailDetails;

    public EmailHelpers(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        emailListing = new EmailListingGridPO(driver);
        emailDetails = new EmailDetailsPO(driver);
    }

    /**
     * Navigate to email box
     *
     * @param emailDomain      email domain name
     * @param emailAccountName email account name
     */
    private void navigateToEmailbox(String emailDomain, String emailAccountName) {
        if (emailDomain.equals(Constants.MAILINATORNAME)) {
            String emailBoxUrl = Constants.MAILINATORURL + emailAccountName + "#/#inboxpane";
            selenium.navigateToPage(emailBoxUrl);
        }
    }

    /**
     * To check whether email is received or not
     *
     * @param emailDomain      email domain name
     * @param emailAccountName email account name
     * @param fromInfo         email from info
     * @param subjectInfo      email subject info
     * @return boolean true or false
     */
    public boolean isEmailReceived(String emailDomain, String emailAccountName, String fromInfo, String subjectInfo, int waitTime) throws InterruptedException {
        selenium.hardWait(waitTime);
        navigateToEmailbox(emailDomain, emailAccountName);

        if (emailDomain.equals(Constants.MAILINATORNAME)) {
            Map<String, String> emailData;
            emailData = emailListing.getAllEmailsSubjectAndFromInfo();

            if (emailData.containsKey(subjectInfo)) {
                return emailData.get(subjectInfo).equals(fromInfo);
            }
        }

        return false;
    }

    /**
     * On email body, click on Email or Link
     *
     * @param emailDomain      email domain name
     * @param fromInfo         email from info
     * @param subjectInfo      email subject info
     * @param buttonOrLinkName button or link name
     */
    public void clickOnButtonOrLinkOnEmailBody(String emailDomain, String fromInfo, String subjectInfo, String buttonOrLinkName) throws InterruptedException {

        if (emailDomain.equals(Constants.MAILINATORNAME)) {
            //Open email
            emailListing.openEmail(fromInfo, subjectInfo);

            //Click on button
            emailDetails.clickOnButtonOrLink(buttonOrLinkName);
        }
    }
}


