package com.supplier.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "purchasing_payment_made")
@Data
public class PurchasingPaymentMade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "po_id", nullable = false)
    private PurchasingPO purchasingPO;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private PurchasingInvoice purchasingInvoice;

    @NotBlank(message = "Date must not be empty.")
    private String date;

    @NotNull(message = "Amount must not be null.")
    private Double amount;

    @NotBlank(message = "Mode of payment must not be empty.")
    private String modeOfPayment;

    @NotBlank(message = "Created by field must not be empty.")
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @NotBlank(message = "Updated by field must not be empty.")
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Getters and Setters
}
