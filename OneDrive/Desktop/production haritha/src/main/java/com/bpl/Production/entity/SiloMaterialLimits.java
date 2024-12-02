package com.bpl.Production.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "silo_material_limits")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SiloMaterialLimits {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @Column(name = "validate_from")
    private Date validateFrom;

    @Column(name = "expired_from")
    private Date expiredFrom;

    @Column(name = "sm_no")
    private String smNo;

    @Column(name = "sm_description")
    private String smDescription;

    @Column(name = "lessthan_twelve_mm_target")
    @DecimalMin(value = "0.0", inclusive = false) // Example validation, adjust as needed
    private Double lessthanTwelveMMTarget;

    @Column(name = "lessthan_twelve_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanTwelveMMMinimum;

    @Column(name = "lessthan_twelve_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanTwelveMMMaximum;

    @Column(name = "lessthan_six_thirty_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanSixThirtyMMTarget;

    @Column(name = "lessthan_six_thirty_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanSixThirtyMMMinimum;

    @Column(name = "lessthan_six_thirty_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanSixThirtyMMMaximum;

    @Column(name = "lessthan_five_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanFiveMMTarget;

    @Column(name = "lessthan_five_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanFiveMMMinimum;

    @Column(name = "lessthan_five_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanFiveMMMaximum;

    @Column(name = "lessthan_four_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanFourMMTarget;

    @Column(name = "lessthan_four_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanFourMMMinimum;

    @Column(name = "lessthan_four_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanFourMMMaximum;

    @Column(name = "lessthan_three_fifteen_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanThreeFifteenMMTarget;

    @Column(name = "lessthan_three_fifteen_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanThreeFifteenMMMinimum;

    @Column(name = "lessthan_three_fifteen_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanThreeFifteenMMMaximum;

    @Column(name = "lessthan_two_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanTwoMMTarget;

    @Column(name = "lessthan_two_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanTwoMMMinimum;

    @Column(name = "lessthan_two_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanTwoMMMaximum;

    @Column(name = "lessthan_one_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanOneMMTarget;

    @Column(name = "lessthan_one_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanOneMMMinimum;

    @Column(name = "lessthan_one_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanOneMMMaximum;

    @Column(name = "lessthan_zero_fifty_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroFiftyMMTarget;

    @Column(name = "lessthan_zero_fifty_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroFiftyMMMinimum;

    @Column(name = "lessthan_zero_fifty_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroFiftyMMMaximum;

    @Column(name = "lessthan_zero_twenty_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroTwentyMMTarget;

    @Column(name = "lessthan_zero_twenty_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroTwentyMMMinimum;

    @Column(name = "lessthan_zero_twenty_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroTwentyMMMaximum;

    @Column(name = "lessthan_zero_onehundredsix_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroOnehundredsixMMTarget;

    @Column(name = "lessthan_zero_onehundredsix_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroOnehundredsixMMMinimum;

    @Column(name = "lessthan_zero_onehundredsix_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroOnehundredsixMMMaximum;

    @Column(name = "lessthan_zero_nine_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroNineMMTarget;

    @Column(name = "lessthan_zero_nine_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroNineMMMinimum;

    @Column(name = "lessthan_zero_nine_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroNineMMMaximum;

    @Column(name = "lessthan_zero_sixtythree_mm_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroSixtythreeMMTarget;

    @Column(name = "lessthan_zero_sixtythree_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroSixtythreeMMMinimum;

    @Column(name = "lessthan_zero_sixtythree_mm_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double lessthanZeroSixtythreeMMMaximum;

    @Column(name = "free_iron_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double freeIronTarget;

    @Column(name = "free_iron_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double freeIronMinimum;

    @Column(name = "free_iron_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double freeIronMaximum;

    @Column(name = "moisture_percentage_target")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double moisturePercentageTarget;

    @Column(name = "moisture_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double moisturePercentageMinimum;

    @Column(name = "moisture_percentage_maximum")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double moisturePercentageMaximum;
}
