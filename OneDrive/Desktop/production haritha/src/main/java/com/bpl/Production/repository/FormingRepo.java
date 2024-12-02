package com.bpl.Production.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.Forming;

public interface FormingRepo extends JpaRepository<Forming, Integer>{
    List<Forming> findByHpgInAndShiftInAndDateBetween(List<String> hpgList, List<String> shiftList, Date fromDate, Date toDate);

	List<Forming> findByDateBetween(Date fromDate, Date toDate);


}
