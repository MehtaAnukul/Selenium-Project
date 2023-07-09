package pageobjects.gonnaorder.admin.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

public class ResetPasswordPO extends BasePO {

	public ResetPasswordPO(WebDriver driver) {
		super(driver);
	}


	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */


	@FindBy(xpath = "//h1")
	private WebElement updatePasswordHeading;

	@FindBy(xpath = "//input[@class='form-control']")
	private WebElement emailTextBoxValue;

	@FindBy(css = "input[formcontrolname='password']")
	private WebElement resetPasswordTextBox;

	@FindBy(css = "input[formcontrolname='confirmPassword']")
	private WebElement resetConfirmPasswordTextBox;

	@FindBy(xpath = "//button[contains(text(),'Update')]")
	private WebElement UpdateButton;

	@FindBy(className = "alert-success")
	private WebElement passwordResetSuccessMessage;

	/**
	 *Get Update Password heading text
	 * @return update password heading
	 */
	public String getUpdatePasswordHeading()
	{
		return selenium.getText(updatePasswordHeading);
	}

	/**
	 *Get entered email text
	 * @return email
	 */
	public String getEmailTextBoxValue()
	{
		return selenium.getAttributeOfInvisibleElement(emailTextBoxValue, "value");
	}



	/**
	 *Get Password reset success message
	 * @return error message
	 */
	public String getPasswordResetSuccessMessage()
	{
		return selenium.getText(passwordResetSuccessMessage);
	}

	/**
	 * Fill out Password reset details and click on update button
	 *
	 * @throws InterruptedException exception
	 */
	public void fillResetPasswordDetailAndUpdate(String ResetPassword, String ResetConfirmPassword) throws InterruptedException
	{
		selenium.enterText(resetPasswordTextBox, ResetPassword , false);
		selenium.enterText(resetConfirmPasswordTextBox, ResetConfirmPassword, false);
		selenium.clickOn(UpdateButton);
		selenium.hardWait(3);
	}

}
