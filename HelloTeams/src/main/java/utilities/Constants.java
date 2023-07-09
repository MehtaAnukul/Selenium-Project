package utilities;

public class Constants
{
	public static final String PROPERTY_FILE ="src/main/resources/constants.properties";

	//Environment i.e. Dev,QA,Prod etc
	public static final String ENV = JavaHelpers.setSystemVariable(PROPERTY_FILE,"Env");
	
	//Setting up the URLs
	public static final String URL = JavaHelpers.getPropertyValue(PROPERTY_FILE,"url_" + ENV);

	//Selenium constants
	public static final int WEBDRIVER_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTY_FILE,"WebDriverWaitDuration"));
	public static final int MINIMUM_WEBDRIVER_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTY_FILE,"MinimumWebDriverWaitDuration"));
	public static final int PAGEFACTORY_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTY_FILE,"PageFactoryWaitDuration"));
	public static final String SCREENSHOT_LOCATION= JavaHelpers.getPropertyValue(PROPERTY_FILE,"ScreenshotLocation");

	//HelloTeams constants fields
	public static final String Organization_Name = JavaHelpers.getPropertyValue(PROPERTY_FILE,"OrganizationName");
	public static final String HeaderText1_In_TeamDashboard = JavaHelpers.getPropertyValue(PROPERTY_FILE,"HeaderText1InTeamDashboard");
	public static final String HeaderText2_In_TeamDashboard = JavaHelpers.getPropertyValue(PROPERTY_FILE,"HeaderText2InTeamDashboard");

	//DummySiteApi path
	public static final String Base_URI = JavaHelpers.getPropertyValue(PROPERTY_FILE,"BaseURI");
	public static final String Registration_Path = JavaHelpers.getPropertyValue(PROPERTY_FILE,"Registration");
	public static final String List_Of_Users = JavaHelpers.getPropertyValue(PROPERTY_FILE,"ListOfUsers");
	public static final String Put_User = JavaHelpers.getPropertyValue(PROPERTY_FILE,"UpdateUsers");

	//public static final String ;
	private Constants() 
	{
	    throw new IllegalStateException("Constants class");
	}

}


