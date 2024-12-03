package com.bluepal.entity;



import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consumables")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
	@SequenceGenerator(name = "material_seq", sequenceName = "material_sequence", allocationSize = 1, initialValue = 6001)
    private Long materialNo; // No @NotBlank needed here, as it's auto-generated.;
	
    @NotBlank(message = "Material group is mandatory")
    @Size(max = 100, message = "Material group cannot exceed 100 characters")
    private String materialGroup;

    @NotBlank(message = "Material sub-group is mandatory")
    @Size(max = 100, message = "Material sub-group cannot exceed 100 characters")
    private String materialSubGroup;

    @NotBlank(message = "Material name is mandatory")
    @Size(max = 255, message = "Material name cannot exceed 255 characters")
    private String materialName;

    private Double length;

    private Double breadth;

    private Double height;

    @Size(max = 255, message = "Original OEM cannot exceed 255 characters")
    private String originalOEM;

    @Size(max = 255, message = "Part number cannot exceed 255 characters")
    private String partNo;

    @Size(max = 255, message = "Drawing number cannot exceed 255 characters")
    private String drawingNo;

    @NotBlank(message = "Unit of measurement is mandatory")
    private String unitOfMeasurement;

    @Min(value = 1, message = "Minimum order level must be at least 1")
    private Integer minimumOrderLevel;

    @Min(value = 0, message = "Lead time must be a positive value")
    private Integer leadTime;
    
    @Version
	private Integer version;
	private String createdBy;
	private String updatedBy;
	@CreatedBy
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
   
}

