package com.bpl.Production.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.OrderDetails;
import com.bpl.Production.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {

    // Logger for logging service actions
    private static final Logger logger = LoggerFactory.getLogger(OrderDetailsService.class);

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    // Create OrderDetails
    public OrderDetails saveOrderDetails(OrderDetails orderDetails) {
        logger.info("Creating new OrderDetails with OrderNo: {}", orderDetails.getOrderNo());
        OrderDetails savedOrderDetails = orderDetailsRepository.save(orderDetails);
        logger.info("Created OrderDetails with ID: {}", savedOrderDetails.getId());
        return savedOrderDetails;
    }

    // Get OrderDetails by ID
    public OrderDetails getOrderDetailsById(Integer id) {
        logger.info("Fetching OrderDetails with ID: {}", id);
        Optional<OrderDetails> orderDetails = orderDetailsRepository.findById(id);
        if (orderDetails.isPresent()) {
            logger.info("Found OrderDetails with ID: {}", id);
            return orderDetails.get();
        } else {
            logger.warn("OrderDetails with ID: {} not found", id);
            return null; // Return null if not found
        }
    }

    // Get All OrderDetails
    public List<OrderDetails> getAllOrderDetails() {
        logger.info("Fetching all OrderDetails");
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findAll();
        logger.info("Retrieved {} OrderDetails", orderDetailsList.size());
        return orderDetailsList;
    }

    // Update OrderDetails
    public OrderDetails updateOrderDetails(Integer id, OrderDetails orderDetails) {
        logger.info("Updating OrderDetails with ID: {}", id);
        
        Optional<OrderDetails> existingOrderDetailsOpt = orderDetailsRepository.findById(id);
        if (existingOrderDetailsOpt.isPresent()) {
            OrderDetails existingOrderDetails = existingOrderDetailsOpt.get();
            // Log existing data before update
            logger.info("Existing OrderDetails: {}", existingOrderDetails);

            // Here, you can update the fields you want to modify
            existingOrderDetails.setMaterialNo(orderDetails.getMaterialNo());
            existingOrderDetails.setHpg(orderDetails.getHpg());
            existingOrderDetails.setPg(orderDetails.getPg());
            existingOrderDetails.setQuality(orderDetails.getQuality());
            existingOrderDetails.setShape(orderDetails.getShape());
            existingOrderDetails.setExternalBrandName(orderDetails.getExternalBrandName());
            existingOrderDetails.setQtyPcs(orderDetails.getQtyPcs());
            existingOrderDetails.setQtyMtons(orderDetails.getQtyMtons());
            existingOrderDetails.setPackingCode(orderDetails.getPackingCode());
            existingOrderDetails.setRatePc(orderDetails.getRatePc());
            existingOrderDetails.setPackPc(orderDetails.getPackPc());
            existingOrderDetails.setFreightPc(orderDetails.getFreightPc());
            existingOrderDetails.setTotal(orderDetails.getTotal());
            existingOrderDetails.setRemarks(orderDetails.getRemarks());
            existingOrderDetails.setPoPosNo(orderDetails.getPoPosNo());
            existingOrderDetails.setOcPosNo(orderDetails.getOcPosNo());
            existingOrderDetails.setFgid(orderDetails.getFgid());
            existingOrderDetails.setOrderId(orderDetails.getOrderId());
            existingOrderDetails.setOrderNo(orderDetails.getOrderNo());
            existingOrderDetails.setGreenPcs(orderDetails.getGreenPcs());
            existingOrderDetails.setGreenMt(orderDetails.getGreenMt());

            // Save updated order details
            OrderDetails updatedOrderDetails = orderDetailsRepository.save(existingOrderDetails);
            logger.info("Successfully updated OrderDetails with ID: {}", id);
            return updatedOrderDetails;
        } else {
            logger.error("OrderDetails with ID: {} not found for update", id);
            return null; // Return null if the record wasn't found
        }
    }

    // Delete OrderDetails
    public boolean deleteOrderDetails(Integer id) {
        logger.info("Attempting to delete OrderDetails with ID: {}", id);
        if (orderDetailsRepository.existsById(id)) {
            orderDetailsRepository.deleteById(id);
            logger.info("Successfully deleted OrderDetails with ID: {}", id);
            return true;
        } else {
            logger.error("OrderDetails with ID: {} not found for deletion", id);
            return false; // Return false if not found to delete
        }
    }
}
