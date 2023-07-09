package pageObjects.swagLabs.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

public class ProductDescriptionPO extends BasePO {
    public ProductDescriptionPO(WebDriver driver) {
        super(driver);
    }

    /*
     *  All WebElements are identified by @FindBy annotation
     *  @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//button[@class='inventory_details_back_button']")
    private WebElement productDescriptionPageBackButton;

    @FindBy(xpath = "//button[@class='btn_primary btn_inventory']")
    private WebElement productDescriptionAddToCartButton;

    @FindBy(xpath = "//button[@class='btn_secondary btn_inventory']")
    private WebElement productDescriptionRemoveButton;

    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    private WebElement productDescriptionYourCartIcon;

    /**
     * Click on 'Product Description Page Back Button'
     *
     * @throws InterruptedException exception
     */
    public void productDescriptionPageBackButton() throws InterruptedException {
        selenium.clickOn(productDescriptionPageBackButton);
    }


    /**
     * Click on 'Product Description AddToCart Button'
     *
     * @throws InterruptedException exception
     */
    public void productDescriptionAddToCartButton() throws InterruptedException {
        selenium.clickOn(productDescriptionAddToCartButton);
    }

    /**
     * Click on 'Product Description Remove Button'
     *
     * @throws InterruptedException exception
     */
    public void productDescriptionRemoveButton() throws InterruptedException {
        selenium.clickOn(productDescriptionRemoveButton);
    }

    /**
     * Click on 'Product Description YourCartIcon'
     *
     * @throws InterruptedException exception
     */
    public void productDescriptionYourCartIcon() throws InterruptedException {
        selenium.clickOn(productDescriptionYourCartIcon);
    }
}
