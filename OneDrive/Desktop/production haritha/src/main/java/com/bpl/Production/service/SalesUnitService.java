package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpl.Production.entity.SalesUnit;
import com.bpl.Production.repository.SalesUnitRepository;

@Service
@Transactional
public class SalesUnitService {

    @Autowired
    private SalesUnitRepository salesUnitRepository;

    // Create SalesUnit
    public SalesUnit createSalesUnit(SalesUnit salesUnit) {
        return salesUnitRepository.save(salesUnit);
    }

    // Update SalesUnit
    public SalesUnit updateSalesUnit(Integer id, SalesUnit updatedSalesUnit) {
        Optional<SalesUnit> existingSalesUnit = salesUnitRepository.findById(id);
        if (existingSalesUnit.isPresent()) {
            SalesUnit salesUnit = existingSalesUnit.get();
            // Update fields
            salesUnit.setCreatedBy(updatedSalesUnit.getCreatedBy());
            salesUnit.setLastModifiedBy(updatedSalesUnit.getLastModifiedBy());
            salesUnit.setCreatedDate(updatedSalesUnit.getCreatedDate());
            salesUnit.setLastModifiedDate(updatedSalesUnit.getLastModifiedDate());
            salesUnit.setVeCode(updatedSalesUnit.getVeCode());
            salesUnit.setVeDescription(updatedSalesUnit.getVeDescription());
            return salesUnitRepository.save(salesUnit);
        } else {
            throw new RuntimeException("SalesUnit not found with id: " + id);
        }
    }

    // Delete SalesUnit
    public void deleteSalesUnit(Integer id) {
        salesUnitRepository.deleteById(id);
    }

    // Edit SalesUnit (Similar to Update but can be renamed to make semantic distinction)
    public SalesUnit editSalesUnit(Integer id, SalesUnit editedSalesUnit) {
        return updateSalesUnit(id, editedSalesUnit);
    }

    // Get SalesUnit by ID
    public Optional<SalesUnit> getSalesUnitById(Integer id) {
        return salesUnitRepository.findById(id);
    }

    // Pagination and Filter for SalesUnit
    public Page<SalesUnit> getSalesUnitsWithPaginationAndFilter(
            String veCode, String veDescription, int page, int size, String sortBy, boolean ascending) {

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (veCode != null && veDescription != null) {
            return salesUnitRepository.findByVeCodeContainingAndVeDescriptionContaining(veCode, veDescription, pageable);
        } else if (veCode != null) {
            return salesUnitRepository.findByVeCodeContaining(veCode, pageable);
        } else if (veDescription != null) {
            return salesUnitRepository.findByVeDescriptionContaining(veDescription, pageable);
        } else {
            return salesUnitRepository.findAll(pageable);
        }
    }

    // Get all SalesUnit with pagination (no filters)
    public Page<SalesUnit> getAllSalesUnitsWithPagination(int page, int size, String sortBy, boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return salesUnitRepository.findAll(pageable);
    }

    // Get all SalesUnit (without pagination)
    public List<SalesUnit> getAllSalesUnits() {
        return salesUnitRepository.findAll();
    }
}
