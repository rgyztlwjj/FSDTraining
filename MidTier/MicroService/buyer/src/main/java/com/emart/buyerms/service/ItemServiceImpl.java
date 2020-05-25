package com.emart.buyerms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.emart.buyerms.entity.DescriptionsEntity;
import com.emart.buyerms.entity.ItemViewEntity;
import com.emart.buyerms.entity.ItemsEntity;
import com.emart.buyerms.entity.PictureEntity;
import com.emart.buyerms.models.ItemDetailModel;
import com.emart.buyerms.models.ItemsModel;
import com.emart.buyerms.repository.DescriptionsRepository;
import com.emart.buyerms.repository.ItemViewRepository;
import com.emart.buyerms.repository.ItemsRepository;
import com.emart.buyerms.repository.PictureRepository;

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
	private DescriptionsRepository descptionRepositor;

	/**
	 * Search item
	 * 
	 * @param context
	 * @return List<ItemsModel>
	 */
	@Override
	public List<ItemsModel> search(String context) {
		List<ItemsEntity> lstEntity = itemRepositor.findByManufacture_NameLike("%" + context + "%");

		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}

		List<ItemsModel> lstModel = new ArrayList<ItemsModel>(lstEntity.size());

		// Convert entity to model
		lstEntity.stream().forEach(entity -> lstModel.add(toItemsModel(entity)));

		return lstModel;

	}

	/**
	 * Search item
	 * 
	 * @param startPrice endPrice Manufactuer
	 * @return List<ItemsModel>
	 */
	@Override
	public List<ItemsModel> searchbyfilter(Integer manufacturId,String startPrice,String endPrice) {
		List<ItemsEntity> lstEntity = itemRepositor.findByManufacturId(manufacturId);

		if (CollectionUtils.isEmpty(lstEntity)) {
			return null;
		}

		// Filter by FromDate
		if (!StringUtils.isEmpty(startPrice)) {
			BigDecimal intStartPrice = new BigDecimal(startPrice);
			lstEntity = lstEntity.stream().filter(e -> e.getPrice().compareTo(intStartPrice) >= 0)
					.collect(Collectors.toList());
		}

		// Filter by ToDate
		if (!StringUtils.isEmpty(endPrice)) {
			BigDecimal intEndPrice = new BigDecimal(endPrice);
			lstEntity = lstEntity.stream().filter(e -> e.getPrice().compareTo(intEndPrice) <= 0)
					.collect(Collectors.toList());
		}
		List<ItemsModel> lstModel = new ArrayList<ItemsModel>(lstEntity.size());

		// Convert entity to model
		lstEntity.stream().forEach(entity -> lstModel.add(toItemsModel(entity)));

		return lstModel;

	}

	/**
	 * Convert entity to model
	 * 
	 * @param model
	 * @return ItemviewModel
	 */
	private void editModel(ItemsModel model) {
		
		if(!model.getDescriptions().isEmpty()) {
			String description ="";
			
			List<DescriptionsEntity> desList=model.getDescriptions();
			
			for (int i = 0; i < desList.size(); i++) {
				
				description=description.concat(desList.get(i).getDescription()).concat(" ");

			}
			model.setDescriptionString(description);
			
		}else {
			model.setDescriptionString("");
			
		}
	
	}

	/**
	 * Convert entity to model
	 * 
	 * @param entity
	 * @return ItemsModel
	 */
	private ItemsModel toItemsModel(ItemsEntity entity) {
		ItemsModel model = new ItemsModel();

		// Copy propeties from cart entity to cart model
		BeanUtils.copyProperties(entity, model);
	
		//edit description
		editModel(model);

		return model;
	}

	/**
	 * Get item detail
	 * 
	 * @param id
	 * @return ItemDetailModel
	 */
	@Override
	public ItemsModel getItemDetail(Integer id) {
		ItemsModel model = null;

		// Get item from Item table
		Optional<ItemsEntity> optEntity = itemRepositor.findById(id);

		if (optEntity.isPresent()) {
			model = toItemsModel(optEntity.get());
		}

		return model;
	}
}
