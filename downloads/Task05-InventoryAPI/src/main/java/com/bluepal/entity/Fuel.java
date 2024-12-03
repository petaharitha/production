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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fuels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fuel {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
	@SequenceGenerator(name = "material_seq", sequenceName = "material_sequence", allocationSize = 1, initialValue = 4001)
    private Long materialNo; // No @NotBlank needed here, as it's auto-generated.;
		
		@NotBlank(message = "Material Group is mandatory")
	    @Size(max = 100, message = "Material Group can be up to 100 characters long")
	    private String materialGroup;
	    
	    @NotBlank(message = "Material Sub Group is mandatory")
	    @Size(max = 100, message = "Material Sub Group can be up to 100 characters long")
	    private String materialSubGroup;
	    
	    @NotBlank(message = "Material Name is mandatory")
	    @Size(max = 100, message = "Material Name can be up to 100 characters long")
	    private String materialName;
	    
	    @NotBlank(message = "Calrific Value is mandatory")
	    @Size(max = 100, message = " Calrific Value can be up to 100 characters long")
	    private String calrificValue;
	    
	    @NotBlank(message = "Other Desc is mandatory")
	    @Size(max = 100, message = "Other Desc can be up to 100 characters long")
	    private String otherDesc;
	   
	    @NotBlank(message = "Unit of Measurement is mandatory")
	    @Size(max = 50, message = "Unit of Measurement can be up to 50 characters long")
	    private String unitOfMeasurement;
	    
	    @NotNull(message = "Minimum Order Level is mandatory")
	    @Min(value = 1, message = "Minimum Order Level must be greater than 0")
	    private long minimumOrderLevel;
	    
	    @NotNull(message = "Lead Time is mandatory")
	    @Min(value = 1, message = "Lead Time must be greater than 0")
	    @Max(value = 365, message = "Lead Time must be less than or equal to 365 days")
	    private long leadTime;
	    
	    @Version
		private Integer version;
		private String createdBy;
		private String updatedBy;
		@CreatedBy
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
}

