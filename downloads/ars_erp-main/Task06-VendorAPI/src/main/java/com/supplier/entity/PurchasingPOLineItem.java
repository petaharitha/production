package com.supplier.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "purchasing_po_line_item")
@Data
public class PurchasingPOLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "po_id", nullable = false)
    private PurchasingPO purchasingPO;

    @NotBlank(message = "Item name must not be empty.")
    private String name;

    @NotBlank(message = "Unit of measure must not be empty.")
    private String unitOfMeasure;

    @NotNull(message = "Quantity must not be null.")
    private Integer quantity;

    @NotNull(message = "Rate must not be null.")
    private Double rate;

    @NotNull(message = "Amount must not be null.")
    private Double amount;

    @NotBlank(message = "Promise date must not be empty.")
    private String promiseDate;

    private String status;

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
