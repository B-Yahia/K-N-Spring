package com.KuehneNagel.SpringAssessement.service;

import com.KuehneNagel.SpringAssessement.exception.ResourceNotFoundException;
import com.KuehneNagel.SpringAssessement.model.Product;
import com.KuehneNagel.SpringAssessement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product findProductById (Long id){
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
