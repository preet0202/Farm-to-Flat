package com.example.farmtoflat;

public class CategoryModel {

    private String CategoryLink;
    private String CategoryName;

    public CategoryModel(String categoryLink, String categoryName) {
        CategoryLink = categoryLink;
        CategoryName = categoryName;
    }

    public String getCategoryLink() {
        return CategoryLink;
    }

    public void setCategoryLink(String categoryLink) {
        CategoryLink = categoryLink;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
