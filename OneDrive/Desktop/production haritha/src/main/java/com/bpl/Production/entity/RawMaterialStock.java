package com.bpl.Production.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "raw_material_stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RawMaterialStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private Integer createdBy;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "rm_no")
    private Integer rmNo;

    @Column(name = "rm_description", length = 100)
    private String rmDescription;

    @Column(name = "unit_of_measure")
    private Integer unitOfMeasure;

    @Column(name = "lead_time")
    @Temporal(TemporalType.DATE)
    private Date leadTime;

    @Column(name = "minimum_stock")
    private Double minimumStock;

    @Column(name = "rawmaterial_stock")
    private Double rawMaterialStock;
}
