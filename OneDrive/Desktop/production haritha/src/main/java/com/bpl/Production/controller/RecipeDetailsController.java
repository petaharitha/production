package com.bpl.Production.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.RecipeDetails;
import com.bpl.Production.service.RecipeDetailsService;

@RestController
@RequestMapping("/recipe-details")
public class RecipeDetailsController {

    @Autowired
    private RecipeDetailsService recipeDetailsService;

    // Create a new RecipeDetails
    @PostMapping
    public RecipeDetails createRecipeDetails(@RequestBody RecipeDetails recipeDetails) {
        return recipeDetailsService.createRecipeDetails(recipeDetails);
    }

    // Update an existing RecipeDetails
    @PutMapping("/{id}")
    public RecipeDetails updateRecipeDetails(@PathVariable Integer id, @RequestBody RecipeDetails updatedRecipeDetails) {
        return recipeDetailsService.updateRecipeDetails(id, updatedRecipeDetails);
    }

    // Delete a RecipeDetails
    @DeleteMapping("/{id}")
    public void deleteRecipeDetails(@PathVariable Integer id) {
        recipeDetailsService.deleteRecipeDetails(id);
    }

    // Get a RecipeDetails by id
    @GetMapping("/{id}")
    public Optional<RecipeDetails> getRecipeDetailsById(@PathVariable Integer id) {
        return recipeDetailsService.getRecipeDetailsById(id);
    }

    // Get all RecipeDetails with pagination and filtering
    @GetMapping
    public Page<RecipeDetails> getRecipeDetails(
            @RequestParam(required = false) Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return recipeDetailsService.getRecipeDetails(id, page, size);
    }
}
