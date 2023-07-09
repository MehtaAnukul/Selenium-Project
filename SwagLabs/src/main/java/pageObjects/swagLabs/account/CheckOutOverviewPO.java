package pageObjects.swagLabs.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

public class CheckOutOverviewPO extends BasePO {
    public CheckOutOverviewPO(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='cart_cancel_link btn_secondary']")
    private WebElement cancelButton;

    @FindBy(xpath = "//a[@class='btn_action cart_button']")
    private WebElement finishButton;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    private WebElement itemTotalPrice;


    /**
     * Click on 'ProductName'
     *
     * @throws InterruptedException exception
     */
    public void clickOnProductName(String productName) throws InterruptedException {
        String productNameXpath = "//div[contains(text(),'" + productName + "')]";
        selenium.clickOn(By.xpath(productNameXpath));
    }

    /**
     * Click on Cancel button
     *
     * @throws InterruptedException exception
     */
    public void clickOnCancelButton() throws InterruptedException {
        selenium.clickOn(cancelButton);
    }

    /**
     * Click on Finish button
     *
     * @throws InterruptedException exception
     */
    public void clickOnFinishButton() throws InterruptedException {
        selenium.clickOn(finishButton);
    }
    /**
     * Total item price
     * @return item total price
     */
    public String getItemTotalPrice() throws InterruptedException{
        return selenium.getText(itemTotalPrice);
    }
}
