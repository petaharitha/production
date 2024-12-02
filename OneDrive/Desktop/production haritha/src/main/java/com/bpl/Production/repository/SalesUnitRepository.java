package com.bpl.Production.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.SalesUnit;

public interface SalesUnitRepository extends JpaRepository<SalesUnit, Integer> {
    
    Page<SalesUnit> findByVeCodeContaining(String veCode, Pageable pageable);

    Page<SalesUnit> findByVeDescriptionContaining(String veDescription, Pageable pageable);

    Page<SalesUnit> findByVeCodeContainingAndVeDescriptionContaining(String veCode, String veDescription, Pageable pageable);
}
