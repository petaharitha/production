package com.bpl.request;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class RawMaterialRequirementRequest {

    @NotBlank(message = "SO Number cannot be blank")
    @Size(max = 50, message = "SO Number cannot exceed 50 characters")
    private String soNo;

    @NotNull(message = "Material Number cannot be null")
    @Positive(message = "Material Number must be a positive integer")
    private Integer materialNo;

    @NotBlank(message = "PO Position Number cannot be blank")
    @Size(max = 50, message = "PO Position Number cannot exceed 50 characters")
    private String poPosNo;

    @NotNull(message = "Plant Load Year cannot be null")
    @Min(value = 2000, message = "Plant Load Year must be after 2000")
    @Max(value = 2100, message = "Plant Load Year must be before 2100")
    private Integer plantLoadYear;

    @NotNull(message = "Plant Load Month cannot be null")
    @Min(value = 1, message = "Plant Load Month must be between 1 and 12")
    @Max(value = 12, message = "Plant Load Month must be between 1 and 12")
    private Integer plantLoadMonth;

    @NotNull(message = "Recipe Number cannot be null")
    @Positive(message = "Recipe Number must be a positive integer")
    private Integer recipeNumber;

    @NotNull(message = "Alternative Number cannot be null")
    @Positive(message = "Alternative Number must be a positive integer")
    private Integer altNumber;

    @NotNull(message = "Raw Material Number cannot be null")
    @Positive(message = "Raw Material Number must be a positive integer")
    private Integer rawMaterialNo;

    @NotBlank(message = "Raw Material Name cannot be blank")
    @Size(max = 100, message = "Raw Material Name cannot exceed 100 characters")
    private String rawMaterialName;

    @NotNull(message = "Quantity in Metric Tons cannot be null")
    @Positive(message = "Quantity in Metric Tons must be positive")
    private Double qtyMtons;

    @NotNull(message = "Unit of Measure cannot be null")
    @Positive(message = "Unit of Measure must be a positive integer")
    private Integer unitOfMeasure;

    @NotNull(message = "Status cannot be null")
    @PositiveOrZero(message = "Status must be a non-negative integer")
    private Integer status;

    @Size(max = 255, message = "Reason cannot exceed 255 characters")
    private String reason;

    @Size(max = 100, message = "Quality cannot exceed 100 characters")
    private String quality;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be a positive value")
    private Double quantity;
}
