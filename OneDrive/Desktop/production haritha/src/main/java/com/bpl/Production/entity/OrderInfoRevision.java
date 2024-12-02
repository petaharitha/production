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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order-info-revision")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoRevision implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "last_modified_by", nullable = false)
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @NotNull
    @Column(name = "so_no", length = 50)
    private String soNo;

    @Column(name = "customer_po_no", length = 50)
    private String customerPoNo;

    @Column(name = "customer_nr")
    private Long customerNr;

    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(name = "consignee_number", length = 50)
    private String consigneeNumber;

    @Column(name = "consignee_name", length = 100)
    private String consigneeName;

    @Column(name = "oc_no", length = 50)
    private String ocNo;

    @Column(name = "exw_date")
    private Date exwDate;

    @Column(name = "exw_week")
    private Integer exwWeek;

    @Column(name = "exw_month")
    private Integer exwMonth;

    @Column(name = "exw_year")
    private Integer exwYear;

    @Column(name = "mrw_date")
    private Date mrwDate;

    @Column(name = "mrw_week")
    private Integer mrwWeek;

    @Column(name = "mrw_month")
    private Integer mrwMonth;

    @Column(name = "mrw_year")
    private Integer mrwYear;

    @Column(name = "business_unit")
    private Integer businessUnit;

    @Column(name = "sales_unit", length = 50)
    private String salesUnit;

    @Column(name = "mfs")
    private Integer mfs;

    @Column(name = "label_marking", length = 100)
    private String labelMarking;

    @Column(name = "order_type", length = 50)
    private String orderType;

    @Column(name = "fgid")
    private Integer fgid;

    @Column(name = "orderid")
    private Integer orderid;

    @Column(name = "reason", length = 255)
    private String reason;

    @Column(name = "revision_no", length = 50)
    private String revisionNo;

    @Column(name = "remarks", length = 255)
    private String remarks;

    @Column(name = "is_packing_list_created")
    private Boolean isPackingListCreated;

    @Column(name = "is_container_list_created")
    private Boolean isContainerListCreated;

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_work_order_generated")
    private Boolean isWorkOrderGenerated;

    @Column(name = "is_approval")
    private Boolean isApproval;

}
