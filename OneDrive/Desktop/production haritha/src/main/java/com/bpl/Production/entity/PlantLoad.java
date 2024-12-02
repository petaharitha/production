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
@Table(name = "plantload")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantLoad implements Serializable {

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
    
    private String type;
    private String number;
    private String materialno;
    private Integer itemid;
    private String hpg;
    private String pg;
    private String quality;
    private String shape;
    private Double quantityinmt;
    private Double quantity;
    private Integer year;
    private Integer month;
    private String remarks;
    
    @Column(name = "poPosNo")
    private String poPosNo;
    
    private Integer totalPcs;
    private Integer plannedPcs;
    
    @Column(name = "originalPlantLoadId")
    private Integer originalPlantLoadId;
    
    @Column(name = "originalPlannedPcs")
    private Integer originalPlannedPcs;
    
    private Integer recipeNo;
    private Integer altNo;
    
    private String consigneeName;
    private String consigneeNumber;
    
    private String plantName;
    private String loadType;
}
