package com.buyerms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.buyerms.entity.DescriptionEntity;
import com.buyerms.entity.ItemViewEntity;
import com.buyerms.entity.ItemsEntity;
import com.buyerms.entity.PictureEntity;
import com.buyerms.models.ItemDetailModel;
import com.buyerms.models.ItemModel;
import com.buyerms.repository.DescriptionRepository;
import com.buyerms.repository.ItemsRepository;
import com.buyerms.repository.ItemViewRepository;
import com.buyerms.repository.PictureRepository;
@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemViewRepository itemViewRepositor;

    @Autowired
    private ItemsRepository itemRepositor;

    @Autowired
    private PictureRepository pictureRepositor;

    @Autowired
    private DescriptionRepository descptionRepositor;

    /**
     * Search item
     * @param context
     * @return List<ItemModel>
     */
    @Override
    public List<ItemModel> search(String context) {
        List<ItemViewEntity> lstEntity = itemViewRepositor.findBySearchContextLike("%" + context + "%");

        if (CollectionUtils.isEmpty(lstEntity)) {
            return null;
        }

        List<ItemModel> lstModel = new ArrayList<ItemModel>(lstEntity.size());

        //Convert entity to model
        lstEntity.stream().forEach(entity -> lstModel.add(toItemModel(entity)));

        return lstModel;

    }

    /**
     * Convert entity to model
     * @param entity
     * @return ItemModel
     */
    private ItemModel toItemModel(ItemViewEntity entity) {
        ItemModel model = new ItemModel();

        //Copy propeties from cart entity to cart model
        BeanUtils.copyProperties(entity, model);

        return model;
    }

    /**
     * Get item detail
     * @param id
     * @return ItemDetailModel
     */
    @Override
    public ItemDetailModel getItemDetail(Integer id){
        ItemDetailModel model = null;

        //Get item from Item table
        Optional<ItemsEntity> optEntity = itemRepositor.findById(id);

        if (optEntity.isPresent()) {
            ItemsEntity ItemsEntity = optEntity.get();

            //Get item info from Item view
            ItemViewEntity itemViewEntity = itemViewRepositor.findById(id).get();

            //Get pictures
            List<PictureEntity> lstPictures = pictureRepositor.findByItemId(id);

            //Get descriptions
            List<DescriptionEntity> lstescription = descptionRepositor.findByItemId(id);

            model = new ItemDetailModel();
            model.setId(String.valueOf(id));
            model.setCategory(String.valueOf(ItemsEntity.getCategoryId()));
            model.setSubcategory(String.valueOf(ItemsEntity.getSubcategoryId()));
            model.setManufactur(String.valueOf(ItemsEntity.getManufacturId()));
            model.setSellerId(String.valueOf(ItemsEntity.getSellId()));
            model.setSeller(itemViewEntity.getSeller());
            model.setVolume(ItemsEntity.getSalesNumber());
            model.setNum(1);
            model.setPictures((String[])lstPictures.toArray());
            model.setDescriptions((String[])lstescription.toArray());
        }

        return model;
    }
}
