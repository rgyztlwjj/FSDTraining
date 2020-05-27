package com.emart.sellerms.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.emart.sellerms.entity.PurchaseHistoryEntity;
import com.emart.sellerms.entity.*;
import com.emart.sellerms.models.*;
import com.emart.sellerms.repository.*;;

@Service
public class ReportServiceImpl implements ReportService {
	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
	

	@Autowired
	private PurchaseHistoryRepository historyRepository;
	

	@Autowired
	private ItemsRepository iremRepository;


	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMMdd");


	/**
	 * Get report
	 * @param sellerId
	 * @param item
	 * @param strFromDate
	 * @param strToDate
	 * @return List<ReportModel>
	 */
	@Override
	public List<ReportModel> getReport(Integer sellerId, String strFromDate, String strToDate) throws ParseException  {
		
		//Get seller's history
		List<PurchaseHistoryEntity> lstEntity = historyRepository.findBySellerId(sellerId);
		
		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}
		
		//Filter by FromDate 
		if (!StringUtils.isEmpty(strFromDate)) {
			Date fromDate = FORMAT.parse(strFromDate); 
			lstEntity = lstEntity.stream().filter(e -> e.getDatetime().compareTo(fromDate) >= 0).collect(Collectors.toList());
		}
		
		//Filter by ToDate 
		if (!StringUtils.isEmpty(strToDate)) {
			Date toDate = FORMAT.parse(strToDate); 
			lstEntity = lstEntity.stream().filter(e -> e.getDatetime().compareTo(toDate) <= 0).collect(Collectors.toList());
		}
		
		//Group by item id
		Map<Integer, List<PurchaseHistoryEntity>> map = lstEntity.stream().collect(Collectors.groupingBy(e -> e.getItemId()));
		
		List<ReportModel> lstModel = new ArrayList<ReportModel>(map.size());
		
		for(Map.Entry<Integer, List<PurchaseHistoryEntity>> entry : map.entrySet()) {
			//Get item info
			ItemsEntity itemEntity = entry.getValue().get(0).getItem();
			ReportModel model = new ReportModel();
			BeanUtils.copyProperties(itemEntity, model);
			
			int sumSalesVolume = 0;
			
			//Sum
			for (PurchaseHistoryEntity e : entry.getValue()) {
				sumSalesVolume += e.getNumber();
			}
			
			model.setSales(sumSalesVolume);
			
			lstModel.add(model);
		}
		//Sort
		lstModel = lstModel.stream()
				.sorted(Comparator.comparing(ReportModel::getId))
				.collect(Collectors.toList());
		
		return lstModel;
	}



}
	

