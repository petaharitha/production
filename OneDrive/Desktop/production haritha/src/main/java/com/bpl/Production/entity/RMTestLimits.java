package com.bpl.Production.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rm_limits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RMTestLimits {

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

    @Column(name = "rm_no")
    private Integer rmNo;

    @Column(name = "rm_description")
    private String rmDescription;

    @Column(name = "moisture_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target moisture percentage cannot be less than 0")
    private Double moisturePercentageTarget;

    @Column(name = "moisture_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum moisture percentage cannot be less than 0")
    private Double moisturePercentageMinimum;

    @Column(name = "moisture_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum moisture percentage cannot be greater than 100")
    private Double moisturePercentageMaximum;

    @Column(name = "lessthan_twelve_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 12mm cannot be less than 0")
    private Double lessthanTwelveMMTarget;

    @Column(name = "lessthan_twelve_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 12mm cannot be less than 0")
    private Double lessthanTwelveMMMinimum;

    @Column(name = "lessthan_twelve_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 12mm cannot be greater than 100")
    private Double lessthanTwelveMMMaximum;

    @Column(name = "lessthan_ten_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 10mm cannot be less than 0")
    private Double lessthanTenMMTarget;

    @Column(name = "lessthan_ten_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 10mm cannot be less than 0")
    private Double lessthanTenMMMinimum;

    @Column(name = "lessthan_ten_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 10mm cannot be greater than 100")
    private Double lessthanTenMMMaximum;

    @Column(name = "lessthan_six_thirty_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 630mm cannot be less than 0")
    private Double lessthanSixThirtyMMTarget;

    @Column(name = "lessthan_six_thirty_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 630mm cannot be less than 0")
    private Double lessthanSixThirtyMMMinimum;

    @Column(name = "lessthan_six_thirty_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 630mm cannot be greater than 100")
    private Double lessthanSixThirtyMMMaximum;

    @Column(name = "lessthan_five_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 5mm cannot be less than 0")
    private Double lessthanFiveMMTarget;

    @Column(name = "lessthan_five_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 5mm cannot be less than 0")
    private Double lessthanFiveMMMinimum;

    @Column(name = "lessthan_five_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 5mm cannot be greater than 100")
    private Double lessthanFiveMMMaximum;

    @Column(name = "lessthan_four_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 4mm cannot be less than 0")
    private Double lessthanFourMMTarget;

    @Column(name = "lessthan_four_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 4mm cannot be less than 0")
    private Double lessthanFourMMMinimum;

    @Column(name = "lessthan_four_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 4mm cannot be greater than 100")
    private Double lessthanFourMMMaximum;
    
    @Column(name = "lessthan_three_fifteen_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 315mm cannot be less than 0")
    private Double lessthanThreeFifteenMMTarget;

    @Column(name = "lessthan_three_fifteen_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 315mm cannot be less than 0")
    private Double lessthanThreeFifteenMMMinimum;

    @Column(name = "lessthan_three_fifteen_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 315mm cannot be greater than 100")
    private Double lessthanThreeFifteenMMMaximum;

    @Column(name = "lessthan_two_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 2mm cannot be less than 0")
    private Double lessthanTwoMMTarget;

    @Column(name = "lessthan_two_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 2mm cannot be less than 0")
    private Double lessthanTwoMMMinimum;

    @Column(name = "lessthan_two_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 2mm cannot be greater than 100")
    private Double lessthanTwoMMMaximum;

    @Column(name = "lessthan_one_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 1mm cannot be less than 0")
    private Double lessthanOneMMTarget;

    @Column(name = "lessthan_one_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 1mm cannot be less than 0")
    private Double lessthanOneMMMinimum;

    @Column(name = "lessthan_one_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 1mm cannot be greater than 100")
    private Double lessthanOneMMMaximum;

    @Column(name = "lessthan_zero_fifty_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 0.50mm cannot be less than 0")
    private Double lessthanZeroFiftyMMTarget;

    @Column(name = "lessthan_zero_fifty_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 0.50mm cannot be less than 0")
    private Double lessthanZeroFiftyMMMinimum;

    @Column(name = "lessthan_zero_fifty_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 0.50mm cannot be greater than 100")
    private Double lessthanZeroFiftyMMMaximum;

    @Column(name = "lessthan_zero_twentyone_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 0.21mm cannot be less than 0")
    private Double lessthanZeroTwentyoneMMTarget;

    @Column(name = "lessthan_zero_twentyone_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 0.21mm cannot be less than 0")
    private Double lessthanZeroTwentyoneMMMinimum;

    @Column(name = "lessthan_zero_twentyone_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 0.21mm cannot be greater than 100")
    private Double lessthanZeroTwentyoneMMMaximum;

    @Column(name = "lessthan_zero_onehundredsix_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 0.106mm cannot be less than 0")
    private Double lessthanZeroOnehundredsixMMTarget;

    @Column(name = "lessthan_zero_onehundredsix_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 0.106mm cannot be less than 0")
    private Double lessthanZeroOnehundredsixMMMinimum;

    @Column(name = "lessthan_zero_onehundredsix_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 0.106mm cannot be greater than 100")
    private Double lessthanZeroOnehundredsixMMMaximum;

    @Column(name = "lessthan_zero_nine_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 0.9mm cannot be less than 0")
    private Double lessthanZeroNineMMTarget;

    @Column(name = "lessthan_zero_nine_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 0.9mm cannot be less than 0")
    private Double lessthanZeroNineMMMinimum;

    @Column(name = "lessthan_zero_nine_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 0.9mm cannot be greater than 100")
    private Double lessthanZeroNineMMMaximum;

    @Column(name = "lessthan_zero_sixtythree_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 0.63mm cannot be less than 0")
    private Double lessthanZeroSixtythreeMMTarget;

    @Column(name = "lessthan_zero_sixtythree_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 0.63mm cannot be less than 0")
    private Double lessthanZeroSixtythreeMMMinimum;

    @Column(name = "lessthan_zero_sixtythree_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 0.63mm cannot be greater than 100")
    private Double lessthanZeroSixtythreeMMMaximum;

    @Column(name = "lessthan_zero_fortyfive_mm_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target for less than 0.45mm cannot be less than 0")
    private Double lessthanZeroFortyfiveMMTarget;

    @Column(name = "lessthan_zero_fortyfive_mm_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum for less than 0.45mm cannot be less than 0")
    private Double lessthanZeroFortyfiveMMMinimum;

    @Column(name = "lessthan_zero_fortyfive_mm_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum for less than 0.45mm cannot be greater than 100")
    private Double lessthanZeroFortyfiveMMMaximum;

    @Column(name = "water_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "Target water percentage cannot be less than 0")
    private Double waterPercentageTarget;

    @Column(name = "water_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum water percentage cannot be less than 0")
    private Double waterPercentageMinimum;

    @Column(name = "water_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "Maximum water percentage cannot be greater than 100")
    private Double waterPercentageMaximum;

    @Column(name = "i_sett_time_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "iSettTimeTarget cannot be less than 0")
    private Double iSettTimeTarget;

    @Column(name = "i_sett_time_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "iSettTimeMinimum cannot be less than 0")
    private Double iSettTimeMinimum;

    @Column(name = "i_sett_time_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "iSettTimeMaximum cannot be greater than 100")
    private Double iSettTimeMaximum;

    @Column(name = "f_sett_time_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "fSettTimeTarget cannot be less than 0")
    private Double fSettTimeTarget;

    @Column(name = "f_sett_time_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "fSettTimeMinimum cannot be less than 0")
    private Double fSettTimeMinimum;

    @Column(name = "f_sett_time_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "fSettTimeMaximum cannot be greater than 100")
    private Double fSettTimeMaximum;
    
    @Column(name = "liq_density_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "LiqDensityTarget cannot be less than 0")
    private Double liqDensityTarget;

    @Column(name = "liq_density_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "LiqDensityMinimum cannot be less than 0")
    private Double liqDensityMinimum;

    @Column(name = "liq_density_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "LiqDensityMaximum cannot be greater than 100")
    private Double liqDensityMaximum;

    @Column(name = "gp_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "GpPercentageTarget cannot be less than 0")
    private Double gpPercentageTarget;

    @Column(name = "gp_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "GpPercentageMinimum cannot be less than 0")
    private Double gpPercentageMinimum;

    @Column(name = "gp_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "GpPercentageMaximum cannot be greater than 100")
    private Double gpPercentageMaximum;

    @Column(name = "gd_gm_slash_cc_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "GdGmSlashCcTarget cannot be less than 0")
    private Double gdGmSlashCcTarget;

    @Column(name = "gd_gm_slash_cc_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "GdGmSlashCcMinimum cannot be less than 0")
    private Double gdGmSlashCcMinimum;

    @Column(name = "gd_gm_slash_cc_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "GdGmSlashCcMaximum cannot be greater than 100")
    private Double gdGmSlashCcMaximum;
    
    @Column(name = "al_two_o_three_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "AlTwoOThreePercentageTarget cannot be less than 0")
    private Double alTwoOThreePercentageTarget;

    @Column(name = "al_two_o_three_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "AlTwoOThreePercentageMinimum cannot be less than 0")
    private Double alTwoOThreePercentageMinimum;

    @Column(name = "al_two_o_three_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "AlTwoOThreePercentageMaximum cannot be greater than 100")
    private Double alTwoOThreePercentageMaximum;

    @Column(name = "fe_two_o_three_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "FeTwoOThreePercentageTarget cannot be less than 0")
    private Double feTwoOThreePercentageTarget;

    @Column(name = "fe_two_o_three_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "FeTwoOThreePercentageMinimum cannot be less than 0")
    private Double feTwoOThreePercentageMinimum;

    @Column(name = "fe_two_o_three_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "FeTwoOThreePercentageMaximum cannot be greater than 100")
    private Double feTwoOThreePercentageMaximum;

    @Column(name = "tio_two_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "TioTwoPercentageTarget cannot be less than 0")
    private Double tioTwoPercentageTarget;

    @Column(name = "tio_two_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "TioTwoPercentageMinimum cannot be less than 0")
    private Double tioTwoPercentageMinimum;

    @Column(name = "tio_two_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "TioTwoPercentageMaximum cannot be greater than 100")
    private Double tioTwoPercentageMaximum;

    @Column(name = "cao_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "CaoPercentageTarget cannot be less than 0")
    private Double caoPercentageTarget;

    @Column(name = "cao_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "CaoPercentageMinimum cannot be less than 0")
    private Double caoPercentageMinimum;

    @Column(name = "cao_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "CaoPercentageMaximum cannot be greater than 100")
    private Double caoPercentageMaximum;

    @Column(name = "cr_two_o_three_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "CrTwoOThreePercentageTarget cannot be less than 0")
    private Double crTwoOThreePercentageTarget;

    @Column(name = "cr_two_o_three_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "CrTwoOThreePercentageMinimum cannot be less than 0")
    private Double crTwoOThreePercentageMinimum;

    @Column(name = "cr_two_o_three_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "CrTwoOThreePercentageMaximum cannot be greater than 100")
    private Double crTwoOThreePercentageMaximum;

    @Column(name = "sio_two_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "SioTwoPercentageTarget cannot be less than 0")
    private Double sioTwoPercentageTarget;

    @Column(name = "sio_two_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "SioTwoPercentageMinimum cannot be less than 0")
    private Double sioTwoPercentageMinimum;

    @Column(name = "sio_two_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "SioTwoPercentageMaximum cannot be greater than 100")
    private Double sioTwoPercentageMaximum;

    @Column(name = "na_two_o_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "NaTwoOPercentageTarget cannot be less than 0")
    private Double naTwoOPercentageTarget;

    @Column(name = "na_two_o_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "NaTwoOPercentageMinimum cannot be less than 0")
    private Double naTwoOPercentageMinimum;

    @Column(name = "na_two_o_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "NaTwoOPercentageMaximum cannot be greater than 100")
    private Double naTwoOPercentageMaximum;

    @Column(name = "mgo_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "MgoPercentageTarget cannot be less than 0")
    private Double mgoPercentageTarget;

    @Column(name = "mgo_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "MgoPercentageMinimum cannot be less than 0")
    private Double mgoPercentageMinimum;

    @Column(name = "mgo_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "MgoPercentageMaximum cannot be greater than 100")
    private Double mgoPercentageMaximum;

    @Column(name = "k_two_o_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "KTwoOPercentageTarget cannot be less than 0")
    private Double kTwoOPercentageTarget;

    @Column(name = "k_two_o_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "KTwoOPercentageMinimum cannot be less than 0")
    private Double kTwoOPercentageMinimum;

    @Column(name = "k_two_o_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "KTwoOPercentageMaximum cannot be greater than 100")
    private Double kTwoOPercentageMaximum;

    @Column(name = "loi_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "LoiPercentageTarget cannot be less than 0")
    private Double loiPercentageTarget;

    @Column(name = "loi_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "LoiPercentageMinimum cannot be less than 0")
    private Double loiPercentageMinimum;

    @Column(name = "loi_percentage_maximum")
    @DecimalMax(value = "100.0", inclusive = true, message = "LoiPercentageMaximum cannot be greater than 100")
    private Double loiPercentageMaximum;
    
    @Column(name = "p_two_o_five_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "PTwoOFivePercentageTarget cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "PTwoOFivePercentageTarget cannot be greater than 100")
    private Double pTwoOFivePercentageTarget;

    @Column(name = "p_two_o_five_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "PTwoOFivePercentageMinimum cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "PTwoOFivePercentageMinimum cannot be greater than 100")
    private Double pTwoOFivePercentageMinimum;

    @Column(name = "p_two_o_five_percentage_maximum")
    @DecimalMin(value = "0.0", inclusive = true, message = "PTwoOFivePercentageMaximum cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "PTwoOFivePercentageMaximum cannot be greater than 100")
    private Double pTwoOFivePercentageMaximum;

    @Column(name = "zro_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "ZroPercentageTarget cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "ZroPercentageTarget cannot be greater than 100")
    private Double zroPercentageTarget;

    @Column(name = "zro_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "ZroPercentageMinimum cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "ZroPercentageMinimum cannot be greater than 100")
    private Double zroPercentageMinimum;

    @Column(name = "zro_percentage_maximum")
    @DecimalMin(value = "0.0", inclusive = true, message = "ZroPercentageMaximum cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "ZroPercentageMaximum cannot be greater than 100")
    private Double zroPercentageMaximum;

    @Column(name = "ph_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "PHTarget cannot be less than 0")
    @DecimalMax(value = "14.0", inclusive = true, message = "PHTarget cannot be greater than 14")
    private Double pHTarget;

    @Column(name = "ph_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "PHMinimum cannot be less than 0")
    @DecimalMax(value = "14.0", inclusive = true, message = "PHMinimum cannot be greater than 14")
    private Double pHMinimum;

    @Column(name = "ph_maximum")
    @DecimalMin(value = "0.0", inclusive = true, message = "PHMaximum cannot be less than 0")
    @DecimalMax(value = "14.0", inclusive = true, message = "PHMaximum cannot be greater than 14")
    private Double pHMaximum;

    @Column(name = "sic_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "SicPercentageTarget cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "SicPercentageTarget cannot be greater than 100")
    private Double sicPercentageTarget;

    @Column(name = "sic_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "SicPercentageMinimum cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "SicPercentageMinimum cannot be greater than 100")
    private Double sicPercentageMinimum;

    @Column(name = "sic_percentage_maximum")
    @DecimalMin(value = "0.0", inclusive = true, message = "SicPercentageMaximum cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "SicPercentageMaximum cannot be greater than 100")
    private Double sicPercentageMaximum;

    @Column(name = "fired_carbon_percentage_target")
    @DecimalMin(value = "0.0", inclusive = true, message = "FiredCarbonPercentageTarget cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "FiredCarbonPercentageTarget cannot be greater than 100")
    private Double firedCarbonPercentageTarget;

    @Column(name = "fired_carbon_percentage_minimum")
    @DecimalMin(value = "0.0", inclusive = true, message = "FiredCarbonPercentageMinimum cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "FiredCarbonPercentageMinimum cannot be greater than 100")
    private Double firedCarbonPercentageMinimum;

    @Column(name = "fired_carbon_percentage_maximum")
    @DecimalMin(value = "0.0", inclusive = true, message = "FiredCarbonPercentageMaximum cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "FiredCarbonPercentageMaximum cannot be greater than 100")
    private Double firedCarbonPercentageMaximum;
}
      
