package pageobjects.gonnaorder.admin.store.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.base.BasePO;

import java.util.List;

public class OrdersPO extends BasePO {

    public OrdersPO(WebDriver driver) {
        super(driver);
    }


    /*
     * All WebElements are identified by @FindBy annotation
     * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//button[text()='Rejected']")
    private WebElement rejectedTab;

    @FindBy(xpath = "//button[text()='Closed']")
    private WebElement closedTab;


    /**
     * Get data by row/column
     * @param row row index
     * @param column column index
     * @return data
     */
    public String getData(int row,int column )  {
        String cssLocator = "table tbody tr:nth-of-type(" + row + ") td:nth-of-type(" + column + ")";
        return selenium.getText(By.cssSelector(cssLocator));
    }

    /**
     * Get row index per order id
     * @param orderId order id
     * @return index
     */
    public int getRowIndexForOrderID(String orderId)
    {
        String cssLocator = "table tbody tr";
        List<WebElement> all = selenium.waitTillAllElementsAreLocated(By.cssSelector(cssLocator));

        int index = 0;

        for(int i=1 ; i <= all.size(); i++)
        {
            cssLocator = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(1)";
            if(selenium.getText(By.cssSelector(cssLocator)).equals(orderId))
            {
                index++;
                break;
            }
            index =i;
        }
        return  index;
    }


    /**
     * Get Order Id by row
     *
     * @return order Id by row
     * @throws InterruptedException
     */
    public String getOrderID(int row ) throws InterruptedException {
        String cssLocator = "table tbody tr:nth-of-type(" + row + ") td:nth-of-type(1)";
        selenium.hardWait(5);
        return selenium.getText(By.cssSelector(cssLocator));
    }


    /***
     * Click on Order Button
     * @throws InterruptedException
     */
    public void clickOnOrderID(int row) throws InterruptedException {
        String xpathLocator = "//tbody//tr[" + row + "]";
        selenium.javascriptClickOn(selenium.waitTillElementIsClickable(By.xpath(xpathLocator)));
        selenium.waitTillElementsCountIsLessThan(By.xpath(xpathLocator),1);
    }

    /***
     * Click on 'Closed' tab
     * @throws InterruptedException
     */
    public void clickOnCloseTab() throws InterruptedException {
        selenium.clickOn(closedTab);
    }

    /***
     * Click on 'Rejected' tab
     * @throws InterruptedException
     */
    public void clickOnRejectTab() throws InterruptedException {
        selenium.clickOn(rejectedTab);
    }


}


