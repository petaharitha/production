package com.bpl.Production.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.DispatchPlanning;
import com.bpl.Production.repository.DispatchPlanningRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DispatchPlanningService {

    @Autowired
    private DispatchPlanningRepo dispatchPlanningRepo;
    
    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(DispatchPlanningService.class);

    // Save or update DispatchPlanning
    public DispatchPlanning saveOrUpdateDispatchPlanning(DispatchPlanning dispatchPlanning) {
        logger.info("Attempting to save or update DispatchPlanning: {}", dispatchPlanning);
        return dispatchPlanningRepo.save(dispatchPlanning);
    }

    // Get DispatchPlanning by ID
    public Optional<DispatchPlanning> getDispatchPlanningById(Integer id) {
        logger.info("Fetching DispatchPlanning with ID: {}", id);
        Optional<DispatchPlanning> dispatchPlanning = dispatchPlanningRepo.findById(id);
        if (dispatchPlanning.isPresent()) {
            logger.info("Found DispatchPlanning with ID: {}", id);
        } else {
            logger.warn("DispatchPlanning with ID {} not found", id);
        }
        return dispatchPlanning;
    }

    // Get all DispatchPlannings
    public Iterable<DispatchPlanning> getAllDispatchPlannings() {
        logger.info("Fetching all DispatchPlannings");
        return dispatchPlanningRepo.findAll();
    }

    // Delete DispatchPlanning by ID
    public boolean deleteDispatchPlanning(Integer id) {
        logger.info("Attempting to delete DispatchPlanning with ID: {}", id);
        Optional<DispatchPlanning> dispatchPlanning = dispatchPlanningRepo.findById(id);
        if (dispatchPlanning.isPresent()) {
            dispatchPlanningRepo.delete(dispatchPlanning.get());
            logger.info("Successfully deleted DispatchPlanning with ID: {}", id);
            return true;
        } else {
            logger.warn("DispatchPlanning with ID {} not found for deletion", id);
            return false;
        }
    }

    // Update DispatchPlanning
    public DispatchPlanning updateDispatchPlanning(Integer id, DispatchPlanning updatedDispatchPlanning) {
        logger.info("Attempting to update DispatchPlanning with ID: {}", id);
        Optional<DispatchPlanning> existingDispatchPlanning = dispatchPlanningRepo.findById(id);
        if (existingDispatchPlanning.isPresent()) {
            DispatchPlanning dispatchPlanning = existingDispatchPlanning.get();
            logger.info("Updating DispatchPlanning with ID: {}", id);

            // Update fields with the provided data
            dispatchPlanning.setCreatedBy(updatedDispatchPlanning.getCreatedBy());
            dispatchPlanning.setLastModifiedBy(updatedDispatchPlanning.getLastModifiedBy());
            dispatchPlanning.setCreatedDate(updatedDispatchPlanning.getCreatedDate());
            dispatchPlanning.setLastModifiedDate(updatedDispatchPlanning.getLastModifiedDate());
            dispatchPlanning.setNumber(updatedDispatchPlanning.getNumber());
            dispatchPlanning.setMaterialNo(updatedDispatchPlanning.getMaterialNo());
            dispatchPlanning.setItemId(updatedDispatchPlanning.getItemId());
            dispatchPlanning.setHpg(updatedDispatchPlanning.getHpg());
            dispatchPlanning.setPg(updatedDispatchPlanning.getPg());
            dispatchPlanning.setQuality(updatedDispatchPlanning.getQuality());
            dispatchPlanning.setShape(updatedDispatchPlanning.getShape());
            dispatchPlanning.setQuantityInMt(updatedDispatchPlanning.getQuantityInMt());
            dispatchPlanning.setQuantity(updatedDispatchPlanning.getQuantity());
            dispatchPlanning.setDate(updatedDispatchPlanning.getDate());
            dispatchPlanning.setRemarks(updatedDispatchPlanning.getRemarks());
            dispatchPlanning.setQuantityDispatched(updatedDispatchPlanning.getQuantityDispatched());
            dispatchPlanning.setPoPosNo(updatedDispatchPlanning.getPoPosNo());
            dispatchPlanning.setExwWeek(updatedDispatchPlanning.getExwWeek());
            dispatchPlanning.setExwMonth(updatedDispatchPlanning.getExwMonth());
            dispatchPlanning.setExwYear(updatedDispatchPlanning.getExwYear());
            dispatchPlanning.setTotalPcs(updatedDispatchPlanning.getTotalPcs());
            dispatchPlanning.setPlannedPcs(updatedDispatchPlanning.getPlannedPcs());
            dispatchPlanning.setTotal(updatedDispatchPlanning.getTotal());
            dispatchPlanning.setOriginalDispatchPlanningId(updatedDispatchPlanning.getOriginalDispatchPlanningId());
            dispatchPlanning.setOriginalPlannedPcs(updatedDispatchPlanning.getOriginalPlannedPcs());

            // Save the updated DispatchPlanning
            logger.info("DispatchPlanning with ID: {} successfully updated", id);
            return dispatchPlanningRepo.save(dispatchPlanning);
        } else {
            logger.warn("DispatchPlanning with ID {} not found, update failed", id);
            return null; // Return null if not found
        }
    }
}
