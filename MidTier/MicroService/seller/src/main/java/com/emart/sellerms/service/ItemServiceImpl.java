package com.emart.sellerms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.emart.sellerms.entity.*;
import com.emart.sellerms.models.*;
import com.emart.sellerms.models.ItemsModel;
import com.emart.sellerms.repository.*;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemsRepository itemsrepository;

	@Autowired
	private ManufacturRepository manufacturrepository;

	@Autowired
	private CategoryRepository categoryrepository;

	@Autowired
	private SubCategoryRepository subcategoryrepository;
	
	@Autowired
	private PictureRepository pictureRepository;
	
	@Autowired
	private DescriptionsRepository descriptionsRepository;

	/**
	 * Add a new item
	 * 
	 */
	@Override
	public boolean additem(ItemsModel model) {
		ItemsEntity entity = new ItemsEntity();
		
		BeanUtils.copyProperties(model, entity);	
		try {
			entity = itemsrepository.save(entity);
			
			model.setId(entity.getId());
			
			//Insert into item_picture table
			for (int i = 0; i < model.getPictures().length; i++) {
				PictureEntity pictureEntity = new PictureEntity();
				pictureEntity.setItemId(model.getId());
				pictureEntity.setPicturePath(model.getPictures()[i]);
				pictureRepository.save(pictureEntity);
			}
			
			// Insert into item_description table
			String description =model.getDescriptionString();
			String[] descriptionslst = description.split(";");

			for (int i = 0; i < descriptionslst.length; i++) {
				DescriptionsEntity descEntity = new DescriptionsEntity();
				descEntity.setItemId(model.getId());
				descEntity.setDescription(descriptionslst[i]);
				descriptionsRepository.save(descEntity);
			}
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<ManufacturModel> getpulldownlistManu() {

		List<ManufacturEntity> listManufacturEntities = manufacturrepository.findAll();

		if (CollectionUtils.isEmpty(listManufacturEntities)) {
			return null;
		}

		List<ManufacturModel> lstModel = new ArrayList<ManufacturModel>(listManufacturEntities.size());

		listManufacturEntities.stream().forEach(entity -> lstModel.add(conver(entity)));

		return lstModel;

	}

	@Override
	public List<CategoryModel> getpulldownlistCat() {


		List<CategoryEntity> listcategoriEntities = categoryrepository.findAll();

		if (CollectionUtils.isEmpty(listcategoriEntities)) {
			return null;
		}

		List<CategoryModel> lstModel = new ArrayList<CategoryModel>(listcategoriEntities.size());

		listcategoriEntities.stream().forEach(entity -> lstModel.add(conver(entity)));

		return lstModel;

	}

	@Override
	public List<SubCategoryModel> getpulldownlistSubCat() {
		List<SubCategoryEntity> listsubcategoriEntities = subcategoryrepository.findAll();

		if (CollectionUtils.isEmpty(listsubcategoriEntities)) {
			return null;
		}

		List<SubCategoryModel> lstModel = new ArrayList<SubCategoryModel>(listsubcategoriEntities.size());

		listsubcategoriEntities.stream().forEach(entity -> lstModel.add(conver(entity)));

		return lstModel;
	}

	private SubCategoryModel conver(SubCategoryEntity entity) {

		SubCategoryModel model = new SubCategoryModel();

		BeanUtils.copyProperties(entity, model);
		return model;
	}

	private ManufacturModel conver(ManufacturEntity entity) {

		ManufacturModel model = new ManufacturModel();
		
		BeanUtils.copyProperties(entity, model);

		return model;

	}

	private CategoryModel conver(CategoryEntity entity) {

		CategoryModel model = new CategoryModel();

		BeanUtils.copyProperties(entity, model);
		return model;

	}



}
