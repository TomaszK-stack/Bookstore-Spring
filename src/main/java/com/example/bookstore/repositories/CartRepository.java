package com.example.bookstore.repositories;

import com.example.bookstore.entities.Cart;
import com.example.bookstore.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}