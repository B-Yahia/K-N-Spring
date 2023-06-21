package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.model.OrderLine;
import com.KuehneNagel.SpringAssessement.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderline")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    //Create an OrderLine
    @PostMapping
    public ResponseEntity<OrderLine> createOrderLine(@RequestBody OrderLine orderLine){
        var newOrderLine = orderLineService.saveOrderLine(orderLine);
        return new ResponseEntity<>(newOrderLine, HttpStatus.CREATED);
    }

    //Find an OrderLine
    @GetMapping("/{id}")
    public ResponseEntity<OrderLine> findOrderLine(@PathVariable Long id){
        var orderLine = orderLineService.findById(id);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    //Get all OrderLines
    @GetMapping
    public ResponseEntity<List<OrderLine>> getAllOrderLines (){
        var orderLineList = orderLineService.getAllOrderLines();
        return new ResponseEntity<>(orderLineList,HttpStatus.OK);
    }

    //Edite the quantity in OrderLine
    @PutMapping("/edit-quantity/{id}/{quantity}")
    public ResponseEntity<OrderLine> editQuantityInOrderLine(@PathVariable Long id ,@PathVariable int quantity){
        var orderLine = orderLineService.editQuantity(quantity,id);
        return new ResponseEntity<>(orderLine,HttpStatus.OK);
    }
}
