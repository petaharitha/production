package com.bpl.Production.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "supplier")
@Data   // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor  // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@Builder // Lombok annotation for the builder pattern
public class Supplier implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date", length = 19)
    private Timestamp createdDate;

    @Column(name = "last_modified_date", length = 19)
    private Timestamp lastModifiedDate;

    @Column(name = "number")
    private String number;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "address", length = 256)
    private String address;

    @Column(name = "contact", length = 100)
    private String contact;

}

