package com.bpl.Production.controller;

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

import com.bpl.Production.entity.ContainerList;
import com.bpl.Production.service.ContainerListService;

@RestController
@RequestMapping("/api/container-list")
public class ContainerListController {

	@Autowired
	private ContainerListService containerListService;
	
	// Create a new container list
    @PostMapping
    public ResponseEntity<ContainerList> createContainerList(@RequestBody ContainerList containerList) {
        ContainerList createdContainerList = containerListService.saveContainerList(containerList);
        return new ResponseEntity<>(createdContainerList, HttpStatus.CREATED);
    }

    // Get container list by ID
    @GetMapping("/{id}")
    public ResponseEntity<ContainerList> getContainerListById(@PathVariable Integer id) {
        Optional<ContainerList> containerList = containerListService.getContainerListById(id);
        if (containerList.isPresent()) {
            return new ResponseEntity<>(containerList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update an existing container list
    @PutMapping("/{id}")
    public ResponseEntity<ContainerList> updateContainerList(@PathVariable Integer id, @RequestBody ContainerList containerListDetails) {
        ContainerList updatedContainerList = containerListService.updateContainerList(id, containerListDetails);
        if (updatedContainerList != null) {
            return new ResponseEntity<>(updatedContainerList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete container list by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainerList(@PathVariable Integer id) {
        if (containerListService.deleteContainerList(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all container lists
    @GetMapping
    public ResponseEntity<Iterable<ContainerList>> getAllContainerLists() {
        Iterable<ContainerList> containerLists = containerListService.getAllContainerLists();
        return new ResponseEntity<>(containerLists, HttpStatus.OK);
    }
}
