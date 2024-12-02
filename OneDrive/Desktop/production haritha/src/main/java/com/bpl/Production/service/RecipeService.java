package com.bpl.Production.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Recipe;
import com.bpl.Production.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    // Create a new recipe
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    // Update an existing recipe
    public Recipe updateRecipe(Integer id, Recipe updatedRecipe) {
        Optional<Recipe> existingRecipe = recipeRepository.findById(id);
        if (existingRecipe.isPresent()) {
            Recipe recipe = existingRecipe.get();
            recipe.setCreatedBy(updatedRecipe.getCreatedBy());
            recipe.setLastModifiedBy(updatedRecipe.getLastModifiedBy());
            recipe.setCreatedDate(updatedRecipe.getCreatedDate());
            recipe.setLastModifiedDate(updatedRecipe.getLastModifiedDate());
            recipe.setHpg(updatedRecipe.getHpg());
            recipe.setPg(updatedRecipe.getPg());
            recipe.setRecipeNumber(updatedRecipe.getRecipeNumber());
            recipe.setQuality(updatedRecipe.getQuality());
            recipe.setAlt(updatedRecipe.getAlt());
            return recipeRepository.save(recipe);
        } else {
            return null; // or throw exception
        }
    }

    // Delete a recipe by id
    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }

    // Get a recipe by id
    public Optional<Recipe> getRecipeById(Integer id) {
        return recipeRepository.findById(id);
    }

    // Get all recipes with pagination and filtering
    public Page<Recipe> getRecipes(String condition, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return recipeRepository.findByStatus(condition, pageable);
    }
    
    public Page<Recipe> searchId(Integer id, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return recipeRepository.findById(id, pageable);
    }
}
