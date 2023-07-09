package pageObjects.helloTeams.account;

import pageObjects.base.BasePO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrganizationDashboardPO extends BasePO {

    public OrganizationDashboardPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//div[@class='card-content']//div[2]")
    private WebElement getTextCreateOrganization;

    @FindBy(xpath = "//h2[contains(text(),'123@')]")
    private WebElement getOrganizationName;

    /**
     * This text is displayed after successful Login
     *
     * @return create organization text
     */
    public String getTextCreateOrganization() {
        return selenium.getText(getTextCreateOrganization);
    }

    /**
     * Click on 'Organization' name
     *
     * @throws InterruptedException exception
     */
    public String clickOnOrganizationName(){
        return selenium.getText(getOrganizationName);
    }

}
