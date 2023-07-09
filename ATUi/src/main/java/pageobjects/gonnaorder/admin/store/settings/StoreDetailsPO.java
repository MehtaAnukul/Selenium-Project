package pageobjects.gonnaorder.admin.store.settings;

import dataobjects.gonnaorder.admin.Address;
import dataobjects.gonnaorder.admin.Store;
import org.openqa.selenium.WebDriver;
import pageobjects.gonnaorder.admin.store.StoreBasePO;

public class StoreDetailsPO extends StoreBasePO {

	public StoreDetailsPO(WebDriver driver) {
		super(driver);
	}


	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */


	/**
	 * Get Store
	 *
	 * @return Store object
	 */
	public Store getStoreInfo() {
		Store data = new Store();
		data.setName(selenium.javascriptGetValue(nameTextbox));
		data.setDescription(selenium.javascriptGetValue(descriptionTextbox));
		data.setAlias(selenium.javascriptGetValue(aliasTextbox));
		data.setCountry(selenium.getSelectedDropdownValue(countryDropdown));
		Address address = new Address();
		address.setAddressLine1(selenium.javascriptGetValue(addressLine1Textbox));
		address.setAddressLine2(selenium.javascriptGetValue(addressLine2Textbox));
		address.setPostCode(selenium.javascriptGetValue(postCodeTextbox));
		address.setRegion(selenium.javascriptGetValue(regionTextbox));
		address.setCity(selenium.javascriptGetValue(cityTextbox));
		data.setAddress(address);
		data.setPhoneNumber(selenium.javascriptGetValue(phoneNumberTextbox));
		return data;
	}


}
