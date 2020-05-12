package com.emart.buyer.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
@Entity
@Table(name = "Subcategory")
public class SubcategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="subcategory_name")
    private String subcategoryName;
    @Column(name="category_id")
    private Integer categoryId;
    @Column(name="brief_details")
    private String briefDetails;

    private BigDecimal gst;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName == null ? null : subcategoryName.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getBriefDetails() {
        return briefDetails;
    }

    public void setBriefDetails(String briefDetails) {
        this.briefDetails = briefDetails == null ? null : briefDetails.trim();
    }

    public BigDecimal getGst() {
        return gst;
    }

    public void setGst(BigDecimal gst) {
        this.gst = gst;
    }
}