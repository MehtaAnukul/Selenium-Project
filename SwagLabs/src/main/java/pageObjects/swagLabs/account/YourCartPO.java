package pageObjects.swagLabs.account;

import dataObjectsModel.ProductListModel;
import dataObjectsModel.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

import java.util.ArrayList;
import java.util.List;

public class YourCartPO extends BasePO {
    public YourCartPO(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private WebElement yourCartPageProductName;

    @FindBy(xpath = "//button[@class='btn_secondary cart_button']")
    private WebElement yourCartPageRemoveButton;

    @FindBy(xpath = "//a[@class='btn_secondary']")
    private WebElement yourCartPageContinueShoppingButton;

    @FindBy(xpath = "//a[@class='btn_action checkout_button']")
    private WebElement yourCartPageCheckoutButton;

    /*@FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> yourCarPageProductNameList;*/

    private By yourCartPageProductNameList = By.className("inventory_item_name");

    /**
     * Click on YourCart Page 'Remove' Button
     * @throws InterruptedException exception
     * Click on Multiple Product remove button using pass ProductModel as parameter
     */
    public void clickOnMultipleYourCartPageRemoveButton(ProductModel productModel) throws InterruptedException{
        String product1RemoveButton = "//div[contains(text(),'"+productModel.getProductName1()+"')]/parent::a/following-sibling::div/following-sibling::div//button";
        String product2RemoveButton = "//div[contains(text(),'"+productModel.getProductName2()+"')]/parent::a/following-sibling::div/following-sibling::div//button";
        String product3RemoveButton = "//div[contains(text(),'"+productModel.getProductName3()+"')]/parent::a/following-sibling::div/following-sibling::div//button";
        selenium.clickOn(By.xpath(product1RemoveButton));
        selenium.clickOn(By.xpath(product2RemoveButton));
        selenium.clickOn(By.xpath(product3RemoveButton));

    }

    /**
     * Click on YourCart Page RemoveButton
     *
     * @throws InterruptedException exception
     */
    public void yourCartPageRemoveButton() throws InterruptedException {
        selenium.clickOn(yourCartPageRemoveButton);
    }

    /**
     * Click on YourCart Page ContinueShoppingButton
     *
     * @throws InterruptedException exception
     */
    public void yourCartPageContinueShoppingButton() throws InterruptedException {
        selenium.clickOn(yourCartPageContinueShoppingButton);
    }

    /**
     * Click on YourCart Page CheckoutButton
     *
     * @throws InterruptedException exception
     */
    public void yourCartPageCheckoutButton() throws InterruptedException {
        selenium.clickOn(yourCartPageCheckoutButton);
    }
    /**
     * Get All Product name
     * @return List of ProductName
     */
    public List<String> getProductNameList(){
        List<WebElement> allProductNameList = selenium.waitTillAllElementsAreLocated(yourCartPageProductNameList);
        List<String> allProductNames = new ArrayList<>();
        for (WebElement webElement : allProductNameList){
            allProductNames.add(selenium.getText(webElement));
        }
        return allProductNames;
    }
}
