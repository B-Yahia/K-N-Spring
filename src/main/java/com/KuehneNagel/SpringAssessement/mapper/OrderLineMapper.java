package com.KuehneNagel.SpringAssessement.mapper;

import com.KuehneNagel.SpringAssessement.dto.OrderLineDTO;
import com.KuehneNagel.SpringAssessement.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {
    @Autowired
    private ProductMapper productMapper;

    public OrderLineDTO toDto(OrderLine orderLine) {
        OrderLineDTO dto = new OrderLineDTO();
        dto.setId(orderLine.getId());
        dto.setProduct(productMapper.toDto(orderLine.getProduct()));
        dto.setQuantity(orderLine.getQuantity());
        return dto;
    }

    public OrderLine toEntity(OrderLineDTO dto) {
        OrderLine orderLine = new OrderLine();
        orderLine.setId(dto.getId());
        orderLine.setProduct(productMapper.toEntity(dto.getProduct()));
        orderLine.setQuantity(dto.getQuantity());
        return orderLine;
    }
}
