package com.bpl.Production.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recipe")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @NotNull
    @Column(name = "last_modified_by", nullable = false)
    private Integer lastModifiedBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @NotNull
    @Column(name = "last_modified_date", nullable = false)
    private Timestamp lastModifiedDate;

    @NotNull
    @Size(max = 255)
    @Column(name = "hpg", nullable = false, length = 255)
    private String hpg;

    @NotNull
    @Size(max = 255)
    @Column(name = "pg", nullable = false, length = 255)
    private String pg;

    @NotNull
    @Size(max = 50)
    @Column(name = "recipe_number", nullable = false, length = 50)
    private Integer recipeNumber;

    @Size(max = 255)
    @Column(name = "quality", length = 255)
    private String quality;

    @NotNull
    @Column(name = "alt", nullable = false)
    private Integer alt;
    
    @Column(name = "status")
    private String status;

}

