package com.bpl.Production.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bpl.Production.entity.MonthlyPlanning;
import com.bpl.Production.repository.MonthlyPlanningRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MonthlyPlanningService {

    @Autowired
    private MonthlyPlanningRepository monthlyPlanningRepository;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(MonthlyPlanningService.class);

    // Create or Update MonthlyPlanning
    public MonthlyPlanning createOrUpdateMonthlyPlanning(MonthlyPlanning monthlyPlanning) {
        logger.info("Creating or Updating Monthly Planning entry: {}", monthlyPlanning);
        return monthlyPlanningRepository.save(monthlyPlanning);
    }

    // Get all MonthlyPlannings
    public List<MonthlyPlanning> getAllMonthlyPlannings() {
        logger.info("Fetching all Monthly Planning entries");
        return monthlyPlanningRepository.findAll();
    }

    // Get MonthlyPlanning by ID
    public Optional<MonthlyPlanning> getMonthlyPlanningById(Integer id) {
        logger.info("Fetching Monthly Planning entry with ID: {}", id);
        return monthlyPlanningRepository.findById(id);
    }

    // Update an existing MonthlyPlanning entry
    public MonthlyPlanning updateMonthlyPlanning(Integer id, MonthlyPlanning updatedMonthlyPlanning) {
        logger.info("Attempting to update Monthly Planning entry with ID: {}", id);
        if (monthlyPlanningRepository.existsById(id)) {
            // Get the existing MonthlyPlanning entry
            MonthlyPlanning existingMonthlyPlanning = monthlyPlanningRepository.findById(id).get();

            // Update fields from updatedMonthlyPlanning
            existingMonthlyPlanning.setCreatedBy(updatedMonthlyPlanning.getCreatedBy());
            existingMonthlyPlanning.setLastModifiedBy(updatedMonthlyPlanning.getLastModifiedBy());
            existingMonthlyPlanning.setCreatedDate(updatedMonthlyPlanning.getCreatedDate());
            existingMonthlyPlanning.setLastModifiedDate(updatedMonthlyPlanning.getLastModifiedDate());
            existingMonthlyPlanning.setSoNo(updatedMonthlyPlanning.getSoNo());
            existingMonthlyPlanning.setMaterialNo(updatedMonthlyPlanning.getMaterialNo());
            existingMonthlyPlanning.setPoPosNo(updatedMonthlyPlanning.getPoPosNo());
            existingMonthlyPlanning.setHpg(updatedMonthlyPlanning.getHpg());
            existingMonthlyPlanning.setPg(updatedMonthlyPlanning.getPg());
            existingMonthlyPlanning.setQuality(updatedMonthlyPlanning.getQuality());
            existingMonthlyPlanning.setShape(updatedMonthlyPlanning.getShape());
            existingMonthlyPlanning.setPlannedPcs(updatedMonthlyPlanning.getPlannedPcs());
            existingMonthlyPlanning.setPlannedQuantity(updatedMonthlyPlanning.getPlannedQuantity());
            existingMonthlyPlanning.setMrwDate(updatedMonthlyPlanning.getMrwDate());
            existingMonthlyPlanning.setPlannedYear(updatedMonthlyPlanning.getPlannedYear());
            existingMonthlyPlanning.setPlannedMonth(updatedMonthlyPlanning.getPlannedMonth());
            existingMonthlyPlanning.setEquipmentId(updatedMonthlyPlanning.getEquipmentId());
            existingMonthlyPlanning.setEquipment(updatedMonthlyPlanning.getEquipment());
            existingMonthlyPlanning.setSequence(updatedMonthlyPlanning.getSequence());
            existingMonthlyPlanning.setWorkingStatus(updatedMonthlyPlanning.getWorkingStatus());
            existingMonthlyPlanning.setConsigneeName(updatedMonthlyPlanning.getConsigneeName());

            // Log that the update is being saved
            logger.info("Updating Monthly Planning entry with ID: {}", id);
            return monthlyPlanningRepository.save(existingMonthlyPlanning);
        } else {
            logger.warn("Monthly Planning entry with ID {} not found for update", id);
            throw new RuntimeException("Monthly Planning entry with ID " + id + " not found");
        }
    }

    // Delete MonthlyPlanning by ID
    public void deleteMonthlyPlanning(Integer id) {
        logger.info("Attempting to delete Monthly Planning entry with ID: {}", id);
        if (monthlyPlanningRepository.existsById(id)) {
            monthlyPlanningRepository.deleteById(id);
            logger.info("Successfully deleted Monthly Planning entry with ID: {}", id);
        } else {
            logger.warn("Monthly Planning entry with ID {} not found for deletion", id);
        }
    }
}
