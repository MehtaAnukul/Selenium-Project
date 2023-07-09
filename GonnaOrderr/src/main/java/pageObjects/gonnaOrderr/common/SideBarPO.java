package pageObjects.gonnaOrderr.common;/*
package pageObjects.helloTeams.common;

//import dataobjects.Team;

import dataObjectsModel.Team;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

import java.util.ArrayList;
import java.util.List;

public class SideBarPO extends BasePO {

    public SideBarPO(WebDriver driver) {
        super(driver);
    }

    */
/*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     *//*


    @FindBy(xpath = "//div[@class='ion ion-md ion-dark ']")
    private WebElement iconOfCreateTeam;

    @FindBy(xpath = "//div[@class='tree-branch selected']//div[@data-title='Ios Menu Icon']")
    private WebElement MenuIconOfTeam;

    private By deleteItemOfMenuIcon = By.xpath("//span[contains(text(),'Delete')]");

    private By childTeamItemOfMenuIcon = By.xpath("//span[contains(text(),'Team')]");

    @FindBy(xpath = "//div[@data-title='Expand'][@class='ion ion-vsm ion-dark ']")
    private WebElement iconOfOpenChildTeamName;

    */
/**
     * Click on icon of create team
     *
     * @throws InterruptedException exception
     *//*

    public void clickOnIconOfCreateTeam() throws InterruptedException {
        selenium.hardWait(4);
        selenium.clickOn(iconOfCreateTeam);
    }

    */
/**
     * Get team name
     *
     * @return team name
     *//*

    public String getTeamName(Team team) {

        String xPathOfTeamNameLocator = "//div[contains(@class, 'tree-branch')]/div //div[2]/div[contains(text(),'" + team.getEnterTeamName() + "')]";
        return selenium.getText(By.xpath(xPathOfTeamNameLocator));
    }

    */
/**
     * Click on team name
     *
     * @throws InterruptedException exception
     *//*

    public void clickOnTeamName(Team team) throws InterruptedException {
        String xPathOfTeamNameLocator = "//div[contains(@class, 'tree-branch')]/div //div[2]/div[contains(text(),'" + team.getEnterTeamName() + "')]";
        selenium.clickOn(By.xpath(xPathOfTeamNameLocator));
    }

    */
/**
     * Check the team is present or not
     *
     * @return boolean value
     *//*

    public boolean isTeamPresentWhenCancelClick(Team team) {
        String xpathOfTeamNameLocator = "//div[contains(@class,'tree-branch')]/div //div[2]/div[text()='" + team.getEnterTeamName() + "']";
        return selenium.isElementPresent(By.xpath(xpathOfTeamNameLocator));
    }

    */
/**
     * Get list of team name present in Organization
     *
     * @return list of team name
     *//*

    public List<String> getAllTeamNameFromOrganization(String OrganizationName) {
        String xPathOfTeamNameLocator = "//div[@class='tree-branch']/div //div[2]/div";
        List<String> TeamNames = new ArrayList<>();
        List<WebElement> teams = selenium.waitTillAllElementsAreLocated(By.xpath(xPathOfTeamNameLocator));
        for (WebElement team1 : teams) {
            TeamNames.add(selenium.getText(team1));
        }
        return TeamNames;
    }

    */
/**
     * Get selected Team name from list of the team name
     *
     * @param OrganizationName
     * @param TeamNames
     * @return selected team name
     *//*

    public Boolean isTeamPresent(String OrganizationName, String TeamNames) {
        return getAllTeamNameFromOrganization(OrganizationName).contains(TeamNames);
    }

    */
/**
     * Click on Menu dropdown
     *
     * @throws InterruptedException exception
     *//*

    public void clickOnMenuDropdown(Team team) throws InterruptedException {
        selenium.clickOn(MenuIconOfTeam);
    }

    */
/**
     * Click on delete item for delete team
     *
     * @throws InterruptedException exception
     *//*

    public void clickOnDeleteItemOfMenuIcon() throws InterruptedException {
        selenium.hardWait(3);
        WebElement item = selenium.waitTillAllElementsAreLocated(deleteItemOfMenuIcon).stream().filter(e -> e.isDisplayed()).findFirst().get();
        selenium.clickOn(item);
    }

    */
/**
     * Get team name for check delete team
     *
     * @return team is deleted or not
     *//*

    public boolean getTeamNameForDeleteTeam(Team team) throws InterruptedException {
        String xpathOfTeamNameLocator = "//div[contains(@class, 'tree-branch')]/div //div[2]/div[contains(text(),'" + team.getEnterTeamName() + "')]";
        return selenium.isElementPresent(By.xpath(xpathOfTeamNameLocator));
    }

    */
/**
     * Create child team
     *
     * @throws InterruptedException exception
     *//*

    public void clickOnTeamItemForChildTeam() throws InterruptedException {
        selenium.clickOn(childTeamItemOfMenuIcon);
    }

    */
/**
     * Open child team
     *
     * @throws InterruptedException exception
     *//*

    public void clickOnIconOfOpenChildTeamName() throws InterruptedException {
        selenium.clickOn(iconOfOpenChildTeamName);
    }
}
*/
