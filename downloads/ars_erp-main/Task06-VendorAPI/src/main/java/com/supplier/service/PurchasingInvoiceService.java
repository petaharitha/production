package com.supplier.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.supplier.entity.PurchasingInvoice;
import com.supplier.entity.PurchasingPO;
import com.supplier.entity.Vendor;
import com.supplier.exception.ResourceNotFoundException;
import com.supplier.repository.PurchasingInvoiceRepository;
import com.supplier.repository.PurchasingPORepository;
import com.supplier.repository.VendorRepository;

@Service
public class PurchasingInvoiceService {

	// Logger for this class
	private static final Logger logger = LoggerFactory.getLogger(PurchasingInvoiceService.class);

	@Autowired
	private PurchasingInvoiceRepository invoiceRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private PurchasingPORepository purchasingPORepository;

	public PurchasingInvoice createInvoice(PurchasingInvoice invoice) {
		// Fetch supplier by ID (ensure it's present)
		Optional<Vendor> optionalSupplier = vendorRepository.findById(invoice.getVendor().getId());
		if (optionalSupplier.isPresent()) {
			invoice.setVendor(optionalSupplier.get());
		} else {
			throw new ResourceNotFoundException("Supplier not found with id " + invoice.getVendor().getId());
		}

		// Fetch PurchasingPO by ID (ensure it's present)
		Optional<PurchasingPO> optionalPO = purchasingPORepository.findById(invoice.getPurchasingPO().getId());
		if (optionalPO.isPresent()) {
			invoice.setPurchasingPO(optionalPO.get());
		} else {
			throw new ResourceNotFoundException("PurchasingPO not found with id " + invoice.getPurchasingPO().getId());
		}

		// Save the populated invoice
		logger.info("Creating invoice: {}", invoice.getNumber());
		PurchasingInvoice savedInvoice = invoiceRepository.save(invoice);
		logger.info("Invoice with ID: {} created successfully.", savedInvoice.getId());
		return savedInvoice;
	}

	@Cacheable(value = "invoices")
	public List<PurchasingInvoice> getAllInvoices() {
		logger.info("Fetching all invoices.");
		return invoiceRepository.findAll();
	}

	@Cacheable(value = "invoices", key = "#id")
	public PurchasingInvoice getInvoiceById(Long id) {
		logger.info("Fetching invoice with ID: {}", id);
		return invoiceRepository.findById(id).orElseThrow(() -> {
			logger.error("Invoice with ID: {} not found.", id);
			return new ResourceNotFoundException("Invoice not found with id " + id);
		});
	}

	@CacheEvict(value = "invoices", key = "#id")
	public PurchasingInvoice updateInvoice(Long id, PurchasingInvoice invoiceDetails) {
		logger.info("Updating invoice with ID: {}", id);
		Optional<PurchasingInvoice> optionalInvoice = invoiceRepository.findById(id);
		if (optionalInvoice.isPresent()) {
			PurchasingInvoice invoice = optionalInvoice.get();
			invoice.setDate(invoiceDetails.getDate());
			invoice.setNumber(invoiceDetails.getNumber());
			invoice.setParticulars(invoiceDetails.getParticulars());
			invoice.setAmount(invoiceDetails.getAmount());
			invoice.setDueBy(invoiceDetails.getDueBy());
			// Update any additional fields as needed
			PurchasingInvoice updatedInvoice = invoiceRepository.save(invoice);
			logger.info("Invoice with ID: {} updated successfully.", updatedInvoice.getId());
			return updatedInvoice;
		}
		logger.error("Invoice with ID: {} not found for update.", id);
		throw new ResourceNotFoundException("Invoice not found with id " + id);
	}

	@CacheEvict(value = "invoices", key = "#id")
	public void deleteInvoice(Long id) {
		logger.info("Deleting invoice with ID: {}", id);
		PurchasingInvoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + id));
		invoiceRepository.delete(invoice);
		logger.info("Invoice with ID: {} deleted successfully.", id);
	}
}
