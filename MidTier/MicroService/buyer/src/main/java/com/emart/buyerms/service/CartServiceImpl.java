package com.emart.buyerms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.buyerms.entity.CartEntity;
import com.emart.buyerms.entity.PictureEntity;
import com.emart.buyerms.models.CartModel;
import com.emart.buyerms.models.ItemDetailModel;
import com.emart.buyerms.repository.CartRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;

import ch.qos.logback.core.joran.conditional.IfAction;
@Service
public class CartServiceImpl implements CartService {
    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository cartRepositor;

    @Autowired
    private ItemService itemService;

    /**
     * Get cart
     * @param userId
     * @return List<CartModel>
     */
    @Override
    public List<CartModel> getCart(Integer userId) {
        List<CartEntity> lstEntity = cartRepositor.findByBuyerId(userId);

        if (CollectionUtils.isEmpty(lstEntity)) {
            log.info("The cart is empty! user id=" + userId);
            return null;
        }

        List<CartModel> lstModel = new ArrayList<CartModel>(lstEntity.size());

        //Convert entity to model
        lstEntity.stream().forEach(entity -> lstModel.add(toModel(entity)));

        return lstModel;
    }

    /**
     * Convert entity to model
     * @param entity
     * @return CartModel
     */
    private CartModel toModel(CartEntity entity) {
        CartModel model = new CartModel();

        //Copy propeties from cart entity to cart model
        BeanUtils.copyProperties(entity, model);
        
        model.setManufactur(entity.getItem().getManufacture().getName());
        model.setItem(entity.getItem().getItemname());
        model.setPrice(entity.getItem().getPrice());
        model.setCategory(entity.getItem().getCategory().getCategoryName());
        model.setSubcategory(entity.getItem().getSubcategory().getSubcategoryName());
        model.setTax(entity.getItem().getPrice().multiply(new BigDecimal("0.05")));
        
        List<PictureEntity> picList =entity.getItem().getPicture();

		if(picList !=null && picList.size()>0) {
			model.setPicture(picList.get(0).getPicturePath());
		}
        return model;
    }

    /**
     * Add item to buyer's cart.
     * If you have added before just refresh the number
     * @param model CartModel
     * @return the number of items in buyer's cart
     */
    @Override
    public Integer add(CartModel model) {
        CartEntity entity = new CartEntity();

        CartEntity obj = cartRepositor.findByItemId(model.getItemId());

        //item have been added also
        if( obj != null){

            int number= obj.getNumber();
            entity = obj;
            
        	entity.setNumber(number+1);
        }else {
            //Copy propeties from cart model to cart entity
            BeanUtils.copyProperties(model, entity);
		}

        //Add to cart
        cartRepositor.save(entity);

        //Get the number of items in buyer's cart
        return cartRepositor.findByBuyerId(model.getBuyerId()).size();
    }

    /**
     * Delete item from buyer's cart.
     * @param model CartModel
     * @return the number of items in buyer's cart
     */
    @Override
    public Integer delete(String id) {
        Optional<CartEntity> entity = cartRepositor.findById(Integer.valueOf(id));
        Integer userId = entity.get().getBuyerId();
        //Delete item from buyer's cart
        cartRepositor.delete(entity.get());

        //Get the number of items in buyer's cart
        return cartRepositor.findByBuyerId(userId).size();
    }

}
