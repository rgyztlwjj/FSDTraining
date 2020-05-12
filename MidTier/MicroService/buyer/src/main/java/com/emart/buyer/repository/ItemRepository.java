package com.emart.buyer.repository;

import com.emart.buyer.entity.ItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemsEntity, Integer>{
}
