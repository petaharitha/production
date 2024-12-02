package com.bpl.Production.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.OrderInfo;
import com.bpl.Production.repository.OrderInfoRepository;

@Service
public class OrderInfoService {

    // Logger for logging service actions
    private static final Logger logger = LoggerFactory.getLogger(OrderInfoService.class);

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    // Create a new OrderInfo record
    public OrderInfo createOrderInfo(OrderInfo orderInfo) {
        logger.info("Creating new OrderInfo record with SO No: {}", orderInfo.getSoNo());
        OrderInfo savedOrderInfo = orderInfoRepository.save(orderInfo);
        logger.info("Created OrderInfo with ID: {}", savedOrderInfo.getId());
        return savedOrderInfo;
    }

    // Get all OrderInfo records
    public List<OrderInfo> getAllOrderInfo() {
        logger.info("Fetching all OrderInfo records");
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        logger.info("Retrieved {} OrderInfo records", orderInfoList.size());
        return orderInfoList;
    }

    // Get OrderInfo by ID
    public Optional<OrderInfo> getOrderInfoById(Integer id) {
        logger.info("Fetching OrderInfo with ID: {}", id);
        Optional<OrderInfo> orderInfoOptional = orderInfoRepository.findById(id);
        if (orderInfoOptional.isPresent()) {
            logger.info("Found OrderInfo with ID: {}", id);
        } else {
            logger.warn("OrderInfo with ID: {} not found", id);
        }
        return orderInfoOptional;
    }

    // Update an existing OrderInfo record
    public OrderInfo updateOrderInfo(Integer id, OrderInfo orderInfoDetails) {
        logger.info("Updating OrderInfo with ID: {}", id);
        
        Optional<OrderInfo> orderInfoOptional = orderInfoRepository.findById(id);
        if (orderInfoOptional.isPresent()) {
            OrderInfo existingOrderInfo = orderInfoOptional.get();

            // Log existing data before update
            logger.info("Existing OrderInfo: {}", existingOrderInfo);

            // Update fields
            existingOrderInfo.setCreatedBy(orderInfoDetails.getCreatedBy());
            existingOrderInfo.setLastModifiedBy(orderInfoDetails.getLastModifiedBy());
            existingOrderInfo.setCreatedDate(orderInfoDetails.getCreatedDate());
            existingOrderInfo.setLastModifiedDate(orderInfoDetails.getLastModifiedDate());
            existingOrderInfo.setSoNo(orderInfoDetails.getSoNo());
            existingOrderInfo.setCustomerPoNo(orderInfoDetails.getCustomerPoNo());
            existingOrderInfo.setCustomerNr(orderInfoDetails.getCustomerNr());
            existingOrderInfo.setCustomerName(orderInfoDetails.getCustomerName());
            existingOrderInfo.setConsigneeNumber(orderInfoDetails.getConsigneeNumber());
            existingOrderInfo.setConsigneeName(orderInfoDetails.getConsigneeName());
            existingOrderInfo.setOcNo(orderInfoDetails.getOcNo());
            existingOrderInfo.setExwDate(orderInfoDetails.getExwDate());
            existingOrderInfo.setExwWeek(orderInfoDetails.getExwWeek());
            existingOrderInfo.setExwMonth(orderInfoDetails.getExwMonth());
            existingOrderInfo.setExwYear(orderInfoDetails.getExwYear());
            existingOrderInfo.setMrwDate(orderInfoDetails.getMrwDate());
            existingOrderInfo.setMrwWeek(orderInfoDetails.getMrwWeek());
            existingOrderInfo.setMrwMonth(orderInfoDetails.getMrwMonth());
            existingOrderInfo.setMrwYear(orderInfoDetails.getMrwYear());
            existingOrderInfo.setBusinessUnit(orderInfoDetails.getBusinessUnit());
            existingOrderInfo.setSalesUnit(orderInfoDetails.getSalesUnit());
            existingOrderInfo.setMfs(orderInfoDetails.getMfs());
            existingOrderInfo.setLabelMarking(orderInfoDetails.getLabelMarking());
            existingOrderInfo.setOrderType(orderInfoDetails.getOrderType());
            existingOrderInfo.setFgid(orderInfoDetails.getFgid());
            existingOrderInfo.setRemarks(orderInfoDetails.getRemarks());
            existingOrderInfo.setIsPackingListCreated(orderInfoDetails.getIsPackingListCreated());
            existingOrderInfo.setIsContainerListCreated(orderInfoDetails.getIsContainerListCreated());
            existingOrderInfo.setStatus(orderInfoDetails.getStatus());
            existingOrderInfo.setIsWorkOrderGenerated(orderInfoDetails.getIsWorkOrderGenerated());
            existingOrderInfo.setIsApproval(orderInfoDetails.getIsApproval());

            // Log after update
            logger.info("Updated OrderInfo: {}", existingOrderInfo);

            // Save and return updated OrderInfo
            return orderInfoRepository.save(existingOrderInfo);
        } else {
            logger.warn("OrderInfo with ID: {} not found for update", id);
            return null; // Return null if not found for update
        }
    }

    // Delete an OrderInfo record
    public void deleteOrderInfo(Integer id) {
        logger.info("Attempting to delete OrderInfo with ID: {}", id);
        if (orderInfoRepository.existsById(id)) {
            orderInfoRepository.deleteById(id);
            logger.info("Successfully deleted OrderInfo with ID: {}", id);
        } else {
            logger.warn("OrderInfo with ID: {} not found for deletion", id);
        }
    }
}
