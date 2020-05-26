package com.emart.buyerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.buyerms.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer>{

    public List<CartEntity> findByBuyerId(Integer buyerId);
    public CartEntity findByItemId(Integer itemId);
    public CartEntity findByItemIdAndBuyerId(Integer itemId,Integer buyerIdInteger);

}
