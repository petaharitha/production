package com.bpl.Production.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpl.Production.entity.RawMaterialRequirement;

@Repository
public interface RawMaterialRequirementRepository extends JpaRepository<RawMaterialRequirement, Integer> {
}
