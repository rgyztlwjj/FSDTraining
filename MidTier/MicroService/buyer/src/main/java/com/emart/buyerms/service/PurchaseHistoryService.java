package com.emart.buyerms.service;

import java.util.List;

import com.emart.buyerms.models.PurchaseHistoryModel;

public interface PurchaseHistoryService {

    /**
     * Get purchase history
     * @param buyerId
     * @return List<PurchaseHistoryModel>
     */
    public List<PurchaseHistoryModel> getPurchaseHistory(String buyerId);

//	List<ReportModel> getReport(Integer sellerId, String item, String strFromDate, String strToDate)
//			throws ParseException;

}
