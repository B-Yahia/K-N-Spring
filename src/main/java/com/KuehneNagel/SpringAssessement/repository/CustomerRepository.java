package com.KuehneNagel.SpringAssessement.repository;

import com.KuehneNagel.SpringAssessement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
