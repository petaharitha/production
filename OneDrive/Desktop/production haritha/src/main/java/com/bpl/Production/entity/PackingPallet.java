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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "packing_pallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackingPallet implements Serializable {

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

    @NotNull
    private Long palletNo;

    private String soNo;

    @NotNull
    private String consigneeId;

    private String consigneeName;
    
    private String consigneeAddress;

    private String ocPosNo;
    
    private String ocNo;

    private Integer materialNo;
    
    private Integer materialMergedWith;

    private String quality;
    
    private String shape;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    private Integer pieces;

    private Double palletNetWeight;

    private Double palletGrossWeight;

    private Boolean received;

    @Temporal(TemporalType.DATE)
    private Date receivedDate;

    private Integer containerId;
    
    private String containerNo;

    private Boolean isPrinted;
    
    private String dimensions;

    private Integer orderId;

    private String itemMarking;

    private String externalBrandName;

    private Long customerPalletNo;

    private String poPosNo;

    private Integer externalMaterialNumber;

    private String hpg;

}
