package com.KuehneNagel.SpringAssessement.service;

import com.KuehneNagel.SpringAssessement.model.Customer;
import com.KuehneNagel.SpringAssessement.model.Order;
import com.KuehneNagel.SpringAssessement.model.OrderLine;
import com.KuehneNagel.SpringAssessement.model.Product;
import com.KuehneNagel.SpringAssessement.repository.CustomerRepository;
import com.KuehneNagel.SpringAssessement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderLineService orderLineService;

    public Order saveOrder(Order order) {
        return  orderRepository.save(order);
    }
    public Order findOrderById (Long id){
        return orderRepository.findById(id).orElseThrow(()-> new RuntimeException("No order found"));
    }
    public List<Order> getAllOrders (){
        return orderRepository.findAll();
    }

    public  Order addOrderLineToOrder (Long orderID, OrderLine orderLine){
        Order order = findOrderById(orderID);
        order.addOrderLineToOrder(orderLine);
        return saveOrder(order);
    }

    public List<Order> getOrdersByCustomer(Long id) {
        Customer customer = customerService.findCustomerById(id);
        return orderRepository.findByCustomer(customer);
    }

    public List<Order> getOrdersByProduct(Long id) {
        Product product= productService.findProductById(id);
        return  orderRepository.findByProduct(product);
    }

    public Order addExistingOrderLineToOrder(Long idOrder, Long idOrderL) {
        OrderLine orderLine = orderLineService.findById(idOrderL);
        return addOrderLineToOrder(idOrder,orderLine);
    }
}
