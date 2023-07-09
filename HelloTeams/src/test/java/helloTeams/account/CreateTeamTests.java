package helloTeams.account;

import base.BaseTest;
import datafactory.TeamData;
import dataobjects.Team;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.helloteams.account.AddTeamPO;
import pageobjects.helloteams.account.LoginPO;
import pageobjects.helloteams.account.OrganizationDashboardPO;
import pageobjects.helloteams.account.TeamDashboardPO;
import pageobjects.helloteams.common.DeletePO;
import pageobjects.helloteams.common.SideBarPO;
import utilities.Constants;

public class CreateTeamTests extends BaseTest
{
    /*Test 1 - Verify that user can create 'Team' with 'Team Type' and 'Delete' a team */
    @Test
    public void verifyUserCreateTeamWithTeamType() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        AddTeamPO addTeam = new AddTeamPO(driver);
        DeletePO delete = new DeletePO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Verify user can create team");
        sideBar.clickOnIconOfCreateTeam();
        Team team = new TeamData().getTeamType1Data();
        addTeam.fillFormOfCreateTeamWithTeamTypeAndSave(team);
        selenium.refreshPage();

        Reporter.log("Step 5 - Verify team is created");
        String expectedTeamName =team.getEnterTeamName();
        Assert.assertEquals(sideBar.getTeamName(team),expectedTeamName,"Team is not created");

        Reporter.log("Step 6 - Verify that team is deleted");
       // selenium.hardWait(3);
        sideBar.clickOnTeamName(team);
        sideBar.clickOnMenuDropdown(team);
        sideBar.clickOnDeleteItemOfMenuIcon();
        delete.clickOnOkButtonForDeleteTeam();
        selenium.refreshPage();
        Assert.assertFalse(sideBar.getTeamNameForDeleteTeam(team),"Team is not deleted");
    }

    /* Test 2 - Verify that user can create 'Team' with 'Program Type or Portfolio type' and team is not deleted when click on 'Cancel' button*/
    @Test
    public void verifyUserCreateTeamWithOtherType() throws InterruptedException
    {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        AddTeamPO addTeam = new AddTeamPO(driver);
        DeletePO delete = new DeletePO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Verify user can create team");
        sideBar.clickOnIconOfCreateTeam();
        Team team = new TeamData().getTeamType2Data();
        addTeam.fillFormOfCreateTeamWithOtherTypeAndSave(team);
        selenium.hardWait(5);
        Assert.assertFalse(addTeam.isOrganizationFieldPresent());
        Assert.assertFalse((addTeam.isPurposeFieldPresent()));
        addTeam.clickOnSaveButton();
        selenium.refreshPage();

        Reporter.log("Step 5 - Verify team is created");
        String expectedTeamName =team.getEnterTeamName();
        Assert.assertEquals(sideBar.getTeamName(team),expectedTeamName,"Team is not created");

        Reporter.log("Step 6 - Verify that team is not deleted");
        String OrganizationName= Constants.Organization_Name;
        sideBar.isTeamPresent(OrganizationName, sideBar.getTeamName(team));
        sideBar.clickOnTeamName(team);
        sideBar.clickOnMenuDropdown(team);
        sideBar.clickOnDeleteItemOfMenuIcon();
        delete.clickOnCancelButtonForDeleteTeam();
        Assert.assertTrue(sideBar.isTeamPresentWhenCancelClick(team),"Team is Deleted");
    }

    /* Test 3 - Close button is work as per expected */
    @Test
    public void verifyCloseButtonWorkAsExpected() throws InterruptedException
    {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        AddTeamPO addTeam = new AddTeamPO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Verify user can not create team");
        sideBar.clickOnIconOfCreateTeam();
        addTeam.clickOnCancelButton();
        Team team = new TeamData().getTeamType2Data();
        Assert.assertFalse(sideBar.isTeamPresentWhenCancelClick(team), "Team is created");
    }

    /* Test 4 - Verify that user can create child team  */
    @Test
    public void verifyUserCreateChildTeam() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        AddTeamPO addTeam = new AddTeamPO(driver);
        DeletePO delete = new DeletePO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name of Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on Team name from Sidebar");
        Team team = new TeamData().getTeamNameForAddChildTeam();
        String OrganizationName= Constants.Organization_Name;
        sideBar.isTeamPresent(OrganizationName, sideBar.getTeamName(team));
        sideBar.clickOnTeamName(team);

        Reporter.log("Step 5 - Verify that child team is created");
        selenium.hardWait(3);
        sideBar.clickOnMenuDropdown(team);
        sideBar.clickOnTeamItemForChildTeam();
        Team team1 = new TeamData().getTeamType1Data();
        addTeam.fillFormOfCreateTeamWithTeamTypeAndSave(team1);
        selenium.refreshPage();
        sideBar.clickOnTeamName(team);
        sideBar.clickOnIconOfOpenChildTeamName();
        sideBar.clickOnTeamName(team1);
        String expectedTeamName =team1.getEnterTeamName();
        Assert.assertEquals(sideBar.getTeamName(team1),expectedTeamName,"Child team is not created");

        Reporter.log("Step 6 - Verify that child team is deleted");
        sideBar.clickOnMenuDropdown(team1);
        sideBar.clickOnDeleteItemOfMenuIcon();
        delete.clickOnOkButtonForDeleteTeam();
        selenium.refreshPage();
        sideBar.clickOnTeamName(team);
        sideBar.clickOnIconOfOpenChildTeamName();
        Assert.assertFalse(sideBar.getTeamNameForDeleteTeam(team1),"Child team is not deleted");

    }

    /* Test 5 - Verify that user can create child 'Team' with 'Program Type or Portfolio type' and child team is not deleted when click on 'Cancel' button*/
    @Test
    public void verifyUserCreateChildTeamWithOtherType() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        AddTeamPO addTeam = new AddTeamPO(driver);
        DeletePO delete = new DeletePO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL + "/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on Team name from Sidebar");
        Team team1 = new TeamData().getTeamNameForAddChildTeam();
        String OrganizationName= Constants.Organization_Name;
        sideBar.isTeamPresent(OrganizationName, sideBar.getTeamName(team1));
        sideBar.clickOnTeamName(team1);

        Reporter.log("Step 5 - Verify that child team is created");
        selenium.hardWait(3);
        sideBar.clickOnMenuDropdown(team1);
        sideBar.clickOnTeamItemForChildTeam();
        Team team = new TeamData().getTeamType2Data();
        addTeam.fillFormOfCreateTeamWithOtherTypeAndSave(team);
        Assert.assertNotNull(addTeam.isOrganizationFieldPresent());
        Assert.assertNotNull(addTeam.isPurposeFieldPresent());
        addTeam.clickOnSaveButton();
        selenium.refreshPage();
        sideBar.clickOnTeamName(team1);
        sideBar.clickOnIconOfOpenChildTeamName();
        sideBar.clickOnTeamName(team1);
        String expectedTeamName =team1.getEnterTeamName();
        Assert.assertEquals(sideBar.getTeamName(team1),expectedTeamName,"Child team with otherType is not created");

        Reporter.log("Step 6 - Verify that team is not deleted");
        sideBar.clickOnMenuDropdown(team1);
        sideBar.clickOnDeleteItemOfMenuIcon();
        delete.clickOnCancelButtonForDeleteTeam();
        Assert.assertTrue(sideBar.isTeamPresentWhenCancelClick(team1), "Team is Deleted");
    }
}
