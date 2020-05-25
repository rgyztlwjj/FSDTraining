package com.emart.sellerms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.descriptor.tld.ImplicitTldRuleSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.sellerms.entity.*;
import com.emart.sellerms.models.*;
import com.emart.sellerms.repository.*;
import com.fasterxml.jackson.databind.util.BeanUtil;

import javassist.expr.NewArray;;

@Service
public class StockServiceImpl implements StockService {
	
	private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

	@Autowired
	private ItemsRepository itemsrepository;

	/**
	 * 
	 */
	@Override
	public List<StockModel> getStock(Integer userId) {
		

		List<ItemsEntity> lstEntity = itemsrepository.findBySellerId(userId);
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			log.info("No stocks" + userId);
			return null;
		}
		
		List<StockModel> lstModel = new ArrayList<StockModel>(lstEntity.size());

		lstEntity.stream().forEach(entity -> lstModel.add(conver(entity)));
		
		return lstModel;		
	}



	/**
	 * 
	 * @param entity
	 * @return
	 */
	private StockModel conver(ItemsEntity entity) {
		StockModel stockmodel= new StockModel();
		
		BeanUtils.copyProperties(entity, stockmodel);
		stockmodel.setItemId(entity.getId());
		
		return stockmodel;
	}



	@Override
	public boolean updateStocks(Integer id,Integer stock) {
		
		Optional<ItemsEntity> objEntity =itemsrepository.findById(id);
		if (objEntity == null) {

			return false;
		}
		try {
			ItemsEntity updateobjEntity=  objEntity.get();
			updateobjEntity.setStock(stock);
			
			itemsrepository.save(updateobjEntity);
		} catch (Exception e) {
			
			return false;
		}
		return true;
	}



	

	
}
	

