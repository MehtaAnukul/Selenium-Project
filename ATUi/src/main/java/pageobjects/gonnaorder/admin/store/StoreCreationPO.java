package pageobjects.gonnaorder.admin.store;

import dataobjects.gonnaorder.admin.Store;
import org.openqa.selenium.WebDriver;

public class StoreCreationPO extends StoreBasePO {

	public StoreCreationPO(WebDriver driver) {
		super(driver);
	}

	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */


	/**
	 * Fill crate store form and click on Save button
	 *
	 * @param storeInfo Store object
	 * @throws InterruptedException exception
	 */
	public void fillCreateStoreFormAndClickOnSaveButton(Store storeInfo) throws InterruptedException {
		selenium.enterText(nameTextbox, storeInfo.getName(), false);
		selenium.selectDropdownValueByText(countryDropdown, storeInfo.getCountry());
		selenium.enterText(addressLine1Textbox, storeInfo.getAddress().getAddressLine1(), false);
		selenium.enterText(addressLine2Textbox, storeInfo.getAddress().getAddressLine2(), false);
		selenium.enterText(postCodeTextbox, storeInfo.getAddress().getPostCode(), false);
		selenium.enterText(regionTextbox, storeInfo.getAddress().getRegion(), false);
		selenium.enterText(cityTextbox, storeInfo.getAddress().getCity(), false);
		selenium.click(saveButton);
		selenium.waitTillElementsCountIsLessThan(saveButton, 1);
	}


}
