package com.bluepal.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="RFG_CRUD")
public class RFG {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
	@SequenceGenerator(name = "material_seq", sequenceName = "material_sequence", allocationSize = 1, initialValue = 7001)
	   private Integer productNo; // No @NotBlank needed here, as it's auto-generated.

    @NotNull(message = "ProductGroup is mandatory")
    private String productGroup;

    @NotNull(message = "ProductSubGroup is mandatory")
    private String productSubGroup;

    private String productSubGroupII; // Optional

    @NotNull(message = "ProductName is mandatory")
    private String productName;

    @NotNull(message = "ShapeNo is mandatory")
    private String shapeNo;

    @NotNull(message = "DrawingNo is mandatory")
    private String drawingNo;

    @NotNull(message = "BulkDensity is mandatory")
    private BigDecimal bulkDensity;

    @NotNull(message = "Volume is mandatory")
    private BigDecimal volume;

    @NotNull(message = "UnitWt is mandatory")
    private BigDecimal unitWt;

    @NotNull(message = "UnitOfMeasurement is mandatory")
    private String unitOfMeasurement;

    private Integer minimumOrderLevel; // Optional
    private Integer leadTime; // Optional

    private BigDecimal al2o3Min; // Optional
    private BigDecimal al2o3Max; // Optional
    private BigDecimal al2o3Typical; // Optional

    private BigDecimal sio2Min; // Optional
    private BigDecimal sio2Max; // Optional
    private BigDecimal sio2Typical; // Optional

    private BigDecimal fe2o3Min; // Optional
    private BigDecimal fe2o3Max; // Optional
    private BigDecimal fe2o3Typical; // Optional

    private BigDecimal chemical4Min; // Optional
    private BigDecimal chemical4Max; // Optional
    private BigDecimal chemical4Typical; // Optional

    private BigDecimal chemical5Min; // Optional
    private BigDecimal chemical5Max; // Optional
    private BigDecimal chemical5Typical; // Optional

    private BigDecimal chemical6Min; // Optional
    private BigDecimal chemical6Max; // Optional
    private BigDecimal chemical6Typical; // Optional

    private BigDecimal chemical7Min; // Optional
    private BigDecimal chemical7Max; // Optional
    private BigDecimal chemical7Typical; // Optional

    private BigDecimal chemical8Min; // Optional
    private BigDecimal chemical8Max; // Optional
    private BigDecimal chemical8Typical; // Optional

    private BigDecimal bulkDensityMin; // Optional
    private BigDecimal bulkDensityMax; // Optional
    private BigDecimal bulkDensityTypical; // Optional

    private BigDecimal apparentPorosityMin; // Optional
    private BigDecimal apparentPorosityMax; // Optional
    private BigDecimal apparentPorosityTypical; // Optional

    private BigDecimal physical4Min; // Optional
    private BigDecimal physical4Max; // Optional
    private BigDecimal physical4Typical; // Optional

    private BigDecimal physical5Min; // Optional
    private BigDecimal physical5Max; // Optional
    private BigDecimal physical5Typical; // Optional

    private BigDecimal physical6Min; // Optional
    private BigDecimal physical6Max; // Optional
    private BigDecimal physical6Typical; // Optional

    private BigDecimal physical7Min; // Optional
    private BigDecimal physical7Max; // Optional
    private BigDecimal physical7Typical; // Optional

    private BigDecimal physical8Min; // Optional
    private BigDecimal physical8Max; // Optional
    private BigDecimal physical8Typical;
    
    
    @Version
	private Integer version;
	private String createdBy;
	private String updatedBy;
	@CreatedBy
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}