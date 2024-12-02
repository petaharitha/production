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

import com.bpl.Production.entity.Container;
import com.bpl.Production.service.ContainerService;

@RestController
@RequestMapping("/api/container")
public class ContainerController {

	@Autowired
    private ContainerService containerService;

    // Create a new container
    @PostMapping
    public ResponseEntity<Container> createContainer(@RequestBody Container container) {
        Container createdContainer = containerService.saveContainer(container);
        return new ResponseEntity<>(createdContainer, HttpStatus.CREATED);
    }

    // Get container by ID
    @GetMapping("/{id}")
    public ResponseEntity<Container> getContainerById(@PathVariable Integer id) {
        Optional<Container> container = containerService.getContainerById(id);
        if (container.isPresent()) {
            return new ResponseEntity<>(container.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update container
    @PutMapping("/{id}")
    public ResponseEntity<Container> updateContainer(@PathVariable Integer id, @RequestBody Container containerDetails) {
        Container updatedContainer = containerService.updateContainer(id, containerDetails);
        if (updatedContainer != null) {
            return new ResponseEntity<>(updatedContainer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete container
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainer(@PathVariable Integer id) {
        if (containerService.deleteContainer(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all containers
    @GetMapping
    public ResponseEntity<Iterable<Container>> getAllContainers() {
        Iterable<Container> containers = containerService.getAllContainers();
        return new ResponseEntity<>(containers, HttpStatus.OK);
    }
}
