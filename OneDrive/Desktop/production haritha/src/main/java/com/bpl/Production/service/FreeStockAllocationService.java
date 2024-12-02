package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.FreeStockAllocation;
import com.bpl.Production.repository.FreeStockAllocationRepository;

@Service
public class FreeStockAllocationService {

    private static final Logger logger = LoggerFactory.getLogger(FreeStockAllocationService.class); // Initialize logger

    @Autowired
    private FreeStockAllocationRepository freeStockAllocationRepository;

    // Create operation
    public FreeStockAllocation createFreeStockAllocation(FreeStockAllocation freeStockAllocation) {
        logger.info("Creating FreeStockAllocation: {}", freeStockAllocation);
        FreeStockAllocation savedFreeStockAllocation = freeStockAllocationRepository.save(freeStockAllocation);
        logger.info("FreeStockAllocation created with ID: {}", savedFreeStockAllocation.getId());
        return savedFreeStockAllocation;
    }

    // GetById operation
    public Optional<FreeStockAllocation> getFreeStockAllocationById(Integer id) {
        logger.info("Fetching FreeStockAllocation with ID: {}", id);
        Optional<FreeStockAllocation> freeStockAllocation = freeStockAllocationRepository.findById(id);
        if (freeStockAllocation.isPresent()) {
            logger.info("FreeStockAllocation found with ID: {}", id);
        } else {
            logger.warn("No FreeStockAllocation found with ID: {}", id);
        }
        return freeStockAllocation;
    }

    // GetAll operation
    public List<FreeStockAllocation> getAllFreeStockAllocations() {
        logger.info("Fetching all FreeStockAllocations");
        List<FreeStockAllocation> freeStockAllocations = freeStockAllocationRepository.findAll();
        logger.info("Found {} FreeStockAllocations", freeStockAllocations.size());
        return freeStockAllocations;
    }

    // Delete operation
    public boolean deleteFreeStockAllocation(Integer id) {
        logger.info("Attempting to delete FreeStockAllocation with ID: {}", id);
        Optional<FreeStockAllocation> freeStockAllocation = freeStockAllocationRepository.findById(id);
        if (freeStockAllocation.isPresent()) {
            freeStockAllocationRepository.deleteById(id);
            logger.info("FreeStockAllocation with ID: {} deleted", id);
            return true;
        } else {
            logger.warn("No FreeStockAllocation found with ID: {} for deletion", id);
            return false;
        }
    }

    // Update operation
    public FreeStockAllocation updateFreeStockAllocation(Integer id, FreeStockAllocation updatedFreeStockAllocation) {
        logger.info("Attempting to update FreeStockAllocation with ID: {}", id);
        
        Optional<FreeStockAllocation> existingFreeStockAllocation = freeStockAllocationRepository.findById(id);
        if (existingFreeStockAllocation.isPresent()) {
            FreeStockAllocation freeStockAllocation = existingFreeStockAllocation.get();
            logger.info("FreeStockAllocation found for update with ID: {}", id);

            // Update fields (ensure not null and valid updates)
            freeStockAllocation.setMaterialNo(updatedFreeStockAllocation.getMaterialNo());
            freeStockAllocation.setHpg(updatedFreeStockAllocation.getHpg());
            freeStockAllocation.setPg(updatedFreeStockAllocation.getPg());
            freeStockAllocation.setQuality(updatedFreeStockAllocation.getQuality());
            freeStockAllocation.setShape(updatedFreeStockAllocation.getShape());
            freeStockAllocation.setQtyPcs(updatedFreeStockAllocation.getQtyPcs());
            freeStockAllocation.setRemarks(updatedFreeStockAllocation.getRemarks());
            freeStockAllocation.setPoPosNo(updatedFreeStockAllocation.getPoPosNo());
            freeStockAllocation.setSoNo(updatedFreeStockAllocation.getSoNo());
            freeStockAllocation.setFreeStockPieces(updatedFreeStockAllocation.getFreeStockPieces());
            freeStockAllocation.setFreeStockSoNo(updatedFreeStockAllocation.getFreeStockSoNo());
            freeStockAllocation.setFreeStockMaterialNo(updatedFreeStockAllocation.getFreeStockMaterialNo());
            freeStockAllocation.setFreeStockPoPosNo(updatedFreeStockAllocation.getFreeStockPoPosNo());
            freeStockAllocation.setFreeStockQuality(updatedFreeStockAllocation.getFreeStockQuality());
            freeStockAllocation.setFreeStockShape(updatedFreeStockAllocation.getFreeStockShape());
            freeStockAllocation.setFreeStockId(updatedFreeStockAllocation.getFreeStockId());
            freeStockAllocation.setStatus(updatedFreeStockAllocation.getStatus());
            freeStockAllocation.setFreeStockPiecesRejected(updatedFreeStockAllocation.getFreeStockPiecesRejected());

            FreeStockAllocation savedFreeStockAllocation = freeStockAllocationRepository.save(freeStockAllocation);
            logger.info("FreeStockAllocation updated with ID: {}", savedFreeStockAllocation.getId());
            return savedFreeStockAllocation;
        } else {
            logger.warn("No FreeStockAllocation found with ID: {} for update", id);
            return null;
        }
    }
    
    public List<FreeStockAllocation> getAllocationsByOrderNo(String orderNo) {
        return freeStockAllocationRepository.findBySoNo(orderNo);
    }
}
