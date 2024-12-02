package com.bpl.Production.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.SiloMaterialLimits;

public interface SiloMaterialLimitsRepository extends JpaRepository<SiloMaterialLimits, Integer> {

	Page<SiloMaterialLimits> findBySmNoContainingAndSmDescriptionContaining(String smNo, String smDescription,
			Pageable pageable);

}
