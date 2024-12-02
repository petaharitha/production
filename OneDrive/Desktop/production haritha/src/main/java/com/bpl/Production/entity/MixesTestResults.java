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
@Table(name = "mixes_test_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MixesTestResults implements Serializable {

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
    private String labSampleNo;
    private String soNo;
    private Integer materialNo;
    private String poPosNo;
    private Integer recipeNo;
    private Integer altNo;
    private String hpg;
    private String pg;
    private String quality;
    private Integer sBatchNo;
    private Integer eBatchNo;
    private Double qntyMt;
    private String freeIron;
    private Double moisturePercentage;
    private Double lessthanTwelveMM;
    private Double lessthanSixThreeMM;
    private Double lessthanFiveSixMM;
    private Double lessthanThreeFifteenMM;
    private Double lessthanOneMM;
    private Double lessthanZeroSixtythreeMM;
    private Integer airTemperature;
    private Integer waterTemperature;
    private Integer dryMixTemperature;
    private Integer wetMixTemperature;
    private Integer dryMixTime;
    private Integer wetMixTime;
    private Double waterPercentage;
    private Double flowImmd;
    private Double flowAfterTwentyMin;
    private Double twentyfiveDegreesCelsius;
    private Double thirtyDegreesCelsius;
    private Double apPercentageAtOneTenDegressCelsius;
    private Double bdGSlashCcAtOneTenDegressCelsius;
    private Double ccsNSlashMmTwoAtOneTenDegressCelsius;
    private Double lcAtOneTenDegressCelsius;
    private Double apPercentageAtThousandDegressCelsius;
    private Double bdGSlashCcAtThousandDegressCelsius;
    private Double ccsNSlashMmTwoAtThousandDegressCelsius;
    private Double lcAtThousandDegressCelsius;
    private Double alTwoOThreePercentage;
    private Double feTwoOThreePercentage;
    private Double tioTwoPercentage;
    private Double caoPercentage;
    private Double mgoPercentage;
    private Double sioTwoPercentage;
    private Double crTwoOThreePercentage;
    private Double pTwoOFivePercentage;
    private Double naTwoOPercentage;
    private Double kTwoOPercentage;
    private Double loiPercentage;
    private Double cPercentage;
    private Double sicPercentage;
    private Integer status;
    private Double lessthanFourMM;
    private Double lessthanZeroFiftyMM;
    private Double lessthanZerothreefifteenMM;
    private Double ccsNSlashMmEightFifteenDegressCelsius;

    private Integer approvalBy;
    private String remarks;
}
