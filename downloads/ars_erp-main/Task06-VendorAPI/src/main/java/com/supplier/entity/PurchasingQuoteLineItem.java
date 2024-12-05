package com.supplier.entity;
//
//import java.time.LocalDateTime;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//
//@Entity
//@Table(name = "purchasing_quote_line_item")
//@Data
//public class PurchasingQuoteLineItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "quote_id", nullable = false)
//    private PurchasingQuote purchasingQuote;
//
//    @NotBlank(message = "Item name must not be empty.")
//    private String name;
//
//    @NotBlank(message = "Unit of measure must not be empty.")
//    private String unitOfMeasure;
//
//    @NotNull(message = "Quantity must not be null.")
//    private Integer quantity;
//
//    @NotNull(message = "Rate must not be null.")
//    private Double rate;
//
//    @NotNull(message = "Amount must not be null.")
//    private Double amount;
//
//    @NotBlank(message = "Promise date must not be empty.")
//    private String promiseDate;
//
//    private String status;
//
//    @NotBlank(message = "Created by field must not be empty.")
//    @Column(name = "created_by", nullable = false, updatable = false)
//    private String createdBy;
//
//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @NotBlank(message = "Updated by field must not be empty.")
//    @Column(name = "updated_by", nullable = false)
//    private String updatedBy;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at", nullable = false)
//    private LocalDateTime updatedAt;
//
//    // Getters and Setters
//}

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "purchasing_quote_line_item")
@Data
@ToString(exclude = "purchasingQuote")  // Avoid circular references in toString
@EqualsAndHashCode(exclude = "purchasingQuote")  // Exclude purchasingQuote from hashCode and equals
public class PurchasingQuoteLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false)
    @JsonBackReference  // Prevent infinite recursion during serialization
    private PurchasingQuote purchasingQuote;

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
}

