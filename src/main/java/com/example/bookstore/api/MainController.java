package com.example.bookstore.api;


import com.example.bookstore.entities.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
public class MainController {

    @Autowired
    BookRepository bookRepository;


    @GetMapping("/list")
    public List<Book> get_all_book(){
        return bookRepository.findAll();
    }

}
