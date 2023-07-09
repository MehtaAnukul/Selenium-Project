package pageObjects.swagLabs.common;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

public class HeaderPO extends BasePO {
    public HeaderPO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */
    @FindBy(xpath = "//div[@class='bm-burger-button']")
    private WebElement navigationDrawerIconMenu;

    @FindBy(xpath = "//div[@class='bm-cross-button']")
    private WebElement navigationDrawerIconMenuCrossIcon;

    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    private WebElement headerCartIconButton;


    /**
     * Click on Any headerSideBarListText
     *
     * @param headerNavigationDrawerIconSideBarText
     * @throws InterruptedException
     */

    public void clickOnHeaderNavigationDrawerIconSideBarListText(String headerNavigationDrawerIconSideBarText) throws InterruptedException {
        String headerNavigationDrawerIconSideBarTextXpath = "//a[contains(text(),'" + headerNavigationDrawerIconSideBarText + "')]";
        selenium.clickOn(By.xpath(headerNavigationDrawerIconSideBarTextXpath));
    }

    /**
     * Click on 'navigationDrawerIconMenu'
     *
     * @throws InterruptedException exception
     */
    public void clickOnHeaderNavigationDrawerIconMenu() throws InterruptedException {
        selenium.clickOn(navigationDrawerIconMenu);
    }

    /**
     * Click on 'navigationDrawerIconMenuCrossIcon'
     *
     * @throws InterruptedException exception
     */
    public void clickOnHeaderNavigationDrawerIconMenuCrossIcon() throws InterruptedException {
        selenium.clickOn(navigationDrawerIconMenuCrossIcon);
    }

    /**
     * Click on 'Header cart icon button'
     *
     * @throws InterruptedException exception
     */
    public void clickOnHeaderCartIconButton() throws InterruptedException {
        selenium.clickOn(headerCartIconButton);
    }
}


