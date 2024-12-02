package com.bpl.Production.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sample_test_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleTestResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "created_by")
    @NotNull(message = "Created By cannot be null")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date")
    @NotNull(message = "Created Date cannot be null")
    private Timestamp createdDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @Column(name = "sample_received_date")
    @NotNull(message = "Sample Received Date cannot be null")
    private Date sampleReceivedDate;

    @Column(name = "shift", length = 10)
    @Size(max = 10, message = "Shift must not exceed 10 characters")
    private String shift;

    @Column(name = "lab_sample_no", length = 50)
    @NotBlank(message = "Lab Sample No cannot be blank")
    @Size(max = 50, message = "Lab Sample No must not exceed 50 characters")
    private String labSampleNo;

    @Column(name = "description", length = 255)
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @Column(name = "received_from", length = 100)
    @Size(max = 100, message = "Received From must not exceed 100 characters")
    private String receivedFrom;

    @Column(name = "purpose", length = 100)
    @Size(max = 100, message = "Purpose must not exceed 100 characters")
    private String pupose;

    @Column(name = "free_iron", length = 50)
    @Size(max = 50, message = "Free Iron must not exceed 50 characters")
    private String freeIron;

    @Column(name = "moisture_percentage")
    private Double moisturePercentage;

    @Column(name = "less_than_twelve_mm")
    private Double lessthanTwelveMM;

    @Column(name = "less_than_ten_mm")
    private Double lessthanTenMM;

    @Column(name = "less_than_six_thirty_mm")
    private Double lessthanSixThirtyMM;

    @Column(name = "less_than_five_sixty_mm")
    private Double lessthanFiveSixtyMM;

    @Column(name = "less_than_five_mm")
    private Double lessthanFiveMM;

    @Column(name = "less_than_four_mm")
    private Double lessthanFourMM;

    @Column(name = "less_than_three_fifteen_mm")
    private Double lessthanThreeFifteenMM;

    @Column(name = "less_than_two_mm")
    private Double lessthanTwoMM;

    @Column(name = "less_than_one_mm")
    private Double lessthanOneMM;

    @Column(name = "less_than_zero_fifty_mm")
    private Double lessthanZeroFiftyMM;

    @Column(name = "less_than_zero_threefifteen_mm")
    private Double lessthanZeroThreefifteenMM;

    @Column(name = "less_than_zero_twentyone_mm")
    private Double lessthanZeroTwentyoneMM;

    @Column(name = "less_than_zero_onehundredsix_mm")
    private Double lessthanZeroOnehundredsixMM;

    @Column(name = "less_than_zero_nine_mm")
    private Double lessthanZeroNineMM;

    @Column(name = "less_than_zero_seventyfive_mm")
    private Double lessthanZeroSeventyfiveMM;

    @Column(name = "less_than_zero_sixtythree_mm")
    private Double lessthanZeroSixtythreeMM;

    @Column(name = "less_than_zero_fortyfive_mm")
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

    @Column(name = "gspgr_percentage")
    private Double gspgrPercentage;

    @Column(name = "plc_percentage")
    private Double plcPercentage;

    @Column(name = "pce")
    private Double pce;

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

    @Column(name = "vm_percentage")
    private Double vmPercentage;

    @Column(name = "fc_percentage")
    private Double fcPercentage;

    @Column(name = "loi_percentage")
    private Double loiPercentage;

    @Column(name = "status")
    private Integer status;

    @Column(name = "remarks", length = 500)
    @Size(max = 500, message = "Remarks must not exceed 500 characters")
    private String remarks;

	public Date parse(String fromDateStr) {
		// TODO Auto-generated method stub
		return null;
	}
}
