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
@Table(name = "packing_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackingList implements Serializable {

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

	    @Column(name = "order_id")
	    private Integer orderId;

	    @Column(name = "po_number")
	    private String poNumber;

	    @Column(name = "ts_number")
	    private String tsNumber;

	    @Column(name = "customer_id")
	    private Integer customerId;

	    @Column(name = "customer_name")
	    private String customerName;

	    @Column(name = "consignee_id")
	    private Integer consigneeId;

	    @Column(name = "consignee_name")
	    private String consigneeName;

	    @Column(name = "container_number")
	    private String containerNumber;

	    @Column(name = "position_number")
	    private Integer positionNumber;

	    @Column(name = "material_number")
	    private Integer materialNumber;

	    private String quality;
	    private String shape;

	    @Column(name = "pallet_number")
	    private Long palletNumber;

	    @Column(name = "new_pallet_number")
	    private Long newPalletNumber;

	    @Column(name = "number_of_pits")
	    private Double numberOfPits;

	    @Column(name = "pieces_per_pallet")
	    private Integer piecesPerPallet;

	    @Column(name = "pallet_net_weight")
	    private Double palletNetWeight;

	    @Column(name = "pallet_gr_weight")
	    private Double palletGrWeight;

	    @Column(name = "delivery_date")
	    private Date deliveryDate;

	    private String status;
}
