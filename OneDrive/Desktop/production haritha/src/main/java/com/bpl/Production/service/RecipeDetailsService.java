package com.bpl.Production.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.RecipeDetails;
import com.bpl.Production.repository.RecipeDetailsRepository;

@Service
public class RecipeDetailsService {

    @Autowired
    private RecipeDetailsRepository recipeDetailsRepository;

    // Create a new RecipeDetails
    public RecipeDetails createRecipeDetails(RecipeDetails recipeDetails) {
        return recipeDetailsRepository.save(recipeDetails);
    }

    // Update an existing RecipeDetails
    public RecipeDetails updateRecipeDetails(Integer id, RecipeDetails updatedRecipeDetails) {
        Optional<RecipeDetails> existingRecipeDetails = recipeDetailsRepository.findById(id);
        if (existingRecipeDetails.isPresent()) {
            RecipeDetails recipeDetails = existingRecipeDetails.get();
            recipeDetails.setCreatedBy(updatedRecipeDetails.getCreatedBy());
            recipeDetails.setLastModifiedBy(updatedRecipeDetails.getLastModifiedBy());
            recipeDetails.setCreatedDate(updatedRecipeDetails.getCreatedDate());
            recipeDetails.setLastModifiedDate(updatedRecipeDetails.getLastModifiedDate());
            recipeDetails.setRecipeNo(updatedRecipeDetails.getRecipeNo());
            recipeDetails.setAltNo(updatedRecipeDetails.getAltNo());
            recipeDetails.setMaterialNo(updatedRecipeDetails.getMaterialNo());
            recipeDetails.setMaterialName(updatedRecipeDetails.getMaterialName());
            recipeDetails.setQtyMtons(updatedRecipeDetails.getQtyMtons());
            recipeDetails.setUnitOfMeasure(updatedRecipeDetails.getUnitOfMeasure());
            recipeDetails.setRawMaterialNo(updatedRecipeDetails.getRawMaterialNo());
            recipeDetails.setRawMaterialDescription(updatedRecipeDetails.getRawMaterialDescription());
            return recipeDetailsRepository.save(recipeDetails);
        } else {
            return null; // or throw exception
        }
    }

    // Delete a RecipeDetails by id
    public void deleteRecipeDetails(Integer id) {
        recipeDetailsRepository.deleteById(id);
    }

    // Get a RecipeDetails by id
    public Optional<RecipeDetails> getRecipeDetailsById(Integer id) {
        return recipeDetailsRepository.findById(id);
    }

    // Get all RecipeDetails with pagination and filtering
    public Page<RecipeDetails> getRecipeDetails(Integer id, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return recipeDetailsRepository.findById(id, pageable);
    }
}
