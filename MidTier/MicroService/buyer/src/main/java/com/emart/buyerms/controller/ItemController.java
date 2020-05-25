package com.emart.buyerms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emart.buyerms.models.ItemDetailModel;
import com.emart.buyerms.models.ItemModel;
import com.emart.buyerms.models.ItemsModel;
import com.emart.buyerms.service.ItemService;

@RestController
@RequestMapping(value = "/buyer/item")
public class ItemController {
    @Autowired
    private ItemService service;


    @GetMapping
    public String search() {

        return "I am buyer";
    }
    
    
    /**
     * Search items
     * @param context
     * @return List<ItemModel>
     */
    @GetMapping("/search")
    public ResponseEntity<List<ItemsModel>> search(@RequestParam("context") String context) {
    	if("".equals(context)) {

            return ResponseEntity.ok(null);
    	}
        List<ItemsModel> lst = service.search(context);

        if (CollectionUtils.isEmpty(lst)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(lst);
    }

    
    /**
     * Search items by filter
     * @param context
     * @return List<ItemModel>
     */
    @GetMapping("/filter")
    public ResponseEntity<List<ItemsModel>> search(@RequestParam("startPrice") String startPrice,
			@RequestParam("endPrice") String endPrice,
			@RequestParam("manufacturer") Integer manufacturer) {
        List<ItemsModel> lst = service.searchbyfilter(manufacturer, startPrice, endPrice);

        if (CollectionUtils.isEmpty(lst)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(lst);
    }

    /**
     * View item detail
     * @param itemId
     * @return ItemDetailModel
     */
    @GetMapping("/detail")
    public ResponseEntity<ItemsModel> viewDetail(@RequestParam("itemId") String itemId) {
        ItemsModel item = service.getItemDetail(Integer.parseInt(itemId));

        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(item);
    }
}