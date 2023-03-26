package com.KuehneNagel.SpringAssessement.repository;

import com.KuehneNagel.SpringAssessement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
