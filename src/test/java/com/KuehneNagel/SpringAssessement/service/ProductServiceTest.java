package com.KuehneNagel.SpringAssessement.service;

import com.KuehneNagel.SpringAssessement.model.Product;
import com.KuehneNagel.SpringAssessement.repository.ProductRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setSkuCode("P1");
        product1.setUnitPrice(10.0);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setSkuCode("P2");
        product2.setUnitPrice(20.0);
    }

    @Test
    public void addProductTest() {
        when(productRepository.save(product1)).thenReturn(product1);

        Product result = productService.addProduct(product1);

        assertEquals(product1, result);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void findProductByIdTest() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Product result = productService.findProductById(1L);

        assertEquals(product1, result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void findProductByIdNotFoundTest() {
        when(productRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.findProductById(3L));
        verify(productRepository, times(1)).findById(3L);
    }

    @Test
    public void findAllProductsTest() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        List<Product> result = productService.findAllProducts();
        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));
        verify(productRepository, times(1)).findAll();
    }
}