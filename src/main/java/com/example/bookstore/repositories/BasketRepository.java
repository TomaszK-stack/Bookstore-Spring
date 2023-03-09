package com.example.bookstore.repositories;

import com.example.bookstore.entities.Basket;
import com.example.bookstore.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BasketRepository extends CrudRepository<Basket, Long> {
    Optional<Basket> findByUser(User user);
}