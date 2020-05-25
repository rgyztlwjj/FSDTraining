package com.emart.buyerms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.buyerms.entity.DiscountsEntity;

@Repository
public interface DiscountsRepository extends JpaRepository<DiscountsEntity, Integer>{
	
	public Optional<DiscountsEntity> findByDiscountCode(String discountCode);
}
