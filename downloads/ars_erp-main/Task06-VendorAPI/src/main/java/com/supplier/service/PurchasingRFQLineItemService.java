package com.supplier.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.itextpdf.text.List;
import com.supplier.entity.PurchasingRFQLineItem;
import com.supplier.exception.ResourceNotFoundException;
import com.supplier.repository.PurchasingRFQLineItemRepository;

import jakarta.persistence.Cacheable;

@Service
public class PurchasingRFQLineItemService {
    private static final Logger logger = LoggerFactory.getLogger(PurchasingRFQLineItemService.class);

    @Autowired
    private PurchasingRFQLineItemRepository lineItemRepository;
    
    

    public PurchasingRFQLineItem createLineItem(PurchasingRFQLineItem lineItem) {
        logger.info("Creating line item: {}", lineItem);
        return lineItemRepository.save(lineItem);
    }
//
//    @Cacheable(value = "lineItems")
//    public List<PurchasingRFQLineItem> getAllLineItems() {
//        logger.info("Fetching all line items");
//        return lineItemRepository.findAll();
//    }
//
//    @Cacheable(value = "lineItems", key = "#id")
//    public PurchasingRFQLineItem getLineItemById(Long id) {
//        logger.info("Fetching line item with id: {}", id);
//        PurchasingRFQLineItem lineItem = lineItemRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Line item not found with id " + id));
//
//        // Manually access the purchasingRFQ fields to ensure they are fetched
//        lineItem.getPurchasingRFQ().getVendor();
//        lineItem.getPurchasingRFQ().getDate();
//        return lineItem;
//    }

    @CacheEvict(value = "lineItems", key = "#id")
    public PurchasingRFQLineItem updateLineItem(Long id, PurchasingRFQLineItem lineItemDetails) {
        logger.info("Updating line item with id: {}", id);
        PurchasingRFQLineItem lineItem = lineItemRepository.findById(id).orElse(null);
        if (lineItem != null) {
            lineItem.setName(lineItemDetails.getName());
            lineItem.setUnitOfMeasure(lineItemDetails.getUnitOfMeasure());
            lineItem.setQuantity(lineItemDetails.getQuantity());
            lineItem.setDeliveryBy(lineItemDetails.getDeliveryBy());
            return lineItemRepository.save(lineItem);
        }
        logger.warn("Line item with id {} not found for update", id);
        return null;
    }
//
//    @CacheEvict(value = "lineItems", key = "#id")
//    public void deleteLineItem(Long id) {
//        logger.info("Deleting line item with id: {}", id);
//        lineItemRepository.deleteById(id);
//    }
}

