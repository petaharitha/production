package com.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supplier.entity.PurchasingQuote;

@Repository
public interface PurchasingQuoteRepository extends JpaRepository<PurchasingQuote, Long> {
}
