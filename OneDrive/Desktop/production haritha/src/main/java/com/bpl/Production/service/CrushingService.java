package com.bpl.Production.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Crushing;
import com.bpl.Production.repository.CrushingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CrushingService {

    @Autowired
    private CrushingRepo crushingRepo;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(CrushingService.class);

    // Create or update a crushing entry
    public Crushing saveCrushing(Crushing crushing) {
        logger.info("Attempting to save or update crushing entry: {}", crushing);
        return crushingRepo.save(crushing);
    }

    // Get crushing by ID
    public Optional<Crushing> getCrushingById(Integer id) {
        logger.info("Fetching crushing entry by ID: {}", id);
        Optional<Crushing> crushing = crushingRepo.findById(id);
        if (crushing.isPresent()) {
            logger.info("Crushing entry found: {}", crushing.get());
        } else {
            logger.warn("Crushing entry with ID {} not found", id);
        }
        return crushing;
    }

    // Update an existing crushing entry
    public Crushing updateCrushing(Integer id, Crushing crushingDetails) {
        logger.info("Attempting to update crushing entry with ID: {}", id);
        Optional<Crushing> existingCrushing = crushingRepo.findById(id);
        if (existingCrushing.isPresent()) {
            Crushing crushing = existingCrushing.get();
            logger.info("Updating crushing entry with ID: {}", id);
            crushing.setCreatedBy(crushingDetails.getCreatedBy());
            crushing.setLastModifiedBy(crushingDetails.getLastModifiedBy());
            crushing.setCreatedDate(crushingDetails.getCreatedDate());
            crushing.setLastModifiedDate(crushingDetails.getLastModifiedDate());
            crushing.setDate(crushingDetails.getDate());
            crushing.setShift(crushingDetails.getShift());
            crushing.setBatchNo(crushingDetails.getBatchNo());
            crushing.setMaterialNo(crushingDetails.getMaterialNo());
            crushing.setSize(crushingDetails.getSize());
            crushing.setSiloNo(crushingDetails.getSiloNo());
            crushing.setMt(crushingDetails.getMt());
            crushing.setRemarks(crushingDetails.getRemarks());
            crushing.setMaterialName(crushingDetails.getMaterialName());
            return crushingRepo.save(crushing);
        } else {
            logger.warn("Crushing entry with ID {} not found, update failed", id);
            return null; // Handle case where entity is not found
        }
    }

    // Delete crushing entry by ID
    public boolean deleteCrushing(Integer id) {
        logger.info("Attempting to delete crushing entry with ID: {}", id);
        Optional<Crushing> crushing = crushingRepo.findById(id);
        if (crushing.isPresent()) {
            crushingRepo.delete(crushing.get());
            logger.info("Successfully deleted crushing entry with ID: {}", id);
            return true;
        } else {
            logger.warn("Crushing entry with ID {} not found, delete failed", id);
            return false;
        }
    }

    // Get all crushing entries
    public Iterable<Crushing> getAllCrushing() {
        logger.info("Fetching all crushing entries");
        return crushingRepo.findAll();
    }
}
