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
@Table(name = "free_stock_allocation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeStockAllocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Created By cannot be null")
    private Integer createdBy;

    @NotNull(message = "Last Modified By cannot be null")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @NotNull(message = "Material Number cannot be null")
    @Min(value = 1, message = "Material Number must be a positive integer")
    private Integer materialNo;

    @Size(max = 50, message = "HPG cannot exceed 50 characters")
    private String hpg;

    @Size(max = 50, message = "PG cannot exceed 50 characters")
    private String pg;

    @Size(max = 100, message = "Quality cannot exceed 100 characters")
    private String quality;

    @Size(max = 50, message = "Shape cannot exceed 50 characters")
    private String shape;

    @NotNull(message = "Quantity in Pieces cannot be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer qtyPcs;

    @Size(max = 255, message = "Remarks cannot exceed 255 characters")
    private String remarks;

    @Size(max = 50, message = "PO/Pos No cannot exceed 50 characters")
    private String poPosNo;

    @Size(max = 50, message = "SO No cannot exceed 50 characters")
    private String soNo;

    @NotNull(message = "Free Stock Pieces cannot be null")
    @Min(value = 1, message = "Free Stock Pieces must be greater than 0")
    private Integer freeStockPieces;

    @Size(max = 50, message = "Free Stock SO No cannot exceed 50 characters")
    private String freeStockSoNo;

    @NotNull(message = "Free Stock Material No cannot be null")
    private Integer freeStockMaterialNo;

    @Size(max = 50, message = "Free Stock PO/Pos No cannot exceed 50 characters")
    private String freeStockPoPosNo;

    @Size(max = 50, message = "Free Stock Quality cannot exceed 50 characters")
    private String freeStockQuality;

    @Size(max = 50, message = "Free Stock Shape cannot exceed 50 characters")
    private String freeStockShape;

    @NotNull(message = "Free Stock ID cannot be null")
    private Integer freeStockId;

    @NotNull(message = "Status cannot be null")
    @Min(value = 1, message = "Status must be greater than 0")
    private Integer status;

    @Min(value = 0, message = "Free Stock Pieces Rejected cannot be negative")
    private Integer freeStockPiecesRejected;

}
