package com.emart.buyerms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchasehistory")
public class PurchaseHistoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name="buyer_id")
    private Integer buyerId;
	
	@Column(name="seller_id")
    private Integer sellerId;

	@Column(name="transaction_id")
    private Integer transactionId;

	@Column(name="item_id")
    private Integer itemId;

    private Integer number;

    private Date datetime;

    @OneToOne
    @JoinColumn(name="item_id",referencedColumnName="id", nullable = false, insertable = false,updatable = false)
    private ItemsEntity item;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
    
    public void setItem(ItemsEntity item) {
    	this.item = item;
    }
    public ItemsEntity getItem() {
    	return item;
    }
    
  
}