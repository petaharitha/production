package com.bpl.request;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.util.Date;

@Data
public class RawMaterialStockRequest {

    @NotNull(message = "Raw Material Number cannot be null")
    @Positive(message = "Raw Material Number must be a positive integer")
    private Integer rmNo;

    @NotBlank(message = "Raw Material Description cannot be blank")
    @Size(max = 100, message = "Raw Material Description cannot exceed 100 characters")
    private String rmDescription;

    @NotNull(message = "Unit of Measure cannot be null")
    @Positive(message = "Unit of Measure must be a positive integer")
    private Integer unitOfMeasure;

    @NotNull(message = "Lead Time cannot be null")
    @FutureOrPresent(message = "Lead Time must be a present or future date")
    private Date leadTime;

    @NotNull(message = "Minimum Stock cannot be null")
    @PositiveOrZero(message = "Minimum Stock must be a non-negative value")
    private Double minimumStock;

    @NotNull(message = "Raw Material Stock cannot be null")
    @PositiveOrZero(message = "Raw Material Stock must be a non-negative value")
    private Double rawMaterialStock;
}
