package com.bpl.Production.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.bpl.Production.entity.PackingList;
import com.bpl.Production.repository.PackingListRepository;

@Service
public class PackingListService {

    // Logger for logging service actions
    private static final Logger logger = LoggerFactory.getLogger(PackingListService.class);

    @Autowired
    private PackingListRepository packingListRepository;
    
    // Create a new PackingList entry
    public PackingList createPackingList(PackingList packingList) {
        logger.info("Creating new PackingList with Order ID: {}", packingList.getOrderId());
        PackingList savedPackingList = packingListRepository.save(packingList);
        logger.info("Created PackingList with ID: {}", savedPackingList.getId());
        return savedPackingList;
    }

    // Get all PackingList entries
    public List<PackingList> getAllPackingLists() {
        logger.info("Fetching all PackingLists");
        List<PackingList> packingList = packingListRepository.findAll();
        logger.info("Retrieved {} PackingLists", packingList.size());
        return packingList;
    }

    // Get PackingList entry by ID
    public Optional<PackingList> getPackingListById(Integer id) {
        logger.info("Fetching PackingList with ID: {}", id);
        Optional<PackingList> packingListOptional = packingListRepository.findById(id);
        if (packingListOptional.isPresent()) {
            logger.info("Found PackingList with ID: {}", id);
        } else {
            logger.warn("PackingList with ID: {} not found", id);
        }
        return packingListOptional;
    }

    // Update an existing PackingList entry
    public PackingList updatePackingList(Integer id, PackingList packingListDetails) {
        logger.info("Updating PackingList with ID: {}", id);
        Optional<PackingList> packingListOptional = packingListRepository.findById(id);
        if (packingListOptional.isPresent()) {
            PackingList existingPackingList = packingListOptional.get();

            // Log existing data before update
            logger.info("Existing PackingList: {}", existingPackingList);

            // Update fields
            existingPackingList.setCreatedBy(packingListDetails.getCreatedBy());
            existingPackingList.setLastModifiedBy(packingListDetails.getLastModifiedBy());
            existingPackingList.setCreatedDate(packingListDetails.getCreatedDate());
            existingPackingList.setLastModifiedDate(packingListDetails.getLastModifiedDate());
            existingPackingList.setOrderId(packingListDetails.getOrderId());
            existingPackingList.setPoNumber(packingListDetails.getPoNumber());
            existingPackingList.setTsNumber(packingListDetails.getTsNumber());
            existingPackingList.setCustomerId(packingListDetails.getCustomerId());
            existingPackingList.setCustomerName(packingListDetails.getCustomerName());
            existingPackingList.setConsigneeId(packingListDetails.getConsigneeId());
            existingPackingList.setConsigneeName(packingListDetails.getConsigneeName());
            existingPackingList.setContainerNumber(packingListDetails.getContainerNumber());
            existingPackingList.setPositionNumber(packingListDetails.getPositionNumber());
            existingPackingList.setMaterialNumber(packingListDetails.getMaterialNumber());
            existingPackingList.setQuality(packingListDetails.getQuality());
            existingPackingList.setShape(packingListDetails.getShape());
            existingPackingList.setPalletNumber(packingListDetails.getPalletNumber());
            existingPackingList.setNewPalletNumber(packingListDetails.getNewPalletNumber());
            existingPackingList.setNumberOfPits(packingListDetails.getNumberOfPits());
            existingPackingList.setPiecesPerPallet(packingListDetails.getPiecesPerPallet());
            existingPackingList.setPalletNetWeight(packingListDetails.getPalletNetWeight());
            existingPackingList.setPalletGrWeight(packingListDetails.getPalletGrWeight());
            existingPackingList.setDeliveryDate(packingListDetails.getDeliveryDate());
            existingPackingList.setStatus(packingListDetails.getStatus());

            // Log after update
            logger.info("Updated PackingList: {}", existingPackingList);

            // Save and return updated PackingList
            return packingListRepository.save(existingPackingList);
        } else {
            logger.warn("PackingList with ID: {} not found for update", id);
            return null; // Return null if not found for update
        }
    }

    // Delete PackingList entry by ID
    public void deletePackingList(Integer id) {
        logger.info("Attempting to delete PackingList with ID: {}", id);
        if (packingListRepository.existsById(id)) {
            packingListRepository.deleteById(id);
            logger.info("Successfully deleted PackingList with ID: {}", id);
        } else {
            logger.warn("PackingList with ID: {} not found for deletion", id);
        }
    }
}
