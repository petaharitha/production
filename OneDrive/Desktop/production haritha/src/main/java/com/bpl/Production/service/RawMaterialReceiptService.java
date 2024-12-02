package com.bpl.Production.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpl.Production.entity.RawMaterialReceipt;
import com.bpl.Production.repository.RawMaterialReceiptRepository;

@Service
@Transactional
public class RawMaterialReceiptService {

	private static final Logger logger = LoggerFactory.getLogger(RawMaterialReceiptService.class);

	@Autowired
	private RawMaterialReceiptRepository rawMaterialReceiptRepository;

	// Create or save a new RawMaterialReceipt

	// Create a new RawMaterialReceipt
	public RawMaterialReceipt createRawMaterialReceipt(RawMaterialReceipt rawMaterialReceipt) {
		return rawMaterialReceiptRepository.save(rawMaterialReceipt);
	}

	// Update an existing RawMaterialReceipt
	public RawMaterialReceipt updateRawMaterialReceipt(Integer id, RawMaterialReceipt updatedReceipt) {
		Optional<RawMaterialReceipt> existingReceipt = rawMaterialReceiptRepository.findById(id);

		if (existingReceipt.isPresent()) {
			RawMaterialReceipt receipt = existingReceipt.get();
			// You can update the fields as necessary here
			receipt.setCreatedBy(updatedReceipt.getCreatedBy());
			receipt.setLastModifiedBy(updatedReceipt.getLastModifiedBy());
			receipt.setCreatedDate(updatedReceipt.getCreatedDate());
			receipt.setLastModifiedDate(updatedReceipt.getLastModifiedDate());
			receipt.setInwardDate(updatedReceipt.getInwardDate());
			receipt.setInwardNo(updatedReceipt.getInwardNo());
			receipt.setRmNo(updatedReceipt.getRmNo());
			receipt.setRmDescription(updatedReceipt.getRmDescription());
			receipt.setSupplierNo(updatedReceipt.getSupplierNo());
			receipt.setSupplierDescription(updatedReceipt.getSupplierDescription());
			receipt.setRcdAgainst(updatedReceipt.getRcdAgainst());
			receipt.setUnitOfMeasure(updatedReceipt.getUnitOfMeasure());
			receipt.setTruckNo(updatedReceipt.getTruckNo());
			receipt.setPoONo(updatedReceipt.getPoONo());
			receipt.setPoODate(updatedReceipt.getPoODate());
			receipt.setDcNo(updatedReceipt.getDcNo());
			receipt.setDcDate(updatedReceipt.getDcDate());
			receipt.setQtyAsPerDc(updatedReceipt.getQtyAsPerDc());
			receipt.setActualQtyRcd(updatedReceipt.getActualQtyRcd());
			receipt.setQtyRejected(updatedReceipt.getQtyRejected());
			receipt.setQtyAccepted(updatedReceipt.getQtyAccepted());
			receipt.setValue(updatedReceipt.getValue());
			receipt.setRemarks(updatedReceipt.getRemarks());
			receipt.setStatus(updatedReceipt.getStatus());
			receipt.setTag(updatedReceipt.getTag());

			return rawMaterialReceiptRepository.save(receipt);
		} else {
			return null; // Receipt not found for the given ID
		}
	}

	// Delete a RawMaterialReceipt by ID
	public boolean deleteRawMaterialReceipt(Integer id) {
		Optional<RawMaterialReceipt> existingReceipt = rawMaterialReceiptRepository.findById(id);

		if (existingReceipt.isPresent()) {
			rawMaterialReceiptRepository.deleteById(id);
			return true; // Deletion successful
		} else {
			return false; // Receipt not found for the given ID
		}
	}

	// Get all RawMaterialReceipts
	public List<RawMaterialReceipt> getAllRawMaterialReceipts() {
		return rawMaterialReceiptRepository.findAll();
	}

	// Get a RawMaterialReceipt by ID
	public RawMaterialReceipt getRawMaterialReceiptById(Integer id) {
		Optional<RawMaterialReceipt> receipt = rawMaterialReceiptRepository.findById(id);
		return receipt.orElse(null); // Returns null if not found
	}

	public Page<RawMaterialReceipt> getRawMaterialReceipts(String ststus, int page, int size) {
        // Create a pageable object with the page number and size
        PageRequest pageable = PageRequest.of(page, size);

        // Call the repository method with the condition and pageable
        return rawMaterialReceiptRepository.findByStatus(ststus, pageable);
    }
	
	public Page<RawMaterialReceipt> getReceiptsByDateRange(String fromDateStr, String toDateStr, int page, int size) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = null;
        Date toDate = null;

        if (fromDateStr != null && !fromDateStr.trim().isEmpty()) {
            fromDate = sdf.parse(fromDateStr);
        }

        if (toDateStr != null && !toDateStr.trim().isEmpty()) {
            toDate = sdf.parse(toDateStr);
        }

        if (fromDate != null) {
            if (toDate == null) {
                toDate = fromDate;
            }
        }

        // Return all records if no dates are provided
        if (fromDate == null || toDate == null) {
            return rawMaterialReceiptRepository.findAll(PageRequest.of(page, size));
        }

        // Query with date range
        return rawMaterialReceiptRepository.findByDcDate(fromDate, toDate, PageRequest.of(page, size));
    }
	
}
