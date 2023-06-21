package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.dto.OrderLineDTO;
import com.KuehneNagel.SpringAssessement.mapper.OrderLineMapper;
import com.KuehneNagel.SpringAssessement.model.OrderLine;
import com.KuehneNagel.SpringAssessement.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderline")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private OrderLineMapper orderLineMapper;

    //Create an OrderLine
    @PostMapping
    public ResponseEntity<OrderLineDTO> createOrderLine(@RequestBody OrderLineDTO orderLineDTO){
        var newOrderLine = orderLineService.saveOrderLine(orderLineMapper.toEntity(orderLineDTO));
        return new ResponseEntity<>(orderLineMapper.toDto(newOrderLine), HttpStatus.CREATED);
    }

    //Find an OrderLine
    @GetMapping("/{id}")
    public ResponseEntity<OrderLineDTO> findOrderLine(@PathVariable Long id){
        var orderLine = orderLineService.findById(id);
        return new ResponseEntity<>(orderLineMapper.toDto(orderLine), HttpStatus.OK);
    }

    //Get all OrderLines
    @GetMapping
    public ResponseEntity<List<OrderLineDTO>> getAllOrderLines (){
        var orderLineList = orderLineService.getAllOrderLines();
        var orderLineDTOList = orderLineList.stream().map(orderLineMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderLineDTOList,HttpStatus.OK);
    }

    //Edite the quantity in OrderLine
    @PutMapping("/edit-quantity/{id}/{quantity}")
    public ResponseEntity<OrderLineDTO> editQuantityInOrderLine(@PathVariable Long id ,@PathVariable int quantity){
        var orderLine = orderLineService.editQuantity(quantity,id);
        return new ResponseEntity<>(orderLineMapper.toDto(orderLine),HttpStatus.OK);
    }
}
