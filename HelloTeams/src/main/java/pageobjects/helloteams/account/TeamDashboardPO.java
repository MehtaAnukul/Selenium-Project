package pageobjects.helloteams.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

import java.util.List;

public class TeamDashboardPO extends BasePO {
    public TeamDashboardPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */
    @FindBy(css = ".section-header")
    private WebElement displayTeamName;

    @FindBy(className = "header-text")
    private WebElement displayTeamType;

    @FindBy(xpath = "//div[@data-title='Edit']")
    private WebElement editIconOfTeam;

    @FindBy(xpath = "//div[contains(@style,'column-count')]/div[1][contains(@style,'padding')]/div/div[1]")
    private WebElement headerTextOfOrganizationField;

    @FindBy(xpath = "//div[contains(@style,'column-count')]/div[2][contains(@style,'padding')]/div/div[1]")
    private WebElement headerTextOfPurposeField;

    @FindBy(xpath = "//div[contains(@style,'column-count')]/div[1]//span[@class='tag-class'][1]")
    private WebElement selectedOrganizationItem;

    @FindBy(xpath = "//div[contains(@style,'column-count')]/div[1]//span[@class='tag-class'][2]")
    private WebElement selectedOrganizationItem1;

    @FindBy(xpath = "//div[contains(@style,'column-count')]/div[1]//span[@class='tag-class'][3]")
    private WebElement selectedOrganizationItem2;

    @FindBy(xpath = "//div[contains(@style,'column-count')]/div[2]//span[@class='tag-class']")
    private WebElement selectedPurposeItems;

    @FindBy(xpath = "//div[@data-title='Ios Arrow Down Icon']")
    private WebElement IconOfDisplayingTeamMember;

    @FindBy(xpath = "//div[@class='members-wrapper']//div[@data-title='Add']")
    private WebElement iconOfAddTeamMember;

    @FindBy(xpath = "//div[@data-title='Ios Add Circle Outline Icon']")
    private WebElement iconOfAddConnector;

    @FindBy(className = "svc-checkbox-checkmark")
    private List<WebElement> checkboxForDeleteTeamMember;

    @FindBy(xpath = "//div[contains(@data-title,'Delete')]")
    private WebElement iconOfDeleteTeamMember;

    @FindBy(xpath = "//div[contains(@data-title,'Ios Contact Icon')]")
    private WebElement imageIconOfTeamMember;

    @FindBy(xpath = "//div[contains(@style,'flex-direction')]//div[@class='ease']")
    private WebElement parentClassOfTeamMemberDisplay;

    @FindBy(xpath = "//span[contains(text(),'9 Team Members')]")
    private WebElement totalNumberOfMembers;

    /**
     * Click on icon of Add team member
     *
     * @throws InterruptedException exception
     */
    public void clickOnIconOfAddTeamMember() throws InterruptedException {
        selenium.clickOn(iconOfAddTeamMember);
    }

    /**
     * Get the name of Team
     *
     * @return team name
     */
    public String getNameOfTeamFromTeamDashboard() {
        return selenium.getText(displayTeamName);
    }

    /**
     * Get text of organization field
     *
     * @return text
     */
    public String getTextOrganizationField() {
        return selenium.getText(headerTextOfOrganizationField);
    }

    /**
     * Get text of purpose field
     *
     * @return text
     */
    public String getTextPurposeField() {
        return selenium.getText(headerTextOfPurposeField);
    }

    /**
     * Get selected Organization item
     *
     * @return items of organization
     */
    public String getOrganizationItem() {
        return selenium.getText(selectedOrganizationItem);
    }

    /**
     * Get selected Organization item
     *
     * @return items of organization
     */
    public String getOrganizationItem1() {
        return selenium.getText(selectedOrganizationItem1);
    }

    /**
     * Get selected Organization item
     *
     * @return items of organization
     */
    public String getOrganizationItem2() {
        return selenium.getText(selectedOrganizationItem2);
    }

    /**
     * Get selected purpose item
     *
     * @return items of purpose
     */
    public String getPurposeItem() {
        return selenium.getText(selectedPurposeItems);
    }

    /**
     * Check team type is present or not
     *
     * @return true
     */
    public Boolean getTeamTypeFromTeamDashboard() {
        return selenium.isElementPresent(displayTeamType);
    }

    /**
     * Click on Checkbox for select team member to delete
     */
    public void clickOnCheckboxForSelectTeamMember() {
        for (WebElement el : checkboxForDeleteTeamMember) {
            if (!el.isSelected()) {
                el.click();
            }
        }
    }

    /**
     * Click on delete icon of team dashboard
     *
     * @throws InterruptedException exception
     */
    public void clickOnDeleteIconForDeleteTeamMember() throws InterruptedException {
        selenium.clickOn(iconOfDeleteTeamMember);
    }

    /**
     * Click on IconOfDisplayingTeamMember
     *
     * @throws InterruptedException exception
     */
    public void clickOnIconOfDisplayingTeamMember() throws InterruptedException {
        selenium.clickOn(IconOfDisplayingTeamMember);
    }

    /**
     * Check if value is present
     *
     * @return present or not
     */
    public boolean isTeamMemberPresent() {
        return selenium.isElementPresent(parentClassOfTeamMemberDisplay);
    }

    /**
     * Get size of one team team members through checkbox
     *
     * @return count of team members
     */
    public int sizeOfTeamMembersPresentInTeam() {
        int i = checkboxForDeleteTeamMember.size();
        return i;
    }

    /**
     * Get total number of team members
     *
     * @return number of members with string text
     */
    public String getTotalNumberOfMembers() {
        return selenium.getText(totalNumberOfMembers);
    }

}
