package com.bpl.Production.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.RawMaterial;



public interface RawMaterialRepository extends JpaRepository<RawMaterial, Integer> {

	Page<RawMaterial> findAll(Pageable pageable);
}
