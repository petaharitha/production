package com.supplier.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.supplier.entity.PurchasingPO;
import com.supplier.entity.Vendor;
import com.supplier.exception.ResourceNotFoundException;
import com.supplier.repository.CommentRepository;
import com.supplier.repository.PurchasingPORepository;
import com.supplier.repository.VendorRepository;

@Service
public class PurchasingPOService {

	private static final Logger logger = LoggerFactory.getLogger(PurchasingPOService.class);

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private PurchasingPORepository poRepository;

	@Autowired
	private CommentRepository commentRepository;

	public PurchasingPO createPO(PurchasingPO po) {
		if (po.getVendor() != null && po.getVendor().getId() != null) {
			Vendor vendor = vendorRepository.findById(po.getVendor().getId()).orElseThrow(
					() -> new ResourceNotFoundException("Supplier not found with id " + po.getVendor().getId()));
			po.setVendor(vendor);
		} else {
			throw new ResourceNotFoundException("Supplier information is required to create a PurchasingPO");
		}
		logger.info("Creating PurchasingPO: {}", po);
		return poRepository.save(po);
	}

	@Cacheable(value = "purchaseOrders")
	public List<PurchasingPO> getAllPOs() {
		logger.info("Fetching all Purchasing POs");
		return poRepository.findAll();
	}

	@Cacheable(value = "purchaseOrders", key = "#id")
	public PurchasingPO getPOById(Long id) {
		logger.info("Fetching Purchasing PO with id: {}", id);
		return poRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PO not found with id " + id));
	}

	@CacheEvict(value = "purchaseOrders", key = "#id")
	public PurchasingPO updatePO(Long id, PurchasingPO poDetails) {
		logger.info("Updating Purchasing PO with id: {}", id);
		PurchasingPO po = poRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("PO not found with id " + id));

		po.setDate(poDetails.getDate());
		po.setNumber(poDetails.getNumber());
		po.setAmount(poDetails.getAmount());
		po.setApprovedBy(poDetails.getApprovedBy());
		po.setDeliveryBy(poDetails.getDeliveryBy());
		po.setStatus(poDetails.getStatus());

		return poRepository.save(po);
	}

	@CacheEvict(value = "purchaseOrders", key = "#id")
	public void deletePO(Long id) {
		logger.info("Deleting Purchasing PO with id: {}", id);
		PurchasingPO po = poRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("PO not found with id " + id));
		poRepository.delete(po);
	}
}
