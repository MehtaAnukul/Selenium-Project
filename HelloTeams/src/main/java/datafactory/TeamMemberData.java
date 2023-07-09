package datafactory;

import dataobjects.TeamMember;
import utilities.JavaHelpers;

public class TeamMemberData
{
    /**
     * Declared Variables
     */
    String timeStamp = new JavaHelpers().timeStamp();
    TeamMember teamMember = new TeamMember();
    /**
     * Set Team member data
     *
     * @return TeamMember object
     */
    public TeamMember getTeamMemberData()
    {
        teamMember.setEnterTeamMemberEmail("piyush+"+timeStamp+"@helloteams.io");
        teamMember.setEnterTeamMemberFirstName("Piyush");
        teamMember.setEnterTeamMemberLastName("User"+timeStamp);
        teamMember.setSelectTeamMemberRole("QA");
        teamMember.setSelectTeamMemberDistribution("Remote");
        teamMember.setSelectTeamMemberLocation("Brazil");
        teamMember.setSelectTeamMemberEmployment("FTE");
        return teamMember;
    }

    /**
     * Set Leaders data
     *
     * @return TeamMember object
     */
    public TeamMember getLeadersData()
    {
        teamMember.setEnterTeamMemberEmail("piyush+"+timeStamp+"@helloteams.io");
        teamMember.setEnterTeamMemberFirstName("Piyush");
        teamMember.setEnterTeamMemberLastName("User"+timeStamp);
        teamMember.setSelectTeamMemberRole("Director");
        return teamMember;
    }

}
