package com.KuehneNagel.SpringAssessement.service;

import com.KuehneNagel.SpringAssessement.model.OrderLine;
import com.KuehneNagel.SpringAssessement.repository.OrderLineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderLineServiceTest {
    @Mock
    private OrderLineRepository orderLineRepository;

    @InjectMocks
    private OrderLineService orderLineService;

    private OrderLine orderLine1;
    private OrderLine orderLine2;

    @BeforeEach
    public void setUp() {
        orderLine1 = new OrderLine();
        orderLine1.setId(1L);
        orderLine1.setQuantity(2);

        orderLine2 = new OrderLine();
        orderLine2.setId(2L);
        orderLine2.setQuantity(3);
    }

    @Test
    public void saveOrderLineTest() {
        when(orderLineRepository.save(orderLine1)).thenReturn(orderLine1);

        var result = orderLineService.saveOrderLine(orderLine1);

        assertEquals(orderLine1, result);
        verify(orderLineRepository, times(1)).save(orderLine1);
    }

    @Test
    public void findByIdTest() {
        when(orderLineRepository.findById(1L)).thenReturn(Optional.of(orderLine1));

        var result = orderLineService.findById(1L);

        assertEquals(orderLine1, result);
        verify(orderLineRepository, times(1)).findById(1L);
    }

    @Test
    public void findByIdNotFoundTest() {
        when(orderLineRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> orderLineService.findById(3L));
        verify(orderLineRepository, times(1)).findById(3L);
    }

    @Test
    public void editQuantityTest() {
        when(orderLineRepository.findById(1L)).thenReturn(Optional.of(orderLine1));
        when(orderLineRepository.save(orderLine1)).thenReturn(orderLine1);

        var result = orderLineService.editQuantity(5, 1L);

        assertEquals(5, result.getQuantity());
        verify(orderLineRepository, times(1)).findById(1L);
        verify(orderLineRepository, times(1)).save(orderLine1);
    }

    @Test
    public void getAllOrderLinesTest() {
        when(orderLineRepository.findAll()).thenReturn(Arrays.asList(orderLine1, orderLine2));

        var result = orderLineService.getAllOrderLines();

        assertEquals(2, result.size());
        assertEquals(orderLine1, result.get(0));
        assertEquals(orderLine2, result.get(1));
        verify(orderLineRepository, times(1)).findAll();
    }


}