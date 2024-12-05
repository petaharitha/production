package com.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplier.entity.PurchasingPaymentMade;

@Repository
public interface PurchasingPaymentRepository extends JpaRepository<PurchasingPaymentMade, Long> {
}
