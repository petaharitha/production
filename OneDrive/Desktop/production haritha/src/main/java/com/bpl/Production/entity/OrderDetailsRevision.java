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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details_revision")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsRevision implements Serializable {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "created_by")
	    private String createdBy;

	    @Column(name = "last_modified_by")
	    private String lastModifiedBy;

	    @Column(name = "created_date", length = 19)
	    private LocalDateTime createdDate;

	    @Column(name = "last_modified_date", length = 19)
	    private LocalDateTime lastModifiedDate;

	    @NotNull(message = "Material Number cannot be null")
	    private Integer materialNo;

	    @NotNull(message = "HPG cannot be null")
	    private String hpg;

	    @NotNull(message = "PG cannot be null")
	    private String pg;

	    @NotNull(message = "Quality cannot be null")
	    private String quality;

	    @NotNull(message = "Shape cannot be null")
	    private String shape;

	    private String externalBrandName;

	    @NotNull(message = "Quantity in Pieces cannot be null")
	    private Integer qtyPcs;

	    @NotNull(message = "Quantity in Metric Tons cannot be null")
	    private Double qtyMtons;

	    private String packingCode;

	    @NotNull(message = "Rate Per Piece cannot be null")
	    private Double ratePc;

	    private Double packPc;

	    private Double freightPc;

	    @NotNull(message = "Total cannot be null")
	    private Double total;

	    private String remarks;

	    private String poPosNo;

	    private String ocPosNo;

	    private Integer fgid;

	    private Integer orderId;

	    private String orderNo;

	    private Integer greenPcs;

	    private Double greenMt;

	    private Integer orderdetailsid;

	    private String reason;

	    private String revisionNo;

	    private Double palletWeight;

	    private String itemMarking;

	    private Integer externalMaterialNumber;

	    private Integer freeStockPieces;
}
