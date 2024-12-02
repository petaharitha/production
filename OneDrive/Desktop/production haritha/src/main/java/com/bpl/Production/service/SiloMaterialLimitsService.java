package com.bpl.Production.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.SiloMaterialLimits;
import com.bpl.Production.repository.SiloMaterialLimitsRepository;

@Service
public class SiloMaterialLimitsService {

    @Autowired
    private SiloMaterialLimitsRepository repository;

    // Create method
    public SiloMaterialLimits createSiloMaterialLimit(SiloMaterialLimits siloMaterialLimits) {
        return repository.save(siloMaterialLimits);
    }

    // Update method
    public SiloMaterialLimits updateSiloMaterialLimit(Integer id, SiloMaterialLimits siloMaterialLimits) {
        if (repository.existsById(id)) {
            siloMaterialLimits.setId(id);
            return repository.save(siloMaterialLimits);
        }
        return null; // Or throw exception based on your use case
    }

    // Delete method
    public boolean deleteSiloMaterialLimit(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false; // Or throw exception based on your use case
    }

    // Edit method (also update, so handled by update method above)

    // Pagination method
    public Page<SiloMaterialLimits> getSiloMaterialLimits(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    // Filtering method (example: filter by smNo and smDescription)
    public Page<SiloMaterialLimits> filterSiloMaterialLimits(String smNo, String smDescription, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findBySmNoContainingAndSmDescriptionContaining(smNo, smDescription, pageable);
    }
}
