package com.emart.buyerms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emart.buyerms.entity.PictureEntity;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Integer>{

    public List<PictureEntity> findByItemId(Integer itemId);

}
