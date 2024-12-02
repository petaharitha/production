package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.FreeStock;
import com.bpl.Production.repository.FreeStockRepository;

@Service
public class FreeStockService {

    private static final Logger logger = LoggerFactory.getLogger(FreeStockService.class); // Initialize logger

    @Autowired
    private FreeStockRepository freeStockRepository;

    // Create FreeStock
    public FreeStock createFreeStock(FreeStock freeStock) {
        logger.info("Creating FreeStock: {}", freeStock);
        FreeStock savedFreeStock = freeStockRepository.save(freeStock);
        logger.info("FreeStock created with ID: {}", savedFreeStock.getId());
        return savedFreeStock;
    }

    // Get a FreeStock by ID
    public Optional<FreeStock> getFreeStockById(Integer id) {
        logger.info("Fetching FreeStock with ID: {}", id);
        Optional<FreeStock> freeStock = freeStockRepository.findById(id);
        if (freeStock.isPresent()) {
            logger.info("FreeStock found with ID: {}", id);
        } else {
            logger.warn("No FreeStock found with ID: {}", id);
        }
        return freeStock;
    }

    // Get all FreeStocks
    public List<FreeStock> getAllFreeStocks() {
        logger.info("Fetching all FreeStocks");
        List<FreeStock> freeStocks = freeStockRepository.findAll();
        logger.info("Found {} FreeStocks", freeStocks.size());
        return freeStocks;
    }

    // Delete FreeStock by ID
    public void deleteFreeStock(Integer id) {
        logger.info("Attempting to delete FreeStock with ID: {}", id);
        if (freeStockRepository.existsById(id)) {
            freeStockRepository.deleteById(id);
            logger.info("FreeStock with ID: {} deleted successfully", id);
        } else {
            logger.warn("No FreeStock found with ID: {} for deletion", id);
        }
    }

    // Update FreeStock
    public FreeStock updateFreeStock(Integer id, FreeStock freeStock) {
        logger.info("Attempting to update FreeStock with ID: {}", id);

        // Check if FreeStock exists
        Optional<FreeStock> existingFreeStock = freeStockRepository.findById(id);
        if (existingFreeStock.isPresent()) {
            FreeStock updatedFreeStock = existingFreeStock.get();
            logger.info("FreeStock found for update with ID: {}", id);

            // Update the values
            updatedFreeStock.setCreatedBy(freeStock.getCreatedBy());
            updatedFreeStock.setLastModifiedBy(freeStock.getLastModifiedBy());
            updatedFreeStock.setCreatedDate(freeStock.getCreatedDate());
            updatedFreeStock.setLastModifiedDate(freeStock.getLastModifiedDate());
            updatedFreeStock.setSoNo(freeStock.getSoNo());
            updatedFreeStock.setMaterialNo(freeStock.getMaterialNo());
            updatedFreeStock.setPoPosNo(freeStock.getPoPosNo());
            updatedFreeStock.setQuality(freeStock.getQuality());
            updatedFreeStock.setShape(freeStock.getShape());
            updatedFreeStock.setQtyPcs(freeStock.getQtyPcs());
            updatedFreeStock.setSource(freeStock.getSource());
            updatedFreeStock.setSourceId(freeStock.getSourceId());
            updatedFreeStock.setStatus(freeStock.getStatus());
            updatedFreeStock.setBalance(freeStock.getBalance());

            // Save and return the updated FreeStock
            FreeStock savedFreeStock = freeStockRepository.save(updatedFreeStock);
            logger.info("FreeStock updated with ID: {}", savedFreeStock.getId());
            return savedFreeStock;
        } else {
            // If the FreeStock doesn't exist, return null or throw an exception
            logger.warn("No FreeStock found with ID: {} for update", id);
            return null;
        }
    }
    
    public List<FreeStock> getFreeStocks(String orderNo) {
        if (orderNo != null && !orderNo.isEmpty()) {
            return freeStockRepository.findBySoNo(orderNo);
        }
        return freeStockRepository.findAll();
    }
}
