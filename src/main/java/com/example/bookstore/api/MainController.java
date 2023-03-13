package com.example.bookstore.api;


import com.example.bookstore.crudservices.Bookservice;
import com.example.bookstore.entities.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
public class MainController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    Bookservice bookservice;

    @GetMapping("/list")
    public ResponseEntity<List<Book>> get_all_book() {
        return ResponseEntity.ok(bookRepository.findAll());
    }


    @PostMapping("/cover-upload/{book-id}")
    public ResponseEntity<String> upload_cover(@PathVariable("book-id") int id, @RequestParam("file") MultipartFile file) throws IOException {
        bookservice.upload_cover(id, file);
        return ResponseEntity.ok("Pomyślnie");
    }


}
