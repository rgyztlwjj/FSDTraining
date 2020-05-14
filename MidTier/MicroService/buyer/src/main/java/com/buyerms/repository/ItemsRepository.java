package com.buyerms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buyerms.entity.ItemsEntity;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity, Integer>{
	/**
	 * Update stock if stock is larger than purchase number.
	 * @param id
	 * @param number
	 * @return
	 */
//	@Modifying
//	@Query(value="update items set stock_number = stock_number - :num, sales_number = sales_number + :num where id = :id and stock_number >= :num")
//	public int updateStock(@Param("id") Integer id, @Param("num") Integer number);
}
