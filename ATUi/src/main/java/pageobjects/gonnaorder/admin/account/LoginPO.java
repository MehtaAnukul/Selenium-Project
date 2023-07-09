package pageobjects.gonnaorder.admin.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class LoginPO extends BasePO {

	public LoginPO(WebDriver driver) {
		super(driver);
	}


	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */

	@FindBy(css = "input[formcontrolname='username']")
	private WebElement emailTextbox;

	@FindBy(css = "input[formcontrolname='password']")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement loginButton;

	@FindBy(xpath = "//button[contains(text(),'SignUp')]")
	private WebElement signUpButton;

	@FindBy(css = "div[role='alert']")
	private WebElement errorMessage;

	@FindBy(className = "alert-success")
	private WebElement inviteUserSuccessMessage;

	@FindBy(css = "button.btn-link")
	private WebElement forgotPasswordLink;


	/**
	 * Fill out login details and Login to application
	 *
	 * @param email    Enter email or phone details
	 * @param password Enter password details
	 * @throws InterruptedException exception
	 */
	public void fillLoginDetailsAndPerformLogin(String email,String password) throws InterruptedException
	{
		selenium.enterText(emailTextbox, email, false);
		selenium.enterText(passwordTextbox, password, false);
		selenium.clickOn(loginButton);
		selenium.hardWait(3);
	}

	/**
	 *Get error message
	 * @return error message
	 */
	public String getErrorMessage()
	{
		return selenium.getText(errorMessage);
	}


	/***
	 * Click on 'Sign Up' button
	 * @throws InterruptedException
	 */
	public void clickOnSignUpButton() throws InterruptedException {
		selenium.clickOn(signUpButton);
	}

	/**
	 *Get invite user success message
	 * @return error message
	 */
	public String getInviteUserSuccessMessage()
	{
		return selenium.getText(inviteUserSuccessMessage);
	}


	/***
	 * Click on 'Forgot Password' Link
	 * @throws InterruptedException
	 */
	public void clickOnForgotPasswordLink() throws InterruptedException {
		selenium.clickOn(forgotPasswordLink);
	}



}
