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

import com.emart.buyerms.models.PurchaseHistoryModel;
import com.emart.buyerms.service.PurchaseHistoryService;

@RestController
@RequestMapping(value = "/buyer/history")
public class PurchaseHistoryController {
    @Autowired
    private PurchaseHistoryService service;
    /**
     * Get purchase history
     * @param userId
     * @return List<PurchaseHistoryModel>
     */
    @GetMapping
    public ResponseEntity<List<PurchaseHistoryModel>> getPurchaseHistory(@RequestParam("userId") String userId) {
        List<PurchaseHistoryModel> lst = service.getPurchaseHistory(userId);

        if (CollectionUtils.isEmpty(lst)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(lst);
    }
}