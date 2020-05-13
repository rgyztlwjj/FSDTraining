package com.sellerms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sellerms.entity.*;
import com.sellerms.repository.*;

import com.sellerms.models.*;;

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

	/**
	 * Add a new item
	 * @param additem
	 * @return boolean true:saved false:not saved
	 * 
	 */
	@Override
	public boolean addItem(AdditemModel additem) {

		ItemsEntity item = new ItemsEntity();
		BeanUtils.copyProperties(additem, item);

		try {
			itemsrepository.save(item);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Get Manufacture pulldown list
	 * @return List<ManufacturModel> 
	 */
	@Override
	public List<ManufacturModel> getpulldownlistManu() {
		ManufacturModel model = new ManufacturModel();

		List<ManufacturEntity> listManufacturEntities = manufacturrepository.findAll();

		if (CollectionUtils.isEmpty(listManufacturEntities)) {
			return null;
		}

		List<ManufacturModel> lstModel = new ArrayList<ManufacturModel>(listManufacturEntities.size());

		listManufacturEntities.stream().forEach(entity -> lstModel.add(convert(entity, model)));

		return lstModel;

	}

	
	/**
	 * Get Category pulldown list
	 * @return List<CategoryModel> 
	 */
	@Override
	public List<CategoryModel> getpulldownlistCat() {

		CategoryModel model = new CategoryModel();

		List<CategoryEntity> listcategoriEntities = categoryrepository.findAll();

		if (CollectionUtils.isEmpty(listcategoriEntities)) {
			return null;
		}

		List<CategoryModel> lstModel = new ArrayList<CategoryModel>(listcategoriEntities.size());

		listcategoriEntities.stream().forEach(entity -> lstModel.add(convert(entity, model)));

		return lstModel;

	}

	
	/**
	 * Get SubCategory pulldown list
	 * @return List<SubCategoryModel> 
	 */
	@Override
	public List<SubCategoryModel> getpulldownlistSubCat() {
		SubCategoryModel model = new SubCategoryModel();

		List<SubCategoryEntity> listsubcategoriEntities = subcategoryrepository.findAll();

		if (CollectionUtils.isEmpty(listsubcategoriEntities)) {
			return null;
		}

		List<SubCategoryModel> lstModel = new ArrayList<SubCategoryModel>(listsubcategoriEntities.size());

		listsubcategoriEntities.stream().forEach(entity -> lstModel.add(convert(entity, model)));

		return lstModel;
	}

	
	/**
	 * Convert entity to model
	 * @return SubCategoryModel
	 */
	private SubCategoryModel convert(SubCategoryEntity entity, SubCategoryModel model) {
		BeanUtils.copyProperties(entity, model);
		return model;
	}

	
	/**
	 * Convert entity to model
	 * @return ManufacturModel
	 */
	private ManufacturModel convert(ManufacturEntity entity, ManufacturModel model) {

		BeanUtils.copyProperties(entity, model);

		return model;

	}

	
	/**
	 * Convert entity to model
	 * @return CategoryModel
	 */
	private CategoryModel convert(CategoryEntity entity, CategoryModel model) {

		BeanUtils.copyProperties(entity, model);
		return model;

	}

}
