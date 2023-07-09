package utilities;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SeleniumHelpers 
{
	WebDriver driver;
	JavaHelpers helper;
	Actions actions;
	
	public SeleniumHelpers(WebDriver driver)
	{
		this.driver = driver;
		helper = new JavaHelpers();
		actions = new Actions(driver);
	}
	
	// Take screenshot
		/**
	 * Take screenshot of the web page
	 *
	 * @param fileName screenshot file name
	 * @throws IOException
	 */
	public void takeScreenshot(String fileName) throws IOException
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(scrFile,
				new File(Constants.SCREENSHOT_LOCATION + "\\" + fileName + helper.getTimeStamp("_yyyyMMdd_HHmmss") + ".png"));
	}


	//Elements 
		/**
	 * Enter text to input field
	 * @param e WebElement object
	 * @param text input text
	 * @param clear set true if want to clear field else set false
	 */
	public void enterText(WebElement e, String text, boolean clear)
	{
		e = waitTillElementIsClickable(e);
		if(clear)
		{
			e.clear();
		}
		e.sendKeys(text);
	}
	
	/**
	 * Enter text to input field
	 * @param by By object
	 * @param text input text
	 * @param clear set true if want to clear field else set false
	 */
	public void enterText(By by, String text, boolean clear)
	{
		WebElement e = waitTillElementIsClickable(by);
		if(clear)
		{
			e.clear();
		}
		e.sendKeys(text);
	}

	/**
	 * Enter Key strokes to textbox
	 * @param e WebElement object
	 * @param key Keys value
	 */
	public void enterText(WebElement e, Keys key) {
		e.sendKeys(key);
	}

	/**
	 * Clear text box using Keys
	 * @param e WebElement Object
	 */
	public void clearTextboxUsingKeys(WebElement e)
	{
		e = waitTillElementIsClickable(e);
		e.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
	}

	/**
	 * Enter text to input field
	 * @param e WebElement object
	 * @param text input text
	 * @param clear set true if want to clear field else set false
	 * @throws InterruptedException
	 */
	public void enterTextCharacterByCharacter(WebElement e, String text, boolean clear) throws InterruptedException
	{
		e = waitTillElementIsClickable(e);
		if(clear)
		{
			e.clear();
		}

		for (int i = 0; i < text.length(); i++)
		{
	        char c = text.charAt(i);
	        String s = new StringBuilder().append(c).toString();
	        e.sendKeys(s);
	        Thread.sleep(500); // Waiting for 0.5 second
	    }
	}

	/**
	 * Double click and enter text to input field
	 * @param e WebElement object
	 * @param text input text
	 * @throws InterruptedException
	 */
	public void fillFieldAfterDoubleClick( WebElement e, String text) {
		Actions actions = new Actions(driver);
		actions.moveToElement(e);
		actions.doubleClick();
		actions.sendKeys(text);
		actions.build().perform();
	}

	/**
	 * Get Text from field
	 * @param e WebElement object
	 * @return text from field
	 */
	public String getText(WebElement e)
	{
		return waitTillElementIsVisible(e).getText().trim();
	}


	/**
	 * Get Text from disable field
	 * @param e WebElement object
	 * @return value of disable filed
	 */
	public String getAttributeOfInvisibleElement(WebElement e, String value)
	{
		return e.getAttribute(value);
	}

	/**
	 * Get Text from field
	 * @param by By object
	 * @return text from field
	 */
	public String getText(By by)
	{
		return waitInCaseElementVisible(by,10).getText().trim();
	}

	/**
	 * Click on Element
	 * @param e WebElement object
	 * @throws InterruptedException 
	 */
	public void clickOn(WebElement e) throws InterruptedException
	{
		waitTillElementIsClickable(e).click();
		waitForJavascriptToLoad();
	}
	
	/**
	 * Click on Element
	 * @param e WebElement object
	 * @throws InterruptedException 
	 */
	public void click(WebElement e) throws InterruptedException
	{
		e.click();
		waitForJavascriptToLoad();
	}

	/**
	 * Click on Element
	 * @param by By object
	 * @throws InterruptedException 
	 */
	public void click(By by) throws InterruptedException
	{
		waitTillElementIsClickable(by).click();
		waitForJavascriptToLoad();
	}

	/**
	 * To determine whether WebElement has given Attribute or not
	 * @param e WebElement object
	 * @param attributeName attribute name e.g. style
	 * @return boolean
	 */
	public boolean isElementAtrributePresent(WebElement e, String attributeName)
	{
		return e.getAttribute(attributeName) != null;
	}

	/**
	 * To get Element attribute value
	 * @param e WebElement object
	 * @param attributeName attribute name e.g. style
	 * @return attribute value
	 */
	public String getElementAttributeValue(WebElement e, String attributeName)
	{
		if(isElementAtrributePresent(e,attributeName))
		{
			return e.getAttribute(attributeName);
		}
		return "Attribute" + attributeName +" not found";
	}

	public String getElementCssValue(WebElement e, String propertyName)
	{
		return waitInCaseElementVisible(e,10).getCssValue(propertyName).trim();
	}


	/**
	 * method verify whether element is present on screen
	 *
	 * @param targetElement element to be present
	 * @return true if element is present else throws exception
	 */
	public Boolean isElementPresent(By targetElement)
	{
		return waitInCaseElementVisible(targetElement,Constants.WEBDRIVER_WAIT_DURATION)!=null && waitInCaseElementVisible(targetElement,Constants.WEBDRIVER_WAIT_DURATION).isDisplayed();
	}

	/**
	 * method verify whether element is present on screen
	 *
	 * @param targetElement element to be present
	 * @return true if element is present else throws exception
	 */
	public Boolean isElementPresent(WebElement targetElement)
	{
		return waitInCaseElementVisible(targetElement,Constants.WEBDRIVER_WAIT_DURATION)!=null && waitInCaseElementVisible(targetElement,Constants.WEBDRIVER_WAIT_DURATION).isDisplayed();
	}


	/**
	 * method verify whether element is enabled on screen
	 *
	 * @param targetElement element to be enabled
	 * @return true if element is enabled else throws exception
	 */
	public Boolean isElementEnabled(By targetElement)
	{
		return driver.findElement(targetElement).isEnabled();
	}


	/**
	 * method verify whether element is enabled on screen
	 *
	 * @param targetElement element to be enabled
	 * @return true if element is enabled else throws exception
	 */
	public Boolean isElementEnabled(WebElement targetElement)
	{
		return targetElement.isEnabled();
	}

	//Checkboxes

	/**
	 * Select / De-select checkbox
	 * @param e WebElement object
	 * @param select true if you want to select it, false if you want to de-select it
	 */
	public void checkbox(WebElement e, boolean select)
	{
		if(select)
		{
			if(!e.isSelected())
			{
				waitTillElementIsClickable(e).click();
			}
		}
		else
		{
			if(e.isSelected())
			{
				waitTillElementIsClickable(e).click();
			}
		}
	}

	/**
	 * Is checkbox selected ?
	 * @param e WebElement object
	 * @return true / false
	 */
	public boolean isCheckboxSelected(WebElement e)
	{
		return e.isSelected();
	}

	/**
	 * Is selected ?
	 * @param e WebElement object
	 * @return true / false
	 */
	public boolean isSelected(WebElement e)
	{
		return e.isSelected();
	}

	//Waits
	/**
	 * To wait until element is clickable
	 * @param e WebElement object
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsClickable(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.elementToBeClickable(e));
		 return e;
	}

	/**
	 * To wait until element is clickable
	 * @param by By object
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsClickable(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * o wait until element is visible
	 * @param e WebElement object
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsVisible(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		wait.until(ExpectedConditions.visibilityOf(e));
		return e;
	}
	/**
	 * o wait until element is visible
	 * @param e WebElement object
	 * @param waitDurationInSeconds wait duration in seconds
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsVisible(WebElement e, int waitDurationInSeconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
		 wait.until(ExpectedConditions.visibilityOf(e));
		 return e;
	}

	/**
	 * o wait until element is visible
	 * @param by By object
	 * @param waitDurationInSeconds wait duration in seconds
	 * @return WebElement object
	 */
	public void waitTillElementIsVisible(By by, int waitDurationInSeconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Wait until element is invisible
	 * @param e WebElement object
	 */
	public void waitTillElementIsNotVisible(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.invisibilityOf(e));
	}

	/**
	 * Wait until element is invisible
	 * @param by By object
	 */
	public void waitTillElementIsNotVisible(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * Wait until element is invisible
	 * @param e WebElement object
	 * @param waitDurationInSeconds wait duration in seconds
	 */
	public void waitTillElementIsNotVisible(WebElement e,int waitDurationInSeconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver, waitDurationInSeconds);
		 wait.until(ExpectedConditions.invisibilityOf(e));
	}

	/**
	 * Wait until element is invisible
	 * @param waitDurationInSeconds wait duration in seconds
	 * @param by By object
	 */
	public void waitTillElementIsNotVisible(By by, int waitDurationInSeconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver,  waitDurationInSeconds);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * Wait for specified duration and check if element is visible or not
	 * @param e WebElement object
	 * @param duration wait duration in seconds
	 * @return WebElement if visible or null if not visible
	 */
	public WebElement waitInCaseElementVisible(WebElement e, int duration)
	{
		 WebDriverWait wait = new WebDriverWait(driver, duration);
		 try
		 {
			 return wait.until(ExpectedConditions.visibilityOf(e));
		 }
		 catch (Exception ex)
		 {
			 return null;
		 }
	}

	/**
	 * Wait for specified duration and check if element is visible or not
	 * @param by By object
	 * @param duration wait duration in seconds
	 * @return WebElement if visible or null if not visible
	 */
	public WebElement waitInCaseElementVisible(By by, int duration)
	{
		 WebDriverWait wait = new WebDriverWait(driver, duration);
		 try
		 {
			 return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		 }
		 catch (Exception ex)
		 {
			 return null;
		 }
	}

	/**
	 * Wait for specified duration and check if element is clickable or not
	 * @param e WebElement object
	 * @param duration wait duration in seconds
	 * @return WebElement if clickable or null if not visible
	 */
	public WebElement waitInCaseElementClickable(WebElement e, int duration)
	{
		 WebDriverWait wait = new WebDriverWait(driver, duration);
		 try
		 {
			 return wait.until(ExpectedConditions.elementToBeClickable(e));
		 }
		 catch (Exception ex)
		 {
			 return null;
		 }
	}

	/**
	 * Wait till Element count is less than provided number
	 * @param by By object
	 * @param count provide number
	 */
	public void waitTillElementsCountIsLessThan(By by, int count)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.numberOfElementsToBeLessThan(by, count));
	}

	/**
	 * Wait till Element count is more than provided number
	 * @param by By object
	 * @param count provide number
	 */
	public void waitTillElementsCountIsMoreThan(By by, int count)
	{
		WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, count));
	}

	/**
	 * Wait till Element count is equal to provided number
	 * @param by By object
	 * @param count provide number
	 */
	public void waitTillElementsCountIsEqualTo(By by, int count)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.numberOfElementsToBe(by, count));
	}

	/**
	 * Wait till frame is available for switching
	 * @param e WebElement object
	 */
	public void waitTillframeToBeAvailableAndSwitchToIt(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(e));
	}

	/**
	 * Wait till element not attached to DOM
	 * @param e WebElement object
	 */
	public void waitTillElementNotAttachedToDOM(WebElement e)
	{
		WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		wait.until(ExpectedConditions.stalenessOf(e));
	}

	/**
	 * Waiting before performing next action
	 * @param seconds provide duration e.g. 1,2 etc
	 * @throws InterruptedException
	 */
	public void hardWait(int seconds) throws InterruptedException
	{
		Thread.sleep(seconds * 1000);
	}

	/**
	 * Wait till all elements are located
	 * @param by By object
	 * @return List of WebElement
	 */
	public List<WebElement> waitTillAllElementsAreLocated(By by)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		}
		catch(Exception e)
		{
			return new ArrayList<>();
		}
	}
	
	/**
	 * Wait till element is refreshed
	 * @param e WebElement object
	 * @return WebElement object
	 */
	public WebElement waitTillElementIsRefreshed(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(e)));
	}
	
	/** This function will wait for page to load (waiting for java script to finish loading) before moving further
     *  
     * @param WaitTime  Maximum time is the time out time. if the page loading completes before timeout, code will process 
     * @throws InterruptedException  
     */
    public  void waitForJavascriptToLoad() throws InterruptedException  
    { 
	    Thread.sleep(1000); 
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>()  
	    { 
	        public Boolean apply(WebDriver driver)  
	        { 
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"); 
	        } 
	     }; 
	     Wait <WebDriver> wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION); 
	     try  
	     { 
	    	 wait.until(expectation); 
	     } 
	     catch(Exception e)  
	     { 
	    	 e.printStackTrace(); 
	     } 
	     catch(Error e) 
	     { 
	    	 e.printStackTrace(); 
	     } 
    } 


	
	//Navigation
	public void navigateToPage(String url)
	{
		driver.get(url);
	}
	
	public void refreshPage()
	{
		driver.navigate().refresh();
	}
	
	public void back()
	{
		driver.navigate().back();
	}
	
	public String getURL() 
	{ 
		return driver.getCurrentUrl(); 
	}

	/**
	 * Open new Tab
	 * @throws AWTException exception
	 */
	public void openNewTab() throws AWTException {
		/*Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_T);*/

		String link = "window.open('https://www.google.com','_blank');";
		((JavascriptExecutor)driver).executeScript(link);
	}
	
	//Alerts
    public void waitTillAlertPresent() 
	{ 
		WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION); 
		wait.until(ExpectedConditions.alertIsPresent()); 
	} 
    
    public void dismissAlert() 
    { 
    	driver.switchTo().alert().dismiss(); 
    } 
    
    public void acceptAlert() 
    { 
    	driver.switchTo().alert().accept(); 
    } 
    
    public String getTextFromAlert() 
    { 
    	return driver.switchTo().alert().getText().trim(); 
    } 
	
    
	//Drop-down
	public void selectDropdownValueByText(WebElement e, String text)
	{
		new Select(e).selectByVisibleText(text);
	}
	
	public String getSelectedDropdownValue(WebElement e)
	{
		return new Select(e).getFirstSelectedOption().getText().trim();
	}
	
	public String selectDropdownValueByIndex(WebElement e, int index)
	{
		new Select(e).selectByIndex(index);
		return new Select(e).getFirstSelectedOption().getText().trim();
	}
	
	public List<String> getAllDropdownValues(WebElement e)
	{
		List<String> dropdownvalues = new ArrayList<String>();
		List<WebElement> list = new Select(e).getOptions();
		
		for(WebElement item :list)
		{
			dropdownvalues.add(item.getText().trim());
		}
		
		return dropdownvalues;
	}
	
	
	//Action events
	public void focusOnElement(WebElement e)
	{
		actions.moveToElement(e);
		actions.click();
	}
	
	public void doubleClickOnElement(WebElement e)
	{
		actions.doubleClick(e).build().perform();
	}
	
	public void doubleClickOnElementWithOffset(WebElement e,int x_off ,int y_off) 
	{    
		actions.moveToElement(e,x_off,y_off).doubleClick().build().perform(); 
	} 
	
	public void singleClickOnElementWithOffset(WebElement e,int x_off ,int y_off) 
	{ 
		actions.moveToElement(e,x_off,y_off).click().build().perform(); 
	} 

	public void singleClickOnElement(WebElement e) 
	{ 
		actions.moveToElement(e).click().build().perform(); 
	} 
	
	public void dragAndDrop(WebElement drag,WebElement drop) throws InterruptedException 
	{ 
		Actions actions = new Actions(driver); 
		actions.clickAndHold(drag).build().perform(); 
		hardWait(3); 
		actions.moveToElement(drop).build().perform(); 
		hardWait(3); 
		actions.release(drop).build().perform(); 
		hardWait(3); 
	} 
	 
		
	//Page scrolls
	public WebElement pageScrollInView(WebElement e)
	{
	    JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("arguments[0].scrollIntoView(true);",e);
	    return e;
	}
	
	public WebElement pageScrollInView(By by) 
	{ 
		WebElement e = driver.findElement(by); 
	    JavascriptExecutor jse = (JavascriptExecutor) driver; 
	    jse.executeScript("arguments[0].scrollIntoView(true);",e); 
	    return e; 
	} 
	
	public void widnowFocus() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.focus();");
	}


	//Java-script Helpers
	public void javascriptSetValue(WebElement e, String value) {
		String script = "arguments[0].value='" + value + "';";
		((JavascriptExecutor) driver).executeScript(script, e);
	}

	/**
	 * Get textbox value using javascript method
	 *
	 * @param element WebElement object having type Id
	 * @return text
	 */
	public String javascriptGetValue(WebElement element) {
		String id = element.toString().replaceAll("]", "").split("id:")[1].trim();
		return javascriptGetValue(id);
	}

	public String javascriptGetValue(String elementId) {
		String script = "return document.getElementById('" + elementId + "').value;";
		return ((JavascriptExecutor) driver).executeScript(script).toString().trim();
	}


	public void javascriptClickOn(WebElement e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
	}

	public void javascriptSetAnAttribute(WebElement e, String attribute, String value) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String jsscript = "arguments[0].setAttribute(arguments[1], arguments[2])";
		jse.executeScript(jsscript, e, attribute, value);
	}


	//Browser's Tab handler
	public String getWindowHandle() {
		return driver.getWindowHandle(); 
	} 
	
	public Set<String> getWindowHandles() 
	{ 
		return driver.getWindowHandles(); 
	} 
	
	public void switchToWindow(int tabNumber) 
	{
		int i = 1;
		for (String winHandle : getWindowHandles()) 
		{
			driver.switchTo().window(winHandle);
			if (i == tabNumber)
				break;
			i++;
		}
	}

	public void switchToWindow(String windowHandle) 
	{ 
		driver.switchTo().window(windowHandle); 
	} 

	
	//iFrames
	public void switchToIframe(String iframeId) 
	{ 
		driver.switchTo().frame(iframeId); 
	}
	
	public void switchToIframe(int iframeIndex) 
	{ 
		driver.switchTo().frame(iframeIndex); 
	}

	public void switchToIframe(WebElement e)
	{
		driver.switchTo().frame(e);
	}
	
	public void switchToMainIframe()
	{ 
		driver.switchTo().defaultContent(); 
	}

}

