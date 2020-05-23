package com.emart.userms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emart.userms.entity.BuyerEntity;
import com.emart.userms.models.BuyerModel;
import com.emart.userms.models.SellerModel;
import com.emart.userms.models.UserModel;
import com.emart.userms.service.UsermsService;
import com.emart.userms.util.JwtUtil;

@RestController
@RequestMapping(value = "/user")
public class UsermsController {
	@Autowired
	private UsermsService userService;
	
    
	@GetMapping
	public int findAllUsers() {

		UserModel user=new UserModel();
		user.setUserame("jjwang");
		user.setRole("1");
		return userService.getUserId(user);
	}
	
	@PostMapping
    public ResponseEntity<UserModel> login(@RequestBody UserModel user) {
		

		int userId = userService.getUserId(user);
		
		if (userId==0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
		}
		
		//create token
		user.setId(userId);
		user.setToken(JwtUtil.generateToken(userId));
		return ResponseEntity.ok(user);
		
    }
	
	/**
	 * 
	 * @param buyer
	 * @return
	 */
	@PostMapping("/signinbuyer")
	public ResponseEntity<BuyerModel> signinAsBuyer(@RequestBody BuyerModel buyer) {
		if(userService.signinBuyer(buyer)) {

			return ResponseEntity.status(HttpStatus.CREATED).body(buyer);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	
	/**
	 * 
	 * @param seller
	 * @return
	 */
	@PostMapping("/signinseller")
	public ResponseEntity<SellerModel> signinAsBuyer(@RequestBody SellerModel seller) {
		if(userService.signinSeller(seller)) {

			return ResponseEntity.status(HttpStatus.CREATED).body(seller);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

}