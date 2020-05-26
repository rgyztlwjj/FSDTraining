package com.emart.buyerms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emart.buyerms.models.CartModel;
import com.emart.buyerms.service.CartService;

@RestController
@RequestMapping(value = "/buyer/cart")
public class CartController {

	@Autowired
	private CartService service;

	/**
	 * Get cart
	 * 
	 * @param userId
	 * @return List<ItemModel>
	 */
	@GetMapping
	public ResponseEntity<List<CartModel>> getCart(@RequestParam("userId") String userId) {

		List<CartModel> lst = service.getCart(Integer.parseInt(userId));

		if (CollectionUtils.isEmpty(lst)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.ok(lst);
	}

	/**
	 * Add item to buyer's cart.
	 * 
	 * @param model CartModel
	 * @return the number of items in buyer's cart
	 */
	@PostMapping("/add")
	public ResponseEntity<Integer> addToCart(@RequestBody CartModel model) {
		return ResponseEntity.ok(service.add(model));
	}

	/**
	 * Delete item from buyer's cart.
	 * 
	 * @param model CartModel
	 * @return the number of items in buyer's cart
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<Integer> delete(@RequestParam("Id") String id) {
		return ResponseEntity.ok(service.delete(id));
	}
}