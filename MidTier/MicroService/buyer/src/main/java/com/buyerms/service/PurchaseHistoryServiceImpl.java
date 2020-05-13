package com.buyerms.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.buyerms.entity.PurchaseHistoryEntity;
import com.buyerms.models.ItemDetailModel;
import com.buyerms.models.PurchaseHistoryModel;
import com.buyerms.repository.PurchaseHistoryRepository;
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
        ItemDetailModel itemModel =  itemService.getItemDetail(entity.getItemId());


        return model;
    }

}
