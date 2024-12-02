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
@Table(name = "precast_test_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecastTestResults implements Serializable {

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

    private Date date;
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

    private String hpg;
    private String pg;
    private String quality;
    private String shape;

    @Column(name = "batch_no")
    private Integer batchNo;

    @Column(name = "pp_no")
    private Integer ppNo;

    @Column(name = "ap_percentage_at_threefifty_degress_celsius")
    private Double apPercentageAtThreefiftyDegressCelsius;

    @Column(name = "bd_g_slash_cc_at_threefifty_degress_celsius")
    private Double bdGSlashCcAtThreefiftyDegressCelsius;

    @Column(name = "ccs_n_slash_mm_two_at_threefifty_degress_celsius")
    private Double ccsNSlashMmTwoAtThreefiftyDegressCelsius;

    @Column(name = "lc_at_threefifty_degress_celsius")
    private Double lcAtThreefiftyDegressCelsius;

    private Integer status;

    @Column(name = "approval_by")
    private Integer approvalBy;

    private String remarks;
    
    
   

}
