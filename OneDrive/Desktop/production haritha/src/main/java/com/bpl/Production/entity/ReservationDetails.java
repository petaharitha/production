package com.bpl.Production.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservation_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @NotNull
    @Column(name = "last_modified_by", nullable = false)
    private Integer lastModifiedBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @Size(max = 255)
    @Column(name = "hpg", length = 255)
    private String hpg;

    @Size(max = 255)
    @Column(name = "pg", length = 255)
    private String pg;

    @Size(max = 255)
    @Column(name = "quality", length = 255)
    private String quality;

    @Size(max = 255)
    @Column(name = "shape", length = 255)
    private String shape;

    @NotNull
    @Column(name = "qty_mtons", nullable = false)
    private Double qtyMTons;

    @Size(max = 255)
    @Column(name = "remarks", length = 255)
    private String remarks;

    @Column(name = "fgid")
    private Integer fgid;

    @NotNull
    @Column(name = "reservation_id", nullable = false)
    private Integer reservationId;

    @Size(max = 255)
    @Column(name = "reservation_no", length = 255)
    private Integer reservationNo;
}

