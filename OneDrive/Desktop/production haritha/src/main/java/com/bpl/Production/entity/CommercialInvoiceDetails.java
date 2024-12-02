package com.bpl.Production.entity;

import java.time.LocalDateTime;

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
@Table(name = "commercial_invoice_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommercialInvoiceDetails {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 @Column(name = "created_by")
	 private String createdBy;

	 @Column(name = "last_modified_by")
	 private String lastModifiedBy;

	 @Column(name = "created_date", length = 19)
	 private LocalDateTime createdDate;

	 @Column(name = "last_modified_date", length = 19)
	 private LocalDateTime lastModifiedDate;

	    @Column(name = "commercial_invoice_no")
	    private String commercialInvoiceNo;

	    @Column(name = "so_no")
	    private String soNo;

	    @Column(name = "material_no")
	    private int materialNo;

	    @Column(name = "po_pos_no")
	    private String poPosNo;

	    @Column(name = "quality")
	    private String quality;

	    @Column(name = "shape")
	    private String shape;

	    @Column(name = "scheme")
	    private String scheme;

	    @Column(name = "incentive")
	    private String incentive;

	    @Column(name = "item_ce_chapter_no")
	    private String itemCeChapterNo;

	    @Column(name = "item_ce_description")
	    private String itemCeDescription;

	    @Column(name = "external_quality")
	    private String externalQuality;

	    @Column(name = "no_of_pallets")
	    private String noOfPallets;

	    @Column(name = "qty_pcs")
	    private int qtyPcs;

	    @Column(name = "qty_mtons")
	    private double qtyMtons;

	    @Column(name = "uom")
	    private int uom;

	    @Column(name = "remarks")
	    private String remarks;

	    @Column(name = "material_cost")
	    private double materialCost;

	    @Column(name = "packing_cost")
	    private double packingCost;

	    @Column(name = "freight")
	    private double freight;

	    @Column(name = "total")
	    private double total;
}
