package com.bpl.Production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.FreeStock;

public interface FreeStockRepository extends JpaRepository<FreeStock, Integer>{

	List<FreeStock> findBySoNo(String orderNo);

}
