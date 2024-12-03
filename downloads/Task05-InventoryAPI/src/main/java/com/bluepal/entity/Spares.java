package com.bluepal.entity;



import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "spares")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spares implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
	@SequenceGenerator(name = "material_seq", sequenceName = "material_sequence", allocationSize = 1, initialValue = 5001)
	    private Long materialNo; // No @NotBlank needed here, as it's auto-generated.
	
	@NotBlank(message = "Material Group is mandatory")
    @Size(max = 100, message = "Material Group can be up to 100 characters long")
    private String materialGroup;
    
    @NotBlank(message = "Material Sub Group is mandatory")
    @Size(max = 100, message = "Material Sub Group can be up to 100 characters long")
    private String materialSubGroup;
    
    @NotBlank(message = "Material Name is mandatory")
    @Size(max = 100, message = "Material Name can be up to 100 characters long")
    private String materialName;
    
    @NotNull(message = "Length is mandatory")
    @Min(value = 1, message = "Length must be greater than 0")
    private Long length;
    
    @NotNull(message = "Breadth is mandatory")
    @Min(value = 1, message = "Breadth must be greater than 0")
    private Long breadth;
    
    @NotNull(message = "Height is mandatory")
    @Min(value = 1, message = "Height must be greater than 0")
    private long height;
    
    @NotBlank(message = "Original OEM is mandatory")
    @Size(max = 100, message = "Original OEM can be up to 100 characters long")
    private String originalOEM;
    
    @NotBlank(message = "Part Number is mandatory")
    @Size(max = 100, message = "Part Number can be up to 100 characters long")
    private String partNo;
    
    @NotBlank(message = "Drawing Number is mandatory")
    @Size(max = 100, message = "Drawing Number can be up to 100 characters long")
    private String drawingNo;
    
    @NotBlank(message = "Unit of Measure is mandatory")
    @Size(max = 50, message = "Unit of Measure can be up to 50 characters long")
    private String unitOfMeasure;
    
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
    private LocalDateTime lastUpdated;
    @PrePersist
    @PreUpdate
    public void setLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }
	public Object getField(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

}
