package com.example.bookstore.repositories;

import com.example.bookstore.entities.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Integer> {
}
