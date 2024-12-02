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
@Table(name = "inspection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inspection implements Serializable {

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

    @Column(name = "hpg")
    private String hpg;

    @Column(name = "material_no")
    private Integer materialNo;

    @Column(name = "so_no")
    private String soNo;

    @Column(name = "pcs_ul")
    private Integer pcsUL;

    @Column(name = "corner_breaks")
    private Integer cornerBreaks;

    @Column(name = "iron_spots")
    private Integer ironSpots;

    @Column(name = "body_cracks")
    private Integer bodyCracks;

    @Column(name = "burnt_rejns")
    private Integer burntRejns;

    @Column(name = "warphase")
    private Integer warphase;

    @Column(name = "low_melting_spots")
    private Integer lowMeltingSpots;

    @Column(name = "size_variations")
    private Integer sizeVariations;

    @Column(name = "laminations")
    private Integer laminations;

    @Column(name = "excess")
    private Integer excess;

    @Column(name = "symmetry")
    private Integer symmetry;

    @Column(name = "taper")
    private Integer taper;

    @Column(name = "lab_sample")
    private Integer labSample;

    @Column(name = "other_rejns")
    private Integer otherRejns;

    @Column(name = "soft")
    private Integer soft;

    @Column(name = "good_pcs")
    private Integer goodPcs;

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

    @Column(name = "totrej")
    private Integer totrej;

    @Column(name = "quality_number")
    private Integer qualityNumber;

    @Column(name = "po_pos_no")
    private String poPosNo;

    @Column(name = "marketing_clearance")
    private Integer marketingClearance;

    @Column(name = "excess_moved_to_free_stock")
    private Integer excessMovedToFreeStock;
}
