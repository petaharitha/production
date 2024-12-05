package com.supplier.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "purchasing_po")
@Data
public class PurchasingPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Vendor vendor;

    @NotBlank(message = "Date must not be empty.")
    private String date;

    @NotBlank(message = "PO number must not be empty.")
    private String number;

    @NotNull(message = "Amount must not be null.")
    private Double amount;

    @NotBlank(message = "Approved by field must not be empty.")
    private String approvedBy;

    private String deliveryBy;

    private String status;

    @OneToMany(mappedBy = "purchasingPO")
    private Set<PurchasingPOLineItem> lineItems;

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
