package com.bluepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bluepal.entity.Material;


public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByMaterialNameContainingIgnoreCase(String keyword);


//	List<Material> findByMaterialNoContainingIgnoreCaseOrMaterialNameContainingIgnoreCase(Long materialNo, String materialName);

}