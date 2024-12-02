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
@Table(name = "mixes_limits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MixesLimits {

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

    private Date validateFrom;
    
    private Date expiredFrom;

    private Integer recipeNo;
    private String quality;

    private Double lessthanTwelveMMTarget;
    private Double lessthanTwelveMMMinimum;
    private Double lessthanTwelveMMMaximum;

    private Double lessthanSixThreeMMTarget;
    private Double lessthanSixThreeMMMinimum;
    private Double lessthanSixThreeMMMaximum;

    private Double lessthanFiveSixMMTarget;
    private Double lessthanFiveSixMMMinimum;
    private Double lessthanFiveSixMMMaximum;

    private Double lessthanFourMMTarget;
    private Double lessthanFourMMMinimum;
    private Double lessthanFourMMMaximum;

    private Double lessthanThreeFifteenMMTarget;
    private Double lessthanThreeFifteenMMMinimum;
    private Double lessthanThreeFifteenMMMaximum;

    private Double lessthanOneMMTarget;
    private Double lessthanOneMMMinimum;
    private Double lessthanOneMMMaximum;

    private Double lessthanZeroFiftyMMTarget;
    private Double lessthanZeroFiftyMMMinimum;
    private Double lessthanZeroFiftyMMMaximum;

    private Double lessthanZerothreefifteenMMTarget;
    private Double lessthanZerothreefifteenMMMinimum;
    private Double lessthanZerothreefifteenMMMaximum;

    private Double lessthanZeroSixtythreeMMTarget;
    private Double lessthanZeroSixtythreeMMMinimum;
    private Double lessthanZeroSixtythreeMMMaximum;

    private Double waterPercentageTarget;
    private Double waterPercentageMinimum;
    private Double waterPercentageMaximum;

    private Double flowImmdTarget;
    private Double flowImmdMinimum;
    private Double flowImmdMaximum;

    private Double flowAfterTwentyMinTarget;
    private Double flowAfterTwentyMinMinimum;
    private Double flowAfterTwentyMinMaximum;

    private Double sTimeTwentyfiveDegreesCelsiusTarget;
    private Double sTimeTwentyfiveDegreesCelsiusMinimum;
    private Double sTimeTwentyfiveDegreesCelsiusMaximum;

    private Double sTimeThirtyDegreesCelsiusTarget;
    private Double sTimeThirtyDegreesCelsiusMinimum;
    private Double sTimeThirtyDegreesCelsiusMaximum;

    private Double apPercentageAtOneTenDegressCelsiusTarget;
    private Double apPercentageAtOneTenDegressCelsiusMinimum;
    private Double apPercentageAtOneTenDegressCelsiusMaximum;

    private Double bdGSlashCcAtOneTenDegressCelsiusTarget;
    private Double bdGSlashCcAtOneTenDegressCelsiusMinimum;
    private Double bdGSlashCcAtOneTenDegressCelsiusMaximum;

    private Double ccsNSlashMmTwoAtOneTenDegressCelsiusTarget;
    private Double ccsNSlashMmTwoAtOneTenDegressCelsiusMinimum;
    private Double ccsNSlashMmTwoAtOneTenDegressCelsiusMaximum;

    private Double lcAtOnetenDegressCelsiusTarget;
    private Double lcAtOnetenDegressCelsiusMinimum;
    private Double lcAtOnetenDegressCelsiusMaximum;

    private Double ccsNSlashMmEightFifteenDegressCelsiusTarget;
    private Double ccsNSlashMmEightFifteenDegressCelsiusMinimum;
    private Double ccsNSlashMmEightFifteenDegressCelsiusMaximum;

    private Double apPercentageAtThousandDegressCelsiusTarget;
    private Double apPercentageAtThousandDegressCelsiusMinimum;
    private Double apPercentageAtThousandDegressCelsiusMaximum;

    private Double bdGSlashCcAtThousandDegressCelsiusTarget;
    private Double bdGSlashCcAtThousandDegressCelsiusMinimum;
    private Double bdGSlashCcAtThousandDegressCelsiusMaximum;

    private Double ccsNSlashMmTwoAtThousandDegressCelsiusTarget;
    private Double ccsNSlashMmTwoAtThousandDegressCelsiusMinimum;
    private Double ccsNSlashMmTwoAtThousandDegressCelsiusMaximum;

    private Double lcAtThousandDegressCelsiusTarget;
    private Double lcAtThousandDegressCelsiusMinimum;
    private Double lcAtThousandDegressCelsiusMaximum;

    private Double alTwoOThreePercentageTarget;
    private Double alTwoOThreePercentageMinimum;
    private Double alTwoOThreePercentageMaximum;

    private Double feTwoOThreePercentageTarget;
    private Double feTwoOThreePercentageMinimum;
    private Double feTwoOThreePercentageMaximum;

    private Double tioTwoPercentageTarget;
    private Double tioTwoPercentageMinimum;
    private Double tioTwoPercentageMaximum;

    private Double caoPercentageTarget;
    private Double caoPercentageMinimum;
    private Double caoPercentageMaximum;

    private Double mgoPercentageTarget;
    private Double mgoPercentageMinimum;
    private Double mgoPercentageMaximum;

    private Double sioTwoPercentageTarget;
    private Double sioTwoPercentageMinimum;
    private Double sioTwoPercentageMaximum;

    private Double crTwoOThreePercentageTarget;
    private Double crTwoOThreePercentageMinimum;
    private Double crTwoOThreePercentageMaximum;

    private Double naTwoOPercentageTarget;
    private Double naTwoOPercentageMinimum;
    private Double naTwoOPercentageMaximum;

    private Double kTwoOPercentageTarget;
    private Double kTwoOPercentageMinimum;
    private Double kTwoOPercentageMaximum;

    private Double pTwoOFivePercentageTarget;
    private Double pTwoOFivePercentageMinimum;
    private Double pTwoOFivePercentageMaximum;

    private Double loiPercentageTarget;
    private Double loiPercentageMinimum;
    private Double loiPercentageMaximum;

    private Double cPercentageTarget;
    private Double cPercentageMinimum;
    private Double cPercentageMaximum;

    private Double sicPercentageTarget;
    private Double sicPercentageMinimum;
    private Double sicPercentageMaximum;
}
