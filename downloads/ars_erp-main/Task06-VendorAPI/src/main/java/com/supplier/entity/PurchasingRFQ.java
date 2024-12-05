package com.supplier.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "purchasing_rfq")
@Data
@ToString(exclude = "lineItems")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchasingRFQ implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;;
    
    // Assuming there is a reference to Vendor
   

    //@NotBlank(message = "Date must not be empty.")
    private String date;

    //@NotBlank(message = "RFQ number must not be empty.")
    private String number;

    //@NotBlank(message = "Delivery by field must not be empty.")
    private String deliveryBy;

    //@NotBlank(message = "Status field must not be empty.")
    private String status;

    @OneToMany(mappedBy = "purchasingRFQ", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PurchasingRFQLineItem> lineItems;

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
