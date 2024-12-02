package com.bpl.Production.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.ContainerList;
import com.bpl.Production.repository.ContainerListRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContainerListService {

    @Autowired
    private ContainerListRepo containerListRepo;
    
    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(ContainerListService.class);

    // Create or update a container list entry
    public ContainerList saveContainerList(ContainerList containerList) {
        logger.info("Attempting to save or update a container list: {}", containerList);
        return containerListRepo.save(containerList);
    }

    // Find container list by ID
    public Optional<ContainerList> getContainerListById(Integer id) {
        logger.info("Fetching container list by ID: {}", id);
        Optional<ContainerList> containerList = containerListRepo.findById(id);
        if (containerList.isPresent()) {
            logger.info("Container list found: {}", containerList.get());
        } else {
            logger.warn("Container list not found for ID: {}", id);
        }
        return containerList;
    }

    // Update an existing container list
    public ContainerList updateContainerList(Integer id, ContainerList containerListDetails) {
        logger.info("Attempting to update container list with ID: {}", id);
        Optional<ContainerList> existingContainerList = containerListRepo.findById(id);
        if (existingContainerList.isPresent()) {
            ContainerList containerList = existingContainerList.get();
            logger.info("Updating container list with ID: {}", id);
            containerList.setCreatedBy(containerListDetails.getCreatedBy());
            containerList.setLastModifiedBy(containerListDetails.getLastModifiedBy());
            containerList.setCreatedDate(containerListDetails.getCreatedDate());
            containerList.setLastModifiedDate(containerListDetails.getLastModifiedDate());
            containerList.setNumber(containerListDetails.getNumber());
            containerList.setAltNumber(containerListDetails.getAltNumber());
            containerList.setExciseInvoice(containerListDetails.getExciseInvoice());
            containerList.setVoiceDate(containerListDetails.getVoiceDate());
            containerList.setCommercialInvoice(containerListDetails.getCommercialInvoice());
            return containerListRepo.save(containerList);
        } else {
            logger.warn("Container list with ID {} not found, update failed", id);
            return null;  // Handle case where entity is not found
        }
    }

    // Delete container list by ID
    public boolean deleteContainerList(Integer id) {
        logger.info("Attempting to delete container list with ID: {}", id);
        Optional<ContainerList> containerList = containerListRepo.findById(id);
        if (containerList.isPresent()) {
            containerListRepo.delete(containerList.get());
            logger.info("Successfully deleted container list with ID: {}", id);
            return true;
        } else {
            logger.warn("Container list with ID {} not found, delete failed", id);
            return false;
        }
    }

    // Get all container lists
    public Iterable<ContainerList> getAllContainerLists() {
        logger.info("Fetching all container lists");
        return containerListRepo.findAll();
    }
}
