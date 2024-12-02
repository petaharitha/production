package com.bpl.Production.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.Inspection;

public interface InspectionRepository extends JpaRepository<Inspection, Integer>{

}
