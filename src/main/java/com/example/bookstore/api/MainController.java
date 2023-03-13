package com.example.bookstore.api;


import com.example.bookstore.entities.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class MainController {

    @Autowired
    BookRepository bookRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/list")
    public ResponseEntity<List<Book>> get_all_book(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

}
