package com.bpl.Production.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpl.Production.entity.Customer;
import com.bpl.Production.repository.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    // Create or update a customer
    public Customer saveCustomer(Customer customer) {
        logger.info("Attempting to save or update customer: {}", customer);
        return customerRepo.save(customer);
    }

    // Get a customer by ID
    public Optional<Customer> getCustomerById(Integer id) {
        logger.info("Fetching customer by ID: {}", id);
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            logger.info("Customer found: {}", customer.get());
        } else {
            logger.warn("Customer with ID {} not found", id);
        }
        return customer;
    }

    // Update an existing customer by ID
    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        logger.info("Attempting to update customer with ID: {}", id);
        Optional<Customer> existingCustomerOpt = customerRepo.findById(id);
        
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            logger.info("Updating customer with ID: {}", id);
            
            existingCustomer.setCreatedBy(updatedCustomer.getCreatedBy());
            existingCustomer.setLastModifiedBy(updatedCustomer.getLastModifiedBy());
            existingCustomer.setCreatedDate(updatedCustomer.getCreatedDate());
            existingCustomer.setLastModifiedDate(updatedCustomer.getLastModifiedDate());
            existingCustomer.setNumber(updatedCustomer.getNumber());
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setCity(updatedCustomer.getCity());
            existingCustomer.setState(updatedCustomer.getState());
            existingCustomer.setZip(updatedCustomer.getZip());
            existingCustomer.setContactName(updatedCustomer.getContactName());
            existingCustomer.setPhone(updatedCustomer.getPhone());
            existingCustomer.setFax(updatedCustomer.getFax());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setVatDetails(updatedCustomer.getVatDetails());
            existingCustomer.setCeRegistration(updatedCustomer.getCeRegistration());
            existingCustomer.setCeRange(updatedCustomer.getCeRange());
            existingCustomer.setCeDivision(updatedCustomer.getCeDivision());
            existingCustomer.setOtherDetails(updatedCustomer.getOtherDetails());
            existingCustomer.setRemarks(updatedCustomer.getRemarks());
            
            return customerRepo.save(existingCustomer);
        } else {
            logger.warn("Customer with ID {} not found, update failed", id);
            return null; // Handle case where entity is not found
        }
    }

    // Delete customer by ID
    public boolean deleteCustomer(Integer id) {
        logger.info("Attempting to delete customer with ID: {}", id);
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            customerRepo.delete(customer.get());
            logger.info("Successfully deleted customer with ID: {}", id);
            return true;
        } else {
            logger.warn("Customer with ID {} not found, delete failed", id);
            return false;
        }
    }

    // Get all customers
    public Iterable<Customer> getAllCustomers() {
        logger.info("Fetching all customers");
        return customerRepo.findAll();
    }
}
