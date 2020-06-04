package com.example.farmtoflat;

public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //// cart item
    private int productImage;
    private int freecoupons;
    private String productTitle;
    private String productprice;
    private int productquantity;
    private int offersapllied;
    private int couponsapplied;
    //// cart item


    public CartItemModel(int type, int productImage, String productTitle, String productprice, int productquantity , int freecoupons, int offersapllied, int couponsapplied) {
        this.type = type;
        this.productImage = productImage;
        this.freecoupons = freecoupons;
        this.productTitle = productTitle;
        this.productprice = productprice;
        this.productquantity = productquantity;
        this.offersapllied = offersapllied;
        this.couponsapplied = couponsapplied;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getFreecoupons() {
        return freecoupons;
    }

    public void setFreecoupons(int freecoupons) {
        this.freecoupons = freecoupons;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public int getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(int productquantity) {
        this.productquantity = productquantity;
    }

    public int getOffersapllied() {
        return offersapllied;
    }

    public void setOffersapllied(int offersapllied) {
        this.offersapllied = offersapllied;
    }

    public int getCouponsapplied() {
        return couponsapplied;
    }

    public void setCouponsapplied(int couponsapplied) {
        this.couponsapplied = couponsapplied;
    }

    private String totalitems;
    private String totalItemPric;
    private String deleveryPrice;
    private String savedAmount;
    private String totalAmount;

    public CartItemModel(int type, String totalitems, String totalItemPric, String deleveryPrice, String savedAmount,String totalAmount) {
        this.type = type;
        this.totalitems = totalitems;
        this.totalItemPric = totalItemPric;
        this.deleveryPrice = deleveryPrice;
        this.savedAmount = savedAmount;
        this.totalAmount = totalAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(String totalitems) {
        this.totalitems = totalitems;
    }

    public String gettotalItemPric() {
        return totalItemPric;
    }

    public void setTtotalItemPric(String totalAmount) {
        this.totalItemPric = totalAmount;
    }

    public String getDeleveryPrice() {
        return deleveryPrice;
    }

    public void setDeleveryPrice(String deleveryPrice) {
        this.deleveryPrice = deleveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }
}
