package com.KuehneNagel.SpringAssessement.service;

import com.KuehneNagel.SpringAssessement.model.OrderLine;
import com.KuehneNagel.SpringAssessement.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    public OrderLine saveOrderLine (OrderLine orderLine){
        return orderLineRepository.save(orderLine);
    }

    public OrderLine findById (long id){
        return orderLineRepository.findById(id).orElseThrow(()-> new RuntimeException("No OrderLine found"));
    }

    public OrderLine editQuantity (int quantity , long id ){
        OrderLine orderLine = findById(id);
        orderLine.setQuantity(quantity);
        return saveOrderLine(orderLine);
    }


    public List<OrderLine> getAllOrderLines() {
        return orderLineRepository.findAll();
    }
}
