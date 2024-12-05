package com.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplier.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}

