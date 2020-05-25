package com.emart.buyerms.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.buyerms.entity.PurchaseHistoryEntity;
import com.emart.buyerms.models.ItemDetailModel;
import com.emart.buyerms.models.PurchaseHistoryModel;
import com.emart.buyerms.repository.PurchaseHistoryRepository;
@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {
    private static final Logger log = LoggerFactory.getLogger(PurchaseHistoryServiceImpl.class);

    @Autowired
    private PurchaseHistoryRepository repository;
    @Autowired
    private ItemService itemService;

    /**
     * Get purchase history
     * @param buyerId
     * @return List<PurchaseHistoryModel>
     */
    public List<PurchaseHistoryModel> getPurchaseHistory(String buyerId) {
        List<PurchaseHistoryEntity> lstEntity = repository.findByBuyerId(buyerId);

        if (CollectionUtils.isEmpty(lstEntity)) {
            return null;
        }

        List<PurchaseHistoryModel> lstModel = new ArrayList<PurchaseHistoryModel>(lstEntity.size());

        //Convert entity to model
        lstEntity.stream().forEach(entity -> lstModel.add(toModel(entity)));

        return lstModel;
    }

    private PurchaseHistoryModel toModel(PurchaseHistoryEntity entity) {
        PurchaseHistoryModel model = new PurchaseHistoryModel();
        model.setId(String.valueOf(entity.getId()));

        //TODO
//        ItemDetailModel itemModel =  itemService.getItemDetail(entity.getItemId());


        return model;
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

}
