package com.bpl.Production.entity;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "silo_material_test_results")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SiloMaterialTestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotNull(message = "Created by cannot be null")
    @Column(name = "created_by")
    private Integer createdBy;

    @NotNull(message = "Last modified by cannot be null")
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @NotNull(message = "Create Date cannot be null")
    @Column(name = "created_date", length = 19)
    private Timestamp createdDate;

    @NotNull(message = "Last Modified Date cannot be null")
    @Column(name = "last_modified_date", length = 19)
    private Timestamp lastModifiedDate;

    @NotNull(message = "Date cannot be null")
    @Column(name = "date")
    private Date date;

    @NotNull(message = "Shift cannot be null")
    @Column(name = "shift", length = 64)
    private String shift;

    @Size(min = 1, max = 100, message = "Lab sample number should be between 1 and 100 characters")
    @Column(name = "lab_sample_no", length = 100)
    private String labSampleNo;

    @NotNull(message = "Material number cannot be null")
    @Column(name = "material_no")
    private Integer materialNo;

    @NotNull(message = "Sm No cannot be null")
    @Column(name = "sm_no", length = 64)
    private String smNo;

    @NotNull(message = "Sm Description cannot be null")
    @Column(name = "sm_description", length = 64)
    private String smDescription;

    @NotNull(message = "Silo number cannot be null")
    @Column(name = "silo_no", length = 64)
    private String siloNo;

    @Min(value = 0, message = "Free iron value should be greater than or equal to 0")
    @Column(name = "free_iron")
    private Double freeIron;

    @Min(value = 0, message = "Moisture percentage should be greater than or equal to 0")
    @Column(name = "moisture_percentage")
    private Double moisturePercentage;

    @Min(value = 0, message = "Less than 12mm value should be greater than or equal to 0")
    @Column(name = "lessthan_twelve_mm")
    private Double lessthanTwelveMM;

    @Min(value = 0, message = "Less than 6.30mm value should be greater than or equal to 0")
    @Column(name = "lessthan_six_thirty_mm")
    private Double lessthanSixThirtyMM;

    @Min(value = 0, message = "Less than 5mm value should be greater than or equal to 0")
    @Column(name = "lessthan_five_mm")
    private Double lessthanFiveMM;

    @Min(value = 0, message = "Less than 4mm value should be greater than or equal to 0")
    @Column(name = "lessthan_four_mm")
    private Double lessthanFourMM;

    @Min(value = 0, message = "Less than 3.15mm value should be greater than or equal to 0")
    @Column(name = "lessthan_three_fifteen_mm")
    private Double lessthanThreeFifteenMM;

    @Min(value = 0, message = "Less than 2mm value should be greater than or equal to 0")
    @Column(name = "lessthan_two_mm")
    private Double lessthanTwoMM;

    @Min(value = 0, message = "Less than 1mm value should be greater than or equal to 0")
    @Column(name = "lessthan_one_mm")
    private Double lessthanOneMM;

    @Min(value = 0, message = "Less than 0.50mm value should be greater than or equal to 0")
    @Column(name = "lessthan_zero_fifty_mm")
    private Double lessthanZeroFiftyMM;

    @Min(value = 0, message = "Less than 0.20mm value should be greater than or equal to 0")
    @Column(name = "lessthan_zero_twenty_mm")
    private Double lessthanZeroTwentyMM;

    @Min(value = 0, message = "Less than 0.106mm value should be greater than or equal to 0")
    @Column(name = "lessthan_zero_onehundredsix_mm")
    private Double lessthanZeroOnehundredsixMM;

    @Min(value = 0, message = "Less than 0.09mm value should be greater than or equal to 0")
    @Column(name = "lessthan_zero_nine_mm")
    private Double lessthanZeroNineMM;

    @Min(value = 0, message = "Less than 0.063mm value should be greater than or equal to 0")
    @Column(name = "lessthan_zero_sixtythree_mm")
    private Double lessthanZeroSixtythreeMM;

    @Min(value = 0, message = "Molasses value should be greater than or equal to 0")
    @Column(name = "molasis_gm_slash_cc")
    private Double molasisGmSlashCC;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private Integer status;

    @Column(name = "approval_by")
    private Integer approvalBy;

    @Size(max = 500, message = "Remarks should not exceed 500 characters")
    @Column(name = "remarks", length = 256)
    private String remarks;
}
