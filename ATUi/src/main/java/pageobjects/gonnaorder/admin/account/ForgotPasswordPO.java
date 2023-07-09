package pageobjects.gonnaorder.admin.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class ForgotPasswordPO extends BasePO {

	public ForgotPasswordPO(WebDriver driver) {
		super(driver);
	}


	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */

	@FindBy(css = "input[formcontrolname='email']")
	private WebElement emailTextBox;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	private WebElement submitButton;

	@FindBy(css = "div[role='alert']")
	private WebElement errorMessage;



	/**
	 *Get error message
	 * @return error message
	 */
	public String getErrorMessage()
	{
		return selenium.getText(errorMessage);
	}


	/**
	 * Fill out login details and Login to application
	 *
	 * @param email    Enter email details
	 * @throws InterruptedException exception
	 */
	public void fillForgotPasswordDetailAndSubmit(String email) throws InterruptedException
	{
		selenium.enterText(emailTextBox, email, false);
		selenium.clickOn(submitButton);
		selenium.hardWait(3);
	}


}
