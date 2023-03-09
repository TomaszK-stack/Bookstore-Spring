package com.example.bookstore.repositories;

import com.example.bookstore.entities.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
    List<Orders> findAll();

}