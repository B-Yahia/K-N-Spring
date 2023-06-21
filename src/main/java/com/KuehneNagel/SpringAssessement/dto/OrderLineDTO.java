package com.KuehneNagel.SpringAssessement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderLineDTO {
    private long id;
    private ProductDTO product;
    @NotNull(message = "Quantity must not be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}
