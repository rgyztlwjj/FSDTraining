package com.emart.buyerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emart.buyerms.entity.ItemsEntity;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity, Integer>{

	public List<ItemsEntity> findByManufacturId	(Integer manufacturId);
	
	public List<ItemsEntity> findByManufacture_NameLike(String context);
}
