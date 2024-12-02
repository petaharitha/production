package com.bpl.Production.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_by", nullable = false)
    @NotNull(message = "CreatedBy cannot be null")
    private Integer createdBy;

    @Column(name = "last_modified_by", nullable = false)
    @NotNull(message = "LastModifiedBy cannot be null")
    private Integer lastModifiedBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    @NotNull(message = "CreatedDate cannot be null")
    private Timestamp createdDate;

    @Column(name = "last_modified_date", nullable = false)
    @NotNull(message = "LastModifiedDate cannot be null")
    private Timestamp lastModifiedDate;

    @Column(name = "reservation_nr", nullable = false, unique = true)
    @NotBlank(message = "ReservationNr cannot be blank")
    private String reservationNr;

    @Column(name = "customer_nr", nullable = false)
    @NotNull(message = "CustomerNr cannot be null")
    private Long customerNr;

    @Column(name = "customer_name", nullable = false)
    @NotBlank(message = "CustomerName cannot be blank")
    private String customerName;

    @Column(name = "consignee_number", nullable = false)
    @NotBlank(message = "ConsigneeNumber cannot be blank")
    private String consigneeNumber;

    @Column(name = "consignee_name", nullable = false)
    @NotBlank(message = "ConsigneeName cannot be blank")
    private String consigneeName;

    @Column(name = "exw_date", nullable = false)
    @NotNull(message = "ExwDate cannot be null")
    private Date exwDate;

    @Column(name = "exw_week", nullable = false)
    @NotNull(message = "ExwWeek cannot be null")
    @Min(value = 1, message = "ExwWeek must be between 1 and 52")
    @Max(value = 52, message = "ExwWeek must be between 1 and 52")
    private Integer exwWeek;

    @Column(name = "exw_month", nullable = false)
    @NotNull(message = "ExwMonth cannot be null")
    @Min(value = 1, message = "ExwMonth must be between 1 and 12")
    @Max(value = 12, message = "ExwMonth must be between 1 and 12")
    private Integer exwMonth;

    @Column(name = "exw_year", nullable = false)
    @NotNull(message = "ExwYear cannot be null")
    private Integer exwYear;

    @Column(name = "order_type", nullable = false)
    @NotBlank(message = "OrderType cannot be blank")
    private String orderType;

    @Column(name = "business_unit", nullable = false)
    @NotNull(message = "BusinessUnit cannot be null")
    private Integer businessUnit;

    @Column(name = "sales_person", nullable = false)
    @NotBlank(message = "SalesPerson cannot be blank")
    private String salesPerson;
    
    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;
    
    private String hpg;

    
}

