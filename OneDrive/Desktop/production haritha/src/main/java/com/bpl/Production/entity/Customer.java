package com.bpl.Production.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @Column(name = "number")
    private Long number;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "address", length = 64)
    private String address;

    @Column(name = "city", length = 64)
    private String city;

    @Column(name = "state", length = 64)
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "contact_name", length = 100)
    private String contactName;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "fax", length = 15)
    private String fax;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "vat_details", length = 256)
    private String vatDetails;

    @Column(name = "ce_registration", length = 64)
    private String ceRegistration;

    @Column(name = "ce_range", length = 64)
    private String ceRange;

    @Column(name = "ce_division", length = 64)
    private String ceDivision;

    @Column(name = "other_details", length = 256)
    private String otherDetails;

    @Column(name = "remarks", length = 250)
    private String remarks;
}
