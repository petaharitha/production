package com.bpl.Production.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.ReservationDetails;
import com.bpl.Production.repository.ReservationDetailsRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationDetailsService {

    @Autowired
    private ReservationDetailsRepository reservationDetailsRepository;

    // Create ReservationDetails
    @Transactional
    public ReservationDetails createReservationDetails(ReservationDetails reservationDetails) {
        // Add any additional logic (validation, processing, etc.) before saving
        return reservationDetailsRepository.save(reservationDetails);
    }

    // Update ReservationDetails
    @Transactional
    public ReservationDetails updateReservationDetails(Integer id, ReservationDetails updatedDetails) {
        Optional<ReservationDetails> existingDetailsOpt = reservationDetailsRepository.findById(id);
        if (existingDetailsOpt.isPresent()) {
            ReservationDetails existingDetails = existingDetailsOpt.get();
            // Update properties
            existingDetails.setCreatedBy(updatedDetails.getCreatedBy());
            existingDetails.setLastModifiedBy(updatedDetails.getLastModifiedBy());
            existingDetails.setCreatedDate(updatedDetails.getCreatedDate());
            existingDetails.setLastModifiedDate(updatedDetails.getLastModifiedDate());
            existingDetails.setHpg(updatedDetails.getHpg());
            existingDetails.setPg(updatedDetails.getPg());
            existingDetails.setQuality(updatedDetails.getQuality());
            existingDetails.setShape(updatedDetails.getShape());
            existingDetails.setQtyMTons(updatedDetails.getQtyMTons());
            existingDetails.setRemarks(updatedDetails.getRemarks());
            existingDetails.setFgid(updatedDetails.getFgid());
            existingDetails.setReservationId(updatedDetails.getReservationId());
            existingDetails.setReservationNo(updatedDetails.getReservationNo());
            
            // Save and return the updated entity
            return reservationDetailsRepository.save(existingDetails);
        } else {
            // Handle case when ReservationDetails with given ID doesn't exist
            throw new RuntimeException("ReservationDetails not found with ID: " + id);
        }
    }

    // Delete ReservationDetails
    @Transactional
    public void deleteReservationDetails(Integer id) {
        reservationDetailsRepository.deleteById(id);
    }

    // Get ReservationDetails by ID
    public Optional<ReservationDetails> getReservationDetailsById(Integer id) {
        return reservationDetailsRepository.findById(id);
    }

    // Pagination and Filtering (example: filter by reservationId)
    public Page<ReservationDetails> getReservationDetailsWithPaginationAndFilter(Integer reservationId, int page, int size, String sortBy, boolean ascending) {
        Pageable pageable;
        if (ascending) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortBy)));
        } else {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc(sortBy)));
        }

        if (reservationId != null) {
            // Apply filter on reservationId
            return reservationDetailsRepository.findByReservationId(reservationId, pageable);
        } else {
            // Return all records
            return reservationDetailsRepository.findAll(pageable);
        }
    }

    // Get all ReservationDetails without filter (for pagination)
    public Page<ReservationDetails> getAllReservationDetailsWithPagination(int page, int size, String sortBy, boolean ascending) {
        Pageable pageable;
        if (ascending) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortBy)));
        } else {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc(sortBy)));
        }

        return reservationDetailsRepository.findAll(pageable);
    }

    // Get all ReservationDetails without pagination
    public List<ReservationDetails> getAllReservationDetails() {
        return reservationDetailsRepository.findAll();
    }
}
