package com.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplier.entity.PurchasingPO;

@Repository
public interface PurchasingPORepository extends JpaRepository<PurchasingPO, Long> {
}
