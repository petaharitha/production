package com.supplier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.supplier.entity.PurchasingRFQ;
import com.supplier.entity.Vendor;

@Repository
public interface PurchasingRFQRepository extends JpaRepository<PurchasingRFQ, Long> {
	
    List<PurchasingRFQ> findByVendor(Vendor vendor);
    
 // Custom JPQL Query to fetch PurchasingRFQ with its associated Vendor eagerly
    @Query("SELECT p FROM PurchasingRFQ p JOIN FETCH p.vendor WHERE p.id = :id")
    Optional<PurchasingRFQ> findByIdWithVendor(@Param("id") Long id);

	List<PurchasingRFQ> findAllByVendorId(Long vendorId);

}
