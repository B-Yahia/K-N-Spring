package com.KuehneNagel.SpringAssessement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Product name must not be blank")
    private String name;
    private String skuCode;
    @NotNull(message = "Product price must not be null")
    @Min(value = 1, message = "Product price must be greater than or equal to 0")
    private double unitPrice;
}
