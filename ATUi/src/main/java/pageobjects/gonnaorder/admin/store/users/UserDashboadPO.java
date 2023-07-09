package pageobjects.gonnaorder.admin.store.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class UserDashboadPO extends BasePO {


    public UserDashboadPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */


    @FindBy(css = "input[formcontrolname='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[contains(text(),'Re-Authenticate')]")
    private WebElement reAuthenticateButton;

    @FindBy(xpath = "//button[contains(text(),'Invite User')]")
    private WebElement inviteUserButton;

    @FindBy(id = "email")
    private WebElement emailTextBox;

    @FindBy(xpath = "//button[text()='Invite']")
    private WebElement inviteButton;

    @FindBy(xpath = "//button[text()='Ok']")
    private WebElement okButton;

    @FindBy(xpath = "//input[@id='STORE_ADMIN']//following-sibling::span")
    private WebElement inviteAdministratorUserButton;

    @FindBy(xpath = "//input[@id='STORE_STANDARD']//following-sibling::span")
    private WebElement inviteStandardUserButton;

    /**
     * Fill Password Data And Click On ReAuthenticate Button
     *
     * @param password Enter password details
     * @throws InterruptedException exception
     */
    public void fillPasswordDataAndClickOnReAuthenticateButton(String password) throws InterruptedException {
       selenium.hardWait(3);
        selenium.enterText(passwordTextBox, password, false);
        selenium.clickOn(reAuthenticateButton);
    }

    /***
     * Click on 'Invite User' button
     * @throws InterruptedException
     */
    public void clickOnInviteUserButton() throws InterruptedException {
        selenium.clickOn(inviteUserButton);
    }

    /**
     * Fill Email Data And Click On ReAuthenticate Button
     *
     * @param email Enter email details
     * @throws InterruptedException exception
     */
    public void fillEmailDataAndClickOnInviteButton(String email) throws InterruptedException {
        selenium.enterText(emailTextBox, email, false);
        selenium.clickOn(inviteButton);
    }


    /***
     * Click on Remove user icon
     * @throws InterruptedException
     */
    public void clickOnRemoveUserIcon() throws InterruptedException {
        String cssLocator = "i.fa-user-minus";
        selenium.click(By.cssSelector(cssLocator));
    }

    /**
     * Get Email address
     *
     * @return email address
     * @throws InterruptedException
     */
    public String getEmailAddress() throws InterruptedException {
        String cssLocator = "table tbody tr:nth-of-type(1) td:nth-of-type(3)";
        return selenium.getText(By.cssSelector(cssLocator));
    }

    /***
     * Click on Ok Button
     * @throws InterruptedException
     */
    public void clickOnOKButton() throws InterruptedException {
        selenium.clickOn(okButton);
    }

    /***
     * Click on Standard User Radio Button
     * @throws InterruptedException
     */
    public void clickOnStandardUserRadioButton() throws InterruptedException {
        selenium.click(inviteStandardUserButton);
    }
}


