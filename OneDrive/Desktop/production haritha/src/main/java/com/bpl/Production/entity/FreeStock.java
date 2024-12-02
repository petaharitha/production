package com.bpl.Production.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "free_stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeStock implements Serializable {

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

    @NotNull(message = "SO No cannot be null")
    private String soNo;

    @NotNull(message = "Material No cannot be null")
    private Integer materialNo;

    @NotNull(message = "PO Pos No cannot be null")
    private String poPosNo;

    @NotNull(message = "Quality cannot be null")
    private String quality;

    @NotNull(message = "Shape cannot be null")
    private String shape;

    @PositiveOrZero(message = "Quantity pieces must be zero or positive")
    private Integer qtyPcs;

    @NotNull(message = "Source cannot be null")
    private String source;

    @NotNull(message = "Source ID cannot be null")
    private Integer sourceId;

    @NotNull(message = "Status cannot be null")
    private Integer status;

    @PositiveOrZero(message = "Balance must be zero or positive")
    private Integer balance;
}
