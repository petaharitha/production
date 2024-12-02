package com.bpl.Production.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "forming")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forming implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "CreatedBy cannot be null")
    private Integer createdBy;

    @NotNull(message = "LastModifiedBy cannot be null")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @NotNull(message = "Date cannot be null")
    private Date date;

    @NotNull(message = "Shift cannot be null")
    @Size(min = 1, max = 10, message = "Shift must be between 1 and 10 characters")
    private String shift;

    @NotNull(message = "FormingNumber cannot be null")
    private Integer formingNumber;

    @Size(max = 50, message = "HPG cannot be longer than 50 characters")
    private String hpg;

    @NotNull(message = "MaterialNo cannot be null")
    private Integer materialNo;

    @Size(max = 50, message = "SONo cannot be longer than 50 characters")
    private String soNo;

    @NotNull(message = "ProductionPcs cannot be null")
    private Integer productionPcs;

    @NotNull(message = "OkPcs cannot be null")
    private Integer okPcs;

    @NotNull(message = "RejPcs cannot be null")
    private Integer rejPcs;

    @Size(max = 255, message = "Remarks cannot be longer than 255 characters")
    private String remarks;

    @Size(max = 50, message = "Quality cannot be longer than 50 characters")
    private String quality;

    @Size(max = 50, message = "Shape cannot be longer than 50 characters")
    private String shape;

    @Size(max = 50, message = "PG cannot be longer than 50 characters")
    private String pg;

    private Integer fgid;

    private Integer referenceMixingNumber;

    @Size(max = 50, message = "PO/PosNo cannot be longer than 50 characters")
    private String poPosNo;

}
