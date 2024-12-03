package com.bluepal.repository;



import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bluepal.entity.Spares;

import io.lettuce.core.dynamic.annotation.Param;


@Repository
public interface SparesRepository extends JpaRepository<Spares, Long> {

    @Query("SELECT s FROM Spares s WHERE s.materialName LIKE %:searchText% OR s.materialGroup LIKE %:searchText%")
    List<Spares> searchSpares(String searchText);
    
    Optional<Spares> findByMaterialNo(Long materialNo);

public void deleteByMaterialNo(Long materialNo);

Page<Spares> findByMaterialGroupContainingOrMaterialNameContaining(
        String materialGroup, String materialName, PageRequest pageRequest);

List<Spares> findAll();  // Fetch all spares from the database without pagination

List<Spares> findByLastUpdatedAfter(LocalDateTime lastUpdated);


}
