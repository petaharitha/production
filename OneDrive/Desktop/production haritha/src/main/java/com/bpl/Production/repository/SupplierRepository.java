package com.bpl.Production.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.Supplier;



public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	Page<Supplier> findByNameContainingOrContactContaining(String name, String contact, Pageable pageable);

}
