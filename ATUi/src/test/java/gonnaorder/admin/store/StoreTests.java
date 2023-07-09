package gonnaorder.admin.store;

import base.BaseTest;
import datafactory.gonnaorder.admin.StoreData;
import dataobjects.gonnaorder.admin.Store;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.common.HeaderPO;
import pageobjects.gonnaorder.admin.common.LeftMenuPO;
import pageobjects.gonnaorder.admin.store.StoreCreationPO;
import pageobjects.gonnaorder.admin.store.settings.StoreDetailsPO;
import utilities.Constants;

public class StoreTests extends BaseTest {
	//Test Data
	StoreData data = new StoreData();
	private Store createStoreData = data.getStoreData();


	/*Test 1 : Verify that store is created successfully*/
	@Test (enabled = false)
	public void verifyStoreCreationIsSuccessful() throws InterruptedException {
		LoginPO login = new LoginPO(driver);
		LeftMenuPO leftMenu = new LeftMenuPO(driver);
		StoreCreationPO storeCreate = new StoreCreationPO(driver);
		HeaderPO header = new HeaderPO(driver);
		StoreDetailsPO storeDetails = new StoreDetailsPO(driver);

		Reporter.log("Step 1 - Navigate to admin login page");
		selenium.navigateToPage(Constants.ADMIN_URL);

		Reporter.log("Step 2 - Entering valid sign in details and login to application");
		login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

		Reporter.log("Step 3 - Click on 'Register New Store' link");
		leftMenu.clickOnRegisterNewStoreLink();

		Reporter.log("Step 4 - Fill store creation form and click on Save button");
		Store data = createStoreData;
		storeCreate.fillCreateStoreFormAndClickOnSaveButton(data);

		Reporter.log("Step 5 - Verify that user is navigated to Store catalog page and store name is correct");
		Assert.assertEquals(header.getStoreName(), data.getName(), "Store name didn't match");
		Assert.assertTrue(selenium.getURL().endsWith("/register-store-success"), "URL didn't match, user is navigated to :" + selenium.getURL());

		Reporter.log("Step 6 - Navigate to Store > Details page and verifying store information");
		leftMenu.clickOnSettingsLink();
		Store actualData = storeDetails.getStoreInfo();
		Assert.assertEquals(actualData.getName(), createStoreData.getName(), "Store name didn't match");
		Assert.assertEquals(actualData.getAlias(), createStoreData.getAlias(), "Store Alias didn't match");
		Assert.assertEquals(actualData.getCountry(), createStoreData.getCountry(), "Store Country didn't match");
		Assert.assertEquals(actualData.getAddress().getAddressLine1(), createStoreData.getAddress().getAddressLine1(), "Store Address Line 1 didn't match");
		Assert.assertEquals(actualData.getAddress().getAddressLine2(), createStoreData.getAddress().getAddressLine2(), "Store Address Line 2 didn't match");
		Assert.assertEquals(actualData.getAddress().getPostCode(), createStoreData.getAddress().getPostCode(), "Store Address Post Code didn't match");
		Assert.assertEquals(actualData.getAddress().getRegion(), createStoreData.getAddress().getRegion(), "Store Address Region didn't match");
		Assert.assertEquals(actualData.getAddress().getCity(), createStoreData.getAddress().getCity(), "Store Address City didn't match");
	}

}
