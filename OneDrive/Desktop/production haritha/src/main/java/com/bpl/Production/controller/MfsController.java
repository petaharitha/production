package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.Mfs;
import com.bpl.Production.service.MfsService;

@RestController
@RequestMapping("/api/mfs")
public class MfsController {

	@Autowired
    private MfsService mfsService;

    // Create a new Mfs
    @PostMapping
    public ResponseEntity<Mfs> createMfs(@RequestBody Mfs mfs) {
        Mfs createdMfs = mfsService.createMfs(mfs);
        return ResponseEntity.ok(createdMfs);
    }

    // Get Mfs by ID
    @GetMapping("/{id}")
    public ResponseEntity<Mfs> getMfsById(@PathVariable Integer id) {
        Optional<Mfs> mfs = mfsService.getMfsById(id);
        return mfs.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Mfs
    @GetMapping
    public ResponseEntity<List<Mfs>> getAllMfs() {
        List<Mfs> mfsList = mfsService.getAllMfs();
        return ResponseEntity.ok(mfsList);
    }

    // Delete Mfs by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMfs(@PathVariable Integer id) {
        mfsService.deleteMfs(id);
        return ResponseEntity.noContent().build();
    }

    // Update Mfs by ID
    @PutMapping("/{id}")
    public ResponseEntity<Mfs> updateMfs(@PathVariable Integer id, @RequestBody Mfs mfsDetails) {
        Mfs updatedMfs = mfsService.updateMfs(id, mfsDetails);
        return ResponseEntity.ok(updatedMfs);
    }
}
