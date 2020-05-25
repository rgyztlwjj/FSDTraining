package com.emart.buyerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.buyerms.entity.DescriptionsEntity;
import com.emart.buyerms.entity.DiscountsEntity;

@Repository
public interface DescriptionsRepository extends JpaRepository<DescriptionsEntity, Integer>{

    public List<DescriptionsEntity> findByItemId(Integer itemId);

}
