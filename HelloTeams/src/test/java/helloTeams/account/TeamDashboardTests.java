package helloTeams.account;

import base.BaseTest;
import datafactory.TeamData;
import dataobjects.Team;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.helloteams.account.LoginPO;
import pageobjects.helloteams.account.OrganizationDashboardPO;
import pageobjects.helloteams.account.TeamDashboardPO;
import pageobjects.helloteams.common.SideBarPO;
import utilities.Constants;

public class TeamDashboardTests extends BaseTest
{
    /* Test 1 - Verify that team details presented in sidebar are same in team dashboard*/
    @Test
    public void verifyTeamDetailsSameInSidebarAndTeamDashboard() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        TeamDashboardPO teamDashboard = new TeamDashboardPO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name of Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on Team name from Sidebar");
        Team team = new TeamData().getTeamNameForAddTeamMember();
        Team team1 = new TeamData().getTeamType1Data();
        String OrganizationName= Constants.Organization_Name;
        sideBar.isTeamPresent(OrganizationName, sideBar.getTeamName(team));
        sideBar.clickOnTeamName(team);

        Reporter.log("Step 5 - verify team details are same at team dashboard and sidebar");
        Assert.assertEquals(teamDashboard.getNameOfTeamFromTeamDashboard(),sideBar.getTeamName(team),"Team name is not matched");
        Assert.assertEquals(teamDashboard.getTextOrganizationField(),Constants.HeaderText1_In_TeamDashboard,"Organization text does not match");
        Assert.assertEquals(teamDashboard.getTextPurposeField(),Constants.HeaderText2_In_TeamDashboard,"Purpose text does not match");

        Assert.assertEquals(team1.getSelectedOrganizationItems(),teamDashboard.getOrganizationItem(),"not get first item");
        Assert.assertEquals(team1.getSelectedOrganizationItems1(),teamDashboard.getOrganizationItem1(),"not get second item");
        Assert.assertEquals(team1.getSelectedOrganizationItems2(),teamDashboard.getOrganizationItem2(),"not get third item");
        Assert.assertEquals(team1.getSelectedPurposeItems(),teamDashboard.getPurposeItem(),"not get items");
        Assert.assertTrue(teamDashboard.getTeamTypeFromTeamDashboard(),"type not present");
    }

    /* Test 2 - Verify that team members are not displayed when user click on (^) icon of Team Dashboard and total number of team members are same*/
    @Test
    public void verifyTeamMembersSizeDisplayedCorrectlyAndHideWhenClickOnDisplayingTeamMemberIcon() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        TeamDashboardPO teamDashboard = new TeamDashboardPO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name from Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on team name");
        Team team = new TeamData().getTeamNameToCheckNumbersOfMembers();
        sideBar.clickOnTeamName(team);

        Reporter.log("Step 5 - Check total number of team members are same in dashboard ");
        String ExpectedResult = teamDashboard.sizeOfTeamMembersPresentInTeam()+" Team Members";
        selenium.hardWait(3);
        Assert.assertEquals(teamDashboard.getTotalNumberOfMembers(),ExpectedResult,"not matched");  //actual static bcz no other option to get total member

        Reporter.log("Step 6 - Verify that team members are not displayed");
        selenium.hardWait(3);
        teamDashboard.clickOnIconOfDisplayingTeamMember();
        Assert.assertFalse(teamDashboard.isTeamMemberPresent(),"Team Members are displayed");
    }

    /* Test 3 - Verify that child team details are same as sidebar in team dashboard */
    @Test
    public void verifyChildTeamDetailsSameInSidebarAndTeamDashboard() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        OrganizationDashboardPO organization = new OrganizationDashboardPO(driver);
        SideBarPO sideBar = new SideBarPO(driver);
        TeamDashboardPO teamDashboard = new TeamDashboardPO(driver);

        Reporter.log("Step 1 - Navigate to Login page");
        selenium.navigateToPage(Constants.URL+"/login");

        Reporter.log("Step 2 - Enter Valid user details");
        login.fillLoginDetailsAndPerformLogin(user.getEmail(), user.getPassword());

        Reporter.log("Step 3 - Click on organization name of Organization Dashboard");
        organization.clickOnOrganizationName();

        Reporter.log("Step 4 - Click on Team name from Sidebar");
        Team team = new TeamData().getTeamNameForAddChildTeam();
        Team team1 = new TeamData().getChildTeamName();
        Team childTeamDetails = new TeamData().getTeamType1Data();
        String OrganizationName= Constants.Organization_Name;
        sideBar.isTeamPresent(OrganizationName, sideBar.getTeamName(team));
        sideBar.clickOnTeamName(team);

        Reporter.log("Step 5 - Click on child team name from sidebar");
        sideBar.clickOnIconOfOpenChildTeamName();
        sideBar.clickOnTeamName(team1);

        Reporter.log("Step 6 - verify team details are same at team dashboard and sidebar");
        Assert.assertEquals(teamDashboard.getNameOfTeamFromTeamDashboard(),sideBar.getTeamName(team1),"Team name is not matched");
        Assert.assertEquals(teamDashboard.getTextOrganizationField(),Constants.HeaderText1_In_TeamDashboard,"Organization text does not match");
        Assert.assertEquals(teamDashboard.getTextPurposeField(),Constants.HeaderText2_In_TeamDashboard,"Purpose text does not match");

        Assert.assertEquals(childTeamDetails.getSelectedOrganizationItems(),teamDashboard.getOrganizationItem(),"not get first item");
        Assert.assertEquals(childTeamDetails.getSelectedOrganizationItems1(),teamDashboard.getOrganizationItem1(),"not get second item");
        Assert.assertEquals(childTeamDetails.getSelectedOrganizationItems2(),teamDashboard.getOrganizationItem2(),"not get third item");
        Assert.assertEquals(childTeamDetails.getSelectedPurposeItems(),teamDashboard.getPurposeItem(),"not get items");
        Assert.assertTrue(teamDashboard.getTeamTypeFromTeamDashboard(),"type not present");
    }

}
