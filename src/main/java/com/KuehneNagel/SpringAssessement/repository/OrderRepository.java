package com.KuehneNagel.SpringAssessement.repository;

import com.KuehneNagel.SpringAssessement.model.Customer;
import com.KuehneNagel.SpringAssessement.model.Order;
import com.KuehneNagel.SpringAssessement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    List<Order> findBySubmissionDate(LocalDate date);

    @Query("SELECT DISTINCT o FROM Order o JOIN o.orderLines ol WHERE ol.product = :product")
    List<Order> findByProduct(Product product);

    @Query("SELECT o FROM Order o WHERE o.customer = :customer")
    List<Order> findByCustomer(Customer customer);
}
