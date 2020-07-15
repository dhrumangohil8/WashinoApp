package com.example.washino;

public class HorizontalProductScrollModel {

    private int productImage;
    private String productTitle;
    private String productDescription;
    private String productPrice;


    public HorizontalProductScrollModel(int productImage, String productTitle, String productDescription, String productPrice) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductTitle() {
        return productTitle;
    }

}