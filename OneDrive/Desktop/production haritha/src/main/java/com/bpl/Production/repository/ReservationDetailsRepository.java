package com.bpl.Production.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.ReservationDetails;



public interface ReservationDetailsRepository extends JpaRepository<ReservationDetails, Integer> {

    // Find by ID
    Optional<ReservationDetails> findById(Integer id);



	Page<ReservationDetails> findByReservationId(Integer reservationId, Pageable pageable);
}
