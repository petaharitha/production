package com.bpl.Production.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.RecipeDetails;



public interface RecipeDetailsRepository extends JpaRepository<RecipeDetails, Integer>{
	


	Page<RecipeDetails> findById(Integer id, PageRequest pageable);

}
