package com.bpl.Production.repository;




import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bpl.Production.entity.FiredBrickTestResults;

public interface FiredBrickTestResultsRepo extends JpaRepository<FiredBrickTestResults, Integer>{
	//List<FiredBrickTestResults> findByDateBetweenAndQualityAndAltNo(Date startDate, Date endDate, String quality, String altNo);

    List<FiredBrickTestResults> findBySoNo(String soNo);


	
	@Query("SELECT f FROM FiredBrickTestResults f WHERE f.date BETWEEN :startDate AND :endDate")
	List<FiredBrickTestResults> findByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
