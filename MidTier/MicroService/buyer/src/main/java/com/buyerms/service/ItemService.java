package com.buyerms.service;

import java.util.List;

import com.buyerms.models.ItemDetailModel;
import com.buyerms.models.ItemModel;

public interface ItemService {

    /**
     * Search item
     * @param context
     * @return List<ItemModel>
     */
    public List<ItemModel> search(String context);

    /**
     * Get item detail
     * @param id
     * @return ItemDetailModel
     */
    public ItemDetailModel getItemDetail(Integer id);

}
