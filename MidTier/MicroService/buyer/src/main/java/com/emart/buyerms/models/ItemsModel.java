package com.emart.buyerms.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.emart.buyerms.entity.DescriptionsEntity;
import com.emart.buyerms.entity.ManufacturEntity;
import com.emart.buyerms.entity.PictureEntity;


public class ItemsModel {

    private Integer id;

    private Integer categoryId;
    private Integer subcategoryId;
    private Integer manufacturId;
    private String itemname;
    private BigDecimal price;
    private Integer stock=0;
    private Integer sales=0;
    private Integer sellerId;
    private ManufacturEntity manufacture;
    private List<DescriptionsEntity> descriptions;
    private String descriptionString;
    private String[] pictures;
    private List<PictureEntity> picture;

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

	public Integer getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Integer subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public Integer getManufacturId() {
		return manufacturId;
	}

	public void setManufacturId(Integer manufacturId) {
		this.manufacturId = manufacturId;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname == null ? null : itemname.trim();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stockNumber) {
		this.stock = stockNumber;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
    public ManufacturEntity getManufacture() {
		return manufacture;
	}

	public void setManufacture(ManufacturEntity manufacture) {
		this.manufacture = manufacture;
	}
    public List<DescriptionsEntity> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<DescriptionsEntity> descriptions) {
		this.descriptions = descriptions;
	}
	
	public String getDescriptionString() {
		return descriptionString;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString == null ? null : descriptionString.trim();
	}
	
    public String[] getPictures() {
        return pictures;
    }
    public void setPictures(String[] picture) {
        this.pictures = picture;
    }
	
    public List<PictureEntity> getPicture() {
		return picture;
	}

	public void setPicture(List<PictureEntity> picture) {
		this.picture = picture;
	}
	
}