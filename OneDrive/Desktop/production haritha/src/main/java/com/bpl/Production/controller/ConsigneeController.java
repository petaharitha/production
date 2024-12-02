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

import com.bpl.Production.entity.Consignee;
import com.bpl.Production.service.ConsigneeService;

@RestController
@RequestMapping("/api/consignee")
public class ConsigneeController {

	 @Autowired
	    private ConsigneeService consigneeService;

	    // Create a new Consignee
	    @PostMapping
	    public ResponseEntity<Consignee> createConsignee(@RequestBody Consignee consignee) {
	        Consignee createdConsignee = consigneeService.createConsignee(consignee);
	        return new ResponseEntity<>(createdConsignee, HttpStatus.CREATED);
	    }

	    // Get a Consignee by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Consignee> getConsigneeById(@PathVariable Integer id) {
	        Optional<Consignee> consignee = consigneeService.getConsigneeById(id);
	        if (consignee.isPresent()) {
	            return new ResponseEntity<>(consignee.get(), HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    // Get all Consignees
	    @GetMapping
	    public ResponseEntity<Iterable<Consignee>> getAllConsignees() {
	        Iterable<Consignee> consignees = consigneeService.getAllConsignee();
	        return new ResponseEntity<>(consignees, HttpStatus.OK);
	    }

	    // Update an existing Consignee
	    @PutMapping("/{id}")
	    public ResponseEntity<Consignee> updateConsignee(@PathVariable Integer id, @RequestBody Consignee consigneeDetails) {
	        Consignee updatedConsignee = consigneeService.updateConsignee(id, consigneeDetails);
	        if (updatedConsignee != null) {
	            return new ResponseEntity<>(updatedConsignee, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    // Delete a Consignee by ID
	    @DeleteMapping("/{id}")
	    public void deleteConsignee(@PathVariable Integer id) {
	        consigneeService.deleteConsignee(id);
	    }
}
