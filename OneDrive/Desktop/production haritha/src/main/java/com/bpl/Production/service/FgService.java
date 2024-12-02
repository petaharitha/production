package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Fg;
import com.bpl.Production.repository.FgRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FgService {

    @Autowired
    private FgRepo fgRepo;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(FgService.class);

    // Create or Update Fg
    public Fg createOrUpdateFg(Fg fg) {
        logger.info("Attempting to create or update Fg: {}", fg);
        Fg savedFg = fgRepo.save(fg);
        logger.info("Fg created/updated successfully: {}", savedFg);
        return savedFg;
    }

    // Get Fg by ID
    public Optional<Fg> getFgById(Integer id) {
        logger.info("Fetching Fg with ID: {}", id);
        Optional<Fg> fg = fgRepo.findById(id);
        if (fg.isPresent()) {
            logger.info("Found Fg with ID: {}", id);
        } else {
            logger.warn("Fg with ID {} not found", id);
        }
        return fg;
    }

    // Get all Fgs
    public List<Fg> getAllFgs() {
        logger.info("Fetching all Fgs");
        return fgRepo.findAll();
    }

    // Delete Fg by ID
    public void deleteFg(Integer id) {
        logger.info("Attempting to delete Fg with ID: {}", id);
        fgRepo.deleteById(id);
        logger.info("Fg with ID: {} successfully deleted", id);
    }

    // Update Fg
    public Fg updateFg(Integer id, Fg updatedFg) {
        logger.info("Attempting to update Fg with ID: {}", id);
        Optional<Fg> existingFgOptional = fgRepo.findById(id);

        if (existingFgOptional.isPresent()) {
            Fg existingFg = existingFgOptional.get();
            logger.info("Updating Fg with ID: {}", id);

            // Update fields
            existingFg.setCreatedBy(updatedFg.getCreatedBy());
            existingFg.setLastModifiedBy(updatedFg.getLastModifiedBy());
            existingFg.setCreatedDate(updatedFg.getCreatedDate());
            existingFg.setLastModifiedDate(updatedFg.getLastModifiedDate());
            existingFg.setMaterialNo(updatedFg.getMaterialNo());
            existingFg.setHpg(updatedFg.getHpg());
            existingFg.setPg(updatedFg.getPg());
            existingFg.setRecipeNumber(updatedFg.getRecipeNumber());
            existingFg.setQuality(updatedFg.getQuality());
            existingFg.setShape(updatedFg.getShape());
            existingFg.setUnitWeightGr(updatedFg.getUnitWeightGr());
            existingFg.setUnitWeightFr(updatedFg.getUnitWeightFr());
            existingFg.setPcsPellete(updatedFg.getPcsPellete());
            existingFg.setHsCode(updatedFg.getHsCode());
            existingFg.setHscDescription(updatedFg.getHscDescription());

            // Save the updated Fg
            Fg updatedFgSaved = fgRepo.save(existingFg);
            logger.info("Fg with ID: {} successfully updated", id);
            return updatedFgSaved;
        } else {
            logger.warn("Fg with ID {} not found for update", id);
            return null; // Return null or handle it differently (throw exception)
        }
    }
}
