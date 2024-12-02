package com.bpl.Production.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.SiloMaterialTestResult;



public interface SiloMaterialTestResultRepository extends JpaRepository<SiloMaterialTestResult, Integer>{

	Optional<SiloMaterialTestResult> findBySmNoAndSmDescription(String smNo, String smDescription);

	
	List<SiloMaterialTestResult> findByShift(String shift);

}
