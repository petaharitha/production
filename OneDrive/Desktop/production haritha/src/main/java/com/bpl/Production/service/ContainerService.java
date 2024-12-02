package com.bpl.Production.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Container;
import com.bpl.Production.repository.ContainerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContainerService {

    @Autowired
    private ContainerRepo containerRepo;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(ContainerService.class);

    // Create or update container
    public Container saveContainer(Container container) {
        logger.info("Attempting to save or update container: {}", container);
        return containerRepo.save(container);
    }

    // Find a container by ID
    public Optional<Container> getContainerById(Integer id) {
        logger.info("Fetching container by ID: {}", id);
        Optional<Container> container = containerRepo.findById(id);
        if (container.isPresent()) {
            logger.info("Container found: {}", container.get());
        } else {
            logger.warn("Container with ID {} not found", id);
        }
        return container;
    }

    // Update an existing container
    public Container updateContainer(Integer id, Container containerDetails) {
        logger.info("Attempting to update container with ID: {}", id);
        Optional<Container> existingContainer = containerRepo.findById(id);
        if (existingContainer.isPresent()) {
            Container container = existingContainer.get();
            logger.info("Updating container with ID: {}", id);
            container.setCreatedBy(containerDetails.getCreatedBy());
            container.setLastModifiedBy(containerDetails.getLastModifiedBy());
            container.setCreatedDate(containerDetails.getCreatedDate());
            container.setLastModifiedDate(containerDetails.getLastModifiedDate());
            container.setSoNo(containerDetails.getSoNo());
            container.setAreNumber(containerDetails.getAreNumber());
            container.setNumber(containerDetails.getNumber());
            container.setWeight(containerDetails.getWeight());
            container.setExciseInvoice(containerDetails.getExciseInvoice());
            container.setExciseInvoiceDate(containerDetails.getExciseInvoiceDate());
            container.setCommercialInvoice(containerDetails.getCommercialInvoice());
            container.setLicenseNumber(containerDetails.getLicenseNumber());
            container.setCommercialInvoiceDate(containerDetails.getCommercialInvoiceDate());
            container.setOrderInfoId(containerDetails.getOrderInfoId());
            return containerRepo.save(container);
        } else {
            logger.warn("Container with ID {} not found, update failed", id);
            return null; // Handle case where entity is not found
        }
    }

    // Delete a container
    public boolean deleteContainer(Integer id) {
        logger.info("Attempting to delete container with ID: {}", id);
        Optional<Container> container = containerRepo.findById(id);
        if (container.isPresent()) {
            containerRepo.delete(container.get());
            logger.info("Successfully deleted container with ID: {}", id);
            return true;
        } else {
            logger.warn("Container with ID {} not found, delete failed", id);
            return false;
        }
    }

    // Get all containers
    public Iterable<Container> getAllContainers() {
        logger.info("Fetching all containers");
        return containerRepo.findAll();
    }
}
