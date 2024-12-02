package com.bpl.Production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.PlantLoad;
import com.bpl.Production.entity.Reservation;

public interface PlantLoadRepository extends JpaRepository<PlantLoad, Integer>{

	List<PlantLoad> findByConsigneeNumber(String consigneeNumber);

	List<PlantLoad> findByYearAndMonth(int year, int month);

	List<PlantLoad> findByHpgIn(List<String> hpgs);

	List<PlantLoad> findByHpgInAndTypeInAndYearInAndMonthIn(List<String> hpgs, List<String> types, List<Integer> years,
			List<Integer> months);
	
	

	
}
