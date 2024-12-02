package com.bpl.Production.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.OrderItem;
import com.bpl.Production.repository.OrderItemRepository;

@Service
public class OrderItemService {

    // Logger for logging service actions
    private static final Logger logger = LoggerFactory.getLogger(OrderItemService.class);

    @Autowired
    private OrderItemRepository orderItemRepository;
    
    // Create new OrderItem
    public OrderItem createOrderItem(OrderItem orderItem) {
        logger.info("Creating new OrderItem with Order No: {}", orderItem.getOrderNo());
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        logger.info("Created OrderItem with ID: {}", savedOrderItem.getId());
        return savedOrderItem;
    }

    // Get all OrderItems
    public List<OrderItem> getAllOrderItems() {
        logger.info("Fetching all OrderItems");
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        logger.info("Retrieved {} OrderItems", orderItemList.size());
        return orderItemList;
    }

    // Get OrderItem by ID
    public Optional<OrderItem> getOrderItemById(Integer id) {
        logger.info("Fetching OrderItem with ID: {}", id);
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (orderItemOptional.isPresent()) {
            logger.info("Found OrderItem with ID: {}", id);
        } else {
            logger.warn("OrderItem with ID: {} not found", id);
        }
        return orderItemOptional;
    }

    // Update OrderItem by ID
    public OrderItem updateOrderItem(Integer id, OrderItem orderItemDetails) {
        logger.info("Updating OrderItem with ID: {}", id);
        
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (orderItemOptional.isPresent()) {
            OrderItem existingOrderItem = orderItemOptional.get();

            // Log existing data before update
            logger.info("Existing OrderItem: {}", existingOrderItem);

            // Update fields
            existingOrderItem.setCreatedBy(orderItemDetails.getCreatedBy());
            existingOrderItem.setLastModifiedBy(orderItemDetails.getLastModifiedBy());
            existingOrderItem.setCreatedDate(orderItemDetails.getCreatedDate());
            existingOrderItem.setLastModifiedDate(orderItemDetails.getLastModifiedDate());
            existingOrderItem.setMaterialNo(orderItemDetails.getMaterialNo());
            existingOrderItem.setHpg(orderItemDetails.getHpg());
            existingOrderItem.setPg(orderItemDetails.getPg());
            existingOrderItem.setQuality(orderItemDetails.getQuality());
            existingOrderItem.setShape(orderItemDetails.getShape());
            existingOrderItem.setExternalBrandName(orderItemDetails.getExternalBrandName());
            existingOrderItem.setQtyPcs(orderItemDetails.getQtyPcs());
            existingOrderItem.setQtyMtons(orderItemDetails.getQtyMtons());
            existingOrderItem.setPackingCode(orderItemDetails.getPackingCode());
            existingOrderItem.setRatePc(orderItemDetails.getRatePc());
            existingOrderItem.setPackPc(orderItemDetails.getPackPc());
            existingOrderItem.setFreightPc(orderItemDetails.getFreightPc());
            existingOrderItem.setTotal(orderItemDetails.getTotal());
            existingOrderItem.setRemarks(orderItemDetails.getRemarks());
            existingOrderItem.setPoPosNo(orderItemDetails.getPoPosNo());
            existingOrderItem.setOcPosNo(orderItemDetails.getOcPosNo());
            existingOrderItem.setFgid(orderItemDetails.getFgid());
            existingOrderItem.setOrderId(orderItemDetails.getOrderId());
            existingOrderItem.setOrderNo(orderItemDetails.getOrderNo());
            existingOrderItem.setGreenPcs(orderItemDetails.getGreenPcs());
            existingOrderItem.setGreenMt(orderItemDetails.getGreenMt());
            existingOrderItem.setPalletWeight(orderItemDetails.getPalletWeight());
            existingOrderItem.setItemMarking(orderItemDetails.getItemMarking());
            existingOrderItem.setExternalMaterialNumber(orderItemDetails.getExternalMaterialNumber());
            existingOrderItem.setFreeStockPieces(orderItemDetails.getFreeStockPieces());

            // Log after update
            logger.info("Updated OrderItem: {}", existingOrderItem);

            // Save and return updated OrderItem
            return orderItemRepository.save(existingOrderItem);
        } else {
            logger.warn("OrderItem with ID: {} not found for update", id);
            return null; // Return null if not found for update
        }
    }

    // Delete OrderItem by ID
    public void deleteOrderItem(Integer id) {
        logger.info("Attempting to delete OrderItem with ID: {}", id);
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            logger.info("Successfully deleted OrderItem with ID: {}", id);
        } else {
            logger.warn("OrderItem with ID: {} not found for deletion", id);
        }
    }
}
