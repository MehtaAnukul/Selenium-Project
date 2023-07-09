package dataFactory;

import dataObjectsModel.ProModelList;
import dataObjectsModel.ProModel;
import dataObjectsModel.ProductListModel;
import dataObjectsModel.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductData {

    ProductModel productModel = new ProductModel();

    String productNameText1 = "Sauce Labs Backpack";
    String productNameText2 = "Sauce Labs Bolt T-Shirt";
    String productNameText3 = "Sauce Labs Onesie";

    String productNameAtoZSort = "Name (A to Z)";
    String productNameZtoASort = "Name (Z to A)";
    String productPriceLowToHighSort = "Price (low to high)";
    String productPriceHighToLowSort = "Price (high to low)";


    /**
     * Set ProductName Data
     * @return
     */
    public ProductModel getProductModel() {
        productModel.setProductName1(productNameText1);
        productModel.setProductName2(productNameText2);
        productModel.setProductName3(productNameText3);
        productModel.setProductNameAtoZSort(productNameAtoZSort);
        productModel.setProductNameZtoASort(productNameZtoASort);
        productModel.setProductPriceLowToHighSort(productPriceLowToHighSort);
        productModel.setProductPriceHighToLowSort(productPriceHighToLowSort);
        return productModel;
    }

    /**
     * Set ProductNameList Data
     * @return
     */
    public ProductListModel getProductListModel() {
        ProductListModel productListModel = new ProductListModel();
        List<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel(productNameText1, productNameText2, productNameText3));
        productListModel.setProductModelList(productModels);
        return productListModel;
    }









    /* public ProductListModel getProductListModel(){
         productModelList.add(new ProductModel());
         for(int i=0;i<productModelList.size();i++){
             System.out.println(productModelList.get(i));
         }
         productListModel.setProductModelList(productModelList);
         return productListModel;
     }*/
   /* public static void main(String[] args) {

        ProModelList proModelList = new ProModelList();
        List<ProModel> proModels = new ArrayList<>();
        proModels.add(new ProModel("Sauce Labs Backpack"));
        proModels.add(new ProModel("Sauce Labs Bolt T-Shirt"));
        proModels.add(new ProModel("Sauce Labs Bolt T-Shirt"));
        proModelList.setProModelList(proModels);
        for (int i=0;i<proModels.size();i++){
            System.out.println("List :"+ proModels.get(0).getProductName());
        }
       // System.out.println("List :"+ proModelList.getProModelList().get(1).getProductName());

        ProductListModel productListModel = new ProductListModel();
        List<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel("Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Bolt T-Shirt"));
        productListModel.setProductModelList(productModels);
        for (int i=0;i<productModels.size();i++){
            System.out.println("Listt :" + productModels.get(0).getProductName3());
        }
        System.out.println(productListModel.getProductModelList().get(0).getProductName2());
    }*/
}
