package com.bpl.Production.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.CommercialInvoice;
import com.bpl.Production.repository.CommercialInvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommercialInvoiceService {

    // Logger for logging service activities
    private static final Logger logger = LoggerFactory.getLogger(CommercialInvoiceService.class);

    @Autowired
    private CommercialInvoiceRepository commercialInvoiceRepository;

    // Create a new CommercialInvoice
    public CommercialInvoice createCommercialInvoice(CommercialInvoice commercialInvoice) {
        logger.info("Creating new CommercialInvoice with invoiceNo: {}", commercialInvoice.getCommercialInvoiceNo());
        return commercialInvoiceRepository.save(commercialInvoice);
    }

    // Get CommercialInvoice by ID
    public Optional<CommercialInvoice> getCommercialInvoiceById(Long id) {
        logger.info("Fetching CommercialInvoice with ID: {}", id);
        return commercialInvoiceRepository.findById(id);
    }

    // Get all CommercialInvoices
    public List<CommercialInvoice> getAllCommercialInvoices() {
        logger.info("Fetching all CommercialInvoices");
        return commercialInvoiceRepository.findAll();
    }

    // Update an existing CommercialInvoice
    public CommercialInvoice updateCommercialInvoice(Long id, CommercialInvoice updatedCommercialInvoice) {
        logger.info("Updating CommercialInvoice with ID: {}", id);

        // Check if the invoice exists
        Optional<CommercialInvoice> existingInvoice = commercialInvoiceRepository.findById(id);
        if (existingInvoice.isPresent()) {
            CommercialInvoice commercialInvoice = existingInvoice.get();

            // Updating fields
            commercialInvoice.setCommercialInvoiceNo(updatedCommercialInvoice.getCommercialInvoiceNo());
            commercialInvoice.setCommercialInvoiceDate(updatedCommercialInvoice.getCommercialInvoiceDate());
            commercialInvoice.setCustomerPoNo(updatedCommercialInvoice.getCustomerPoNo());
            commercialInvoice.setCustomerNr(updatedCommercialInvoice.getCustomerNr());
            commercialInvoice.setCustomerName(updatedCommercialInvoice.getCustomerName());
            commercialInvoice.setConsigneeNumber(updatedCommercialInvoice.getConsigneeNumber());
            commercialInvoice.setConsigneeName(updatedCommercialInvoice.getConsigneeName());
            commercialInvoice.setSalesUnit(updatedCommercialInvoice.getSalesUnit());
            commercialInvoice.setBusinessUnit(updatedCommercialInvoice.getBusinessUnit());
            commercialInvoice.setMfs(updatedCommercialInvoice.getMfs());
            commercialInvoice.setIecNo(updatedCommercialInvoice.getIecNo());
            commercialInvoice.setCin(updatedCommercialInvoice.getCin());
            commercialInvoice.setCommodity(updatedCommercialInvoice.getCommodity());
            commercialInvoice.setPreCarriageBy(updatedCommercialInvoice.getPreCarriageBy());
            commercialInvoice.setPlaceOfReceiptByPreCarriers(updatedCommercialInvoice.getPlaceOfReceiptByPreCarriers());
            commercialInvoice.setPortOfLoading(updatedCommercialInvoice.getPortOfLoading());
            commercialInvoice.setCountryOfOrigin(updatedCommercialInvoice.getCountryOfOrigin());
            commercialInvoice.setPortOfDischarge(updatedCommercialInvoice.getPortOfDischarge());
            commercialInvoice.setFinalDestination(updatedCommercialInvoice.getFinalDestination());
            commercialInvoice.setCountryOfFinalDestination(updatedCommercialInvoice.getCountryOfFinalDestination());
            commercialInvoice.setAgainstCForm(updatedCommercialInvoice.getAgainstCForm());
            commercialInvoice.setCurrency(updatedCommercialInvoice.getCurrency());
            commercialInvoice.setLabelMarking(updatedCommercialInvoice.getLabelMarking());
            commercialInvoice.setTermsOfDelivery(updatedCommercialInvoice.getTermsOfDelivery());
            commercialInvoice.setIsCommercialInvoiceDetailsCreated(updatedCommercialInvoice.getIsCommercialInvoiceDetailsCreated());

            // Save the updated invoice
            logger.info("CommercialInvoice with ID: {} updated successfully", id);
            return commercialInvoiceRepository.save(commercialInvoice);
        } else {
            logger.error("CommercialInvoice with ID: {} not found", id);
            throw new RuntimeException("CommercialInvoice not found with ID: " + id);
        }
    }

    // Delete a CommercialInvoice by ID
    public void deleteCommercialInvoice(Long id) {
        logger.info("Deleting CommercialInvoice with ID: {}", id);

        // Check if the invoice exists
        if (commercialInvoiceRepository.existsById(id)) {
            commercialInvoiceRepository.deleteById(id);
            logger.info("CommercialInvoice with ID: {} deleted successfully", id);
        } else {
            logger.error("CommercialInvoice with ID: {} not found", id);
            throw new RuntimeException("CommercialInvoice not found with ID: " + id);
        }
    }
}
