package com.bpl.Production.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "raw_material_requirement")
public class RawMaterialRequirement {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    private Integer createdBy;
    private Integer updatedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedDate;

	private String soNo;
	private Integer materialNo;
	private String poPosNo;
	private Integer plantLoadYear;
	private Integer plantLoadMonth;
	private Integer recipeNumber;
	private Integer altNumber;
	private Integer rawMaterialNo;
	private String rawMaterialName;
	private Double qtyMtons;
	private Integer unitOfMeasure;
	private Integer status;
	private String reason;
	private String quality;
	private Double quantity;
	
}
