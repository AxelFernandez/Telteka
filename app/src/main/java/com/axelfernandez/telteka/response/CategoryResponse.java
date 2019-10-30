package com.axelfernandez.telteka.response;

import com.axelfernandez.telteka.model.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("categories")
    @Expose
    private List<Category> categories;

    @SerializedName("hadChildren")
    @Expose
    private String hasChildren;

    public String isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
