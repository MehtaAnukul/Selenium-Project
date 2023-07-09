package pageobjects.helloteams.account;

import dataobjects.TeamMember;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class AddTeamMembersPO extends BasePO
{
    public AddTeamMembersPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//div[@class='members-wrapper']/div//div[@data-title='Close']")
    private WebElement closeIconOfAddTeamMember;

    @FindBy(xpath ="//div[contains(@data-title,'Save')]")
    private WebElement saveIconOfAddTeamMember;

    @FindBy(name = "memberemail")
    private WebElement enterTeamMemberEmail;

    @FindBy(name = "memberFirstName")
    private WebElement enterTeamMemberFirstName;

    @FindBy(name = "memberLastName")
    private WebElement enterTeamMemberLastName;

    @FindBy(name = "name")
    private WebElement enterTeamMemberRole;

    @FindBy(name = "Distribution")
    private WebElement enterTeamMemberDistribution;

    @FindBy(name = "Location")
    private WebElement enterTeamMemberLocation;

    @FindBy(name = "Employment")
    private WebElement enterTeamMemberEmployment;


    /**
     *  Fill a form of Team member with valid data
     *
     * @param teamMember object
     * @throws InterruptedException exception
     */
    public void fillFormOfTeamMemberAndSave(TeamMember teamMember) throws InterruptedException
    {
        selenium.enterText(enterTeamMemberEmail,teamMember.getEnterTeamMemberEmail(),false);
        selenium.enterText(enterTeamMemberFirstName,teamMember.getEnterTeamMemberFirstName(),false);
        selenium.enterText(enterTeamMemberLastName,teamMember.getEnterTeamMemberLastName(),false);
        selenium.clickOn(enterTeamMemberRole);
        String selectRoleLocator = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + teamMember.getSelectTeamMemberRole() + "')]";
        selenium.clickOn(By.xpath(selectRoleLocator));
        selenium.clickOn(enterTeamMemberDistribution);
        String selectDistributionLocator = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + teamMember.getSelectTeamMemberDistribution() + "')]";
        selenium.clickOn(By.xpath(selectDistributionLocator));
        selenium.clickOn(enterTeamMemberLocation);
        String selectLocationLocator = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + teamMember.getSelectTeamMemberLocation() + "')]";
        selenium.clickOn(By.xpath(selectLocationLocator));
        selenium.clickOn(enterTeamMemberEmployment);
        String selectEmploymentLocator = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + teamMember.getSelectTeamMemberEmployment() + "')]";
        selenium.clickOn(By.xpath(selectEmploymentLocator));
        selenium.clickOn(saveIconOfAddTeamMember);
    }

    /**
     * Fill a form of leaders with valid data
     *
     * @param teamMember object
     * @throws InterruptedException exception
     */
    public void fillFormOfLeadersAndSave(TeamMember teamMember) throws InterruptedException {
        selenium.enterText(enterTeamMemberEmail,teamMember.getEnterTeamMemberEmail(),false);
        selenium.enterText(enterTeamMemberFirstName,teamMember.getEnterTeamMemberFirstName(),false);
        selenium.enterText(enterTeamMemberLastName,teamMember.getEnterTeamMemberLastName(),false);
        selenium.clickOn(enterTeamMemberRole);
        String selectRoleLocator = "//li[contains(@class, 'svc-multiselect-item')][contains(text(),'" + teamMember.getSelectTeamMemberRole() + "')]";
        selenium.clickOn(By.xpath(selectRoleLocator));
        selenium.hardWait(3);
        selenium.clickOn(saveIconOfAddTeamMember);
    }

    /**
     * Click on Cancel button
     *
     * @throws InterruptedException exception
     */
    public void clickOnCancelButton() throws InterruptedException {
        selenium.clickOn(closeIconOfAddTeamMember);
    }

}
