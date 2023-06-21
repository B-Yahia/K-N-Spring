package com.KuehneNagel.SpringAssessement.mapper;

import com.KuehneNagel.SpringAssessement.dto.OrderDTO;
import com.KuehneNagel.SpringAssessement.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OrderLineMapper orderLineMapper;

    public OrderDTO toDto(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomer(customerMapper.toDto(order.getCustomer()));
        dto.setOrderLines(order.getOrderLines().stream()
                .map(orderLineMapper::toDto)
                .collect(Collectors.toList()));
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setCustomer(customerMapper.toEntity(dto.getCustomer()));
        order.setOrderLines(dto.getOrderLines().stream()
                .map(orderLineMapper::toEntity)
                .collect(Collectors.toList()));
        order.setOrderDate(dto.getOrderDate());
        return order;
    }
}
