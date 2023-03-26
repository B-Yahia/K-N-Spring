package com.KuehneNagel.SpringAssessement.repository;

import com.KuehneNagel.SpringAssessement.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
}
