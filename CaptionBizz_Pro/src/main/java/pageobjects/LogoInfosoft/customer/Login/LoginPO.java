package pageobjects.LogoInfosoft.customer.Login;

import dataobjects.Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class LoginPO extends BasePO {

    public LoginPO(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#user-name")
    private WebElement userNameTextBox;

    @FindBy(css = "#password")
    private WebElement passwordTextBox;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private WebElement listOfAddToCartButton;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement filterMenu;

    /**
     * Enter username, password, and click on the login button
     *
     * @param data
     * @throws InterruptedException
     */
    public void enterLoginCredentialsAndClickOnLoginButton(Login data) throws InterruptedException {
        selenium.enterText(userNameTextBox, data.getUserName(), true);
        selenium.enterText(passwordTextBox, data.getPassword(), true);
        selenium.click(loginButton);

    }
}
