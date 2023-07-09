package pageobjects.helloteams.account;

import dataobjects.Team;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

import java.time.Duration;


public class AddTeamPO extends BasePO
{

    public AddTeamPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */
    @FindBy(name = "teamname")
    private WebElement enterTeamName;

    @FindBy(xpath = "//select[@name='teamtype']")
    private WebElement clickOnTeamTypeDropDown;

    @FindBy(name = "Organization")
    private WebElement clickOnOrganizationField;

    @FindBy(name = "Purpose")
    private WebElement clickOnPurposeField;

    @FindBy(xpath = "//div[@id='content']/following-sibling::div//div[@data-title='Close']")
    private WebElement closeIconOfAddTeam;

    @FindBy(xpath ="//div[@id='content']/following-sibling::div//div[@data-title='Save']")
    private WebElement saveIconOfAddTeam;


    /**
     * fill the form of create team with 'Team Type' and save
     *
     * @throws InterruptedException exception
     */
    public void fillFormOfCreateTeamWithTeamTypeAndSave(Team team) throws InterruptedException {
        selenium.clearTextBoxUsingKeys(enterTeamName);
        selenium.enterText(enterTeamName,team.getEnterTeamName(),false);
        selenium.selectDropDownValueByIndex(clickOnTeamTypeDropDown, 8);
        selenium.hardWait(5);
        selenium.clickOn(clickOnOrganizationField);
        String selectTeamOrganizationItemLocator1 = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + team.getSelectedOrganizationItems1() + "')]";
        selenium.clickOn(By.xpath(selectTeamOrganizationItemLocator1));
        selenium.clickOn(clickOnOrganizationField);
        String selectTeamOrganizationItemLocator = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + team.getSelectedOrganizationItems() + "')]";
        selenium.clickOn(By.xpath(selectTeamOrganizationItemLocator));
        selenium.clickOn(clickOnOrganizationField);
        String selectTeamOrganizationItemLocator2 = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + team.getSelectedOrganizationItems2() + "')]";
        selenium.clickOn(By.xpath(selectTeamOrganizationItemLocator2));
        selenium.clickOn(clickOnPurposeField);
        String selectTeamPurposeItemLocator = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + team.getSelectedPurposeItems() + "')]";
        selenium.click(By.xpath(selectTeamPurposeItemLocator));
        selenium.clickOn(saveIconOfAddTeam);
    }

    /**
     * fill the form of create team with 'Program/Portfolio' type
     *
     * @throws InterruptedException exception
     */
    public void fillFormOfCreateTeamWithOtherTypeAndSave(Team team) throws InterruptedException
    {
        selenium.clearTextBoxUsingKeys(enterTeamName);
        selenium.enterText(enterTeamName,team.getEnterTeamName(),false);
        selenium.selectDropDownValueByIndex(clickOnTeamTypeDropDown, 2);
    }

    /**
     * Verify that 'Organization' text box is present or not
     *
     * @return if  present
     */
    public boolean isOrganizationFieldPresent() {
        return selenium.waitInCaseElementVisible(clickOnOrganizationField,3) == null;
    }

    /**
     * Verify that 'Organization' text box is present or not
     *
     * @return if  present
     */
    public boolean isPurposeFieldPresent() {
        return selenium.waitInCaseElementVisible(clickOnPurposeField,3) == null;

    }

    /**
     * Click on save button of Add team
     *
     * @throws InterruptedException exception
     */
    public void clickOnSaveButton() throws InterruptedException {
        selenium.clickOn(saveIconOfAddTeam);
    }

    /**
     * Click on cancel button of Add team
     *
     * @throws InterruptedException exception
     */
    public void clickOnCancelButton() throws InterruptedException
    {
        selenium.clickOn(closeIconOfAddTeam);
    }
}
