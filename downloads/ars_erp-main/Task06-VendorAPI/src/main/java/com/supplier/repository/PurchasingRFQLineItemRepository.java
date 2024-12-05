package com.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplier.entity.PurchasingRFQLineItem;

@Repository
public interface PurchasingRFQLineItemRepository extends JpaRepository<PurchasingRFQLineItem, Long> {
}

