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
@Table(name = "fg")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fg implements Serializable{

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

	    @Column(name = "material_no")
	    private Integer materialNo;

	    @Column(name = "hpg", length = 64)
	    private String hpg;

	    @Column(name = "pg", length = 64)
	    private String pg;

	    @Column(name = "recipe_number", length = 20)
	    private String recipeNumber;

	    @Column(name = "quality", length = 50)
	    private String quality;

	    @Column(name = "shape", length = 64)
	    private String shape;

	    @Column(name = "unit_weight_gr")
	    private Double unitWeightGr;

	    @Column(name = "unit_weight_fr")
	    private Double unitWeightFr;

	    @Column(name = "pcs_pellete")
	    private Integer pcsPellete;

	    @Column(name = "hs_code", length = 15)
	    private String hsCode;

	    @Column(name = "hsc_description", length = 64)
	    private String hscDescription;
}
