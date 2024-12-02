package com.bpl.Production.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.PrecastTestResults;

public interface PrecastTestResultsRepository extends JpaRepository<PrecastTestResults, Integer>{
    List<PrecastTestResults> findByDateBetweenAndQualityAndAltNo(Date fromDate, Date toDate, String quality, Integer altNo);
    List<PrecastTestResults> findBySoNo(String soNo);
}
