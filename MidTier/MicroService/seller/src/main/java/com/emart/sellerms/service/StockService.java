package com.emart.sellerms.service;

import java.util.List;

import com.emart.sellerms.models.*;

public interface StockService {
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<StockModel> getStock(Integer userId);
	
	/**
	 * 
	 * @param stockmodel
	 * @return
	 */
	public StockModel updateStocks(Integer id,Integer stock);
}
