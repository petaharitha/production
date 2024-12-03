package com.bluepal.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluepal.entity.Mould;

@Repository
public interface MouldRepository extends JpaRepository<Mould, Long> {
    List<Mould> findByMaterialNoOrMaterialName(Long materialNo, String materialName);

	List<Mould> findByMaterialNoOrMaterialNameContaining(long materialNo, String valueOf);

	Page<Mould> findAll(Specification<Mould> spec, Pageable pageable);
}