package com.emart.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.userms.entity.BuyerEntity;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, Integer>{

	public BuyerEntity findByUsername(String username);

}
