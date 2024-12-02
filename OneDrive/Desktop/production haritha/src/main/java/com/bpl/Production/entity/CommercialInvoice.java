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
@Table(name = "commercial_invoice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommercialInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "commercial_invoice_no")
    private String commercialInvoiceNo;

    @Column(name = "commercial_invoice_date")
    private Date commercialInvoiceDate;

    @Column(name = "customer_po_no")
    private String customerPoNo;

    @Column(name = "customer_nr")
    private Long customerNr;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "consignee_number")
    private String consigneeNumber;

    @Column(name = "consignee_name")
    private String consigneeName;

    @Column(name = "sales_unit")
    private String salesUnit;

    @Column(name = "business_unit")
    private Integer businessUnit;

    @Column(name = "mfs")
    private Integer mfs;

    @Column(name = "iec_no")
    private Integer iecNo;

    @Column(name = "cin")
    private String cin;

    @Column(name = "commodity")
    private String commodity;

    @Column(name = "pre_carriage_by")
    private String preCarriageBy;

    @Column(name = "place_of_receipt_by_pre_carriers")
    private String placeOfReceiptByPreCarriers;

    @Column(name = "port_of_loading")
    private String portOfLoading;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "port_of_discharge")
    private String portOfDischarge;

    @Column(name = "final_destination")
    private String finalDestination;

    @Column(name = "country_of_final_destination")
    private String countryOfFinalDestination;

    @Column(name = "against_c_form")
    private String againstCForm;

    @Column(name = "currency")
    private String currency;

    @Column(name = "label_marking")
    private String labelMarking;

    @Column(name = "terms_of_delivery")
    private String termsOfDelivery;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date", length = 19)
    private LocalDateTime lastModifiedDate;

    @Column(name = "is_commercial_invoice_details_created")
    private Boolean isCommercialInvoiceDetailsCreated;

}
