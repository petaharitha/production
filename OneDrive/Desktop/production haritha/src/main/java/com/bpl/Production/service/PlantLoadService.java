package com.bpl.Production.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.PlantLoad;
import com.bpl.Production.repository.PlantLoadRepository;

@Service
public class PlantLoadService {

    private static final Logger logger = LoggerFactory.getLogger(PlantLoadService.class); // Logger initialization

    @Autowired
    private PlantLoadRepository plantLoadRepository;
    
    // Create or save a new PlantLoad
    public PlantLoad createPlantLoad(PlantLoad plantLoad) {
        logger.info("Creating new PlantLoad: {}", plantLoad);
        PlantLoad createdPlantLoad = plantLoadRepository.save(plantLoad);
        logger.info("Created PlantLoad with ID: {}", createdPlantLoad.getId());
        return createdPlantLoad;
    }

    // Get all PlantLoads
    public List<PlantLoad> getAllPlantLoads() {
        logger.info("Fetching all PlantLoads");
        List<PlantLoad> plantLoads = plantLoadRepository.findAll();
        logger.info("Found {} PlantLoads", plantLoads.size());
        return plantLoads;
    }

    // Get PlantLoad by ID
    public Optional<PlantLoad> getPlantLoadById(Integer id) {
        logger.info("Fetching PlantLoad with ID: {}", id);
        Optional<PlantLoad> plantLoad = plantLoadRepository.findById(id);
        if (plantLoad.isPresent()) {
            logger.info("Found PlantLoad: {}", plantLoad.get());
        } else {
            logger.warn("PlantLoad with ID {} not found", id);
        }
        return plantLoad;
    }

    // Update PlantLoad by ID
    public PlantLoad updatePlantLoad(Integer id, PlantLoad plantLoadDetails) {
        logger.info("Updating PlantLoad with ID: {}", id);
        Optional<PlantLoad> plantLoadOptional = plantLoadRepository.findById(id);
        if (plantLoadOptional.isPresent()) {
            PlantLoad existingPlantLoad = plantLoadOptional.get();
            existingPlantLoad.setCreatedBy(plantLoadDetails.getCreatedBy());
            existingPlantLoad.setLastModifiedBy(plantLoadDetails.getLastModifiedBy());
            existingPlantLoad.setCreatedDate(plantLoadDetails.getCreatedDate());
            existingPlantLoad.setLastModifiedDate(plantLoadDetails.getLastModifiedDate());
            existingPlantLoad.setType(plantLoadDetails.getType());
            existingPlantLoad.setNumber(plantLoadDetails.getNumber());
            existingPlantLoad.setMaterialno(plantLoadDetails.getMaterialno());
            existingPlantLoad.setItemid(plantLoadDetails.getItemid());
            existingPlantLoad.setHpg(plantLoadDetails.getHpg());
            existingPlantLoad.setPg(plantLoadDetails.getPg());
            existingPlantLoad.setQuality(plantLoadDetails.getQuality());
            existingPlantLoad.setShape(plantLoadDetails.getShape());
            existingPlantLoad.setQuantityinmt(plantLoadDetails.getQuantityinmt());
            existingPlantLoad.setQuantity(plantLoadDetails.getQuantity());
            existingPlantLoad.setYear(plantLoadDetails.getYear());
            existingPlantLoad.setMonth(plantLoadDetails.getMonth());
            existingPlantLoad.setRemarks(plantLoadDetails.getRemarks());
            existingPlantLoad.setPoPosNo(plantLoadDetails.getPoPosNo());
            existingPlantLoad.setTotalPcs(plantLoadDetails.getTotalPcs());
            existingPlantLoad.setPlannedPcs(plantLoadDetails.getPlannedPcs());
            existingPlantLoad.setOriginalPlantLoadId(plantLoadDetails.getOriginalPlantLoadId());
            existingPlantLoad.setOriginalPlannedPcs(plantLoadDetails.getOriginalPlannedPcs());
            existingPlantLoad.setRecipeNo(plantLoadDetails.getRecipeNo());
            existingPlantLoad.setAltNo(plantLoadDetails.getAltNo());

            PlantLoad updatedPlantLoad = plantLoadRepository.save(existingPlantLoad);
            logger.info("Updated PlantLoad with ID: {}", updatedPlantLoad.getId());
            return updatedPlantLoad;
        } else {
            logger.warn("PlantLoad with ID {} not found, unable to update", id);
            return null; // or throw an exception
        }
    }

    // Delete PlantLoad by ID
    public void deletePlantLoad(Integer id) {
        logger.info("Deleting PlantLoad with ID: {}", id);
        plantLoadRepository.deleteById(id);
        logger.info("Deleted PlantLoad with ID: {}", id);
    }
    

    public List<PlantLoad> getPlantLoadsByConsigneeNumber(String consigneeNumber) {
        return plantLoadRepository.findByConsigneeNumber(consigneeNumber);
    }

    public List<PlantLoad> getPlantLoadsByYearAndMonth(int year, int month) {
        return plantLoadRepository.findByYearAndMonth(year, month);
    }
    
    public List<PlantLoad> getPlantLoadsByHpgs(List<String> hpgs) {
        return plantLoadRepository.findByHpgIn(hpgs);
    }
    
    public List<PlantLoad> getPlantLoads(List<String> hpgs, List<String> types, List<Integer> years, List<Integer> months) {
        return plantLoadRepository.findByHpgInAndTypeInAndYearInAndMonthIn(hpgs, types, years, months);
    }
    }


