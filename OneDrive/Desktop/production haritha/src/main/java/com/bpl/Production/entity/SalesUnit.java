package com.bpl.Production.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "sales_unit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "created_by")
    @NotNull(message = "Created By cannot be null")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date")
    @NotNull(message = "Created Date cannot be null")
    private Timestamp createdDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @Column(name = "ve_code", length = 64)
    @NotBlank(message = "VE Code cannot be blank")
    @Size(max = 64, message = "VE Code must not exceed 64 characters")
    private String veCode;

    @Column(name = "ve_description", length = 256)
    @Size(max = 256, message = "VE Description must not exceed 256 characters")
    private String veDescription;
}