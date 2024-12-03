package com.bluepal.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bluepal.entity.Consumable;

public interface ConsumableRepository extends JpaRepository<Consumable, Long>, JpaSpecificationExecutor<Consumable> {

	Optional<Consumable> findByMaterialNo(Long materialNo);
    // JpaSpecificationExecutor enables searching and sorting capabilities

}
