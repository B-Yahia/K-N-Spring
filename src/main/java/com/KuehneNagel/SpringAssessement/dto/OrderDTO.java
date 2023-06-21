package com.KuehneNagel.SpringAssessement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private long id;
    private CustomerDTO customer;
    private List<OrderLineDTO> orderLines;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
}
