package com.bpl.Production.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.bpl.Production.entity.PlantLoad;
import com.bpl.Production.service.PlantLoadService;

@RestController
@RequestMapping("/api/plantload")
public class PlantLoadController {

	@Autowired
	private PlantLoadService plantLoadService;
	
	// Create a new PlantLoad
    @PostMapping
    public ResponseEntity<PlantLoad> createPlantLoad(@RequestBody PlantLoad plantLoad) {
        PlantLoad createdPlantLoad = plantLoadService.createPlantLoad(plantLoad);
        return new ResponseEntity<>(createdPlantLoad, HttpStatus.CREATED);
    }

    // Get all PlantLoads
    @GetMapping
    public ResponseEntity<List<PlantLoad>> getAllPlantLoads() {
        List<PlantLoad> plantLoads = plantLoadService.getAllPlantLoads();
        return new ResponseEntity<>(plantLoads, HttpStatus.OK);
    }

    // Get PlantLoad by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlantLoad> getPlantLoadById(@PathVariable Integer id) {
        Optional<PlantLoad> plantLoad = plantLoadService.getPlantLoadById(id);
        if (plantLoad.isPresent()) {
            return new ResponseEntity<>(plantLoad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update PlantLoad by ID
    @PutMapping("/{id}")
    public ResponseEntity<PlantLoad> updatePlantLoad(@PathVariable Integer id, @RequestBody PlantLoad plantLoadDetails) {
        PlantLoad updatedPlantLoad = plantLoadService.updatePlantLoad(id, plantLoadDetails);
        if (updatedPlantLoad != null) {
            return new ResponseEntity<>(updatedPlantLoad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete PlantLoad by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlantLoad(@PathVariable Integer id) {
        plantLoadService.deletePlantLoad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/consignee/{consigneeNumber}")
    public List<PlantLoad> getPlantLoadsByConsigneeNumber(@PathVariable String consigneeNumber) {
        return plantLoadService.getPlantLoadsByConsigneeNumber(consigneeNumber);
    }

    @GetMapping("/year/{year}/month/{month}")
    public List<PlantLoad> getPlantLoadsByYearAndMonth(@PathVariable int year, @PathVariable int month) {
        return plantLoadService.getPlantLoadsByYearAndMonth(year , month);
    }
    
    @GetMapping("/hpgs")
    public List<PlantLoad> getPlantLoadsByHpgs(@RequestParam List<String> hpgs) {
        return plantLoadService.getPlantLoadsByHpgs(hpgs);
    }

    @GetMapping("/plantLoads")
    public List<PlantLoad> getPlantLoads(
            @RequestParam(required = false) List<String> hpg,
            @RequestParam(required = false) List<String> type,
            @RequestParam(required = false) List<Integer> year,
            @RequestParam(required = false) List<Integer> month) {
        return plantLoadService.getPlantLoads(hpg, type, year, month);
    }
    
}
