package com.example.bookstore.repositories;

import com.example.bookstore.entities.PaypalOrder;
import org.springframework.data.repository.CrudRepository;

public interface PaypalOrderRepository extends CrudRepository<PaypalOrder, Long> {
    PaypalOrder findByPaypalOrderId(String paypalOrderId);
}