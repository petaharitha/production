package com.bpl.Production.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpl.Production.entity.Reservation;
import com.bpl.Production.repository.ReservationRepository;

@Service
public class ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository reservationRepository;

    // Create a new Reservation
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        logger.info("Creating reservation: {}", reservation);
        reservation.setCreatedDate(new Timestamp(System.currentTimeMillis())); // Set current timestamp for createdDate
        reservation.setLastModifiedDate(new Timestamp(System.currentTimeMillis())); // Set current timestamp for lastModifiedDate
        Reservation createdReservation = reservationRepository.save(reservation);
        logger.info("Reservation created successfully: {}", createdReservation);
        return createdReservation;
    }

    // Update an existing Reservation
    @Transactional
    public Reservation updateReservation(Integer id, Reservation updatedReservation) throws Exception {
        logger.info("Updating reservation with ID: {}", id);
        Optional<Reservation> existingReservationOpt = reservationRepository.findById(id);

        if (existingReservationOpt.isPresent()) {
            Reservation existingReservation = existingReservationOpt.get();

            // Update fields
            existingReservation.setReservationNr(updatedReservation.getReservationNr());
            existingReservation.setCustomerNr(updatedReservation.getCustomerNr());
            existingReservation.setCustomerName(updatedReservation.getCustomerName());
            existingReservation.setConsigneeNumber(updatedReservation.getConsigneeNumber());
            existingReservation.setConsigneeName(updatedReservation.getConsigneeName());
            existingReservation.setExwDate(updatedReservation.getExwDate());
            existingReservation.setExwWeek(updatedReservation.getExwWeek());
            existingReservation.setExwMonth(updatedReservation.getExwMonth());
            existingReservation.setExwYear(updatedReservation.getExwYear());
            existingReservation.setOrderType(updatedReservation.getOrderType());
            existingReservation.setBusinessUnit(updatedReservation.getBusinessUnit());
            existingReservation.setSalesPerson(updatedReservation.getSalesPerson());
            existingReservation.setLastModifiedBy(updatedReservation.getLastModifiedBy());
            existingReservation.setLastModifiedDate(new Timestamp(System.currentTimeMillis())); // Update the last modified date

            Reservation savedReservation = reservationRepository.save(existingReservation);
            logger.info("Reservation updated successfully: {}", savedReservation);
            return savedReservation;
        } else {
            logger.error("Reservation with ID {} not found.", id);
            throw new Exception("Reservation with ID " + id + " not found.");
        }
    }

    // Delete a Reservation by ID
    @Transactional
    public void deleteReservation(Integer id) throws Exception {
        logger.info("Attempting to delete reservation with ID: {}", id);
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            logger.info("Reservation with ID {} deleted successfully.", id);
        } else {
            logger.error("Reservation with ID {} not found.", id);
            throw new Exception("Reservation with ID " + id + " not found.");
        }
    }

    // Edit specific fields of a Reservation (partial update)
    @Transactional
    public Reservation editReservation(Integer id, Map<String, Object> updates) throws Exception {
        logger.info("Editing reservation with ID: {}", id);
        Optional<Reservation> reservationOpt = reservationRepository.findById(id);

        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();

            // Apply partial updates dynamically
            updates.forEach((field, value) -> {
                logger.info("Updating field: {} with value: {}", field, value);
                switch (field) {
                    case "reservationNr":
                        reservation.setReservationNr((String) value);
                        break;
                    case "customerNr":
                        reservation.setCustomerNr((Long) value);
                        break;
                    case "customerName":
                        reservation.setCustomerName((String) value);
                        break;
                    case "consigneeNumber":
                        reservation.setConsigneeNumber((String) value);
                        break;
                    case "consigneeName":
                        reservation.setConsigneeName((String) value);
                        break;
                    case "exwDate":
                        reservation.setExwDate((Date) value);
                        break;
                    case "exwWeek":
                        reservation.setExwWeek((Integer) value);
                        break;
                    case "exwMonth":
                        reservation.setExwMonth((Integer) value);
                        break;
                    case "exwYear":
                        reservation.setExwYear((Integer) value);
                        break;
                    case "orderType":
                        reservation.setOrderType((String) value);
                        break;
                    case "businessUnit":
                        reservation.setBusinessUnit((Integer) value);
                        break;
                    case "salesPerson":
                        reservation.setSalesPerson((String) value);
                        break;
                }
            });

            reservation.setLastModifiedDate(new Timestamp(System.currentTimeMillis())); // Update the last modified date
            Reservation savedReservation = reservationRepository.save(reservation);
            logger.info("Reservation edited successfully: {}", savedReservation);
            return savedReservation;
        } else {
            logger.error("Reservation with ID {} not found.", id);
            throw new Exception("Reservation with ID " + id + " not found.");
        }
    }

    // Get filtered reservations with pagination
    public Page<Reservation> getReservations(String fromDateStr, String toDateStr, String tagStr, int pageNumber, int tableRowsToDisplay) throws Exception {
        logger.info("Fetching reservations with filters. From Date: {}, To Date: {}, Tag: {}", fromDateStr, toDateStr, tagStr);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fromDate = null;
        Date toDate = null;

        // Parse dates
        if (fromDateStr != null && fromDateStr.trim().length() > 0) {
            fromDate = sdf.parse(fromDateStr);
            logger.info("Parsed from date: {}", fromDate);
        }

        if (toDateStr != null && toDateStr.trim().length() > 0) {
            toDate = sdf.parse(toDateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(toDate);
            cal.add(Calendar.DAY_OF_YEAR, 1); // Adjust to include the next day
            toDate = cal.getTime();
            logger.info("Parsed to date: {}", toDate);
        }

        if (fromDate != null && toDate == null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fromDate);
            cal.add(Calendar.DAY_OF_YEAR, 1); // Adjust to include the next day
            toDate = cal.getTime();
            logger.info("Adjusted to date to include one day: {}", toDate);
        }

        // Set pagination
        Pageable pageable = PageRequest.of(pageNumber, tableRowsToDisplay);

        // Fetch filtered reservations
        Page<Reservation> reservations = reservationRepository.findById(fromDate, toDate, tagStr, pageable);
        logger.info("Fetched {} reservations.", reservations.getTotalElements());
        return reservations;
    }
    
    public List<Reservation> getReservationsalternate(String consigneeNumber, List<String> hpgList, Integer year, Integer month) {
        if (consigneeNumber != null && !consigneeNumber.isEmpty()) {
            return reservationRepository.findByConsigneeNumber(consigneeNumber);
        } else if (hpgList != null && !hpgList.isEmpty()) {
            return reservationRepository.findByHpgIn(hpgList);
        } else if (year != null && month != null) {
            return reservationRepository.findByYearAndMonth(year, month);
        }
        return reservationRepository.findAll(); // Return all if no filters are applied
    }
}
