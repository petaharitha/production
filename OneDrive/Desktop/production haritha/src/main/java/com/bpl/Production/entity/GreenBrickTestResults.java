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
@Table(name = "green_brick_test_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreenBrickTestResults implements Serializable {

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

    @Column(name = "lab_sample_no")
    private String labSampleNo;

    @Column(name = "so_no")
    private String soNo;

    @Column(name = "material_no")
    private Integer materialNo;

    @Column(name = "po_pos_no")
    private String poPosNo;

    @Column(name = "recipe_no")
    private Integer recipeNo;

    @Column(name = "alt_no")
    private Integer altNo;

    @Column(name = "hpg")
    private String hpg;

    @Column(name = "pg")
    private String pg;

    @Column(name = "quality")
    private String quality;

    @Column(name = "shape")
    private String shape;

    @Column(name = "free_iron")
    private Double freeIron;

    @Column(name = "press_no")
    private Integer pressNo;

    @Column(name = "moisture_percentage")
    private Double moisturePercentage;

    @Column(name = "lessthan_three_fifteen_mm")
    private Double lessthanThreeFifteenMM;

    @Column(name = "lessthan_two_mm")
    private Double lessthanTwoMM;

    @Column(name = "lessthan_one_mm")
    private Double lessthanOneMM;

    @Column(name = "lessthan_zero_fifty_mm")
    private Double lessthanZeroFiftyMM;

    @Column(name = "lessthan_zero_twenty_mm")
    private Double lessthanZeroTwentyMM;

    @Column(name = "lessthan_zero_onehundredsix_mm")
    private Double lessthanZeroOnehundredsixMM;

    @Column(name = "lessthan_zero_nine_mm")
    private Double lessthanZeroNineMM;

    @Column(name = "gbd_gm_slash_cc")
    private Double gbdGmSlashCC;

    @Column(name = "status")
    private Integer status;

    @Column(name = "approval_by")
    private Integer approvalBy;

    @Column(name = "remarks")
    private String remarks;
}
