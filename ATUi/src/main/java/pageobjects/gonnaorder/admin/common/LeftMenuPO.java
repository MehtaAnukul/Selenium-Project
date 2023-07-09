package pageobjects.gonnaorder.admin.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class LeftMenuPO extends BasePO {

	public LeftMenuPO(WebDriver driver) {
		super(driver);
	}


	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
	@FindBy(css="a[href='/manager/stores/init']")
	private WebElement registerNewStoreLink;

	@FindBy(css="a[href$='/settings']")
	private WebElement settingsLink;

	@FindBy(css="a[href$='/catalog']")
	private WebElement catalogLink;

	@FindBy(css="a[href$='/orders']")
	private WebElement ordersLink;

	@FindBy(css="a[href$='manager/user/partner']")
	private WebElement partnerProgramLink;

	/***
	 * Click on 'Register New Store' link on Menu
	 * @throws InterruptedException
	 */
	public void clickOnRegisterNewStoreLink() throws InterruptedException
	{
		selenium.click(registerNewStoreLink);
	}

	/***
	 * Click on 'Settings' link on Menu
	 * @throws InterruptedException
	 */
	public void clickOnSettingsLink() throws InterruptedException
	{
		selenium.click(settingsLink);
	}

	/***
	 * Click on 'Catalog' link on Menu
	 * @throws InterruptedException
	 */
	public void clickOnCatalogLink() throws InterruptedException
	{
		selenium.click(catalogLink);
	}

	/***
	 * Click on 'Order' link on Menu
	 * @throws InterruptedException
	 */
	public void clickOnOrdersLink() throws InterruptedException
	{
		selenium.click(ordersLink);
	}

	/**
	 * Is 'Partner Program' menu present ?
	 *
	 * @return true or false
	 * @throws InterruptedException exception
	 */
	public boolean isPartnerProgramMenuPresent() throws InterruptedException {
		String cssLocator = "a[href$='manager/user/partner']";
		return selenium.isElementPresent(By.cssSelector(cssLocator));
	}

	/**
	 * Is 'Tables' menu present ?
	 *
	 * @return true or false
	 * @throws InterruptedException exception
	 */
	public boolean isTablesMenuPresent() throws InterruptedException {
		String xpathLocator = "//app-sidebar-nav-link-content[text()='Tables']";
		return selenium.isElementPresent(By.xpath(xpathLocator));
	}

	/**
	 * Is 'Setting' menu present ?
	 *
	 * @return true or false
	 * @throws InterruptedException exception
	 */
	public boolean isSettingsMenuPresent() throws InterruptedException {
		String cssLocator = "a[href$='/settings/store-edit']";
		return selenium.isElementPresent(By.cssSelector(cssLocator));
	}

	/**
	 * Is 'Catalog' menu present ?
	 *
	 * @return true or false
	 * @throws InterruptedException exception
	 */
	public boolean isCatalogMenuPresent() throws InterruptedException {
		String xpathLocator = "//app-sidebar-nav-link-content[text()='Catalog']";
		return selenium.isElementPresent(By.xpath(xpathLocator));
	}

	/**
	 * Is 'Users' menu present ?
	 *
	 * @return true or false
	 * @throws InterruptedException exception
	 */
	public boolean isUsersMenuPresent() throws InterruptedException {
		String xpathLocator = "//app-sidebar-nav-link-content[text()='Users']";
		return selenium.isElementPresent(By.xpath(xpathLocator));
	}

	/**
	 * Is 'Subscription' menu present ?
	 *
	 * @return true or false
	 * @throws InterruptedException exception
	 */
	public boolean isSubscriptionMenuPresent() throws InterruptedException {
		String xpathLocator = "//app-sidebar-nav-link-content[text()='Subscription']";
		return selenium.isElementPresent(By.xpath(xpathLocator));
	}

}
