package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.FgStock;
import com.bpl.Production.repository.FgStockRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FgStockService {

    @Autowired
    private FgStockRepo fgStockRepo;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(FgStockService.class);

    // Create FgStock
    public FgStock createFgStock(FgStock fgStock) {
        logger.info("Attempting to create FgStock: {}", fgStock);
        FgStock savedFgStock = fgStockRepo.save(fgStock);
        logger.info("FgStock created successfully: {}", savedFgStock);
        return savedFgStock;
    }

    // Get FgStock by ID
    public Optional<FgStock> getFgStockById(Integer id) {
        logger.info("Fetching FgStock with ID: {}", id);
        Optional<FgStock> fgStock = fgStockRepo.findById(id);
        if (fgStock.isPresent()) {
            logger.info("Found FgStock with ID: {}", id);
        } else {
            logger.warn("FgStock with ID {} not found", id);
        }
        return fgStock;
    }

    // Get All FgStocks
    public List<FgStock> getAllFgStocks() {
        logger.info("Fetching all FgStocks");
        return fgStockRepo.findAll();
    }

    // Delete FgStock by ID
    public void deleteFgStock(Integer id) {
        logger.info("Attempting to delete FgStock with ID: {}", id);
        fgStockRepo.deleteById(id);
        logger.info("FgStock with ID: {} successfully deleted", id);
    }

    // Update FgStock by ID
    public FgStock updateFgStock(Integer id, FgStock updatedFgStock) {
        logger.info("Attempting to update FgStock with ID: {}", id);
        Optional<FgStock> existingFgStockOptional = fgStockRepo.findById(id);

        if (existingFgStockOptional.isPresent()) {
            FgStock existingFgStock = existingFgStockOptional.get();
            logger.info("Updating FgStock with ID: {}", id);

            // Update fields
            existingFgStock.setCreatedBy(updatedFgStock.getCreatedBy());
            existingFgStock.setLastModifiedBy(updatedFgStock.getLastModifiedBy());
            existingFgStock.setCreatedDate(updatedFgStock.getCreatedDate());
            existingFgStock.setLastModifiedDate(updatedFgStock.getLastModifiedDate());
            existingFgStock.setSoNo(updatedFgStock.getSoNo());
            existingFgStock.setMaterialNo(updatedFgStock.getMaterialNo());
            existingFgStock.setPoPosNo(updatedFgStock.getPoPosNo());
            existingFgStock.setQuality(updatedFgStock.getQuality());
            existingFgStock.setShape(updatedFgStock.getShape());
            existingFgStock.setQtyPcs(updatedFgStock.getQtyPcs());
            existingFgStock.setPalletNo(updatedFgStock.getPalletNo());
            existingFgStock.setReferencePackingPalletId(updatedFgStock.getReferencePackingPalletId());
            existingFgStock.setIsMovedToFreeStock(updatedFgStock.getIsMovedToFreeStock());

            // Save the updated FgStock
            FgStock updatedFgStockSaved = fgStockRepo.save(existingFgStock);
            logger.info("FgStock with ID: {} successfully updated", id);
            return updatedFgStockSaved;
        } else {
            logger.warn("FgStock with ID {} not found for update", id);
            return null; // Return null or handle it differently (e.g., throw exception)
        }
    }
}
