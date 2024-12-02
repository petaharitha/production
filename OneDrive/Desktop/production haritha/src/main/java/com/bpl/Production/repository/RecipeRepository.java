package com.bpl.Production.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.Recipe;



public interface RecipeRepository extends JpaRepository<Recipe, Integer> {


	Page<Recipe> findByStatus(String status, PageRequest pageable);

	Page<Recipe> findById(Integer id, PageRequest pageable);
}
