package com.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supplier.entity.PurchasingRFQLineItem;
import com.supplier.service.PurchasingRFQLineItemService;

@RestController
@RequestMapping("/api/vendors/purchasing-rfq-line-item")
public class PurchasingRFQLineItemController {

    @Autowired
    private PurchasingRFQLineItemService lineItemService;

    @PostMapping
    public ResponseEntity<PurchasingRFQLineItem> createLineItem(@RequestHeader ("Authorization") String jwt, @RequestBody PurchasingRFQLineItem lineItem) {
        return ResponseEntity.ok(lineItemService.createLineItem(lineItem));
    }

//    @GetMapping
//    public ResponseEntity<List<PurchasingRFQLineItem>> getAllLineItems(@RequestHeader ("Authorization") String jwt) {
//        return ResponseEntity.ok(lineItemService.getAllLineItems());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PurchasingRFQLineItem> getLineItemById(@RequestHeader ("Authorization") String jwt, @PathVariable Long id) {
//        PurchasingRFQLineItem lineItem = lineItemService.getLineItemById(id);
//        return ResponseEntity.ok(lineItem);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<PurchasingRFQLineItem> updateLineItem(@RequestHeader ("Authorization") String jwt, @PathVariable Long id, @RequestBody PurchasingRFQLineItem lineItemDetails) {
//        PurchasingRFQLineItem updatedLineItem = lineItemService.updateLineItem(id, lineItemDetails);
//        return ResponseEntity.ok(updatedLineItem);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLineItem(@RequestHeader ("Authorization") String jwt, @PathVariable Long id) {
//        lineItemService.deleteLineItem(id);
//        return ResponseEntity.noContent().build();
//    }
}

