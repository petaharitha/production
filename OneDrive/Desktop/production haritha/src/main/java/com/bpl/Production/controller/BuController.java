package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.Bu;
import com.bpl.Production.service.BuService;

@RestController
@RequestMapping("/api/bu")
public class BuController {

	@Autowired
    private BuService buService;

    @PostMapping
    public ResponseEntity<Bu> createBu(@RequestBody Bu bu) {
        Bu createdBu = buService.createBu(bu);  
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBu);
    }

    @GetMapping
    public List<Bu> getAllBu() {
        return buService.getAllBu();
    }

    @GetMapping("/{id}")
    public Optional<Bu> getBuById(@PathVariable Integer id) {
        return buService.getBuById(id);
    }

    @PutMapping("/{id}")
    public Bu updateBu(@PathVariable Integer id, @RequestBody Bu buDetails) {
        return buService.updateBu(id, buDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBu(@PathVariable Integer id) {
        buService.deleteBu(id);
    }
}
