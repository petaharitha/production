package com.bpl.Production.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
@Table(name = "container")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Container implements Serializable{

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

    @Column(name = "so_no", length = 20)
    private String soNo;

    @Column(name = "are_number", length = 20)
    private String areNumber;

    @Column(name = "number")
    private String number;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "excise_invoice", length = 20)
    private String exciseInvoice;

    @Column(name = "excise_invoice_date")
    private Date exciseInvoiceDate;

    @Column(name = "commercial_invoice", length = 20)
    private String commercialInvoice;

    @Column(name = "license_number", length = 20)
    private String licenseNumber;

    @Column(name = "commercial_invoice_date")
    private Date commercialInvoiceDate;

    @Column(name = "order_info_id")
    private Integer orderInfoId;
}
