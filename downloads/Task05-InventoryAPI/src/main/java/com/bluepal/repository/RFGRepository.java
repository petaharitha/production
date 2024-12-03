package com.bluepal.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.bluepal.entity.RFG;


@Repository
public interface RFGRepository extends JpaRepository<RFG, Integer>, JpaSpecificationExecutor<RFG> {
    Optional<RFG> findByProductNo(Integer productNo);
    Optional<RFG> findByProductName(String productName);
//    Page<RFG> findAll(Pageable pageable);
//    List<RFG> findByProductNoContainingIgnoreCaseOrProductNameContainingIgnoreCase(String searchCriteria, String productName);
    List<RFG> findByProductNoOrProductNameContainingIgnoreCase(Integer productNo, String productName);

}