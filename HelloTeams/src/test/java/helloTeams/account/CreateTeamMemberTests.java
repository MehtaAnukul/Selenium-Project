package helloTeams.account;

import base.BaseTest;
import datafactory.TeamData;
import datafactory.TeamMemberData;
import dataobjects.Team;
import dataobjects.TeamMember;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.helloteams.account.*;
import pageobjects.helloteams.common.DeletePO;
import pageobjects.helloteams.common.SideBarPO;
import utilities.Constants;

public class CreateTeamMemberTests extends BaseTest
{
    /* Test 1 - Verify that user can create a team member and Delete a team member */
    @Test
    public void verifyUserCreateTeamMember() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        TeamDashboardPO teamDashboard = new TeamDashboardPO(driver);
        AddTeamMembersPO addTeamMember = new AddTeamMembersPO(driver);
        DeletePO delete = new DeletePO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on team name");
        Team team = new TeamData().getTeamNameForAddTeamMember();
        sideBar.clickOnTeamName(team);                      //direct call constant team name

        Reporter.log("Step 5 - Verify user can Add team member");
        teamDashboard.clickOnIconOfAddTeamMember();
        TeamMember teamMember = new TeamMemberData().getTeamMemberData();
        addTeamMember.fillFormOfTeamMemberAndSave(teamMember);

        Reporter.log("Step 6 - Verify that teamMember is created");
        String expectedName = teamMember.getEnterTeamMemberFirstName()+teamMember.getEnterTeamMemberLastName();
        Assert.assertEquals(teamMember.getEnterTeamMemberFirstName() + teamMember.getEnterTeamMemberLastName(),expectedName,"name is not displayed");
        String expectedEmailId = teamMember.getEnterTeamMemberEmail();
        Assert.assertEquals(teamMember.getEnterTeamMemberEmail(),expectedEmailId,"Email id is not displayed");
        String expectedRole = teamMember.getSelectTeamMemberRole();
        Assert.assertEquals(teamMember.getSelectTeamMemberRole(),expectedRole,"Role is not displayed");
        selenium.hardWait(5);

        Reporter.log("Step 7 - Verify that teamMembers are deleted");
        teamDashboard.clickOnCheckboxForSelectTeamMember();
        teamDashboard.clickOnDeleteIconForDeleteTeamMember();
        delete.clickOnOkButtonOfDeleteTeamMembers();
        Assert.assertFalse(teamDashboard.isTeamMemberPresent(),"Team members are not deleted");
    }

    /* Test 2 - Verify that Cancel button is work as expected */
    @Test
    public void verifyCancelButtonAsExpected() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        TeamDashboardPO teamDashboard = new TeamDashboardPO(driver);
        AddTeamMembersPO addTeamMember = new AddTeamMembersPO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Verify cancel button work as expected");
        Team team = new TeamData().getTeamNameForAddTeamMember();
        sideBar.clickOnTeamName(team);
        teamDashboard.clickOnIconOfAddTeamMember();
        addTeamMember.clickOnCancelButton();
    }

    /* Test 3 - Verify that user can create leaders and leaders are not deleted when click on 'Cancel' button */
    @Test
    public void verifyUserCreateLeaders() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        TeamDashboardPO teamDashboard = new TeamDashboardPO(driver);
        AddTeamMembersPO addTeamMember = new AddTeamMembersPO(driver);
        DeletePO delete = new DeletePO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on team name");
        Team team = new TeamData().getOtherTypeTeamNameForAddTeamMember();
        sideBar.clickOnTeamName(team);

        Reporter.log("Step 5 - Verify user can Add team leaders");
        teamDashboard.clickOnIconOfAddTeamMember();
        TeamMember teamMember = new TeamMemberData().getLeadersData();
        addTeamMember.fillFormOfLeadersAndSave(teamMember);

        Reporter.log("Step 6 - Verify leader is created");
        String expectedName = teamMember.getEnterTeamMemberFirstName()+teamMember.getEnterTeamMemberLastName();
        Assert.assertEquals(teamMember.getEnterTeamMemberFirstName() + teamMember.getEnterTeamMemberLastName(),expectedName,"name is not displayed");
        String expectedEmailId = teamMember.getEnterTeamMemberEmail();
        Assert.assertEquals(teamMember.getEnterTeamMemberEmail(),expectedEmailId,"Email id is not displayed");
        String expectedRole = teamMember.getSelectTeamMemberRole();
        Assert.assertEquals(teamMember.getSelectTeamMemberRole(),expectedRole,"Role is not displayed");
        selenium.hardWait(5);

        Reporter.log("Step 7 - Verify leaders are selected but not deleted");
        teamDashboard.clickOnCheckboxForSelectTeamMember();
        teamDashboard.clickOnDeleteIconForDeleteTeamMember();
        delete.clickOnCancelButtonOfDeleteTeamMembers();
        Assert.assertTrue(teamDashboard.isTeamMemberPresent(),"Team Members are deleted");
    }

    /* Test 4 - Verify that user can create child team member and Delete team member */
    @Test
    public void verifyUserCreateChildTeam() throws InterruptedException
    {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        TeamDashboardPO teamDashboard = new TeamDashboardPO(driver);
        AddTeamMembersPO addTeamMember = new AddTeamMembersPO(driver);
        DeletePO delete = new DeletePO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on team name");
        Team team = new TeamData().getTeamNameForAddChildTeam();
        sideBar.clickOnTeamName(team);
        //direct call constant team name

        Reporter.log("Step 5 - Click on child team name");
        Team team1 = new TeamData().getChildTeamName();
        sideBar.clickOnIconOfOpenChildTeamName();
        sideBar.clickOnTeamName(team1);

        Reporter.log("Step 6 - Verify user can Add team member");
        teamDashboard.clickOnIconOfAddTeamMember();
        TeamMember teamMember = new TeamMemberData().getTeamMemberData();
        addTeamMember.fillFormOfTeamMemberAndSave(teamMember);

        Reporter.log("Step 7 - Verify that teamMember is created");
        String expectedName = teamMember.getEnterTeamMemberFirstName()+teamMember.getEnterTeamMemberLastName();
        Assert.assertEquals(teamMember.getEnterTeamMemberFirstName() + teamMember.getEnterTeamMemberLastName(),expectedName,"name is not displayed");
        String expectedEmailId = teamMember.getEnterTeamMemberEmail();
        Assert.assertEquals(teamMember.getEnterTeamMemberEmail(),expectedEmailId,"Email id is not displayed");
        String expectedRole = teamMember.getSelectTeamMemberRole();
        Assert.assertEquals(teamMember.getSelectTeamMemberRole(),expectedRole,"Role is not displayed");
        selenium.hardWait(5);

        Reporter.log("Step 8 - Verify that teamMembers are deleted");
        teamDashboard.clickOnCheckboxForSelectTeamMember();
        teamDashboard.clickOnDeleteIconForDeleteTeamMember();
        delete.clickOnOkButtonOfDeleteTeamMembers();
        Assert.assertFalse(teamDashboard.isTeamMemberPresent(),"Child Team members are not deleted");
    }

    /* Test 5 - Verify that user can create child leaders and leader are not deleted when click on 'Cancel' button */
    @Test
    public void verifyUserCreateChildLeaders() throws InterruptedException  {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        TeamDashboardPO teamDashboard = new TeamDashboardPO(driver);
        AddTeamMembersPO addTeamMember = new AddTeamMembersPO(driver);
        DeletePO delete = new DeletePO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on team name");
        Team team = new TeamData().getTeamNameForAddChildTeam();
        sideBar.clickOnTeamName(team);

        Reporter.log("Step 5 - Click on Child team name");
        sideBar.clickOnIconOfOpenChildTeamName();
        Team team1 = new TeamData().getChildOtherTypeTeamName();
        sideBar.clickOnTeamName(team1);

        Reporter.log("Step 6 - Verify user can Add team leaders");
        teamDashboard.clickOnIconOfAddTeamMember();
        TeamMember teamMember = new TeamMemberData().getLeadersData();
        addTeamMember.fillFormOfLeadersAndSave(teamMember);

        Reporter.log("Step 7 - Verify leader is created");
        String expectedName = teamMember.getEnterTeamMemberFirstName()+teamMember.getEnterTeamMemberLastName();
        Assert.assertEquals(teamMember.getEnterTeamMemberFirstName() + teamMember.getEnterTeamMemberLastName(),expectedName,"name is not displayed");
        String expectedEmailId = teamMember.getEnterTeamMemberEmail();
        Assert.assertEquals(teamMember.getEnterTeamMemberEmail(),expectedEmailId,"Email id is not displayed");
        String expectedRole = teamMember.getSelectTeamMemberRole();
        Assert.assertEquals(teamMember.getSelectTeamMemberRole(),expectedRole,"Role is not displayed");
        selenium.hardWait(5);

        Reporter.log("Step 8 - Verify leaders are selected but not deleted");
        teamDashboard.clickOnCheckboxForSelectTeamMember();
        teamDashboard.clickOnDeleteIconForDeleteTeamMember();
        delete.clickOnCancelButtonOfDeleteTeamMembers();
        Assert.assertTrue(teamDashboard.isTeamMemberPresent(),"Child Team Members are deleted");
    }

}
