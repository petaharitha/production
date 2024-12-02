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
@Table(name = "consignee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyPlanning implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate; 

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @Column(name = "s0_no", length = 64)
    private String soNo;
    private String materialNo;
    private String poPosNo;
    private String hpg;
    private String pg;
    private String quality;
    private String shape;

    private Integer plannedPcs;
    private Double plannedQuantity;

    private Date mrwDate;
    private Integer plannedYear;
    private Integer plannedMonth;

    private Integer equipmentId;
    private String equipment;
    private String sequence;

    private Integer workingStatus;
    private String consigneeName;

}