package com.emart.sellerms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.sellerms.entity.ManufacturEntity;

@Repository
public interface ManufacturRepository extends JpaRepository<ManufacturEntity, Integer>{
}