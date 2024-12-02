package com.bpl.Production.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.SampleTestResults;



public interface SampleTestResultsRepository extends JpaRepository<SampleTestResults, Integer>{

	Page<SampleTestResults> findAll(Specification<SampleTestResults> specification, Pageable pageable);

}
