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
public class Mould {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
	@SequenceGenerator(name = "material_seq", sequenceName = "material_sequence", allocationSize = 1, initialValue = 2001)
	private Long materialNo; // No @NotBlank needed here, as it's auto-generated.

	@NotBlank(message = "Material group is mandatory")
	@Size(max = 50, message = "Material group cannot exceed 50 characters")
	private String materialGroup;

	@NotBlank(message = "Material sub-group is mandatory")
	@Size(max = 50, message = "Material sub-group cannot exceed 50 characters")
	private String materialSubGroup;

	@NotBlank(message = "Material name is mandatory")
	@Size(max = 100, message = "Material name cannot exceed 100 characters")
	private String materialName;

	@NotNull(message = "Length is mandatory")
	@Min(value = 1, message = "Length must be greater than 0")
	private Double length;

	@NotNull(message = "Breadth is mandatory")
	@Min(value = 1, message = "Breadth must be greater than 0")
	private Double breadth;

	@NotNull(message = "Height is mandatory")
	@Min(value = 1, message = "Height must be greater than 0")
	private Double height;

	private Double diameter;

	private Double thickness;

	private String gsm;

	@NotBlank(message = "Unit of measurement is mandatory")
	private String unitOfMeasurement;

	@NotNull(message = "Minimum order level is mandatory")
	@Min(value = 1, message = "Minimum order level must be greater than 0")
	private Integer minimumOrderLevel;

	@NotNull(message = "Lead time is mandatory")
	@Min(value = 1, message = "Lead time must be greater than 0")
	private Integer leadTime;

	private String drawingNo; // Optional - Link to attach drawing

	@Version
	private Integer version;
	private String createdBy;
	private String updatedBy;
	@CreatedBy
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}