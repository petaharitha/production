package com.bpl.Production.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "raw_material_receipt")
public class RawMaterialReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "Created By cannot be null")
    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @NotNull(message = "Created Date cannot be null")
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @NotNull(message = "Inward Date cannot be null")
    @Column(name = "inward_date", nullable = false)
    private Date inwardDate;

    @NotNull(message = "Inward No cannot be null")
    @Column(name = "inward_no", nullable = false)
    private Integer inwardNo;

    @NotNull(message = "RM No cannot be null")
    @Column(name = "rm_no", nullable = false)
    private Integer rmNo;

    @NotEmpty(message = "RM Description cannot be empty")
    @Column(name = "rm_description", nullable = false, length = 255)
    private String rmDescription;

    @NotEmpty(message = "Supplier No cannot be empty")
    @Column(name = "supplier_no", nullable = false, length = 50)
    private String supplierNo;

    @Column(name = "supplier_description", length = 255)
    private String supplierDescription;

    @NotEmpty(message = "RCD Against cannot be empty")
    @Column(name = "rcd_against", nullable = false, length = 255)
    private String rcdAgainst;

    @NotNull(message = "Unit of Measure cannot be null")
    @Column(name = "unit_of_measure", nullable = false)
    private Integer unitOfMeasure;

    @Column(name = "truck_no", length = 50)
    private String truckNo;

    @NotNull(message = "PO No cannot be null")
    @Column(name = "po_no", nullable = false)
    private Integer poONo;

    @NotNull(message = "PO Date cannot be null")
    @Column(name = "po_o_date", nullable = false)
    private Date poODate;

    @Column(name = "dc_no")
    private Integer dcNo;

    @Column(name = "dc_date")
    @Temporal(TemporalType.DATE)  
    private Date dcDate;

    @PositiveOrZero(message = "Quantity As Per DC must be greater than or equal to zero")
    @Column(name = "qty_as_per_dc")
    private double qtyAsPerDc;

    @PositiveOrZero(message = "Actual Quantity Received must be greater than or equal to zero")
    @Column(name = "actual_qty_rcd")
    private double actualQtyRcd;

    @PositiveOrZero(message = "Quantity Rejected must be greater than or equal to zero")
    @Column(name = "qty_rejected")
    private double qtyRejected;

    @PositiveOrZero(message = "Quantity Accepted must be greater than or equal to zero")
    @Column(name = "qty_accepted")
    private double qtyAccepted;

    @PositiveOrZero(message = "Value must be greater than or equal to zero")
    @Column(name = "value")
    private double value;

    @Column(name = "remarks", length = 255)
    private String remarks;

    @Column(name = "status")
    private String status;
    
    @Column(name = "tag", length = 50)
    private String tag;


	public String getComments() {
		// TODO Auto-generated method stub
		return null;
	}
}

