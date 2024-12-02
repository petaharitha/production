package com.bpl.Production.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipe_details")
public class RecipeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_by", nullable = false)
    @NotNull(message = "Created By cannot be null")
    private Integer createdBy;

    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    @NotNull(message = "Created Date cannot be null")
    private Timestamp createdDate;

    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    @Column(name = "recipe_no", nullable = false)
    @NotNull(message = "Recipe No cannot be null")
    private Integer recipeNo;

    @Column(name = "alt_no")
    private Integer altNo;

    @Column(name = "material_no", nullable = false)
    @NotNull(message = "Material No cannot be null")
    private Integer materialNo;

    @Column(name = "material_name", length = 100)
    @NotBlank(message = "Material Name cannot be blank")
    @Size(max = 100, message = "Material Name cannot exceed 100 characters")
    private String materialName;

    @Column(name = "qty_mtons")
    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be greater than 0")
    private Double qtyMtons;

    @Column(name = "unit_of_measure")
    private Integer unitOfMeasure;

    @Column(name = "raw_material_no")
    private Integer rawMaterialNo;

    @Column(name = "raw_material_description", length = 255)
    @Size(max = 255, message = "Raw Material Description cannot exceed 255 characters")
    private String rawMaterialDescription;
}
