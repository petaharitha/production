package com.bpl.Production.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bpl.Production.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
