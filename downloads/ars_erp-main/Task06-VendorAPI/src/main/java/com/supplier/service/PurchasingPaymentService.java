package com.supplier.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.supplier.entity.PurchasingInvoice;
import com.supplier.entity.PurchasingPO;
import com.supplier.entity.PurchasingPaymentMade;
import com.supplier.entity.Vendor;
import com.supplier.exception.ResourceNotFoundException;
import com.supplier.repository.PurchasingInvoiceRepository;
import com.supplier.repository.PurchasingPORepository;
import com.supplier.repository.PurchasingPaymentRepository;
import com.supplier.repository.VendorRepository;

@Service
public class PurchasingPaymentService {

    // Logger for this class
    private static final Logger logger = LoggerFactory.getLogger(PurchasingPaymentService.class);
    
    @Autowired
    private VendorRepository vendorRepository;
    
    @Autowired
    private PurchasingPORepository purchasingPORepository;
    
    @Autowired
    private PurchasingInvoiceRepository purchasingInvoiceRepository;

    @Autowired
    private PurchasingPaymentRepository paymentRepository;

    public PurchasingPaymentMade createPayment(PurchasingPaymentMade payment) {
        // Here, you should ensure that the related entities are fetched and set properly
        if (payment.getVendor() != null) {
        	Vendor vendor = vendorRepository.findById(payment.getVendor().getId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            payment.setVendor(vendor);
        }
        if (payment.getPurchasingPO() != null) {
            PurchasingPO po = purchasingPORepository.findById(payment.getPurchasingPO().getId())
                    .orElseThrow(() -> new RuntimeException("PurchasingPO not found"));
            payment.setPurchasingPO(po);
        }
        if (payment.getPurchasingInvoice() != null) {
            PurchasingInvoice invoice = purchasingInvoiceRepository.findById(payment.getPurchasingInvoice().getId())
                    .orElseThrow(() -> new RuntimeException("PurchasingInvoice not found"));
            payment.setPurchasingInvoice(invoice);
        }
        return paymentRepository.save(payment);
    }


    @Cacheable(value = "payments")
    public List<PurchasingPaymentMade> getAllPayments() {
        logger.info("Fetching all purchasing payments.");
        return paymentRepository.findAll();
    }

    @Cacheable(value = "payments", key = "#id")
    public PurchasingPaymentMade getPaymentById(Long id) {
        logger.info("Fetching purchasing payment with ID: {}", id);
        return paymentRepository.findById(id).orElseThrow(() -> {
            logger.error("Purchasing payment with ID: {} not found.", id);
            return new ResourceNotFoundException("Purchasing Payment not found with id: " + id);
        });
    }

    @CacheEvict(value = "payments", key = "#id")
    public PurchasingPaymentMade updatePayment(Long id, PurchasingPaymentMade paymentDetails) {
        logger.info("Updating purchasing payment with ID: {}", id);
        PurchasingPaymentMade payment = getPaymentById(id);

        payment.setDate(paymentDetails.getDate());
        payment.setAmount(paymentDetails.getAmount());
        payment.setModeOfPayment(paymentDetails.getModeOfPayment());

        PurchasingPaymentMade updatedPayment = paymentRepository.save(payment);
        logger.info("Purchasing payment with ID: {} updated successfully.", updatedPayment.getId());
        return updatedPayment;
    }

    @CacheEvict(value = "payments", key = "#id")
    public void deletePayment(Long id) {
        logger.info("Deleting purchasing payment with ID: {}", id);
        PurchasingPaymentMade payment = getPaymentById(id);
        paymentRepository.delete(payment);
        logger.info("Purchasing payment with ID: {} deleted successfully.", id);
    }
}
