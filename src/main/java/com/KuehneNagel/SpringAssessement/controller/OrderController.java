package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.model.Order;
import com.KuehneNagel.SpringAssessement.model.OrderLine;
import com.KuehneNagel.SpringAssessement.model.Product;
import com.KuehneNagel.SpringAssessement.service.OrderLineService;
import com.KuehneNagel.SpringAssessement.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private OrderService orderService;

    // Create an order
    @PostMapping
    public ResponseEntity<Order> createOrder( @Valid @RequestBody Order order){
        var newOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
    }

    // Add an Orderline to an existing order
    @PutMapping("/addOrderLine/{id}")
    public ResponseEntity<Order> addOrderLineToOrder (@RequestBody OrderLine orderLine ,@PathVariable Long id){
        var newOrder =orderService.addOrderLineToOrder(id,orderLine);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }

    // Add an existing Orderline to an existing order
    @PutMapping("/addOrderLine/{idOrder}/{idOrderL}")
    public ResponseEntity<Order> addExistingOrderLineToOrder (@PathVariable Long idOrder ,@PathVariable Long idOrderL){
        var newOrder =orderService.addExistingOrderLineToOrder(idOrder,idOrderL);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }

    // Get order by Id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        var order =orderService.findOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    //returns all the orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        var orderList =orderService.getAllOrders();
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    // Find orders by customerId
    @GetMapping("/by-customer-id/{id}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long id){
        var orderList = orderService.getOrdersByCustomer(id);
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    // find orders by productId
    @GetMapping("/by-product-id/{id}")
    public ResponseEntity<List<Order>> getOrdersByProduct(@PathVariable Long id){
        var orderList  = orderService.getOrdersByProduct(id);
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }
}
