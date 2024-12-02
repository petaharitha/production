package com.bpl.Production.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.Reservation;



public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	

		Page<Reservation> findById(Date fromDate, Date toDate, String tagStr, Pageable pageable);

		List<Reservation> findByHpgIn(List<String> hpgList);

		List<Reservation> findByConsigneeNumber(String consigneeNumber);

		List<Reservation> findByYearAndMonth(Integer year, Integer month);
}
