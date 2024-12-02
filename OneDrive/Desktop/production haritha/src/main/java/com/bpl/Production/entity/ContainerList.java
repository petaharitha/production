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
@Table(name = "container_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerList {

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

    @Column(name = "number")
    private String number;

    @Column(name = "alt_number")
    private Integer altNumber;

    @Column(name = "excise_invoice", length = 250)
    private String exciseInvoice;

    @Column(name = "voice_date")
    private Date voiceDate;

    @Column(name = "commercial_invoice", length = 250)
    private String commercialInvoice;
}
