package com.emart.sellerms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emart.sellerms.models.*;
import com.emart.sellerms.service.*;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {
	@Autowired
	private ItemService itemservice;

	@Autowired
	private ReportService reportservice;

	@Autowired
	private StockService stockservice;

	@GetMapping
	public String checin() {

		return "Iam seller";
	}

	@GetMapping("/manufacturer")
	public ResponseEntity<List<ManufacturModel>> getMamulist() {

		List<ManufacturModel> lst = itemservice.getpulldownlistManu();

		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(lst);
	}

	@GetMapping("/category")
	public ResponseEntity<List<CategoryModel>> getCatelist() {

		List<CategoryModel> lst = itemservice.getpulldownlistCat();

		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(lst);
	}

	@GetMapping("/subcategory")
	public ResponseEntity<List<SubCategoryModel>> getSubcatelist() {

		List<SubCategoryModel> lst = itemservice.getpulldownlistSubCat();

		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(lst);
	}

	@GetMapping("/report/{userId}")
	public ResponseEntity<List<ReportModel>> getReport(@PathVariable Integer userId) {

		List<ReportModel> lst = reportservice.getReport(userId);

		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(lst);
	}

	@GetMapping("/stock")
	public ResponseEntity<List<StockModel>> getStocks(@RequestParam("userId") String userId) {

		Integer id = Integer.valueOf(userId);
		List<StockModel> lst = stockservice.getStock(id);

		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(lst);
	}

	@PostMapping("/updatestock")
	public ResponseEntity<StockModel> andItem(@RequestParam("itemId") Integer itemId,
			@RequestParam("stock") Integer stock) {

		if (!stockservice.updateStocks(itemId, stock)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(null);

	}

	@PostMapping("/additem")
	public ResponseEntity<ItemsModel> andItem(@RequestBody ItemsModel item) {

		Boolean addrlt = itemservice.additem(item);
		if (!addrlt) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(item);

	}

}