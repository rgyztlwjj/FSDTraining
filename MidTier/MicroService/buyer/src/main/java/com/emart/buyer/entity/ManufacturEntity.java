package com.emart.buyer.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "Manufactur")
public class ManufacturEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(name="manufactur_name")
    private String manufacturName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getManufacturName() {
        return manufacturName;
    }

    public void setManufacturName(String manufacturName) {
        this.manufacturName = manufacturName == null ? null : manufacturName.trim();
    }
}