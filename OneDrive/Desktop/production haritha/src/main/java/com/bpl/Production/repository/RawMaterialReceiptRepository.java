package com.bpl.Production.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.RawMaterialReceipt;

import com.bpl.Production.entity.RawMaterialReceipt;



public interface RawMaterialReceiptRepository extends JpaRepository<RawMaterialReceipt, Integer> {

	Page<RawMaterialReceipt> findByStatus(String ststus, PageRequest pageable);

	RawMaterialReceipt findByInwardNo(Integer srNo);

	Page<RawMaterialReceipt> findByDcDate(Date fromDate, Date toDate, PageRequest of);

}
