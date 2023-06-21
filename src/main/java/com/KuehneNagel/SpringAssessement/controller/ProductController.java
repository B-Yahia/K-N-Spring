package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.model.Product;
import com.KuehneNagel.SpringAssessement.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Create product
    @PostMapping
    public ResponseEntity<Product> addProduct (@Valid @RequestBody Product product){
        var newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    //Find product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id){
        var product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Get All products
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts(){
        var productList = productService.findAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
