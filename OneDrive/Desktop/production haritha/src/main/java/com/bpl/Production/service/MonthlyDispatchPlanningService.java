package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.MonthlyDispatchPlanning;
import com.bpl.Production.repository.MonthlyDispatchPlanningRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MonthlyDispatchPlanningService {

    @Autowired
    private MonthlyDispatchPlanningRepository monthlyDispatchPlanningRepository;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(MonthlyDispatchPlanningService.class);

    // Create a new MonthlyDispatchPlanning entry
    public MonthlyDispatchPlanning create(MonthlyDispatchPlanning monthlyDispatchPlanning) {
        logger.info("Creating a new Monthly Dispatch Planning entry: {}", monthlyDispatchPlanning);
        return monthlyDispatchPlanningRepository.save(monthlyDispatchPlanning);
    }

    // Get a MonthlyDispatchPlanning by ID
    public Optional<MonthlyDispatchPlanning> getById(Integer id) {
        logger.info("Fetching Monthly Dispatch Planning entry with ID: {}", id);
        return monthlyDispatchPlanningRepository.findById(id);
    }

    // Get all MonthlyDispatchPlanning entries
    public List<MonthlyDispatchPlanning> getAll() {
        logger.info("Fetching all Monthly Dispatch Planning entries");
        return monthlyDispatchPlanningRepository.findAll();
    }

    // Update an existing MonthlyDispatchPlanning entry
    public MonthlyDispatchPlanning update(Integer id, MonthlyDispatchPlanning updatedData) {
        logger.info("Attempting to update Monthly Dispatch Planning entry with ID: {}", id);
        if (monthlyDispatchPlanningRepository.existsById(id)) {
            // Get the existing MonthlyDispatchPlanning entry
            MonthlyDispatchPlanning existingEntry = monthlyDispatchPlanningRepository.findById(id).get();

            // Update the fields from the incoming updatedData
            existingEntry.setCreatedBy(updatedData.getCreatedBy());
            existingEntry.setLastModifiedBy(updatedData.getLastModifiedBy());
            existingEntry.setCreatedDate(updatedData.getCreatedDate());
            existingEntry.setLastModifiedDate(updatedData.getLastModifiedDate());
            existingEntry.setSoNo(updatedData.getSoNo());
            existingEntry.setMaterialNo(updatedData.getMaterialNo());
            existingEntry.setPoPosNo(updatedData.getPoPosNo());
            existingEntry.setHpg(updatedData.getHpg());
            existingEntry.setPg(updatedData.getPg());
            existingEntry.setQuality(updatedData.getQuality());
            existingEntry.setShape(updatedData.getShape());
            existingEntry.setPlannedPcs(updatedData.getPlannedPcs());
            existingEntry.setPlannedQuantity(updatedData.getPlannedQuantity());
            existingEntry.setExwDate(updatedData.getExwDate());
            existingEntry.setPlannedYear(updatedData.getPlannedYear());
            existingEntry.setPlannedMonth(updatedData.getPlannedMonth());
            existingEntry.setWorkingStatus(updatedData.getWorkingStatus());
            existingEntry.setConsigneeName(updatedData.getConsigneeName());

            // Log that the update is being saved
            logger.info("Updating Monthly Dispatch Planning entry with ID: {}", id);
            return monthlyDispatchPlanningRepository.save(existingEntry);
        } else {
            logger.warn("Monthly Dispatch Planning entry with ID {} not found for update", id);
            throw new RuntimeException("Monthly Dispatch Planning entry with ID " + id + " not found");
        }
    }

    // Delete a MonthlyDispatchPlanning entry by ID
    public boolean delete(Integer id) {
        if (monthlyDispatchPlanningRepository.existsById(id)) {
            logger.info("Deleting Monthly Dispatch Planning entry with ID: {}", id);
            monthlyDispatchPlanningRepository.deleteById(id);
            return true;
        } else {
            logger.warn("Monthly Dispatch Planning entry with ID {} not found for deletion", id);
            return false;
        }
    }
}
