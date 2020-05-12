package com.emart.buyer.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "pictures")
public class PictureEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="item_id")
    private Integer itemId;

    @Column(name="picture_Path")
    private String picturePath;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }
}