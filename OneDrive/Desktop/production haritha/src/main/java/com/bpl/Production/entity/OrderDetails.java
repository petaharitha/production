package com.bpl.Production.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consignee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Created By cannot be null")
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_by")
    @NotNull(message = "Last Modified By cannot be null")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @NotNull(message = "Material Number cannot be null")
    @Min(value = 1, message = "Material Number must be greater than 0")
    private Integer materialNo;

    @Size(max = 255, message = "HPG cannot exceed 255 characters")
    private String hpg;

    @Size(max = 255, message = "PG cannot exceed 255 characters")
    private String pg;

    @Size(max = 50, message = "Quality cannot exceed 50 characters")
    private String quality;

    @Size(max = 50, message = "Shape cannot exceed 50 characters")
    private String shape;

    @Size(max = 100, message = "External Brand Name cannot exceed 100 characters")
    private String externalBrandName;

    @NotNull(message = "Quantity (Pcs) cannot be null")
    private Integer qtyPcs;

    @NotNull(message = "Quantity (MTons) cannot be null")
    @Min(value = 0, message = "Quantity (MTons) must be greater than or equal to 0")
    private Double qtyMtons;

    @Size(max = 50, message = "Packing Code cannot exceed 50 characters")
    private String packingCode;

    @NotNull(message = "Rate per Piece cannot be null")
    @Min(value = 0, message = "Rate per Piece must be greater than or equal to 0")
    private Double ratePc;

    @NotNull(message = "Pack per Piece cannot be null")
    @Min(value = 0, message = "Pack per Piece must be greater than or equal to 0")
    private Double packPc;

    @NotNull(message = "Freight per Piece cannot be null")
    @Min(value = 0, message = "Freight per Piece must be greater than or equal to 0")
    private Double freightPc;

    @NotNull(message = "Total cannot be null")
    @Min(value = 0, message = "Total must be greater than or equal to 0")
    private Double total;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String remarks;

    @NotNull(message = "PO Position Number cannot be null")
    private Integer poPosNo;

    @Size(max = 50, message = "OC Position Number cannot exceed 50 characters")
    private String ocPosNo;

    @NotNull(message = "FGID cannot be null")
    private Integer fgid;

    @NotNull(message = "Order ID cannot be null")
    private Integer orderId;

    @Size(max = 50, message = "Order Number cannot exceed 50 characters")
    private String orderNo;

    @NotNull(message = "Green Pieces cannot be null")
    @Min(value = 0, message = "Green Pieces must be greater than or equal to 0")
    private Integer greenPcs;

    @NotNull(message = "Green Metric Tons cannot be null")
    @Min(value = 0, message = "Green Metric Tons must be greater than or equal to 0")
    private Double greenMt;
}
