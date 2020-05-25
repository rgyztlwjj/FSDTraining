package com.emart.sellerms.service;

import java.util.List;

import com.emart.sellerms.models.*;

public interface ItemService {
	

//	public boolean addItem(AdditemModel additem);
	
	public List<ManufacturModel> getpulldownlistManu();
	
	public List<CategoryModel> getpulldownlistCat();
	
	public List<SubCategoryModel> getpulldownlistSubCat();

	public boolean additem(ItemsModel model);
	
}
