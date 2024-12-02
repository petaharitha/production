package com.bpl.Production.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.OrderDetailsRevision;
import com.bpl.Production.repository.OrderDetailsRevisionRepository;

@Service
public class OrderDetailsRevisionService {

    // Logger for logging service actions
    private static final Logger logger = LoggerFactory.getLogger(OrderDetailsRevisionService.class);

    @Autowired
    private OrderDetailsRevisionRepository orderDetailsRevisionRepository;
    
    // Create a new OrderDetailsRevision
    public OrderDetailsRevision createOrderDetailsRevision(OrderDetailsRevision orderDetailsRevision) {
        logger.info("Creating new OrderDetailsRevision with materialNo: {}", orderDetailsRevision.getMaterialNo());
        OrderDetailsRevision savedOrderDetailsRevision = orderDetailsRevisionRepository.save(orderDetailsRevision);
        logger.info("Created OrderDetailsRevision with ID: {}", savedOrderDetailsRevision.getId());
        return savedOrderDetailsRevision;
    }

    // Get OrderDetailsRevision by ID
    public Optional<OrderDetailsRevision> getOrderDetailsRevisionById(Integer id) {
        logger.info("Fetching OrderDetailsRevision with ID: {}", id);
        Optional<OrderDetailsRevision> orderDetailsRevision = orderDetailsRevisionRepository.findById(id);
        if (orderDetailsRevision.isPresent()) {
            logger.info("Found OrderDetailsRevision with ID: {}", id);
        } else {
            logger.warn("OrderDetailsRevision with ID: {} not found", id);
        }
        return orderDetailsRevision;
    }

    // Get all OrderDetailsRevisions
    public List<OrderDetailsRevision> getAllOrderDetailsRevisions() {
        logger.info("Fetching all OrderDetailsRevisions");
        List<OrderDetailsRevision> orderDetailsRevisions = orderDetailsRevisionRepository.findAll();
        logger.info("Retrieved {} OrderDetailsRevisions", orderDetailsRevisions.size());
        return orderDetailsRevisions;
    }

    // Update an existing OrderDetailsRevision
    public OrderDetailsRevision updateOrderDetailsRevision(Integer id, OrderDetailsRevision updatedOrderDetailsRevision) {
        logger.info("Updating OrderDetailsRevision with ID: {}", id);
        Optional<OrderDetailsRevision> existingOrderDetailsRevisionOpt = orderDetailsRevisionRepository.findById(id);
        if (existingOrderDetailsRevisionOpt.isPresent()) {
            OrderDetailsRevision existingOrderDetailsRevision = existingOrderDetailsRevisionOpt.get();
            // Here, you can update the fields you want to modify
            logger.info("Updating fields for OrderDetailsRevision ID: {}", id);
            existingOrderDetailsRevision.setMaterialNo(updatedOrderDetailsRevision.getMaterialNo());
            existingOrderDetailsRevision.setHpg(updatedOrderDetailsRevision.getHpg());
            existingOrderDetailsRevision.setPg(updatedOrderDetailsRevision.getPg());
            existingOrderDetailsRevision.setQuality(updatedOrderDetailsRevision.getQuality());
            existingOrderDetailsRevision.setShape(updatedOrderDetailsRevision.getShape());
            existingOrderDetailsRevision.setQtyPcs(updatedOrderDetailsRevision.getQtyPcs());
            existingOrderDetailsRevision.setQtyMtons(updatedOrderDetailsRevision.getQtyMtons());
            existingOrderDetailsRevision.setRatePc(updatedOrderDetailsRevision.getRatePc());
            existingOrderDetailsRevision.setTotal(updatedOrderDetailsRevision.getTotal());
            existingOrderDetailsRevision.setRemarks(updatedOrderDetailsRevision.getRemarks());
            
            OrderDetailsRevision updatedRecord = orderDetailsRevisionRepository.save(existingOrderDetailsRevision);
            logger.info("Successfully updated OrderDetailsRevision with ID: {}", id);
            return updatedRecord;
        } else {
            logger.error("OrderDetailsRevision with ID: {} not found for update", id);
            throw new RuntimeException("OrderDetailsRevision with id " + id + " not found.");
        }
    }

    // Delete an OrderDetailsRevision by ID
    public void deleteOrderDetailsRevision(Integer id) {
        logger.info("Attempting to delete OrderDetailsRevision with ID: {}", id);
        Optional<OrderDetailsRevision> orderDetailsRevision = orderDetailsRevisionRepository.findById(id);
        if (orderDetailsRevision.isPresent()) {
            orderDetailsRevisionRepository.deleteById(id);
            logger.info("Successfully deleted OrderDetailsRevision with ID: {}", id);
        } else {
            logger.error("OrderDetailsRevision with ID: {} not found for deletion", id);
            throw new RuntimeException("OrderDetailsRevision with id " + id + " not found.");
        }
    }
}
