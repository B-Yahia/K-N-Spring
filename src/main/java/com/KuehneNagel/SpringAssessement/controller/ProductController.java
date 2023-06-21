package com.KuehneNagel.SpringAssessement.controller;

import com.KuehneNagel.SpringAssessement.dto.ProductDTO;
import com.KuehneNagel.SpringAssessement.mapper.ProductMapper;
import com.KuehneNagel.SpringAssessement.model.Product;
import com.KuehneNagel.SpringAssessement.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    // Create product
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct (@Valid @RequestBody ProductDTO productDTO){
        var newProduct = productService.addProduct(productMapper.toEntity(productDTO));
        return new ResponseEntity<>(productMapper.toDto(newProduct), HttpStatus.CREATED);
    }

    //Find product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProduct(@PathVariable Long id){
        var product = productService.findProductById(id);
        return new ResponseEntity<>(productMapper.toDto(product), HttpStatus.OK);
    }

    // Get All products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProducts(){
        var productList = productService.findAllProducts();
        var productDTOList = productList.stream().map(productMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }
}
