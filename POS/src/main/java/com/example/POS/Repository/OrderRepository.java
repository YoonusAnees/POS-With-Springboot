package com.example.POS.Repository;

import com.example.POS.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
   Optional <Order> findByReferenceNo(String referenceNo);
}
