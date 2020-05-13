package com.buyerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buyerms.entity.ItemViewEntity;

@Repository
public interface ItemViewRepository extends JpaRepository<ItemViewEntity, Integer>{

    public List<ItemViewEntity> findBySearchContextLike(String searchContext);

}
