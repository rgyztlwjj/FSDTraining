package com.emart.buyerms.service;

import java.util.List;

import com.emart.buyerms.models.ItemDetailModel;
import com.emart.buyerms.models.ItemsModel;

public interface ItemService {

    /**
     * Search item
     * @param context
     * @return List<ItemModel>
     */
    public List<ItemsModel> search(String context);

    /**
     * Get item detail
     * @param id
     * @return ItemDetailModel
     */
    public ItemsModel getItemDetail(Integer id);

    /**
     * Get item by filter
     * @param manufactureIdid
     * @return ItemsModel
     */
	public List<ItemsModel> searchbyfilter(Integer manufactureId,String startPrice,String endPrice);

}
