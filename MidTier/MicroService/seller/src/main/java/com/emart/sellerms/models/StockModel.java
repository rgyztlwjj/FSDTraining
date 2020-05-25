package com.emart.sellerms.models;

import java.math.BigDecimal;


public class StockModel {
	
	private Integer itemid;
	
    private String manufacturname;
    
    
    private String itemname;

    private BigDecimal price;
    
    private Integer stock;
    

	public Integer getItemId() {
		return itemid;
	}

	public void setItemId(Integer itemid) {
		this.itemid = itemid;
	}
 

	public String getManufacturname() {
		return manufacturname;
	}

	public void setMmanufacturname(String manufacturname) {
		this.manufacturname = manufacturname;
	}

	public String getItemname() {
		return itemname;
	
	}

	public void setItemname(String itemName) {
		this.itemname = itemName == null ? null : itemName.trim();
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

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}