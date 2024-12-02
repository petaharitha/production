package com.bpl.Production.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpl.Production.entity.RawMaterialStock;

@Repository
public interface RawMaterialStockRepository extends JpaRepository<RawMaterialStock, Integer> {

}
