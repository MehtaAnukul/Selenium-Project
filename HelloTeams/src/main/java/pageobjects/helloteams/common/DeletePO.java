package pageobjects.helloteams.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.base.BasePO;

public class DeletePO extends BasePO
{
    public DeletePO(WebDriver driver) {
        super(driver);
    }

    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */
    private By confirmDeleteTeamByOkButton = By.xpath("//span[@class='btn btn-primary']");

    private By clickOnCancelButtonFromDeleteTeam = By.xpath("//span[@class='btn btn-secondary']");

    /**
     * Click on ok button for confirm delete team member
     *
     * @throws InterruptedException exception
     */
    public void clickOnOkButtonOfDeleteTeamMembers() throws InterruptedException {
        selenium.hardWait(5);
        WebElement button = selenium.waitTillAllElementsAreLocated(confirmDeleteTeamByOkButton).stream().filter(e -> e.isDisplayed()).findFirst().get();
        selenium.clickOn(button);
    }

    /**
     * Click on cancel button from confirm delete team member popup box
     *
     * @throws InterruptedException exception
     */
    public void clickOnCancelButtonOfDeleteTeamMembers() throws InterruptedException {
        selenium.hardWait(3);
        WebElement button = selenium.waitTillAllElementsAreLocated(clickOnCancelButtonFromDeleteTeam).stream().filter(e -> e.isDisplayed()).findFirst().get();
        selenium.clickOn(button);
    }

    /**
     * Click on ok button for confirm delete team
     *
     * @throws InterruptedException exception
     */
    public void clickOnOkButtonForDeleteTeam() throws InterruptedException {
        selenium.hardWait(3);
        WebElement button = selenium.waitTillAllElementsAreLocated(confirmDeleteTeamByOkButton).stream().filter(e -> e.isDisplayed()).findFirst().get();
        selenium.clickOn(button);
    }


    /**
     * Click on cancel button for confirm delete team
     *
     * @throws InterruptedException exception
     */
    public void clickOnCancelButtonForDeleteTeam() throws InterruptedException {
        selenium.hardWait(3);
        WebElement button = selenium.waitTillAllElementsAreLocated(clickOnCancelButtonFromDeleteTeam).stream().filter(e -> e.isDisplayed()).findFirst().get();
        selenium.clickOn(button);
    }
}
