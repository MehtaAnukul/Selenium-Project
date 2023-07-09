package dataObjectsModel;

import java.util.List;

public class ProductModel {

    private String productName1;
    private String productName2;
    private String productName3;

    private String productNameAtoZSort;
    private String productNameZtoASort;
    private String productPriceLowToHighSort;
    private String productPriceHighToLowSort;

    public ProductModel(String productName1, String productName2, String productName3) {
        this.productName1 = productName1;
        this.productName2 = productName2;
        this.productName3 = productName3;
    }

    public String getProductNameAtoZSort() {
        return productNameAtoZSort;
    }

    public void setProductNameAtoZSort(String productNameAtoZSort) {
        this.productNameAtoZSort = productNameAtoZSort;
    }

    public String getProductNameZtoASort() {
        return productNameZtoASort;
    }

    public void setProductNameZtoASort(String productNameZtoASort) {
        this.productNameZtoASort = productNameZtoASort;
    }

    public String getProductPriceLowToHighSort() {
        return productPriceLowToHighSort;
    }

    public void setProductPriceLowToHighSort(String productPriceLowToHighSort) {
        this.productPriceLowToHighSort = productPriceLowToHighSort;
    }

    public String getProductPriceHighToLowSort() {
        return productPriceHighToLowSort;
    }

    public void setProductPriceHighToLowSort(String productPriceHighToLowSort) {
        this.productPriceHighToLowSort = productPriceHighToLowSort;
    }

    public ProductModel() {

    }

    public String getProductName1() {
        return productName1;
    }

    public void setProductName1(String productName1) {
        this.productName1 = productName1;
    }

    public String getProductName2() {
        return productName2;
    }

    public void setProductName2(String productName2) {
        this.productName2 = productName2;
    }

    public String getProductName3() {
        return productName3;
    }

    public void setProductName3(String productName3) {
        this.productName3 = productName3;
    }
}

   /* private String productName;

    public ProModel(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }*/