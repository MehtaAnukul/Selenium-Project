package datafactory;

import dataobjects.Team;
import utilities.JavaHelpers;

public class TeamData {

    /**
     * Set Team Data
     *
     * @return Team object
     */
    public Team getTeamType1Data() {
        Team team = new Team();
        String timeStamp = new JavaHelpers().timeStamp();
        team.setEnterTeamName("QA" + timeStamp);
        team.setSelectedOrganizationItems("Self-Organizing");
        team.setSelectedOrganizationItems1("Virtual");
        team.setSelectedOrganizationItems2("Department");
        team.setSelectedPurposeItems("Marketing");
        return team;
    }

    /**
     * Set Program or Portfolio Data
     *
     * @return Team object
     */
    public Team getTeamType2Data() {
        Team team = new Team();
        String timeStamp = new JavaHelpers().timeStamp();
        team.setEnterTeamName("QA" + timeStamp);
        return team;
    }

    /**
     * Set Team name for Team Member creation
     *
     * @return Team object
     */
    public Team getTeamNameForAddTeamMember() {
        Team team = new Team();
        team.setEnterTeamName("Team Member Testing");
        return team;
    }

    /**
     * Set Portfolio/ Program for Team Member Creation
     *
     * @return Team object
     */
    public Team getOtherTypeTeamNameForAddTeamMember() {
        Team team = new Team();
        team.setEnterTeamName("Team Member Testing Othertype");
        return team;
    }

    /**
     * Set Team name for checking total number of members
     *
     * @return Team object
     */
    public Team getTeamNameToCheckNumbersOfMembers() {
        Team team = new Team();
        team.setEnterTeamName("Testing");
        return team;
    }

    /**
     * set team name for creating new child team
     * @return child team name
     */
    public Team getTeamNameForAddChildTeam()
    {
        Team team = new Team();
        team.setEnterTeamName("TeamDeleted");
        return team;
    }

    /**
     * set child team name
     * @return child team name
     */
    public Team getChildTeamName()
    {
        Team team = new Team();
        team.setEnterTeamName("Child Team Testing");
        return team;
    }

    /**
     * set child Team name for other type
     *
     * @return child team name;
     */
    public Team getChildOtherTypeTeamName()
    {
        Team team = new Team();
        team.setEnterTeamName("Child Team Member");
        return team;
    }
}
