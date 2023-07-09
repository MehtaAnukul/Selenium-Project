package utilities;

public class Constants
{
	public static final String PROPERTYFILE ="src/main/resources/constants.properties";

	//Environment i.e. Dev,QA,Prod etc
	public static final String Env = JavaHelpers.setSystemVariable(PROPERTYFILE, "Env");

	//Setting up the URLs
	public static final String ADMIN_URL = JavaHelpers.getPropertyValue(PROPERTYFILE,"admin_url_" + Env);

	//Email constants
	public static final String PAYPALURL = "https://www.paypal.com/paypalme/";

	//Login Details
	public static final String EMAIL = JavaHelpers.getPropertyValue(PROPERTYFILE, "email_" + Env);
	public static final String PASSWORD = JavaHelpers.getPropertyValue(PROPERTYFILE,"password_" + Env);
	public static final String ACCOUNTNAME = JavaHelpers.getPropertyValue(PROPERTYFILE,"accountName_" + Env);

	//Invite User Details
	public static final String InviteUserEMAIL = JavaHelpers.getPropertyValue(PROPERTYFILE, "inviteUserEmail_" + Env);
	public static final String InviteUserPASSWORD = JavaHelpers.getPropertyValue(PROPERTYFILE,"inviteUserPassword_" + Env);
	public static final String InviteUserACCOUNTNAME = JavaHelpers.getPropertyValue(PROPERTYFILE,"inviteUserAccountName_" + Env);

	//Email constants
	public static final String MAILINATORNAME = "mailinator";
	public static final String MAILINATORURL = "https://www.mailinator.com/v3/index.jsp?zone=public&query=";

	//Selenium constants
	public static final int WEBDRIVER_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"WebDriverWaitDuration"));
	public static final int MINIMUM_WEBDRIVER_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"MinimumWebDriverWaitDuration"));
	public static final int PAGEFACTORY_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"PageFactoryWaitDuration"));

	//Other
	public static final String SCREENSHOT_LOCATION= JavaHelpers.getPropertyValue(PROPERTYFILE,"ScreenshotLocation");

	//Store Details
	public static final String StoreName =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName_" + Env);
	public static final String StoreAliasName =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName_" + Env);
	public static final String StoreId =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId_" + Env);
	public static final String StoreCustomerUiUrl = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl_" + Env);
	public static final String StoreCategory =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory_" + Env);
	public static final String StoreOffer =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer_" + Env);
	public static final String StoreOfferPrice =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice_" + Env);
	public static final String StoreTable =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable_" + Env);

	//New Store Details for Ordering
	public static final String StoreName3 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName3_" + Env);
	public static final String StoreAliasName3 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName3_" + Env);
	public static final String StoreId3 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId3_" + Env);
	public static final String StoreCustomerUiUrl3 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl3_" + Env);
	public static final String StoreCategory3 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory3_" + Env);
	public static final String StoreOffer3 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer3_" + Env);
	public static final String StoreOfferPrice3 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice3_" + Env);
	public static final String StoreTable3 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable3_" + Env);

	//Another New Store Details for Ordering
	public static final String StoreName4 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName4_" + Env);
	public static final String StoreAliasName4 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName4_" + Env);
	public static final String StoreId4 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId4_" + Env);
	public static final String StoreCustomerUiUrl4 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl4_" + Env);
	public static final String StoreCategory4 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory4_" + Env);
	public static final String StoreOffer4 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer4_" + Env);
	public static final String StoreOfferPrice4 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice4_" + Env);
	public static final String StoreTable4 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable4_" + Env);

	//Another New Store Details for Ordering
	public static final String storeName6 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName6_" + Env);
	public static final String StoreAliasName6 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName6_" + Env);
	public static final String StoreId6 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId6_" + Env);
	public static final String StoreCustomerUiUrl6 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl6_" + Env);
	public static final String StoreCategory6 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory6_" + Env);
	public static final String StoreOffer6 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer6_" + Env);
	public static final String StoreOfferPrice6 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice6_" + Env);
	public static final String StoreTable6 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable6_" + Env);

	//Another New Store Details for Ordering
	public static final String storeName8 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName8_" + Env);
	public static final String StoreAliasName8 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName8_" + Env);
	public static final String StoreId8 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId8_" + Env);
	public static final String StoreCustomerUiUrl8 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl8_" + Env);
	public static final String StoreCategory8 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory8_" + Env);
	public static final String StoreOffer8 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer8_" + Env);
	public static final String StoreOfferPrice8 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice8_" + Env);
	public static final String StoreTable8 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable8_" + Env);


	//Store Details for payment
	public static final String StoreName1 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName1_" + Env);
	public static final String StoreAliasName1 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName1_" + Env);
	public static final String StoreId1 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId1_" + Env);
	public static final String StoreCustomerUiUrl1 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl1_" + Env);
	public static final String StoreCategory1 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory1_" + Env);
	public static final String StoreOffer1 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer1_" + Env);
	public static final String StoreOfferPrice1 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice1_" + Env);
	public static final String StoreTable1 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable1_" + Env);

	//New Store Details for payment
	public static final String StoreName2 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName2_" + Env);
	public static final String StoreId2 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId2_" + Env);
	public static final String StoreCustomerUiUrl2 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl2_" + Env);
	public static final String StoreCategory2 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory2_" + Env);
	public static final String StoreOffer2 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer2_" + Env);
	public static final String StoreOfferPrice2 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice2_" + Env);
	public static final String StoreTable2 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable2_" + Env);

	//New Store Details for Option Group
	public static final String StoreName5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName5_" + Env);
	public static final String StoreAliasName5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName5_" + Env);
	public static final String StoreId5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId5_" + Env);
	public static final String StoreCustomerUiUrl5 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl5_" + Env);
	public static final String StoreCategory5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory5_" + Env);
	public static final String StoreOffer5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer5_" + Env);
	public static final String StoreOptionGroup5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOptionGroup5_" + Env);
	public static final String StoreOptionGroup15 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOptionGroup15_" + Env);
	public static final String StoreOfferPrice5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice5_" + Env);
	public static final String StoreTable5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable5_" + Env);
	public static final String StoreOfferFloatPrice5 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferFloatPrice5_" + Env);

	//New Store Details for Payment Provider
	public static final String StoreName7 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName7_" + Env);
	public static final String StoreAliasName7 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName7_" + Env);
	public static final String StoreId7 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId7_" + Env);
	public static final String StoreCustomerUiUrl7 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl7_" + Env);
	public static final String StoreCategory7 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory7_" + Env);
	public static final String StoreOffer7 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer7_" + Env);
	public static final String StoreOfferPrice7 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice7_" + Env);
	public static final String StoreTable7 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable7_" + Env);

	//New Store Details for Payment Provider
	public static final String StoreName9 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName9_" + Env);
	public static final String StoreAliasName9 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName9_" + Env);
	public static final String StoreId9 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId9_" + Env);
	public static final String StoreCustomerUiUrl9 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl9_" + Env);
	public static final String StoreCategory9 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory9_" + Env);
	public static final String StoreOffer9 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer9_" + Env);
	public static final String StoreOfferPrice9 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice9_" + Env);
	public static final String StoreTable9 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable9_" + Env);

	//New Store Details for Date format Test
	public static final String StoreName10 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeName10_" + Env);
	public static final String StoreAliasName10 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeAliasName10_" + Env);
	public static final String StoreId10 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeId10_" + Env);
	public static final String StoreCustomerUiUrl10 = JavaHelpers.getPropertyValue(PROPERTYFILE,"storeUrl10_" + Env);
	public static final String StoreCategory10 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeCategory10_" + Env);
	public static final String StoreOffer10 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOffer10_" + Env);
	public static final String StoreOfferPrice10 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeOfferPrice10_" + Env);
	public static final String StoreTable10 =  JavaHelpers.getPropertyValue(PROPERTYFILE,"storeTable10_" + Env);

}


