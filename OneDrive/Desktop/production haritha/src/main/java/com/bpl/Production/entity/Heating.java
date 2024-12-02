package com.bpl.Production.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "heating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Heating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @Column(name = "date")
    private Date date;

    @Column(name = "shift")
    private String shift;

    @Column(name = "car_no")
    private String carNo;

    @Column(name = "hpg")
    private String hpg;

    @Column(name = "material_no")
    private Integer materialNo;

    @Column(name = "so_no")
    private String soNo;

    @Column(name = "total_pcs")
    private Integer totalPcs;

    @Column(name = "loaded_pcs")
    private Integer loadedPcs;

    @Column(name = "rej_pcs")
    private Integer rejPcs;

    @Column(name = "heating_number")
    private Integer heatingNumber;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "quality")
    private String quality;

    @Column(name = "shape")
    private String shape;

    @Column(name = "pg")
    private String pg;

    @Column(name = "fgid")
    private Integer fgid;

    @Column(name = "reference_forming_number")
    private Integer referenceFormingNumber;

    @Column(name = "po_pos_no")
    private String poPosNo;
}
