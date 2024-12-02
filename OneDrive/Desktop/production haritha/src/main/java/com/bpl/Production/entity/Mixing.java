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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consignee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mixing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @Column(name = "date")
    private Date date;

    @Column(name = "shift")
    private String shift;

    @Column(name = "mixing_number")
    private Integer mixingNumber;

    @Column(name = "hpg")
    private String hpg;

    @Column(name = "recipe_no")
    private Integer recipeNo;

    @Column(name = "alt_no")
    private Integer altNo;

    @Column(name = "total_qty_in_mt")
    private Double totalQtyInMt;

    @Column(name = "mix_ok_qty_in_mt")
    private Double mixOkQtyInMt;

    @Column(name = "mix_rej_qty_in_mt")
    private Double mixRejQtyInMt;

    @Column(name = "hand_over_press_table")
    private String handOverPressTable;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "quality")
    private String quality;

    @Column(name = "pg")
    private String pg;

    @Column(name = "fgid")
    private Integer fgid;

    @Column(name = "so_no")
    private String soNo;

}
