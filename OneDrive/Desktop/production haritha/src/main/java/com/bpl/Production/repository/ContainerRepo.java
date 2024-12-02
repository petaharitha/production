package com.bpl.Production.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.Container;

public interface ContainerRepo  extends JpaRepository<Container, Integer>{

}
