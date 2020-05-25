package com.emart.sellerms.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subcategory")
public class SubCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name="subcategory_name")
    private String subcategoryname;

	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="brief_details")
    private String briefDetails;

	private BigDecimal gsn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubcategoryname() {
        return subcategoryname;
    }

    public void setSubcategoryname(String subcategoryname) {
        this.subcategoryname = subcategoryname == null ? null : subcategoryname.trim();
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
    

	public BigDecimal getGSN() {
		return gsn;
	}

	public void setPrice(BigDecimal GSN) {
		this.gsn = GSN;
	}

}
