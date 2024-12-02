package com.bpl.Production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.PrecastLimits;

public interface PrecastLimitsRepository extends JpaRepository<PrecastLimits, Integer>{
	List<PrecastLimits> findByQualityAndRecipeNoAndExpiredFromIsNull(String quality, Integer recipeNo);
}
