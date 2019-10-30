package com.axelfernandez.telteka.model;

public class Category {

    String idcategory;
    String description;
    String hadChildren;


    public String isHadChildren() {
        return hadChildren;
    }

    public void setHadChildren(String hadChildren) {
        this.hadChildren = hadChildren;
    }

    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
