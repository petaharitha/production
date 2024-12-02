package com.bpl.Production.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.OrderInfoRevision;
import com.bpl.Production.repository.OrderInfoRevisionRepository;

@Service
public class OrderInfoRevisionService {

    // Logger for logging service actions
    private static final Logger logger = LoggerFactory.getLogger(OrderInfoRevisionService.class);

    @Autowired
    private OrderInfoRevisionRepository orderInfoRevisionRepository;

    // Create OrderInfoRevision
    public OrderInfoRevision createOrderInfoRevision(OrderInfoRevision orderInfoRevision) {
        logger.info("Creating OrderInfoRevision with SO No: {}", orderInfoRevision.getSoNo());
        OrderInfoRevision savedOrderInfoRevision = orderInfoRevisionRepository.save(orderInfoRevision);
        logger.info("Created OrderInfoRevision with ID: {}", savedOrderInfoRevision.getId());
        return savedOrderInfoRevision;
    }

    // Get all OrderInfoRevisions
    public List<OrderInfoRevision> getAllOrderInfoRevisions() {
        logger.info("Fetching all OrderInfoRevisions");
        List<OrderInfoRevision> orderInfoRevisions = orderInfoRevisionRepository.findAll();
        logger.info("Retrieved {} OrderInfoRevisions", orderInfoRevisions.size());
        return orderInfoRevisions;
    }

    // Get OrderInfoRevision by ID
    public Optional<OrderInfoRevision> getOrderInfoRevisionById(Integer id) {
        logger.info("Fetching OrderInfoRevision with ID: {}", id);
        Optional<OrderInfoRevision> orderInfoRevision = orderInfoRevisionRepository.findById(id);
        if (orderInfoRevision.isPresent()) {
            logger.info("Found OrderInfoRevision with ID: {}", id);
        } else {
            logger.warn("OrderInfoRevision with ID: {} not found", id);
        }
        return orderInfoRevision;
    }

    // Delete OrderInfoRevision by ID
    public void deleteOrderInfoRevision(Integer id) {
        logger.info("Attempting to delete OrderInfoRevision with ID: {}", id);
        if (orderInfoRevisionRepository.existsById(id)) {
            orderInfoRevisionRepository.deleteById(id);
            logger.info("Successfully deleted OrderInfoRevision with ID: {}", id);
        } else {
            logger.warn("OrderInfoRevision with ID: {} not found for deletion", id);
        }
    }

    // Update OrderInfoRevision by ID
    public Optional<OrderInfoRevision> updateOrderInfoRevision(Integer id, OrderInfoRevision orderInfoRevisionDetails) {
        logger.info("Updating OrderInfoRevision with ID: {}", id);
        
        Optional<OrderInfoRevision> existingOrderInfoRevisionOpt = orderInfoRevisionRepository.findById(id);
        if (existingOrderInfoRevisionOpt.isPresent()) {
            OrderInfoRevision existingOrderInfoRevision = existingOrderInfoRevisionOpt.get();
            
            // Log existing data before update
            logger.info("Existing OrderInfoRevision: {}", existingOrderInfoRevision);

            // Update fields
            existingOrderInfoRevision.setSoNo(orderInfoRevisionDetails.getSoNo());
            existingOrderInfoRevision.setCustomerPoNo(orderInfoRevisionDetails.getCustomerPoNo());
            existingOrderInfoRevision.setCustomerNr(orderInfoRevisionDetails.getCustomerNr());
            existingOrderInfoRevision.setCustomerName(orderInfoRevisionDetails.getCustomerName());
            existingOrderInfoRevision.setConsigneeNumber(orderInfoRevisionDetails.getConsigneeNumber());
            existingOrderInfoRevision.setConsigneeName(orderInfoRevisionDetails.getConsigneeName());
            existingOrderInfoRevision.setOcNo(orderInfoRevisionDetails.getOcNo());
            existingOrderInfoRevision.setExwDate(orderInfoRevisionDetails.getExwDate());
            existingOrderInfoRevision.setExwWeek(orderInfoRevisionDetails.getExwWeek());
            existingOrderInfoRevision.setExwMonth(orderInfoRevisionDetails.getExwMonth());
            existingOrderInfoRevision.setExwYear(orderInfoRevisionDetails.getExwYear());
            existingOrderInfoRevision.setMrwDate(orderInfoRevisionDetails.getMrwDate());
            existingOrderInfoRevision.setMrwWeek(orderInfoRevisionDetails.getMrwWeek());
            existingOrderInfoRevision.setMrwMonth(orderInfoRevisionDetails.getMrwMonth());
            existingOrderInfoRevision.setMrwYear(orderInfoRevisionDetails.getMrwYear());
            existingOrderInfoRevision.setBusinessUnit(orderInfoRevisionDetails.getBusinessUnit());
            existingOrderInfoRevision.setSalesUnit(orderInfoRevisionDetails.getSalesUnit());
            existingOrderInfoRevision.setMfs(orderInfoRevisionDetails.getMfs());
            existingOrderInfoRevision.setLabelMarking(orderInfoRevisionDetails.getLabelMarking());
            existingOrderInfoRevision.setOrderType(orderInfoRevisionDetails.getOrderType());
            existingOrderInfoRevision.setFgid(orderInfoRevisionDetails.getFgid());
            existingOrderInfoRevision.setOrderid(orderInfoRevisionDetails.getOrderid());
            existingOrderInfoRevision.setReason(orderInfoRevisionDetails.getReason());
            existingOrderInfoRevision.setRevisionNo(orderInfoRevisionDetails.getRevisionNo());
            existingOrderInfoRevision.setRemarks(orderInfoRevisionDetails.getRemarks());
            existingOrderInfoRevision.setIsPackingListCreated(orderInfoRevisionDetails.getIsPackingListCreated());
            existingOrderInfoRevision.setIsContainerListCreated(orderInfoRevisionDetails.getIsContainerListCreated());
            existingOrderInfoRevision.setStatus(orderInfoRevisionDetails.getStatus());
            existingOrderInfoRevision.setIsWorkOrderGenerated(orderInfoRevisionDetails.getIsWorkOrderGenerated());
            existingOrderInfoRevision.setIsApproval(orderInfoRevisionDetails.getIsApproval());

            // Log after update
            logger.info("Updated OrderInfoRevision: {}", existingOrderInfoRevision);

            // Save updated order info revision
            OrderInfoRevision updatedOrderInfoRevision = orderInfoRevisionRepository.save(existingOrderInfoRevision);
            logger.info("Successfully updated OrderInfoRevision with ID: {}", id);
            return Optional.of(updatedOrderInfoRevision);
        } else {
            logger.warn("OrderInfoRevision with ID: {} not found for update", id);
            return Optional.empty(); // Return empty if not found for update
        }
    }
}
