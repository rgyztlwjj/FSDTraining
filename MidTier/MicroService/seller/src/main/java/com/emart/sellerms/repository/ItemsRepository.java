package com.emart.sellerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.sellerms.entity.ItemsEntity;


@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity, Integer>{
	
	public List<ItemsEntity> findBySellerId(Integer userId);
}