package com.emart.sellerms.models;


public class SubCategoryModel {

    private Integer id;

    private Integer categoryId;
    

    private String subcategoryname;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
		return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    
    public String getSubcategoryname() {
        return subcategoryname;
    }

    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname == null ? null : subcategoryname.trim();
    }

}
