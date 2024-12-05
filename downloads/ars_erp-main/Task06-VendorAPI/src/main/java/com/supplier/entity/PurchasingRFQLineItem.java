package com.supplier.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "purchasing_rfq_line_item")
@Data
public class PurchasingRFQLineItem implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rfq_id", nullable = false)
    private PurchasingRFQ purchasingRFQ;

    //@NotBlank(message = "Item name must not be empty.")
    private String name;

    //@NotBlank(message = "Unit of measure must not be empty.")
    private String unitOfMeasure;

   // @NotNull(message = "Quantity must not be null.")
    private Integer quantity;

    //@NotBlank(message = "Delivery by field must not be empty.")
    private String deliveryBy;

    //@NotBlank(message = "Created by field must not be empty.")
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //@NotBlank(message = "Updated by field must not be empty.")
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    @Version
    private Long version;

    // Getters and Setters
}
