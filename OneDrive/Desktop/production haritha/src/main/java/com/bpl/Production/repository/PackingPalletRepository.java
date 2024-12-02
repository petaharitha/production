package com.bpl.Production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.PackingPallet;

public interface PackingPalletRepository extends JpaRepository<PackingPallet, Integer>{
	List<PackingPallet> findByHpg(String hpg);

}
