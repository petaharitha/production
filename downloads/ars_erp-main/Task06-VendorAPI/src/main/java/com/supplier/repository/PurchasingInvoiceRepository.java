package com.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplier.entity.PurchasingInvoice;

@Repository
public interface PurchasingInvoiceRepository extends JpaRepository<PurchasingInvoice, Long> {
}

