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
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @NotNull
        @Column(name = "created_by")
        private String createdBy;

	    @NotNull
        @Column(name = "last_modified_by")
        private String lastModifiedBy;

	    @NotNull
        @Column(name = "created_date", length = 19)
        private LocalDateTime createdDate;

	    @NotNull
        @Column(name = "last_modified_date", length = 19)
        private LocalDateTime lastModifiedDate;

	    @NotNull
	    private Integer materialNo;

	    @Size(max = 255)
	    private String hpg;

	    @Size(max = 255)
	    private String pg;

	    @Size(max = 255)
	    private String quality;

	    @Size(max = 255)
	    private String shape;

	    @Size(max = 255)
	    private String externalBrandName;

	    @NotNull
	    @Positive
	    private Integer qtyPcs;

	    @Positive
	    private Double qtyMtons;

	    @Size(max = 255)
	    private String packingCode;

	    @Positive
	    private Double ratePc;

	    @Positive
	    private Double packPc;

	    @Positive
	    private Double freightPc;

	    @Positive
	    private Double total;

	    @Size(max = 255)
	    private String remarks;

	    @Size(max = 255)
	    private String poPosNo;

	    @Size(max = 255)
	    private String ocPosNo;

	    private Integer fgid;

	    @NotNull
	    private Integer orderId;

	    @Size(max = 255)
	    private String orderNo;

	    @Positive
	    private Integer greenPcs;

	    @Positive
	    private Double greenMt;

	    @Positive
	    private Double palletWeight;

	    @Size(max = 255)
	    private String itemMarking;

	    private Integer externalMaterialNumber;

	    @Positive
	    private Integer freeStockPieces;
}
