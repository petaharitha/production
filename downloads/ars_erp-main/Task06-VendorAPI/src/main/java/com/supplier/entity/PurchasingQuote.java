package com.supplier.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//import java.time.LocalDateTime;
//import java.util.Set;
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
//@Table(name = "purchasing_quote")
//@Data
//public class PurchasingQuote {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(cascade = CascadeType.ALL) // Ensure correct cascade type if needed
//    @JoinColumn(name = "supplier_id", nullable = false)
//    private Supplier supplier;
//
//    @NotBlank(message = "Date must not be empty.")
//    private String date;
//
//    @NotBlank(message = "Quote number must not be empty.")
//    private String number;
//
//    @NotNull(message = "Amount must not be null.")
//    private Double amount;
//
//    @NotBlank(message = "Approved by field must not be empty.")
//    private String approvedBy;
//
//    private String status;
//
//    @OneToMany(mappedBy = "purchasingQuote", cascade = CascadeType.ALL)
//    private Set<PurchasingQuoteLineItem> lineItems;
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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "purchasing_quote")
@Data
@ToString(exclude = "lineItems")  // Avoid circular references in toString
@EqualsAndHashCode(exclude = "lineItems")  // Exclude lineItems from hashCode and equals
public class PurchasingQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Vendor vendor;

    @NotBlank(message = "Date must not be empty.")
    private String date;

    @NotBlank(message = "Quote number must not be empty.")
    private String number;

    @NotNull(message = "Amount must not be null.")
    private Double amount;

    @NotBlank(message = "Approved by field must not be empty.")
    private String approvedBy;

    private String status;

    @OneToMany(mappedBy = "purchasingQuote", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Prevent infinite recursion during serialization
    private Set<PurchasingQuoteLineItem> lineItems = new HashSet<>();
    
 // Getter for lineItems
    public Set<PurchasingQuoteLineItem> getLineItems() {
        return lineItems;
    }

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
