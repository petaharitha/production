package com.bpl.Production.entity;

import java.io.Serializable;
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
@Table(name = "fg-stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FgStock implements Serializable{

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

    @Column(name = "so_no", length = 64)
    private String soNo;

    @Column(name = "material_no")
    private Integer materialNo;

    @Column(name = "po_pos_no", length = 20)
    private String poPosNo;

    @Column(name = "quality", length = 50)
    private String quality;

    @Column(name = "shape", length = 64)
    private String shape;

    @Column(name = "qty_pcs")
    private Integer qtyPcs;

    @Column(name = "pallet_no", length = 20)
    private Long palletNo;

    @Column(name = "reference_packing_pallet_id")
    private Integer referencePackingPalletId;

    @Column(name = "is_moved_to_free_stock")
    private Boolean isMovedToFreeStock;
}
