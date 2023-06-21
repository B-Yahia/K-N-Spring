package com.KuehneNagel.SpringAssessement.controller;
import com.KuehneNagel.SpringAssessement.dto.OrderDTO;
import com.KuehneNagel.SpringAssessement.dto.OrderLineDTO;
import com.KuehneNagel.SpringAssessement.mapper.OrderLineMapper;
import com.KuehneNagel.SpringAssessement.mapper.OrderMapper;
import com.KuehneNagel.SpringAssessement.service.OrderLineService;
import com.KuehneNagel.SpringAssessement.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderLineMapper orderLineMapper;

    // Create an order
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO){
        var newOrder = orderService.saveOrder(orderMapper.toEntity(orderDTO));
        return new ResponseEntity<>(orderMapper.toDto(newOrder),HttpStatus.CREATED);
    }

    // Add an Orderline to an existing order
    @PutMapping("/addOrderLine/{id}")
    public ResponseEntity<OrderDTO> addOrderLineToOrder (@RequestBody OrderLineDTO orderLineDTO , @PathVariable Long id){
        var newOrder =orderService.addOrderLineToOrder(id,orderLineMapper.toEntity(orderLineDTO));
        return new ResponseEntity<>(orderMapper.toDto(newOrder),HttpStatus.OK);
    }

    // Add an existing Orderline to an existing order
    @PutMapping("/addOrderLine/{idOrder}/{idOrderL}")
    public ResponseEntity<OrderDTO> addExistingOrderLineToOrder (@PathVariable Long idOrder ,@PathVariable Long idOrderL){
        var newOrder =orderService.addExistingOrderLineToOrder(idOrder,idOrderL);
        return new ResponseEntity<>(orderMapper.toDto(newOrder),HttpStatus.OK);
    }

    // Get order by Id
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        var order =orderService.findOrderById(id);
        return new ResponseEntity<>(orderMapper.toDto(order),HttpStatus.OK);
    }

    //returns all the orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        var orderList =orderService.getAllOrders();
        var orderDTOList = orderList.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOList,HttpStatus.OK);
    }

    // Find orders by customerId
    @GetMapping("/by-customer-id/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(@PathVariable Long id){
        var orderList = orderService.getOrdersByCustomer(id);
        var orderDTOList = orderList.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOList,HttpStatus.OK);
    }

    // find orders by productId
    @GetMapping("/by-product-id/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByProduct(@PathVariable Long id){
        var orderList  = orderService.getOrdersByProduct(id);
        var orderDTOList = orderList.stream().map(orderMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOList,HttpStatus.OK);
    }
}
