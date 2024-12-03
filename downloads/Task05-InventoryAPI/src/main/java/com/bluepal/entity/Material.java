package com.bluepal.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
	@SequenceGenerator(name = "material_seq", sequenceName = "material_sequence", allocationSize = 1, initialValue = 1001)
    private Long materialNo; // No @NotBlank needed here, as it's auto-generated.;

    @NotBlank(message = "Material group is mandatory")
    @Size(max = 50, message = "Material group cannot exceed 50 characters")
    private String materialGroup;

    @NotBlank(message = "Material sub-group is mandatory")
    @Size(max = 50, message = "Material sub-group cannot exceed 50 characters")
    private String materialSubGroup;

    @NotBlank(message = "Material name is mandatory")
    @Size(max = 100, message = "Material name cannot exceed 100 characters")
    private String materialName;

    @NotBlank(message = "Size is mandatory")
    private String size;

    @NotBlank(message = "Unit of measurement is mandatory")
    private String unitOfMeasurement;

    @NotNull(message = "Minimum order level is mandatory")
    @Min(value = 1, message = "Minimum order level must be greater than 0")
    private Integer minimumOrderLevel;

    @NotNull(message = "Lead time is mandatory")
    @Min(value = 1, message = "Lead time must be greater than 0")
    private Integer leadTime;
    
    private String al2o3Min; // Alphanumeric instead of Double
    private String al2o3Max;
    private String al2o3Typical;
    private String sio2Min;
    private String sio2Max;
    private String sio2Typical;
    private String fe2o3Min;
    private String fe2o3Max;
    private String fe2o3Typical;
    private String chemical4Min;
    private String chemical4Max;
    private String chemical4Typical;
    private String chemical5Min;
    private String chemical5Max;
    private String chemical5Typical;
    private String chemical6Min;
    private String chemical6Max;
    private String chemical6Typical;
    private String chemical7Min;
    private String chemical7Max;
    private String chemical7Typical;
    private String chemical8Min;
    private String chemical8Max;
    private String chemical8Typical;

    private String bulkDensityMin;
    private String bulkDensityMax;
    private String bulkDensityTypical;
    private String apparentPorosityMin;
    private String apparentPorosityMax;
    private String apparentPorosityTypical;
    private String physical4Min;
    private String physical4Max;
    private String physical4Typical;
    private String physical5Min;
    private String physical5Max;
    private String physical5Typical;
    private String physical6Min;
    private String physical6Max;
    private String physical6Typical;
    private String physical7Min;
    private String physical7Max;
    private String physical7Typical;
    private String physical8Min;
    private String physical8Max;
    private String physical8Typical;
    
    @Version
	private Integer version;
	private String createdBy;
	private String updatedBy;
	@CreatedBy
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}