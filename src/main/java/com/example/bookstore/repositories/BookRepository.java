package com.example.bookstore.repositories;

import com.example.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends  JpaSpecificationExecutor<Book>, CrudRepository<Book, Integer> {
    List<Book> findAll();

}