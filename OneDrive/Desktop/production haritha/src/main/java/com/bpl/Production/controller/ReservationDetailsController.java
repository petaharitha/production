package com.bpl.Production.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.bpl.Production.entity.ReservationDetails;
import com.bpl.Production.service.ReservationDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservation-details")
public class ReservationDetailsController {

    @Autowired
    private ReservationDetailsService reservationDetailsService;

    // Create ReservationDetails
    @PostMapping
    public ResponseEntity<ReservationDetails> createReservationDetails(@Valid @RequestBody ReservationDetails reservationDetails) {
        ReservationDetails createdDetails = reservationDetailsService.createReservationDetails(reservationDetails);
        return new ResponseEntity<>(createdDetails, HttpStatus.CREATED);
    }

    // Update ReservationDetails
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDetails> updateReservationDetails(@PathVariable Integer id, 
                                                                      @Valid @RequestBody ReservationDetails updatedDetails) {
        try {
            ReservationDetails updatedReservation = reservationDetailsService.updateReservationDetails(id, updatedDetails);
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete ReservationDetails
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationDetails(@PathVariable Integer id) {
        reservationDetailsService.deleteReservationDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get ReservationDetails by ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDetails> getReservationDetailsById(@PathVariable Integer id) {
        Optional<ReservationDetails> reservationDetails = reservationDetailsService.getReservationDetailsById(id);
        return reservationDetails.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all ReservationDetails with pagination and filtering
    @GetMapping
    public ResponseEntity<Page<ReservationDetails>> getReservationDetailsWithPaginationAndFilter(
            @RequestParam(required = false) Integer reservationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {

        Page<ReservationDetails> reservationDetailsPage = reservationDetailsService.getReservationDetailsWithPaginationAndFilter(
                reservationId, page, size, sortBy, ascending);
        
        return new ResponseEntity<>(reservationDetailsPage, HttpStatus.OK);
    }

    // Get all ReservationDetails with pagination (without filter)
    @GetMapping("/all")
    public ResponseEntity<Page<ReservationDetails>> getAllReservationDetailsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        
        Page<ReservationDetails> reservationDetailsPage = reservationDetailsService.getAllReservationDetailsWithPagination(page, size, sortBy, ascending);
        
        return new ResponseEntity<>(reservationDetailsPage, HttpStatus.OK);
    }

    // Get all ReservationDetails (without pagination)
    @GetMapping("/list")
    public ResponseEntity<List<ReservationDetails>> getAllReservationDetails() {
        List<ReservationDetails> reservationDetailsList = reservationDetailsService.getAllReservationDetails();
        return new ResponseEntity<>(reservationDetailsList, HttpStatus.OK);
    }
}
