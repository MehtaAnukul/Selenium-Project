package pageObjects.swagLabs.account;

import dataFactory.ProductData;
import dataObjectsModel.CheckOutModel;
import dataObjectsModel.ProductListModel;
import dataObjectsModel.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.base.BasePO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Double.parseDouble;

public class ProductPO extends BasePO {


    public ProductPO(WebDriver driver) {
        super(driver);
    }

    /*
     *  All WebElements are identified by @FindBy annotation
     *  @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
     */

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement productSortDropDown;

    @FindBy(xpath = "//select[@class='product_sort_container']/option[@value='az']")
    private WebElement nameAtoZProductSortDropDown;

    @FindBy(xpath = "//select[@class='product_sort_container']/option[@value='za']")
    private WebElement nameZtoAProductSortDropDown;

    @FindBy(xpath = "//select[@class='product_sort_container']/option[@value='lohi']")
    private WebElement priceLowToHighProductSortDropDown;

    @FindBy(xpath = "//select[@class='product_sort_container']/option[@value='hilo']")
    private WebElement priceHighToLowProductSortDropDown;


   /* public static void main(String[] args) {
        ProductModel productModel = new ProductData().getProductModel();
        String product1PriceXpath = "//div[contains(text(),'Sauce Labs Backpack')]/parent::a/parent::div/following-sibling::div/div";
        System.out.println(product1PriceXpath);
        double product1Price= Double.parseDouble(product1PriceXpath);
        System.out.println(product1Price);
        *//*double product2Price=Double.parseDouble(product2PriceXpath);
        double product3Price=Double.parseDouble(product3PriceXpath);
        double totalPrice = product1Price + product2Price + product3Price;
        System.out.println(totalPrice);*//*
        //.replace("$29.99","29.99")+
    }*/



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
     * Click on 'ProductImage'
     *
     * @throws InterruptedException exception
     */
    public void clickOnProductImageIDXpath(String productImage) throws InterruptedException {
        String productImageIDXpath = "//a[contains(@id,'" + productImage + "')]/img";
        selenium.clickOn(By.xpath(productImageIDXpath));
    }

    /**
     * Click on 'AddToCart' button
     *
     * @throws InterruptedException exception
     */
    public void clickOnProductAddToCartButton(String productName) throws InterruptedException {

        String productAddToCartButtonXpath = "//div[contains(text(),'" + productName + "')]/parent::a/parent::div/following-sibling::div//button";
        selenium.clickOn(By.xpath(productAddToCartButtonXpath));
    }

    /**
     * Click on 'AddToCart' button
     *
     * @throws InterruptedException exception
     * Click on Product AddToCartButton Using String ProductModel as parameter
     */
    public void clickOnProductAddToCartButtonUsingProductModel(String productModel) throws InterruptedException {
        String productAddToCartButtonXpath = "//div[contains(text(),'" + productModel + "')]/parent::a/parent::div/following-sibling::div//button";
        selenium.clickOn(By.xpath(productAddToCartButtonXpath));
    }

    /**
     * Click on 'AddToCart' button
     *
     * @throws InterruptedException exception
     * Click on Product AddToCartButton Using String ProductListModel as parameter
     */
    public void clickOnProductAddToCartButtonUsingProductListModel(String productListModel) throws InterruptedException {
        String productAddToCartButtonXpath = "//div[contains(text(),'" + productListModel + "')]/parent::a/parent::div/following-sibling::div//button";
        selenium.clickOn(By.xpath(productAddToCartButtonXpath));
    }

    /**
     * Click on multiple 'AddToCart' button
     *
     * @throws InterruptedException exception
     * Click Multiple Product AddToCartButton using pass ProductModel as parameter
     */
    public void clickOnMultipleAddToCartButton(ProductModel productModel) throws InterruptedException {
        String product1AddToCartButtonXpath = "//div[contains(text(),'" + productModel.getProductName1() + "')]/parent::a/parent::div/following-sibling::div//button";
        String product2AddToCartButtonXpath = "//div[contains(text(),'" + productModel.getProductName2() + "')]/parent::a/parent::div/following-sibling::div//button";
        String product3AddToCartButtonXpath = "//div[contains(text(),'" + productModel.getProductName3() + "')]/parent::a/parent::div/following-sibling::div//button";
        selenium.clickOn(By.xpath(product1AddToCartButtonXpath));
        selenium.clickOn(By.xpath(product2AddToCartButtonXpath));
        selenium.clickOn(By.xpath(product3AddToCartButtonXpath));
    }

    /**
     * Click on multiple 'AddToCart' button
     *
     * @throws InterruptedException exception
     * Click Multiple Product AddToCartButton using pass ProductListModel as parameter
     */
    public void clickOnMultipleAddToCartButtonUsingProductListModel(ProductListModel productListModel) throws InterruptedException {

        String product1AddToCartButtonXpath = "//div[contains(text(),'" + productListModel.getProductModelList().get(0).getProductName1() + "')]/parent::a/parent::div/following-sibling::div//button";
        String product2AddToCartButtonXpath = "//div[contains(text(),'" + productListModel.getProductModelList().get(0).getProductName2() + "')]/parent::a/parent::div/following-sibling::div//button";
        String product3AddToCartButtonXpath = "//div[contains(text(),'" + productListModel.getProductModelList().get(0).getProductName3() + "')]/parent::a/parent::div/following-sibling::div//button";
        selenium.clickOn(By.xpath(product1AddToCartButtonXpath));
        selenium.clickOn(By.xpath(product2AddToCartButtonXpath));
        selenium.clickOn(By.xpath(product3AddToCartButtonXpath));

    }


    /**
     * Click on 'Remove' button
     *
     * @throws InterruptedException exception
     */
    public void clickOnProductRemoveButton(String productName) throws InterruptedException {
        String productRemoveButtonXpath = "//div[contains(text(),'" + productName + "')]/parent::a/parent::div/following-sibling::div//button";
        selenium.clickOn(By.xpath(productRemoveButtonXpath));
    }


    /**
     *
     * @param productName
     * @return
     * @throws InterruptedException
     */
    public String getProductPrice(String productName) throws InterruptedException{

        String productPriceXpath = "//div[contains(text(),'"+productName+"')]/parent::a/parent::div/following-sibling::div/div";
        return selenium.getText(By.xpath(productPriceXpath));
    }

    /**
     * Click on 'productSortDropDown'
     *
     * @throws InterruptedException exception
     */
    public void clickOnProductSortDropDown() throws InterruptedException {
        selenium.clickOn(productSortDropDown);

    }

    /**
     * Click on 'productSortDropDown' and Select sort option
     */
    public void clickOnProductSortDropDownSelectSortOption(String productSortOption) throws InterruptedException {
        String productSortOptionXpath = "//select[@class='product_sort_container']/option[contains(text(),'" + productSortOption + "')]";
        selenium.clickOn(By.xpath(productSortOptionXpath));
    }

    /**
     * Click on 'productSortDropDown' and Select sort option
     */
    public void clickOnProductSortDropDownSelectSortOptionUsingModel(String productModel) throws InterruptedException{
        String productSortOptionXpath = "//select[@class='product_sort_container']/option[contains(text(),'" + productModel + "')]";
        selenium.clickOn(By.xpath(productSortOptionXpath));
    }


    private By productNameListXpath = By.className("inventory_item_name");
    /**
     *
     */
    public List<String> getProductNameList(){
        List<WebElement> allProductNameList = selenium.waitTillAllElementsAreLocated(productNameListXpath);
        List<String> allProductNames = new ArrayList<>();
        for (WebElement webElement : allProductNameList){
            allProductNames.add(selenium.getText(webElement));
        }
        return allProductNames;
    }

    private By productPriceListXpath = By.className("inventory_item_price");
    /**
     *
     */
    public List<Double> getProductPriceList(){
        List<WebElement> allProductPriceList = selenium.waitTillAllElementsAreLocated(productPriceListXpath);
        List<Double> allProductPrices = new ArrayList<>();
        for (WebElement webElement : allProductPriceList){
            allProductPrices.add(Double.valueOf(selenium.getText(webElement).replace("$","")));
        }
        return allProductPrices;
    }

    /**
     * Click on 'nameAtoZProductSortDropDown'
     *
     * @throws InterruptedException exception
     */
    public void clickOnNameAtoZProductSortDropDown() throws InterruptedException {
        selenium.clickOn(nameAtoZProductSortDropDown);
    }

    /**
     * Click on 'nameZtoAProductSortDropDown'
     *
     * @throws InterruptedException exception
     */
    public void clickOnNameZtoAProductSortDropDown() throws InterruptedException {
        selenium.clickOn(nameZtoAProductSortDropDown);
    }

    /**
     * Click on 'priceLowToHighProductSortDropDown'
     *
     * @throws InterruptedException exception
     */
    public void clickOnPriceLowToHighProductSortDropDown() throws InterruptedException {
        selenium.clickOn(priceLowToHighProductSortDropDown);
    }

    /**
     * Click on 'priceHighToLowProductSortDropDown'
     *
     * @throws InterruptedException exception
     */
    public void clickOnPriceHighToLowProductSortDropDown() throws InterruptedException {
        selenium.clickOn(priceHighToLowProductSortDropDown);
    }

}
