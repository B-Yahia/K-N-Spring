package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.model.Order;
import com.KuehneNagel.SpringAssessement.model.OrderLine;
import com.KuehneNagel.SpringAssessement.model.Product;
import com.KuehneNagel.SpringAssessement.service.OrderLineService;
import com.KuehneNagel.SpringAssessement.service.OrderService;
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
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order newOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
    }

    // Add an orderline to an existing order
    @PutMapping("/addOrderLine/{id}")
    public ResponseEntity<Order> addOrderLineToOrder (@RequestBody OrderLine orderLine ,@PathVariable Long id){
        Order newOrder =orderService.addOrderLineToOrder(id,orderLine);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }
    @PutMapping("/addOrderLine/{idOrder}/{idOrderL}")
    public ResponseEntity<Order> addExistingOrderLineToOrder (@PathVariable Long idOrder ,@PathVariable Long idOrderL){
        Order newOrder =orderService.addExistingOrderLineToOrder(idOrder,idOrderL);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }

    // get order by Id
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order =orderService.findOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    //returnes all the orders
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orderList =orderService.getAllOrders();
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    // find order by customer
    @GetMapping("/by-customer-id/{id}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long id){
        List<Order> orderList = orderService.getOrdersByCustomer(id);
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    // find order by product
    @GetMapping("/by-product-id/{id}")
    public ResponseEntity<List<Order>> getOrdersByProduct(@PathVariable Long id){
        List<Order> orderList  = orderService.getOrdersByProduct(id);
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }
}
