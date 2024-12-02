package com.bpl.Production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.FreeStockAllocation;

public interface FreeStockAllocationRepository extends JpaRepository<FreeStockAllocation, Integer>{

	List<FreeStockAllocation> findBySoNo(String orderNo);

}
