package com.emart.buyerms.entity;


import javax.persistence.*;

@Table(name = "cart")
@Entity
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "buyer_id")
    private Integer buyerId;

    @Column(name = "item_id")
    private Integer itemId;

    private Integer number;

    @OneToOne
    @JoinColumn(name="item_id",referencedColumnName="id", nullable = false, insertable = false,updatable = false)
    private ItemsEntity item;
    
    private static final long serialVersionUID = 1L;

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
    
    public void setItem(ItemsEntity item) {
    	this.item = item;
    }
    public ItemsEntity getItem() {
    	return item;
    }
    
}