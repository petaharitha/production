package com.bluepal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class PackingMaterial implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long materialId; // Auto-generated

	@NotEmpty(message = "Material Group is required")
	@Column(nullable = false)
	private String materialGroup; // Mandatory, Text, Dropdown

	@NotEmpty(message = "Material No is required")
	@Column(nullable = false)
	private String materialNo; // Mandatory, Text, Dropdown

	@NotEmpty(message = "Material Name is required")
	@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Material Name must be alphanumeric")
	@Column(nullable = false)
	private String materialName; // Mandatory, Alphanumeric, Manual

	@NotNull(message = "Length is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Length must be greater than 0")
	@Column(nullable = false)
	private Double length; // Mandatory, Numeric, Manual

	@NotNull(message = "Breadth is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Breadth must be greater than 0")
	@Column(nullable = false)
	private Double breadth; // Mandatory, Numeric, Manual

	@NotNull(message = "Height is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Height must be greater than 0")
	@Column(nullable = false)
	private Double height; // Mandatory, Numeric, Manual

	@DecimalMin(value = "0.0", inclusive = false, message = "Diameter must be greater than 0")
	private Double diameter; // Optional, Numeric, Manual

	@DecimalMin(value = "0.0", inclusive = false, message = "Thickness must be greater than 0")
	private Double thickness; // Optional, Numeric, Manual

	@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "GSM must be alphanumeric")
	private String gsm; // Optional, Alphanumeric, Manual

	@NotEmpty(message = "Unit of Measurement is required")
	@Column(nullable = false)
	private String unitOfMeasurement; // Mandatory, Text, Dropdown

	@NotNull(message = "Minimum Order Level is required")
	@Min(value = 1, message = "Minimum Order Level must be at least 1")
	@Column(nullable = false)
	private Integer minimumOrderLevel; // Mandatory, Numeric, Manual

	@NotNull(message = "Lead Time is required")
	@Min(value = 1, message = "Lead Time must be at least 1")
	@Column(nullable = false)
	private Integer leadTimes; // Mandatory, Numeric, Manual

	@Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Drawing No must be alphanumeric")
	private String drawingNo; // Optional, Alphanumeric, Manual

	@Version
	private Integer version;
	private String createdBy;
	private String updatedBy;
	@CreatedBy
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}