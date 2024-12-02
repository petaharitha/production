package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Consignee;
import com.bpl.Production.repository.ConsigneeRepo;

@Service
public class ConsigneeService {

    private static final Logger logger = LoggerFactory.getLogger(ConsigneeService.class); // Initialize logger

    @Autowired
    private ConsigneeRepo consigneeRepo;

    // Create Consignee
    public Consignee createConsignee(Consignee consignee) {
        logger.info("Creating new Consignee with name: {}", consignee.getName()); // Log info about creation
        return consigneeRepo.save(consignee);
    }

    // Get All Consignees
    public List<Consignee> getAllConsignee() {
        logger.info("Fetching all Consignees"); // Log info about fetching all consignees
        return consigneeRepo.findAll();
    }

    // Get Consignee by ID
    public Optional<Consignee> getConsigneeById(Integer id) {
        logger.info("Fetching Consignee with ID: {}", id); // Log info about fetching consignee by ID
        return consigneeRepo.findById(id);
    }

    // Update Consignee
    public Consignee updateConsignee(Integer id, Consignee consigneeDetails) {
        logger.info("Updating Consignee with ID: {}", id); // Log info about updating consignee

        Optional<Consignee> existingConsignee = consigneeRepo.findById(id);
        if (existingConsignee.isPresent()) {
            Consignee consignee = existingConsignee.get();

            // Set new values to the consignee
            consignee.setCreatedBy(consigneeDetails.getCreatedBy());
            consignee.setLastModifiedBy(consigneeDetails.getLastModifiedBy());
            consignee.setCreatedDate(consigneeDetails.getCreatedDate());
            consignee.setLastModifiedDate(consigneeDetails.getLastModifiedDate());
            consignee.setNumber(consigneeDetails.getNumber());
            consignee.setName(consigneeDetails.getName());
            consignee.setAddress(consigneeDetails.getAddress());
            consignee.setCity(consigneeDetails.getCity());
            consignee.setState(consigneeDetails.getState());
            consignee.setZip(consigneeDetails.getZip());
            consignee.setContactName(consigneeDetails.getContactName());
            consignee.setPhone(consigneeDetails.getPhone());
            consignee.setFax(consigneeDetails.getFax());
            consignee.setEmail(consigneeDetails.getEmail());
            consignee.setVatDetails(consigneeDetails.getVatDetails());
            consignee.setCeRegistration(consigneeDetails.getCeRegistration());
            consignee.setCeRange(consigneeDetails.getCeRange());
            consignee.setCeDivision(consigneeDetails.getCeDivision());
            consignee.setOtherDetails(consigneeDetails.getOtherDetails());
            consignee.setCustomerNo(consigneeDetails.getCustomerNo());
            consignee.setCustomerName(consigneeDetails.getCustomerName());

            // Log updated details
            logger.info("Consignee with ID: {} updated successfully", id);
            return consigneeRepo.save(consignee);
        } else {
            logger.warn("Consignee with ID: {} not found for update", id); // Log warning if not found
        }
        return null;
    }

    // Delete Consignee
    public void deleteConsignee(Integer id) {
        logger.info("Deleting Consignee with ID: {}", id); // Log info about deleting consignee
        consigneeRepo.deleteById(id);
    }
}
