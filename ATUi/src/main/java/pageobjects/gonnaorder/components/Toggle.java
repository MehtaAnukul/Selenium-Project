package pageobjects.gonnaorder.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.base.BasePO;

public class Toggle extends BasePO {
    public Toggle(WebDriver driver) {
        super(driver);
    }

    /**
     * Custom method for handle toggle selection
     *
     * @param toggleSpan  toggle span WebElement object
     * @param toggleInput toggle input WebElement object
     * @param select true / false based on toggle selection need
     * @throws InterruptedException exception
     */
    public void toggle(WebElement toggleSpan, WebElement toggleInput, boolean select) throws InterruptedException {
        if (select) {
            if (!selenium.isSelected(toggleInput)) {
                selenium.click(toggleSpan);
            }
        } else {
            if (selenium.isSelected(toggleInput)) {
                selenium.click(toggleSpan);
            }
        }

        selenium.hardWait(5);
    }
}
