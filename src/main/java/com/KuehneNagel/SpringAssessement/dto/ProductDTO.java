package com.KuehneNagel.SpringAssessement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    private long id;
    @NotBlank(message = "Product name must not be blank")
    private String name;
    private String skuCode;
    @NotNull(message = "Product price must not be null")
    @Min(value = 0, message = "Product price must be greater than or equal to 0")
    private double unitPrice;
}
