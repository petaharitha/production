package com.bluepal.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluepal.entity.PackingMaterial;


@Repository
public interface PackingMaterialRepository extends JpaRepository<PackingMaterial, Long> {

    // Paginated search based on materialGroup, materialNo, and materialName
    Page<PackingMaterial> findByMaterialGroupContainingAndMaterialNoContainingAndMaterialNameContaining(
            String materialGroup, String materialNo, String materialName, Pageable pageable);

    // Non-paginated search based on materialGroup, materialNo, and materialName
    List<PackingMaterial> findByMaterialGroupContainingAndMaterialNoContainingAndMaterialNameContaining(
            String materialGroup, String materialNo, String materialName);

	Page<PackingMaterial> findByMaterialNoContainingOrMaterialGroupContainingOrMaterialNameContaining(Object object,
			Object object2, Object object3, Pageable pageable);

	
	List<PackingMaterial> findByMaterialNoContainingOrMaterialNameContaining(String materialNo, String materialName);


}