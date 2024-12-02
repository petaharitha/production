package com.bpl.request;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class RawMaterialRequest {

    @NotNull(message = "Raw Material Number cannot be null")
    @Positive(message = "Raw Material Number must be a positive integer")
    private Integer rawMaterialNo;

    @NotBlank(message = "Raw Material Description cannot be blank")
    @Size(max = 255, message = "Raw Material Description cannot exceed 255 characters")
    private String rawMaterialDescription;

    @NotBlank(message = "HS Code cannot be blank")
    @Pattern(regexp = "\\d{6,10}", message = "HS Code must be between 6 to 10 digits")
    private String hsCode;

    @NotBlank(message = "HS Code Description cannot be blank")
    @Size(max = 255, message = "HS Code Description cannot exceed 255 characters")
    private String hscDescription;

    @NotNull(message = "SM Number cannot be null")
    @Positive(message = "SM Number must be a positive integer")
    private Integer smNo;

    @NotBlank(message = "SM Description cannot be blank")
    @Size(max = 255, message = "SM Description cannot exceed 255 characters")
    private String smDescription;

    @NotNull(message = "Unit of Measure cannot be null")
    @Positive(message = "Unit of Measure must be a positive integer")
    private Integer unitOfMeasure;
}
