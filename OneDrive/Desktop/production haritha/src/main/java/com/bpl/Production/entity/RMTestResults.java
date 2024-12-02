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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rm_test_results")
public class RMTestResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Created By cannot be null")
    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @NotNull(message = "Created Date cannot be null")
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date must be in the past or present")
    @Column(name = "date", nullable = false)
    private Date date;

    @NotEmpty(message = "Shift cannot be empty")
    @Column(name = "shift", nullable = false, length = 20)
    private String shift;

    @NotEmpty(message = "Lab Sample No cannot be empty")
    @Column(name = "lab_sample_no", nullable = false, length = 50)
    private String labSampleNo;

    @NotNull(message = "RM No cannot be null")
    @Column(name = "rm_no", nullable = false)
    private Integer rmNo;

    @Column(name = "rm_description")
    private String rmDescription;

    @NotEmpty(message = "Supplier No cannot be empty")
    @Column(name = "supplier_no", nullable = false, length = 50)
    private String supplierNo;

    @Column(name = "supplier_description")
    private String supplierDescription;

    @NotNull(message = "PO No cannot be null")
    @Column(name = "po_no", nullable = false)
    private Integer poNo;

    @NotNull(message = "SR No cannot be null")
    @Column(name = "sr_no", nullable = false)
    private Integer srNo;

    @NotNull(message = "SR Date cannot be null")
    @Column(name = "sr_date", nullable = false)
    private Date srDate;

    @PositiveOrZero(message = "Quantity (Mt) must be greater than or equal to zero")
    @Column(name = "qnty_mt")
    private Double qntyMt;

    @DecimalMin(value = "0.0", inclusive = false, message = "Moisture Percentage must be greater than 0")
    @Column(name = "moisture_percentage")
    private Double moisturePercentage;

    @DecimalMin(value = "0.0", inclusive = false, message = "Lessthan Twelve MM must be greater than 0")
    @Column(name = "lessthan_twelve_mm")
    private Double lessthanTwelveMM;

    @Column(name = "lessthan_ten_mm")
    private Double lessthanTenMM;

    @Column(name = "lessthan_six_thirty_mm")
    private Double lessthanSixThirtyMM;

    @Column(name = "lessthan_five_mm")
    private Double lessthanFiveMM;

    @Column(name = "lessthan_four_mm")
    private Double lessthanFourMM;

    @Column(name = "lessthan_three_fifteen_mm")
    private Double lessthanThreeFifteenMM;

    @Column(name = "lessthan_two_mm")
    private Double lessthanTwoMM;

    @Column(name = "lessthan_one_mm")
    private Double lessthanOneMM;

    @Column(name = "lessthan_zero_fifty_mm")
    private Double lessthanZeroFiftyMM;

    @Column(name = "lessthan_zero_twentyone_mm")
    private Double lessthanZeroTwentyoneMM;

    @Column(name = "lessthan_zero_onehundredsix_mm")
    private Double lessthanZeroOnehundredsixMM;

    @Column(name = "lessthan_zero_nine_mm")
    private Double lessthanZeroNineMM;

    @Column(name = "lessthan_zero_sixtythree_mm")
    private Double lessthanZeroSixtythreeMM;

    @Column(name = "lessthan_zero_fortyfive_mm")
    private Double lessthanZeroFortyfiveMM;

    @Column(name = "liq_density")
    private Double liqDensity;

    @Column(name = "water_percentage")
    private Double waterPercentage;

    @Column(name = "i_sett_time")
    private Double iSettTime;

    @Column(name = "f_sett_time")
    private Double fSettTime;

    @Column(name = "ph")
    private Double pH;

    @Column(name = "gp_percentage")
    private Double gpPercentage;

    @Column(name = "gd_gm_slash_cc")
    private Double gdGmSlashCc;

    @Column(name = "al_two_o_three_percentage")
    private Double alTwoOThreePercentage;

    @Column(name = "fe_two_o_three_percentage")
    private Double feTwoOThreePercentage;

    @Column(name = "tio_two_percentage")
    private Double tioTwoPercentage;

    @Column(name = "cao_percentage")
    private Double caoPercentage;

    @Column(name = "mgo_percentage")
    private Double mgoPercentage;

    @Column(name = "sio_two_percentage")
    private Double sioTwoPercentage;

    @Column(name = "cr_two_o_three_percentage")
    private Double crTwoOThreePercentage;

    @Column(name = "p_two_o_five_percentage")
    private Double pTwoOFivePercentage;

    @Column(name = "na_two_o_percentage")
    private Double naTwoOPercentage;

    @Column(name = "k_two_o_percentage")
    private Double kTwoOPercentage;

    @Column(name = "zro_percentage")
    private Double zroPercentage;

    @Column(name = "loi_percentage")
    private Double loiPercentage;

    @Column(name = "status")
    private Integer status;

    @Column(name = "sic_percentage")
    private Double sicPercentage;

    @Column(name = "fired_carbon_percentage")
    private Double firedCarbonPercentage;

    @Column(name = "approval_by")
    private Integer approvalBy;

    @Column(name = "remarks", length = 255)
    private String remarks;

	public void setComment(Object object) {
		// TODO Auto-generated method stub
		
	}
}

