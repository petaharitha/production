package com.bpl.Production.entity;

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
@Table(name = "dispatch-planning")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispatchPlanning {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @Column(name = "number", length = 64)
    private String number;

    @Column(name = "material_no", length = 64)
    private String materialNo;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "hpg", length = 64)
    private String hpg;

    @Column(name = "pg", length = 64)
    private String pg;

    @Column(name = "quality", length = 50)
    private String quality;

    @Column(name = "shape", length = 64)
    private String shape;

    @Column(name = "quantitymtons")
    private Double quantityInMt;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "date")
    private Date date;

    @Column(name = "remarks", length = 256)
    private String remarks;

    @Column(name = "quantity_dispatched")
    private Double quantityDispatched;

    @Column(name = "po_pos_no", length = 20)
    private String poPosNo;

    @Column(name = "exw_week")
    private Integer exwWeek;

    @Column(name = "exw_month")
    private Integer exwMonth;

    @Column(name = "exw_year")
    private Integer exwYear;

    @Column(name = "total_pcs")
    private Integer totalPcs;

    @Column(name = "planned_pcs")
    private Integer plannedPcs;

    @Column(name = "total")
    private Double total;

    @Column(name = "original_dispatch_planning_id")
    private Integer originalDispatchPlanningId;

    @Column(name = "original_planned_pcs")
    private Integer originalPlannedPcs;
}
