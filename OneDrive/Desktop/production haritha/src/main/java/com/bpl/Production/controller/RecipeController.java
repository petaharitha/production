package com.bpl.Production.controller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bpl.Production.entity.Recipe;
import com.bpl.Production.entity.SampleTestResults;
import com.bpl.Production.repository.SampleTestResultsRepository;
import com.bpl.Production.service.RecipeService;
import com.bpl.Production.xml.RecipeImportExport;
import com.bpl.Production.xml.SampleTestResultsImportExport;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;
    
    @Autowired
    private RecipeImportExport recipeImportExport;
    
    @Autowired
    private SampleTestResultsImportExport sampleTestResultsImportExport;
    
    @Autowired
    private SampleTestResultsRepository sampleTestResultsRepository;

    // Create a new recipe
    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    // Update an existing recipe
    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Integer id, @RequestBody Recipe updatedRecipe) {
        return recipeService.updateRecipe(id, updatedRecipe);
    }

    // Delete a recipe
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Integer id) {
        recipeService.deleteRecipe(id);
    }

    // Get a recipe by id
    @GetMapping("/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable Integer id) {
        return recipeService.getRecipeById(id);
    }

    // Get all recipes with pagination and filtering
    @GetMapping
    public Page<Recipe> getRecipes(
            @RequestParam(required = false) String condition,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return recipeService.getRecipes(condition, page, size);
    }

    // Import recipes from an Excel file
    @PostMapping("/import/recipes")
    public ResponseEntity<String> importRecipes(@RequestParam("file") MultipartFile file) {
        try {
            List<Recipe> recipes = recipeImportExport.importFromExcel(file);
            // Save the recipes to the database if needed
            // recipeRepository.saveAll(recipes);

            return ResponseEntity.ok("Recipes imported successfully, total: " + recipes.size());
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            return ResponseEntity.status(500).body("Error importing recipes: " + e.getMessage());
        }
    }

    // Import sample test results from an Excel file
    @PostMapping("/import/test-results")
    public ResponseEntity<String> importFromExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<SampleTestResults> results = sampleTestResultsImportExport.importFromExcel(file.getInputStream());
            // Save the imported results to your repository
            return ResponseEntity.ok("File imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to import file.");
        }
    }

    // Export recipes to Excel
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportRecipes() {
        try {
            List<Recipe> recipes = getAllRecipes(); // Retrieve recipes from the database
            byte[] excelFile = recipeImportExport.exportToExcel(recipes);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("recipes.xlsx")
                    .build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(excelFile);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Export sample test results to Excel
    @GetMapping("/export/test-results")
    public ResponseEntity<byte[]> exportSampleTestResultsToExcel() {
        List<SampleTestResults> results = sampleTestResultsRepository.findAll();
        try {
            byte[] excelFile = sampleTestResultsImportExport.exportToExcel(results);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=SampleTestResults.xlsx");
            return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Helper method to get all recipes (can be adjusted to fetch from DB)
    private List<Recipe> getAllRecipes() {
        return List.of(); // Placeholder for actual fetching logic
    }

    // Search for recipes by id
    @GetMapping("/search-recipes")
    public Page<Recipe> searchRecipes(
            @RequestParam Integer id,
            @RequestParam int page,
            @RequestParam int size) {
        return recipeService.searchId(id, page, size);
    }
}
