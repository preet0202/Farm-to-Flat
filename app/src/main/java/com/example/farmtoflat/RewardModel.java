package com.example.farmtoflat;

public class RewardModel {

    private String expiryDate;
    private String coupenBody;
    private String title;

    public RewardModel(String expiryDate, String coupenBody, String title) {
        this.expiryDate = expiryDate;
        this.title = title;
        this.coupenBody = coupenBody;

    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCoupenBody() {
        return coupenBody;
    }

    public void setCoupenBody(String coupenBody) {
        this.coupenBody = coupenBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
