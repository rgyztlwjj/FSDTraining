package com.buyerms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buyerms.entity.DiscountsEntity;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountsEntity, Integer>{
	
	public Optional<DiscountsEntity> findByDiscountCode(String discountCode);
}
