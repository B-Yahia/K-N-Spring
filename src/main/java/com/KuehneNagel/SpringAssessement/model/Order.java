package com.KuehneNagel.SpringAssessement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Customer customer;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;


    public void addOrderLineToOrder (OrderLine orderLine){
        this.orderLines.add(orderLine);
    }
}
