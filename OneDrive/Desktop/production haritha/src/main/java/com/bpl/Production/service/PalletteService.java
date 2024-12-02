package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Pallette;
import com.bpl.Production.repository.PalletteRepository;

@Service
public class PalletteService {

    private static final Logger logger = LoggerFactory.getLogger(PalletteService.class); // Initialize logger

    @Autowired
    private PalletteRepository palletteRepository;
    
    // Create a new Pallette
    public Pallette createPallette(Pallette pallette) {
        logger.info("Creating new Pallette: {}", pallette);
        Pallette createdPallette = palletteRepository.save(pallette);
        logger.info("Created Pallette with ID: {}", createdPallette.getId());
        return createdPallette;
    }

    // Get all Pallette entities
    public List<Pallette> getAllPallettes() {
        logger.info("Fetching all Pallettes");
        List<Pallette> pallettes = palletteRepository.findAll();
        logger.info("Found {} Pallettes", pallettes.size());
        return pallettes;
    }

    // Get Pallette by ID
    public Optional<Pallette> getPalletteById(Integer id) {
        logger.info("Fetching Pallette with ID: {}", id);
        Optional<Pallette> pallette = palletteRepository.findById(id);
        if (pallette.isPresent()) {
            logger.info("Found Pallette: {}", pallette.get());
        } else {
            logger.warn("Pallette with ID {} not found", id);
        }
        return pallette;
    }

    // Update an existing Pallette
    public Pallette updatePallette(Integer id, Pallette palletteDetails) {
        logger.info("Updating Pallette with ID: {}", id);
        Optional<Pallette> existingPalletteOptional = palletteRepository.findById(id);
        if (existingPalletteOptional.isPresent()) {
            Pallette existingPallette = existingPalletteOptional.get();
            existingPallette.setCreatedBy(palletteDetails.getCreatedBy());
            existingPallette.setLastModifiedBy(palletteDetails.getLastModifiedBy());
            existingPallette.setCreatedDate(palletteDetails.getCreatedDate());
            existingPallette.setLastModifiedDate(palletteDetails.getLastModifiedDate());
            existingPallette.setNo(palletteDetails.getNo());
            Pallette updatedPallette = palletteRepository.save(existingPallette);
            logger.info("Updated Pallette with ID: {}", updatedPallette.getId());
            return updatedPallette;
        } else {
            logger.warn("Pallette with ID {} not found, unable to update", id);
            return null; // return null if Pallette not found
        }
    }

    // Delete Pallette by ID
    public void deletePallette(Integer id) {
        logger.info("Deleting Pallette with ID: {}", id);
        palletteRepository.deleteById(id);
        logger.info("Deleted Pallette with ID: {}", id);
    }
}
