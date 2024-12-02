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
@Table(name = "precast_limits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecastLimits implements Serializable {

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
    
    @Column(name = "validate_from")
    private Date validateFrom;
    
    @Column(name = "expired_from")
    private Date expiredFrom;
    
    @Column(name = "recipe_no")
    private Integer recipeNo;
    
    private String quality;

    @Column(name = "ap_percentage_at_threefifty_degress_celsius_target")
    private Double apPercentageAtThreefiftyDegressCelsiusTarget;

    @Column(name = "ap_percentage_at_threefifty_degress_celsius_maximum")
    private Double apPercentageAtThreefiftyDegressCelsiusMaximum;

    @Column(name = "ap_percentage_at_threefifty_degress_celsius_minimum")
    private Double apPercentageAtThreefiftyDegressCelsiusMinimum;

    @Column(name = "bd_g_slash_cc_at_threefifty_degress_celsius_target")
    private Double bdGSlashCcAtThreefiftyDegressCelsiusTarget;

    @Column(name = "bd_g_slash_cc_at_threefifty_degress_celsius_maximum")
    private Double bdGSlashCcAtThreefiftyDegressCelsiusMaximum;

    @Column(name = "bd_g_slash_cc_at_threefifty_degress_celsius_minimum")
    private Double bdGSlashCcAtThreefiftyDegressCelsiusMinimum;

    @Column(name = "ccs_n_slash_mm_two_at_threefifty_degress_celsius_target")
    private Double ccsNSlashMmTwoAtThreefiftyDegressCelsiusTarget;

    @Column(name = "ccs_n_slash_mm_two_at_threefifty_degress_celsius_maximum")
    private Double ccsNSlashMmTwoAtThreefiftyDegressCelsiusMaximum;

    @Column(name = "ccs_n_slash_mm_two_at_threefifty_degress_celsius_minimum")
    private Double ccsNSlashMmTwoAtThreefiftyDegressCelsiusMinimum;


}
