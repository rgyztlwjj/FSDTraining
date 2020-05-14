package com.buyerms.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.buyerms.entity.DiscountsEntity;
import com.buyerms.entity.ItemsEntity;
import com.buyerms.entity.PurchaseHistoryEntity;
import com.buyerms.entity.TransactionEntity;
import com.buyerms.models.TransactionModel;
import com.buyerms.repository.DiscountRepository;
import com.buyerms.repository.ItemsRepository;
import com.buyerms.repository.PurchaseHistoryRepository;
import com.buyerms.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	ItemsRepository itemsRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	PurchaseHistoryRepository historyRepository;
	
	@Autowired
	DiscountRepository discountRepository;
	
//	@Autowired
//	private DataSourceTransactionManager dataSourceTransactionManager;
	
//	@Autowired
//	private TransactionDefinition transactionDefinition;
	
	
	/**
	 * Checkout
	 * @param models TransactionModel[]
	 * @return true:checkout sucessful, false:checkout failure
	 */
//	@Transactional
//	@Override
//	public boolean checkout(TransactionModel[] models) {
//		Date datetime = new Date();
//		
//		TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
//		
//		for (TransactionModel model : models) {
//			//Update stock if stock is larger than purchase number.
////			int updatedCnt = itemsRepository.updateStock(Integer.parseInt(model.getItemId()), model.getNumber());
//			
//
//			//Create transaction
//			ItemsEntity entityItems = new ItemsEntity();
//			
//			entityItems = itemsRepository.save(entityItems);
//			
//			//If the inventory is insufficient, rollback
//			if (entityItems ==null) {
//				dataSourceTransactionManager.rollback(transactionStatus);
//				return false;
//			}
//			
//			//Create transaction
//			TransactionEntity entity = new TransactionEntity();
//			BeanUtils.copyProperties(model, entity);
//			entity.setTransactionDatetime(datetime);
//			
//			entity = transactionRepository.save(entity);
//			
//			//Create purchase history
//			PurchaseHistoryEntity historyEntity = new PurchaseHistoryEntity();
//			BeanUtils.copyProperties(model, historyEntity);
//			historyEntity.setTransactionId(entity.getId());
//			historyEntity.setDatetime(datetime);
//			
//			historyRepository.save(historyEntity);
//			
//		}
//
//		dataSourceTransactionManager.commit(transactionStatus);
//		return true;
//		
//	}
	
	/**
	 * Get discount by code.
	 * @param code
	 * @return discount percent, if not found or expired then return null.
	 */
	@Override
	public BigDecimal getDiscount(String code) {
		Optional<DiscountsEntity> optEntity = discountRepository.findByDiscountCode(code);
		
		if (optEntity.isPresent()) {
			DiscountsEntity entity = optEntity.get();
			
			Date now = Calendar.getInstance().getTime();
			
			//If within the validity period
			if (now.compareTo(entity.getStartDate()) >= 0 && now.compareTo(entity.getEndDate()) <= 0  ) {
				return entity.getPercentage();
			}
		}
		
		return null;
	}

}