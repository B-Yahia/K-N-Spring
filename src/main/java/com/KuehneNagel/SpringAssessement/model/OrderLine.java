package com.KuehneNagel.SpringAssessement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NotNull(message = "Quantity must not be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}
