package com.emart.sellerms.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.sellerms.entity.*;
import com.emart.sellerms.models.*;
import com.emart.sellerms.repository.*;;

@Service
public class ReportServiceImpl implements ReportService {
	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
	

	@Autowired
	private ItemsRepository itemsrepository;
	
	
	@Override
	public List<ReportModel> getReport(Integer userId) {
		

		List<ItemsEntity> lstEntity = itemsrepository.findBySellerId(userId);
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			log.info("No reports" + userId);
			return null;
		}
		
		List<ReportModel> lstModel = new ArrayList<ReportModel>(lstEntity.size());

		lstEntity.stream().forEach(entity -> lstModel.add(conver(entity)));
		
		return lstModel;		
	}

	/**
	 * Get report
	 * @param sellerId
	 * @param item
	 * @param strFromDate
	 * @param strToDate
	 * @return List<ReportModel>
	 */
//	@Override
//	public List<ReportModel> getReport(Integer sellerId, String item, String strFromDate, String strToDate) throws ParseException  {
//		
//		//Get seller's history
//		List<PurchaseHistoryEntity> lstEntity = historyRepository.findBySellerId(sellerId);
//		
//		if (CollectionUtils.isEmpty(lstEntity)) {
//			return null;
//		}
//		
//		//Filter by FromDate 
//		if (!StringUtils.isEmpty(strFromDate)) {
//			Date fromDate = sdf.parse(strFromDate); 
//			lstEntity = lstEntity.stream().filter(e -> e.getDatetime().compareTo(fromDate) >= 0).collect(Collectors.toList());
//		}
//		
//		//Filter by ToDate 
//		if (!StringUtils.isEmpty(strToDate)) {
//			Date toDate = sdf.parse(strToDate); 
//			lstEntity = lstEntity.stream().filter(e -> e.getDatetime().compareTo(toDate) <= 0).collect(Collectors.toList());
//		}
//		
//		//Group by item id
//		Map<Integer, List<PurchaseHistoryEntity>> map = lstEntity.stream().collect(Collectors.groupingBy(e -> e.getItemId()));
//		
//		List<ReportModel> lstModel = new ArrayList<ReportModel>(map.size());
//		
//		for(Map.Entry<Integer, List<PurchaseHistoryEntity>> entry : map.entrySet()) {
//			//Get item info
//			ItemViewEntity itemEntity = itemViewRepositor.findById(entry.getKey()).get();
//			
//			ReportModel model = new ReportModel();
//			BeanUtils.copyProperties(itemEntity, model);
//			
//			int sumSalesVolume = 0;
//			BigDecimal sumTransactionAmount = new BigDecimal(0);
//			
//			//Sum
//			for (PurchaseHistoryEntity e : entry.getValue()) {
//				sumSalesVolume += e.getPurchaseNumber();
//				sumTransactionAmount = sumTransactionAmount.add(e.getTransactionAmount());
//			}
//			
//			model.setSalesVolume(sumSalesVolume);
//			model.setTransactionAmount(sumTransactionAmount);
//			
//			lstModel.add(model);
//		}
//		
//		//Filter by item name
//		if (!"".equals(item)) {
//			lstModel = lstModel.stream()
//					.filter(m -> m.getItemName().indexOf(item) >= 0)
//					.collect(Collectors.toList());
//		}
//		
//		//Sort
//		lstModel = lstModel.stream()
//				.sorted(Comparator.comparing(ReportModel::getId))
//				.collect(Collectors.toList());
//		
//		return lstModel;
//	}


	private ReportModel conver(ItemsEntity entity) {
		ReportModel reportModel= new ReportModel();
		
		BeanUtils.copyProperties(entity, reportModel);
		
		return reportModel;
	}

	@Override
	public List<ReportModel> getReport(Integer sellerId, String item, String strFromDate, String strToDate)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
	

