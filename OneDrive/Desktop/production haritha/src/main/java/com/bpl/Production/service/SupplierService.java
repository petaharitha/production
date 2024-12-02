package com.bpl.Production.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Supplier;
import com.bpl.Production.repository.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // Create new supplier
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Update an existing supplier
    public Supplier updateSupplier(Integer id, Supplier supplierDetails) {
        Optional<Supplier> supplierOpt = supplierRepository.findById(id);
        if (supplierOpt.isPresent()) {
            Supplier supplier = supplierOpt.get();
            supplier.setName(supplierDetails.getName());
            supplier.setAddress(supplierDetails.getAddress());
            supplier.setContact(supplierDetails.getContact());
            supplier.setLastModifiedBy(supplierDetails.getLastModifiedBy());
            supplier.setLastModifiedDate(supplierDetails.getLastModifiedDate());
            return supplierRepository.save(supplier);
        }
        return null;  // Return null if supplier not found
    }

    // Delete a supplier
    public boolean deleteSupplier(Integer id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get supplier by id
    public Optional<Supplier> getSupplierById(Integer id) {
        return supplierRepository.findById(id);
    }

    // Get all suppliers with pagination and sorting
    public Page<Supplier> getSuppliers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return supplierRepository.findAll(pageable);
    }

    // Get suppliers with a filter (e.g., by name or contact)
    public Page<Supplier> getSuppliersWithFilter(String name, String contact, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return supplierRepository.findByNameContainingOrContactContaining(name, contact, pageable);
    }
}
