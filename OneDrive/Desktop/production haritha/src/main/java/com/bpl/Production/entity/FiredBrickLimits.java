package com.bpl.Production.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fired_brick_limits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiredBrickLimits implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming the primary key is auto-generated
    private Integer id;

    @NotNull(message = "Created by is required")
    private Integer createdBy;

    @NotNull(message = "Last modified by is required")
    private Integer lastModifiedBy;

    @NotNull(message = "Created date is required")
    private LocalDateTime createdDate;

    @NotNull(message = "Last modified date is required")
    private LocalDateTime lastModifiedDate;

    @NotNull(message = "Validate from date is required")
    private Date validateFrom;

    @NotNull(message = "Expired from date is required")
    private Date expiredFrom;

    @NotNull(message = "Recipe number is required")
    private Integer recipeNo;

    @NotBlank(message = "Quality is required")
    private String quality;

    @DecimalMin(value = "0.0", message = "AP Percentage Target must be greater than or equal to 0")
    private Double apPercentageTarget;

    @DecimalMin(value = "0.0", message = "AP Percentage Minimum must be greater than or equal to 0")
    private Double apPercentageMinimum;

    @DecimalMin(value = "0.0", message = "AP Percentage Maximum must be greater than or equal to 0")
    private Double apPercentageMaximum;

    @DecimalMin(value = "0.0", message = "BD G/Slash CC Target must be greater than or equal to 0")
    private Double bdGSlashCcTarget;

    @DecimalMin(value = "0.0", message = "BD G/Slash CC Minimum must be greater than or equal to 0")
    private Double bdGSlashCcMinimum;

    @DecimalMin(value = "0.0", message = "BD G/Slash CC Maximum must be greater than or equal to 0")
    private Double bdGSlashCcMaximum;

    @DecimalMin(value = "0.0", message = "CCS N/Slash MM2 Target must be greater than or equal to 0")
    private Double ccsNSlashMmTwoTarget;

    @DecimalMin(value = "0.0", message = "CCS N/Slash MM2 Minimum must be greater than or equal to 0")
    private Double ccsNSlashMmTwoMinimum;

    @DecimalMin(value = "0.0", message = "CCS N/Slash MM2 Maximum must be greater than or equal to 0")
    private Double ccsNSlashMmTwoMaximum;

    @DecimalMin(value = "0.0", message = "RUL T/Zero Five Target must be greater than or equal to 0")
    private Double rulTZeroFiveTarget;

    @DecimalMin(value = "0.0", message = "RUL T/Zero Five Minimum must be greater than or equal to 0")
    private Double rulTZeroFiveMinimum;

    @DecimalMin(value = "0.0", message = "RUL T/Zero Five Maximum must be greater than or equal to 0")
    private Double rulTZeroFiveMaximum;

    @DecimalMin(value = "0.0", message = "PLC Percentage Target must be greater than or equal to 0")
    private Double plcPercentageTarget;

    @DecimalMin(value = "0.0", message = "PLC Percentage Minimum must be greater than or equal to 0")
    private Double plcPercentageMinimum;

    @DecimalMin(value = "0.0", message = "PLC Percentage Maximum must be greater than or equal to 0")
    private Double plcPercentageMaximum;

    @DecimalMin(value = "0.0", message = "Al2O3 Percentage Target must be greater than or equal to 0")
    private Double alTwoOThreePercentageTarget;

    @DecimalMin(value = "0.0", message = "Al2O3 Percentage Minimum must be greater than or equal to 0")
    private Double alTwoOThreePercentageMinimum;

    @DecimalMin(value = "0.0", message = "Al2O3 Percentage Maximum must be greater than or equal to 0")
    private Double alTwoOThreePercentageMaximum;

    @DecimalMin(value = "0.0", message = "Fe2O3 Percentage Target must be greater than or equal to 0")
    private Double feTwoOThreePercentageTarget;

    @DecimalMin(value = "0.0", message = "Fe2O3 Percentage Minimum must be greater than or equal to 0")
    private Double feTwoOThreePercentageMinimum;

    @DecimalMin(value = "0.0", message = "Fe2O3 Percentage Maximum must be greater than or equal to 0")
    private Double feTwoOThreePercentageMaximum;

    @DecimalMin(value = "0.0", message = "TiO2 Percentage Target must be greater than or equal to 0")
    private Double tioTwoPercentageTarget;

    @DecimalMin(value = "0.0", message = "TiO2 Percentage Minimum must be greater than or equal to 0")
    private Double tioTwoPercentageMinimum;

    @DecimalMin(value = "0.0", message = "TiO2 Percentage Maximum must be greater than or equal to 0")
    private Double tioTwoPercentageMaximum;

    @DecimalMin(value = "0.0", message = "SiO2 Percentage Target must be greater than or equal to 0")
    private Double sioTwoPercentageTarget;

    @DecimalMin(value = "0.0", message = "SiO2 Percentage Minimum must be greater than or equal to 0")
    private Double sioTwoPercentageMinimum;

    @DecimalMin(value = "0.0", message = "SiO2 Percentage Maximum must be greater than or equal to 0")
    private Double sioTwoPercentageMaximum;

    @DecimalMin(value = "0.0", message = "Cr2O3 Percentage Target must be greater than or equal to 0")
    private Double crTwoOThreePercentageTarget;

    @DecimalMin(value = "0.0", message = "Cr2O3 Percentage Minimum must be greater than or equal to 0")
    private Double crTwoOThreePercentageMinimum;

    @DecimalMin(value = "0.0", message = "Cr2O3 Percentage Maximum must be greater than or equal to 0")
    private Double crTwoOThreePercentageMaximum;

    @DecimalMin(value = "0.0", message = "MgO Percentage Target must be greater than or equal to 0")
    private Double mgoPercentageTarget;

    @DecimalMin(value = "0.0", message = "MgO Percentage Minimum must be greater than or equal to 0")
    private Double mgoPercentageMinimum;

    @DecimalMin(value = "0.0", message = "MgO Percentage Maximum must be greater than or equal to 0")
    private Double mgoPercentageMaximum;

    @DecimalMin(value = "0.0", message = "P2O5 Percentage Target must be greater than or equal to 0")
    private Double pTwoOFivePercentageTarget;

    @DecimalMin(value = "0.0", message = "P2O5 Percentage Minimum must be greater than or equal to 0")
    private Double pTwoOFivePercentageMinimum;

    @DecimalMin(value = "0.0", message = "P2O5 Percentage Maximum must be greater than or equal to 0")
    private Double pTwoOFivePercentageMaximum;

    @DecimalMin(value = "0.0", message = "CaO Percentage Target must be greater than or equal to 0")
    private Double caoPercentageTarget;

    @DecimalMin(value = "0.0", message = "CaO Percentage Minimum must be greater than or equal to 0")
    private Double caoPercentageMinimum;

    @DecimalMin(value = "0.0", message = "CaO Percentage Maximum must be greater than or equal to 0")
    private Double caoPercentageMaximum;

    @DecimalMin(value = "0.0", message = "K2O Percentage Target must be greater than or equal to 0")
    private Double kTwoOPercentageTarget;

    @DecimalMin(value = "0.0", message = "K2O Percentage Minimum must be greater than or equal to 0")
    private Double kTwoOPercentageMinimum;

    @DecimalMin(value = "0.0", message = "K2O Percentage Maximum must be greater than or equal to 0")
    private Double kTwoOPercentageMaximum;

    @DecimalMin(value = "0.0", message = "Na2O Percentage Target must be greater than or equal to 0")
    private Double naTwoOPercentageTarget;

    @DecimalMin(value = "0.0", message = "Na2O Percentage Minimum must be greater than or equal to 0")
    private Double naTwoOPercentageMinimum;

    @DecimalMin(value = "0.0", message = "Na2O Percentage Maximum must be greater than or equal to 0")
    private Double naTwoOPercentageMaximum;

    @DecimalMin(value = "0.0", message = "ZrO2 Percentage Target must be greater than or equal to 0")
    private Double zroPercentageTarget;

    @DecimalMin(value = "0.0", message = "ZrO2 Percentage Minimum must be greater than or equal to 0")
    private Double zroPercentageMinimum;

    @DecimalMin(value = "0.0", message = "ZrO2 Percentage Maximum must be greater than or equal to 0")
    private Double zroPercentageMaximum;

    @DecimalMin(value = "0.0", message = "SiC Percentage Target must be greater than or equal to 0")
    private Double sicPercentageTarget;

    @DecimalMin(value = "0.0", message = "SiC Percentage Minimum must be greater than or equal to 0")
    private Double sicPercentageMinimum;

    @DecimalMin(value = "0.0", message = "SiC Percentage Maximum must be greater than or equal to 0")
    private Double sicPercentageMaximum;

}
