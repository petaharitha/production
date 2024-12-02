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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fired_brick_test_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiredBrickTestResults implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Created By cannot be null")
    private Integer createdBy;

    @NotNull(message = "Last Modified By cannot be null")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @NotNull(message = "Date cannot be null")
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotEmpty(message = "Shift cannot be empty")
    private String shift;

    @NotEmpty(message = "Lab Sample No cannot be empty")
    private String labSampleNo;

    @NotEmpty(message = "SO No cannot be empty")
    private String soNo;

    private Integer materialNo;

    @NotEmpty(message = "PO Pos No cannot be empty")
    private String poPosNo;

    private Integer recipeNo;
    
    private Integer altNo;

    private String hpg;
    
    private String pg;

    @NotEmpty(message = "Quality cannot be empty")
    private String quality;

    @NotEmpty(message = "Shape cannot be empty")
    private String shape;

    @DecimalMin(value = "0.0", message = "AP Percentage cannot be less than 0")
    private Double apPercentage;

    @DecimalMin(value = "0.0", message = "BD G/Cc cannot be less than 0")
    private Double bdGSlashCc;

    @DecimalMin(value = "0.0", message = "CCS N/MM² cannot be less than 0")
    private Double ccsNSlashMmTwo;

    @DecimalMin(value = "0.0", message = "RUL T₀₅ cannot be less than 0")
    private Double rulTZeroFive;

    @DecimalMin(value = "0.0", message = "PLC Percentage cannot be less than 0")
    private Double plcPercentage;

    @DecimalMin(value = "0.0", message = "LC Percentage cannot be less than 0")
    private Double lcPercentage;

    @DecimalMin(value = "0.0", message = "PCE OC cannot be less than 0")
    private Double pceOc;

    @DecimalMin(value = "0.0", message = "Thermal Conductivity at 500°C cannot be less than 0")
    private Double therCondFivehundredDegreesCelsius;

    @DecimalMin(value = "0.0", message = "Thermal Conductivity at 750°C cannot be less than 0")
    private Double therCondSevenfiftyDegreesCelsius;

    @DecimalMin(value = "0.0", message = "Thermal Conductivity at 1000°C cannot be less than 0")
    private Double therCondThousandDegreesCelsius;

    @DecimalMin(value = "0.0", message = "Thermal Expansion cannot be less than 0")
    private Double therExp;

    private String creepRes;
    
    private String spalling;
    
    private String coRes;
    
    private String mor;
    
    private String hmor;

    @DecimalMin(value = "0.0", message = "Al₂O₃ Percentage cannot be less than 0")
    private Double alTwoOThreePercentage;

    @DecimalMin(value = "0.0", message = "Fe₂O₃ Percentage cannot be less than 0")
    private Double feTwoOThreePercentage;

    @DecimalMin(value = "0.0", message = "TiO₂ Percentage cannot be less than 0")
    private Double tioTwoPercentage;

    @DecimalMin(value = "0.0", message = "CaO Percentage cannot be less than 0")
    private Double caoPercentage;

    @DecimalMin(value = "0.0", message = "MgO Percentage cannot be less than 0")
    private Double mgoPercentage;

    @DecimalMin(value = "0.0", message = "SiO₂ Percentage cannot be less than 0")
    private Double sioTwoPercentage;

    @DecimalMin(value = "0.0", message = "Cr₂O₃ Percentage cannot be less than 0")
    private Double crTwoOThreePercentage;

    @DecimalMin(value = "0.0", message = "P₂O₅ Percentage cannot be less than 0")
    private Double pTwoOFivePercentage;

    @DecimalMin(value = "0.0", message = "Na₂O Percentage cannot be less than 0")
    private Double naTwoOPercentage;

    @DecimalMin(value = "0.0", message = "K₂O Percentage cannot be less than 0")
    private Double kTwoOPercentage;

    @DecimalMin(value = "0.0", message = "ZrO₂ Percentage cannot be less than 0")
    private Double zroPercentage;

    @DecimalMin(value = "0.0", message = "SiC Percentage cannot be less than 0")
    private Double sicPercentage;

    private Integer status;

    private Integer approvalBy;

    private String remarks;

	public Object map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
