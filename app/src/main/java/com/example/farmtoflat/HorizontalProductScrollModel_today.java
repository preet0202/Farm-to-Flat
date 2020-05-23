package com.example.farmtoflat;

public class HorizontalProductScrollModel_today {

    private int productImage;
    private String productTitle;
    private String productWeight;
    private String productPrice;

    public HorizontalProductScrollModel_today(int productImage, String productTitle, String productWeight, String productPrice) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productWeight = productWeight;
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
