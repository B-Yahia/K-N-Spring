package com.KuehneNagel.SpringAssessement.mapper;

import com.KuehneNagel.SpringAssessement.dto.ProductDTO;
import com.KuehneNagel.SpringAssessement.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setSkuCode(product.getSkuCode());
        dto.setUnitPrice(product.getUnitPrice());
        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setSkuCode(dto.getSkuCode());
        product.setUnitPrice(dto.getUnitPrice());
        return product;
    }
}
