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
@Table(name = "crushing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crushing implements Serializable{

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

    @Column(name = "date", length = 19)
    private Date date;

    @Column(name = "shift", length = 54)
    private String shift;

    @Column(name = "batch_no")
    private Integer batchNo;

    @Column(name = "material_no")
    private Integer materialNo;

    @Column(name = "size", length = 24)
    private String size;

    @Column(name = "silo_no")
    private Integer siloNo;

    @Column(name = "mt")
    private Double mt;

    @Column(name = "remarks", length = 256)
    private String remarks;

    @Column(name = "material_name", length = 250)
    private String materialName;
}
