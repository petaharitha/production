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
@Table(name = "green_brick_limits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreenBrickLimits implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

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

    @Column(name = "quality")
    private String quality;

    @Column(name = "moisture_percentage_target")
    private Double moisturePercentageTarget;

    @Column(name = "moisture_percentage_minimum")
    private Double moisturePercentageMinimum;

    @Column(name = "moisture_percentage_maximum")
    private Double moisturePercentageMaximum;

    @Column(name = "lessthan_one_mm_target")
    private Double lessthanOneMMTarget;

    @Column(name = "lessthan_one_mm_minimum")
    private Double lessthanOneMMMinimum;

    @Column(name = "lessthan_one_mm_maximum")
    private Double lessthanOneMMMaximum;

    @Column(name = "lessthan_zero_onehundredsix_mm_target")
    private Double lessthanZeroOnehundredsixMMTarget;

    @Column(name = "lessthan_zero_onehundredsix_mm_minimum")
    private Double lessthanZeroOnehundredsixMMMinimum;

    @Column(name = "lessthan_zero_onehundredsix_mm_maximum")
    private Double lessthanZeroOnehundredsixMMMaximum;

    @Column(name = "gbd_gm_slash_cc_target")
    private Double gbdGmSlashCCTarget;

    @Column(name = "gbd_gm_slash_cc_minimum")
    private Double gbdGmSlashCCMinimum;

    @Column(name = "gbd_gm_slash_cc_maximum")
    private Double gbdGmSlashCCMaximum;

}
