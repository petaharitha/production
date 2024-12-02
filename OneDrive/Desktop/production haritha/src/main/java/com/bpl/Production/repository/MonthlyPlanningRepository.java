package com.bpl.Production.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.MonthlyPlanning;

public interface MonthlyPlanningRepository extends JpaRepository<MonthlyPlanning, Integer>{

}
