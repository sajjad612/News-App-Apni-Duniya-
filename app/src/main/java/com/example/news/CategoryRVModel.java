package com.example.news;

public class CategoryRVModel {
    String category;
    int categoryImage;

    public CategoryRVModel(String category, int categoryImage) {
        this.category = category;
        this.categoryImage = categoryImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }
}
