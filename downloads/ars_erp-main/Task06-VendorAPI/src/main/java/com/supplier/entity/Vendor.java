package com.supplier.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "vendor")
@Data
public class Vendor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<PurchasingRFQ> purchaseRFQs;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vendor Details
    //@NotNull(message = "Vendor ID No is required")
    @Pattern(regexp = "^[0-9]+$", message = "Vendor ID No must be numeric")
    private String vendorIdNo;

   // @NotBlank(message = "Vendor Name is required")
    private String vendorName;

    //@NotBlank(message = "Address Line 1 is required")
    private String addressLine1;

   // @NotBlank(message = "Address Line 2 is required")
    private String addressLine2;

    //@NotBlank(message = "Address Line 3 is required")
    private String addressLine3;

    //@NotBlank(message = "Village / City is required")
    private String city;

    //@NotBlank(message = "District is required")
    private String district;

   // @NotBlank(message = "State is required")
    private String state;

    //@NotBlank(message = "Country is required")
    private String country;

    //@NotNull(message = "Pin Code is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pin Code must be 6 digits")
    private String pinCode;
    
    @JsonIgnore
    //@NotBlank(message = "PAN Number is required")
   // @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN Number format")
    private String panNumber;

    //@NotBlank(message = "CIN Number is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Invalid CIN Number format")
    private String cinNumber;

    //@NotBlank(message = "GST Number is required")
    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[A-Z]{1}[0-9]{1}$", message = "Invalid GST Number format")
    private String gstNumber;

    //@NotBlank(message = "MSME is required")
    private String msmE;

    @Column(name = "spare_field")
    private String spareField;

    // Sales Representative Details
   // @NotBlank(message = "Sales Rep First Name is required")
    private String salesRepFirstName;

    @Column(name = "sales_rep_middle_name")
    private String salesRepMiddleName;

   // @NotBlank(message = "Sales Rep Last Name is required")
    private String salesRepLastName;

    //@NotBlank(message = "Sales Rep Mobile No 1 is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Sales Rep Mobile No 1 must be 10 digits")
    private String salesRepMobileNo1;

    @Column(name = "sales_mobile_no_2")
    private String salesMobileNo2;

    //@NotBlank(message = "Sales Rep Email 1 is required")
    @Email(message = "Invalid email format for Sales Rep Email 1")
    private String salesRepEmail1;

    @Column(name = "sales_rep_email_2")
    private String salesRepEmail2;

    // Accounts Representative Details
    //@NotBlank(message = "Accounts Rep First Name is required")
    private String accountsRepFirstName;

    @Column(name = "accounts_rep_middle_name")
    private String accountsRepMiddleName;

    //@NotBlank(message = "Accounts Rep Last Name is required")
    private String accountsRepLastName;
    
   // @NotBlank(message = "Accounts Rep Mobile No 1 is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Accounts Rep Mobile No 1 must be 10 digits")
    private String accountsRepMobileNo1;

    @Column(name = "accounts_mobile_no_2")
    private String accountsMobileNo2;

    //@NotBlank(message = "Accounts Rep Email 1 is required")
    @Email(message = "Invalid email format for Accounts Rep Email 1")
    private String accountsRepEmail1;

    @Column(name = "accounts_rep_email_2")
    private String accountsRepEmail2;

    // Logistics Representative Details
    @Column(name = "logistics_rep_first_name")
    private String logisticsRepFirstName;

    @Column(name = "logistics_rep_middle_name")
    private String logisticsRepMiddleName;

    @Column(name = "logistics_rep_last_name")
    private String logisticsRepLastName;

    @Column(name = "logistics_rep_mobile_no_1")
    private String logisticsRepMobileNo1;

    @Column(name = "logistics_mobile_no_2")
    private String logisticsMobileNo2;

    @Column(name = "logistics_rep_email_1")
    private String logisticsRepEmail1;

    @Column(name = "logistics_rep_email_2")
    private String logisticsRepEmail2;
    
    //@NotBlank(message = "Created by field must not be empty.")
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

   // @NotBlank(message = "Updated by field must not be empty.")
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
   
    //PDF Details
//    @JsonIgnore
//    @Lob
//    @Column(name = "pan_number_pdf")
    private Blob panNumberPdf;
    
//    @JsonIgnore
//    @Lob
//    @Column(name = "cin_number_pdf")
    private Blob cinNumberPdf;
    
//    @JsonIgnore
//    @Lob
//    @Column(name = "gst_number_pdf")
    private Blob gstNumberPdf;
    
//    @JsonIgnore
//    @Lob
//    @Column(name = "msme_pdf")
    private Blob msmePdf;

}
