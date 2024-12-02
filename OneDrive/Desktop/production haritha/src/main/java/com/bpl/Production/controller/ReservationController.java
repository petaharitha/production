package com.bpl.Production.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bpl.Production.entity.Reservation;
import com.bpl.Production.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Create a new Reservation
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        try {
            Reservation createdReservation = reservationService.createReservation(reservation);
            return ResponseEntity.ok(createdReservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Update an existing Reservation
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Integer id, @RequestBody Reservation updatedReservation) {
        try {
            Reservation reservation = reservationService.updateReservation(id, updatedReservation);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Delete a Reservation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Integer id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Reservation deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete reservation: " + e.getMessage());
        }
    }

    // Edit specific fields of a Reservation
    @PatchMapping("/{id}")
    public ResponseEntity<Reservation> editReservation(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            Reservation updatedReservation = reservationService.editReservation(id, updates);
            return ResponseEntity.ok(updatedReservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @GetMapping
    public Page<Reservation> getReservations(
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String tag,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int tableRowsToDisplay) {
        try {
            return reservationService.getReservations(fromDate, toDate, tag, page, tableRowsToDisplay);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching reservations: " + e.getMessage());
        }
    }
    @GetMapping("/reserveations/alternate")
    public List<Reservation> getReservations(
            @RequestParam(required = false) String consigneeNumber,
            @RequestParam(required = false) List<String> hpg,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        return reservationService.getReservationsalternate(consigneeNumber, hpg, year, month);
    }
}

