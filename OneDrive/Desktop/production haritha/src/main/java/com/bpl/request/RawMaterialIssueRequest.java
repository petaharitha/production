package com.bpl.request;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.util.Date;

@Data
public class RawMaterialIssueRequest {

    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date must be in the past or present")
    private Date date;

    @NotBlank(message = "Shift cannot be blank")
    @Pattern(regexp = "^(Morning|Afternoon|Night)$", message = "Shift must be 'Morning', 'Afternoon', or 'Night'")
    private String shift;

    @NotNull(message = "Raw Material Number (RM No) cannot be null")
    @Positive(message = "Raw Material Number (RM No) must be positive")
    private Integer rmNo;

    @NotBlank(message = "Raw Material Description cannot be blank")
    @Size(max = 100, message = "Raw Material Description cannot exceed 100 characters")
    private String rmDescription;

    @NotNull(message = "Unit of Measure cannot be null")
    @Positive(message = "Unit of Measure must be positive")
    private Integer unitOfMeasure;

    @Positive(message = "Issued Quantity must be positive")
    private double issuesQty;
}
