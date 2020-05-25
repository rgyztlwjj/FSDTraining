package com.emart.buyerms.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class ItemsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name="category_id")
    private Integer categoryId;

	@Column(name="subcategory_id")
    private Integer subcategoryId;

	@Column(name="manufactur_id")
    private Integer manufacturId;
    
    @Column(name="item_name")
    private String itemname;

    private BigDecimal price;
    
    private Integer stock;
    
    private Integer sales;
    
    @Column(name="seller_id")
    private Integer sellerId;

    @OneToOne
    @JoinColumn(name="manufactur_id",referencedColumnName="id", nullable = false, insertable = false,updatable = false)
    private ManufacturEntity manufacture;
    
    @OneToOne
    @JoinColumn(name="category_id",referencedColumnName="id", nullable = false, insertable = false,updatable = false)
    private CategoryEntity category;
    
    @OneToOne
    @JoinColumn(name="subcategory_id",referencedColumnName="id", nullable = false, insertable = false,updatable = false)
    private SubcategoryEntity subcategory;
    
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy="item")
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
   private List<DescriptionsEntity> descriptions;
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

	public void setStock(Integer stock) {
		this.stock = stock;
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
	
    public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
    public SubcategoryEntity getSubcategory() {
		return subcategory;
	}

	public void setCategory(SubcategoryEntity subcategory) {
		this.subcategory = subcategory;
	}
}