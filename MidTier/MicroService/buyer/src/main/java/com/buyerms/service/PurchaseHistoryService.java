package com.buyerms.service;

import java.util.List;

import com.buyerms.models.PurchaseHistoryModel;

public interface PurchaseHistoryService {

    /**
     * Get purchase history
     * @param buyerId
     * @return List<PurchaseHistoryModel>
     */
    public List<PurchaseHistoryModel> getPurchaseHistory(String buyerId);

}
