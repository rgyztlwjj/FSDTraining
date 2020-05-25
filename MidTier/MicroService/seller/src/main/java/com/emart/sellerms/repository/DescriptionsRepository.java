package com.emart.sellerms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.sellerms.entity.DescriptionsEntity;

@Repository
public interface DescriptionsRepository extends JpaRepository<DescriptionsEntity, Integer>{
}