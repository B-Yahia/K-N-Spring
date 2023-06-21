package com.KuehneNagel.SpringAssessement.service;

import com.KuehneNagel.SpringAssessement.model.Customer;
import com.KuehneNagel.SpringAssessement.model.Order;
import com.KuehneNagel.SpringAssessement.model.OrderLine;
import com.KuehneNagel.SpringAssessement.model.Product;
import com.KuehneNagel.SpringAssessement.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order1;
    private Customer customer1;
    private Product product1;

    @BeforeEach
    public void setUp() {
        order1 = new Order();
        order1.setId(1L);
        order1.setOrderLines(new ArrayList<>());

        customer1 = new Customer();
        customer1.setId(1L);

        product1 = new Product();
        product1.setId(1L);
    }

    @Test
    public void saveOrderTest() {
        when(orderRepository.save(order1)).thenReturn(order1);

        var result = orderService.saveOrder(order1);

        assertEquals(order1, result);
        verify(orderRepository, times(1)).save(order1);
    }

    @Test
    public void findOrderByIdTest() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));

        var result = orderService.findOrderById(1L);

        assertEquals(order1, result);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void findOrderByIdNotFoundTest() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> orderService.findOrderById(2L));
        verify(orderRepository, times(1)).findById(2L);
    }

    @Test
    public void getAllOrdersTest() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1));

        var result = orderService.getAllOrders();

        assertEquals(1, result.size());
        assertEquals(order1, result.get(0));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void addOrderLineToOrderTest() {
        var orderLine = new OrderLine();
        orderLine.setQuantity(2);
        orderLine.setProduct(product1);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order1));
        when(orderRepository.save(order1)).thenReturn(order1);

        var result = orderService.addOrderLineToOrder(1L, orderLine);

        assertEquals(order1, result);
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(order1);
    }
}