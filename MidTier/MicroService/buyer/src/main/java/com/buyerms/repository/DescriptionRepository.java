package com.buyerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buyerms.entity.DescriptionEntity;
import com.buyerms.entity.DiscountsEntity;

@Repository
public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Integer>{

    public List<DescriptionEntity> findByItemId(Integer itemId);

}
